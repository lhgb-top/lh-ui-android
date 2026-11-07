package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Navbar",
  lede = "A title bar at the top of the page with a left / center / right three-part layout, commonly used for going back and showing a title.",
  group = DocGroup.OVERLAY,
  contract = "Spec source: `lh-ui-core/specs/navbar.json` · Golden cases: `cases/navbar.json` (6 cases; token mappings translated into JVM unit tests, left/right taps translated into Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Usage",
      prose = "The three parts are equally wide (`weight(1f)` each), the title is horizontally centered within the available width, and a bottom divider is drawn by default.",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"页面标题\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"返回\",",
        "  rightText = \"更多\",",
        "  onLeftClick = { /* go back */ },",
        "  onRightClick = { /* more */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Left and right content",
      prose = "Text and icon can coexist (icon on the left, text on the right, gap `spacing.xs`); when a side has no content at all, it does not respond to taps\n(golden case `no-left-content-no-onleftclick`: that node does not even carry the clickable semantics).",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Center title", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "Triggered when the left area is tapped", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "Triggered when the right area is tapped", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "Left button text; can coexist with leftIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "Left icon name (local icon system, e.g. `\"arrow-left\"`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "Right button text; can coexist with rightIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "Right icon name", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Whether to draw the bottom divider", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "Title content (overrides title; the spec's default slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "Left content (overrides leftText/leftIcon; the spec's left slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "Right content (overrides rightText/rightIcon; the spec's right slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "Navbar height (44dp)")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "Left/right horizontal padding (follows `spacing.md`)")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "Font size of the title and both sides' text")),
    DocRow(cells = listOf("`navbar.iconSize`", "Icon size on both sides (follows `icon.size.medium`)")),
    DocRow(cells = listOf("`navbar.borderWidth`", "Bottom divider width")),
    DocRow(cells = listOf("`zIndex.sticky`", "Sticky layering semantics alignment")),
    DocRow(cells = listOf("`color.background.default`", "Background color")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Title / side text color")),
    DocRow(cells = listOf("`color.border.lighter`", "Bottom divider color")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "Tapping the left triggers onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test)")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "Tapping the right triggers onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "With no left content, nothing is triggered", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "The title renders text", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "The divider consumes the border token", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "The height consumes the navbar token", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # JVM part",
    "./gradlew :components:navbar:connectedAndroidTest                # Interaction part (requires a device)",
  ),
)
