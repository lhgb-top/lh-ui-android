package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Cargando",
  lede = "Indicador de carga con animación de rotación anular; la duración toma `motion.duration.base` (milisegundos).",
  group = DocGroup.BASIC,
  contract = "Fuente del contrato: `lh-ui-core/specs/loading.json` · Casos dorados: `cases/loading.json` (5 casos, componente puramente visual, traducidos a pruebas unitarias JVM)",
  sections = listOf(
    DocSection(
      title = "Tamaño y tono",
      prose = "Tres tamaños se mapean a los tokens `loading.size.*`; el ancho del trazo toma `loading.strokeWidth` y el tono toma el color semántico.",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "Diámetro del indicador", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Color del anillo", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("Valor", "Token de tamaño"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`loading.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("Valor", "Token de color"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.regular`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "Tres diámetros")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Ancho del trazo del anillo")),
    DocRow(cells = listOf("`color.text.regular`", "Tono default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Tonos semánticos")),
    DocRow(cells = listOf("`motion.duration.base`", "Duración de una vuelta de rotación (del dispositivo: entero en milisegundos)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "El tamaño mediano toma el token de tamaño medium", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "El tamaño grande toma el token de tamaño large", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "El tono primary toma el token del color principal", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "El tono success toma el token del color de éxito", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "Se renderiza el indicador anular (el ancho del trazo consume el token)", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # parte JVM (componente puramente visual, sin casos de interacción)",
  ),
)
