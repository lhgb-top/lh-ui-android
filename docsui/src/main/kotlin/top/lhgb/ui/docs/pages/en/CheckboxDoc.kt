package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Checkbox",
  lede = "A controlled checkbox that supports the indeterminate state and disabled.",
  group = DocGroup.FORM,
  contract = "Spec source: `lh-ui-core/specs/checkbox.json` · Golden cases: `cases/checkbox.json` (6 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "States",
      prose = "Checked and indeterminate render a solid primary background with an `onPrimary` indicator (a checkmark or a dash); unchecked is outlined only.",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"同意协议\",",
        ")",
      ),
    ),
    DocSection(
      title = "Sizes",
      prose = "Three box sizes map to the `checkbox.size.*` tokens.",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Whether the checkbox is checked (required)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "Checked-state change callback, carrying the new checked state", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "Whether the checkbox is indeterminate", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "Whether the checkbox is disabled", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Label text", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Box size", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Label content (overrides label; the spec's default slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("Value", "Size token"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large` (24dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "Three box sizes")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "Box corner radius and border width")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "Checkmark / dash stroke width")),
    DocRow(cells = listOf("`checkbox.gap`", "Gap between the box and the label")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Checked background / indicator color")),
    DocRow(cells = listOf("`color.border.base`", "Unchecked border color")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "The three disabled colors")),
    DocRow(cells = listOf("`color.text.primary`", "Label color")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Label font size")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "A tap toggles the state and emits onChange", "`LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "Tapping the checked state unchecks it", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "Tapping the indeterminate state selects it", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "The disabled state does not trigger", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "Checked consumes the primary tokens", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "Unchecked consumes the border token", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # JVM part",
    "./gradlew :components:checkbox:connectedAndroidTest           # Interaction part (requires a device)",
  ),
)
