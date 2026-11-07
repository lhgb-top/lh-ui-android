package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextLevelsPreview
import top.lhgb.ui.docs.previews.DocTextTonesPreview
import top.lhgb.ui.docs.previews.DocTextWeightPreview

val textDoc = DocEntry(
  id = "text",
  title = "Texte (Text)",
  lede = "Primitive d'affichage de texte : consomme les tokens typographiques globaux, avec niveaux de taille, graisse, ton et alignement.",
  group = DocGroup.BASIC,
  contract = "Contrat : `lh-ui-core/specs/text.json` · Cas dorés : `cases/text.json` (5, traduits en tests JVM)",
  sections = listOf(
    DocSection(
      title = "Niveaux de taille",
      prose = "`level` est mappé au token `typography.fontSize.<level>` ; par défaut `base`.",
      preview = { DocTextLevelsPreview() },
      code = listOf(
        "LhText(content = \"Title text\", level = LhTextLevel.large)",
      ),
    ),
    DocSection(
      title = "Graisse",
      prose = "`weight` est mappé au token `typography.fontWeight.<weight>` (primary 400 / bold 600).",
      preview = { DocTextWeightPreview() },
    ),
    DocSection(
      title = "Ton",
      prose = "`tone` est mappé au token `color.text.<tone>` ; `selected` prend `color.selected`.",
      preview = { DocTextTonesPreview() },
    ),
    DocSection(
      title = "Alignement et hauteur de ligne",
      prose = "`lhAlign` aligne dans la boîte de ligne ; la hauteur de ligne prend toujours `typography.lineHeight.primary` (taille × 1,8).",
      code = listOf(
        "LhText(content = \"Centered\", lhAlign = LhTextAlign.center)",
      ),
    ),
    DocSection(
      title = "Props",
      prose = "",
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`typography.fontSize.<level>`", "six size levels")),
    DocRow(cells = listOf("`typography.fontWeight.{primary,bold}`", "two weights")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "line height (font size × 1.8)")),
    DocRow(cells = listOf("`color.text.<tone>` / `color.selected`", "text tones")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-level-uses-base-tokens`", "default level consumes the base typography tokens", "`LhTextDefaultsTest.default_level_uses_base_tokens`")),
    DocRow(cells = listOf("`large-level-uses-large-token`", "large level consumes the large size token", "`LhTextDefaultsTest.large_level_uses_large_token`")),
    DocRow(cells = listOf("`bold-weight-uses-bold-token`", "bold consumes the bold weight token", "`LhTextDefaultsTest.bold_weight_uses_bold_token`")),
    DocRow(cells = listOf("`secondary-tone-uses-text-secondary`", "secondary tone takes the secondary text color token", "`LhTextDefaultsTest.secondary_tone_uses_text_secondary`")),
    DocRow(cells = listOf("`render-emits-single-text-command`", "render emits a single TEXT command", "`LhTextDefaultsTest.all_levels_map_to_their_tokens`")),
  ),
  commands = listOf(

    "./gradlew :components:text:test                             # JVM",
  ),
)
