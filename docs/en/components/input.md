# Input

A controlled single-line input that supports size, disabled, readonly, clearable, password masking, and a maximum length.

> Spec source: [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> Golden cases: [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input) (8 cases, translated into JVM unit tests + Compose UI Test)

## Basic usage

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="Please enter text" /></div>
    <div class="sim-row"><input class="sim-input" value="Pre-filled value" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="Disabled" disabled /></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "请输入内容",
  onValueChange = { value = it },
)
```

## Size and focus

Three sizes map to the `input.height` / `input.paddingHorizontal` / `input.fontSize` tokens; when focused, the border takes the `color.focus` color and widens to `input.focusRingWidth`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input sizes</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium (focus-state demo)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `value` | Controlled value (required) | `String` | — |
| `onValueChange` | Value change callback (required) | `(String) -> Unit` | — |
| `placeholder` | Placeholder text | `String?` | `null` |
| `type` | Input type (plaintext / password) | `LhInputType` | `Text` |
| `lhSize` | Size | `LhInputSize` | `Medium` |
| `disabled` | Whether the input is disabled | `Boolean` | `false` |
| `readonly` | Whether the input is read-only (not editable, but without the disabled style) | `Boolean` | `false` |
| `clearable` | Whether to show the clear button | `Boolean` | `false` |
| `maxlength` | Maximum length (extra input is truncated) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Focus / blur callbacks | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | Prefix / suffix slots | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhInputSize

| Value | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small` (32dp) | `input.paddingHorizontal.small` (12dp) | `input.fontSize.small` (13sp) |
| `Medium` | `input.height.medium` (40dp) | `input.paddingHorizontal.medium` (16dp) | `input.fontSize.medium` (14sp) |
| `Large` | `input.height.large` (48dp) | `input.paddingHorizontal.large` (20dp) | `input.fontSize.large` (16sp) |

## Token reference

| Token | Usage |
| --- | --- |
| `input.height.{small,medium,large}` | Three heights |
| `input.paddingHorizontal.{small,medium,large}` | Three horizontal paddings |
| `input.fontSize.{small,medium,large}` | Three font sizes |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | Corner radius / border width / focus border width |
| `input.clearIconSize` | Clear icon size |
| `color.focus` | Focus border color |
| `color.text.placeholder` | Placeholder text color |
| `color.border.base` | Default border color |
| `color.disabled.{bg,text,border}` | The three disabled colors |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `input-emits-onvaluechange` | Input triggers onValueChange | `LhInputTest.input_emits_onValueChange` (Compose UI Test) |
| `clearable-emits-empty-value` | The clear button emits an empty value | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | The disabled state accepts no input | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | The read-only state accepts no input | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | The placeholder color takes the placeholder token | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | Focus consumes the focus tokens (unfocused takes the neutral border) | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | Small consumes the small size tokens | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | Input beyond the maximum length is truncated | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # JVM part
./gradlew :components:input:connectedAndroidTest              # Interaction part (requires a device)
```
