# 紹介

**lh-ui-android** は lh-ui ファミリーの**ネイティブ血脈**における Android 端の実装です。**Kotlin + Jetpack Compose** ベースの UI コンポーネントライブラリです。

## ファミリー内での位置づけ

```
                        ┌── Web 血脈：share（TS core + SCSS）→ plus / vue2 / react / uniapp
lh-ui ファミリー（同一ブランド）─────┤
                        └── ネイティブ血脈：core（JSON トークン + 契約 + codegen）→ android / ios / harmony / windows
                                                                    ↑ 本リポジトリ
```

- **ビルド時の中心**: [`lh-ui-core`](https://www.lhgb.top) はネイティブ血脈のトークン真源（`tokens/*.json`）、コンポーネント契約（`specs/`）、ゴールデンケース（`cases/`）、codegen ツールです。Kotlin のトークンスケルトン（`LhTokens` / `LhDarkTokens`）を生成しますが、**UI 実装は一切生成しません**。
- **本リポジトリの役割**: Compose UI の手書き、テーマの接続、プロパティの列挙型とデフォルト値のバインディング、ゴールデンケースのローカルテストへの翻訳。
- **Web 血脈とは互いに独立**: `@lh-ui/share` に依存せず、リリース順序の制約もありません。視覚的な一致性は『デザイン言語基線』への手作業の合わせこみで保ちます。

## デザイン言語

中華風テーマ: 青花瓷ブルーのメインカラー（`#2B4C7C`）、宣紙ホワイトの背景（`#FAF6F0`）、成功は松花绿（しょうっかりょく）緑、警告は藤黄、危険は朱砂、セリフフォントスタック（Noto Serif SC → STSong → SimSun）。すべての視覚値がトークンであり、コンポーネントの中にリテラルは一切現れません。

## なぜ Compose か

- プラットフォーム第一級の宣言的 UI であり、SwiftUI / ArkUI とメンタルモデルが同形なので、跨端契約を合わせやすい
- JS ランタイムも HTML/CSS ブリッジもなく、ネイティブの性能と組み込み友好性の要件を満たす
- `@Preview` がすなわちドキュメント、`@Composable` がすなわちコンポーネントであり、Web 血脈の SFC 開発体験と対称

## 現在の状態

- **P0 の全 14 コンポーネントを交付済み**（button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar）。各コンポーネントは同一パラダイムの全链路です: トークン消費 + 外観解析 + Compose UI + JVM 単体テスト + Compose UI Test + `@Preview` + ドキュメントページ。
- **ネイティブドキュメントモジュール `:docsui` を交付済み**: ドラッグするだけですぐ使え（`LhDocs.launch(context)`）、単独パッケージング（`:docsapp`）も可能。プレビューは真の Compose リアルタイムレンダリングでインタラクティブです。詳しくは[クイックスタート](./quickstart)を参照してください。
