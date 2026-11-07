package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Navbar 導覽列",
  lede = "頁面頂部標題列，左 / 中 / 右三段版面配置，常用於返回與標題展示。",
  group = DocGroup.OVERLAY,
  contract = "契約源：`lh-ui-core/specs/navbar.json` · 黃金用例：`cases/navbar.json`（6 條，令牌對應翻譯為 JVM 單測，左右點選翻譯為 Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "用法",
      prose = "三段等寬（各 `weight(1f)`），標題水平置中於可用寬度；預設繪製底部分隔線。",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"頁面標題\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"返回\",",
        "  rightText = \"更多\",",
        "  onLeftClick = { /* 返回 */ },",
        "  onRightClick = { /* 更多 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "左右內容",
      prose = "文字與圖示可同時存在（圖示在左、文字在右，間距 `spacing.xs`）；某一側沒有任何內容時不回應點選\n（黃金用例 `no-left-content-no-onleftclick`：該節點連可點選語義都不存在）。",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "中間標題", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "點選左側區域時觸發", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "點選右側區域時觸發", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "左側按鈕文字；與 leftIcon 可共存", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "左側圖示名（本機圖示體系，如 `\"arrow-left\"`）", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "右側按鈕文字；與 rightIcon 可共存", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "右側圖示名", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "是否繪製底部分隔線", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "標題內容（覆蓋 title，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "左側內容（覆蓋 leftText/leftIcon，spec 的 left 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "右側內容（覆蓋 rightText/rightIcon，spec 的 right 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "導覽列高度（44dp）")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "左右橫向內邊距（跟隨 `spacing.md`）")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "標題與兩側文字字級")),
    DocRow(cells = listOf("`navbar.iconSize`", "兩側圖示尺寸（跟隨 `icon.size.medium`）")),
    DocRow(cells = listOf("`navbar.borderWidth`", "底部分隔線寬度")),
    DocRow(cells = listOf("`zIndex.sticky`", "吸頂層級語義對齊")),
    DocRow(cells = listOf("`color.background.default`", "底色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "標題 / 兩側文字色")),
    DocRow(cells = listOf("`color.border.lighter`", "底部分隔線色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "點選左側觸發 onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick`（Compose UI Test）")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "點選右側觸發 onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "無左側內容時不觸發", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "標題渲染文字", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "分隔線消費描邊令牌", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "高度消費 navbar 令牌", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # JVM 部分",
    "./gradlew :components:navbar:connectedAndroidTest                # 互動部分（需裝置）",
  ),
)
