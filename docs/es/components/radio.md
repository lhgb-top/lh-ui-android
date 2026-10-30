# Botón de radio

Botón de radio controlado: pulsar de nuevo un elemento ya seleccionado no lo desmarca (semántica de selección única).

> Fuente del contrato: [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> Casos dorados: [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio) (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Estados y grupo de selección

El componente por sí mismo no incluye lógica de grupo; la exclusión mutua la mantiene el host (se usa `value` para identificar el elemento dentro del grupo).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>No seleccionado</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>Seleccionado</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>Deshabilitado (no seleccionado)</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>Deshabilitado (seleccionado)</label></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("Opción uno" to "a", "Opción dos" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## Tamaños

Tres tamaños de anillo se mapean a los tokens `radio.size.*`; el diámetro del punto interior = tamaño × `radio.dotScale`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `checked` | Si está seleccionado (obligatorio) | `Boolean` | — |
| `onChange` | Se dispara al pasar de no seleccionado a seleccionado, con `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | Si está deshabilitado | `Boolean` | `false` |
| `label` | Etiqueta de texto | `String?` | `null` |
| `value` | Valor del elemento (identificador dentro del grupo; el componente por sí mismo no incluye lógica de grupo) | `String?` | `null` |
| `lhSize` | Tamaño del anillo | `LhRadioSize` | `Medium` |
| `labelContent` | Contenido de la etiqueta (prevalece sobre label, slot default del spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhRadioSize

| Valor | Token de tamaño | Diámetro del punto interior |
| --- | --- | --- |
| `Small` | `radio.size.small` (16dp) | 16dp × `radio.dotScale` (0.5) |
| `Medium` | `radio.size.medium` (20dp) | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large` (24dp) | 24dp × `radio.dotScale` |

La pulsación golpea toda la fila: solo cuando no está seleccionado ni deshabilitado pasa a estar seleccionado y dispara `onChange(true)`.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `radio.size.{small,medium,large}` | Tres tamaños de anillo |
| `radio.ringWidth` | Ancho del borde del anillo exterior |
| `radio.dotScale` | Proporción del punto interior respecto al diámetro del anillo exterior (fracción) |
| `radio.gap` | Espacio entre el anillo y la etiqueta |
| `color.primary.default` | Color del borde y del punto del estado seleccionado |
| `color.border.base` | Color del borde del estado no seleccionado |
| `color.disabled.{border,text}` | Colores del borde / el punto / el texto del estado deshabilitado |
| `color.text.primary` | Color de la etiqueta |
| `typography.fontSize.base` | Tamaño de fuente de la etiqueta |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | Una pulsación en un elemento no seleccionado dispara onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test) |
| `tap-checked-keeps-checked` | Una pulsación en un elemento seleccionado no dispara | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | El estado deshabilitado no dispara | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | El estado seleccionado consume los tokens del color principal | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | El estado no seleccionado consume el token del borde | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | El tamaño pequeño consume el token de tamaño small | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # parte JVM
./gradlew :components:radio:connectedAndroidTest             # parte de interacción (requiere dispositivo)
```
