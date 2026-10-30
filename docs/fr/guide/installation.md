# Installation

## Prérequis

- Android Studio Ladybug ou supérieur (AGP 8.7+)
- Kotlin 2.1+, Jetpack Compose (BOM 2024.12+)
- compileSdk 35, minSdk 24
- Chaîne de construction : Node.js >= 20 + pnpm (uniquement pour la liaison des jetons et le site de documentation, **n'entre pas dans le runtime**)

## Ajout de la dépendance

### Maven (une fois la chaîne de publication en place)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // dépôt Maven de la famille (transition / intranet)
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // D22 coordonnée unique : composants + jetons inclus
}
```

### Intégration par code source (phase actuelle)

En disposant les dépôts selon la disposition de la famille, on dépend directement des sous-modules :

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## Synchroniser les jetons

Les jetons sont des artefacts générés : ils doivent être synchronisés depuis `lh-ui-core` (source unique, ne pas modifier à la main) :

```bash
cd ../lh-ui-core && pnpm codegen   # génère les artefacts de jetons dist/android
cd ../lh-ui-android
pnpm install                        # installe la chaîne de construction
pnpm link:tokens                    # synchronise les jetons vers core/src/main/kotlin/top/lhgb/ui/tokens/
```

`pnpm link:tokens` embarque une garde d'existence à trois niveaux : `../lh-ui-core/dist/android` local → artefact npm `@lh-ui/core` → erreur.

## Vérifier l'installation

```bash
./gradlew :components:button:test                # tests unitaires JVM (logique pure des cas dorés)
./gradlew :components:button:assembleDebug       # construire
```
