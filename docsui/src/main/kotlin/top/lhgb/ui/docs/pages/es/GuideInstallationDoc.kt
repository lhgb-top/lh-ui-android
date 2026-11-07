package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "Instalación",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Requisitos",
      prose = "• Android Studio Ladybug o superior (AGP 8.7+)\n• Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)\n• compileSdk 35, minSdk 24\n• Cadena de construcción: Node.js >= 20 + pnpm (únicamente para el enlace de tokens y el sitio de documentación, no entra en el runtime)",
    ),
    DocSection(
      title = "Añadir la dependencia",
      prose = "Con la disposición de la familia, se depende directamente de los submodule:",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // repositorio Maven de la familia (transición / intranet)",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // coordenada única D22: componentes + tokens incluidos",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "Sincronizar los tokens",
      prose = "Los tokens son artefactos generados: deben sincronizarse desde `lh-ui-core` (fuente única, prohibido modificarlos a mano):\n`pnpm link:tokens` lleva una guarda de existencia de tres niveles: `../lh-ui-core/dist/android` local → artefacto npm `@lh-ui/core` → error.",
    ),
    DocSection(
      title = "Verificar la instalación",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # pruebas unitarias JVM (lógica pura de los casos dorados)",
    "./gradlew :components:button:assembleDebug       # construir",
  ),
)
