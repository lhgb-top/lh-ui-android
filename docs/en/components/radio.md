# Radio

A controlled radio. Tapping it again after selection does not deselect it (radio semantics).

> Spec source: [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> Golden cases: [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio) (6 cases, translated into JVM unit tests + Compose UI Test)

## States and radio groups

The component itself contains no group logic; mutual exclusion within a group is maintained by the host (use `value` to identify the item within the group).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>Unchecked</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>Checked</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>Disabled (unchecked)</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>Disabled (checked)</label></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("选项一" to "a", "选项二" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## Sizes

Three ring sizes map to the `radio.size.*` tokens; the inner dot diameter = size × `radio.dotScale`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `checked` | Whether the radio is selected (required) | `Boolean` | — |
| `onChange` | Triggered when the radio goes from unchecked to checked, carrying `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | Whether the radio is disabled | `Boolean` | `false` |
| `label` | Label text | `String?` | `null` |
| `value` | Radio value (identifier within the group; the component itself carries no group logic) | `String?` | `null` |
| `lhSize` | Ring size | `LhRadioSize` | `Medium` |
| `labelContent` | Label content (overrides label; the spec's default slot) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhRadioSize

| Value | Size token | Inner dot diameter |
| --- | --- | --- |
| `Small` | `radio.size.small` (16dp) | 16dp × `radio.dotScale` (0.5) |
| `Medium` | `radio.size.medium` (20dp) | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large` (24dp) | 24dp × `radio.dotScale` |

The tap target is the whole row: it becomes selected and fires `onChange(true)` only when it is unchecked and not disabled.

## Token reference

| Token | Usage |
| --- | --- |
| `radio.size.{small,medium,large}` | Three ring sizes |
| `radio.ringWidth` | Outer ring stroke width |
| `radio.dotScale` | Ratio of the inner dot diameter to the outer ring diameter (a fraction) |
| `radio.gap` | Gap between the ring and the label |
| `color.primary.default` | Selected ring and dot color |
| `color.border.base` | Unchecked ring color |
| `color.disabled.{border,text}` | Disabled border / dot / text colors |
| `color.text.primary` | Label color |
| `typography.fontSize.base` | Label font size |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | Tapping an unchecked radio triggers onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test) |
| `tap-checked-keeps-checked` | Tapping a checked radio does not trigger | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | The disabled state does not trigger | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | Selected consumes the primary tokens | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | Unchecked consumes the border token | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | Small consumes the small size tokens | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # JVM part
./gradlew :components:radio:connectedAndroidTest             # Interaction part (requires a device)
```
