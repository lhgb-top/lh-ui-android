package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Chargement",
  lede = "Indicateur de chargement, animation de rotation en anneau, durée issue de `motion.duration.base` (millisecondes).",
  group = DocGroup.BASIC,
  contract = "Source du contrat : `lh-ui-core/specs/loading.json` · Cas dorés : `cases/loading.json` (5 cas, composant purement visuel, traduits en tests unitaires JVM)",
  sections = listOf(
    DocSection(
      title = "Dimensions et tons",
      prose = "Trois dimensions mappées sur les jetons `loading.size.*`, largeur de trait issue de `loading.strokeWidth`, ton pris dans les couleurs sémantiques.",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "Diamètre de l'indicateur", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Couleur de l'anneau", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("Valeur", "Jeton de dimension"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`loading.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("Valeur", "Jeton de couleur"),
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
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "Trois diamètres")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Largeur du trait de l'anneau")),
    DocRow(cells = listOf("`color.text.regular`", "Ton default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Tons sémantiques")),
    DocRow(cells = listOf("`motion.duration.base`", "Durée d'un tour complet (entier en millisecondes côté appareil)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "La taille moyenne prend le jeton de dimension medium", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "La grande taille prend le jeton de dimension large", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "Le ton primary prend le jeton de couleur principale", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "Le ton success prend le jeton de couleur de succès", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "L'indicateur en anneau est rendu (la largeur de trait consomme le jeton)", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # partie JVM (purement visuel, aucun cas d'interaction)",
  ),
)
