// 根构建文件：仅声明插件版本，不在此应用插件（各模块自行 apply）。
// 统一 Kotlin/JVM 目标与编译参数。

plugins {
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.kotlin.compose) apply false
}
