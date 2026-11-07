package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Barre de navigation",
  lede = "Barre de titre en haut de page, disposition en trois sections gauche / centre / droite, souvent utilisée pour le retour et l'affichage du titre.",
  group = DocGroup.OVERLAY,
  contract = "Source du contrat : `lh-ui-core/specs/navbar.json` · Cas dorés : `cases/navbar.json` (6 cas : mappage des jetons traduit en tests unitaires JVM, clics gauche/droite en Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Utilisation",
      prose = "Trois sections de largeur égale (chacune `weight(1f)`), le titre centré horizontalement sur la largeur disponible ; ligne de séparation inférieure dessinée par défaut.",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"Titre de la page\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"Retour\",",
        "  rightText = \"Plus\",",
        "  onLeftClick = { /* retour */ },",
        "  onRightClick = { /* plus */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Contenus gauche et droit",
      prose = "Texte et icône peuvent coexister (icône à gauche, texte à droite, espacement `spacing.xs`) ; un côté sans aucun contenu ne répond pas au clic\n(cas doré `no-left-content-no-onleftclick` : ce nœud ne possède même pas de sémantique cliquable).",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Titre central", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "Déclenché au clic sur la zone gauche", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "Déclenché au clic sur la zone droite", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "Texte du bouton gauche ; peut coexister avec leftIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "Nom de l'icône à gauche (système d'icônes local, par ex. `\"arrow-left\"`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "Texte du bouton droit ; peut coexister avec rightIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "Nom de l'icône à droite", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Dessiner ou non la ligne de séparation inférieure", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "Contenu du titre (remplace title, slot default du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "Contenu de gauche (remplace leftText/leftIcon, slot left du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "Contenu de droite (remplace rightText/rightIcon, slot right du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "Hauteur de la barre (44dp)")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "Marge interne horizontale gauche et droite (suit `spacing.md`)")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "Taille de police du titre et des textes latéraux")),
    DocRow(cells = listOf("`navbar.iconSize`", "Dimension des icônes latérales (suit `icon.size.medium`)")),
    DocRow(cells = listOf("`navbar.borderWidth`", "Largeur de la ligne de séparation inférieure")),
    DocRow(cells = listOf("`zIndex.sticky`", "Alignement sémantique du niveau sticky")),
    DocRow(cells = listOf("`color.background.default`", "Couleur de fond")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Couleurs du titre / des textes latéraux")),
    DocRow(cells = listOf("`color.border.lighter`", "Couleur de la ligne de séparation")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "Le clic à gauche déclenche onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test)")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "Le clic à droite déclenche onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "Sans contenu à gauche, aucun déclenchement", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "Le titre rend du texte", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "La ligne de séparation consomme le jeton de bordure", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "La hauteur consomme le jeton navbar", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # partie JVM",
    "./gradlew :components:navbar:connectedAndroidTest                # partie d'interaction (nécessite un appareil)",
  ),
)
