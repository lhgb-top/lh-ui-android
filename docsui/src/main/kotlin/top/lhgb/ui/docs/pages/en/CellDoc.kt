package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Cell",
  lede = "A list cell with a three-part layout — icon / title + label / right-side value plus arrow — commonly composed into lists.",
  group = DocGroup.BASIC,
  contract = "Spec source: `lh-ui-core/specs/cell.json` · Golden cases: `cases/cell.json` (6 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Basic usage",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"带描述的标题\",",
        "  label = \"描述文字\",",
        "  value = \"值\",",
        "  isLink = true,",
        "  lhOnClick = { /* navigate */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Required and link",
      prose = "`required` draws a red asterisk in front of the title (`color.danger.default`); `isLink` draws a right-arrow icon at the end.",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Title (primary text)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "Right-side value (secondary text, right-aligned)", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "Description text below the title", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Whether to draw the bottom divider (`color.border.lighter`)", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "Whether the cell is tappable; when true, shows a trailing indicator arrow and responds to taps", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "Whether to show the red required asterisk in front of the title (`color.danger.default`)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Left-side icon name (local icon system); nothing is drawn when empty", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "Tap callback; not triggered when `clickable` is false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "Title content (overrides title; the spec's default slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "Right-side value content (overrides value; the spec's value slot)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "Vertical padding (references `spacing.md`)")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "Horizontal padding (`spacing.md`)")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "Title / label / value font sizes")),
    DocRow(cells = listOf("`cell.gap`", "Gap between the icon and the title (`spacing.xs`)")),
    DocRow(cells = listOf("`icon.size.medium`", "Left-side icon and indicator arrow size")),
    DocRow(cells = listOf("`cell.borderWidth`", "Bottom divider width")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Text line height")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "Title / label / value colors")),
    DocRow(cells = listOf("`color.danger.default`", "The red required asterisk")),
    DocRow(cells = listOf("`color.border.lighter`", "The bottom divider")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "The title and right value render their text", "`LhCellTest.title_and_value_render` (Compose UI Test)")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "The label takes the secondary text token", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "The required marker takes the danger token", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "The divider consumes the border token", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "A tap triggers onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "The non-clickable state does not trigger", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # JVM part",
    "./gradlew :components:cell:connectedAndroidTest              # Interaction part (requires a device)",
  ),
)
