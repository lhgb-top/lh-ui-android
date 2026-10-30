# Zone de texte

Zone de saisie multiligne contrôlée : nombre de lignes, comptage de caractères et longueur maximale.

> Source du contrat : [`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> Cas dorés : [`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea) (6 cas, traduits en tests unitaires JVM + Compose UI Test)

## Forme de base

Hauteur minimale = nombre de lignes × hauteur de ligne × taille de police ; au-delà, défilement vertical.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="Veuillez saisir le contenu multiligne" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">Texte de trois lignes
Deuxième ligne
Troisième ligne</textarea>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## Comptage de caractères

Lorsque `showCount` vaut true, un compteur est rendu en bas à droite (`textarea.countFontSize`, couleur `text.secondary`) ; `maxlength` tronque la saisie.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Comptage de Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">青花瓷</textarea>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel ; sur appareil réel, le compteur « 3 / 20 » s'affiche en bas à droite</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `value` | Valeur contrôlée (obligatoire) | `String` | — |
| `onValueChange` | Callback de changement de valeur (obligatoire) | `(String) -> Unit` | — |
| `placeholder` | Texte de l'espace réservé | `String?` | `null` |
| `rows` | Nombre de lignes (détermine la hauteur minimale) | `Int` | `3` |
| `disabled` | Désactivé ou non | `Boolean` | `false` |
| `readonly` | En lecture seule ou non | `Boolean` | `false` |
| `showCount` | Afficher ou non le comptage de caractères | `Boolean` | `false` |
| `maxlength` | Longueur maximale (tronqué au-delà) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Callbacks de focus / de perte de focus | `(() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | Marges internes (`spacing.md`) |
| `textarea.fontSize` | Taille de police (`typography.fontSize.base`) |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | Arrondi / largeur de bordure / largeur de bordure au focus |
| `textarea.countFontSize` | Taille de police du compteur (`typography.fontSize.extraSmall` |
| `textarea.minHeight` | Référence de hauteur minimale |
| `typography.lineHeight.primary` | Hauteur de ligne (conversion du nombre de lignes) |
| `color.text.{primary,secondary,placeholder}` | Couleurs du texte / du compteur / de l'espace réservé |
| `color.focus` / `color.border.base` | Couleurs de bordure au focus / par défaut |
| `color.disabled.{bg,text,border}` | Les trois couleurs de l'état désactivé |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `input-emits-onvaluechange` | La saisie déclenche onValueChange | `LhTextareaTest.input_emits_onValueChange` (Compose UI Test) |
| `disabled-blocks-input` | L'état désactivé bloque la saisie | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | Le compteur rend « n / max » | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | Sans maxlength, seule la longueur est rendue | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | Le focus consomme le jeton de focus | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | La saisie trop longue est tronquée | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # partie JVM
./gradlew :components:textarea:connectedAndroidTest           # partie d'interaction (nécessite un appareil)
```
