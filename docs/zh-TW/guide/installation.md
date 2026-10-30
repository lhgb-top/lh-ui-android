# 安裝

## 環境要求

- Android Studio Ladybug 或更高（AGP 8.7+）
- Kotlin 2.1+，Jetpack Compose（BOM 2024.12+）
- compileSdk 35，minSdk 24
- 構建期工具鏈：Node.js >= 20 + pnpm（僅令牌連接與文件站使用，**不進入執行時**）

## 引入依賴

### Maven（發包鏈路打通後）

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // 家族 Maven 倉（過渡 / 內網）
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // D22 單座標：元件 + 令牌全在內
}
```

### 原始碼整合（當前階段）

家族布局下直接依賴子模組：

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## 同步令牌

令牌是生成物，必須從 `lh-ui-core` 同步（唯一真源，禁止手改）：

```bash
cd ../lh-ui-core && pnpm codegen   # 生成 dist/android 令牌產物
cd ../lh-ui-android
pnpm install                        # 安裝構建期工具鏈
pnpm link:tokens                    # 同步令牌到 core/src/main/kotlin/top/lhgb/ui/tokens/
```

`pnpm link:tokens` 帶三級存在性守衛：本地 `../lh-ui-core/dist/android` → npm `@lh-ui/core` 產物 → 報錯。

## 驗證安裝

```bash
./gradlew :components:button:test                # JVM 單測（黃金用例純邏輯）
./gradlew :components:button:assembleDebug       # 構建
```
