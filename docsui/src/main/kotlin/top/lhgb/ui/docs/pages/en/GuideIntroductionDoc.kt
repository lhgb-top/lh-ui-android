package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideIntroductionDoc = DocEntry(
  id = "guide-introduction",
  title = "Introduction",
  lede = "lh-ui-android is the Android implementation of the native bloodline of the lh-ui family: a UI component library built on Kotlin + Jetpack Compose.",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Position in the family",
      prose = "• Build-time center: `lh-ui-core` is the token source of truth (`tokens/*.json`), the component specs (`specs/`), the golden cases (`cases/`), and the codegen tooling of the native bloodline. It generates the Kotlin token skeletons (`LhTokens` / `LhDarkTokens`) and generates no UI implementation at all.\n• What this repo does: hand-written Compose UI, theme integration, prop enums and default-value bindings, and translating the golden cases into local tests.\n• Independent from the web bloodline: it does not depend on `@lh-ui/share`, and the two bloodlines place no release ordering constraints on each other. Visual consistency is kept by manually aligning against the Design Language Baseline.",
      code = listOf(
        "                        ┌── web bloodline: share (TS core + SCSS) → plus / vue2 / react / uniapp",
        "lh-ui family (one brand)├──",
        "                        └── native bloodline: core (JSON tokens + specs + codegen) → android / ios / harmony / windows",
        "                                                                    ↑ this repo",
      ),
    ),
    DocSection(
      title = "Design language",
      prose = "Chinese-style theme: 青花瓷 (ceramic blue) primary color (`#2B4C7C`), 宣纸白 (rice-paper white) background (`#FAF6F0`), 松花绿 (pine-flower green) for success, 藤黄 (gamboge) for warning, 朱砂 (cinnabar) for danger, and a serif font stack (Noto Serif SC → STSong → SimSun). Every visual value is a token — no literals appear inside components.",
    ),
    DocSection(
      title = "Why Compose",
      prose = "• Declarative UI as a first-class citizen of the platform, mentally isomorphic to SwiftUI / ArkUI, which makes cross-platform spec alignment easy.\n• No JS runtime and no HTML/CSS bridging — meets native-performance and embedded-friendly constraints.\n• `@Preview` is the documentation, `@Composable` is the component — symmetric with the SFC development experience of the web bloodline.",
    ),
    DocSection(
      title = "Current status",
      prose = "• All 14 P0 components delivered (button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar). Each component follows the same full-chain paradigm: token consumption + appearance resolution + Compose UI + JVM unit tests + Compose UI Test + `@Preview` + a docs page.\n• The native docs module `:docsui` is delivered: drag-and-drop ready (`LhDocs.launch(context)`) plus a standalone build (`:docsapp`). Previews are real, interactive, live-rendered Compose — see Getting started.",
    ),
  ),
)
