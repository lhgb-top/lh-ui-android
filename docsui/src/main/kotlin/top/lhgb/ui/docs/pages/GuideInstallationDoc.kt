package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "安装",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "环境要求",
      prose = "• Android Studio Ladybug 或更高（AGP 8.7+）\n• Kotlin 2.1+，Jetpack Compose（BOM 2024.12+）\n• compileSdk 35，minSdk 24\n• 构建期工具链：Node.js >= 20 + pnpm（仅令牌连接与文档站使用，不进入运行时）",
    ),
    DocSection(
      title = "引入依赖",
      prose = "家族布局下直接依赖子模块：",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // 家族 Maven 仓",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // D22 单坐标：组件 + 令牌全在内",
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
      prose = "令牌是生成物，必须从 `lh-ui-core` 同步（唯一真源，禁止手改）：\n`pnpm link:tokens` 带三级存在性守卫：本地 `../lh-ui-core/dist/android` → npm `@lh-ui/core` 产物 → 报错。",
    ),
    DocSection(
      title = "验证安装",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # JVM 单测（黄金用例纯逻辑）",
    "./gradlew :components:button:assembleDebug       # 构建",
  ),
)
