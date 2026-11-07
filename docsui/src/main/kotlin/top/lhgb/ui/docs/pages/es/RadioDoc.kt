package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Botón de radio",
  lede = "Botón de radio controlado: pulsar de nuevo un elemento ya seleccionado no lo desmarca (semántica de selección única).",
  group = DocGroup.FORM,
  contract = "Fuente del contrato: `lh-ui-core/specs/radio.json` · Casos dorados: `cases/radio.json` (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Estados y grupo de selección",
      prose = "El componente por sí mismo no incluye lógica de grupo; la exclusión mutua la mantiene el host (se usa `value` para identificar el elemento dentro del grupo).",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"Opción uno\" to \"a\", \"Opción dos\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "Tamaños",
      prose = "Tres tamaños de anillo se mapean a los tokens `radio.size.*`; el diámetro del punto interior = tamaño × `radio.dotScale`.",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Si está seleccionado (obligatorio)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Se dispara al pasar de no seleccionado a seleccionado, con `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Si está deshabilitado", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Etiqueta de texto", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "Valor del elemento (identificador dentro del grupo; el componente por sí mismo no incluye lógica de grupo)", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Tamaño del anillo", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Contenido de la etiqueta (prevalece sobre label, slot default del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("Valor", "Token de tamaño", "Diámetro del punto interior"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small` (16dp)", "16dp × `radio.dotScale` (0.5)")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium` (20dp)", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large` (24dp)", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "Tres tamaños de anillo")),
    DocRow(cells = listOf("`radio.ringWidth`", "Ancho del borde del anillo exterior")),
    DocRow(cells = listOf("`radio.dotScale`", "Proporción del punto interior respecto al diámetro del anillo exterior (fracción)")),
    DocRow(cells = listOf("`radio.gap`", "Espacio entre el anillo y la etiqueta")),
    DocRow(cells = listOf("`color.primary.default`", "Color del borde y del punto del estado seleccionado")),
    DocRow(cells = listOf("`color.border.base`", "Color del borde del estado no seleccionado")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "Colores del borde / el punto / el texto del estado deshabilitado")),
    DocRow(cells = listOf("`color.text.primary`", "Color de la etiqueta")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Tamaño de fuente de la etiqueta")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "Una pulsación en un elemento no seleccionado dispara onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "Una pulsación en un elemento seleccionado no dispara", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "El estado deshabilitado no dispara", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "El estado seleccionado consume los tokens del color principal", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "El estado no seleccionado consume el token del borde", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "El tamaño pequeño consume el token de tamaño small", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # parte JVM",
    "./gradlew :components:radio:connectedAndroidTest             # parte de interacción (requiere dispositivo)",
  ),
)
