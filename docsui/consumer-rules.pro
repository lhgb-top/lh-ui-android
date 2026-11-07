# lh-ui Android docsui 消费者混淆规则
#
# 文档模块全 Kotlin + Jetpack Compose，无反射；Compose 自身的 keep 规则由
# androidx.compose.* 的 consumer rules 提供，LhDocsActivity 由宿主 manifest
# 合并后 AGP 自动生成 keep。此文件满足 docsui/build.gradle.kts 的
# consumerProguardFiles 引用（宿主 release minify 时缺此文件会构建失败），
# 追加条目时在此维护。
