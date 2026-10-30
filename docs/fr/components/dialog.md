# Boîte de dialogue

Surcouche modale pour les confirmations importantes ou les contenus nécessitant l'attention de l'utilisateur. Le niveau de fenêtre est pris en charge par le `Dialog` de Compose.

> Source du contrat : [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> Cas dorés : [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog) (8 cas, traduits en tests unitaires JVM + Compose UI Test)

## Types

`type` détermine la couleur du bouton de confirmation (mappage 1:1 sur `LhButtonType`), le reste de la disposition est inchangé.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Titre</div>
      <div class="sim-dialog__content">Ceci est un contenu de corps, destiné à expliquer l'impact de l'opération en cours.</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Annuler</button>
        <button class="sim-btn sim-btn--primary">Confirmer</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* logique de confirmation */ },
  onCancel = {},
  onClose = { visible = false },
  title = "Confirmer la suppression",
  content = "La suppression est irréversible. Confirmer ?",
  type = LhDialogType.danger,
)
```

## Types sémantiques et masque

Lorsque `type` vaut `danger`, le bouton de confirmation prend la couleur de danger ; `closable` contrôle si un clic sur le masque / la touche retour déclenche `onClose`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Confirmer la suppression</div>
      <div class="sim-dialog__content">La suppression est irréversible. Confirmer ?</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Annuler</button>
        <button class="sim-btn sim-btn--danger">Confirmer</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

Après un clic sur Confirmer / Annuler, **le callback métier est déclenché d'abord, puis `onClose`** ; l'hôte passe alors `visible` à false.

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `visible` | Affichée ou non ; rien n'est rendu si false | `Boolean` | — |
| `onConfirm` | Déclenché au clic sur le bouton de confirmation | `() -> Unit` | — |
| `onCancel` | Déclenché au clic sur le bouton d'annulation | `() -> Unit` | — |
| `onClose` | Déclenché après confirmation/annulation ou (si closable) au clic sur le masque | `() -> Unit` | — |
| `title` | Titre | `String?` | `null` |
| `content` | Contenu du corps | `String?` | `null` |
| `type` | Type sémantique, détermine le type (couleur) du bouton de confirmation | `LhDialogType` | `default` |
| `confirmText` | Texte du bouton de confirmation ; le bouton est masqué si vide | `String` | `"确认"` |
| `cancelText` | Texte du bouton d'annulation ; le bouton est masqué si vide | `String` | `"取消"` |
| `closable` | Un clic sur le masque / la touche retour déclenche-t-il onClose | `Boolean` | `false` |
| `titleContent` | Contenu du titre (remplace title, slot title du spec) | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | Contenu du corps (remplace content, slot default du spec) | `@Composable (() -> Unit)?` | `null` |
| `footer` | Zone des boutons en bas (remplace les boutons par défaut, slot footer du spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier (appliqué à la carte de contenu) | `Modifier` | `Modifier` |

### LhDialogType

| Valeur | Type du bouton de confirmation |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | `LhButtonType` homonyme, couleurs sémantiques correspondantes |

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `dialog.width` | Largeur de la carte de contenu (320dp) |
| `dialog.borderRadius` | Arrondi de la carte (suit `radius.base`) |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | Marges internes de la carte (suivent `spacing.lg`) |
| `dialog.titleFontSize` / `dialog.contentFontSize` | Tailles de police du titre / du corps |
| `dialog.gap` | Espacement entre le titre, le corps et la zone des boutons |
| `zIndex.modal` / `zIndex.modalBackdrop` | Alignement sémantique des niveaux (le `Dialog` de Compose gère le niveau de fenêtre) |
| `color.background.overlay` | Couleur du masque |
| `color.background.default` | Couleur de fond de la carte |
| `color.text.primary` / `color.text.regular` | Couleurs du titre / du corps |
| `shadow.base` | Ombre de la carte (Compose ne gère pas le spread : le rayon de flou sert d'élévation) |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `confirm-emits-onconfirm` | Confirmer déclenche onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | Confirmer déclenche ensuite onClose | idem |
| `cancel-emits-oncancel` | Annuler déclenche onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | Si closable, un clic sur le masque déclenche onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | Si non closable, le clic sur le masque est absorbé | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | Rien n'est rendu si invisible | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | Le titre rend du texte | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | Le bouton de confirmation de type danger prend la couleur de danger | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # partie JVM
./gradlew :components:dialog:connectedAndroidTest             # partie d'interaction (nécessite un appareil)
```
