package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "Icono",
  lede = "Componente base de iconos. Los iconos del resto de componentes (cell / input / navbar, etc.) se renderizan a través del registro local de este componente.",
  group = DocGroup.BASIC,
  contract = "Fuente del contrato: `lh-ui-core/specs/icon.json` · Casos dorados: `cases/icon.json` (5 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Tamaño y tono",
      prose = "Tres tamaños se mapean a los tokens `icon.size.*`; el tono `default` toma `color.text.primary` y los demás toman los colores semánticos.",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "Tonos semánticos",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "Registro de iconos",
      prose = "`LhIcons` es el registro de nombres de iconos locales → `ImageVector` de Material; los nombres no registrados devuelven `null` (no se dibuja, no lanza excepciones):",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "Nombre del icono (resuelto por el registro `LhIcons`)", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "Tamaño del icono", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Tono semántico", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "Descripción de accesibilidad; por defecto toma el nombre del icono; pasar `null` explícitamente lo declara como icono decorativo", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "Devolución de llamada al pulsar; cuando es null, no se puede pulsar", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("Valor", "Token de tamaño"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`icon.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("Valor", "Token de color"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "Tres tamaños")),
    DocRow(cells = listOf("`color.text.primary`", "Tono default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Tonos semánticos")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "Tonos regular / secondary")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "La pulsación dispara onClick", "`LhIconTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "El tono predeterminado toma el token del color de texto principal", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "El tono primary toma el token del color principal", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "El tamaño pequeño toma el token de tamaño small (aserción inversa: large no se aplica)", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "Un nombre registrado se resuelve como vector; uno no registrado no se dibuja", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # parte JVM",
    "./gradlew :components:icon:connectedAndroidTest            # parte de interacción (requiere dispositivo)",
  ),
)
