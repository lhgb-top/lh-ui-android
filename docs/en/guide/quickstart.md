# Quick Start

## Minimal example

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* handle the tap */ },
    ) {
      Text("主要按钮")
    }
  }
}
```

`LhTheme` is the theme container — every Lh component inside it consumes tokens automatically; without the wrapper, components fall back to the light palette.

## Type and size

```kotlin
// Six types
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("默认") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("主要") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("成功") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("警告") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("危险") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("信息") }

// Three sizes
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("小型") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("中型") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("大型") }
```

## States

```kotlin
// Disabled: ignores taps and renders the disabled style
LhButton(disabled = true, lhOnClick = {}) { Text("禁用") }

// Loading: shows an indicator and does not trigger lhOnClick
LhButton(loading = true, lhOnClick = {}) { Text("加载中") }

// Both: disabled wins (golden case disabled-precedes-loading)
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("禁用优先") }
```

## Icons and pattern background

```kotlin
// Icon names resolve against the local icon system (see LhButtonIcons; unregistered names draw nothing)
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("搜索") }

// Chinese-style background pattern (consumes the color.decoration token)
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("花纹底") }
```

## Light and dark theme

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // Every component inside switches to dark tokens automatically (precomputed by core, no runtime color math)
}
```

## Native docs module

Besides this website, the repo also ships an **Android-native docs module** (`:docsui`): its doc pages mirror this site's structure 1:1, but the previews are **real, interactive, live-rendered Compose** (buttons are tappable, inputs accept typing, dialogs pop up) rather than static HTML mocks.

**Drag-and-drop ready**: once any Android project depends on it, a single `LhDocs.launch(context)` opens the full documentation (14 component pages + 5 guide pages + light/dark toggle + live previews):

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // or the Maven coordinate top.lhgb.ui:docsui

// Any entry point (e.g. the click callback of a "Component docs" button)
LhDocs.launch(context)
```

The library's manifest automatically merges in an `exported = false` `LhDocsActivity`, so no launcher icon is injected into the host app.

**Standalone build**: `:docsapp` is a paper-thin application module (a single Activity) — building it on its own yields a standalone "lh-ui docs" app:

```bash
./gradlew :docsapp:installDebug
```

## Component example preview

Native UI cannot be rendered live in the browser, so this site presents the look with an **inline static HTML phone-frame simulator**:

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">Default</button>
      <button class="sim-btn sim-btn--primary">Primary</button>
      <button class="sim-btn sim-btn--success">Success</button>
      <button class="sim-btn sim-btn--warning">Warning</button>
      <button class="sim-btn sim-btn--danger">Danger</button>
      <button class="sim-btn sim-btn--info">Info</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Small</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">Large</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Disabled</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Loading
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Search</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Pattern</button>
    </div>
  </div>
</div>
<p class="sim-note">An HTML mock preview of the Android (Compose) look — not an on-device render. Style values are taken from the core token snapshot, so they share the same source and values as the real device. For the true look, refer to <code>@Preview</code> and device screenshots.</p>

> The simulator follows the site's dark mode: toggle the theme in the top-right corner and the colors inside the phone frame change with it (dark tokens are precomputed by core).
