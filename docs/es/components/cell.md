# Celda

Celda de lista: disposición de tres secciones (icono / título + descripción / valor derecho y flecha), se usa a menudo en combinación con listas.

> Fuente del contrato: [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> Casos dorados: [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell) (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Uso básico

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">Texto del título</div>
      <div class="sim-cell__value">Valor derecho</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">Título con descripción</div>
        <div class="sim-cell__label">Texto de la descripción</div>
      </div>
      <div class="sim-cell__value">Valor</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Campo obligatorio</div>
      <div class="sim-cell__value">Detalle</div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
LhCell(
  title = "Título con descripción",
  label = "Texto de la descripción",
  value = "Valor",
  isLink = true,
  lhOnClick = { /* navegar */ },
)
```

## Obligatorio y enlace

`required` dibuja un asterisco rojo delante del título (`color.danger.default`); `isLink` dibuja un icono de flecha derecha al final.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Variantes de Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Obligatorio</div>
      <div class="sim-cell__value">Flecha →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">Sin borde</div>
      <div class="sim-cell__value">Valor</div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `title` | Título (texto principal) | `String?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `value` | Valor derecho (texto secundario, alineado a la derecha) | `String?` | `null` |
| `label` | Texto explicativo debajo del título | `String?` | `null` |
| `bordered` | Si se dibuja la línea separadora inferior (`color.border.lighter`) | `Boolean` | `true` |
| `clickable` | Si se puede pulsar; cuando es true, muestra la flecha indicadora derecha y responde a la pulsación | `Boolean` | `false` |
| `required` | Si se muestra el asterisco rojo de obligatorio delante del título (`color.danger.default`) | `Boolean` | `false` |
| `icon` | Nombre del icono izquierdo (sistema de iconos local); cuando está vacío no se dibuja | `String?` | `null` |
| `lhOnClick` | Devolución de llamada al pulsar; no se dispara cuando `clickable` es false | `(() -> Unit)?` | `null` |
| `titleContent` | Contenido del título (prevalece sobre title, slot default del spec) | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | Contenido del valor derecho (prevalece sobre value, slot value del spec) | `@Composable (() -> Unit)?` | `null` |

`clickable` controla a la vez «si se dibuja la flecha indicadora derecha» y «si la fila entera responde a la pulsación»: la pulsación solo se dispara cuando `clickable = true` y `lhOnClick` no es nulo.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `cell.paddingVertical` | Relleno vertical (referencia a `spacing.md`) |
| `cell.paddingHorizontal` | Relleno horizontal (`spacing.md`) |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | Tamaños de fuente del título / la descripción / el valor |
| `cell.gap` | Espacio entre el icono y el título (`spacing.xs`) |
| `icon.size.medium` | Tamaño del icono izquierdo y de la flecha indicadora |
| `cell.borderWidth` | Ancho de la línea separadora inferior |
| `typography.lineHeight.primary` | Interlineado del texto |
| `color.text.{primary,secondary}` | Colores del título / la descripción / el valor |
| `color.danger.default` | Asterisco rojo de obligatorio |
| `color.border.lighter` | Línea separadora inferior |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `title-and-value-render` | El título y el valor derecho renderizan texto | `LhCellTest.title_and_value_render` (Compose UI Test) |
| `label-renders-secondary-tone` | label toma el token del color de texto secundario | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | La marca de obligatorio toma el token del color de peligro | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | La línea separadora consume el token del borde | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | La pulsación dispara onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | El estado no pulsable no dispara | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # parte JVM
./gradlew :components:cell:connectedAndroidTest              # parte de interacción (requiere dispositivo)
```
