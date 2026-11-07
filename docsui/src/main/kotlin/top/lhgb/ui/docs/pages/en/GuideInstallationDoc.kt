package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "Installation",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Requirements",
      prose = "• Android Studio Ladybug or newer (AGP 8.7+)\n• Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)\n• compileSdk 35, minSdk 24\n• Build-time toolchain: Node.js >= 20 + pnpm (used only for token linking and the docs site — never enters the runtime)",
    ),
    DocSection(
      title = "Adding the dependency",
      prose = "With the family layout, depend on the submodules directly:",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // family Maven repo (transition / intranet)",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // D22 single coordinate: components + tokens all included",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "Syncing tokens",
      prose = "Tokens are generated artifacts and must be synced from `lh-ui-core` (the single source of truth — hand-editing is forbidden):\n`pnpm link:tokens` ships with a three-level existence guard: local `../lh-ui-core/dist/android` → the npm `@lh-ui/core` artifact → error.",
    ),
    DocSection(
      title = "Verifying the installation",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # JVM unit tests (pure golden-case logic)",
    "./gradlew :components:button:assembleDebug       # build",
  ),
)
