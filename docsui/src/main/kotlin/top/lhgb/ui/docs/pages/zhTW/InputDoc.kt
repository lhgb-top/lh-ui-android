package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Input 輸入框",
  lede = "受控單行輸入框，支援尺寸、停用、唯讀、清除、密碼遮罩與最大長度。",
  group = DocGroup.FORM,
  contract = "契約源：`lh-ui-core/specs/input.json` · 黃金用例：`cases/input.json`（8 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "基本用法",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"請輸入內容\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "尺寸與焦點",
      prose = "三檔尺寸對應到 `input.height` / `input.paddingHorizontal` / `input.fontSize` 令牌；聚焦時描邊取 `color.focus` 並加寬到 `input.focusRingWidth`。",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "受控值（必填）", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "值變更回呼（必填）", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "佔位文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "輸入類型（明文 / 密碼）", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "尺寸", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "是否停用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "是否唯讀（不可編輯但不停用樣式）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "是否顯示清除按鈕", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "最大長度（超長截斷）", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "聚焦 / 失焦回呼", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "前綴 / 後綴插槽", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
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
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "三檔高度")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "三檔橫向內邊距")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "三檔字級")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "圓角 / 描邊寬 / 焦點描邊寬")),
    DocRow(cells = listOf("`input.clearIconSize`", "清除圖示尺寸")),
    DocRow(cells = listOf("`color.focus`", "焦點描邊色")),
    DocRow(cells = listOf("`color.text.placeholder`", "佔位文字色")),
    DocRow(cells = listOf("`color.border.base`", "預設描邊色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "停用態三色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "輸入觸發 onValueChange", "`LhInputTest.input_emits_onValueChange`（Compose UI Test）")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "清除按鈕發射空值", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "停用態不可輸入", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "唯讀態不可輸入", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "佔位色取佔位令牌", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "聚焦消費焦點令牌（失焦取中性描邊）", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小號消費 small 尺寸令牌", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "超長輸入截斷", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # JVM 部分",
    "./gradlew :components:input:connectedAndroidTest              # 互動部分（需裝置）",
  ),
)
