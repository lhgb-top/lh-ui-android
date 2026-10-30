# Icône

Composant de base des icônes. Les icônes des autres composants (cell / input / navbar, etc.) sont toutes rendues via le registre local de ce composant.

> Source du contrat : [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> Cas dorés : [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon) (5 cas, traduits en tests unitaires JVM + Compose UI Test)

## Dimensions et tons

Trois dimensions mappées sur les jetons `icon.size.*` ; le ton `default` prend `color.text.primary`, les autres prennent les couleurs sémantiques.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions de l'icône</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">Couleur par défaut (text.primary)</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## Tons sémantiques

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tons de l'icône</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Correspondance des noms d'icône

`LhIcons` est le registre local nom d'icône → `ImageVector` Material ; un nom non enregistré renvoie `null` (rien n'est dessiné, aucune exception levée) :

| Nom d'icône | Vecteur Material |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `name` | Nom de l'icône (résolu par le registre `LhIcons`) | `String` | — |
| `lhSize` | Dimension de l'icône | `LhIconSize` | `Medium` |
| `tone` | Ton sémantique | `LhIconTone` | `Default` |
| `contentDescription` | Description d'accessibilité ; prend par défaut le nom de l'icône ; passer explicitement `null` déclare une icône décorative | `String?` | `name` |
| `lhOnClick` | Callback de clic ; non cliquable si null | `(() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhIconSize

| Valeur | Jeton de dimension |
| --- | --- |
| `Small` | `icon.size.small` (16dp) |
| `Medium` | `icon.size.medium` (20dp) |
| `Large` | `icon.size.large` (24dp) |

### LhIconTone

| Valeur | Jeton de couleur |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `icon.size.{small,medium,large}` | Trois dimensions |
| `color.text.primary` | Ton default |
| `color.{primary,success,warning,danger,info}.default` | Tons sémantiques |
| `color.text.{regular,secondary}` | Tons regular / secondary |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `click-emits-onclick` | Le clic déclenche onClick | `LhIconTest.click_emits_onClick` (Compose UI Test) |
| `default-tone-uses-text-primary` | Le ton par défaut prend le jeton de couleur de texte principal | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | Le ton primary prend le jeton de couleur principale | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | La petite taille prend le jeton de dimension small (assertion inverse : large n'est pas appliqué) | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | Un nom d'icône enregistré est résolu en vecteur ; un nom non enregistré n'est pas dessiné | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # partie JVM
./gradlew :components:icon:connectedAndroidTest            # partie d'interaction (nécessite un appareil)
```
