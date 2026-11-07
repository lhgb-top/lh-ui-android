package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "インストール",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "環境要件",
      prose = "• Android Studio Ladybug 以上（AGP 8.7+）\n• Kotlin 2.1+、Jetpack Compose（BOM 2024.12+）\n• compileSdk 35、minSdk 24\n• ビルド時ツールチェーン: Node.js >= 20 + pnpm（トークンの接続とドキュメントサイトでのみ使用、ランタイムには含まれない）",
    ),
    DocSection(
      title = "依存関係の追加",
      prose = "ファミリーレイアウトでは子モジュールを直接参照します:",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // ファミリー Maven リポジトリ（移行期 / 社内ネットワーク用）",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // D22 の単一座標: コンポーネント + トークンがすべて含まれる",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "トークンの同期",
      prose = "トークンは生成物であり、`lh-ui-core` から同期しなければなりません（唯一の真源であり、手書きでの変更は禁止）:\n`pnpm link:tokens` は三段階の存在性ガード付きです: ローカルの `../lh-ui-core/dist/android` → npm の `@lh-ui/core` 成果物 → エラー。",
    ),
    DocSection(
      title = "インストールの検証",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # JVM 単体テスト（ゴールデンケースの純粋なロジック）",
    "./gradlew :components:button:assembleDebug       # ビルド",
  ),
)
