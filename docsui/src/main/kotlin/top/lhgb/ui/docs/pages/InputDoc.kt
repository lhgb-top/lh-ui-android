package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Input 输入框",
  lede = "受控单行输入框，支持尺寸、禁用、只读、清除、密码遮罩与最大长度。",
  group = DocGroup.FORM,
  contract = "契约源：`lh-ui-core/specs/input.json` · 黄金用例：`cases/input.json`（8 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "基本用法",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"请输入内容\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "尺寸与焦点",
      prose = "三档尺寸映射到 `input.height` / `input.paddingHorizontal` / `input.fontSize` 令牌；聚焦时描边取 `color.focus` 并加宽到 `input.focusRingWidth`。",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "受控值（必填）", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "值变更回调（必填）", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "占位文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "输入类型（明文 / 密码）", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`size`", "尺寸", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "是否禁用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "是否只读（不可编辑但不禁用样式）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "是否显示清除按钮", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "最大长度（超长截断）", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`onFocus` / `onBlur`", "聚焦 / 失焦回调", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "前缀 / 后缀插槽", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("值", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small`（32dp）", "`input.paddingHorizontal.small`（12dp）", "`input.fontSize.small`（13sp）")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium`（40dp）", "`input.paddingHorizontal.medium`（16dp）", "`input.fontSize.medium`（14sp）")),
        DocRow(cells = listOf("`Large`", "`input.height.large`（48dp）", "`input.paddingHorizontal.large`（20dp）", "`input.fontSize.large`（16sp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "三档高度")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "三档横向内边距")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "三档字号")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "圆角 / 描边宽 / 焦点描边宽")),
    DocRow(cells = listOf("`input.clearIconSize`", "清除图标尺寸")),
    DocRow(cells = listOf("`color.focus`", "焦点描边色")),
    DocRow(cells = listOf("`color.text.placeholder`", "占位文字色")),
    DocRow(cells = listOf("`color.border.base`", "默认描边色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "禁用态三色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "输入触发 onValueChange", "`LhInputTest.input_emits_onValueChange`（Compose UI Test）")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "清除按钮发射空值", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "禁用态不可输入", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "只读态不可输入", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "占位色取占位令牌", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "聚焦消费焦点令牌（失焦取中性描边）", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小号消费 small 尺寸令牌", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "超长输入截断", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # JVM 部分",
    "./gradlew :components:input:connectedAndroidTest              # 交互部分（需设备）",
  ),
)
