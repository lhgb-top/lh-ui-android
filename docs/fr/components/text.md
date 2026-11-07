# Texte (Text)

Primitive d'affichage de texte : consomme les tokens typographiques globaux, avec niveaux de taille, graisse, ton et alignement.

> Contrat : `lh-ui-core/specs/text.json` · Cas dorés : `cases/text.json` (5, traduits en tests JVM)

## Niveaux de taille

`level` est mappé au token `typography.fontSize.<level>` ; par défaut `base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text sim-text--extraLarge">extraLarge</p>
    <p class="sim-text sim-text--large">large</p>
    <p class="sim-text sim-text--medium">medium</p>
    <p class="sim-text">base</p>
    <p class="sim-text sim-text--small">small</p>
    <p class="sim-text sim-text--extraSmall">extraSmall</p>
  </div>
</div>
<p class="sim-note">Aperçu du simulateur HTML (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhText(content = "Title text", level = LhTextLevel.large)
```

## Graisse

`weight` est mappé au token `typography.fontWeight.<weight>` (primary 400 / bold 600).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text">primary</p>
    <p class="sim-text sim-text--bold">bold</p>
  </div>
</div>
<p class="sim-note">Aperçu du simulateur HTML (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Ton

`tone` est mappé au token `color.text.<tone>` ; `selected` prend `color.selected`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text">primary</p>
    <p class="sim-text sim-text--regular">regular</p>
    <p class="sim-text sim-text--secondary">secondary</p>
    <p class="sim-text sim-text--placeholder">placeholder</p>
    <p class="sim-text sim-text--disabled">disabled</p>
    <p class="sim-text sim-text--selected">selected</p>
  </div>
</div>
<p class="sim-note">Aperçu du simulateur HTML (apparence Android / Compose), pas un rendu sur appareil réel</p>

## Alignement et hauteur de ligne

`lhAlign` aligne dans la boîte de ligne ; la hauteur de ligne prend toujours `typography.lineHeight.primary` (taille × 1,8).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text sim-text--align-center">center</p>
    <p class="sim-text sim-text--align-end">end</p>
  </div>
</div>
<p class="sim-note">Aperçu du simulateur HTML (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
LhText(content = "Centered", lhAlign = LhTextAlign.center)
```

## Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `content` | text content (string form of the spec default slot) | `String` | `""` |
| `modifier` | modifier | `Modifier` | `Modifier` |
| `level` | font-size level | `LhTextLevel` | `Base` |
| `weight` | font weight | `LhTextWeight` | `Primary` |
| `tone` | text tone | `LhTextTone` | `Primary` |
| `lhAlign` | horizontal alignment | `LhTextAlign` | `Start` |

### LhTextLevel

| Value | fontSize token |
| --- | --- |
| `ExtraLarge` | `typography.fontSize.extraLarge` (20sp) |
| `Large` | `typography.fontSize.large` (18sp) |
| `Medium` | `typography.fontSize.medium` (16sp) |
| `Base` | `typography.fontSize.base` (14sp, default) |
| `Small` | `typography.fontSize.small` (13sp) |
| `ExtraSmall` | `typography.fontSize.extraSmall` (12sp) |

### LhTextTone

| Value | text color token |
| --- | --- |
| `Primary` | `color.text.primary` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |
| `Placeholder` | `color.text.placeholder` |
| `Disabled` | `color.text.disabled` |
| `Selected` | `color.selected` |

## Référence des tokens

| Token | Usage |
| --- | --- |
| `typography.fontSize.<level>` | six size levels |
| `typography.fontWeight.{primary,bold}` | two weights |
| `typography.lineHeight.primary` | line height (font size × 1.8) |
| `color.text.<tone>` / `color.selected` | text tones |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `default-level-uses-base-tokens` | default level consumes the base typography tokens | `LhTextDefaultsTest.default_level_uses_base_tokens` |
| `large-level-uses-large-token` | large level consumes the large size token | `LhTextDefaultsTest.large_level_uses_large_token` |
| `bold-weight-uses-bold-token` | bold consumes the bold weight token | `LhTextDefaultsTest.bold_weight_uses_bold_token` |
| `secondary-tone-uses-text-secondary` | secondary tone takes the secondary text color token | `LhTextDefaultsTest.secondary_tone_uses_text_secondary` |
| `render-emits-single-text-command` | render emits a single TEXT command | `LhTextDefaultsTest.all_levels_map_to_their_tokens` |

```bash
./gradlew :components:text:test                             # JVM
```
