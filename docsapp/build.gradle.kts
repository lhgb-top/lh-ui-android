plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

// 版本号与仓单包（package.json）保持同一来源：禁止在此手改版本，改 package.json 后
// 重新构建自动生效（LHUI 版本同步口径见 AGENTS.md §五 D22）。
val pkgVersion: String = (groovy.json.JsonSlurper()
  .parseText(file("../package.json").readText()) as Map<String, Any>)["version"] as String
val versionParts = pkgVersion.split(".").map { it.toIntOrNull() ?: 0 }
val pkgVersionCode = (versionParts.getOrNull(0) ?: 0) * 10000 +
  (versionParts.getOrNull(1) ?: 0) * 100 +
  (versionParts.getOrNull(2) ?: 0)

android {
  namespace = "top.lhgb.ui.docsapp"
  compileSdk = 35

  defaultConfig {
    applicationId = "top.lhgb.ui.docsapp"
    minSdk = 24
    targetSdk = 35
    versionCode = pkgVersionCode
    versionName = pkgVersion
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      // 文档 APP 纯展示：暂不开混淆（Compose 规则未配齐），需要时再补 proguard-rules.pro
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  sourceSets {
    getByName("main").java.srcDirs("src/main/kotlin")
  }
}

dependencies {
  implementation(project(":docsui"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)

  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material3)
}
