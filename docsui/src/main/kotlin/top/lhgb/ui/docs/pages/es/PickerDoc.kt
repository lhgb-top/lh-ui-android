package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Selector",
  lede = "Selector de rueda inferior de una sola columna: al detenerse el desplazamiento, el elemento seleccionado es el más cercano al indicador central.",
  group = DocGroup.OVERLAY,
  contract = "Fuente del contrato: `lh-ui-core/specs/picker.json` · Casos dorados: `cases/picker.json` (8 casos: el clamp y el mapeo de tokens traducidos a pruebas unitarias JVM; la interacción de desplazamiento y de botones traducida a Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Uso",
      prose = "Aparece desde la parte inferior: la capa de cobertura arriba y el panel abajo (esquinas redondeadas en la parte superior). La barra de herramientas tiene tres secciones: «Cancelar / título / Confirmar».",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"Opción uno\", \"Opción dos\", \"Opción tres\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"Seleccionar\",",
        ")",
      ),
    ),
    DocSection(
      title = "Mecanismo de selección",
      prose = "La rueda es una lista desplazable; la altura de cada elemento es `picker.itemHeight`; el indicador central son dos líneas horizontales separadas por una distancia igual a itemHeight.\nAl detenerse el desplazamiento, el índice seleccionado se obtiene redondeando la distancia recorrida; cuando cambia, se dispara `onValueChange`.\nSi `value` está fuera de rango, `LhPickerDefaults.clamp` lo limita (clamp) al rango de candidatos.",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Si se muestra; cuando es false, no se renderiza nada", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "Array de textos de los candidatos (una columna)", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Se dispara cuando la rueda se detiene y el elemento seleccionado cambia, con el nuevo índice", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Se dispara al pulsar el botón de confirmación, con el índice seleccionado actual", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Se dispara al pulsar el botón de cancelación", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Se dispara tras confirmar/cancelar; el host, en consecuencia, pone visible a false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "Índice del elemento seleccionado (base 0); fuera de rango se limita con clamp", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "Título central de la barra de herramientas", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "Texto del botón de confirmación", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Texto del botón de cancelación", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "Zona de contenido de la rueda (prevalece sobre el renderizado predeterminado de candidatos, slot default del spec)", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "Barra de herramientas (prevalece sobre la confirmación/título/cancelación predeterminados, slot toolbar del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "Estado de la lista de la rueda (por defecto lo proporciona `rememberLhPickerListState(value, options)`; en pruebas se puede inyectar desde fuera)", "`LazyListState`", "Véase a la izquierda")),
    DocRow(cells = listOf("`modifier`", "Modifier (aplicado al panel)", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "Altura del panel (260dp)")),
    DocRow(cells = listOf("`picker.itemHeight`", "Altura de cada elemento (40dp; también es la separación del indicador central)")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "Altura de la barra de herramientas (44dp)")),
    DocRow(cells = listOf("`picker.itemFontSize`", "Tamaño de fuente de los candidatos")),
    DocRow(cells = listOf("`picker.borderRadius`", "Radio de esquina superior del panel")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "Relleno horizontal del panel (sigue `spacing.md`)")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Alineación semántica de la jerarquía (el `Popup` de Compose aporta el nivel de jerarquía de ventana)")),
    DocRow(cells = listOf("`color.background.overlay`", "Color de la capa de cobertura")),
    DocRow(cells = listOf("`color.background.default`", "Color de fondo del panel")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Colores del título / los candidatos")),
    DocRow(cells = listOf("`color.border.lighter`", "Color de las líneas del indicador central")),
    DocRow(cells = listOf("`color.primary.default`", "Color del botón de confirmación")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "Confirmar envía el índice actual + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "Tras confirmar se dispara onClose", "Ídem")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Cancelar dispara onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "El desplazamiento cambia la selección y dispara onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "Un value negativo se limita a 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "Un value fuera de rango se limita al último elemento", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "El título renderiza texto", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "Cuando no es visible, no se renderiza", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # parte JVM",
    "./gradlew :components:picker:connectedAndroidTest               # parte de interacción (requiere dispositivo)",
  ),
)
