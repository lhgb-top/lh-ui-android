# Switch

A two-state toggle with a capsule track and a circular thumb, supporting a loading-state placeholder.

> Spec source: [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> Golden cases: [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch) (6 cases, translated into JVM unit tests + Compose UI Test)

## States

When on, the track takes the primary color; when off, it takes the border color. The thumb diameter = height - 2 × `switch.gap`, and the slide animation uses `motion.duration.base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>Off</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>On</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>Disabled (off)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>Disabled (on)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Loading (a spinner is shown in the thumb position; taps are ignored)</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## Sizes

Three sizes map to the `switch.width` / `switch.height` tokens.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `checked` | Whether the switch is on (required) | `Boolean` | — |
| `onChange` | State change callback, carrying the new state | `(Boolean) -> Unit` | — |
| `disabled` | Whether the switch is disabled | `Boolean` | `false` |
| `loading` | Whether the switch is loading (a spinner is shown in the thumb position; taps are ignored) | `Boolean` | `false` |
| `lhSize` | Size | `LhSwitchSize` | `Medium` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhSwitchSize

| Value | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small` (36dp) | `switch.height.small` (20dp) |
| `Medium` | `switch.width.medium` (44dp) | `switch.height.medium` (24dp) |
| `Large` | `switch.width.large` (52dp) | `switch.height.large` (28dp) |

The track is a capsule (corner radius is half the height); the tap target is the track area — a tap toggles the state and fires `onChange`.

## Token reference

| Token | Usage |
| --- | --- |
| `switch.width.{small,medium,large}` | Three track widths |
| `switch.height.{small,medium,large}` | Three track heights |
| `switch.gap` | Padding between the thumb and the track (thumb diameter = height - 2 × gap) |
| `color.primary.default` | On-track color |
| `color.border.base` | Off-track color |
| `color.onPrimary` | Thumb color |
| `color.disabled.{bg,text}` | Disabled track / thumb colors |
| `motion.duration.base` / `motion.easing.easeInOut` | Thumb slide animation |
| `loading.strokeWidth` | Loading ring stroke width (reused) |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | A tap toggles the state and emits onChange | `LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-turns-off` | Tapping the on state turns it off | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | The disabled state does not trigger | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | The loading state does not trigger | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | On consumes the primary token | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | Off consumes the border token | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # JVM part
./gradlew :components:switch:connectedAndroidTest            # Interaction part (requires a device)
```
