# Установка

## Требования к окружению

- Android Studio Ladybug или новее (AGP 8.7+)
- Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)
- compileSdk 35, minSdk 24
- Инструменты сборки: Node.js >= 20 + pnpm (только для линковки токенов и сайта документации, **в рантайм не попадают**)

## Подключение зависимости

### Maven (после настройки публикации)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // семейный Maven-репозиторий (переходный / внутренний)
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // единый артефакт D22: и компоненты, и токены
}
```

### Интеграция из исходников (текущий этап)

При семейной раскладке зависим напрямую от подмодулей:

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## Синхронизация токенов

Токены — генерируемый артефакт, их нужно синхронизировать из `lh-ui-core` (единственный источник, ручное редактирование запрещено):

```bash
cd ../lh-ui-core && pnpm codegen   # генерирует артефакты токенов в dist/android
cd ../lh-ui-android
pnpm install                        # устанавливает инструменты сборки
pnpm link:tokens                    # синхронизирует токены в core/src/main/kotlin/top/lhgb/ui/tokens/
```

В `pnpm link:tokens` встроена трёхуровневая проверка наличия: локальный `../lh-ui-core/dist/android` → npm-артефакт `@lh-ui/core` → ошибка.

## Проверка установки

```bash
./gradlew :components:button:test                # JVM-тесты (чистая логика золотых сценариев)
./gradlew :components:button:assembleDebug       # сборка
```
