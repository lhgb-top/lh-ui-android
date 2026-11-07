package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Bouton radio",
  lede = "Bouton radio contrôlé : une fois sélectionné, un nouveau clic ne le désélectionne pas (sémantique radio).",
  group = DocGroup.FORM,
  contract = "Source du contrat : `lh-ui-core/specs/radio.json` · Cas dorés : `cases/radio.json` (6 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "États et groupe radio",
      prose = "Le composant ne contient pas de logique de groupe : l'exclusion mutuelle est gérée par l'hôte (utiliser `value` pour identifier les éléments du groupe).",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"Option un\" to \"a\", \"Option deux\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "Dimensions",
      prose = "Trois dimensions d'anneau mappées sur les jetons `radio.size.*` ; diamètre du point intérieur = dimension × `radio.dotScale`.",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Sélectionné ou non (obligatoire)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Déclenché lors du passage de non sélectionné à sélectionné, avec `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Désactivé ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Texte du libellé", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "Valeur radio (identifiant dans le groupe ; le composant ne contient pas de logique de groupe)", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Dimension de l'anneau", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Contenu du libellé (remplace label, slot default du spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("Valeur", "Jeton de dimension", "Diamètre du point intérieur"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small` (16dp)", "16dp × `radio.dotScale` (0.5)")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium` (20dp)", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large` (24dp)", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "Trois dimensions d'anneau")),
    DocRow(cells = listOf("`radio.ringWidth`", "Largeur de trait de l'anneau extérieur")),
    DocRow(cells = listOf("`radio.dotScale`", "Ratio du point intérieur par rapport au diamètre de l'anneau (fraction)")),
    DocRow(cells = listOf("`radio.gap`", "Espacement entre l'anneau et le libellé")),
    DocRow(cells = listOf("`color.primary.default`", "Couleur de bordure et de point à l'état sélectionné")),
    DocRow(cells = listOf("`color.border.base`", "Couleur de bordure non sélectionnée")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "Couleurs bordure / point / texte de l'état désactivé")),
    DocRow(cells = listOf("`color.text.primary`", "Couleur du libellé")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Taille de police du libellé")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "Un clic non sélectionné déclenche onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "Un clic déjà sélectionné ne déclenche pas", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "L'état désactivé ne déclenche pas", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "L'état sélectionné consomme les jetons de la couleur principale", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "L'état non sélectionné consomme le jeton de bordure", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "La petite taille consomme le jeton de dimension small", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # partie JVM",
    "./gradlew :components:radio:connectedAndroidTest             # partie d'interaction (nécessite un appareil)",
  ),
)
