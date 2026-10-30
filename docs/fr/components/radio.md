# Bouton radio

Bouton radio contrôlé : une fois sélectionné, un nouveau clic ne le désélectionne pas (sémantique radio).

> Source du contrat : [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> Cas dorés : [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio) (6 cas, traduits en tests unitaires JVM + Compose UI Test)

## États et groupe radio

Le composant ne contient pas de logique de groupe : l'exclusion mutuelle est gérée par l'hôte (utiliser `value` pour identifier les éléments du groupe).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>Non sélectionné</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>Sélectionné</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>Désactivé (non sélectionné)</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>Désactivé (sélectionné)</label></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("Option un" to "a", "Option deux" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## Dimensions

Trois dimensions d'anneau mappées sur les jetons `radio.size.*` ; diamètre du point intérieur = dimension × `radio.dotScale`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions de Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `checked` | Sélectionné ou non (obligatoire) | `Boolean` | — |
| `onChange` | Déclenché lors du passage de non sélectionné à sélectionné, avec `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | Désactivé ou non | `Boolean` | `false` |
| `label` | Texte du libellé | `String?` | `null` |
| `value` | Valeur radio (identifiant dans le groupe ; le composant ne contient pas de logique de groupe) | `String?` | `null` |
| `lhSize` | Dimension de l'anneau | `LhRadioSize` | `Medium` |
| `labelContent` | Contenu du libellé (remplace label, slot default du spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhRadioSize

| Valeur | Jeton de dimension | Diamètre du point intérieur |
| --- | --- | --- |
| `Small` | `radio.size.small` (16dp) | 16dp × `radio.dotScale` (0.5) |
| `Medium` | `radio.size.medium` (20dp) | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large` (24dp) | 24dp × `radio.dotScale` |

Le clic touche toute la ligne : uniquement si l'élément est non sélectionné et non désactivé, il devient sélectionné et déclenche `onChange(true)`.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `radio.size.{small,medium,large}` | Trois dimensions d'anneau |
| `radio.ringWidth` | Largeur de trait de l'anneau extérieur |
| `radio.dotScale` | Ratio du point intérieur par rapport au diamètre de l'anneau (fraction) |
| `radio.gap` | Espacement entre l'anneau et le libellé |
| `color.primary.default` | Couleur de bordure et de point à l'état sélectionné |
| `color.border.base` | Couleur de bordure non sélectionnée |
| `color.disabled.{border,text}` | Couleurs bordure / point / texte de l'état désactivé |
| `color.text.primary` | Couleur du libellé |
| `typography.fontSize.base` | Taille de police du libellé |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | Un clic non sélectionné déclenche onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test) |
| `tap-checked-keeps-checked` | Un clic déjà sélectionné ne déclenche pas | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | L'état désactivé ne déclenche pas | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | L'état sélectionné consomme les jetons de la couleur principale | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | L'état non sélectionné consomme le jeton de bordure | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | La petite taille consomme le jeton de dimension small | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # partie JVM
./gradlew :components:radio:connectedAndroidTest             # partie d'interaction (nécessite un appareil)
```
