package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Diálogo",
  lede = "Superposición modal, para confirmaciones importantes o contenido que requiere la atención del usuario. El nivel de jerarquía de ventana lo gestiona el `Dialog` de Compose.",
  group = DocGroup.OVERLAY,
  contract = "Fuente del contrato: `lh-ui-core/specs/dialog.json` · Casos dorados: `cases/dialog.json` (8 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Tipos",
      prose = "`type` determina el color del botón de confirmación (mapeo 1:1 a `LhButtonType`); el resto de la disposición no varía.",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* lógica de confirmación */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"Confirmar eliminación\",",
        "  content = \"Esta acción eliminará el elemento de forma permanente. ¿Desea continuar?\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "Tipo semántico y capa de cobertura",
      prose = "Cuando `type` es `danger`, el botón de confirmación toma el color de peligro; `closable` controla si la pulsación en la capa de cobertura / la tecla de retroceso dispara `onClose`.\nTras pulsar Confirmar / Cancelar, primero se dispara la devolución de llamada de negocio y luego `onClose`; el host, en consecuencia, pone `visible` a false.",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Si se muestra; cuando es false, no se renderiza nada", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Se dispara al pulsar el botón de confirmación", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Se dispara al pulsar el botón de cancelación", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Se dispara tras confirmar/cancelar o (con closable) al pulsar la capa de cobertura", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "Título", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "Contenido del cuerpo", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Tipo semántico, determina el tipo (color) del botón de confirmación", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "Texto del botón de confirmación; si está vacío, no se muestra el botón de confirmación", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Texto del botón de cancelación; si está vacío, no se muestra el botón de cancelación", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "Si la pulsación en la capa de cobertura / la tecla de retroceso dispara onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "Contenido del título (prevalece sobre title, slot title del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "Contenido del cuerpo (prevalece sobre content, slot default del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "Zona de botones inferior (prevalece sobre los botones predeterminados, slot footer del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier (aplicado a la tarjeta de contenido)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("Valor", "Tipo del botón de confirmación"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "El `LhButtonType` del mismo nombre, con el color semántico correspondiente")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "Ancho de la tarjeta de contenido (320dp)")),
    DocRow(cells = listOf("`dialog.borderRadius`", "Radio de esquina de la tarjeta (sigue `radius.base`)")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "Relleno de la tarjeta (sigue `spacing.lg`)")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "Tamaños de fuente del título / del cuerpo")),
    DocRow(cells = listOf("`dialog.gap`", "Espacio entre el título, el cuerpo y la zona de botones")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Alineación semántica de la jerarquía (el `Dialog` de Compose gestiona el nivel de jerarquía de ventana)")),
    DocRow(cells = listOf("`color.background.overlay`", "Color de la capa de cobertura")),
    DocRow(cells = listOf("`color.background.default`", "Color de fondo de la tarjeta")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Colores del título / del cuerpo")),
    DocRow(cells = listOf("`shadow.base`", "Sombra de la tarjeta (Compose no admite spread; se toma el radio de desenfoque como elevation)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "Confirmar dispara onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "Tras confirmar se dispara onClose", "Ídem")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Cancelar dispara onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "Con closable, pulsar la capa de cobertura dispara onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "Sin closable, la pulsación en la capa de cobertura se descarta", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "Cuando no es visible, no se renderiza", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "El título renderiza texto", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "El botón de confirmación del tipo danger toma el color de peligro", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # parte JVM",
    "./gradlew :components:dialog:connectedAndroidTest             # parte de interacción (requiere dispositivo)",
  ),
)
