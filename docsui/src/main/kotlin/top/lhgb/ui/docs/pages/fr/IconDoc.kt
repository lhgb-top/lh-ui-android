package top.lhgb.ui.docs.pages.fr

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
  title = "Icône",
  lede = "Composant de base des icônes. Les icônes des autres composants (cell / input / navbar, etc.) sont toutes rendues via le registre local de ce composant.",
  group = DocGroup.BASIC,
  contract = "Source du contrat : `lh-ui-core/specs/icon.json` · Cas dorés : `cases/icon.json` (5 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Dimensions et tons",
      prose = "Trois dimensions mappées sur les jetons `icon.size.*` ; le ton `default` prend `color.text.primary`, les autres prennent les couleurs sémantiques.",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "Tons sémantiques",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "Correspondance des noms d'icône",
      prose = "`LhIcons` est le registre local nom d'icône → `ImageVector` Material ; un nom non enregistré renvoie `null` (rien n'est dessiné, aucune exception levée) :",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "Nom de l'icône (résolu par le registre `LhIcons`)", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "Dimension de l'icône", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Ton sémantique", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "Description d'accessibilité ; prend par défaut le nom de l'icône ; passer explicitement `null` déclare une icône décorative", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "Callback de clic ; non cliquable si null", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("Valeur", "Jeton de dimension"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`icon.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("Valeur", "Jeton de couleur"),
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
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "Trois dimensions")),
    DocRow(cells = listOf("`color.text.primary`", "Ton default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Tons sémantiques")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "Tons regular / secondary")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "Le clic déclenche onClick", "`LhIconTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "Le ton par défaut prend le jeton de couleur de texte principal", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "Le ton primary prend le jeton de couleur principale", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "La petite taille prend le jeton de dimension small (assertion inverse : large n'est pas appliqué)", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "Un nom d'icône enregistré est résolu en vecteur ; un nom non enregistré n'est pas dessiné", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # partie JVM",
    "./gradlew :components:icon:connectedAndroidTest            # partie d'interaction (nécessite un appareil)",
  ),
)
