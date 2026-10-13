pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "lh-ui-android"

include(":core")
include(":components:button")
include(":components:icon")
include(":components:loading")
include(":components:tag")
include(":components:cell")
include(":components:input")
include(":components:textarea")
include(":components:checkbox")
include(":components:radio")
include(":components:switch")
include(":components:dialog")
include(":components:toast")
include(":components:picker")
include(":components:navbar")

include(":docsui")
include(":docsapp")
