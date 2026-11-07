package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Campo de entrada",
  lede = "Campo de entrada controlado de una sola línea: admite tamaño, deshabilitación, solo lectura, limpieza, máscara de contraseña y longitud máxima.",
  group = DocGroup.FORM,
  contract = "Fuente del contrato: `lh-ui-core/specs/input.json` · Casos dorados: `cases/input.json` (8 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Uso básico",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"Introduce el contenido\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Tamaño y focus",
      prose = "Tres tamaños se mapean a los tokens `input.height` / `input.paddingHorizontal` / `input.fontSize`; al recibir el focus, el borde pasa a `color.focus` y se ensancha hasta `input.focusRingWidth`.",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Valor controlado (obligatorio)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Devolución de llamada de cambio de valor (obligatorio)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Texto del marcador de posición", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Tipo de entrada (texto plano / contraseña)", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "Tamaño", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Si está deshabilitado", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "Si es de solo lectura (no editable, pero sin el aspecto deshabilitado)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "Si se muestra el botón de limpieza", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Longitud máxima (trunca al superarla)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Devoluciones de llamada de focus / pérdida de focus", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "Slots de prefijo / sufijo", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("Valor", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small` (32dp)", "`input.paddingHorizontal.small` (12dp)", "`input.fontSize.small` (13sp)")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium` (40dp)", "`input.paddingHorizontal.medium` (16dp)", "`input.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`input.height.large` (48dp)", "`input.paddingHorizontal.large` (20dp)", "`input.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "Tres alturas")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "Tres rellenos horizontales")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "Tres tamaños de fuente")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "Radio de esquina / ancho del borde / ancho del anillo de focus")),
    DocRow(cells = listOf("`input.clearIconSize`", "Tamaño del icono de limpieza")),
    DocRow(cells = listOf("`color.focus`", "Color del borde en focus")),
    DocRow(cells = listOf("`color.text.placeholder`", "Color del marcador de posición")),
    DocRow(cells = listOf("`color.border.base`", "Color del borde predeterminado")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Tres colores del estado deshabilitado")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "Al escribir se dispara onValueChange", "`LhInputTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "El botón de limpieza emite un valor vacío", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "El estado deshabilitado no admite entrada", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "El estado de solo lectura no admite entrada", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "El marcador de posición toma el token del marcador", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "El focus consume el token focus (al perderlo, borde neutro)", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "El tamaño pequeño consume los tokens de tamaño small", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "Al superar la longitud máxima se trunca", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # parte JVM",
    "./gradlew :components:input:connectedAndroidTest              # parte de interacción (requiere dispositivo)",
  ),
)
