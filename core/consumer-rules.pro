# lh-ui Android core 消费者混淆规则
#
# core 模块（主题接入层 LhTheme + 令牌对象）全 Kotlin、无反射，
# 公共混淆规则随单坐标主库（top.lhgb.ui:android）的 consumer-rules 分发；
# 此文件满足 core/build.gradle.kts 的 consumerProguardFiles 引用，
# 追加条目时在此维护。
