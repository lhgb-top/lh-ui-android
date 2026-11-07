# lh-ui Android consumer ProGuard 规则（top.lhgb.ui:android 单坐标发布模块）
#
# 家族库以 Kotlin + Jetpack Compose 实现，公共混淆规则随 AAR 分发（AGP 的
# consumerProguardFiles 在消费方 minify 时自动并入，无需用户手写）。
#
# Compose 自身的 keep 规则由 androidx.compose.* 的 consumer rules 提供，
# 此处只登记库自身的特例；当前实现全 Kotlin、无反射，追加条目时在此维护。

# Keep Compose Composable 函数签名（R8 对 @Composable 的内联优化的兜底）
-keep class top.lhgb.ui.** { *; }
