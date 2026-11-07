package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTagPlainPreview
import top.lhgb.ui.docs.previews.DocTagSizeAndMarkPreview
import top.lhgb.ui.docs.previews.DocTagTypesPreview

val tagDoc = DocEntry(
  id = "tag",
  title = "Tag",
  lede = "A semantic-color tag that supports plain (outlined), fully rounded marks, and a close button.",
  group = DocGroup.BASIC,
  contract = "Spec source: `lh-ui-core/specs/tag.json` · Golden cases: `cases/tag.json` (6 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Types",
      prose = "Six semantic types determine the background and text color.",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"成功\") }",
      ),
    ),
    DocSection(
      title = "Plain",
      prose = "When `plain` is set, the tag has a transparent background with a semantic-color border and text.",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "Size and mark",
      prose = "Three sizes map to the `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` tokens; `mark` uses a fully rounded radius of half the height.",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "Tag text (a convenience form; mutually exclusive with `content`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Semantic type", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Size", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "Whether the tag is outlined (transparent background + semantic-color border)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "Whether the corners are fully rounded (half the height)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "Whether to show the close button", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "Close callback; not triggered when `closable` is false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "Tag content (overrides text; the spec's default slot)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("Value", "Background (non-plain)", "Border/text (plain)"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + `color.border.base` border", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("Value", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small` (20dp)", "`tag.paddingHorizontal.small` (8dp)", "`tag.fontSize.small` (12sp)")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium` (24dp)", "`tag.paddingHorizontal.medium` (10dp)", "`tag.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`tag.height.large` (28dp)", "`tag.paddingHorizontal.large` (12dp)", "`tag.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "Three heights")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "Three horizontal paddings")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "Three font sizes")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "Corner radius and border width")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Semantic background / border / text colors")),
    DocRow(cells = listOf("`color.onPrimary`", "Non-plain text color")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "The three disabled colors")),
    DocRow(cells = listOf("`spacing.xs`", "Gap between the close button and the text")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "The default type takes a neutral background + primary text color + a base border", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "The primary type consumes the primary tokens", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain consumes the semantic light-background tokens", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "Small consumes the small tokens (with a reverse assertion that large is not applied)", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round makes the corner radius half the height", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "Tapping close triggers onClose", "`LhTagTest.closable_tap_emits_onClose` (Compose UI Test)")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # JVM part",
    "./gradlew :components:tag:connectedAndroidTest            # Interaction part (requires a device)",
  ),
)
