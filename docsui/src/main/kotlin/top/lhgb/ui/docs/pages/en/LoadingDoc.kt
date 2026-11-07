package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Loading",
  lede = "A loading indicator with a spinning ring animation. The duration comes from `motion.duration.base` (in milliseconds).",
  group = DocGroup.BASIC,
  contract = "Spec source: `lh-ui-core/specs/loading.json` · Golden cases: `cases/loading.json` (5 cases; a purely presentational component, translated into JVM unit tests)",
  sections = listOf(
    DocSection(
      title = "Size and tone",
      prose = "Three sizes map to the `loading.size.*` tokens, the stroke width comes from `loading.strokeWidth`, and tones take semantic colors.",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "Indicator diameter", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Ring color", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("Value", "Size token"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`loading.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("Value", "Color token"),
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
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "Three diameters")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Ring stroke width")),
    DocRow(cells = listOf("`color.text.regular`", "default tone")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Semantic tones")),
    DocRow(cells = listOf("`motion.duration.base`", "Duration of one full rotation (an integer number of milliseconds on the client)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "Medium takes the medium size token", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "Large takes the large size token", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "The primary tone takes the primary token", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "The success tone takes the success token", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "The ring indicator renders (the stroke width consumes a token)", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # JVM part (purely presentational, no interaction cases)",
  ),
)
