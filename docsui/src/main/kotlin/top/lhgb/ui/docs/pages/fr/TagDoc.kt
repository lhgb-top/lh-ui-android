package top.lhgb.ui.docs.pages.fr

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
  title = "Étiquette",
  lede = "Étiquette à couleur sémantique : prend en charge le style plain, le marquage tout arrondi et le bouton de fermeture.",
  group = DocGroup.BASIC,
  contract = "Source du contrat : `lh-ui-core/specs/tag.json` · Cas dorés : `cases/tag.json` (6 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Types",
      prose = "Six types sémantiques déterminent la couleur de fond et la couleur du texte.",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"Succès\") }",
      ),
    ),
    DocSection(
      title = "Plain (contour)",
      prose = "`plain` applique un fond transparent avec bordure et texte aux couleurs sémantiques.",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "Dimensions et marquage",
      prose = "Trois dimensions mappées sur les jetons `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` ; `mark` applique un arrondi de la moitié de la hauteur.",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "Texte de l'étiquette (raccourci, au choix avec `content`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Type sémantique", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Dimension", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "Style plain ou non (fond transparent + bordure aux couleurs sémantiques)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "Arrondi complet ou non (moitié de la hauteur)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "Afficher ou non le bouton de fermeture", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "Callback de fermeture ; non déclenché si `closable` vaut false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "Contenu de l'étiquette (remplace text, slot default du spec)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("Valeur", "Fond (hors plain)", "Bordure/texte (plain)"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + bordure `color.border.base`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("Valeur", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small` (20dp)", "`tag.paddingHorizontal.small` (8dp)", "`tag.fontSize.small` (12sp)")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium` (24dp)", "`tag.paddingHorizontal.medium` (10dp)", "`tag.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`tag.height.large` (28dp)", "`tag.paddingHorizontal.large` (12dp)", "`tag.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "Trois hauteurs")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "Trois marges internes horizontales")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "Trois tailles de police")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "Arrondi et largeur de bordure")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Fond / bordure / texte sémantiques")),
    DocRow(cells = listOf("`color.onPrimary`", "Couleur du texte hors type par défaut")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Les trois couleurs de l'état désactivé")),
    DocRow(cells = listOf("`spacing.xs`", "Espacement entre le bouton de fermeture et le texte")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "Le type par défaut prend le fond neutre + le texte principal + la bordure de base", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "Le type principal consomme les jetons de la couleur principale", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "Le style plain consomme les jetons de fond clair sémantiques", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "La petite taille consomme les jetons small (assertion inverse : large n'est pas appliqué)", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "L'arrondi de round prend la moitié de la hauteur", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "Le clic sur la fermeture déclenche onClose", "`LhTagTest.closable_tap_emits_onClose` (Compose UI Test)")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # partie JVM",
    "./gradlew :components:tag:connectedAndroidTest             # partie d'interaction (nécessite un appareil)",
  ),
)
