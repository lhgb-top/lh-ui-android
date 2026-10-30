# Toast (notification)

Surcouche en pilule couleur encre, qui gère elle-même sa visibilité et son minutage, pour un retour d'action léger. Elle ne bloque pas les opérations de l'utilisateur.

> Source du contrat : [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> Cas dorés : [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast) (6 cas : 4 sur la machine à états + 2 sur le rendu, tous traduits en tests locaux)

## Utilisation

Le toast est une **machine à états autonome** : l'état est porté par `LhToastState`, l'hôte appelle `show(text)` pour l'afficher et `hide()` pour le masquer ;
à l'expiration du minuteur, il se masque automatiquement et déclenche `onClose`. Le composant s'auto-entraîne par `tick` à chaque image lorsqu'il est visible ; l'hôte peut aussi l'appeler lui-même pour prendre en charge le minutage.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">Opération réussie</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
val state = rememberLhToastState(onClose = { /* facultatif : journaliser la fermeture */ })
Column {
  LhButton(lhOnClick = { state.show("Enregistré") }) { Text("Déclencher") }
  LhToast(state = state)
}
```

## Types sémantiques

Pour un type autre que `default`, un point de couleur sémantique de diamètre égal à la taille de police est dessiné devant le texte.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Types de Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>Notification de succès</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>Notification d'avertissement</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>Notification d'échec</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>Notification principale</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Positionnement

`lhPosition` prend `top` / `center` / `bottom`, avec une marge de `spacing.xl` aux deux extrémités ; la largeur est mesurée d'après le texte et plafonnée à 80 % de la largeur disponible.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Positionnement de Toast</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">En haut</div>
    <div class="sim-toast sim-toast--bottom">En bas</div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `state` | Machine à états du toast (porte la visibilité, le texte et le minutage) | `LhToastState` | — |
| `type` | Type sémantique ; hors default, dessine un point de couleur sémantique | `LhToastType` | `default` |
| `lhPosition` | Position dans la zone disponible | `LhToastPosition` | `center` |
| `modifier` | Modifier (appliqué à la pilule) | `Modifier` | `Modifier` |

### LhToastState

| Membre | Description |
| --- | --- |
| `show(text: String)` | Afficher et réinitialiser le minuteur |
| `hide()` | Masquer immédiatement, **sans déclencher onClose** |
| `tick(deltaMs: Int): Boolean` | Avancer le minuteur ; à l'expiration, masquer, déclencher onClose et renvoyer true |
| `visible` / `text` / `duration` | État courant (observable) |

Fabrique : `rememberLhToastState(duration = 2000, onClose = {})`.

### LhToastType / LhToastPosition

| Valeur | Couleur du point |
| --- | --- |
| `default` | Aucun point |
| `primary` / `success` / `warning` / `danger` / `info` | Couleur sémantique correspondante |

`LhToastPosition` : `top` / `center` / `bottom`.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `toast.maxWidth` | Largeur maximale de la pilule (240dp ; on prend la plus petite entre 80 % de la largeur disponible et cette valeur) |
| `toast.borderRadius` | Arrondi de la pilule (suit `radius.base`) |
| `toast.paddingHorizontal` / `toast.paddingVertical` | Marges internes de la pilule |
| `toast.fontSize` | Taille de police du texte (également diamètre du point) |
| `toast.gap` | Espacement entre le point et le texte |
| `zIndex.toast` | Alignement sémantique des niveaux (le `Popup` de Compose gère le niveau de fenêtre) |
| `color.background.overlay` (mappage ink de la couche de thème) | Couleur de fond de la pilule |
| `color.onPrimary` | Couleur du texte |
| `color.{primary,success,warning,danger,info}.default` | Couleurs des points sémantiques |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `show-makes-visible` | Après show, visible et rend le texte | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | À l'expiration, se masque et déclenche onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | Avant l'expiration, reste visible et ne déclenche pas onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide ne déclenche pas onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | Le type success consomme la couleur de succès | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | Rien n'est rendu si invisible | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # partie JVM
./gradlew :components:toast:connectedAndroidTest                 # partie d'interaction (nécessite un appareil)
```
