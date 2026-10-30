# Chargement

Indicateur de chargement, animation de rotation en anneau, durée issue de `motion.duration.base` (millisecondes).

> Source du contrat : [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> Cas dorés : [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading) (5 cas, composant purement visuel, traduits en tests unitaires JVM)

## Dimensions et tons

Trois dimensions mappées sur les jetons `loading.size.*`, largeur de trait issue de `loading.strokeWidth`, ton pris dans les couleurs sémantiques.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Chargement</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">Chargement…</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `lhSize` | Diamètre de l'indicateur | `LhLoadingSize` | `Medium` |
| `tone` | Couleur de l'anneau | `LhLoadingTone` | `Primary` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhLoadingSize

| Valeur | Jeton de dimension |
| --- | --- |
| `Small` | `loading.size.small` (16dp) |
| `Medium` | `loading.size.medium` (20dp) |
| `Large` | `loading.size.large` (24dp) |

### LhLoadingTone

| Valeur | Jeton de couleur |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `loading.size.{small,medium,large}` | Trois diamètres |
| `loading.strokeWidth` | Largeur du trait de l'anneau |
| `color.text.regular` | Ton default |
| `color.{primary,success,warning,danger,info}.default` | Tons sémantiques |
| `motion.duration.base` | Durée d'un tour complet (entier en millisecondes côté appareil) |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `medium-size-uses-medium-token` | La taille moyenne prend le jeton de dimension medium | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | La grande taille prend le jeton de dimension large | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | Le ton primary prend le jeton de couleur principale | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | Le ton success prend le jeton de couleur de succès | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | L'indicateur en anneau est rendu (la largeur de trait consomme le jeton) | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # partie JVM (purement visuel, aucun cas d'interaction)
```
