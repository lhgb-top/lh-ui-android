# Dialog

A modal overlay for important confirmations or content that needs the user's focus. The window-level layering is hosted by the Compose `Dialog`.

> Spec source: [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> Golden cases: [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog) (8 cases, translated into JVM unit tests + Compose UI Test)

## Types

`type` determines the confirm button's color (mapped 1:1 to `LhButtonType`); the rest of the layout stays the same.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Title</div>
      <div class="sim-dialog__content">This is body text that explains the impact of the current action.</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Cancel</button>
        <button class="sim-btn sim-btn--primary">Confirm</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* confirm logic */ },
  onCancel = {},
  onClose = { visible = false },
  title = "删除确认",
  content = "删除后不可恢复，确定删除吗？",
  type = LhDialogType.danger,
)
```

## Semantic type and overlay

When `type` is `danger`, the confirm button takes the danger color; `closable` controls whether tapping the overlay or pressing the back button triggers `onClose`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Confirm deletion</div>
      <div class="sim-dialog__content">This action cannot be undone. Delete anyway?</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Cancel</button>
        <button class="sim-btn sim-btn--danger">Confirm</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

When the confirm / cancel button is tapped, the **business callback fires first, then `onClose`** — the host uses this to set `visible` to false.

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `visible` | Whether the dialog is shown; when false, nothing is rendered | `Boolean` | — |
| `onConfirm` | Triggered when the confirm button is tapped | `() -> Unit` | — |
| `onCancel` | Triggered when the cancel button is tapped | `() -> Unit` | — |
| `onClose` | Triggered after confirm / cancel, or (when closable) when the overlay is tapped | `() -> Unit` | — |
| `title` | Title | `String?` | `null` |
| `content` | Body content | `String?` | `null` |
| `type` | Semantic type; determines the confirm button's type (color) | `LhDialogType` | `default` |
| `confirmText` | Confirm button text; when empty, the confirm button is hidden | `String` | `"确认"` |
| `cancelText` | Cancel button text; when empty, the cancel button is hidden | `String` | `"取消"` |
| `closable` | Whether tapping the overlay or pressing back triggers onClose | `Boolean` | `false` |
| `titleContent` | Title content (overrides title; the spec's title slot) | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | Body content (overrides content; the spec's default slot) | `@Composable (() -> Unit)?` | `null` |
| `footer` | Bottom button area (overrides the default buttons; the spec's footer slot) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier (applied to the content card) | `Modifier` | `Modifier` |

### LhDialogType

| Value | Confirm button type |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | The `LhButtonType` of the same name, taking the corresponding semantic color |

## Token reference

| Token | Usage |
| --- | --- |
| `dialog.width` | Content card width (320dp) |
| `dialog.borderRadius` | Card corner radius (follows `radius.base`) |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | Card padding (follows `spacing.lg`) |
| `dialog.titleFontSize` / `dialog.contentFontSize` | Title / body font sizes |
| `dialog.gap` | Spacing between the title, body, and button area |
| `zIndex.modal` / `zIndex.modalBackdrop` | Layering semantics alignment (the Compose `Dialog` hosts the window-level layering) |
| `color.background.overlay` | Overlay color |
| `color.background.default` | Card background color |
| `color.text.primary` / `color.text.regular` | Title / body color |
| `shadow.base` | Card shadow (Compose has no spread; the blur radius is used as elevation) |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `confirm-emits-onconfirm` | Confirm triggers onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | Confirm also triggers onClose | same as above |
| `cancel-emits-oncancel` | Cancel triggers onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | When closable, tapping the overlay triggers onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | When not closable, the overlay tap is swallowed | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | When hidden, nothing is rendered | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | The title renders text | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | The danger type's confirm button takes the danger color | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # JVM part
./gradlew :components:dialog:connectedAndroidTest             # Interaction part (requires a device)
```
