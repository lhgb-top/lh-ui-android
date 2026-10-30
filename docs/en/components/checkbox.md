# Checkbox

A controlled checkbox that supports the indeterminate state and disabled.

> Spec source: [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> Golden cases: [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox) (6 cases, translated into JVM unit tests + Compose UI Test)

## States

Checked and indeterminate render a solid primary background with an `onPrimary` indicator (a checkmark or a dash); unchecked is outlined only.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>Unchecked</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>Checked</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>Indeterminate</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Disabled (checked)</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Disabled (unchecked)</label></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "同意协议",
)
```

## Sizes

Three box sizes map to the `checkbox.size.*` tokens.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `checked` | Whether the checkbox is checked (required) | `Boolean` | — |
| `onCheckedChange` | Checked-state change callback, carrying the new checked state | `(Boolean) -> Unit` | — |
| `indeterminate` | Whether the checkbox is indeterminate | `Boolean` | `false` |
| `disabled` | Whether the checkbox is disabled | `Boolean` | `false` |
| `label` | Label text | `String?` | `null` |
| `lhSize` | Box size | `LhCheckboxSize` | `Medium` |
| `labelContent` | Label content (overrides label; the spec's default slot) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhCheckboxSize

| Value | Size token |
| --- | --- |
| `Small` | `checkbox.size.small` (16dp) |
| `Medium` | `checkbox.size.medium` (20dp) |
| `Large` | `checkbox.size.large` (24dp) |

The tap target is the whole row (box + label): tapping the indeterminate state selects it; otherwise the checked state is toggled.

## Token reference

| Token | Usage |
| --- | --- |
| `checkbox.size.{small,medium,large}` | Three box sizes |
| `checkbox.borderRadius` / `checkbox.borderWidth` | Box corner radius and border width |
| `checkbox.checkWidth` | Checkmark / dash stroke width |
| `checkbox.gap` | Gap between the box and the label |
| `color.primary.default` / `color.onPrimary` | Checked background / indicator color |
| `color.border.base` | Unchecked border color |
| `color.disabled.{bg,border,text}` | The three disabled colors |
| `color.text.primary` | Label color |
| `typography.fontSize.base` | Label font size |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | A tap toggles the state and emits onChange | `LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-unchecks` | Tapping the checked state unchecks it | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | Tapping the indeterminate state selects it | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | The disabled state does not trigger | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | Checked consumes the primary tokens | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | Unchecked consumes the border token | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # JVM part
./gradlew :components:checkbox:connectedAndroidTest           # Interaction part (requires a device)
```
