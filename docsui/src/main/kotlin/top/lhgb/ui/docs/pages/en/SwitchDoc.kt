package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Switch",
  lede = "A two-state toggle with a capsule track and a circular thumb, supporting a loading-state placeholder.",
  group = DocGroup.FORM,
  contract = "Spec source: `lh-ui-core/specs/switch.json` · Golden cases: `cases/switch.json` (6 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "States",
      prose = "When on, the track takes the primary color; when off, it takes the border color. The thumb diameter = height - 2 × `switch.gap`, and the slide animation uses `motion.duration.base`.",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "Sizes",
      prose = "Three sizes map to the `switch.width` / `switch.height` tokens.",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Whether the switch is on (required)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "State change callback, carrying the new state", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Whether the switch is disabled", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "Whether the switch is loading (a spinner is shown in the thumb position; taps are ignored)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "Size", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("Value", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small` (36dp)", "`switch.height.small` (20dp)")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium` (44dp)", "`switch.height.medium` (24dp)")),
        DocRow(cells = listOf("`Large`", "`switch.width.large` (52dp)", "`switch.height.large` (28dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "Three track widths")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "Three track heights")),
    DocRow(cells = listOf("`switch.gap`", "Padding between the thumb and the track (thumb diameter = height - 2 × gap)")),
    DocRow(cells = listOf("`color.primary.default`", "On-track color")),
    DocRow(cells = listOf("`color.border.base`", "Off-track color")),
    DocRow(cells = listOf("`color.onPrimary`", "Thumb color")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "Disabled track / thumb colors")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "Thumb slide animation")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Loading ring stroke width (reused)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "A tap toggles the state and emits onChange", "`LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "Tapping the on state turns it off", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "The disabled state does not trigger", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "The loading state does not trigger", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "On consumes the primary token", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "Off consumes the border token", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # JVM part",
    "./gradlew :components:switch:connectedAndroidTest            # Interaction part (requires a device)",
  ),
)
