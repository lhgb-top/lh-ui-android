# クイックスタート

## 最小サンプル

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* クリックを処理 */ },
    ) {
      Text("プライマリボタン")
    }
  }
}
```

`LhTheme` はテーマコンテナで、内部のすべての Lh コンポーネントが自動的にトークンを消費します。ラップしない場合はライトカラーパレットにフォールバックします。

## タイプとサイズ

```kotlin
// 6 種類のタイプ
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("デフォルト") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("プライマリ") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("成功") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("警告") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("危険") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("情報") }

// 3 種類のサイズ
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("小") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("中") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("大") }
```

## 状態

```kotlin
// 無効: クリックに応答せず、無効状態のスタイルを表示
LhButton(disabled = true, lhOnClick = {}) { Text("無効") }

// 読み込み中: インジケーターを表示し、lhOnClick を発火しない
LhButton(loading = true, lhOnClick = {}) { Text("読み込み中") }

// 両方指定: 無効が優先される（ゴールデンケース disabled-precedes-loading）
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("無効が優先") }
```

## アイコンと紋様背景

```kotlin
// アイコン名はローカルのアイコン体系で解決される（LhButtonIcons を参照、未登録名は描画されない）
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("検索") }

// 中華風の紋様背景（color.decoration トークンを消費）
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("紋様背景") }
```

## ライト / ダークテーマ

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // 内部の全コンポーネントがダークトークンに自動切替（core が事前計算した値、ランタイム演算なし）
}
```

## ネイティブドキュメントモジュール

本ウェブドキュメントとは別に、リポジトリ内に**Android ネイティブのドキュメントモジュール**（`:docsui`）があります。ドキュメントページは本サイトの構造と 1:1 で一致しますが、プレビューは**真の Compose リアルタイムレンダリングでインタラクティブ**（ボタンはクリック可、入力はタイピング可、ダイアログは開ける）であり、静的 HTML のシミュレーションではありません。

**ドラッグするだけですぐ使える**: 任意の Android プロジェクトが依存すれば、`LhDocs.launch(context)` 一行で完全なドキュメント（14 コンポーネントページ + 5 ガイドページ + ライト/ダーク切替 + リアルタイムプレビュー）を開けます:

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // または Maven 座標 top.lhgb.ui:docsui

// 任意のエントリ（例: 「コンポーネントドキュメント」ボタンのクリックコールバック）
LhDocs.launch(context)
```

ライブラリの manifest が `exported = false` の `LhDocsActivity` を自動的にマージします。ホストアプリにランチャーアイコンは注入しません。

**単独パッケージング**: `:docsapp` はごく薄い application モジュール（Activity を一つ持つだけ）で、単独でパッケージングすれば「lh-ui ドキュメント」APP が完成します:

```bash
./gradlew :docsapp:installDebug
```

## コンポーネントサンプルのプレビュー

ネイティブ UI はブラウザでリアルタイムレンダリングできないため、本サイトでは**インラインの静的 HTML 携帯フレームシミュレーター**で外観を示します:

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">デフォルトボタン</button>
      <button class="sim-btn sim-btn--primary">プライマリボタン</button>
      <button class="sim-btn sim-btn--success">成功</button>
      <button class="sim-btn sim-btn--warning">警告</button>
      <button class="sim-btn sim-btn--danger">危険</button>
      <button class="sim-btn sim-btn--info">情報</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">小</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">大</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>無効</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>読み込み中
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>検索</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">紋様背景</button>
    </div>
  </div>
</div>
<p class="sim-note">Android（Compose）見た目の HTML シミュレーションプレビューであり、実機レンダリングではありません。スタイル値はすべて core のトークンスナップショットから取っており、実機と同じソース・同じ値です。実機の外観は <code>@Preview</code> とデバイスのスクリーンショットを基準にしてください。</p>

> シミュレーターのダーク表示はサイトと連動します: 右上のテーマを切り替えると、携帯フレーム内の色が即座に変わります（ダークトークンは core が事前計算）。
