package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Cell セル",
  lede = "リストのセルです。アイコン / タイトル + 説明 / 右側の値 + 矢印からなる三段レイアウトで、リストと組み合わせてよく使います。",
  group = DocGroup.BASIC,
  contract = "契約元: `lh-ui-core/specs/cell.json` · ゴールデンケース: `cases/cell.json`（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "基本的な使い方",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"説明付きタイトル\",",
        "  label = \"説明テキスト\",",
        "  value = \"値\",",
        "  isLink = true,",
        "  lhOnClick = { /* 遷移 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "必須とリンク",
      prose = "`required` はタイトルの前に赤いアスタリスクを描画します（`color.danger.default`）。`isLink` は末尾に右矢印アイコンを描画します。",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "タイトル（メインテキスト）", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "右側の値（サブテキスト、右寄せ）", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "タイトルの下の説明テキスト", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "底部の区切り線を描画するかどうか（`color.border.lighter`）", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "クリック可能かどうか。true の場合は右側に矢印インジケーターを表示し、クリックに応答する", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "タイトルの前に必須の赤いアスタリスクを表示するかどうか（`color.danger.default`）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "左側のアイコン名（ローカルのアイコン体系）。空の場合は描画しない", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "クリックコールバック。`clickable` が false の場合は発火しない", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "タイトル内容（title を上書き、spec の default スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "右側の値の内容（value を上書き、spec の value スロット）", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "縦向きパディング（`spacing.md` の参照）")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "横向きパディング（`spacing.md`）")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "タイトル / 説明 / 値のフォントサイズ")),
    DocRow(cells = listOf("`cell.gap`", "アイコンとタイトルの間隔（`spacing.xs`）")),
    DocRow(cells = listOf("`icon.size.medium`", "左側のアイコンと矢印インジケーターのサイズ")),
    DocRow(cells = listOf("`cell.borderWidth`", "底部区切り線の幅")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "テキストの行の高さ")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "タイトル / 説明 / 値の色")),
    DocRow(cells = listOf("`color.danger.default`", "必須の赤いアスタリスク")),
    DocRow(cells = listOf("`color.border.lighter`", "底部区切り線")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "タイトルと右側の値がテキストを描画", "`LhCellTest.title_and_value_render`（Compose UI Test）")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label はセカンダリテキスト色のトークンを使用", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "必須マークは危険色のトークンを使用", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "区切り線はボーダートークンを消費", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "クリックが onClick を発火", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "無効状態は発火しない", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # JVM 部分",
    "./gradlew :components:cell:connectedAndroidTest              # インタラクション部分（デバイスが必要）",
  ),
)
