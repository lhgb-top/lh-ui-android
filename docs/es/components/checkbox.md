# Casilla de verificación

Casilla de verificación controlada, admite el estado indeterminado (selección parcial) y la deshabilitación.

> Fuente del contrato: [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> Casos dorados: [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox) (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Estados

El estado marcado / indeterminado es un cuadro relleno del color principal + indicador onPrimary (marca de verificación / barra); el estado desmarcado es solo borde.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>Desmarcada</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>Marcada</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>Indeterminada</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Deshabilitada (marcada)</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Deshabilitada (desmarcada)</label></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "Acepto los términos",
)
```

## Tamaños

Tres tamaños de cuadro se mapean a los tokens `checkbox.size.*`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `checked` | Si está marcada (obligatorio) | `Boolean` | — |
| `onCheckedChange` | Devolución de llamada de cambio de estado, con el nuevo valor | `(Boolean) -> Unit` | — |
| `indeterminate` | Si está en estado indeterminado | `Boolean` | `false` |
| `disabled` | Si está deshabilitada | `Boolean` | `false` |
| `label` | Etiqueta de texto | `String?` | `null` |
| `lhSize` | Tamaño del cuadro | `LhCheckboxSize` | `Medium` |
| `labelContent` | Contenido de la etiqueta (prevalece sobre label, slot default del spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhCheckboxSize

| Valor | Token de tamaño |
| --- | --- |
| `Small` | `checkbox.size.small` (16dp) |
| `Medium` | `checkbox.size.medium` (20dp) |
| `Large` | `checkbox.size.large` (24dp) |

La pulsación golpea toda la fila (cuadro + etiqueta): en el estado indeterminado, la pulsación pasa a marcada; en caso contrario, se alterna el estado marcado.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `checkbox.size.{small,medium,large}` | Tres tamaños de cuadro |
| `checkbox.borderRadius` / `checkbox.borderWidth` | Radio de esquina y ancho del borde del cuadro |
| `checkbox.checkWidth` | Ancho del trazo de la marca de verificación / la barra |
| `checkbox.gap` | Espacio entre el cuadro y la etiqueta |
| `color.primary.default` / `color.onPrimary` | Color del fondo marcado / del indicador |
| `color.border.base` | Color del borde del estado desmarcado |
| `color.disabled.{bg,border,text}` | Tres colores del estado deshabilitado |
| `color.text.primary` | Color de la etiqueta |
| `typography.fontSize.base` | Tamaño de fuente de la etiqueta |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | La pulsación alterna y dispara onChange | `LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-unchecks` | Una casilla marcada se desmarca al pulsar | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | En el estado indeterminado, la pulsación pasa a marcada | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | El estado deshabilitado no dispara | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | El estado marcado consume los tokens del color principal | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | El estado desmarcado consume el token del borde | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # parte JVM
./gradlew :components:checkbox:connectedAndroidTest            # parte de interacción (requiere dispositivo)
```
