package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Dialog",
  lede = "A modal overlay for important confirmations or content that needs the user's focus. The window-level layering is hosted by the Compose `Dialog`.",
  group = DocGroup.OVERLAY,
  contract = "Spec source: `lh-ui-core/specs/dialog.json` · Golden cases: `cases/dialog.json` (8 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Types",
      prose = "`type` determines the confirm button's color (mapped 1:1 to `LhButtonType`); the rest of the layout stays the same.",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* confirm logic */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"删除确认\",",
        "  content = \"删除后不可恢复，确定删除吗？\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "Semantic type and overlay",
      prose = "When `type` is `danger`, the confirm button takes the danger color; `closable` controls whether tapping the overlay or pressing the back button triggers `onClose`.\nWhen the confirm / cancel button is tapped, the business callback fires first, then `onClose` — the host uses this to set `visible` to false.",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Whether the dialog is shown; when false, nothing is rendered", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Triggered when the confirm button is tapped", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Triggered when the cancel button is tapped", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Triggered after confirm / cancel, or (when closable) when the overlay is tapped", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "Title", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "Body content", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Semantic type; determines the confirm button's type (color)", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "Confirm button text; when empty, the confirm button is hidden", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Cancel button text; when empty, the cancel button is hidden", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "Whether tapping the overlay or pressing back triggers onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "Title content (overrides title; the spec's title slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "Body content (overrides content; the spec's default slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "Bottom button area (overrides the default buttons; the spec's footer slot)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier (applied to the content card)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("Value", "Confirm button type"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "The `LhButtonType` of the same name, taking the corresponding semantic color")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "Content card width (320dp)")),
    DocRow(cells = listOf("`dialog.borderRadius`", "Card corner radius (follows `radius.base`)")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "Card padding (follows `spacing.lg`)")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "Title / body font sizes")),
    DocRow(cells = listOf("`dialog.gap`", "Spacing between the title, body, and button area")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Layering semantics alignment (the Compose `Dialog` hosts the window-level layering)")),
    DocRow(cells = listOf("`color.background.overlay`", "Overlay color")),
    DocRow(cells = listOf("`color.background.default`", "Card background color")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Title / body color")),
    DocRow(cells = listOf("`shadow.base`", "Card shadow (Compose has no spread; the blur radius is used as elevation)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "Confirm triggers onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "Confirm also triggers onClose", "same as above")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Cancel triggers onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "When closable, tapping the overlay triggers onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "When not closable, the overlay tap is swallowed", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "When hidden, nothing is rendered", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "The title renders text", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "The danger type's confirm button takes the danger color", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # JVM part",
    "./gradlew :components:dialog:connectedAndroidTest             # Interaction part (requires a device)",
  ),
)
