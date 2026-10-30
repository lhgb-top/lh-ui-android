# Cell

A list cell with a three-part layout — icon / title + label / right-side value plus arrow — commonly composed into lists.

> Spec source: [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> Golden cases: [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell) (6 cases, translated into JVM unit tests + Compose UI Test)

## Basic usage

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">Title text</div>
      <div class="sim-cell__value">Right value</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">Title with a label</div>
        <div class="sim-cell__label">Label text</div>
      </div>
      <div class="sim-cell__value">Value</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Required</div>
      <div class="sim-cell__value">Detail</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
LhCell(
  title = "带描述的标题",
  label = "描述文字",
  value = "值",
  isLink = true,
  lhOnClick = { /* navigate */ },
)
```

## Required and link

`required` draws a red asterisk in front of the title (`color.danger.default`); `isLink` draws a right-arrow icon at the end.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell variants</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Required</div>
      <div class="sim-cell__value">Arrow →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">Borderless</div>
      <div class="sim-cell__value">Value</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `title` | Title (primary text) | `String?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `value` | Right-side value (secondary text, right-aligned) | `String?` | `null` |
| `label` | Description text below the title | `String?` | `null` |
| `bordered` | Whether to draw the bottom divider (`color.border.lighter`) | `Boolean` | `true` |
| `clickable` | Whether the cell is tappable; when true, shows a trailing indicator arrow and responds to taps | `Boolean` | `false` |
| `required` | Whether to show the red required asterisk in front of the title (`color.danger.default`) | `Boolean` | `false` |
| `icon` | Left-side icon name (local icon system); nothing is drawn when empty | `String?` | `null` |
| `lhOnClick` | Tap callback; not triggered when `clickable` is false | `(() -> Unit)?` | `null` |
| `titleContent` | Title content (overrides title; the spec's default slot) | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | Right-side value content (overrides value; the spec's value slot) | `@Composable (() -> Unit)?` | `null` |

`clickable` controls both "whether the trailing arrow is drawn" and "whether the whole row responds to taps" — a tap only fires when `clickable = true` **and** `lhOnClick` is non-null.

## Token reference

| Token | Usage |
| --- | --- |
| `cell.paddingVertical` | Vertical padding (references `spacing.md`) |
| `cell.paddingHorizontal` | Horizontal padding (`spacing.md`) |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | Title / label / value font sizes |
| `cell.gap` | Gap between the icon and the title (`spacing.xs`) |
| `icon.size.medium` | Left-side icon and indicator arrow size |
| `cell.borderWidth` | Bottom divider width |
| `typography.lineHeight.primary` | Text line height |
| `color.text.{primary,secondary}` | Title / label / value colors |
| `color.danger.default` | The red required asterisk |
| `color.border.lighter` | The bottom divider |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `title-and-value-render` | The title and right value render their text | `LhCellTest.title_and_value_render` (Compose UI Test) |
| `label-renders-secondary-tone` | The label takes the secondary text token | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | The required marker takes the danger token | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | The divider consumes the border token | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | A tap triggers onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | The non-clickable state does not trigger | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # JVM part
./gradlew :components:cell:connectedAndroidTest              # Interaction part (requires a device)
```
