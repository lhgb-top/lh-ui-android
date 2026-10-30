# Theming & Dark Mode

## Token model

The token source of truth of the native bloodline lives in `lh-ui-core/tokens/*.json`; codegen produces the Android artifacts:

| Artifact | Contents | Dark variant |
| --- | --- | --- |
| `LhTokens` | Full light set: Button sizes, Color palette, Motion, Radius, Shadow, Spacing, Typography, ZIndex | — |
| `LhDarkTokens` | Only the `Color` sub-object (**side-by-side object** model) | Dark overrides colors only — sizes, motion and radius are unchanged in dark mode |

> This matches ios / harmony / windows (side-by-side objects) and differs from the docs site (shallow JSON merge).
> The theme layer is designed accordingly: `LhColors` carries colors only, while sizes are read straight from `LhTokens` by the components.

## LhColors

`LightLhColors` / `DarkLhColors` flatten the two generated objects into a single immutable palette:

```kotlin
@Immutable
data class LhColors(
  val primary: Color, val onPrimary: Color,
  val success: Color, val warning: Color, val danger: Color, val info: Color,
  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */
)

val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)
val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)
```

## LhTheme

```kotlin
LhTheme(isDark = true) {
  // LocalLhColors injects DarkLhColors; components inside consume it automatically
}
```

Without an `LhTheme` wrapper, components fall back to `LightLhColors` (light is the default).

## Dark-mode precomputation (important)

The web bloodline evaluates dark colors dynamically with CSS `color-mix()`. Native has no CSS cascade, so core **precomputes the dark expressions into literals** (e.g. `rgba(61,100,158,0.25)`, `#B1C1D8`). Consequence (master plan R-n6):

> **When you change the preset primary color, the precomputed dark values do not follow automatically.** You must recompute them by hand, update `lh-ui-core/tokens/color-dark.json`, and refresh the Design Language Baseline accordingly.

## Token reference

For the full token table (light / dark / notes), see the [lh-ui-core token reference](https://www.lhgb.top/lhui/core/tokens/), or run `pnpm codegen` in the core repo and open `dist/docs/tokens.md`. For component-level token mappings, see the [Button token reference](/en/components/button#token-reference).

## Custom presets (reserved)

`LhTheme(colors = ...)` accepts a custom palette. Preset theme support will arrive in a later release, together with core's theme preset tokens.
