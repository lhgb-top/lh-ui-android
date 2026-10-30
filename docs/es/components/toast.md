# Toast (notificación breve)

Superposición en píldora color tinta, con visibilidad y temporizador autogestionados, para retroalimentación ligera de operaciones. No bloquea la interacción del usuario.

> Fuente del contrato: [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> Casos dorados: [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast) (6 casos: 4 de máquina de estados + 2 de renderizado, todos traducidos a pruebas locales)

## Uso

Toast es una **máquina de estados autogestionada**: el estado lo gestiona `LhToastState`; el host llama a `show(text)` para mostrarlo y a `hide()` para ocultarlo; al vencer el temporizador se oculta automáticamente y se dispara `onClose`. Cuando es visible, el componente se autogestiona con `tick` fotograma a fotograma; el host también puede tomar el control del temporizador llamándolo manualmente.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">Operación exitosa</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
val state = rememberLhToastState(onClose = { /* opcional: registrar el cierre */ })
Column {
  LhButton(lhOnClick = { state.show("Guardado") }) { Text("Lanzar") }
  LhToast(state = state)
}
```

## Tipos semánticos

Con un tipo distinto de `default` se dibuja antes del texto un punto semántico de diámetro igual al tamaño de fuente.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tipos de Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>Aviso de éxito</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>Aviso de advertencia</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>Aviso de error</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>Aviso principal</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## Posición

`lhPosition` toma `top` / `center` / `bottom`, con un margen de `spacing.xl` en cada extremo; el ancho se obtiene de la medición del texto y se limita al 80% del ancho disponible.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Posición de Toast</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">Arriba</div>
    <div class="sim-toast sim-toast--bottom">Abajo</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `state` | Máquina de estados del toast (gestiona la visibilidad, el texto y el temporizador) | `LhToastState` | — |
| `type` | Tipo semántico; cuando es distinto de default, se dibuja un punto semántico | `LhToastType` | `default` |
| `lhPosition` | Posición dentro del área disponible | `LhToastPosition` | `center` |
| `modifier` | Modifier (aplicado a la píldora) | `Modifier` | `Modifier` |

### LhToastState

| Miembro | Descripción |
| --- | --- |
| `show(text: String)` | Muestra y reinicia el temporizador |
| `hide()` | Oculta inmediatamente, **sin disparar onClose** |
| `tick(deltaMs: Int): Boolean` | Avanza el temporizador; al vencer, oculta, dispara onClose y devuelve true |
| `visible` / `text` / `duration` | Estado actual (observable) |

Fábrica: `rememberLhToastState(duration = 2000, onClose = {})`.

### LhToastType / LhToastPosition

| Valor | Color del punto |
| --- | --- |
| `default` | Sin punto |
| `primary` / `success` / `warning` / `danger` / `info` | Color semántico correspondiente |

`LhToastPosition`: `top` / `center` / `bottom`.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `toast.maxWidth` | Ancho máximo de la píldora (240dp; en la práctica, el menor entre el 80% del ancho disponible y este valor) |
| `toast.borderRadius` | Radio de esquina de la píldora (sigue `radius.base`) |
| `toast.paddingHorizontal` / `toast.paddingVertical` | Relleno de la píldora |
| `toast.fontSize` | Tamaño de fuente del texto (el diámetro del punto toma este mismo valor) |
| `toast.gap` | Espacio entre el punto y el texto |
| `zIndex.toast` | Alineación semántica de la jerarquía (el `Popup` de Compose aporta el nivel de jerarquía de ventana) |
| `color.background.overlay` (mapeo ink de la capa de temas) | Color de fondo de la píldora |
| `color.onPrimary` | Color del texto |
| `color.{primary,success,warning,danger,info}.default` | Colores semánticos del punto |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `show-makes-visible` | Tras show, es visible y renderiza texto | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | Al vencer el temporizador se oculta y dispara onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | Mientras no vence el temporizador, se mantiene visible sin disparar onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide no dispara onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | El tipo success consume el color de éxito | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | Cuando no es visible, no se renderiza | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # parte JVM
./gradlew :components:toast:connectedAndroidTest                 # parte de interacción (requiere dispositivo)
```
