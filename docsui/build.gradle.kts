plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "top.lhgb.ui.docsui"
  compileSdk = 35

  defaultConfig {
    minSdk = 24
    consumerProguardFiles("consumer-rules.pro")
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
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
  // api 暴露：宿主工程需要 LhTheme 包裹 LhDocsHost（拖入即用的必要 API）
  api(project(":core"))
  implementation(project(":components:button"))
  implementation(project(":components:icon"))
  implementation(project(":components:loading"))
  implementation(project(":components:tag"))
  implementation(project(":components:cell"))
  implementation(project(":components:input"))
  implementation(project(":components:textarea"))
  implementation(project(":components:checkbox"))
  implementation(project(":components:radio"))
  implementation(project(":components:switch"))
  implementation(project(":components:dialog"))
  implementation(project(":components:toast"))
  implementation(project(":components:picker"))
  implementation(project(":components:navbar"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)

  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.ui.graphics)
  implementation(libs.compose.ui.tooling.preview)
  implementation(libs.compose.foundation)
  implementation(libs.compose.material3)
  debugImplementation(libs.compose.ui.tooling)

  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.compose.bom))
  androidTestImplementation(libs.compose.ui.test.junit4)
  debugImplementation(libs.compose.ui.test.manifest)
}
