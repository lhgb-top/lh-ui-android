# Cargando

Indicador de carga con animación de rotación anular; la duración toma `motion.duration.base` (milisegundos).

> Fuente del contrato: [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> Casos dorados: [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading) (5 casos, componente puramente visual, traducidos a pruebas unitarias JVM)

## Tamaño y tono

Tres tamaños se mapean a los tokens `loading.size.*`; el ancho del trazo toma `loading.strokeWidth` y el tono toma el color semántico.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">Cargando…</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `lhSize` | Diámetro del indicador | `LhLoadingSize` | `Medium` |
| `tone` | Color del anillo | `LhLoadingTone` | `Primary` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhLoadingSize

| Valor | Token de tamaño |
| --- | --- |
| `Small` | `loading.size.small` (16dp) |
| `Medium` | `loading.size.medium` (20dp) |
| `Large` | `loading.size.large` (24dp) |

### LhLoadingTone

| Valor | Token de color |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `loading.size.{small,medium,large}` | Tres diámetros |
| `loading.strokeWidth` | Ancho del trazo del anillo |
| `color.text.regular` | Tono default |
| `color.{primary,success,warning,danger,info}.default` | Tonos semánticos |
| `motion.duration.base` | Duración de una vuelta de rotación (del dispositivo: entero en milisegundos) |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `medium-size-uses-medium-token` | El tamaño mediano toma el token de tamaño medium | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | El tamaño grande toma el token de tamaño large | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | El tono primary toma el token del color principal | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | El tono success toma el token del color de éxito | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | Se renderiza el indicador anular (el ancho del trazo consume el token) | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # parte JVM (componente puramente visual, sin casos de interacción)
```
