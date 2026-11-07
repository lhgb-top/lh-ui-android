// buildSrc：lh-ui android 家族的构建期约定插件工程
//
// 目前只含 lhui.publishing —— Maven 发布约定插件（见 src/main/kotlin/lhui.publishing.gradle.kts）。
// 以 Gradle 内置 Kotlin 编译（kotlin-dsl）；AGP 以 compileOnly 引入，
// 类型访问 com.android.build.gradle.LibraryExtension，实际插件版本由各模块的 plugins 块决定。

plugins {
  `kotlin-dsl`
}

repositories {
  google()
  mavenCentral()
}

// 不依赖 AGP：发布插件只用 Gradle core API（components / publishing / signing），
// Android library 的 singleVariant 配置由各模块的 android 块自行声明，
// 避免插件 classpath 与主构建的 AGP 插件解析冲突。

gradlePlugin {
  plugins {
    register("lhuiPublishing") {
      id = "lhui.publishing"
      implementationClass = "LhuiPublishingPlugin"
    }
  }
}
