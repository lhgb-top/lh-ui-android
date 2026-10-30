# Navbar

A title bar at the top of the page with a left / center / right three-part layout, commonly used for going back and showing a title.

> Spec source: [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> Golden cases: [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar) (6 cases; token mappings translated into JVM unit tests, left/right taps translated into Compose UI Test)

## Usage

The three parts are equally wide (`weight(1f)` each), the title is horizontally centered within the available width, and a bottom divider is drawn by default.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>Back</div>
      <div class="sim-navbar__title">Page title</div>
      <div class="sim-navbar__side sim-navbar__side--right">More</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
LhNavbar(
  title = "页面标题",
  leftIcon = "arrow-left",
  leftText = "返回",
  rightText = "更多",
  onLeftClick = { /* go back */ },
  onRightClick = { /* more */ },
)
```

## Left and right content

Text and icon can coexist (icon on the left, text on the right, gap `spacing.xs`); **when a side has no content at all, it does not respond to taps**
(golden case `no-left-content-no-onleftclick`: that node does not even carry the clickable semantics).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar variants</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">Icon only</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">Text</div>
      <div class="sim-navbar__title">No divider</div>
      <div class="sim-navbar__side sim-navbar__side--right">Edit</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `title` | Center title | `String?` | `null` |
| `onLeftClick` | Triggered when the left area is tapped | `() -> Unit` | `{}` |
| `onRightClick` | Triggered when the right area is tapped | `() -> Unit` | `{}` |
| `leftText` | Left button text; can coexist with leftIcon | `String?` | `null` |
| `leftIcon` | Left icon name (local icon system, e.g. `"arrow-left"`) | `String?` | `null` |
| `rightText` | Right button text; can coexist with rightIcon | `String?` | `null` |
| `rightIcon` | Right icon name | `String?` | `null` |
| `bordered` | Whether to draw the bottom divider | `Boolean` | `true` |
| `titleContent` | Title content (overrides title; the spec's default slot) | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | Left content (overrides leftText/leftIcon; the spec's left slot) | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | Right content (overrides rightText/rightIcon; the spec's right slot) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

When all of a side's `*Content` / `*Text` / `*Icon` props are empty, that side is not tappable and draws nothing.

## Token reference

| Token | Usage |
| --- | --- |
| `navbar.height` | Navbar height (44dp) |
| `navbar.paddingHorizontal` | Left/right horizontal padding (follows `spacing.md`) |
| `navbar.titleFontSize` | Font size of the title and both sides' text |
| `navbar.iconSize` | Icon size on both sides (follows `icon.size.medium`) |
| `navbar.borderWidth` | Bottom divider width |
| `zIndex.sticky` | Sticky layering semantics alignment |
| `color.background.default` | Background color |
| `color.text.primary` / `color.text.regular` | Title / side text color |
| `color.border.lighter` | Bottom divider color |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | Tapping the left triggers onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test) |
| `rightclick-emits-onrightclick` | Tapping the right triggers onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | With no left content, nothing is triggered | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | The title renders text | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | The divider consumes the border token | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | The height consumes the navbar token | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # JVM part
./gradlew :components:navbar:connectedAndroidTest                # Interaction part (requires a device)
```
