package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Textarea テキストエリア",
  lede = "コントロールされる複数行入力フィールドです。行数、文字数カウント、最大長をサポートします。",
  group = DocGroup.FORM,
  contract = "契約元: `lh-ui-core/specs/textarea.json` · ゴールデンケース: `cases/textarea.json`（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "基本的な使い方",
      prose = "最小の高さ = 行数 × 行の高さ × フォントサイズで、超過すると縦方向にスクロールします。",
      preview = { DocTextareaBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhTextarea(",
        "  value = value,",
        "  rows = 3,",
        "  showCount = true,",
        "  maxlength = 200,",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "文字数カウント",
      prose = "`showCount` が true のとき、右下にカウントが描画されます（`textarea.countFontSize`、色は `text.secondary`）。`maxlength` が入力を切り詰めます。",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "コントロール値（必須）", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "値変更コールバック（必須）", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "プレースホルダーのテキスト", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "行数（最小の高さを決定）", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "無効かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "読み取り専用かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "文字数カウントを表示するかどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "最大長（超過分は切り詰め）", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "フォーカス / フォーカス外れのコールバック", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "パディング（`spacing.md`）")),
    DocRow(cells = listOf("`textarea.fontSize`", "フォントサイズ（`typography.fontSize.base`）")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "角丸 / 枠線幅 / フォーカス枠線幅")),
    DocRow(cells = listOf("`textarea.countFontSize`", "カウントのフォントサイズ（`typography.fontSize.extraSmall`")),
    DocRow(cells = listOf("`textarea.minHeight`", "最小の高さの基準値")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "行の高さ（行数からの換算）")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "テキスト / カウント / プレースホルダーの色")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "フォーカス / デフォルトの枠線色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "無効状態の三色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "入力が onValueChange を発火", "`LhTextareaTest.input_emits_onValueChange`（Compose UI Test）")),
    DocRow(cells = listOf("`disabled-blocks-input`", "無効状態では入力できない", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "カウントが「n / max」を描画", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "maxlength がない場合は長さのみ描画", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "フォーカスがフォーカストークンを消費", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "超過した入力は切り詰められる", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # JVM 部分",
    "./gradlew :components:textarea:connectedAndroidTest           # インタラクション部分（デバイスが必要）",
  ),
)
