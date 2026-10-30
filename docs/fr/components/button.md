# Bouton

Bouton d'action de base. Déclenche une action immédiate ; gère le type, la dimension, le chargement, la désactivation, l'icône et le motif décoratif style chinois.

> Source du contrat : [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> Cas dorés : [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button) (8 cas, traduits en tests unitaires JVM + Compose UI Test)

## Types

Six types déterminent la couleur de fond et la couleur du texte.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Types de Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">Bouton par défaut</button>
      <button class="sim-btn sim-btn--primary">Bouton principal</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">Bouton succès</button>
      <button class="sim-btn sim-btn--warning">Bouton avertissement</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">Bouton danger</button>
      <button class="sim-btn sim-btn--info">Bouton info</button>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("Bouton principal") }
```

## Dimensions

Trois dimensions mappées sur les jetons `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions de Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Bouton petit</button>
      <button class="sim-btn">Bouton moyen</button>
      <button class="sim-btn sim-btn--large">Bouton grand</button>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## États

Désactivé et chargement ne déclenchent pas `lhOnClick` ; lorsque les deux se cumulent, le **désactivé prime** (cas doré `disabled-precedes-loading`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>États de Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Normal</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Désactivé</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Chargement
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Désactivé prioritaire</button>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Icône et motif décoratif

Le nom de l'icône est résolu par le système d'icônes local (`LhButtonIcons` ; actuellement search / refresh / check / close à l'appui, les noms non enregistrés ne sont pas dessinés).
Le motif décoratif consomme le jeton `color.decoration` : superposé au-dessus de la couleur de fond et en dessous du texte, sans affecter le contraste.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icône et motif</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Rechercher</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>Par défaut</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Motif principal</button>
      <button class="sim-btn sim-btn--pattern">Motif par défaut</button>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Thèmes clair et sombre

Basculez le thème du site en haut à droite : les couleurs des boutons dans le cadre du simulateur changent ; sur appareil réel, via `LhTheme(isDark = true)`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Bouton en sombre</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Bouton principal</button>
      <button class="sim-btn">Bouton par défaut</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Désactivé</button>
      <button class="sim-btn sim-btn--success">Succès</button>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel ; les valeurs clair/sombre proviennent toutes de l'instantané des jetons core</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `lhOnClick` | Callback de clic ; non déclenché si `disabled` ou `loading` vaut true | `() -> Unit` | — |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `type` | Type de bouton, détermine la couleur de fond et du texte | `LhButtonType` | `Default` |
| `lhSize` | Dimension du bouton, mappée sur les jetons de dimension | `LhButtonSize` | `Medium` |
| `disabled` | Désactivé ou non | `Boolean` | `false` |
| `loading` | En chargement ou non (affiche un indicateur, ne déclenche pas lhOnClick) | `Boolean` | `false` |
| `icon` | Nom de l'icône (résolu par le système d'icônes local) | `String?` | `null` |
| `bgPattern` | Dessiner ou non le motif décoratif style chinois | `Boolean` | `false` |
| `content` | Contenu textuel du bouton (slot default du spec) | `@Composable () -> Unit` | — |

### LhButtonType

| Valeur | Fond | Couleur du texte |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| Valeur | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small` (32dp) | `button.paddingHorizontal.small` (16dp) | `button.fontSize.small` (13sp) | `button.iconSize.small` (16dp) |
| `Medium` | `button.height.medium` (40dp) | `button.paddingHorizontal.medium` (20dp) | `button.fontSize.medium` (14sp) | `button.iconSize.medium` (18dp) |
| `Large` | `button.height.large` (48dp) | `button.paddingHorizontal.large` (24dp) | `button.fontSize.large` (16sp) | `button.iconSize.large` (20dp) |

Surcharges de l'état désactivé : fond `color.disabled.bg`, texte `color.disabled.text`, bordure `color.disabled.border`.

## Référence des jetons

Jetons consommés par le bouton (alignés un à un avec les `tokenRefs` de `specs/button.json`) :

| Jeton | Utilisation |
| --- | --- |
| `button.height.{small,medium,large}` | Trois hauteurs |
| `button.paddingHorizontal.{small,medium,large}` | Trois marges internes horizontales |
| `button.fontSize.{small,medium,large}` | Trois tailles de police |
| `button.iconSize.{small,medium,large}` | Trois tailles d'icône |
| `button.borderRadius` / `button.borderWidth` | Arrondi (suit `radius.base`) et largeur de bordure |
| `button.fontWeight` | Graisse (suit `typography.fontWeight.primary`) |
| `color.primary.default` / `color.onPrimary` | Fond / texte du type principal |
| `color.{success,warning,danger,info}.default` | Fonds des types sémantiques |
| `color.background.default` / `color.text.primary` / `color.border.base` | Fond / texte / bordure du type par défaut |
| `color.disabled.{bg,text,border}` | Les trois couleurs de l'état désactivé |
| `color.decoration` | Couleur du motif décoratif |
| `shadow.lighter` | Élévation du type par défaut |
| `motion.duration.fast` / `motion.easing.easeInOut` | Animation de retour au clic |

## Référence des cas dorés

Correspondance entre les 8 assertions de `cases/button.json` et les tests locaux :

| case id | Assertion | Test local |
| --- | --- | --- |
| `click-emits-onclick` | Le clic déclenche onClick une seule fois | `LhButtonTest.click_emits_onClick` (Compose UI Test) |
| `disabled-blocks-click` | L'état désactivé ne déclenche pas | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | L'état de chargement ne déclenche pas | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | Le désactivé prime | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | Le type par défaut rend du texte | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | Le type principal consomme les jetons de la couleur principale | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | La petite taille consomme les jetons de dimension small | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | Le motif décoratif consomme la couleur de décoration | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | Un nom d'icône enregistré rend un vecteur ; un nom non enregistré n'est pas dessiné | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # partie JVM
./gradlew :components:button:connectedAndroidTest        # partie d'interaction (nécessite un appareil)
```
