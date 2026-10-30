# Button

A basic action button. It triggers an immediate action and supports type, size, loading, disabled, icon, and a Chinese-style pattern background.

> Spec source: [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> Golden cases: [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button) (8 cases, translated into JVM unit tests + Compose UI Test)

## Types

Six types determine the background and text color.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button types</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">Default</button>
      <button class="sim-btn sim-btn--primary">Primary</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">Success</button>
      <button class="sim-btn sim-btn--warning">Warning</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">Danger</button>
      <button class="sim-btn sim-btn--info">Info</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("主要按钮") }
```

## Sizes

Three sizes map to the `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` tokens.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Small</button>
      <button class="sim-btn">Medium</button>
      <button class="sim-btn sim-btn--large">Large</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## States

Neither disabled nor loading triggers `lhOnClick`; when both are set, **disabled wins** (golden case `disabled-precedes-loading`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button states</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Normal</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Disabled</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Loading
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Disabled wins</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## Icons and pattern background

Icon names resolve against the local icon system (`LhButtonIcons`, currently supporting search / refresh / check / close; unregistered names draw nothing).
The pattern background consumes the `color.decoration` token — it is layered above the background color but below the text, so it does not affect contrast.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icons & patterns</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Search</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>Default</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Primary pattern</button>
      <button class="sim-btn sim-btn--pattern">Default pattern</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## Light and dark theme

Toggle the site theme in the top-right corner and the button colors inside the simulator phone frame change with it; on a real device, switch via `LhTheme(isDark = true)`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dark buttons</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Primary</button>
      <button class="sim-btn">Default</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Disabled</button>
      <button class="sim-btn sim-btn--success">Success</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render; light/dark values are all taken from the core token snapshot</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `lhOnClick` | Tap callback; not triggered when `disabled` or `loading` is true | `() -> Unit` | — |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `type` | Button type; determines the background and text color | `LhButtonType` | `Default` |
| `lhSize` | Button size; maps to the size tokens | `LhButtonSize` | `Medium` |
| `disabled` | Whether the button is disabled | `Boolean` | `false` |
| `loading` | Whether the button is loading (shows an indicator and does not trigger lhOnClick) | `Boolean` | `false` |
| `icon` | Icon name (resolved by the local icon system) | `String?` | `null` |
| `bgPattern` | Whether to draw the Chinese-style background pattern | `Boolean` | `false` |
| `content` | Button text content (the spec's default slot) | `@Composable () -> Unit` | — |

### LhButtonType

| Value | Background | Text color |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| Value | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small` (32dp) | `button.paddingHorizontal.small` (16dp) | `button.fontSize.small` (13sp) | `button.iconSize.small` (16dp) |
| `Medium` | `button.height.medium` (40dp) | `button.paddingHorizontal.medium` (20dp) | `button.fontSize.medium` (14sp) | `button.iconSize.medium` (18dp) |
| `Large` | `button.height.large` (48dp) | `button.paddingHorizontal.large` (24dp) | `button.fontSize.large` (16sp) | `button.iconSize.large` (20dp) |

The disabled state overrides: background `color.disabled.bg`, text `color.disabled.text`, border `color.disabled.border`.

## Token reference

Tokens consumed by the button (aligned one-to-one with the `tokenRefs` of `specs/button.json`):

| Token | Usage |
| --- | --- |
| `button.height.{small,medium,large}` | Three heights |
| `button.paddingHorizontal.{small,medium,large}` | Three horizontal paddings |
| `button.fontSize.{small,medium,large}` | Three font sizes |
| `button.iconSize.{small,medium,large}` | Three icon sizes |
| `button.borderRadius` / `button.borderWidth` | Corner radius (follows `radius.base`) and border width |
| `button.fontWeight` | Font weight (follows `typography.fontWeight.primary`) |
| `color.primary.default` / `color.onPrimary` | Primary type background / text |
| `color.{success,warning,danger,info}.default` | Semantic type backgrounds |
| `color.background.default` / `color.text.primary` / `color.border.base` | Default type background / text / border |
| `color.disabled.{bg,text,border}` | The three disabled colors |
| `color.decoration` | Pattern color |
| `shadow.lighter` | Default-type elevation |
| `motion.duration.fast` / `motion.easing.easeInOut` | Press-feedback animation |

## Golden case reference

How the 8 assertions of `cases/button.json` map to local tests:

| case id | Assertion | Local test |
| --- | --- | --- |
| `click-emits-onclick` | A tap triggers onClick exactly once | `LhButtonTest.click_emits_onClick` (Compose UI Test) |
| `disabled-blocks-click` | The disabled state does not trigger | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | The loading state does not trigger | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | Disabled wins | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | The default type renders text | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | The primary type consumes primary tokens | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | Small consumes the small size tokens | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | The pattern background consumes the decoration color | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | A registered icon name renders a vector; an unregistered name draws nothing | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # JVM part
./gradlew :components:button:connectedAndroidTest        # Interaction part (requires a device)
```
