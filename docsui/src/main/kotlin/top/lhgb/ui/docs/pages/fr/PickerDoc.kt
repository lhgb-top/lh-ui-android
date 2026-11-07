package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Sélecteur",
  lede = "Sélecteur à roue monoligne en bas d'écran : après l'arrêt du défilement, l'élément le plus proche de l'indicateur central est celui sélectionné.",
  group = DocGroup.OVERLAY,
  contract = "Source du contrat : `lh-ui-core/specs/picker.json` · Cas dorés : `cases/picker.json` (8 cas : clamp et mappage des jetons traduits en tests unitaires JVM, défilement et interactions des boutons en Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Utilisation",
      prose = "Apparition en bas : zone de masque au-dessus, panneau en dessous (coins supérieurs arrondis). La barre d'outils se compose en trois sections « Annuler / Titre / Confirmer ».",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"Option un\", \"Option deux\", \"Option trois\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"Veuillez choisir\",",
        ")",
      ),
    ),
    DocSection(
      title = "Mécanisme de sélection",
      prose = "La roue est une liste défilable, chaque élément fait `picker.itemHeight` de haut ; l'indicateur central est constitué de deux lignes horizontales, espacées de itemHeight.\nAprès l'arrêt du défilement, l'index sélectionné est obtenu par arrondi de la distance parcourue ; tout changement déclenche `onValueChange`.\nLorsque `value` est hors bornes, `LhPickerDefaults.clamp` le ramène dans la plage des candidats.",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Affiché ou non ; rien n'est rendu si false", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "Tableau de textes des candidats (colonne unique)", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Déclenché à l'arrêt de la roue lorsque l'élément sélectionné change, avec le nouvel index", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Déclenché au clic sur le bouton de confirmation, avec l'index sélectionné courant", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Déclenché au clic sur le bouton d'annulation", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Déclenché après confirmation/annulation ; l'hôte passe alors visible à false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "Index de l'élément sélectionné (base 0) ; ramené dans les bornes sinon", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "Titre au centre de la barre d'outils", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "Texte du bouton de confirmation", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Texte du bouton d'annulation", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "Zone de contenu de la roue (remplace le rendu par défaut des candidats, slot default du spec)", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "Barre d'outils (remplace confirmer/titre/annuler par défaut, slot toolbar du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "État de la liste de la roue (fourni par défaut par `rememberLhPickerListState(value, options)` ; injectable de l'extérieur pour les tests)", "`LazyListState`", "voir ci-contre")),
    DocRow(cells = listOf("`modifier`", "Modifier (appliqué au panneau)", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "Hauteur du panneau (260dp)")),
    DocRow(cells = listOf("`picker.itemHeight`", "Hauteur d'un élément (40dp, également espacement de l'indicateur central)")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "Hauteur de la barre d'outils (44dp)")),
    DocRow(cells = listOf("`picker.itemFontSize`", "Taille de police des candidats")),
    DocRow(cells = listOf("`picker.borderRadius`", "Arrondi supérieur du panneau")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "Marge interne horizontale du panneau (suit `spacing.md`)")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Alignement sémantique des niveaux (le `Popup` de Compose porte le niveau de fenêtre)")),
    DocRow(cells = listOf("`color.background.overlay`", "Couleur du masque")),
    DocRow(cells = listOf("`color.background.default`", "Couleur de fond du panneau")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Couleurs du titre / des candidats")),
    DocRow(cells = listOf("`color.border.lighter`", "Couleur des lignes de l'indicateur central")),
    DocRow(cells = listOf("`color.primary.default`", "Couleur du bouton de confirmation")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "Confirmer porte l'index courant + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "Confirmer déclenche ensuite onClose", "idem")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Annuler déclenche onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "Le défilement change la sélection et déclenche onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "Une valeur négative est ramenée à 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "Une valeur hors bornes est ramenée au dernier élément", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "Le titre rend du texte", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "Rien n'est rendu si invisible", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # partie JVM",
    "./gradlew :components:picker:connectedAndroidTest               # partie d'interaction (nécessite un appareil)",
  ),
)
