package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "Icon",
  lede = "The base icon component. Icons in other components (cell / input / navbar, etc.) are all rendered through this component's local registry.",
  group = DocGroup.BASIC,
  contract = "Spec source: `lh-ui-core/specs/icon.json` · Golden cases: `cases/icon.json` (5 cases, translated into JVM unit tests + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Size and tone",
      prose = "Three sizes map to the `icon.size.*` tokens; the `default` tone takes `color.text.primary`, and the others take semantic colors.",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "Semantic tones",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "Icon registry",
      prose = "`LhIcons` is the local registry mapping icon names to Material `ImageVector`s. Unregistered names return `null` (nothing is drawn, no exception thrown):",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "Icon name (resolved by the `LhIcons` registry)", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "Icon size", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Semantic tone", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "Accessibility description; defaults to the icon name — pass `null` explicitly to declare a decorative icon", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "Tap callback; not tappable when null", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("Value", "Size token"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`icon.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("Value", "Color token"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "Three sizes")),
    DocRow(cells = listOf("`color.text.primary`", "default tone")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Semantic tones")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "regular / secondary tones")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "A tap triggers onClick", "`LhIconTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "The default tone takes the primary text token", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "The primary tone takes the primary token", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "Small takes the small size token (with a reverse assertion that large is not applied)", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "A registered icon name resolves to a vector; an unregistered name draws nothing", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # JVM part",
    "./gradlew :components:icon:connectedAndroidTest            # Interaction part (requires a device)",
  ),
)
