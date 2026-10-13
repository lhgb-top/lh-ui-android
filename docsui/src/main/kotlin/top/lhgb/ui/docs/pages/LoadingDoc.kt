package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Loading 加载",
  lede = "加载指示器，环形旋转动画，时长取 `motion.duration.base`（毫秒）。",
  group = DocGroup.BASIC,
  contract = "契约源：`lh-ui-core/specs/loading.json` · 黄金用例：`cases/loading.json`（5 条，纯展示组件，翻译为 JVM 单测）",
  sections = listOf(
    DocSection(
      title = "尺寸与色调",
      prose = "三档尺寸映射到 `loading.size.*` 令牌，描边宽取 `loading.strokeWidth`，色调取语义色。",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(size = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`size`", "指示器直径", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "环色", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
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
      headers = listOf("值", "颜色令牌"),
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
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "三档直径")),
    DocRow(cells = listOf("`loading.strokeWidth`", "环描边宽度")),
    DocRow(cells = listOf("`color.text.regular`", "default 色调")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "语义色调")),
    DocRow(cells = listOf("`motion.duration.base`", "旋转一周时长（端侧为毫秒整型）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "中号取 medium 尺寸令牌", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "大号取 large 尺寸令牌", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 色调取主色令牌", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "success 色调取成功色令牌", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "环形指示器渲染（描边宽消费令牌）", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # JVM 部分（纯展示，无交互用例）",
  ),
)
