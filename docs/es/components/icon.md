# Icono

Componente base de iconos. Los iconos del resto de componentes (cell / input / navbar, etc.) se renderizan a través del registro local de este componente.

> Fuente del contrato: [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> Casos dorados: [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon) (5 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Tamaño y tono

Tres tamaños se mapean a los tokens `icon.size.*`; el tono `default` toma `color.text.primary` y los demás toman los colores semánticos.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Icon</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">Color predeterminado (text.primary)</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## Tonos semánticos

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tonos de Icon</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## Registro de iconos

`LhIcons` es el registro de nombres de iconos locales → `ImageVector` de Material; los nombres no registrados devuelven `null` (no se dibuja, no lanza excepciones):

| Nombre del icono | Vector de Material |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `name` | Nombre del icono (resuelto por el registro `LhIcons`) | `String` | — |
| `lhSize` | Tamaño del icono | `LhIconSize` | `Medium` |
| `tone` | Tono semántico | `LhIconTone` | `Default` |
| `contentDescription` | Descripción de accesibilidad; por defecto toma el nombre del icono; pasar `null` explícitamente lo declara como icono decorativo | `String?` | `name` |
| `lhOnClick` | Devolución de llamada al pulsar; cuando es null, no se puede pulsar | `(() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhIconSize

| Valor | Token de tamaño |
| --- | --- |
| `Small` | `icon.size.small` (16dp) |
| `Medium` | `icon.size.medium` (20dp) |
| `Large` | `icon.size.large` (24dp) |

### LhIconTone

| Valor | Token de color |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `icon.size.{small,medium,large}` | Tres tamaños |
| `color.text.primary` | Tono default |
| `color.{primary,success,warning,danger,info}.default` | Tonos semánticos |
| `color.text.{regular,secondary}` | Tonos regular / secondary |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `click-emits-onclick` | La pulsación dispara onClick | `LhIconTest.click_emits_onClick` (Compose UI Test) |
| `default-tone-uses-text-primary` | El tono predeterminado toma el token del color de texto principal | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | El tono primary toma el token del color principal | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | El tamaño pequeño toma el token de tamaño small (aserción inversa: large no se aplica) | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | Un nombre registrado se resuelve como vector; uno no registrado no se dibuja | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # parte JVM
./gradlew :components:icon:connectedAndroidTest            # parte de interacción (requiere dispositivo)
```
