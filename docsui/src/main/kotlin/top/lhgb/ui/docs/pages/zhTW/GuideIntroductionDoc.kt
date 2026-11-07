package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideIntroductionDoc = DocEntry(
  id = "guide-introduction",
  title = "介紹",
  lede = "lh-ui-android 是 lh-ui 家族原生血脈的 Android 端實現：基於 Kotlin + Jetpack Compose 的 UI 元件庫。",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "家族中的位置",
      prose = "• 構建期中心：`lh-ui-core` 是原生血脈的令牌真源（`tokens/*.json`）、元件契約（`specs/`）、黃金用例（`cases/`）與 codegen 工具。它生成 Kotlin 令牌骨架（`LhTokens` / `LhDarkTokens`），不生成任何 UI 實現。\n• 本倉職責：手寫 Compose UI、主題接入、props 列舉與預設值綁定、把黃金用例翻譯為本地測試。\n• 與 web 血脈互相獨立：不依賴 `@lh-ui/share`，發版互不約束。視覺一致性靠《設計語言基線》人工對齊。",
      code = listOf(
        "                        ┌── web 血脈：share（TS core + SCSS）→ plus / vue2 / react / uniapp",
        "lh-ui 家族（同品牌）─────┤",
        "                        └── 原生血脈：core（JSON 令牌 + 契約 + codegen）→ android / ios / harmony / windows",
        "                                                                    ↑ 本倉",
      ),
    ),
    DocSection(
      title = "設計語言",
      prose = "國風主題：青花瓷藍主色（`#2B4C7C`）、宣紙白底（`#FAF6F0`）、松花綠成功、藤黃警告、朱砂危險，襯線字體棧（Noto Serif SC → STSong → SimSun）。所有視覺值都是令牌，元件不出現任何字面量。",
    ),
    DocSection(
      title = "為什麼是 Compose",
      prose = "• 平台一等公民的宣告式 UI，與 SwiftUI / ArkUI 心智同構，跨端契約容易對齊\n• 無 JS 執行時、無 HTML/CSS 橋接，滿足原生效能與嵌入式友好約束\n• `@Preview` 即文件，`@Composable` 即元件，與 web 血脈的 SFC 開發體驗對稱",
    ),
    DocSection(
      title = "當前狀態",
      prose = "• P0 全部 14 個元件已交付（button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar），每個元件都是同範式全鏈路：令牌消費 + 外觀解析 + Compose UI + JVM 單測 + Compose UI Test + `@Preview` + 文件頁。\n• 原生文件模組 `:docsui` 已交付：拖入即用（`LhDocs.launch(context)`）+ 獨立打包（`:docsapp`），預覽為真 Compose 即時渲染、可互動，見快速開始。",
    ),
  ),
)
