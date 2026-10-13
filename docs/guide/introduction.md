# 介绍

**lh-ui-android** 是 lh-ui 家族**原生血脉**的 Android 端实现：基于 **Kotlin + Jetpack Compose** 的 UI 组件库。

## 家族中的位置

```
                        ┌── web 血脉：share（TS core + SCSS）→ plus / vue2 / react / uniapp
lh-ui 家族（同品牌）─────┤
                        └── 原生血脉：core（JSON 令牌 + 契约 + codegen）→ android / ios / harmony / windows
                                                                    ↑ 本仓
```

- **构建期中心**：[`lh-ui-core`](https://www.lhgb.top) 是原生血脉的令牌真源（`tokens/*.json`）、组件契约（`specs/`）、黄金用例（`cases/`）与 codegen 工具。它生成 Kotlin 令牌骨架（`LhTokens` / `LhDarkTokens`），**不生成任何 UI 实现**。
- **本仓职责**：手写 Compose UI、主题接入、props 枚举与默认值绑定、把黄金用例翻译为本地测试。
- **与 web 血脉互相独立**：不依赖 `@lh-ui/share`，发版互不约束。视觉一致性靠《设计语言基线》人工对齐。

## 设计语言

国风主题：青花瓷蓝主色（`#2B4C7C`）、宣纸白底（`#FAF6F0`）、松花绿成功、藤黄警告、朱砂危险，衬线字体栈（Noto Serif SC → STSong → SimSun）。所有视觉值都是令牌，组件不出现任何字面量。

## 为什么是 Compose

- 平台一等公民的声明式 UI，与 SwiftUI / ArkUI 心智同构，跨端契约容易对齐
- 无 JS 运行时、无 HTML/CSS 桥接，满足原生性能与嵌入式友好约束
- `@Preview` 即文档，`@Composable` 即组件，与 web 血脉的 SFC 开发体验对称

## 当前状态

- **P0 全部 14 个组件已交付**（button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar），每个组件都是同范式全链路：令牌消费 + 外观解析 + Compose UI + JVM 单测 + Compose UI Test + `@Preview` + 文档页。
- **原生文档模块 `:docsui` 已交付**：拖入即用（`LhDocs.launch(context)`）+ 独立打包（`:docsapp`），预览为真 Compose 实时渲染、可交互，见[快速开始](./quickstart)。
