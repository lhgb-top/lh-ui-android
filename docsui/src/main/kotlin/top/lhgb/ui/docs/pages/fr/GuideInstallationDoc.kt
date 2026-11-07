package top.lhgb.ui.docs.pages.fr

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
      title = "Prérequis",
      prose = "• Android Studio Ladybug ou supérieur (AGP 8.7+)\n• Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)\n• compileSdk 35, minSdk 24\n• Chaîne de construction : Node.js >= 20 + pnpm (uniquement pour la liaison des jetons et le site de documentation, n'entre pas dans le runtime)",
    ),
    DocSection(
      title = "Ajout de la dépendance",
      prose = "En disposant les dépôts selon la disposition de la famille, on dépend directement des sous-modules :",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // dépôt Maven de la famille (transition / intranet)",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // D22 coordonnée unique : composants + jetons inclus",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "Synchroniser les jetons",
      prose = "Les jetons sont des artefacts générés : ils doivent être synchronisés depuis `lh-ui-core` (source unique, ne pas modifier à la main) :\n`pnpm link:tokens` embarque une garde d'existence à trois niveaux : `../lh-ui-core/dist/android` local → artefact npm `@lh-ui/core` → erreur.",
    ),
    DocSection(
      title = "Vérifier l'installation",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # tests unitaires JVM (logique pure des cas dorés)",
    "./gradlew :components:button:assembleDebug       # construire",
  ),
)
