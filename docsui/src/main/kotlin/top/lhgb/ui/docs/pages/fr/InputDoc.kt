package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Champ de saisie",
  lede = "Champ de saisie monoligne contrôlé : dimensions, désactivation, lecture seule, effacement, masquage du mot de passe et longueur maximale.",
  group = DocGroup.FORM,
  contract = "Source du contrat : `lh-ui-core/specs/input.json` · Cas dorés : `cases/input.json` (8 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Forme de base",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"Veuillez saisir le contenu\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Dimensions et focus",
      prose = "Trois dimensions mappées sur les jetons `input.height` / `input.paddingHorizontal` / `input.fontSize` ; au focus, la bordure prend `color.focus` et s'épaissit jusqu'à `input.focusRingWidth`.",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Valeur contrôlée (obligatoire)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Callback de changement de valeur (obligatoire)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Texte de l'espace réservé", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Type de saisie (texte en clair / mot de passe)", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "Dimension", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Désactivé ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "En lecture seule ou non (non éditable sans style désactivé)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "Afficher ou non le bouton d'effacement", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Longueur maximale (tronqué au-delà)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Callbacks de focus / de perte de focus", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "Slots préfixe / suffixe", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("Valeur", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small` (32dp)", "`input.paddingHorizontal.small` (12dp)", "`input.fontSize.small` (13sp)")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium` (40dp)", "`input.paddingHorizontal.medium` (16dp)", "`input.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`input.height.large` (48dp)", "`input.paddingHorizontal.large` (20dp)", "`input.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "Trois hauteurs")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "Trois marges internes horizontales")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "Trois tailles de police")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "Arrondi / largeur de bordure / largeur de bordure au focus")),
    DocRow(cells = listOf("`input.clearIconSize`", "Dimension de l'icône d'effacement")),
    DocRow(cells = listOf("`color.focus`", "Couleur de la bordure au focus")),
    DocRow(cells = listOf("`color.text.placeholder`", "Couleur du texte de l'espace réservé")),
    DocRow(cells = listOf("`color.border.base`", "Couleur de bordure par défaut")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Les trois couleurs de l'état désactivé")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "La saisie déclenche onValueChange", "`LhInputTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "Le bouton d'effacement émet une valeur vide", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "L'état désactivé bloque la saisie", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "L'état en lecture seule bloque la saisie", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "La couleur de l'espace réservé prend le jeton d'espace réservé", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "Le focus consomme le jeton de focus (bordure neutre hors focus)", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "La petite taille consomme les jetons de dimension small", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "La saisie trop longue est tronquée", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # partie JVM",
    "./gradlew :components:input:connectedAndroidTest              # partie d'interaction (nécessite un appareil)",
  ),
)
