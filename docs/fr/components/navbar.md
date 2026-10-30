# Barre de navigation

Barre de titre en haut de page, disposition en trois sections gauche / centre / droite, souvent utilisée pour le retour et l'affichage du titre.

> Source du contrat : [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> Cas dorés : [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar) (6 cas : mappage des jetons traduit en tests unitaires JVM, clics gauche/droite en Compose UI Test)

## Utilisation

Trois sections de largeur égale (chacune `weight(1f)`), le titre centré horizontalement sur la largeur disponible ; ligne de séparation inférieure dessinée par défaut.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>Retour</div>
      <div class="sim-navbar__title">Titre de la page</div>
      <div class="sim-navbar__side sim-navbar__side--right">Plus</div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhNavbar(
  title = "Titre de la page",
  leftIcon = "arrow-left",
  leftText = "Retour",
  rightText = "Plus",
  onLeftClick = { /* retour */ },
  onRightClick = { /* plus */ },
)
```

## Contenus gauche et droit

Texte et icône peuvent coexister (icône à gauche, texte à droite, espacement `spacing.xs`) ; **un côté sans aucun contenu ne répond pas au clic**
(cas doré `no-left-content-no-onleftclick` : ce nœud ne possède même pas de sémantique cliquable).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Variantes de Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">Icône seule</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">Texte</div>
      <div class="sim-navbar__title">Sans séparateur</div>
      <div class="sim-navbar__side sim-navbar__side--right">Modifier</div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `title` | Titre central | `String?` | `null` |
| `onLeftClick` | Déclenché au clic sur la zone gauche | `() -> Unit` | `{}` |
| `onRightClick` | Déclenché au clic sur la zone droite | `() -> Unit` | `{}` |
| `leftText` | Texte du bouton gauche ; peut coexister avec leftIcon | `String?` | `null` |
| `leftIcon` | Nom de l'icône à gauche (système d'icônes local, par ex. `"arrow-left"`) | `String?` | `null` |
| `rightText` | Texte du bouton droit ; peut coexister avec rightIcon | `String?` | `null` |
| `rightIcon` | Nom de l'icône à droite | `String?` | `null` |
| `bordered` | Dessiner ou non la ligne de séparation inférieure | `Boolean` | `true` |
| `titleContent` | Contenu du titre (remplace title, slot default du spec) | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | Contenu de gauche (remplace leftText/leftIcon, slot left du spec) | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | Contenu de droite (remplace rightText/rightIcon, slot right du spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

Lorsque `*Content` / `*Text` / `*Icon` d'un côté sont tous vides, ce côté n'est pas cliquable et aucun contenu n'est dessiné.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `navbar.height` | Hauteur de la barre (44dp) |
| `navbar.paddingHorizontal` | Marge interne horizontale gauche et droite (suit `spacing.md`) |
| `navbar.titleFontSize` | Taille de police du titre et des textes latéraux |
| `navbar.iconSize` | Dimension des icônes latérales (suit `icon.size.medium`) |
| `navbar.borderWidth` | Largeur de la ligne de séparation inférieure |
| `zIndex.sticky` | Alignement sémantique du niveau sticky |
| `color.background.default` | Couleur de fond |
| `color.text.primary` / `color.text.regular` | Couleurs du titre / des textes latéraux |
| `color.border.lighter` | Couleur de la ligne de séparation |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | Le clic à gauche déclenche onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test) |
| `rightclick-emits-onrightclick` | Le clic à droite déclenche onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | Sans contenu à gauche, aucun déclenchement | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | Le titre rend du texte | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | La ligne de séparation consomme le jeton de bordure | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | La hauteur consomme le jeton navbar | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # partie JVM
./gradlew :components:navbar:connectedAndroidTest                # partie d'interaction (nécessite un appareil)
```
