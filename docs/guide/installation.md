# 安装

## 环境要求

- Android Studio Ladybug 或更高（AGP 8.7+）
- Kotlin 2.1+，Jetpack Compose（BOM 2024.12+）
- compileSdk 35，minSdk 24
- 构建期工具链：Node.js >= 20 + pnpm（仅令牌连接与文档站使用，**不进入运行时**）

## 引入依赖

### Maven（nM6 发包链路打通后）

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb/lh-ui") // 家族 Maven 仓
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:lh-ui-android:0.0.1")
  implementation("top.lhgb.ui:tokens:0.0.1") // 令牌包（与 core 同版本）
}
```

### 源码集成（当前阶段）

家族布局下直接依赖子模块：

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## 同步令牌

令牌是生成物，必须从 `lh-ui-core` 同步（唯一真源，禁止手改）：

```bash
cd ../lh-ui-core && pnpm codegen   # 生成 dist/android 令牌产物
cd ../lh-ui-android
pnpm install                        # 安装构建期工具链
pnpm link:tokens                    # 同步令牌到 core/src/main/kotlin/top/lhgb/ui/tokens/
```

`pnpm link:tokens` 带三级存在性守卫：本地 `../lh-ui-core/dist/android` → npm `@lh-ui/core` 产物 → 报错。

## 验证安装

```bash
./gradlew :components:button:test                # JVM 单测（黄金用例纯逻辑）
./gradlew :components:button:assembleDebug       # 构建
```
