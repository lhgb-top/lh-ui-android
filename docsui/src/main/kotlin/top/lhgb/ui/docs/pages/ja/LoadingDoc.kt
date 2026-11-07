package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Loading ローディング",
  lede = "読み込みインジケーターです。リング状の回転アニメーションで、周期は `motion.duration.base`（ミリ秒）を取ります。",
  group = DocGroup.BASIC,
  contract = "契約元: `lh-ui-core/specs/loading.json` · ゴールデンケース: `cases/loading.json`（5 件、表示専用コンポーネント、JVM 単体テストに翻訳済み）",
  sections = listOf(
    DocSection(
      title = "サイズと色調",
      prose = "3 段階のサイズが `loading.size.*` トークンに対応し、枠線幅は `loading.strokeWidth`、色調はセマンティック色を取ります。",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "インジケーターの直径", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "リングの色", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("値", "サイズトークン"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`loading.size.large`（24dp）")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("値", "カラートークン"),
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
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "3 段階の直径")),
    DocRow(cells = listOf("`loading.strokeWidth`", "リングの枠線幅")),
    DocRow(cells = listOf("`color.text.regular`", "default 色調")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "セマンティック色調")),
    DocRow(cells = listOf("`motion.duration.base`", "1 回転の周期（エンド側ではミリ秒の整数値）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "medium が medium サイズトークンを取る", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "large が large サイズトークンを取る", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 色調が主色トークンを取る", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "success 色調が成功色トークンを取る", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "リングインジケーターがレンダリングされる（枠線幅がトークンを消費）", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # JVM 部分（表示専用、インタラクションケースなし）",
  ),
)
