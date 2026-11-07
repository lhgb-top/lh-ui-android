package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Cell 儲存格",
  lede = "列表儲存格，圖示 / 標題 + 描述 / 右值 + 箭頭的三段式版面配置，常與列表組合使用。",
  group = DocGroup.BASIC,
  contract = "契約源：`lh-ui-core/specs/cell.json` · 黃金用例：`cases/cell.json`（6 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "基本用法",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"帶描述的標題\",",
        "  label = \"描述文字\",",
        "  value = \"值\",",
        "  isLink = true,",
        "  lhOnClick = { /* 跳轉 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "必填與連結",
      prose = "`required` 在標題前繪製紅色星號（`color.danger.default`）；`isLink` 在末尾繪製右箭頭圖示。",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "標題（主文字）", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "右側值（次文字，右對齊）", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "標題下方的說明文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "是否繪製底部分隔線（`color.border.lighter`）", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "是否可點選；為 true 時顯示右側指示箭頭並回應點選", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "是否在標題前顯示必填紅星（`color.danger.default`）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "左側圖示名（本機圖示體系）；空時不繪製", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "點選回呼；`clickable` 為 false 時不觸發", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "標題內容（覆蓋 title，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "右側值內容（覆蓋 value，spec 的 value 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "縱向內邊距（`spacing.md` 參照）")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "橫向內邊距（`spacing.md`）")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "標題 / 描述 / 值字級")),
    DocRow(cells = listOf("`cell.gap`", "圖示與標題間距（`spacing.xs`）")),
    DocRow(cells = listOf("`icon.size.medium`", "左側圖示與指示箭頭尺寸")),
    DocRow(cells = listOf("`cell.borderWidth`", "底分隔線寬度")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "文字行高")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "標題 / 描述 / 值顏色")),
    DocRow(cells = listOf("`color.danger.default`", "必填紅星")),
    DocRow(cells = listOf("`color.border.lighter`", "底分隔線")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "標題與右值渲染文字", "`LhCellTest.title_and_value_render`（Compose UI Test）")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label 取次要文字色令牌", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "必填標記取危險色令牌", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "分隔線消費描邊令牌", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "點選觸發 onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "停用態不觸發", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # JVM 部分",
    "./gradlew :components:cell:connectedAndroidTest              # 互動部分（需裝置）",
  ),
)
