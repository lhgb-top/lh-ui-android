package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Interrupteur",
  lede = "Contrôle à deux états : piste en capsule + curseur circulaire, avec prise en charge de l'état de chargement.",
  group = DocGroup.FORM,
  contract = "Source du contrat : `lh-ui-core/specs/switch.json` · Cas dorés : `cases/switch.json` (6 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "États",
      prose = "À l'état ouvert, la piste prend la couleur principale ; à l'état fermé, la couleur de bordure ; diamètre du curseur = hauteur − 2 × `switch.gap`, l'animation de déplacement prend `motion.duration.base`.",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "Dimensions",
      prose = "Trois dimensions mappées sur les jetons `switch.width` / `switch.height`.",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Ouvert ou non (obligatoire)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Callback de changement d'état, portant le nouvel état", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Désactivé ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "En chargement ou non (anneau à la position du curseur, ne répond pas au clic)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "Dimension", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("Valeur", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small` (36dp)", "`switch.height.small` (20dp)")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium` (44dp)", "`switch.height.medium` (24dp)")),
        DocRow(cells = listOf("`Large`", "`switch.width.large` (52dp)", "`switch.height.large` (28dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "Trois largeurs de piste")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "Trois hauteurs de piste")),
    DocRow(cells = listOf("`switch.gap`", "Marge entre le curseur et la piste (diamètre du curseur = hauteur − 2 × gap)")),
    DocRow(cells = listOf("`color.primary.default`", "Couleur de la piste à l'état ouvert")),
    DocRow(cells = listOf("`color.border.base`", "Couleur de la piste à l'état fermé")),
    DocRow(cells = listOf("`color.onPrimary`", "Couleur du curseur")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "Couleurs de piste / de curseur à l'état désactivé")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "Animation de déplacement du curseur")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Largeur de trait de l'anneau de chargement (réutilisé)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "Le clic inverse l'état et émet onChange", "`LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "Un clic sur l'état ouvert le ferme", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "L'état désactivé ne déclenche pas", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "L'état de chargement ne déclenche pas", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "L'état ouvert consomme le jeton de couleur principale", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "L'état fermé consomme le jeton de bordure", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # partie JVM",
    "./gradlew :components:switch:connectedAndroidTest            # partie d'interaction (nécessite un appareil)",
  ),
)
