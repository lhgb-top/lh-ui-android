package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "설치",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "환경 요구 사항",
      prose = "• Android Studio Ladybug 이상(AGP 8.7+)\n• Kotlin 2.1+, Jetpack Compose(BOM 2024.12+)\n• compileSdk 35, minSdk 24\n• 빌드 시간 도구 체인: Node.js >= 20 + pnpm(토큰 연결과 문서 사이트에만 사용, 런타임에는 포함되지 않음)",
    ),
    DocSection(
      title = "의존성 추가",
      prose = "패밀리 레이아웃 하에서는 서브모듈을 직접 의존합니다:",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // 패밀리 Maven 저장소(전환 / 사내망)",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // D22 단일 좌표: 컴포넌트 + 토큰 모두 포함",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "토큰 동기화",
      prose = "토큰은 생성물이며 `lh-ui-core`에서 동기화해야 합니다(유일한 원본, 수동 수정 금지):\n`pnpm link:tokens`에는 3단계 존재 가드가 있습니다: 로컬 `../lh-ui-core/dist/android` → npm `@lh-ui/core` 산출물 → 오류.",
    ),
    DocSection(
      title = "설치 확인",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # JVM 단위 테스트(골든 케이스 순수 로직)",
    "./gradlew :components:button:assembleDebug       # 빌드",
  ),
)
