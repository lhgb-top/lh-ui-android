# Diálogo

Superposición modal, para confirmaciones importantes o contenido que requiere la atención del usuario. El nivel de jerarquía de ventana lo gestiona el `Dialog` de Compose.

> Fuente del contrato: [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> Casos dorados: [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog) (8 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Tipos

`type` determina el color del botón de confirmación (mapeo 1:1 a `LhButtonType`); el resto de la disposición no varía.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Título</div>
      <div class="sim-dialog__content">Este es un texto del cuerpo que explica el impacto de la acción actual.</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Cancelar</button>
        <button class="sim-btn sim-btn--primary">Confirmar</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* lógica de confirmación */ },
  onCancel = {},
  onClose = { visible = false },
  title = "Confirmar eliminación",
  content = "Esta acción eliminará el elemento de forma permanente. ¿Desea continuar?",
  type = LhDialogType.danger,
)
```

## Tipo semántico y capa de cobertura

Cuando `type` es `danger`, el botón de confirmación toma el color de peligro; `closable` controla si la pulsación en la capa de cobertura / la tecla de retroceso dispara `onClose`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Confirmar eliminación</div>
      <div class="sim-dialog__content">Esta acción eliminará el elemento de forma permanente. ¿Desea continuar?</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Cancelar</button>
        <button class="sim-btn sim-btn--danger">Confirmar</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

Tras pulsar Confirmar / Cancelar, **primero se dispara la devolución de llamada de negocio y luego `onClose`**; el host, en consecuencia, pone `visible` a false.

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `visible` | Si se muestra; cuando es false, no se renderiza nada | `Boolean` | — |
| `onConfirm` | Se dispara al pulsar el botón de confirmación | `() -> Unit` | — |
| `onCancel` | Se dispara al pulsar el botón de cancelación | `() -> Unit` | — |
| `onClose` | Se dispara tras confirmar/cancelar o (con closable) al pulsar la capa de cobertura | `() -> Unit` | — |
| `title` | Título | `String?` | `null` |
| `content` | Contenido del cuerpo | `String?` | `null` |
| `type` | Tipo semántico, determina el tipo (color) del botón de confirmación | `LhDialogType` | `default` |
| `confirmText` | Texto del botón de confirmación; si está vacío, no se muestra el botón de confirmación | `String` | `"确认"` |
| `cancelText` | Texto del botón de cancelación; si está vacío, no se muestra el botón de cancelación | `String` | `"取消"` |
| `closable` | Si la pulsación en la capa de cobertura / la tecla de retroceso dispara onClose | `Boolean` | `false` |
| `titleContent` | Contenido del título (prevalece sobre title, slot title del spec) | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | Contenido del cuerpo (prevalece sobre content, slot default del spec) | `@Composable (() -> Unit)?` | `null` |
| `footer` | Zona de botones inferior (prevalece sobre los botones predeterminados, slot footer del spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier (aplicado a la tarjeta de contenido) | `Modifier` | `Modifier` |

### LhDialogType

| Valor | Tipo del botón de confirmación |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | El `LhButtonType` del mismo nombre, con el color semántico correspondiente |

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `dialog.width` | Ancho de la tarjeta de contenido (320dp) |
| `dialog.borderRadius` | Radio de esquina de la tarjeta (sigue `radius.base`) |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | Relleno de la tarjeta (sigue `spacing.lg`) |
| `dialog.titleFontSize` / `dialog.contentFontSize` | Tamaños de fuente del título / del cuerpo |
| `dialog.gap` | Espacio entre el título, el cuerpo y la zona de botones |
| `zIndex.modal` / `zIndex.modalBackdrop` | Alineación semántica de la jerarquía (el `Dialog` de Compose gestiona el nivel de jerarquía de ventana) |
| `color.background.overlay` | Color de la capa de cobertura |
| `color.background.default` | Color de fondo de la tarjeta |
| `color.text.primary` / `color.text.regular` | Colores del título / del cuerpo |
| `shadow.base` | Sombra de la tarjeta (Compose no admite spread; se toma el radio de desenfoque como elevation) |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `confirm-emits-onconfirm` | Confirmar dispara onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | Tras confirmar se dispara onClose | Ídem |
| `cancel-emits-oncancel` | Cancelar dispara onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | Con closable, pulsar la capa de cobertura dispara onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | Sin closable, la pulsación en la capa de cobertura se descarta | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | Cuando no es visible, no se renderiza | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | El título renderiza texto | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | El botón de confirmación del tipo danger toma el color de peligro | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # parte JVM
./gradlew :components:dialog:connectedAndroidTest             # parte de interacción (requiere dispositivo)
```
