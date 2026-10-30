# Botón

Botón de acción básico. Dispara una acción inmediata y admite tipo, tamaño, carga, deshabilitación, icono y fondo de patrón chino.

> Fuente del contrato: [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> Casos dorados: [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button) (8 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Tipos

Seis tipos determinan el color de fondo y el color del texto.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tipos de Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">Predeterminado</button>
      <button class="sim-btn sim-btn--primary">Principal</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">Éxito</button>
      <button class="sim-btn sim-btn--warning">Advertencia</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">Peligro</button>
      <button class="sim-btn sim-btn--info">Información</button>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("Botón principal") }
```

## Tamaños

Tres tamaños se mapean a los tokens `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Botón pequeño</button>
      <button class="sim-btn">Botón mediano</button>
      <button class="sim-btn sim-btn--large">Botón grande</button>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## Estados

Ni el estado deshabilitado ni el de carga disparan `lhOnClick`; cuando ambos están presentes, **la deshabilitación tiene prioridad** (caso dorado `disabled-precedes-loading`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Estados de Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Normal</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Deshabilitado</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Cargando
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Deshabilitado prioritario</button>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## Icono y fondo de patrón

El nombre del icono se resuelve contra el sistema de iconos local (`LhButtonIcons`, admite actualmente search / refresh / check / close; los nombres no registrados no se dibujan).
El fondo de patrón consume el token `color.decoration`: se superpone al color de fondo y queda debajo del texto, sin afectar al contraste.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icono y patrón</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Buscar</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>Predeterminado</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Patrón principal</button>
      <button class="sim-btn sim-btn--pattern">Patrón predeterminado</button>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## Temas claro y oscuro

Cambia el tema del sitio en la esquina superior derecha y los colores de los botones dentro del marco del simulador cambian; en el dispositivo, se alterna con `LhTheme(isDark = true)`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Botones oscuros</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Botón principal</button>
      <button class="sim-btn">Botón predeterminado</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Deshabilitado</button>
      <button class="sim-btn sim-btn--success">Éxito</button>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo; los valores claro/oscuro provienen de la instantánea de tokens de core</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `lhOnClick` | Devolución de llamada al pulsar; no se dispara cuando `disabled` o `loading` es true | `() -> Unit` | — |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `type` | Tipo de botón, determina el color de fondo y del texto | `LhButtonType` | `Default` |
| `lhSize` | Tamaño del botón, mapeado a los tokens de tamaño | `LhButtonSize` | `Medium` |
| `disabled` | Si está deshabilitado | `Boolean` | `false` |
| `loading` | Si está cargando (muestra un indicador, no dispara lhOnClick) | `Boolean` | `false` |
| `icon` | Nombre del icono (resuelto por el sistema de iconos local) | `String?` | `null` |
| `bgPattern` | Si se dibuja el patrón decorativo chino | `Boolean` | `false` |
| `content` | Contenido de texto del botón (el slot default del spec) | `@Composable () -> Unit` | — |

### LhButtonType

| Valor | Fondo | Color del texto |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| Valor | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small` (32dp) | `button.paddingHorizontal.small` (16dp) | `button.fontSize.small` (13sp) | `button.iconSize.small` (16dp) |
| `Medium` | `button.height.medium` (40dp) | `button.paddingHorizontal.medium` (20dp) | `button.fontSize.medium` (14sp) | `button.iconSize.medium` (18dp) |
| `Large` | `button.height.large` (48dp) | `button.paddingHorizontal.large` (24dp) | `button.fontSize.large` (16sp) | `button.iconSize.large` (20dp) |

La anulación del estado deshabilitado: fondo `color.disabled.bg`, texto `color.disabled.text`, borde `color.disabled.border`.

## Referencia de tokens

Tokens consumidos por el botón (alineados uno a uno con los `tokenRefs` de `specs/button.json`):

| Token | Uso |
| --- | --- |
| `button.height.{small,medium,large}` | Tres alturas |
| `button.paddingHorizontal.{small,medium,large}` | Tres rellenos horizontales |
| `button.fontSize.{small,medium,large}` | Tres tamaños de fuente |
| `button.iconSize.{small,medium,large}` | Tres tamaños de icono |
| `button.borderRadius` / `button.borderWidth` | Radio de esquina (sigue `radius.base`) y ancho del borde |
| `button.fontWeight` | Peso de la fuente (sigue `typography.fontWeight.primary`) |
| `color.primary.default` / `color.onPrimary` | Fondo / texto del tipo primary |
| `color.{success,warning,danger,info}.default` | Fondos de los tipos semánticos |
| `color.background.default` / `color.text.primary` / `color.border.base` | Fondo / texto / borde del tipo predeterminado |
| `color.disabled.{bg,text,border}` | Tres colores del estado deshabilitado |
| `color.decoration` | Color del patrón |
| `shadow.lighter` | Elevación del tipo predeterminado |
| `motion.duration.fast` / `motion.easing.easeInOut` | Animación de retroalimentación de la pulsación |

## Referencia de casos dorados

Cómo se mapean las 8 aserciones de `cases/button.json` a las pruebas locales:

| case id | Aserción | Test local |
| --- | --- | --- |
| `click-emits-onclick` | La pulsación dispara onClick una sola vez | `LhButtonTest.click_emits_onClick` (Compose UI Test) |
| `disabled-blocks-click` | El estado deshabilitado no dispara | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | El estado de carga no dispara | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | La deshabilitación tiene prioridad | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | El tipo predeterminado renderiza texto | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | El tipo primary consume los tokens del color principal | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | El tamaño pequeño consume los tokens de tamaño small | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | El fondo de patrón consume el color decorativo | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | Un nombre registrado renderiza un vector; uno no registrado no dibuja nada | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # parte JVM
./gradlew :components:button:connectedAndroidTest        # parte de interacción (requiere dispositivo)
```
