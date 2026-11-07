package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "Установка",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Требования к окружению",
      prose = "• Android Studio Ladybug или новее (AGP 8.7+)\n• Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)\n• compileSdk 35, minSdk 24\n• Инструменты сборки: Node.js >= 20 + pnpm (только для линковки токенов и сайта документации, в рантайм не попадают)",
    ),
    DocSection(
      title = "Подключение зависимости",
      prose = "При семейной раскладке зависим напрямую от подмодулей:",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // семейный Maven-репозиторий (переходный / внутренний)",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // единый артефакт D22: и компоненты, и токены",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "Синхронизация токенов",
      prose = "Токены — генерируемый артефакт, их нужно синхронизировать из `lh-ui-core` (единственный источник, ручное редактирование запрещено):\nВ `pnpm link:tokens` встроена трёхуровневая проверка наличия: локальный `../lh-ui-core/dist/android` → npm-артефакт `@lh-ui/core` → ошибка.",
    ),
    DocSection(
      title = "Проверка установки",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # JVM-тесты (чистая логика золотых сценариев)",
    "./gradlew :components:button:assembleDebug       # сборка",
  ),
)
