package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Celda",
  lede = "Celda de lista: disposición de tres secciones (icono / título + descripción / valor derecho y flecha), se usa a menudo en combinación con listas.",
  group = DocGroup.BASIC,
  contract = "Fuente del contrato: `lh-ui-core/specs/cell.json` · Casos dorados: `cases/cell.json` (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Uso básico",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"Título con descripción\",",
        "  label = \"Texto de la descripción\",",
        "  value = \"Valor\",",
        "  isLink = true,",
        "  lhOnClick = { /* navegar */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Obligatorio y enlace",
      prose = "`required` dibuja un asterisco rojo delante del título (`color.danger.default`); `isLink` dibuja un icono de flecha derecha al final.",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Título (texto principal)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "Valor derecho (texto secundario, alineado a la derecha)", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "Texto explicativo debajo del título", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Si se dibuja la línea separadora inferior (`color.border.lighter`)", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "Si se puede pulsar; cuando es true, muestra la flecha indicadora derecha y responde a la pulsación", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "Si se muestra el asterisco rojo de obligatorio delante del título (`color.danger.default`)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Nombre del icono izquierdo (sistema de iconos local); cuando está vacío no se dibuja", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "Devolución de llamada al pulsar; no se dispara cuando `clickable` es false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "Contenido del título (prevalece sobre title, slot default del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "Contenido del valor derecho (prevalece sobre value, slot value del spec)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "Relleno vertical (referencia a `spacing.md`)")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "Relleno horizontal (`spacing.md`)")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "Tamaños de fuente del título / la descripción / el valor")),
    DocRow(cells = listOf("`cell.gap`", "Espacio entre el icono y el título (`spacing.xs`)")),
    DocRow(cells = listOf("`icon.size.medium`", "Tamaño del icono izquierdo y de la flecha indicadora")),
    DocRow(cells = listOf("`cell.borderWidth`", "Ancho de la línea separadora inferior")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Interlineado del texto")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "Colores del título / la descripción / el valor")),
    DocRow(cells = listOf("`color.danger.default`", "Asterisco rojo de obligatorio")),
    DocRow(cells = listOf("`color.border.lighter`", "Línea separadora inferior")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "El título y el valor derecho renderizan texto", "`LhCellTest.title_and_value_render` (Compose UI Test)")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label toma el token del color de texto secundario", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "La marca de obligatorio toma el token del color de peligro", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "La línea separadora consume el token del borde", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "La pulsación dispara onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "El estado no pulsable no dispara", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # parte JVM",
    "./gradlew :components:cell:connectedAndroidTest              # parte de interacción (requiere dispositivo)",
  ),
)
