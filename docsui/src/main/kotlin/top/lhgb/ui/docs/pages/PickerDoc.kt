package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Picker 底部滚轮选择器",
  lede = "单列底部滚轮选择器，滚动停止后最靠近中央指示器的项即选中项。",
  group = DocGroup.OVERLAY,
  contract = "契约源：`lh-ui-core/specs/picker.json` · 黄金用例：`cases/picker.json`（8 条，钳制与令牌映射翻译为 JVM 单测，滚动与按钮交互翻译为 Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "用法",
      prose = "底部弹出：遮罩区在上、面板在下（顶部圆角）。工具栏为「取消 / 标题 / 确认」三段。",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"选项一\", \"选项二\", \"选项三\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"请选择\",",
        ")",
      ),
    ),
    DocSection(
      title = "选中机制",
      prose = "滚轮是一个可滚动列表，每项高 `picker.itemHeight`；中央指示器为两条水平线，间距 = itemHeight。\n滚动停止后，按已滚动距离四舍五入得到选中索引，变化时触发 `onValueChange`。\n`value` 越界时由 `LhPickerDefaults.clamp` 钳制到候选范围内。",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "是否显示；为 false 时不渲染任何内容", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "候选项文本数组（单列）", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "滚轮停止且选中项变化时触发，携带新索引", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "点击确认按钮时触发，携带当前选中索引", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "点击取消按钮时触发", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "确认/取消后触发，宿主据此置 visible 为 false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "选中项索引（0 基）；越界时钳制", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "工具栏中间的标题", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "确认按钮文字", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "取消按钮文字", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "滚轮内容区（覆盖默认候选渲染，spec 的 default 插槽）", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "工具栏（覆盖默认确认/标题/取消，spec 的 toolbar 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "滚轮列表状态（默认由 `rememberLhPickerListState(value, options)` 提供；测试时可外部注入）", "`LazyListState`", "见左")),
    DocRow(cells = listOf("`modifier`", "修饰符（作用于面板）", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "面板高度（260dp）")),
    DocRow(cells = listOf("`picker.itemHeight`", "每项高度（40dp，也是中央指示器间距）")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "工具栏高度（44dp）")),
    DocRow(cells = listOf("`picker.itemFontSize`", "候选项字号")),
    DocRow(cells = listOf("`picker.borderRadius`", "面板顶部圆角")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "面板横向内边距（跟随 `spacing.md`）")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "层级语义对齐（Compose `Popup` 自带窗口级层级）")),
    DocRow(cells = listOf("`color.background.overlay`", "遮罩色")),
    DocRow(cells = listOf("`color.background.default`", "面板底色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "标题 / 候选项色")),
    DocRow(cells = listOf("`color.border.lighter`", "中央指示器线条色")),
    DocRow(cells = listOf("`color.primary.default`", "确认按钮色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "确认携带当前索引 + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose`（Compose UI Test）")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "确认后触发 onClose", "同上")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "取消触发 onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "滚动改变选中并触发 onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "value 为负数时钳制到 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "value 越界时钳制到末项", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "标题渲染文字", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "不可见时不渲染", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # JVM 部分",
    "./gradlew :components:picker:connectedAndroidTest               # 交互部分（需设备）",
  ),
)
