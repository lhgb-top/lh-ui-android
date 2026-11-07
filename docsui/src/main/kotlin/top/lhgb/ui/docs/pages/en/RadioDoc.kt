package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Radio",
  lede = "A controlled radio. Tapping it again after selection does not deselect it (radio semantics).",
  group = DocGroup.FORM,
  contract = "Spec source: `lh-ui-core/specs/radio.json` · Golden cases: `cases/radio.json` (6 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "States and radio groups",
      prose = "The component itself contains no group logic; mutual exclusion within a group is maintained by the host (use `value` to identify the item within the group).",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"选项一\" to \"a\", \"选项二\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "Sizes",
      prose = "Three ring sizes map to the `radio.size.*` tokens; the inner dot diameter = size × `radio.dotScale`.",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Whether the radio is selected (required)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Triggered when the radio goes from unchecked to checked, carrying `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Whether the radio is disabled", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Label text", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "Radio value (identifier within the group; the component itself carries no group logic)", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Ring size", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Label content (overrides label; the spec's default slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("Value", "Size token", "Inner dot diameter"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small` (16dp)", "16dp × `radio.dotScale` (0.5)")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium` (20dp)", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large` (24dp)", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "Three ring sizes")),
    DocRow(cells = listOf("`radio.ringWidth`", "Outer ring stroke width")),
    DocRow(cells = listOf("`radio.dotScale`", "Ratio of the inner dot diameter to the outer ring diameter (a fraction)")),
    DocRow(cells = listOf("`radio.gap`", "Gap between the ring and the label")),
    DocRow(cells = listOf("`color.primary.default`", "Selected ring and dot color")),
    DocRow(cells = listOf("`color.border.base`", "Unchecked ring color")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "Disabled border / dot / text colors")),
    DocRow(cells = listOf("`color.text.primary`", "Label color")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Label font size")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "Tapping an unchecked radio triggers onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "Tapping a checked radio does not trigger", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "The disabled state does not trigger", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "Selected consumes the primary tokens", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "Unchecked consumes the border token", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "Small consumes the small size tokens", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # JVM part",
    "./gradlew :components:radio:connectedAndroidTest             # Interaction part (requires a device)",
  ),
)
