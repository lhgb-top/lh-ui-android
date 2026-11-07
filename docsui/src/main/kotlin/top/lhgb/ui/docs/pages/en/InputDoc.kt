package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Input",
  lede = "A controlled single-line input that supports size, disabled, readonly, clearable, password masking, and a maximum length.",
  group = DocGroup.FORM,
  contract = "Spec source: `lh-ui-core/specs/input.json` · Golden cases: `cases/input.json` (8 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Basic usage",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"请输入内容\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Size and focus",
      prose = "Three sizes map to the `input.height` / `input.paddingHorizontal` / `input.fontSize` tokens; when focused, the border takes the `color.focus` color and widens to `input.focusRingWidth`.",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Controlled value (required)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Value change callback (required)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Placeholder text", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Input type (plaintext / password)", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "Size", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Whether the input is disabled", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "Whether the input is read-only (not editable, but without the disabled style)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "Whether to show the clear button", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Maximum length (extra input is truncated)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Focus / blur callbacks", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "Prefix / suffix slots", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("Value", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small` (32dp)", "`input.paddingHorizontal.small` (12dp)", "`input.fontSize.small` (13sp)")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium` (40dp)", "`input.paddingHorizontal.medium` (16dp)", "`input.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`input.height.large` (48dp)", "`input.paddingHorizontal.large` (20dp)", "`input.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "Three heights")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "Three horizontal paddings")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "Three font sizes")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "Corner radius / border width / focus border width")),
    DocRow(cells = listOf("`input.clearIconSize`", "Clear icon size")),
    DocRow(cells = listOf("`color.focus`", "Focus border color")),
    DocRow(cells = listOf("`color.text.placeholder`", "Placeholder text color")),
    DocRow(cells = listOf("`color.border.base`", "Default border color")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "The three disabled colors")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "Input triggers onValueChange", "`LhInputTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "The clear button emits an empty value", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "The disabled state accepts no input", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "The read-only state accepts no input", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "The placeholder color takes the placeholder token", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "Focus consumes the focus tokens (unfocused takes the neutral border)", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "Small consumes the small size tokens", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "Input beyond the maximum length is truncated", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # JVM part",
    "./gradlew :components:input:connectedAndroidTest              # Interaction part (requires a device)",
  ),
)
