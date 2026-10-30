# Campo de entrada

Campo de entrada controlado de una sola línea: admite tamaño, deshabilitación, solo lectura, limpieza, máscara de contraseña y longitud máxima.

> Fuente del contrato: [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> Casos dorados: [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input) (8 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Uso básico

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="Introduce el contenido" /></div>
    <div class="sim-row"><input class="sim-input" value="青花瓷 (porcelana azul y blanco)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="Deshabilitado" disabled /></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "Introduce el contenido",
  onValueChange = { value = it },
)
```

## Tamaño y focus

Tres tamaños se mapean a los tokens `input.height` / `input.paddingHorizontal` / `input.fontSize`; al recibir el focus, el borde pasa a `color.focus` y se ensancha hasta `input.focusRingWidth`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium (demostración del estado focus)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `value` | Valor controlado (obligatorio) | `String` | — |
| `onValueChange` | Devolución de llamada de cambio de valor (obligatorio) | `(String) -> Unit` | — |
| `placeholder` | Texto del marcador de posición | `String?` | `null` |
| `type` | Tipo de entrada (texto plano / contraseña) | `LhInputType` | `Text` |
| `lhSize` | Tamaño | `LhInputSize` | `Medium` |
| `disabled` | Si está deshabilitado | `Boolean` | `false` |
| `readonly` | Si es de solo lectura (no editable, pero sin el aspecto deshabilitado) | `Boolean` | `false` |
| `clearable` | Si se muestra el botón de limpieza | `Boolean` | `false` |
| `maxlength` | Longitud máxima (trunca al superarla) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Devoluciones de llamada de focus / pérdida de focus | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | Slots de prefijo / sufijo | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhInputSize

| Valor | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small` (32dp) | `input.paddingHorizontal.small` (12dp) | `input.fontSize.small` (13sp) |
| `Medium` | `input.height.medium` (40dp) | `input.paddingHorizontal.medium` (16dp) | `input.fontSize.medium` (14sp) |
| `Large` | `input.height.large` (48dp) | `input.paddingHorizontal.large` (20dp) | `input.fontSize.large` (16sp) |

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `input.height.{small,medium,large}` | Tres alturas |
| `input.paddingHorizontal.{small,medium,large}` | Tres rellenos horizontales |
| `input.fontSize.{small,medium,large}` | Tres tamaños de fuente |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | Radio de esquina / ancho del borde / ancho del anillo de focus |
| `input.clearIconSize` | Tamaño del icono de limpieza |
| `color.focus` | Color del borde en focus |
| `color.text.placeholder` | Color del marcador de posición |
| `color.border.base` | Color del borde predeterminado |
| `color.disabled.{bg,text,border}` | Tres colores del estado deshabilitado |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `input-emits-onvaluechange` | Al escribir se dispara onValueChange | `LhInputTest.input_emits_onValueChange` (Compose UI Test) |
| `clearable-emits-empty-value` | El botón de limpieza emite un valor vacío | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | El estado deshabilitado no admite entrada | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | El estado de solo lectura no admite entrada | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | El marcador de posición toma el token del marcador | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | El focus consume el token focus (al perderlo, borde neutro) | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | El tamaño pequeño consume los tokens de tamaño small | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | Al superar la longitud máxima se trunca | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # parte JVM
./gradlew :components:input:connectedAndroidTest              # parte de interacción (requiere dispositivo)
```
