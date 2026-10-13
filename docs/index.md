---
layout: home

hero:
  name: lh-ui-android
  text: 原生血脉 · Android 端
  tagline: Kotlin + Jetpack Compose 组件库，令牌驱动、契约对齐、黄金用例守护
  actions:
    - theme: brand
      text: 快速开始
      link: /guide/quickstart
    - theme: alt
      text: 组件总览
      link: /components/button

features:
  - title: 令牌驱动
    details: 组件零硬编码色值与间距，全部消费 core codegen 生成的 LhTokens / LhDarkTokens，改令牌全端即时生效。
  - title: 契约一致
    details: props 命名与 lh-ui-core/specs 逐字对齐，与 iOS / 鸿蒙 / Windows 及 web 血脉行为共享。
  - title: 黄金用例守护
    details: cases/button.json 的行为断言翻译为 JVM JUnit 与 Compose UI Test，CI 跑同一批断言。
  - title: 明暗双主题
    details: LhTheme 一行切换，暗色令牌由 core 预计算，无运行时调色、无闪烁。
  - title: 国风设计语言
    details: 青花瓷蓝主色、宣纸白底、藤黄朱砂点缀，衬线字体栈，与 web 血脉同源同值。
  - title: Compose 原生
    details: 纯 Kotlin + Compose 手写 UI，无 JS 运行时，无 HTML/CSS 桥接，一等公民的声明式 UI。
---
