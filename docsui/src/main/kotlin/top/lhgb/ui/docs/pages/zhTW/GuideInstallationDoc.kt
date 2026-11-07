package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "安裝",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "環境要求",
      prose = "• Android Studio Ladybug 或更高（AGP 8.7+）\n• Kotlin 2.1+，Jetpack Compose（BOM 2024.12+）\n• compileSdk 35，minSdk 24\n• 構建期工具鏈：Node.js >= 20 + pnpm（僅令牌連接與文件站使用，不進入執行時）",
    ),
    DocSection(
      title = "引入依賴",
      prose = "家族布局下直接依賴子模組：",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // 家族 Maven 倉（過渡 / 內網）",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // D22 單座標：元件 + 令牌全在內",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "同步令牌",
      prose = "令牌是生成物，必須從 `lh-ui-core` 同步（唯一真源，禁止手改）：\n`pnpm link:tokens` 帶三級存在性守衛：本地 `../lh-ui-core/dist/android` → npm `@lh-ui/core` 產物 → 報錯。",
    ),
    DocSection(
      title = "驗證安裝",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # JVM 單測（黃金用例純邏輯）",
    "./gradlew :components:button:assembleDebug       # 構建",
  ),
)
