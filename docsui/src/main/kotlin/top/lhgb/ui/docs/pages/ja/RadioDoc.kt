package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Radio ラジオボタン",
  lede = "コントロールされるラジオボタンです。選択された後に再度タップしても選択は解除されません（ラジオのセマンティクス）。",
  group = DocGroup.FORM,
  contract = "契約元: `lh-ui-core/specs/radio.json` · ゴールデンケース: `cases/radio.json`（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "状態とラジオグループ",
      prose = "コンポーネント自体はグループのロジックを持たず、ラジオの排他制御はホストが維持します（`value` でグループ内の項目を識別します）。",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"選択肢 1\" to \"a\", \"選択肢 2\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "サイズ",
      prose = "3 段階のリングサイズが `radio.size.*` トークンに対応します。内側の円点の直径 = サイズ × `radio.dotScale` です。",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "選択されているかどうか（必須）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "未選択から選択に変化したときに発火。`checked = true` を伴う", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "無効かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "ラベルテキスト", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "ラジオ値（グループ内の識別子、コンポーネント自体はグループのロジックを持たない）", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "リングサイズ", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "ラベル内容（label を上書き、spec の default スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("値", "サイズトークン", "内側の円点の直径"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small`（16dp）", "16dp × `radio.dotScale`（0.5）")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium`（20dp）", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large`（24dp）", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "3 段階のリングサイズ")),
    DocRow(cells = listOf("`radio.ringWidth`", "外側のリングの枠線幅")),
    DocRow(cells = listOf("`radio.dotScale`", "内側の円点の、外側のリング直径に対する割合（小数）")),
    DocRow(cells = listOf("`radio.gap`", "リングとラベルの間隔")),
    DocRow(cells = listOf("`color.primary.default`", "選択時の枠線と円点の色")),
    DocRow(cells = listOf("`color.border.base`", "未選択の枠線色")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "無効状態の枠線 / 円点 / テキスト色")),
    DocRow(cells = listOf("`color.text.primary`", "ラベル色")),
    DocRow(cells = listOf("`typography.fontSize.base`", "ラベルのフォントサイズ")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "未選択のタップが onChange(true) を発火", "`LhRadioTest.tap_unchecked_emits_onchange_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "選択済みのタップは発火しない", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "無効状態は発火しない", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "選択が主色トークンを消費", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "未選択がボーダートークンを消費", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "small が small サイズトークンを消費", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # JVM 部分",
    "./gradlew :components:radio:connectedAndroidTest             # インタラクション部分（デバイスが必要）",
  ),
)
