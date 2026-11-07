package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Picker 選擇器",
  lede = "單列底部滾輪選擇器，捲動停止後最靠近中央指示器的項即選取項。",
  group = DocGroup.OVERLAY,
  contract = "契約源：`lh-ui-core/specs/picker.json` · 黃金用例：`cases/picker.json`（8 條，鉗制與令牌對應翻譯為 JVM 單測，捲動與按鈕互動翻譯為 Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "用法",
      prose = "底部彈出：遮罩區在上、面板在下（頂部圓角）。工具列為「取消 / 標題 / 確認」三段。",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"選項一\", \"選項二\", \"選項三\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"請選擇\",",
        ")",
      ),
    ),
    DocSection(
      title = "選取機制",
      prose = "滾輪是一個可捲動列表，每項高 `picker.itemHeight`；中央指示器為兩條水平線，間距 = itemHeight。\n捲動停止後，按已捲動距離四捨五入得到選取索引，變化時觸發 `onValueChange`。\n`value` 越界時由 `LhPickerDefaults.clamp` 鉗制到候選範圍內。",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "是否顯示；為 false 時不渲染任何內容", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "候選項文字陣列（單列）", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "滾輪停止且選取項變化時觸發，攜帶新索引", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "點選確認按鈕時觸發，攜帶當前選取索引", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "點選取消按鈕時觸發", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "確認/取消後觸發，宿主據此置 visible 為 false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "選取項索引（0 基）；越界時鉗制", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "工具列中間的標題", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "確認按鈕文字", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "取消按鈕文字", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "滾輪內容區（覆蓋預設候選渲染，spec 的 default 插槽）", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "工具列（覆蓋預設確認/標題/取消，spec 的 toolbar 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "滾輪列表狀態（預設由 `rememberLhPickerListState(value, options)` 提供；測試時可外部注入）", "`LazyListState`", "見左")),
    DocRow(cells = listOf("`modifier`", "修飾符（作用於面板）", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "面板高度（260dp）")),
    DocRow(cells = listOf("`picker.itemHeight`", "每項高度（40dp，也是中央指示器間距）")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "工具列高度（44dp）")),
    DocRow(cells = listOf("`picker.itemFontSize`", "候選項字級")),
    DocRow(cells = listOf("`picker.borderRadius`", "面板頂部圓角")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "面板橫向內邊距（跟隨 `spacing.md`）")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "層級語義對齊（Compose `Popup` 自帶視窗級層級）")),
    DocRow(cells = listOf("`color.background.overlay`", "遮罩色")),
    DocRow(cells = listOf("`color.background.default`", "面板底色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "標題 / 候選項色")),
    DocRow(cells = listOf("`color.border.lighter`", "中央指示器線條色")),
    DocRow(cells = listOf("`color.primary.default`", "確認按鈕色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "確認攜帶當前索引 + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose`（Compose UI Test）")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "確認後觸發 onClose", "同上")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "取消觸發 onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "捲動改變選取並觸發 onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "value 為負數時鉗制到 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "value 越界時鉗制到末項", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "標題渲染文字", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "不可見時不渲染", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # JVM 部分",
    "./gradlew :components:picker:connectedAndroidTest               # 互動部分（需裝置）",
  ),
)
