package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Cell 单元格",
  lede = "列表单元格，图标 / 标题 + 描述 / 右值 + 箭头的三段式布局，常与列表组合使用。",
  group = DocGroup.BASIC,
  contract = "契约源：`lh-ui-core/specs/cell.json` · 黄金用例：`cases/cell.json`（6 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "基本用法",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"带描述的标题\",",
        "  label = \"描述文字\",",
        "  value = \"值\",",
        "  isLink = true,",
        "  onClick = { /* 跳转 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "必填与链接",
      prose = "`required` 在标题前绘制红色星号（`color.danger.default`）；`isLink` 在末尾绘制右箭头图标。",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "标题（主文字）", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "右侧值（次文字，右对齐）", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "标题下方的说明文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "是否绘制底部分隔线（`color.border.lighter`）", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "是否可点击；为 true 时显示右侧指示箭头并响应点击", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "是否在标题前显示必填红星（`color.danger.default`）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "左侧图标名（本地图标体系）；空时不绘制", "`String?`", "`null`")),
    DocRow(cells = listOf("`onClick`", "点击回调；`clickable` 为 false 时不触发", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "标题内容（覆盖 title，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "右侧值内容（覆盖 value，spec 的 value 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "纵向内边距（`spacing.md` 引用）")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "横向内边距（`spacing.md`）")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "标题 / 描述 / 值字号")),
    DocRow(cells = listOf("`cell.gap`", "图标与标题间距（`spacing.xs`）")),
    DocRow(cells = listOf("`icon.size.medium`", "左侧图标与指示箭头尺寸")),
    DocRow(cells = listOf("`cell.borderWidth`", "底分隔线宽度")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "文字行高")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "标题 / 描述 / 值颜色")),
    DocRow(cells = listOf("`color.danger.default`", "必填红星")),
    DocRow(cells = listOf("`color.border.lighter`", "底分隔线")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "标题与右值渲染文字", "`LhCellTest.title_and_value_render`（Compose UI Test）")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label 取次要文字色令牌", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "必填标记取危险色令牌", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "分隔线消费描边令牌", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "点击触发 onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "禁用态不触发", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # JVM 部分",
    "./gradlew :components:cell:connectedAndroidTest              # 交互部分（需设备）",
  ),
)
