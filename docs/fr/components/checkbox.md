# Case à cocher

Case à cocher contrôlée : prend en charge l'état indéterminé et la désactivation.

> Source du contrat : [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> Cas dorés : [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox) (6 cas, traduits en tests unitaires JVM + Compose UI Test)

## États

Coché / indéterminé : fond plein couleur principale + indicateur onPrimary (coche / barre horizontale) ; non coché : bordure seule.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>Non cochée</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>Cochée</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>Indéterminée</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Désactivée (cochée)</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Désactivée (non cochée)</label></div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "Accepter les conditions",
)
```

## Dimensions

Trois dimensions de case mappées sur les jetons `checkbox.size.*`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dimensions de Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé (apparence Android / Compose), pas un rendu sur appareil réel</p>

## API

### Props

| Prop | Description | Type | Valeur par défaut |
| --- | --- | --- | --- |
| `checked` | Cochée ou non (obligatoire) | `Boolean` | — |
| `onCheckedChange` | Callback de changement d'état, portant le nouvel état | `(Boolean) -> Unit` | — |
| `indeterminate` | État indéterminé ou non | `Boolean` | `false` |
| `disabled` | Désactivée ou non | `Boolean` | `false` |
| `label` | Texte du libellé | `String?` | `null` |
| `lhSize` | Dimension de la case | `LhCheckboxSize` | `Medium` |
| `labelContent` | Contenu du libellé (remplace label, slot default du spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

### LhCheckboxSize

| Valeur | Jeton de dimension |
| --- | --- |
| `Small` | `checkbox.size.small` (16dp) |
| `Medium` | `checkbox.size.medium` (20dp) |
| `Large` | `checkbox.size.large` (24dp) |

Le clic touche toute la ligne (case + libellé) : un clic sur l'état indéterminé coche la case, sinon l'état coché est inversé.

## Référence des jetons

| Jeton | Utilisation |
| --- | --- |
| `checkbox.size.{small,medium,large}` | Trois dimensions de case |
| `checkbox.borderRadius` / `checkbox.borderWidth` | Arrondi et largeur de bordure de la case |
| `checkbox.checkWidth` | Largeur de trait de la coche / barre horizontale |
| `checkbox.gap` | Espacement entre la case et le libellé |
| `color.primary.default` / `color.onPrimary` | Fond coché / couleur de l'indicateur |
| `color.border.base` | Couleur de bordure non cochée |
| `color.disabled.{bg,border,text}` | Les trois couleurs de l'état désactivé |
| `color.text.primary` | Couleur du libellé |
| `typography.fontSize.base` | Taille de police du libellé |

## Référence des cas dorés

| case id | Assertion | Test local |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | Le clic inverse l'état et émet onChange | `LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-unchecks` | Un clic sur l'état coché décoche | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | Un clic sur l'état indéterminé coche | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | L'état désactivé ne déclenche pas | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | L'état coché consomme les jetons de la couleur principale | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | L'état non coché consomme le jeton de bordure | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # partie JVM
./gradlew :components:checkbox:connectedAndroidTest           # partie d'interaction (nécessite un appareil)
```
