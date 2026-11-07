package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast (notification)",
  lede = "Surcouche en pilule couleur encre, qui gère elle-même sa visibilité et son minutage, pour un retour d'action léger. Elle ne bloque pas les opérations de l'utilisateur.",
  group = DocGroup.OVERLAY,
  contract = "Source du contrat : `lh-ui-core/specs/toast.json` · Cas dorés : `cases/toast.json` (6 cas : 4 sur la machine à états + 2 sur le rendu, tous traduits en tests locaux)",
  sections = listOf(
    DocSection(
      title = "Utilisation",
      prose = "Le toast est une machine à états autonome : l'état est porté par `LhToastState`, l'hôte appelle `show(text)` pour l'afficher et `hide()` pour le masquer ;\nà l'expiration du minuteur, il se masque automatiquement et déclenche `onClose`. Le composant s'auto-entraîne par `tick` à chaque image lorsqu'il est visible ; l'hôte peut aussi l'appeler lui-même pour prendre en charge le minutage.",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* facultatif : journaliser la fermeture */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"Enregistré\") }) { Text(\"Déclencher\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "Types sémantiques",
      prose = "Pour un type autre que `default`, un point de couleur sémantique de diamètre égal à la taille de police est dessiné devant le texte.",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "Positionnement",
      prose = "`lhPosition` prend `top` / `center` / `bottom`, avec une marge de `spacing.xl` aux deux extrémités ; la largeur est mesurée d'après le texte et plafonnée à 80 % de la largeur disponible.",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "Machine à états du toast (porte la visibilité, le texte et le minutage)", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "Type sémantique ; hors default, dessine un point de couleur sémantique", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "Position dans la zone disponible", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "Modifier (appliqué à la pilule)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("Membre", "Description"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "Afficher et réinitialiser le minuteur")),
        DocRow(cells = listOf("`hide()`", "Masquer immédiatement, sans déclencher onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "Avancer le minuteur ; à l'expiration, masquer, déclencher onClose et renvoyer true")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "État courant (observable)")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("Valeur", "Couleur du point"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "Aucun point")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "Couleur sémantique correspondante")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "Largeur maximale de la pilule (240dp ; on prend la plus petite entre 80 % de la largeur disponible et cette valeur)")),
    DocRow(cells = listOf("`toast.borderRadius`", "Arrondi de la pilule (suit `radius.base`)")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "Marges internes de la pilule")),
    DocRow(cells = listOf("`toast.fontSize`", "Taille de police du texte (également diamètre du point)")),
    DocRow(cells = listOf("`toast.gap`", "Espacement entre le point et le texte")),
    DocRow(cells = listOf("`zIndex.toast`", "Alignement sémantique des niveaux (le `Popup` de Compose gère le niveau de fenêtre)")),
    DocRow(cells = listOf("`color.background.overlay` (mappage ink de la couche de thème)", "Couleur de fond de la pilule")),
    DocRow(cells = listOf("`color.onPrimary`", "Couleur du texte")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Couleurs des points sémantiques")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "Après show, visible et rend le texte", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "À l'expiration, se masque et déclenche onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "Avant l'expiration, reste visible et ne déclenche pas onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide ne déclenche pas onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "Le type success consomme la couleur de succès", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "Rien n'est rendu si invisible", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # partie JVM",
    "./gradlew :components:toast:connectedAndroidTest                 # partie d'interaction (nécessite un appareil)",
  ),
)
