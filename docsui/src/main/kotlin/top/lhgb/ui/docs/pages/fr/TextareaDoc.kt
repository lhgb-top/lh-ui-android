package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Zone de texte",
  lede = "Zone de saisie multiligne contrôlée : nombre de lignes, comptage de caractères et longueur maximale.",
  group = DocGroup.FORM,
  contract = "Source du contrat : `lh-ui-core/specs/textarea.json` · Cas dorés : `cases/textarea.json` (6 cas, traduits en tests unitaires JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Forme de base",
      prose = "Hauteur minimale = nombre de lignes × hauteur de ligne × taille de police ; au-delà, défilement vertical.",
      preview = { DocTextareaBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhTextarea(",
        "  value = value,",
        "  rows = 3,",
        "  showCount = true,",
        "  maxlength = 200,",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Comptage de caractères",
      prose = "Lorsque `showCount` vaut true, un compteur est rendu en bas à droite (`textarea.countFontSize`, couleur `text.secondary`) ; `maxlength` tronque la saisie.",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Valeur contrôlée (obligatoire)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Callback de changement de valeur (obligatoire)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Texte de l'espace réservé", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "Nombre de lignes (détermine la hauteur minimale)", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "Désactivé ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "En lecture seule ou non", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "Afficher ou non le comptage de caractères", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Longueur maximale (tronqué au-delà)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Callbacks de focus / de perte de focus", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "Marges internes (`spacing.md`)")),
    DocRow(cells = listOf("`textarea.fontSize`", "Taille de police (`typography.fontSize.base`)")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "Arrondi / largeur de bordure / largeur de bordure au focus")),
    DocRow(cells = listOf("`textarea.countFontSize`", "Taille de police du compteur (`typography.fontSize.extraSmall`")),
    DocRow(cells = listOf("`textarea.minHeight`", "Référence de hauteur minimale")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Hauteur de ligne (conversion du nombre de lignes)")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "Couleurs du texte / du compteur / de l'espace réservé")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "Couleurs de bordure au focus / par défaut")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Les trois couleurs de l'état désactivé")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "La saisie déclenche onValueChange", "`LhTextareaTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-input`", "L'état désactivé bloque la saisie", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "Le compteur rend « n / max »", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "Sans maxlength, seule la longueur est rendue", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "Le focus consomme le jeton de focus", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "La saisie trop longue est tronquée", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # partie JVM",
    "./gradlew :components:textarea:connectedAndroidTest           # partie d'interaction (nécessite un appareil)",
  ),
)
