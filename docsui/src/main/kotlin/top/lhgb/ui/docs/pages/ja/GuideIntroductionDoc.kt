package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideIntroductionDoc = DocEntry(
  id = "guide-introduction",
  title = "紹介",
  lede = "lh-ui-android は lh-ui ファミリーのネイティブ血脈における Android 端の実装です。Kotlin + Jetpack Compose ベースの UI コンポーネントライブラリです。",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "ファミリー内での位置づけ",
      prose = "• ビルド時の中心: `lh-ui-core` はネイティブ血脈のトークン真源（`tokens/*.json`）、コンポーネント契約（`specs/`）、ゴールデンケース（`cases/`）、codegen ツールです。Kotlin のトークンスケルトン（`LhTokens` / `LhDarkTokens`）を生成しますが、UI 実装は一切生成しません。\n• 本リポジトリの役割: Compose UI の手書き、テーマの接続、プロパティの列挙型とデフォルト値のバインディング、ゴールデンケースのローカルテストへの翻訳。\n• Web 血脈とは互いに独立: `@lh-ui/share` に依存せず、リリース順序の制約もありません。視覚的な一致性は『デザイン言語基線』への手作業の合わせこみで保ちます。",
      code = listOf(
        "                        ┌── Web 血脈：share（TS core + SCSS）→ plus / vue2 / react / uniapp",
        "lh-ui ファミリー（同一ブランド）─────┤",
        "                        └── ネイティブ血脈：core（JSON トークン + 契約 + codegen）→ android / ios / harmony / windows",
        "                                                                    ↑ 本リポジトリ",
      ),
    ),
    DocSection(
      title = "デザイン言語",
      prose = "中華風テーマ: 青花瓷ブルーのメインカラー（`#2B4C7C`）、宣紙ホワイトの背景（`#FAF6F0`）、成功は松花绿（しょうっかりょく）緑、警告は藤黄、危険は朱砂、セリフフォントスタック（Noto Serif SC → STSong → SimSun）。すべての視覚値がトークンであり、コンポーネントの中にリテラルは一切現れません。",
    ),
    DocSection(
      title = "なぜ Compose か",
      prose = "• プラットフォーム第一級の宣言的 UI であり、SwiftUI / ArkUI とメンタルモデルが同形なので、跨端契約を合わせやすい\n• JS ランタイムも HTML/CSS ブリッジもなく、ネイティブの性能と組み込み友好性の要件を満たす\n• `@Preview` がすなわちドキュメント、`@Composable` がすなわちコンポーネントであり、Web 血脈の SFC 開発体験と対称",
    ),
    DocSection(
      title = "現在の状態",
      prose = "• P0 の全 14 コンポーネントを交付済み（button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar）。各コンポーネントは同一パラダイムの全链路です: トークン消費 + 外観解析 + Compose UI + JVM 単体テスト + Compose UI Test + `@Preview` + ドキュメントページ。\n• ネイティブドキュメントモジュール `:docsui` を交付済み: ドラッグするだけですぐ使え（`LhDocs.launch(context)`）、単独パッケージング（`:docsapp`）も可能。プレビューは真の Compose リアルタイムレンダリングでインタラクティブです。詳しくはクイックスタートを参照してください。",
    ),
  ),
)
