package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Picker",
  lede = "A single-column bottom wheel picker. When scrolling stops, the item closest to the center indicator is the selected one.",
  group = DocGroup.OVERLAY,
  contract = "Spec source: `lh-ui-core/specs/picker.json` · Golden cases: `cases/picker.json` (8 cases; clamping and token mappings translated into JVM unit tests, scrolling and button interactions translated into Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Usage",
      prose = "It pops up from the bottom: the overlay on top, the panel below (with rounded top corners). The toolbar has three parts: cancel / title / confirm.",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"选项一\", \"选项二\", \"选项三\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"请选择\",",
        ")",
      ),
    ),
    DocSection(
      title = "Selection mechanism",
      prose = "The wheel is a scrollable list where every item is `picker.itemHeight` tall; the center indicator is two horizontal lines spaced one itemHeight apart.\nAfter scrolling stops, the scrolled distance is rounded to derive the selected index, and `onValueChange` fires when it changes.\nWhen `value` is out of range, `LhPickerDefaults.clamp` clamps it into the candidate range.",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Whether the picker is shown; when false, nothing is rendered", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "Array of candidate item texts (single column)", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Triggered when the wheel stops and the selected item changes, carrying the new index", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Triggered when the confirm button is tapped, carrying the currently selected index", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Triggered when the cancel button is tapped", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Triggered after confirm / cancel; the host uses this to set visible to false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "Index of the selected item (0-based); clamped when out of range", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "Title in the middle of the toolbar", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "Confirm button text", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Cancel button text", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "Wheel content area (overrides the default candidate rendering; the spec's default slot)", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "Toolbar (overrides the default confirm / title / cancel; the spec's toolbar slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "Wheel list state (provided by default via `rememberLhPickerListState(value, options)`; can be injected from the outside in tests)", "`LazyListState`", "see left")),
    DocRow(cells = listOf("`modifier`", "Modifier (applied to the panel)", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "Panel height (260dp)")),
    DocRow(cells = listOf("`picker.itemHeight`", "Item height (40dp; also the center indicator spacing)")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "Toolbar height (44dp)")),
    DocRow(cells = listOf("`picker.itemFontSize`", "Candidate item font size")),
    DocRow(cells = listOf("`picker.borderRadius`", "Panel top corner radius")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "Panel horizontal padding (follows `spacing.md`)")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Layering semantics alignment (the Compose `Popup` provides window-level layering on its own)")),
    DocRow(cells = listOf("`color.background.overlay`", "Overlay color")),
    DocRow(cells = listOf("`color.background.default`", "Panel background color")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Title / candidate item color")),
    DocRow(cells = listOf("`color.border.lighter`", "Center indicator line color")),
    DocRow(cells = listOf("`color.primary.default`", "Confirm button color")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "Confirm carries the current index + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "Confirm also triggers onClose", "same as above")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Cancel triggers onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "Scrolling changes the selection and triggers onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "A negative value is clamped to 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "An out-of-range value is clamped to the last item", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "The title renders text", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "When hidden, nothing is rendered", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # JVM part",
    "./gradlew :components:picker:connectedAndroidTest               # Interaction part (requires a device)",
  ),
)
