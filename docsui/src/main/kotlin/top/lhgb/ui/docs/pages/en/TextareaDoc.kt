package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Textarea",
  lede = "A controlled multi-line input that supports row count, a character counter, and a maximum length.",
  group = DocGroup.FORM,
  contract = "Spec source: `lh-ui-core/specs/textarea.json` · Golden cases: `cases/textarea.json` (6 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Basic usage",
      prose = "The minimum height = rows × line height × font size; once content exceeds it, the field scrolls vertically.",
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
      title = "Character counter",
      prose = "When `showCount` is true, a counter is rendered in the bottom-right corner (`textarea.countFontSize`, color `text.secondary`); `maxlength` truncates input.",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Controlled value (required)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Value change callback (required)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Placeholder text", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "Row count (determines the minimum height)", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "Whether the textarea is disabled", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "Whether the textarea is read-only", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "Whether to show the character counter", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Maximum length (extra input is truncated)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Focus / blur callbacks", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "Padding (`spacing.md`)")),
    DocRow(cells = listOf("`textarea.fontSize`", "Font size (`typography.fontSize.base`)")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "Corner radius / border width / focus border width")),
    DocRow(cells = listOf("`textarea.countFontSize`", "Counter font size (`typography.fontSize.extraSmall`)")),
    DocRow(cells = listOf("`textarea.minHeight`", "Minimum-height baseline")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Line height (used to convert rows)")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "Text / counter / placeholder colors")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "Focus / default border colors")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "The three disabled colors")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "Input triggers onValueChange", "`LhTextareaTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-input`", "The disabled state accepts no input", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "The counter renders \"n / max\"", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "Without maxlength, only the length is rendered", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "Focus consumes the focus tokens", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "Input beyond the maximum length is truncated", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # JVM part",
    "./gradlew :components:textarea:connectedAndroidTest           # Interaction part (requires a device)",
  ),
)
