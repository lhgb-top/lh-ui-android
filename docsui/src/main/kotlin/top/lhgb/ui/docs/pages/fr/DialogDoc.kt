package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Boîte de dialogue",
  lede = "Surcouche modale pour les confirmations importantes ou les contenus nécessitant l'attention de l'utilisateur. Le niveau de fenêtre est pris en charge par le `Dialog` de Compose.",
  group = DocGroup.OVERLAY,
  contract = "Source du contrat : `lh-ui-core/specs/dialog.json` · Cas dorés : `cases/dialog.json` (8 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Types",
      prose = "`type` détermine la couleur du bouton de confirmation (mappage 1:1 sur `LhButtonType`), le reste de la disposition est inchangé.",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* logique de confirmation */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"Confirmer la suppression\",",
        "  content = \"La suppression est irréversible. Confirmer ?\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "Types sémantiques et masque",
      prose = "Lorsque `type` vaut `danger`, le bouton de confirmation prend la couleur de danger ; `closable` contrôle si un clic sur le masque / la touche retour déclenche `onClose`.\nAprès un clic sur Confirmer / Annuler, le callback métier est déclenché d'abord, puis `onClose` ; l'hôte passe alors `visible` à false.",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Affichée ou non ; rien n'est rendu si false", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Déclenché au clic sur le bouton de confirmation", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Déclenché au clic sur le bouton d'annulation", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Déclenché après confirmation/annulation ou (si closable) au clic sur le masque", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "Titre", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "Contenu du corps", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Type sémantique, détermine le type (couleur) du bouton de confirmation", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "Texte du bouton de confirmation ; le bouton est masqué si vide", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Texte du bouton d'annulation ; le bouton est masqué si vide", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "Un clic sur le masque / la touche retour déclenche-t-il onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "Contenu du titre (remplace title, slot title du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "Contenu du corps (remplace content, slot default du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "Zone des boutons en bas (remplace les boutons par défaut, slot footer du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier (appliqué à la carte de contenu)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("Valeur", "Type du bouton de confirmation"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "`LhButtonType` homonyme, couleurs sémantiques correspondantes")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "Largeur de la carte de contenu (320dp)")),
    DocRow(cells = listOf("`dialog.borderRadius`", "Arrondi de la carte (suit `radius.base`)")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "Marges internes de la carte (suivent `spacing.lg`)")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "Tailles de police du titre / du corps")),
    DocRow(cells = listOf("`dialog.gap`", "Espacement entre le titre, le corps et la zone des boutons")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Alignement sémantique des niveaux (le `Dialog` de Compose gère le niveau de fenêtre)")),
    DocRow(cells = listOf("`color.background.overlay`", "Couleur du masque")),
    DocRow(cells = listOf("`color.background.default`", "Couleur de fond de la carte")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Couleurs du titre / du corps")),
    DocRow(cells = listOf("`shadow.base`", "Ombre de la carte (Compose ne gère pas le spread : le rayon de flou sert d'élévation)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "Confirmer déclenche onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "Confirmer déclenche ensuite onClose", "idem")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Annuler déclenche onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "Si closable, un clic sur le masque déclenche onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "Si non closable, le clic sur le masque est absorbé", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "Rien n'est rendu si invisible", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "Le titre rend du texte", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "Le bouton de confirmation de type danger prend la couleur de danger", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # partie JVM",
    "./gradlew :components:dialog:connectedAndroidTest             # partie d'interaction (nécessite un appareil)",
  ),
)
