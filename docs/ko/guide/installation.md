# 설치

## 환경 요구 사항

- Android Studio Ladybug 이상(AGP 8.7+)
- Kotlin 2.1+, Jetpack Compose(BOM 2024.12+)
- compileSdk 35, minSdk 24
- 빌드 시간 도구 체인: Node.js >= 20 + pnpm(토큰 연결과 문서 사이트에만 사용, **런타임에는 포함되지 않음**)

## 의존성 추가

### Maven(패키지 게시 체인이 연결된 이후)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // 패밀리 Maven 저장소(전환 / 사내망)
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // D22 단일 좌표: 컴포넌트 + 토큰 모두 포함
}
```

### 소스 코드 통합(현재 단계)

패밀리 레이아웃 하에서는 서브모듈을 직접 의존합니다:

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## 토큰 동기화

토큰은 생성물이며 `lh-ui-core`에서 동기화해야 합니다(유일한 원본, 수동 수정 금지):

```bash
cd ../lh-ui-core && pnpm codegen   # dist/android 토큰 산출물 생성
cd ../lh-ui-android
pnpm install                        # 빌드 시간 도구 체인 설치
pnpm link:tokens                    # 토큰을 core/src/main/kotlin/top/lhgb/ui/tokens/로 동기화
```

`pnpm link:tokens`에는 3단계 존재 가드가 있습니다: 로컬 `../lh-ui-core/dist/android` → npm `@lh-ui/core` 산출물 → 오류.

## 설치 확인

```bash
./gradlew :components:button:test                # JVM 단위 테스트(골든 케이스 순수 로직)
./gradlew :components:button:assembleDebug       # 빌드
```
