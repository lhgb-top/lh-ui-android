package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Loading 載入",
  lede = "載入指示器，環形旋轉動畫，時長取 `motion.duration.base`（毫秒）。",
  group = DocGroup.BASIC,
  contract = "契約源：`lh-ui-core/specs/loading.json` · 黃金用例：`cases/loading.json`（5 條，純展示元件，翻譯為 JVM 單測）",
  sections = listOf(
    DocSection(
      title = "尺寸與色調",
      prose = "三檔尺寸對應到 `loading.size.*` 令牌，描邊寬取 `loading.strokeWidth`，色調取語義色。",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "指示器直徑", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "環色", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("值", "尺寸令牌"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`loading.size.large`（24dp）")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("值", "顏色令牌"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.regular`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "三檔直徑")),
    DocRow(cells = listOf("`loading.strokeWidth`", "環描邊寬度")),
    DocRow(cells = listOf("`color.text.regular`", "default 色調")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "語義色調")),
    DocRow(cells = listOf("`motion.duration.base`", "旋轉一圈時長（端側為毫秒整數）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "中號取 medium 尺寸令牌", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "大號取 large 尺寸令牌", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 色調取主色令牌", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "success 色調取成功色令牌", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "環形指示器渲染（描邊寬消費令牌）", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # JVM 部分（純展示，無互動用例）",
  ),
)
