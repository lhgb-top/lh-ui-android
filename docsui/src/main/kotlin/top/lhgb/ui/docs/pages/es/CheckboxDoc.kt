package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Casilla de verificación",
  lede = "Casilla de verificación controlada, admite el estado indeterminado (selección parcial) y la deshabilitación.",
  group = DocGroup.FORM,
  contract = "Fuente del contrato: `lh-ui-core/specs/checkbox.json` · Casos dorados: `cases/checkbox.json` (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Estados",
      prose = "El estado marcado / indeterminado es un cuadro relleno del color principal + indicador onPrimary (marca de verificación / barra); el estado desmarcado es solo borde.",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"Acepto los términos\",",
        ")",
      ),
    ),
    DocSection(
      title = "Tamaños",
      prose = "Tres tamaños de cuadro se mapean a los tokens `checkbox.size.*`.",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Si está marcada (obligatorio)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "Devolución de llamada de cambio de estado, con el nuevo valor", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "Si está en estado indeterminado", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "Si está deshabilitada", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Etiqueta de texto", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Tamaño del cuadro", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Contenido de la etiqueta (prevalece sobre label, slot default del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("Valor", "Token de tamaño"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large` (24dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "Tres tamaños de cuadro")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "Radio de esquina y ancho del borde del cuadro")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "Ancho del trazo de la marca de verificación / la barra")),
    DocRow(cells = listOf("`checkbox.gap`", "Espacio entre el cuadro y la etiqueta")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Color del fondo marcado / del indicador")),
    DocRow(cells = listOf("`color.border.base`", "Color del borde del estado desmarcado")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "Tres colores del estado deshabilitado")),
    DocRow(cells = listOf("`color.text.primary`", "Color de la etiqueta")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Tamaño de fuente de la etiqueta")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "La pulsación alterna y dispara onChange", "`LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "Una casilla marcada se desmarca al pulsar", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "En el estado indeterminado, la pulsación pasa a marcada", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "El estado deshabilitado no dispara", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "El estado marcado consume los tokens del color principal", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "El estado desmarcado consume el token del borde", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # parte JVM",
    "./gradlew :components:checkbox:connectedAndroidTest            # parte de interacción (requiere dispositivo)",
  ),
)
