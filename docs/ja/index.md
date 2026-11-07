---
layout: home

hero:
  image:
    src: /logo-banner.png
    alt: lh-ui 品牌横幅
  name: lh-ui-android
  text: ネイティブ血脈 · Android 端
  tagline: Kotlin + Jetpack Compose コンポーネントライブラリ、トークン駆動・契約アラインメント・ゴールデンケースによる保護
  actions:
    - theme: brand
      text: クイックスタート
      link: /ja/guide/quickstart
    - theme: alt
      text: コンポーネント一覧
      link: /ja/components/button

features:
  - title: トークン駆動
    details: コンポーネント内に色や間隔のハードコードは一切なく、core の codegen が生成した LhTokens / LhDarkTokens をすべて消費します。トークンを変更すればアプリ全体に即時反映されます。
  - title: 契約の一致
    details: プロパティの命名が lh-ui-core/specs と文字通り一致し、iOS / HarmonyOS / Windows および Web 血脈と同じ振る舞いを共有します。
  - title: ゴールデンケースによる保護
    details: cases/button.json の振る舞いアサーションを JVM JUnit と Compose UI Test に翻訳し、CI が同一のアサーション群を実行します。
  - title: ライト / ダーク両テーマ
    details: LhTheme 一行で切り替え。ダークトークンは core が事前計算するため、ランタイムの演算もちらつきもありません。
  - title: 中華風デザイン言語
    details: 青花瓷（せいがじ）ブルーのメインカラー、宣紙（せんし）ホワイトの背景、藤黄（とうおう）と朱砂（しゅしゃ）のアクセント、セリフフォントスタック。Web 血脈と同じソース・同じ値です。
  - title: Compose ネイティブ
    details: 純 Kotlin + Compose で手書きされた UI。JS ランタイムも HTML/CSS ブリッジもない、プラットフォーム第一級の宣言的 UI です。
---
