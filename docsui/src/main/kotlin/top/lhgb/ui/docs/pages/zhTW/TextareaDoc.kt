package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Textarea 文字區",
  lede = "受控多行輸入框，支援行數、字數統計與最大長度。",
  group = DocGroup.FORM,
  contract = "契約源：`lh-ui-core/specs/textarea.json` · 黃金用例：`cases/textarea.json`（6 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "基本用法",
      prose = "最小高度 = 行數 × 行高 × 字級，超出後縱向捲動。",
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
      title = "字數統計",
      prose = "`showCount` 為 true 時右下角渲染計數（`textarea.countFontSize`，色 `text.secondary`）；`maxlength` 截斷輸入。",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "受控值（必填）", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "值變更回呼（必填）", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "佔位文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "行數（決定最小高度）", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "是否停用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "是否唯讀", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "是否顯示字數統計", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "最大長度（超長截斷）", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "聚焦 / 失焦回呼", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "內邊距（`spacing.md`）")),
    DocRow(cells = listOf("`textarea.fontSize`", "字級（`typography.fontSize.base`）")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "圓角 / 描邊寬 / 焦點描邊寬")),
    DocRow(cells = listOf("`textarea.countFontSize`", "計數字級（`typography.fontSize.extraSmall`")),
    DocRow(cells = listOf("`textarea.minHeight`", "最小高度基準")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "行高（行數換算）")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "文字 / 計數 / 佔位色")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "焦點 / 預設描邊色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "停用態三色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "輸入觸發 onValueChange", "`LhTextareaTest.input_emits_onValueChange`（Compose UI Test）")),
    DocRow(cells = listOf("`disabled-blocks-input`", "停用態不可輸入", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "計數渲染「n / max」", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "無 maxlength 時只渲染長度", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "聚焦消費焦點令牌", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "超長輸入截斷", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # JVM 部分",
    "./gradlew :components:textarea:connectedAndroidTest           # 互動部分（需裝置）",
  ),
)
