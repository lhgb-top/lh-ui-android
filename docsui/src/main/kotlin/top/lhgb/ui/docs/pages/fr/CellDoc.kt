package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Cellule",
  lede = "Cellule de liste : disposition en trois sections icône / titre + description / valeur de droite + flèche, souvent combinée à une liste.",
  group = DocGroup.BASIC,
  contract = "Source du contrat : `lh-ui-core/specs/cell.json` · Cas dorés : `cases/cell.json` (6 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Forme de base",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"Titre avec description\",",
        "  label = \"Texte de description\",",
        "  value = \"Valeur\",",
        "  isLink = true,",
        "  lhOnClick = { /* naviguer */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Obligatoire et lien",
      prose = "`required` dessine une étoile rouge devant le titre (`color.danger.default`) ; `isLink` dessine une flèche vers la droite à la fin.",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Titre (texte principal)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "Valeur de droite (texte secondaire, aligné à droite)", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "Texte d'explication sous le titre", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Dessiner ou non la ligne de séparation inférieure (`color.border.lighter`)", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "Cliquable ou non ; si true, affiche la flèche indicatrice à droite et répond au clic", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "Afficher ou non l'étoile rouge obligatoire devant le titre (`color.danger.default`)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Nom de l'icône à gauche (système d'icônes local) ; rien n'est dessiné si vide", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "Callback de clic ; non déclenché si `clickable` vaut false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "Contenu du titre (remplace title, slot default du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "Contenu de la valeur de droite (remplace value, slot value du spec)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "Marge interne verticale (référence `spacing.md`)")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "Marge interne horizontale (`spacing.md`)")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "Tailles de police du titre / de la description / de la valeur")),
    DocRow(cells = listOf("`cell.gap`", "Espacement entre l'icône et le titre (`spacing.xs`)")),
    DocRow(cells = listOf("`icon.size.medium`", "Dimension de l'icône de gauche et de la flèche indicatrice")),
    DocRow(cells = listOf("`cell.borderWidth`", "Largeur de la ligne de séparation inférieure")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Hauteur de ligne du texte")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "Couleurs du titre / de la description / de la valeur")),
    DocRow(cells = listOf("`color.danger.default`", "Étoile rouge obligatoire")),
    DocRow(cells = listOf("`color.border.lighter`", "Ligne de séparation inférieure")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "Le titre et la valeur de droite rendent du texte", "`LhCellTest.title_and_value_render` (Compose UI Test)")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label prend le jeton de couleur de texte secondaire", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "Le marqueur obligatoire prend le jeton de couleur danger", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "La ligne de séparation consomme le jeton de bordure", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "Le clic déclenche onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "L'état non cliquable ne déclenche pas", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # partie JVM",
    "./gradlew :components:cell:connectedAndroidTest              # partie d'interaction (nécessite un appareil)",
  ),
)
