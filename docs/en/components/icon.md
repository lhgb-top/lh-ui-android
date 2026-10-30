# Icon

The base icon component. Icons in other components (cell / input / navbar, etc.) are all rendered through this component's local registry.

> Spec source: [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> Golden cases: [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon) (5 cases, translated into JVM unit tests + Compose UI Test)

## Size and tone

Three sizes map to the `icon.size.*` tokens; the `default` tone takes `color.text.primary`, and the others take semantic colors.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">Default color (text.primary)</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## Semantic tones

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon tones</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## Icon registry

`LhIcons` is the local registry mapping icon names to Material `ImageVector`s. Unregistered names return `null` (nothing is drawn, no exception thrown):

| Icon name | Material vector |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `name` | Icon name (resolved by the `LhIcons` registry) | `String` | — |
| `lhSize` | Icon size | `LhIconSize` | `Medium` |
| `tone` | Semantic tone | `LhIconTone` | `Default` |
| `contentDescription` | Accessibility description; defaults to the icon name — pass `null` explicitly to declare a decorative icon | `String?` | `name` |
| `lhOnClick` | Tap callback; not tappable when null | `(() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhIconSize

| Value | Size token |
| --- | --- |
| `Small` | `icon.size.small` (16dp) |
| `Medium` | `icon.size.medium` (20dp) |
| `Large` | `icon.size.large` (24dp) |

### LhIconTone

| Value | Color token |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## Token reference

| Token | Usage |
| --- | --- |
| `icon.size.{small,medium,large}` | Three sizes |
| `color.text.primary` | default tone |
| `color.{primary,success,warning,danger,info}.default` | Semantic tones |
| `color.text.{regular,secondary}` | regular / secondary tones |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `click-emits-onclick` | A tap triggers onClick | `LhIconTest.click_emits_onClick` (Compose UI Test) |
| `default-tone-uses-text-primary` | The default tone takes the primary text token | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | The primary tone takes the primary token | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | Small takes the small size token (with a reverse assertion that large is not applied) | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | A registered icon name resolves to a vector; an unregistered name draws nothing | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # JVM part
./gradlew :components:icon:connectedAndroidTest            # Interaction part (requires a device)
```
