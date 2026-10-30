# Tag

A semantic-color tag that supports plain (outlined), fully rounded marks, and a close button.

> Spec source: [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> Golden cases: [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag) (6 cases, translated into JVM unit tests + Compose UI Test)

## Types

Six semantic types determine the background and text color.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag types</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">Default</span><span class="sim-tag sim-tag--success">Success</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">Warning</span><span class="sim-tag sim-tag--danger">Danger</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">Info</span></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
LhTag(type = LhTagType.success) { Text("成功") }
```

## Plain

When `plain` is set, the tag has a transparent background with a semantic-color border and text.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">Primary</span><span class="sim-tag sim-tag--plain sim-tag--success">Success</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">Danger</span></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## Size and mark

Three sizes map to the `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` tokens; `mark` uses a fully rounded radius of half the height.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">Mark</span><span class="sim-tag sim-tag--success sim-tag--mark">Success mark</span></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `text` | Tag text (a convenience form; mutually exclusive with `content`) | `String?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `type` | Semantic type | `LhTagType` | `Default` |
| `lhSize` | Size | `LhTagSize` | `Medium` |
| `plain` | Whether the tag is outlined (transparent background + semantic-color border) | `Boolean` | `false` |
| `round` | Whether the corners are fully rounded (half the height) | `Boolean` | `false` |
| `closable` | Whether to show the close button | `Boolean` | `false` |
| `onClose` | Close callback; not triggered when `closable` is false | `(() -> Unit)?` | `null` |
| `content` | Tag content (overrides text; the spec's default slot) | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| Value | Background (non-plain) | Border/text (plain) |
| --- | --- | --- |
| `Default` | `color.background.default` + `color.border.base` border | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| Value | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small` (20dp) | `tag.paddingHorizontal.small` (8dp) | `tag.fontSize.small` (12sp) |
| `Medium` | `tag.height.medium` (24dp) | `tag.paddingHorizontal.medium` (10dp) | `tag.fontSize.medium` (14sp) |
| `Large` | `tag.height.large` (28dp) | `tag.paddingHorizontal.large` (12dp) | `tag.fontSize.large` (16sp) |

## Token reference

| Token | Usage |
| --- | --- |
| `tag.height.{small,medium,large}` | Three heights |
| `tag.paddingHorizontal.{small,medium,large}` | Three horizontal paddings |
| `tag.fontSize.{small,medium,large}` | Three font sizes |
| `tag.borderRadius` / `tag.borderWidth` | Corner radius and border width |
| `color.{primary,success,warning,danger,info}.default` | Semantic background / border / text colors |
| `color.onPrimary` | Non-plain text color |
| `color.disabled.{bg,text,border}` | The three disabled colors |
| `spacing.xs` | Gap between the close button and the text |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `default-type-renders-text` | The default type takes a neutral background + primary text color + a base border | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | The primary type consumes the primary tokens | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain consumes the semantic light-background tokens | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | Small consumes the small tokens (with a reverse assertion that large is not applied) | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round makes the corner radius half the height | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | Tapping close triggers onClose | `LhTagTest.closable_tap_emits_onClose` (Compose UI Test) |

```bash
./gradlew :components:tag:test                             # JVM part
./gradlew :components:tag:connectedAndroidTest            # Interaction part (requires a device)
```
