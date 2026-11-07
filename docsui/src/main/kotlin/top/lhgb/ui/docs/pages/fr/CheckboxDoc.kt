package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Case à cocher",
  lede = "Case à cocher contrôlée : prend en charge l'état indéterminé et la désactivation.",
  group = DocGroup.FORM,
  contract = "Source du contrat : `lh-ui-core/specs/checkbox.json` · Cas dorés : `cases/checkbox.json` (6 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "États",
      prose = "Coché / indéterminé : fond plein couleur principale + indicateur onPrimary (coche / barre horizontale) ; non coché : bordure seule.",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"Accepter les conditions\",",
        ")",
      ),
    ),
    DocSection(
      title = "Dimensions",
      prose = "Trois dimensions de case mappées sur les jetons `checkbox.size.*`.",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Cochée ou non (obligatoire)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "Callback de changement d'état, portant le nouvel état", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "État indéterminé ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "Désactivée ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Texte du libellé", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Dimension de la case", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Contenu du libellé (remplace label, slot default du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("Valeur", "Jeton de dimension"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large` (24dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "Trois dimensions de case")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "Arrondi et largeur de bordure de la case")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "Largeur de trait de la coche / barre horizontale")),
    DocRow(cells = listOf("`checkbox.gap`", "Espacement entre la case et le libellé")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Fond coché / couleur de l'indicateur")),
    DocRow(cells = listOf("`color.border.base`", "Couleur de bordure non cochée")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "Les trois couleurs de l'état désactivé")),
    DocRow(cells = listOf("`color.text.primary`", "Couleur du libellé")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Taille de police du libellé")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "Le clic inverse l'état et émet onChange", "`LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "Un clic sur l'état coché décoche", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "Un clic sur l'état indéterminé coche", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "L'état désactivé ne déclenche pas", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "L'état coché consomme les jetons de la couleur principale", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "L'état non coché consomme le jeton de bordure", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # partie JVM",
    "./gradlew :components:checkbox:connectedAndroidTest           # partie d'interaction (nécessite un appareil)",
  ),
)
