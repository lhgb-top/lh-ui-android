# Interruptor

Interruptor de dos estados con pista en cápsula y pulgar circular; admite el marcador de posición del estado de carga.

> Fuente del contrato: [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> Casos dorados: [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch) (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Estados

Activado: la pista toma el color principal; desactivado: la pista toma el color del borde; el diámetro del pulgar = altura - 2 × `switch.gap`; la animación de desplazamiento toma `motion.duration.base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>Desactivado</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Activado</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>Deshabilitado (desactivado)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>Deshabilitado (activado)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Cargando (se muestra un anillo de carga en la posición del pulgar; no responde a la pulsación)</div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## Tamaños

Tres tamaños se mapean a los tokens `switch.width` / `switch.height`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `checked` | Si está activado (obligatorio) | `Boolean` | — |
| `onChange` | Devolución de llamada de cambio de estado, con el nuevo valor | `(Boolean) -> Unit` | — |
| `disabled` | Si está deshabilitado | `Boolean` | `false` |
| `loading` | Si está cargando (se muestra un anillo de carga en la posición del pulgar; no responde a la pulsación) | `Boolean` | `false` |
| `lhSize` | Tamaño | `LhSwitchSize` | `Medium` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhSwitchSize

| Valor | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small` (36dp) | `switch.height.small` (20dp) |
| `Medium` | `switch.width.medium` (44dp) | `switch.height.medium` (24dp) |
| `Large` | `switch.width.large` (52dp) | `switch.height.large` (28dp) |

La pista es una cápsula (el radio de esquina es la mitad de la altura); la pulsación golpea la zona de la pista, alterna el estado del interruptor y dispara `onChange`.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `switch.width.{small,medium,large}` | Tres anchos de pista |
| `switch.height.{small,medium,large}` | Tres alturas de pista |
| `switch.gap` | Hueco entre el pulgar y la pista (diámetro del pulgar = altura - 2 × gap) |
| `color.primary.default` | Color de la pista activada |
| `color.border.base` | Color de la pista desactivada |
| `color.onPrimary` | Color del pulgar |
| `color.disabled.{bg,text}` | Colores de la pista / el pulgar deshabilitados |
| `motion.duration.base` / `motion.easing.easeInOut` | Animación de desplazamiento del pulgar |
| `loading.strokeWidth` | Ancho del anillo de carga (reutilizado) |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | La pulsación alterna y dispara onChange | `LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-turns-off` | Una pulsación estando activado lo desactiva | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | El estado deshabilitado no dispara | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | El estado de carga no dispara | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | El estado activado consume el token del color principal | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | El estado desactivado consume el token del borde | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # parte JVM
./gradlew :components:switch:connectedAndroidTest            # parte de interacción (requiere dispositivo)
```
