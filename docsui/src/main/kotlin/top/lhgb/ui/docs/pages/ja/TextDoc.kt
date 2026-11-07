package top.lhgb.ui.docs.pages.ja

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
  title = "Text テキスト",
  lede = "テキスト表示プリミティブ：グローバル組版トークンを消費し、サイズレベル・字重・色調・整列をサポート。",
  group = DocGroup.BASIC,
  contract = "契約元：`lh-ui-core/specs/text.json` · 黄金ケース：`cases/text.json`（5 件、JVM 単体テストに翻訳済み）",
  sections = listOf(
    DocSection(
      title = "サイズレベル",
      prose = "`level` は `typography.fontSize.<level>` トークンにマッピング（デフォルト `base`）。",
      preview = { DocTextLevelsPreview() },
      code = listOf(
        "LhText(content = \"Title text\", level = LhTextLevel.large)",
      ),
    ),
    DocSection(
      title = "字重",
      prose = "`weight` は `typography.fontWeight.<weight>` トークンにマッピング（primary 400 / bold 600）。",
      preview = { DocTextWeightPreview() },
    ),
    DocSection(
      title = "色調",
      prose = "`tone` は `color.text.<tone>` トークンにマッピング。`selected` は `color.selected` を使用。",
      preview = { DocTextTonesPreview() },
    ),
    DocSection(
      title = "整列と行高",
      prose = "`lhAlign` は行ボックス内で整列。行高は常に `typography.lineHeight.primary`（サイズ × 1.8）。",
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
