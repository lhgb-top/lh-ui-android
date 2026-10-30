# Loading

A loading indicator with a spinning ring animation. The duration comes from `motion.duration.base` (in milliseconds).

> Spec source: [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> Golden cases: [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading) (5 cases; a purely presentational component, translated into JVM unit tests)

## Size and tone

Three sizes map to the `loading.size.*` tokens, the stroke width comes from `loading.strokeWidth`, and tones take semantic colors.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">Loading…</div>
  </div>
</div>
<p class="sim-note">HTML mock preview (Android / Compose look), not an on-device render</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `lhSize` | Indicator diameter | `LhLoadingSize` | `Medium` |
| `tone` | Ring color | `LhLoadingTone` | `Primary` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhLoadingSize

| Value | Size token |
| --- | --- |
| `Small` | `loading.size.small` (16dp) |
| `Medium` | `loading.size.medium` (20dp) |
| `Large` | `loading.size.large` (24dp) |

### LhLoadingTone

| Value | Color token |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## Token reference

| Token | Usage |
| --- | --- |
| `loading.size.{small,medium,large}` | Three diameters |
| `loading.strokeWidth` | Ring stroke width |
| `color.text.regular` | default tone |
| `color.{primary,success,warning,danger,info}.default` | Semantic tones |
| `motion.duration.base` | Duration of one full rotation (an integer number of milliseconds on the client) |

## Golden case reference

| case id | Assertion | Local test |
| --- | --- | --- |
| `medium-size-uses-medium-token` | Medium takes the medium size token | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | Large takes the large size token | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | The primary tone takes the primary token | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | The success tone takes the success token | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | The ring indicator renders (the stroke width consumes a token) | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # JVM part (purely presentational, no interaction cases)
```
