# Étiquette

Étiquette à couleur sémantique : prend en charge le style plain, le marquage tout arrondi et le bouton de fermeture.

> Source du contrat : [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> Cas dorés : [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag) (6 cas, traduits en tests unitaires JVM + Compose UI Test)

## Types

Six types sémantiques déterminent la couleur de fond et la couleur du texte.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Types de Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">Par défaut</span><span class="sim-tag sim-tag--success">Succès</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">Avertissement</span><span class="sim-tag sim-tag--danger">Danger</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">Info</span></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhTag(type = LhTagType.success) { Text("Succès") }
```

## Plain (contour)

`plain` applique un fond transparent avec bordure et texte aux couleurs sémantiques.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">Principal</span><span class="sim-tag sim-tag--plain sim-tag--success">Succès</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">Danger</span></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Dimensions et marquage

Trois dimensions mappées sur les jetons `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` ; `mark` applique un arrondi de la moitié de la hauteur.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions de Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">Marque</span><span class="sim-tag sim-tag--success sim-tag--mark">Marque succès</span></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `text` | Texte de l'étiquette (raccourci, au choix avec `content`) | `String?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `type` | Type sémantique | `LhTagType` | `Default` |
| `lhSize` | Dimension | `LhTagSize` | `Medium` |
| `plain` | Style plain ou non (fond transparent + bordure aux couleurs sémantiques) | `Boolean` | `false` |
| `round` | Arrondi complet ou non (moitié de la hauteur) | `Boolean` | `false` |
| `closable` | Afficher ou non le bouton de fermeture | `Boolean` | `false` |
| `onClose` | Callback de fermeture ; non déclenché si `closable` vaut false | `(() -> Unit)?` | `null` |
| `content` | Contenu de l'étiquette (remplace text, slot default du spec) | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| Valeur | Fond (hors plain) | Bordure/texte (plain) |
| --- | --- | --- |
| `Default` | `color.background.default` + bordure `color.border.base` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| Valeur | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small` (20dp) | `tag.paddingHorizontal.small` (8dp) | `tag.fontSize.small` (12sp) |
| `Medium` | `tag.height.medium` (24dp) | `tag.paddingHorizontal.medium` (10dp) | `tag.fontSize.medium` (14sp) |
| `Large` | `tag.height.large` (28dp) | `tag.paddingHorizontal.large` (12dp) | `tag.fontSize.large` (16sp) |

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `tag.height.{small,medium,large}` | Trois hauteurs |
| `tag.paddingHorizontal.{small,medium,large}` | Trois marges internes horizontales |
| `tag.fontSize.{small,medium,large}` | Trois tailles de police |
| `tag.borderRadius` / `tag.borderWidth` | Arrondi et largeur de bordure |
| `color.{primary,success,warning,danger,info}.default` | Fond / bordure / texte sémantiques |
| `color.onPrimary` | Couleur du texte hors type par défaut |
| `color.disabled.{bg,text,border}` | Les trois couleurs de l'état désactivé |
| `spacing.xs` | Espacement entre le bouton de fermeture et le texte |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `default-type-renders-text` | Le type par défaut prend le fond neutre + le texte principal + la bordure de base | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | Le type principal consomme les jetons de la couleur principale | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | Le style plain consomme les jetons de fond clair sémantiques | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | La petite taille consomme les jetons small (assertion inverse : large n'est pas appliqué) | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | L'arrondi de round prend la moitié de la hauteur | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | Le clic sur la fermeture déclenche onClose | `LhTagTest.closable_tap_emits_onClose` (Compose UI Test) |

```bash
./gradlew :components:tag:test                             # partie JVM
./gradlew :components:tag:connectedAndroidTest             # partie d'interaction (nécessite un appareil)
```
