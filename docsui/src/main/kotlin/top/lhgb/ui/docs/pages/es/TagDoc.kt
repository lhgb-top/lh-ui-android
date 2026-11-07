package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTagPlainPreview
import top.lhgb.ui.docs.previews.DocTagSizeAndMarkPreview
import top.lhgb.ui.docs.previews.DocTagTypesPreview

val tagDoc = DocEntry(
  id = "tag",
  title = "Etiqueta",
  lede = "Etiqueta de color semántico, admite plano (plain), marca completamente redondeada y botón de cierre.",
  group = DocGroup.BASIC,
  contract = "Fuente del contrato: `lh-ui-core/specs/tag.json` · Casos dorados: `cases/tag.json` (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Tipos",
      prose = "Seis tipos semánticos determinan el color de fondo y del texto.",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"Éxito\") }",
      ),
    ),
    DocSection(
      title = "Plano (plain)",
      prose = "`plain` usa fondo transparente con borde y texto de color semántico.",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "Tamaño y marca",
      prose = "Tres tamaños se mapean a los tokens `tag.height` / `tag.paddingHorizontal` / `tag.fontSize`; `mark` toma un radio completo equivalente a la mitad de la altura.",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "Texto de la etiqueta (forma abreviada, alternativa a `content`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Tipo semántico", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Tamaño", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "Si es plano (fondo transparente + borde semántico)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "Si el radio es completo (mitad de la altura)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "Si se muestra el botón de cierre", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "Devolución de llamada al cerrar; no se dispara cuando `closable` es false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "Contenido de la etiqueta (prevalece sobre text, slot default del spec)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("Valor", "Fondo (no plain)", "Borde/texto (plain)"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + borde `color.border.base`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("Valor", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small` (20dp)", "`tag.paddingHorizontal.small` (8dp)", "`tag.fontSize.small` (12sp)")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium` (24dp)", "`tag.paddingHorizontal.medium` (10dp)", "`tag.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`tag.height.large` (28dp)", "`tag.paddingHorizontal.large` (12dp)", "`tag.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "Tres alturas")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "Tres rellenos horizontales")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "Tres tamaños de fuente")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "Radio de esquina y ancho del borde")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Fondos / bordes / textos semánticos")),
    DocRow(cells = listOf("`color.onPrimary`", "Color del texto en el modo no plain")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Tres colores del estado deshabilitado")),
    DocRow(cells = listOf("`spacing.xs`", "Espacio entre el botón de cierre y el texto")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "El tipo predeterminado toma fondo neutro + color de texto principal + borde base", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "El tipo primary consume los tokens del color principal", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain consume los tokens de fondo semántico claro", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "El tamaño pequeño consume los tokens small (aserción inversa: large no se aplica)", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round toma un radio de la mitad de la altura", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "Pulsar el cierre dispara onClose", "`LhTagTest.closable_tap_emits_onClose` (Compose UI Test)")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # parte JVM",
    "./gradlew :components:tag:connectedAndroidTest             # parte de interacción (requiere dispositivo)",
  ),
)
