package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Textarea 文本域",
  lede = "受控多行输入框，支持行数、字数统计与最大长度。",
  group = DocGroup.FORM,
  contract = "契约源：`lh-ui-core/specs/textarea.json` · 黄金用例：`cases/textarea.json`（6 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "基本用法",
      prose = "最小高度 = 行数 × 行高 × 字号，超出后纵向滚动。",
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
      title = "字数统计",
      prose = "`showCount` 为 true 时右下角渲染计数（`textarea.countFontSize`，色 `text.secondary`）；`maxlength` 截断输入。",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "受控值（必填）", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "值变更回调（必填）", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "占位文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "行数（决定最小高度）", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "是否禁用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "是否只读", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "是否显示字数统计", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "最大长度（超长截断）", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`onFocus` / `onBlur`", "聚焦 / 失焦回调", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "内边距（`spacing.md`）")),
    DocRow(cells = listOf("`textarea.fontSize`", "字号（`typography.fontSize.base`）")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "圆角 / 描边宽 / 焦点描边宽")),
    DocRow(cells = listOf("`textarea.countFontSize`", "计数字号（`typography.fontSize.extraSmall`")),
    DocRow(cells = listOf("`textarea.minHeight`", "最小高度基准")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "行高（行数换算）")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "文字 / 计数 / 占位色")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "焦点 / 默认描边色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "禁用态三色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "输入触发 onValueChange", "`LhTextareaTest.input_emits_onValueChange`（Compose UI Test）")),
    DocRow(cells = listOf("`disabled-blocks-input`", "禁用态不可输入", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "计数渲染「n / max」", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "无 maxlength 时只渲染长度", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "聚焦消费焦点令牌", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "超长输入截断", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # JVM 部分",
    "./gradlew :components:textarea:connectedAndroidTest           # 交互部分（需设备）",
  ),
)
