// lhui：家族单坐标发布模块（D22）。
//
// 这是一个「聚合源码」的空壳 library 模块：main 源集直接汇聚 :core 与全部
// :components:* 的 src/main/kotlin（组件都只有 kotlin 源、零 res、零 R 引用，
// 聚合后单一 namespace 无冲突），发布为唯一坐标 top.lhgb.ui:android:<LHUI_VERSION>。
// POM 因此只列外部依赖（Compose BOM / androidx），不含任何内部 project 坐标——
// 与 docs/发包流程.md §5.3「components 并入主库、POM 依赖改写到主坐标」等价且更彻底。
//
// 开发态不变：:docsapp / :docsui 继续依赖原模块，dev / 测试 / 演示 APP 走原结构；
// 本模块仅供发布（及发布前的本地验证 publishToMavenLocal）。

plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
}

extra["lhuiArtifactId"] = "android"

android {
  namespace = "top.lhgb.ui.lhui"
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

  // 发布产物形态：release 单变体 + sources / javadoc jar（Maven Central 硬性要求）
  publishing {
    singleVariant("release") {
      withSourcesJar()
      withJavadocJar()
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  // 聚合源集：core + 全部组件（列表与 settings.gradle.kts 的 include 一一对应）。
  // core 的 res（多语言 lh_* strings）一并并入：组件源码引用 top.lhgb.ui.core.R，
  // 聚合后由 lhui 命名空间生成实际 R，再经 lhui/src/main/kotlin 里的桥接垫片
  // （top/lhgb/ui/core/R.kt）把 top.lhgb.ui.core.R 映射过去。
  sourceSets {
    getByName("main").kotlin.srcDirs(
      "../core/src/main/kotlin",
      "../components/button/src/main/kotlin",
      "../components/icon/src/main/kotlin",
      "../components/loading/src/main/kotlin",
      "../components/tag/src/main/kotlin",
      "../components/text/src/main/kotlin",
      "../components/cell/src/main/kotlin",
      "../components/input/src/main/kotlin",
      "../components/textarea/src/main/kotlin",
      "../components/checkbox/src/main/kotlin",
      "../components/radio/src/main/kotlin",
      "../components/switch/src/main/kotlin",
      "../components/dialog/src/main/kotlin",
      "../components/toast/src/main/kotlin",
      "../components/picker/src/main/kotlin",
      "../components/navbar/src/main/kotlin",
    )
    getByName("main").res.srcDirs("../core/src/main/res")
  }
}

// 外部依赖取全部组件的并集，用 api 让消费方编译期可见（发布 POM 列 compile scope）
dependencies {
  api(libs.androidx.core.ktx)
  api(libs.androidx.lifecycle.runtime.ktx)
  api(platform(libs.compose.bom))
  api(libs.compose.ui)
  api(libs.compose.ui.graphics)
  api(libs.compose.ui.tooling.preview)
  api(libs.compose.foundation)
  api(libs.compose.material3)
  api(libs.compose.material.icons.extended)
}

// 在脚本末尾应用发布插件：plugins 块解析早于 extra 属性赋值，
// 此时 extra["lhuiArtifactId"] 已就绪（插件 apply 时读取）
apply(plugin = "lhui.publishing")
