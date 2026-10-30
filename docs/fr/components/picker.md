# Sélecteur

Sélecteur à roue monoligne en bas d'écran : après l'arrêt du défilement, l'élément le plus proche de l'indicateur central est celui sélectionné.

> Source du contrat : [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> Cas dorés : [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker) (8 cas : clamp et mappage des jetons traduits en tests unitaires JVM, défilement et interactions des boutons en Compose UI Test)

## Utilisation

Apparition en bas : zone de masque au-dessus, panneau en dessous (coins supérieurs arrondis). La barre d'outils se compose en trois sections « Annuler / Titre / Confirmer ».

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">Annuler</button>
        <div class="sim-picker__toolbar-title">Veuillez choisir</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">Confirmer</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">Option un</div>
        <div class="sim-picker__item sim-picker__item--selected">Option deux</div>
        <div class="sim-picker__item">Option trois</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("Option un", "Option deux", "Option trois")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "Veuillez choisir",
)
```

## Mécanisme de sélection

La roue est une liste défilable, chaque élément fait `picker.itemHeight` de haut ; l'indicateur central est constitué de deux lignes horizontales, espacées de itemHeight.
Après l'arrêt du défilement, l'index sélectionné est obtenu par arrondi de la distance parcourue ; tout changement déclenche `onValueChange`.
Lorsque `value` est hors bornes, `LhPickerDefaults.clamp` le ramène dans la plage des candidats.

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `visible` | Affiché ou non ; rien n'est rendu si false | `Boolean` | — |
| `options` | Tableau de textes des candidats (colonne unique) | `List<String>` | — |
| `onValueChange` | Déclenché à l'arrêt de la roue lorsque l'élément sélectionné change, avec le nouvel index | `(Int) -> Unit` | — |
| `onConfirm` | Déclenché au clic sur le bouton de confirmation, avec l'index sélectionné courant | `(Int) -> Unit` | — |
| `onCancel` | Déclenché au clic sur le bouton d'annulation | `() -> Unit` | — |
| `onClose` | Déclenché après confirmation/annulation ; l'hôte passe alors visible à false | `() -> Unit` | — |
| `value` | Index de l'élément sélectionné (base 0) ; ramené dans les bornes sinon | `Int` | `0` |
| `title` | Titre au centre de la barre d'outils | `String?` | `null` |
| `confirmText` | Texte du bouton de confirmation | `String` | `"确认"` |
| `cancelText` | Texte du bouton d'annulation | `String` | `"取消"` |
| `content` | Zone de contenu de la roue (remplace le rendu par défaut des candidats, slot default du spec) | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | Barre d'outils (remplace confirmer/titre/annuler par défaut, slot toolbar du spec) | `@Composable (() -> Unit)?` | `null` |
| `listState` | État de la liste de la roue (fourni par défaut par `rememberLhPickerListState(value, options)` ; injectable de l'extérieur pour les tests) | `LazyListState` | voir ci-contre |
| `modifier` | Modifier (appliqué au panneau) | `Modifier` | `Modifier` |

Crochet de test : `rememberLhPickerListState(value, options)` renvoie un `LazyListState` positionné initialement selon `value` ;
les tests instrumentés peuvent piloter le défilement de manière déterministe via `listState.dispatchRawDelta(...)`.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `picker.height` | Hauteur du panneau (260dp) |
| `picker.itemHeight` | Hauteur d'un élément (40dp, également espacement de l'indicateur central) |
| `picker.toolbarHeight` | Hauteur de la barre d'outils (44dp) |
| `picker.itemFontSize` | Taille de police des candidats |
| `picker.borderRadius` | Arrondi supérieur du panneau |
| `picker.paddingHorizontal` | Marge interne horizontale du panneau (suit `spacing.md`) |
| `zIndex.modal` / `zIndex.modalBackdrop` | Alignement sémantique des niveaux (le `Popup` de Compose porte le niveau de fenêtre) |
| `color.background.overlay` | Couleur du masque |
| `color.background.default` | Couleur de fond du panneau |
| `color.text.primary` / `color.text.regular` | Couleurs du titre / des candidats |
| `color.border.lighter` | Couleur des lignes de l'indicateur central |
| `color.primary.default` | Couleur du bouton de confirmation |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | Confirmer porte l'index courant + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | Confirmer déclenche ensuite onClose | idem |
| `cancel-emits-oncancel` | Annuler déclenche onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | Le défilement change la sélection et déclenche onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | Une valeur négative est ramenée à 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | Une valeur hors bornes est ramenée au dernier élément | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | Le titre rend du texte | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | Rien n'est rendu si invisible | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # partie JVM
./gradlew :components:picker:connectedAndroidTest               # partie d'interaction (nécessite un appareil)
```
