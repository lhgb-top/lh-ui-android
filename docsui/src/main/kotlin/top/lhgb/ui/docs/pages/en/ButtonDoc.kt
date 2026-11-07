package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "Button",
  lede = "A basic action button. It triggers an immediate action and supports type, size, loading, disabled, icon, and a Chinese-style pattern background.",
  group = DocGroup.BASIC,
  contract = "Spec source: `lh-ui-core/specs/button.json` · Golden cases: `cases/button.json` (8 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Types",
      prose = "Six types determine the background and text color.",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"主要按钮\") }",
      ),
    ),
    DocSection(
      title = "Sizes",
      prose = "Three sizes map to the `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` tokens.",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "States",
      prose = "Neither disabled nor loading triggers `lhOnClick`; when both are set, disabled wins (golden case `disabled-precedes-loading`).",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "Icons and pattern background",
      prose = "Icon names resolve against the local icon system (`LhButtonIcons`, currently supporting search / refresh / check / close; unregistered names draw nothing).\nThe pattern background consumes the `color.decoration` token — it is layered above the background color but below the text, so it does not affect contrast.",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "Light and dark theme",
      prose = "Toggle the site theme in the top-right corner and the button colors inside the simulator phone frame change with it; on a real device, switch via `LhTheme(isDark = true)`.",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "Tap callback; not triggered when `disabled` or `loading` is true", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Button type; determines the background and text color", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Button size; maps to the size tokens", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Whether the button is disabled", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "Whether the button is loading (shows an indicator and does not trigger lhOnClick)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Icon name (resolved by the local icon system)", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "Whether to draw the Chinese-style background pattern", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "Button text content (the spec's default slot)", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("Value", "Background", "Text color"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("Value", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small` (32dp)", "`button.paddingHorizontal.small` (16dp)", "`button.fontSize.small` (13sp)", "`button.iconSize.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium` (40dp)", "`button.paddingHorizontal.medium` (20dp)", "`button.fontSize.medium` (14sp)", "`button.iconSize.medium` (18dp)")),
        DocRow(cells = listOf("`Large`", "`button.height.large` (48dp)", "`button.paddingHorizontal.large` (24dp)", "`button.fontSize.large` (16sp)", "`button.iconSize.large` (20dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "Three heights")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "Three horizontal paddings")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "Three font sizes")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "Three icon sizes")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "Corner radius (follows `radius.base`) and border width")),
    DocRow(cells = listOf("`button.fontWeight`", "Font weight (follows `typography.fontWeight.primary`)")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Primary type background / text")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "Semantic type backgrounds")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "Default type background / text / border")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "The three disabled colors")),
    DocRow(cells = listOf("`color.decoration`", "Pattern color")),
    DocRow(cells = listOf("`shadow.lighter`", "Default-type elevation")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "Press-feedback animation")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "A tap triggers onClick exactly once", "`LhButtonTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-click`", "The disabled state does not trigger", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "The loading state does not trigger", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "Disabled wins", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "The default type renders text", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "The primary type consumes primary tokens", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "Small consumes the small size tokens", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "The pattern background consumes the decoration color", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "A registered icon name renders a vector; an unregistered name draws nothing", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # JVM part",
    "./gradlew :components:button:connectedAndroidTest        # Interaction part (requires a device)",
  ),
)
