package top.lhgb.ui.docs.pages.ja

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
  title = "Tag タグ",
  lede = "セマンティック色のタグです。アウトライン、完全な角丸マーク、閉じるボタンをサポートします。",
  group = DocGroup.BASIC,
  contract = "契約元: `lh-ui-core/specs/tag.json` · ゴールデンケース: `cases/tag.json`（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "タイプ",
      prose = "6 種類のセマンティックタイプが背景色とテキスト色を決定します。",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"成功\") }",
      ),
    ),
    DocSection(
      title = "アウトライン（plain）",
      prose = "`plain` は透明な背景 + セマンティック色の枠線とテキストです。",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "サイズとマーク",
      prose = "3 段階のサイズが `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` トークンに対応します。`mark` は高さの半分の完全な角丸を取ります。",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "タグのテキスト（簡易書き方、`content` と二択）", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "セマンティックタイプ", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "サイズ", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "アウトラインにするかどうか（透明な背景 + セマンティック色の枠線）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "完全な角丸にするかどうか（高さの半分）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "閉じるボタンを表示するかどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "閉じるコールバック。`closable` が false の場合は発火しない", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "タグ内容（text を上書き、spec の default スロット）", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("値", "背景色（plain 以外）", "枠線 / テキスト（plain）"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + `color.border.base` の枠線", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("値", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small`（20dp）", "`tag.paddingHorizontal.small`（8dp）", "`tag.fontSize.small`（12sp）")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium`（24dp）", "`tag.paddingHorizontal.medium`（10dp）", "`tag.fontSize.medium`（14sp）")),
        DocRow(cells = listOf("`Large`", "`tag.height.large`（28dp）", "`tag.paddingHorizontal.large`（12dp）", "`tag.fontSize.large`（16sp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "3 段階の高さ")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "3 段階の横向きパディング")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "3 段階のフォントサイズ")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "角丸と枠線の幅")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "セマンティックな背景色 / 枠線 / テキスト")),
    DocRow(cells = listOf("`color.onPrimary`", "ソリッドな背景上のテキスト色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "無効状態の三色")),
    DocRow(cells = listOf("`spacing.xs`", "閉じるボタンとテキストの間隔")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "デフォルトタイプが中間色の背景 + 主テキスト色 + ベーシックな枠線を取る", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "主色タイプが主色トークンを消費", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain がセマンティックな薄い背景トークンを消費", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "small が small トークンを消費（large は適用されないという逆向きのアサーション）", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round の角丸が高さの半分を取る", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "閉じるボタンのタップが onClose を発火", "`LhTagTest.closable_tap_emits_onClose`（Compose UI Test）")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # JVM 部分",
    "./gradlew :components:tag:connectedAndroidTest             # インタラクション部分（デバイスが必要）",
  ),
)
