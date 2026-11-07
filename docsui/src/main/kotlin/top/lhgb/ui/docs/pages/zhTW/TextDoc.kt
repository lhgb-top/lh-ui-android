package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextLevelsPreview
import top.lhgb.ui.docs.previews.DocTextTonesPreview
import top.lhgb.ui.docs.previews.DocTextWeightPreview

val textDoc = DocEntry(
  id = "text",
  title = "Text 文字",
  lede = "文字展示原語：消費全局排版令牌，支援字號級別、字重、色調與對齊。",
  group = DocGroup.BASIC,
  contract = "契約源：`lh-ui-core/specs/text.json` · 黃金用例：`cases/text.json`（5 條，已翻譯為 JVM 單測）",
  sections = listOf(
    DocSection(
      title = "字號級別",
      prose = "`level` 映射到 `typography.fontSize.<level>` 令牌，預設 `base`。",
      preview = { DocTextLevelsPreview() },
      code = listOf(
        "LhText(content = \"Title text\", level = LhTextLevel.large)",
      ),
    ),
    DocSection(
      title = "字重",
      prose = "`weight` 映射到 `typography.fontWeight.<weight>` 令牌（primary 400 / bold 600）。",
      preview = { DocTextWeightPreview() },
    ),
    DocSection(
      title = "色調",
      prose = "`tone` 映射到 `color.text.<tone>` 令牌；`selected` 取 `color.selected`。",
      preview = { DocTextTonesPreview() },
    ),
    DocSection(
      title = "對齊與行高",
      prose = "`lhAlign` 在行盒寬度內對齊；行高始終取 `typography.lineHeight.primary`（字號 × 1.8）。",
      code = listOf(
        "LhText(content = \"Centered\", lhAlign = LhTextAlign.center)",
      ),
    ),
    DocSection(
      title = "Props",
      prose = "",
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`typography.fontSize.<level>`", "six size levels")),
    DocRow(cells = listOf("`typography.fontWeight.{primary,bold}`", "two weights")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "line height (font size × 1.8)")),
    DocRow(cells = listOf("`color.text.<tone>` / `color.selected`", "text tones")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-level-uses-base-tokens`", "default level consumes the base typography tokens", "`LhTextDefaultsTest.default_level_uses_base_tokens`")),
    DocRow(cells = listOf("`large-level-uses-large-token`", "large level consumes the large size token", "`LhTextDefaultsTest.large_level_uses_large_token`")),
    DocRow(cells = listOf("`bold-weight-uses-bold-token`", "bold consumes the bold weight token", "`LhTextDefaultsTest.bold_weight_uses_bold_token`")),
    DocRow(cells = listOf("`secondary-tone-uses-text-secondary`", "secondary tone takes the secondary text color token", "`LhTextDefaultsTest.secondary_tone_uses_text_secondary`")),
    DocRow(cells = listOf("`render-emits-single-text-command`", "render emits a single TEXT command", "`LhTextDefaultsTest.all_levels_map_to_their_tokens`")),
  ),
  commands = listOf(

    "./gradlew :components:text:test                             # JVM",
  ),
)
