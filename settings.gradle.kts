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
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
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
include(":components:text")
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

// 单坐标发布模块（聚合 core + 全部组件，仅发布用；开发态走原模块）
include(":lhui")

include(":docsui")
include(":docsapp")
