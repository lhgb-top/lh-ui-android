package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast",
  lede = "An ink-colored capsule overlay that manages its own visibility and timing, used for lightweight action feedback. It does not block the user.",
  group = DocGroup.OVERLAY,
  contract = "Spec source: `lh-ui-core/specs/toast.json` · Golden cases: `cases/toast.json` (6 cases — 4 state-machine + 2 rendering — all translated into local tests)",
  sections = listOf(
    DocSection(
      title = "Usage",
      prose = "A toast is a self-contained state machine: the state is held by `LhToastState` — the host calls `show(text)` to display it and `hide()` to hide it, and it hides itself automatically when the timer expires, firing `onClose`. While visible, the component drives `tick` on its own frame by frame; the host can also call it itself to take over the timing.",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* optional: log the close */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"已保存\") }) { Text(\"触发\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "Semantic types",
      prose = "For any type other than `default`, a semantic-color dot with a diameter equal to the font size is drawn in front of the text.",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "Positioning",
      prose = "`lhPosition` takes `top` / `center` / `bottom`, leaving a `spacing.xl` margin at both ends; the width is derived from text measurement and capped at 80% of the available width.",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "Toast state machine (holds visibility, text, and timing)", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "Semantic type; for any value other than default, a semantic-color dot is drawn", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "Position within the available area", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "Modifier (applied to the capsule)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("Member", "Description"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "Shows the toast and resets the timer")),
        DocRow(cells = listOf("`hide()`", "Hides immediately — does not trigger onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "Advances the timer; hides, fires onClose, and returns true when it expires")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "Current state (observable)")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("Value", "Dot color"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "No dot")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "The corresponding semantic color")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "Capsule maximum width (240dp; in practice the smaller of this and 80% of the available width)")),
    DocRow(cells = listOf("`toast.borderRadius`", "Capsule corner radius (follows `radius.base`)")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "Capsule padding")),
    DocRow(cells = listOf("`toast.fontSize`", "Text font size (the dot diameter equals this value)")),
    DocRow(cells = listOf("`toast.gap`", "Gap between the dot and the text")),
    DocRow(cells = listOf("`zIndex.toast`", "Layering semantics alignment (the Compose `Popup` provides window-level layering on its own)")),
    DocRow(cells = listOf("`color.background.overlay` (mapped to ink by the theme layer)", "Capsule background color")),
    DocRow(cells = listOf("`color.onPrimary`", "Text color")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Semantic dot colors")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "After show, the toast is visible and renders its text", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "Hides automatically when the timer expires and fires onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "Before the timer expires, it stays visible and does not fire onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide does not fire onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "The success type consumes the success color", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "When hidden, nothing is rendered", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # JVM part",
    "./gradlew :components:toast:connectedAndroidTest                 # Interaction part (requires a device)",
  ),
)
