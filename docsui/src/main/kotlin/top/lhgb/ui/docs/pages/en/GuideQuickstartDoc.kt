package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "Quick Start",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Minimal example",
      prose = "`LhTheme` is the theme container — every Lh component inside it consumes tokens automatically; without the wrapper, components fall back to the light palette.",
      code = listOf(
        "import top.lhgb.ui.button.LhButton",
        "import top.lhgb.ui.button.LhButtonType",
        "import top.lhgb.ui.theme.LhTheme",
        "",
        "@Composable",
        "fun Demo() {",
        "  LhTheme {",
        "    LhButton(",
        "      type = LhButtonType.Primary,",
        "      lhOnClick = { /* handle the tap */ },",
        "    ) {",
        "      Text(\"主要按钮\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "Type and size",
      prose = "",
      code = listOf(
        "// Six types",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"默认\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"主要\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"成功\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"警告\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"危险\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"信息\") }",
        "",
        "// Three sizes",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"小型\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"中型\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"大型\") }",
      ),
    ),
    DocSection(
      title = "States",
      prose = "",
      code = listOf(
        "// Disabled: ignores taps and renders the disabled style",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"禁用\") }",
        "",
        "// Loading: shows an indicator and does not trigger lhOnClick",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"加载中\") }",
        "",
        "// Both: disabled wins (golden case disabled-precedes-loading)",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"禁用优先\") }",
      ),
    ),
    DocSection(
      title = "Icons and pattern background",
      prose = "",
      code = listOf(
        "// Icon names resolve against the local icon system (see LhButtonIcons; unregistered names draw nothing)",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"搜索\") }",
        "",
        "// Chinese-style background pattern (consumes the color.decoration token)",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"花纹底\") }",
      ),
    ),
    DocSection(
      title = "Light and dark theme",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // Every component inside switches to dark tokens automatically (precomputed by core, no runtime color math)",
        "}",
      ),
    ),
    DocSection(
      title = "Native docs module",
      prose = "Besides this website, the repo also ships an Android-native docs module (`:docsui`): its doc pages mirror this site's structure 1:1, but the previews are real, interactive, live-rendered Compose (buttons are tappable, inputs accept typing, dialogs pop up) rather than static HTML mocks.\nDrag-and-drop ready: once any Android project depends on it, a single `LhDocs.launch(context)` opens the full documentation (14 component pages + 5 guide pages + light/dark toggle + live previews):\nThe library's manifest automatically merges in an `exported = false` `LhDocsActivity`, so no launcher icon is injected into the host app.\nStandalone build: `:docsapp` is a paper-thin application module (a single Activity) — building it on its own yields a standalone \"lh-ui docs\" app:",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // or the Maven coordinate top.lhgb.ui:docsui",
        "",
        "// Any entry point (e.g. the click callback of a \"Component docs\" button)",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "Component example preview",
      prose = "Native UI cannot be rendered live in the browser, so this site presents the look with an inline static HTML phone-frame simulator:\nThe simulator follows the site's dark mode: toggle the theme in the top-right corner and the colors inside the phone frame change with it (dark tokens are precomputed by core).",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
