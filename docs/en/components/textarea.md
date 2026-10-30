# Textarea

A controlled multi-line input that supports row count, a character counter, and a maximum length.

> Spec source: [`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> Golden cases: [`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea) (6 cases, translated into JVM unit tests + Compose UI Test)

## Basic usage

The minimum height = rows × line height × font size; once content exceeds it, the field scrolls vertically.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="Enter multiple lines of text" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">Line one
Line two
Line three</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## Character counter

When `showCount` is true, a counter is rendered in the bottom-right corner (`textarea.countFontSize`, color `text.secondary`); `maxlength` truncates input.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea counter</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">abc</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render; on a real device the bottom-right corner shows a "3 / 20" counter</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `value` | Controlled value (required) | `String` | — |
| `onValueChange` | Value change callback (required) | `(String) -> Unit` | — |
| `placeholder` | Placeholder text | `String?` | `null` |
| `rows` | Row count (determines the minimum height) | `Int` | `3` |
| `disabled` | Whether the textarea is disabled | `Boolean` | `false` |
| `readonly` | Whether the textarea is read-only | `Boolean` | `false` |
| `showCount` | Whether to show the character counter | `Boolean` | `false` |
| `maxlength` | Maximum length (extra input is truncated) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Focus / blur callbacks | `(() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

## Token reference

| Token | Usage |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | Padding (`spacing.md`) |
| `textarea.fontSize` | Font size (`typography.fontSize.base`) |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | Corner radius / border width / focus border width |
| `textarea.countFontSize` | Counter font size (`typography.fontSize.extraSmall`) |
| `textarea.minHeight` | Minimum-height baseline |
| `typography.lineHeight.primary` | Line height (used to convert rows) |
| `color.text.{primary,secondary,placeholder}` | Text / counter / placeholder colors |
| `color.focus` / `color.border.base` | Focus / default border colors |
| `color.disabled.{bg,text,border}` | The three disabled colors |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `input-emits-onvaluechange` | Input triggers onValueChange | `LhTextareaTest.input_emits_onValueChange` (Compose UI Test) |
| `disabled-blocks-input` | The disabled state accepts no input | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | The counter renders "n / max" | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | Without maxlength, only the length is rendered | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | Focus consumes the focus tokens | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | Input beyond the maximum length is truncated | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # JVM part
./gradlew :components:textarea:connectedAndroidTest           # Interaction part (requires a device)
```
