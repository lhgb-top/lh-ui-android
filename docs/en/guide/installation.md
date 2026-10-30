# Installation

## Requirements

- Android Studio Ladybug or newer (AGP 8.7+)
- Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)
- compileSdk 35, minSdk 24
- Build-time toolchain: Node.js >= 20 + pnpm (used only for token linking and the docs site — **never enters the runtime**)

## Adding the dependency

### Maven (once publishing is live)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // family Maven repo (transition / intranet)
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // D22 single coordinate: components + tokens all included
}
```

### Source integration (current stage)

With the family layout, depend on the submodules directly:

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## Syncing tokens

Tokens are generated artifacts and must be synced from `lh-ui-core` (the single source of truth — hand-editing is forbidden):

```bash
cd ../lh-ui-core && pnpm codegen   # generate the dist/android token artifacts
cd ../lh-ui-android
pnpm install                        # install the build-time toolchain
pnpm link:tokens                    # sync tokens into core/src/main/kotlin/top/lhgb/ui/tokens/
```

`pnpm link:tokens` ships with a three-level existence guard: local `../lh-ui-core/dist/android` → the npm `@lh-ui/core` artifact → error.

## Verifying the installation

```bash
./gradlew :components:button:test                # JVM unit tests (pure golden-case logic)
./gradlew :components:button:assembleDebug       # build
```
