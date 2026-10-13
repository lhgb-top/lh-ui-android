package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Navbar 导航栏",
  lede = "页面顶部标题栏，左 / 中 / 右三段布局，常用于返回与标题展示。",
  group = DocGroup.OVERLAY,
  contract = "契约源：`lh-ui-core/specs/navbar.json` · 黄金用例：`cases/navbar.json`（6 条，令牌映射翻译为 JVM 单测，左右点击翻译为 Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "用法",
      prose = "三段等宽（各 `weight(1f)`），标题水平居中于可用宽度；默认绘制底部分隔线。",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"页面标题\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"返回\",",
        "  rightText = \"更多\",",
        "  onLeftClick = { /* 返回 */ },",
        "  onRightClick = { /* 更多 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "左右内容",
      prose = "文字与图标可同时存在（图标在左、文字在右，间距 `spacing.xs`）；某一侧没有任何内容时不响应点击\n（黄金用例 `no-left-content-no-onleftclick`：该节点连可点击语义都不存在）。",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "中间标题", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "点击左侧区域时触发", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "点击右侧区域时触发", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "左侧按钮文字；与 leftIcon 可共存", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "左侧图标名（本地图标体系，如 `\"arrow-left\"`）", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "右侧按钮文字；与 rightIcon 可共存", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "右侧图标名", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "是否绘制底部分隔线", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "标题内容（覆盖 title，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "左侧内容（覆盖 leftText/leftIcon，spec 的 left 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "右侧内容（覆盖 rightText/rightIcon，spec 的 right 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "导航栏高度（44dp）")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "左右横向内边距（跟随 `spacing.md`）")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "标题与两侧文字字号")),
    DocRow(cells = listOf("`navbar.iconSize`", "两侧图标尺寸（跟随 `icon.size.medium`）")),
    DocRow(cells = listOf("`navbar.borderWidth`", "底部分隔线宽度")),
    DocRow(cells = listOf("`zIndex.sticky`", "吸顶层级语义对齐")),
    DocRow(cells = listOf("`color.background.default`", "底色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "标题 / 两侧文字色")),
    DocRow(cells = listOf("`color.border.lighter`", "底部分隔线色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "点击左侧触发 onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick`（Compose UI Test）")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "点击右侧触发 onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "无左侧内容时不触发", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "标题渲染文字", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "分隔线消费描边令牌", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "高度消费 navbar 令牌", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # JVM 部分",
    "./gradlew :components:navbar:connectedAndroidTest                # 交互部分（需设备）",
  ),
)
