# Champ de saisie

Champ de saisie monoligne contrôlé : dimensions, désactivation, lecture seule, effacement, masquage du mot de passe et longueur maximale.

> Source du contrat : [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> Cas dorés : [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input) (8 cas, traduits en tests unitaires JVM + Compose UI Test)

## Forme de base

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="Veuillez saisir le contenu" /></div>
    <div class="sim-row"><input class="sim-input" value="Contenu saisi" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="Désactivé" disabled /></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "Veuillez saisir le contenu",
  onValueChange = { value = it },
)
```

## Dimensions et focus

Trois dimensions mappées sur les jetons `input.height` / `input.paddingHorizontal` / `input.fontSize` ; au focus, la bordure prend `color.focus` et s'épaissit jusqu'à `input.focusRingWidth`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions d'Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium (démo du focus)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `value` | Valeur contrôlée (obligatoire) | `String` | — |
| `onValueChange` | Callback de changement de valeur (obligatoire) | `(String) -> Unit` | — |
| `placeholder` | Texte de l'espace réservé | `String?` | `null` |
| `type` | Type de saisie (texte en clair / mot de passe) | `LhInputType` | `Text` |
| `lhSize` | Dimension | `LhInputSize` | `Medium` |
| `disabled` | Désactivé ou non | `Boolean` | `false` |
| `readonly` | En lecture seule ou non (non éditable sans style désactivé) | `Boolean` | `false` |
| `clearable` | Afficher ou non le bouton d'effacement | `Boolean` | `false` |
| `maxlength` | Longueur maximale (tronqué au-delà) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Callbacks de focus / de perte de focus | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | Slots préfixe / suffixe | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhInputSize

| Valeur | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small` (32dp) | `input.paddingHorizontal.small` (12dp) | `input.fontSize.small` (13sp) |
| `Medium` | `input.height.medium` (40dp) | `input.paddingHorizontal.medium` (16dp) | `input.fontSize.medium` (14sp) |
| `Large` | `input.height.large` (48dp) | `input.paddingHorizontal.large` (20dp) | `input.fontSize.large` (16sp) |

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `input.height.{small,medium,large}` | Trois hauteurs |
| `input.paddingHorizontal.{small,medium,large}` | Trois marges internes horizontales |
| `input.fontSize.{small,medium,large}` | Trois tailles de police |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | Arrondi / largeur de bordure / largeur de bordure au focus |
| `input.clearIconSize` | Dimension de l'icône d'effacement |
| `color.focus` | Couleur de la bordure au focus |
| `color.text.placeholder` | Couleur du texte de l'espace réservé |
| `color.border.base` | Couleur de bordure par défaut |
| `color.disabled.{bg,text,border}` | Les trois couleurs de l'état désactivé |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `input-emits-onvaluechange` | La saisie déclenche onValueChange | `LhInputTest.input_emits_onValueChange` (Compose UI Test) |
| `clearable-emits-empty-value` | Le bouton d'effacement émet une valeur vide | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | L'état désactivé bloque la saisie | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | L'état en lecture seule bloque la saisie | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | La couleur de l'espace réservé prend le jeton d'espace réservé | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | Le focus consomme le jeton de focus (bordure neutre hors focus) | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | La petite taille consomme les jetons de dimension small | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | La saisie trop longue est tronquée | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # partie JVM
./gradlew :components:input:connectedAndroidTest              # partie d'interaction (nécessite un appareil)
```
