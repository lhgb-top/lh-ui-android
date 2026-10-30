# Introduction

**lh-ui-android** is the Android implementation of the **native bloodline** of the lh-ui family: a UI component library built on **Kotlin + Jetpack Compose**.

## Position in the family

```
                        ┌── web bloodline: share (TS core + SCSS) → plus / vue2 / react / uniapp
lh-ui family (one brand)├──
                        └── native bloodline: core (JSON tokens + specs + codegen) → android / ios / harmony / windows
                                                                    ↑ this repo
```

- **Build-time center**: [`lh-ui-core`](https://www.lhgb.top) is the token source of truth (`tokens/*.json`), the component specs (`specs/`), the golden cases (`cases/`), and the codegen tooling of the native bloodline. It generates the Kotlin token skeletons (`LhTokens` / `LhDarkTokens`) and **generates no UI implementation at all**.
- **What this repo does**: hand-written Compose UI, theme integration, prop enums and default-value bindings, and translating the golden cases into local tests.
- **Independent from the web bloodline**: it does not depend on `@lh-ui/share`, and the two bloodlines place no release ordering constraints on each other. Visual consistency is kept by manually aligning against the Design Language Baseline.

## Design language

Chinese-style theme: 青花瓷 (ceramic blue) primary color (`#2B4C7C`), 宣纸白 (rice-paper white) background (`#FAF6F0`), 松花绿 (pine-flower green) for success, 藤黄 (gamboge) for warning, 朱砂 (cinnabar) for danger, and a serif font stack (Noto Serif SC → STSong → SimSun). Every visual value is a token — no literals appear inside components.

## Why Compose

- Declarative UI as a first-class citizen of the platform, mentally isomorphic to SwiftUI / ArkUI, which makes cross-platform spec alignment easy.
- No JS runtime and no HTML/CSS bridging — meets native-performance and embedded-friendly constraints.
- `@Preview` is the documentation, `@Composable` is the component — symmetric with the SFC development experience of the web bloodline.

## Current status

- **All 14 P0 components delivered** (button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar). Each component follows the same full-chain paradigm: token consumption + appearance resolution + Compose UI + JVM unit tests + Compose UI Test + `@Preview` + a docs page.
- **The native docs module `:docsui` is delivered**: drag-and-drop ready (`LhDocs.launch(context)`) plus a standalone build (`:docsapp`). Previews are real, interactive, live-rendered Compose — see [Getting started](./quickstart).
