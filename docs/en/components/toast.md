# Toast

An ink-colored capsule overlay that manages its own visibility and timing, used for lightweight action feedback. It does not block the user.

> Spec source: [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> Golden cases: [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast) (6 cases — 4 state-machine + 2 rendering — all translated into local tests)

## Usage

A toast is a **self-contained state machine**: the state is held by `LhToastState` — the host calls `show(text)` to display it and `hide()` to hide it, and it hides itself automatically when the timer expires, firing `onClose`. While visible, the component drives `tick` on its own frame by frame; the host can also call it itself to take over the timing.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">Success</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
val state = rememberLhToastState(onClose = { /* optional: log the close */ })
Column {
  LhButton(lhOnClick = { state.show("已保存") }) { Text("触发") }
  LhToast(state = state)
}
```

## Semantic types

For any type other than `default`, a semantic-color dot with a diameter equal to the font size is drawn in front of the text.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast types</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>Success message</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>Warning message</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>Error message</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>Primary message</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## Positioning

`lhPosition` takes `top` / `center` / `bottom`, leaving a `spacing.xl` margin at both ends; the width is derived from text measurement and capped at 80% of the available width.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast positions</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">Top</div>
    <div class="sim-toast sim-toast--bottom">Bottom</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `state` | Toast state machine (holds visibility, text, and timing) | `LhToastState` | — |
| `type` | Semantic type; for any value other than default, a semantic-color dot is drawn | `LhToastType` | `default` |
| `lhPosition` | Position within the available area | `LhToastPosition` | `center` |
| `modifier` | Modifier (applied to the capsule) | `Modifier` | `Modifier` |

### LhToastState

| Member | Description |
| --- | --- |
| `show(text: String)` | Shows the toast and resets the timer |
| `hide()` | Hides immediately — **does not trigger onClose** |
| `tick(deltaMs: Int): Boolean` | Advances the timer; hides, fires onClose, and returns true when it expires |
| `visible` / `text` / `duration` | Current state (observable) |

Factory: `rememberLhToastState(duration = 2000, onClose = {})`.

### LhToastType / LhToastPosition

| Value | Dot color |
| --- | --- |
| `default` | No dot |
| `primary` / `success` / `warning` / `danger` / `info` | The corresponding semantic color |

`LhToastPosition`: `top` / `center` / `bottom`.

## Token reference

| Token | Usage |
| --- | --- |
| `toast.maxWidth` | Capsule maximum width (240dp; in practice the smaller of this and 80% of the available width) |
| `toast.borderRadius` | Capsule corner radius (follows `radius.base`) |
| `toast.paddingHorizontal` / `toast.paddingVertical` | Capsule padding |
| `toast.fontSize` | Text font size (the dot diameter equals this value) |
| `toast.gap` | Gap between the dot and the text |
| `zIndex.toast` | Layering semantics alignment (the Compose `Popup` provides window-level layering on its own) |
| `color.background.overlay` (mapped to ink by the theme layer) | Capsule background color |
| `color.onPrimary` | Text color |
| `color.{primary,success,warning,danger,info}.default` | Semantic dot colors |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `show-makes-visible` | After show, the toast is visible and renders its text | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | Hides automatically when the timer expires and fires onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | Before the timer expires, it stays visible and does not fire onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide does not fire onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | The success type consumes the success color | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | When hidden, nothing is rendered | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # JVM part
./gradlew :components:toast:connectedAndroidTest                 # Interaction part (requires a device)
```
