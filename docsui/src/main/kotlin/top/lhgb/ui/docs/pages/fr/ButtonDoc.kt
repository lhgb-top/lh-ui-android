package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "Bouton",
  lede = "Bouton d'action de base. Déclenche une action immédiate ; gère le type, la dimension, le chargement, la désactivation, l'icône et le motif décoratif style chinois.",
  group = DocGroup.BASIC,
  contract = "Source du contrat : `lh-ui-core/specs/button.json` · Cas dorés : `cases/button.json` (8 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Types",
      prose = "Six types déterminent la couleur de fond et la couleur du texte.",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"Bouton principal\") }",
      ),
    ),
    DocSection(
      title = "Dimensions",
      prose = "Trois dimensions mappées sur les jetons `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "États",
      prose = "Désactivé et chargement ne déclenchent pas `lhOnClick` ; lorsque les deux se cumulent, le désactivé prime (cas doré `disabled-precedes-loading`).",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "Icône et motif décoratif",
      prose = "Le nom de l'icône est résolu par le système d'icônes local (`LhButtonIcons` ; actuellement search / refresh / check / close à l'appui, les noms non enregistrés ne sont pas dessinés).\nLe motif décoratif consomme le jeton `color.decoration` : superposé au-dessus de la couleur de fond et en dessous du texte, sans affecter le contraste.",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "Thèmes clair et sombre",
      prose = "Basculez le thème du site en haut à droite : les couleurs des boutons dans le cadre du simulateur changent ; sur appareil réel, via `LhTheme(isDark = true)`.",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "Callback de clic ; non déclenché si `disabled` ou `loading` vaut true", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Type de bouton, détermine la couleur de fond et du texte", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Dimension du bouton, mappée sur les jetons de dimension", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Désactivé ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "En chargement ou non (affiche un indicateur, ne déclenche pas lhOnClick)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Nom de l'icône (résolu par le système d'icônes local)", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "Dessiner ou non le motif décoratif style chinois", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "Contenu textuel du bouton (slot default du spec)", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("Valeur", "Fond", "Couleur du texte"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("Valeur", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small` (32dp)", "`button.paddingHorizontal.small` (16dp)", "`button.fontSize.small` (13sp)", "`button.iconSize.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium` (40dp)", "`button.paddingHorizontal.medium` (20dp)", "`button.fontSize.medium` (14sp)", "`button.iconSize.medium` (18dp)")),
        DocRow(cells = listOf("`Large`", "`button.height.large` (48dp)", "`button.paddingHorizontal.large` (24dp)", "`button.fontSize.large` (16sp)", "`button.iconSize.large` (20dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "Trois hauteurs")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "Trois marges internes horizontales")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "Trois tailles de police")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "Trois tailles d'icône")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "Arrondi (suit `radius.base`) et largeur de bordure")),
    DocRow(cells = listOf("`button.fontWeight`", "Graisse (suit `typography.fontWeight.primary`)")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Fond / texte du type principal")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "Fonds des types sémantiques")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "Fond / texte / bordure du type par défaut")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Les trois couleurs de l'état désactivé")),
    DocRow(cells = listOf("`color.decoration`", "Couleur du motif décoratif")),
    DocRow(cells = listOf("`shadow.lighter`", "Élévation du type par défaut")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "Animation de retour au clic")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "Le clic déclenche onClick une seule fois", "`LhButtonTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-click`", "L'état désactivé ne déclenche pas", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "L'état de chargement ne déclenche pas", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "Le désactivé prime", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "Le type par défaut rend du texte", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "Le type principal consomme les jetons de la couleur principale", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "La petite taille consomme les jetons de dimension small", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "Le motif décoratif consomme la couleur de décoration", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "Un nom d'icône enregistré rend un vecteur ; un nom non enregistré n'est pas dessiné", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # partie JVM",
    "./gradlew :components:button:connectedAndroidTest        # partie d'interaction (nécessite un appareil)",
  ),
)
