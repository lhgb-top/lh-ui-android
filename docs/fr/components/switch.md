# Interrupteur

Contrôle à deux états : piste en capsule + curseur circulaire, avec prise en charge de l'état de chargement.

> Source du contrat : [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> Cas dorés : [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch) (6 cas, traduits en tests unitaires JVM + Compose UI Test)

## États

À l'état ouvert, la piste prend la couleur principale ; à l'état fermé, la couleur de bordure ; diamètre du curseur = hauteur − 2 × `switch.gap`, l'animation de déplacement prend `motion.duration.base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>Fermé</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Ouvert</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>Désactivé (fermé)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>Désactivé (ouvert)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Chargement (anneau à la position du curseur, ne répond pas au clic)</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## Dimensions

Trois dimensions mappées sur les jetons `switch.width` / `switch.height`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions de Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `checked` | Ouvert ou non (obligatoire) | `Boolean` | — |
| `onChange` | Callback de changement d'état, portant le nouvel état | `(Boolean) -> Unit` | — |
| `disabled` | Désactivé ou non | `Boolean` | `false` |
| `loading` | En chargement ou non (anneau à la position du curseur, ne répond pas au clic) | `Boolean` | `false` |
| `lhSize` | Dimension | `LhSwitchSize` | `Medium` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhSwitchSize

| Valeur | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small` (36dp) | `switch.height.small` (20dp) |
| `Medium` | `switch.width.medium` (44dp) | `switch.height.medium` (24dp) |
| `Large` | `switch.width.large` (52dp) | `switch.height.large` (28dp) |

La piste est une capsule (arrondi égal à la moitié de la hauteur) ; le clic touche la zone de la piste, inverse l'état et déclenche `onChange`.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `switch.width.{small,medium,large}` | Trois largeurs de piste |
| `switch.height.{small,medium,large}` | Trois hauteurs de piste |
| `switch.gap` | Marge entre le curseur et la piste (diamètre du curseur = hauteur − 2 × gap) |
| `color.primary.default` | Couleur de la piste à l'état ouvert |
| `color.border.base` | Couleur de la piste à l'état fermé |
| `color.onPrimary` | Couleur du curseur |
| `color.disabled.{bg,text}` | Couleurs de piste / de curseur à l'état désactivé |
| `motion.duration.base` / `motion.easing.easeInOut` | Animation de déplacement du curseur |
| `loading.strokeWidth` | Largeur de trait de l'anneau de chargement (réutilisé) |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | Le clic inverse l'état et émet onChange | `LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-turns-off` | Un clic sur l'état ouvert le ferme | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | L'état désactivé ne déclenche pas | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | L'état de chargement ne déclenche pas | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | L'état ouvert consomme le jeton de couleur principale | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | L'état fermé consomme le jeton de bordure | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # partie JVM
./gradlew :components:switch:connectedAndroidTest            # partie d'interaction (nécessite un appareil)
```
