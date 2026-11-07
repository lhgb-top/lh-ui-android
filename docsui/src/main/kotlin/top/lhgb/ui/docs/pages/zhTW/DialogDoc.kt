package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Dialog 對話框",
  lede = "模態浮層，用於重要確認或需要使用者聚焦的內容。視窗級層級由 Compose `Dialog` 託管。",
  group = DocGroup.OVERLAY,
  contract = "契約源：`lh-ui-core/specs/dialog.json` · 黃金用例：`cases/dialog.json`（8 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "類型",
      prose = "`type` 決定確認按鈕的顏色（1:1 對應到 `LhButtonType`），其餘版面配置不變。",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* 確認邏輯 */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"刪除確認\",",
        "  content = \"刪除後不可復原，確定刪除嗎？\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "語義類型與遮罩",
      prose = "`type` 走 `danger` 時確認按鈕取危險色；`closable` 控制點遮罩 / 按返回鍵是否觸發 `onClose`。\n確認 / 取消按鈕點選後先觸發業務回呼，再觸發 `onClose`，宿主據此把 `visible` 置為 false。",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "是否顯示；為 false 時不渲染任何內容", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "點選確認按鈕時觸發", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "點選取消按鈕時觸發", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "確認/取消後或（closable 時）點遮罩觸發", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "標題", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "正文內容", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "語義類型，決定確認按鈕的類型（顏色）", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "確認按鈕文字；為空時不顯示確認按鈕", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "取消按鈕文字；為空時不顯示取消按鈕", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "點遮罩 / 按返回鍵是否觸發 onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "標題內容（覆蓋 title，spec 的 title 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "正文內容（覆蓋 content，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "底部按鈕區（覆蓋預設按鈕，spec 的 footer 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符（作用於內容卡）", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("值", "確認按鈕類型"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "同名 `LhButtonType`，取對應語義色")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "內容卡寬度（320dp）")),
    DocRow(cells = listOf("`dialog.borderRadius`", "卡片圓角（跟隨 `radius.base`）")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "卡片內邊距（跟隨 `spacing.lg`）")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "標題 / 正文字級")),
    DocRow(cells = listOf("`dialog.gap`", "標題、正文、按鈕區之間的間距")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "層級語義對齊（Compose `Dialog` 託管視窗級層級）")),
    DocRow(cells = listOf("`color.background.overlay`", "遮罩色")),
    DocRow(cells = listOf("`color.background.default`", "卡片底色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "標題 / 正文色")),
    DocRow(cells = listOf("`shadow.base`", "卡片投影（Compose 不支援 spread，取模糊半徑做 elevation）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "確認觸發 onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose`（Compose UI Test）")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "確認後觸發 onClose", "同上")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "取消觸發 onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "closable 時點遮罩觸發 onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "非 closable 時吞掉遮罩點選", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "不可見時不渲染", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "標題渲染文字", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "danger 類型確認按鈕取危險色", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # JVM 部分",
    "./gradlew :components:dialog:connectedAndroidTest             # 互動部分（需裝置）",
  ),
)
