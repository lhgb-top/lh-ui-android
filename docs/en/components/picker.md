# Picker

A single-column bottom wheel picker. When scrolling stops, the item closest to the center indicator is the selected one.

> Spec source: [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> Golden cases: [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker) (8 cases; clamping and token mappings translated into JVM unit tests, scrolling and button interactions translated into Compose UI Test)

## Usage

It pops up from the bottom: the overlay on top, the panel below (with rounded top corners). The toolbar has three parts: cancel / title / confirm.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">Cancel</button>
        <div class="sim-picker__toolbar-title">Select</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">Confirm</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">Option 1</div>
        <div class="sim-picker__item sim-picker__item--selected">Option 2</div>
        <div class="sim-picker__item">Option 3</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("选项一", "选项二", "选项三")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "请选择",
)
```

## Selection mechanism

The wheel is a scrollable list where every item is `picker.itemHeight` tall; the center indicator is two horizontal lines spaced one itemHeight apart.
After scrolling stops, the scrolled distance is rounded to derive the selected index, and `onValueChange` fires when it changes.
When `value` is out of range, `LhPickerDefaults.clamp` clamps it into the candidate range.

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `visible` | Whether the picker is shown; when false, nothing is rendered | `Boolean` | — |
| `options` | Array of candidate item texts (single column) | `List<String>` | — |
| `onValueChange` | Triggered when the wheel stops and the selected item changes, carrying the new index | `(Int) -> Unit` | — |
| `onConfirm` | Triggered when the confirm button is tapped, carrying the currently selected index | `(Int) -> Unit` | — |
| `onCancel` | Triggered when the cancel button is tapped | `() -> Unit` | — |
| `onClose` | Triggered after confirm / cancel; the host uses this to set visible to false | `() -> Unit` | — |
| `value` | Index of the selected item (0-based); clamped when out of range | `Int` | `0` |
| `title` | Title in the middle of the toolbar | `String?` | `null` |
| `confirmText` | Confirm button text | `String` | `"确认"` |
| `cancelText` | Cancel button text | `String` | `"取消"` |
| `content` | Wheel content area (overrides the default candidate rendering; the spec's default slot) | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | Toolbar (overrides the default confirm / title / cancel; the spec's toolbar slot) | `@Composable (() -> Unit)?` | `null` |
| `listState` | Wheel list state (provided by default via `rememberLhPickerListState(value, options)`; can be injected from the outside in tests) | `LazyListState` | see left |
| `modifier` | Modifier (applied to the panel) | `Modifier` | `Modifier` |

Test hook: `rememberLhPickerListState(value, options)` returns a `LazyListState` initially positioned at `value`; instrumented tests can use `listState.dispatchRawDelta(...)` to drive the scroll deterministically.

## Token reference

| Token | Usage |
| --- | --- |
| `picker.height` | Panel height (260dp) |
| `picker.itemHeight` | Item height (40dp; also the center indicator spacing) |
| `picker.toolbarHeight` | Toolbar height (44dp) |
| `picker.itemFontSize` | Candidate item font size |
| `picker.borderRadius` | Panel top corner radius |
| `picker.paddingHorizontal` | Panel horizontal padding (follows `spacing.md`) |
| `zIndex.modal` / `zIndex.modalBackdrop` | Layering semantics alignment (the Compose `Popup` provides window-level layering on its own) |
| `color.background.overlay` | Overlay color |
| `color.background.default` | Panel background color |
| `color.text.primary` / `color.text.regular` | Title / candidate item color |
| `color.border.lighter` | Center indicator line color |
| `color.primary.default` | Confirm button color |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | Confirm carries the current index + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | Confirm also triggers onClose | same as above |
| `cancel-emits-oncancel` | Cancel triggers onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | Scrolling changes the selection and triggers onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | A negative value is clamped to 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | An out-of-range value is clamped to the last item | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | The title renders text | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | When hidden, nothing is rendered | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # JVM part
./gradlew :components:picker:connectedAndroidTest               # Interaction part (requires a device)
```
