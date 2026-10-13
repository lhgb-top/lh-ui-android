package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Dialog 对话框",
  lede = "模态浮层，用于重要确认或需要用户聚焦的内容。窗口级层级由 Compose `Dialog` 托管。",
  group = DocGroup.OVERLAY,
  contract = "契约源：`lh-ui-core/specs/dialog.json` · 黄金用例：`cases/dialog.json`（8 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "类型",
      prose = "`type` 决定确认按钮的颜色（1:1 映射到 `LhButtonType`），其余布局不变。",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* 确认逻辑 */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"删除确认\",",
        "  content = \"删除后不可恢复，确定删除吗？\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "语义类型与遮罩",
      prose = "`type` 走 `danger` 时确认按钮取危险色；`closable` 控制点遮罩 / 按返回键是否触发 `onClose`。\n确认 / 取消按钮点击后先触发业务回调，再触发 `onClose`，宿主据此把 `visible` 置为 false。",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "是否显示；为 false 时不渲染任何内容", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "点击确认按钮时触发", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "点击取消按钮时触发", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "确认/取消后或（closable 时）点遮罩触发", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "标题", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "正文内容", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "语义类型，决定确认按钮的类型（颜色）", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "确认按钮文字；为空时不显示确认按钮", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "取消按钮文字；为空时不显示取消按钮", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "点遮罩 / 按返回键是否触发 onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "标题内容（覆盖 title，spec 的 title 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "正文内容（覆盖 content，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "底部按钮区（覆盖默认按钮，spec 的 footer 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符（作用于内容卡）", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("值", "确认按钮类型"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "同名 `LhButtonType`，取对应语义色")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "内容卡宽度（320dp）")),
    DocRow(cells = listOf("`dialog.borderRadius`", "卡片圆角（跟随 `radius.base`）")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "卡片内边距（跟随 `spacing.lg`）")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "标题 / 正文字号")),
    DocRow(cells = listOf("`dialog.gap`", "标题、正文、按钮区之间的间距")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "层级语义对齐（Compose `Dialog` 托管窗口级层级）")),
    DocRow(cells = listOf("`color.background.overlay`", "遮罩色")),
    DocRow(cells = listOf("`color.background.default`", "卡片底色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "标题 / 正文色")),
    DocRow(cells = listOf("`shadow.base`", "卡片投影（Compose 不支持 spread，取模糊半径做 elevation）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "确认触发 onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose`（Compose UI Test）")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "确认后触发 onClose", "同上")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "取消触发 onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "closable 时点遮罩触发 onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "非 closable 时吞掉遮罩点击", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "不可见时不渲染", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "标题渲染文字", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "danger 类型确认按钮取危险色", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # JVM 部分",
    "./gradlew :components:dialog:connectedAndroidTest             # 交互部分（需设备）",
  ),
)
