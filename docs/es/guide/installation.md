# Instalación

## Requisitos

- Android Studio Ladybug o superior (AGP 8.7+)
- Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)
- compileSdk 35, minSdk 24
- Cadena de construcción: Node.js >= 20 + pnpm (únicamente para el enlace de tokens y el sitio de documentación, **no entra en el runtime**)

## Añadir la dependencia

### Maven (una vez abierta la cadena de publicación)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // repositorio Maven de la familia (transición / intranet)
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // coordenada única D22: componentes + tokens incluidos
}
```

### Integración por código fuente (fase actual)

Con la disposición de la familia, se depende directamente de los submodule:

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## Sincronizar los tokens

Los tokens son artefactos generados: deben sincronizarse desde `lh-ui-core` (fuente única, prohibido modificarlos a mano):

```bash
cd ../lh-ui-core && pnpm codegen   # genera los artefactos de tokens dist/android
cd ../lh-ui-android
pnpm install                        # instala la cadena de construcción
pnpm link:tokens                    # sincroniza los tokens a core/src/main/kotlin/top/lhgb/ui/tokens/
```

`pnpm link:tokens` lleva una guarda de existencia de tres niveles: `../lh-ui-core/dist/android` local → artefacto npm `@lh-ui/core` → error.

## Verificar la instalación

```bash
./gradlew :components:button:test                # pruebas unitarias JVM (lógica pura de los casos dorados)
./gradlew :components:button:assembleDebug       # construir
```
