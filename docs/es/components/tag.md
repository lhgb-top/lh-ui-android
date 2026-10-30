# Etiqueta

Etiqueta de color semántico, admite plano (plain), marca completamente redondeada y botón de cierre.

> Fuente del contrato: [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> Casos dorados: [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag) (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Tipos

Seis tipos semánticos determinan el color de fondo y del texto.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tipos de Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">Predeterminada</span><span class="sim-tag sim-tag--success">Éxito</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">Advertencia</span><span class="sim-tag sim-tag--danger">Peligro</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">Información</span></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
LhTag(type = LhTagType.success) { Text("Éxito") }
```

## Plano (plain)

`plain` usa fondo transparente con borde y texto de color semántico.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">Principal</span><span class="sim-tag sim-tag--plain sim-tag--success">Éxito</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">Peligro</span></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## Tamaño y marca

Tres tamaños se mapean a los tokens `tag.height` / `tag.paddingHorizontal` / `tag.fontSize`; `mark` toma un radio completo equivalente a la mitad de la altura.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tamaños de Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">Marca</span><span class="sim-tag sim-tag--success sim-tag--mark">Marca de éxito</span></div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `text` | Texto de la etiqueta (forma abreviada, alternativa a `content`) | `String?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `type` | Tipo semántico | `LhTagType` | `Default` |
| `lhSize` | Tamaño | `LhTagSize` | `Medium` |
| `plain` | Si es plano (fondo transparente + borde semántico) | `Boolean` | `false` |
| `round` | Si el radio es completo (mitad de la altura) | `Boolean` | `false` |
| `closable` | Si se muestra el botón de cierre | `Boolean` | `false` |
| `onClose` | Devolución de llamada al cerrar; no se dispara cuando `closable` es false | `(() -> Unit)?` | `null` |
| `content` | Contenido de la etiqueta (prevalece sobre text, slot default del spec) | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| Valor | Fondo (no plain) | Borde/texto (plain) |
| --- | --- | --- |
| `Default` | `color.background.default` + borde `color.border.base` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| Valor | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small` (20dp) | `tag.paddingHorizontal.small` (8dp) | `tag.fontSize.small` (12sp) |
| `Medium` | `tag.height.medium` (24dp) | `tag.paddingHorizontal.medium` (10dp) | `tag.fontSize.medium` (14sp) |
| `Large` | `tag.height.large` (28dp) | `tag.paddingHorizontal.large` (12dp) | `tag.fontSize.large` (16sp) |

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `tag.height.{small,medium,large}` | Tres alturas |
| `tag.paddingHorizontal.{small,medium,large}` | Tres rellenos horizontales |
| `tag.fontSize.{small,medium,large}` | Tres tamaños de fuente |
| `tag.borderRadius` / `tag.borderWidth` | Radio de esquina y ancho del borde |
| `color.{primary,success,warning,danger,info}.default` | Fondos / bordes / textos semánticos |
| `color.onPrimary` | Color del texto en el modo no plain |
| `color.disabled.{bg,text,border}` | Tres colores del estado deshabilitado |
| `spacing.xs` | Espacio entre el botón de cierre y el texto |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `default-type-renders-text` | El tipo predeterminado toma fondo neutro + color de texto principal + borde base | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | El tipo primary consume los tokens del color principal | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain consume los tokens de fondo semántico claro | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | El tamaño pequeño consume los tokens small (aserción inversa: large no se aplica) | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round toma un radio de la mitad de la altura | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | Pulsar el cierre dispara onClose | `LhTagTest.closable_tap_emits_onClose` (Compose UI Test) |

```bash
./gradlew :components:tag:test                             # parte JVM
./gradlew :components:tag:connectedAndroidTest             # parte de interacción (requiere dispositivo)
```
