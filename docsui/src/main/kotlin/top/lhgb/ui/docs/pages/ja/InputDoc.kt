package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Input 入力フィールド",
  lede = "コントロールされる単一行入力フィールドです。サイズ、無効化、読み取り専用、クリア、パスワードのマスク、最大長をサポートします。",
  group = DocGroup.FORM,
  contract = "契約元: `lh-ui-core/specs/input.json` · ゴールデンケース: `cases/input.json`（8 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "基本的な使い方",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"内容を入力してください\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "サイズとフォーカス",
      prose = "3 段階のサイズが `input.height` / `input.paddingHorizontal` / `input.fontSize` トークンに対応します。フォーカス時に枠線は `color.focus` を取り、`input.focusRingWidth` まで太くなります。",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "コントロール値（必須）", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "値変更コールバック（必須）", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "プレースホルダーのテキスト", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "入力タイプ（平文 / パスワード）", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "サイズ", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "無効かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "読み取り専用かどうか（編集不可だが無効状態のスタイルにはならない）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "クリアボタンを表示するかどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "最大長（超過分は切り詰め）", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "フォーカス / フォーカス外れのコールバック", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "先頭 / 末尾のスロット", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("値", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small`（32dp）", "`input.paddingHorizontal.small`（12dp）", "`input.fontSize.small`（13sp）")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium`（40dp）", "`input.paddingHorizontal.medium`（16dp）", "`input.fontSize.medium`（14sp）")),
        DocRow(cells = listOf("`Large`", "`input.height.large`（48dp）", "`input.paddingHorizontal.large`（20dp）", "`input.fontSize.large`（16sp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "3 段階の高さ")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "3 段階の横向きパディング")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "3 段階のフォントサイズ")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "角丸 / 枠線幅 / フォーカス枠線幅")),
    DocRow(cells = listOf("`input.clearIconSize`", "クリアアイコンのサイズ")),
    DocRow(cells = listOf("`color.focus`", "フォーカス枠線色")),
    DocRow(cells = listOf("`color.text.placeholder`", "プレースホルダーのテキスト色")),
    DocRow(cells = listOf("`color.border.base`", "デフォルトの枠線色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "無効状態の三色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "入力が onValueChange を発火", "`LhInputTest.input_emits_onValueChange`（Compose UI Test）")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "クリアボタンが空の値を発火", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "無効状態では入力できない", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "読み取り専用では入力できない", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "プレースホルダー色がプレースホルダートークンを取る", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "フォーカスがフォーカストークンを消費（フォーカス外れは中間色の枠線を取る）", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "small が small サイズトークンを消費", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "超過した入力は切り詰められる", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # JVM 部分",
    "./gradlew :components:input:connectedAndroidTest              # インタラクション部分（デバイスが必要）",
  ),
)
