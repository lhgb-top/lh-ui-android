plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

android {
  namespace = "top.lhgb.ui.core"
  compileSdk = 35

  defaultConfig {
    minSdk = 24
    consumerProguardFiles("consumer-rules.pro")
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
}

// 令牌源集：com/lh/ui/tokens/ 由 scripts/link-tokens.mjs 从 ../lh-ui-core/dist/android
// 同步（生成物，gitignore）。家族布局不存在时回退 Maven 令牌包（nM6 打通后）。
sourceSets {
  getByName("main").java.srcDirs("src/main/kotlin")
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(platform(libs.compose.bom))
  implementation(libs.compose.ui)
  implementation(libs.compose.ui.graphics)
  implementation(libs.compose.ui.tooling.preview)
  implementation(libs.compose.foundation)
  debugImplementation(libs.compose.ui.tooling)
}
