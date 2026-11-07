package top.lhgb.ui.docs.pages

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
  title = "Text 文本",
  lede = "文本展示原语：消费全局排版令牌，支持字号级别、字重、色调与对齐。",
  group = DocGroup.BASIC,
  contract = "契约源：`lh-ui-core/specs/text.json` · 黄金用例：`cases/text.json`（5 条，已翻译为 JVM 单测）",
  sections = listOf(
    DocSection(
      title = "字号级别",
      prose = "`level` 映射到 `typography.fontSize.<level>` 令牌，默认 `base`。",
      preview = { DocTextLevelsPreview() },
      code = listOf(
        "LhText(content = \"标题文字\", level = LhTextLevel.large)",
      ),
    ),
    DocSection(
      title = "字重",
      prose = "`weight` 映射到 `typography.fontWeight.<weight>` 令牌（primary 400 / bold 600）。",
      preview = { DocTextWeightPreview() },
    ),
    DocSection(
      title = "色调",
      prose = "`tone` 映射到 `color.text.<tone>` 令牌；`selected` 取 `color.selected`。",
      preview = { DocTextTonesPreview() },
    ),
    DocSection(
      title = "对齐与行高",
      prose = "`lhAlign` 在行盒宽度内对齐；行高始终取 `typography.lineHeight.primary`（字号 × 1.8）。",
      code = listOf(
        "LhText(content = \"居中\", lhAlign = LhTextAlign.center)",
      ),
    ),
    DocSection(
      title = "Props",
      prose = "",
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`typography.fontSize.<level>`", "六档字号")),
    DocRow(cells = listOf("`typography.fontWeight.{primary,bold}`", "两档字重")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "行高（字号 × 1.8）")),
    DocRow(cells = listOf("`color.text.<tone>` / `color.selected`", "文字色调")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-level-uses-base-tokens`", "默认级别消费 base 排版令牌", "`LhTextDefaultsTest.default_level_uses_base_tokens`")),
    DocRow(cells = listOf("`large-level-uses-large-token`", "大级别消费 large 字号令牌", "`LhTextDefaultsTest.large_level_uses_large_token`")),
    DocRow(cells = listOf("`bold-weight-uses-bold-token`", "粗体消费 bold 字重令牌", "`LhTextDefaultsTest.bold_weight_uses_bold_token`")),
    DocRow(cells = listOf("`secondary-tone-uses-text-secondary`", "次要色调取次要文字色令牌", "`LhTextDefaultsTest.secondary_tone_uses_text_secondary`")),
    DocRow(cells = listOf("`render-emits-single-text-command`", "渲染发出一条 TEXT 指令", "`LhTextDefaultsTest.all_levels_map_to_their_tokens`（令牌映射覆盖率）")),
  ),
  commands = listOf(

    "./gradlew :components:text:test                             # JVM 部分",
  ),
)
