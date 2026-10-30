# Cellule

Cellule de liste : disposition en trois sections icône / titre + description / valeur de droite + flèche, souvent combinée à une liste.

> Source du contrat : [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> Cas dorés : [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell) (6 cas, traduits en tests unitaires JVM + Compose UI Test)

## Forme de base

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">Texte du titre</div>
      <div class="sim-cell__value">Valeur de droite</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">Titre avec description</div>
        <div class="sim-cell__label">Texte de description</div>
      </div>
      <div class="sim-cell__value">Valeur</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Champ obligatoire</div>
      <div class="sim-cell__value">Détails</div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhCell(
  title = "Titre avec description",
  label = "Texte de description",
  value = "Valeur",
  isLink = true,
  lhOnClick = { /* naviguer */ },
)
```

## Obligatoire et lien

`required` dessine une étoile rouge devant le titre (`color.danger.default`) ; `isLink` dessine une flèche vers la droite à la fin.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Variantes de Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Obligatoire</div>
      <div class="sim-cell__value">Flèche →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">Sans bordure</div>
      <div class="sim-cell__value">Valeur</div>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `title` | Titre (texte principal) | `String?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |
| `value` | Valeur de droite (texte secondaire, aligné à droite) | `String?` | `null` |
| `label` | Texte d'explication sous le titre | `String?` | `null` |
| `bordered` | Dessiner ou non la ligne de séparation inférieure (`color.border.lighter`) | `Boolean` | `true` |
| `clickable` | Cliquable ou non ; si true, affiche la flèche indicatrice à droite et répond au clic | `Boolean` | `false` |
| `required` | Afficher ou non l'étoile rouge obligatoire devant le titre (`color.danger.default`) | `Boolean` | `false` |
| `icon` | Nom de l'icône à gauche (système d'icônes local) ; rien n'est dessiné si vide | `String?` | `null` |
| `lhOnClick` | Callback de clic ; non déclenché si `clickable` vaut false | `(() -> Unit)?` | `null` |
| `titleContent` | Contenu du titre (remplace title, slot default du spec) | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | Contenu de la valeur de droite (remplace value, slot value du spec) | `@Composable (() -> Unit)?` | `null` |

`clickable` contrôle à la fois « le dessin de la flèche indicatrice à droite » et « la réponse de toute la ligne au clic » — le clic n'est déclenché que si `clickable = true` et `lhOnClick` non null.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `cell.paddingVertical` | Marge interne verticale (référence `spacing.md`) |
| `cell.paddingHorizontal` | Marge interne horizontale (`spacing.md`) |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | Tailles de police du titre / de la description / de la valeur |
| `cell.gap` | Espacement entre l'icône et le titre (`spacing.xs`) |
| `icon.size.medium` | Dimension de l'icône de gauche et de la flèche indicatrice |
| `cell.borderWidth` | Largeur de la ligne de séparation inférieure |
| `typography.lineHeight.primary` | Hauteur de ligne du texte |
| `color.text.{primary,secondary}` | Couleurs du titre / de la description / de la valeur |
| `color.danger.default` | Étoile rouge obligatoire |
| `color.border.lighter` | Ligne de séparation inférieure |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `title-and-value-render` | Le titre et la valeur de droite rendent du texte | `LhCellTest.title_and_value_render` (Compose UI Test) |
| `label-renders-secondary-tone` | label prend le jeton de couleur de texte secondaire | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | Le marqueur obligatoire prend le jeton de couleur danger | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | La ligne de séparation consomme le jeton de bordure | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | Le clic déclenche onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | L'état non cliquable ne déclenche pas | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # partie JVM
./gradlew :components:cell:connectedAndroidTest              # partie d'interaction (nécessite un appareil)
```
