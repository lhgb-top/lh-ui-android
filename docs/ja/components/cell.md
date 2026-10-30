# Cell セル

リストのセルです。アイコン / タイトル + 説明 / 右側の値 + 矢印からなる三段レイアウトで、リストと組み合わせてよく使います。

> 契約元: [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> ゴールデンケース: [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell)（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## 基本的な使い方

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">タイトルテキスト</div>
      <div class="sim-cell__value">右側の値</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">説明付きタイトル</div>
        <div class="sim-cell__label">説明テキスト</div>
      </div>
      <div class="sim-cell__value">値</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>必須項目</div>
      <div class="sim-cell__value">詳細</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
LhCell(
  title = "説明付きタイトル",
  label = "説明テキスト",
  value = "値",
  isLink = true,
  lhOnClick = { /* 遷移 */ },
)
```

## 必須とリンク

`required` はタイトルの前に赤いアスタリスクを描画します（`color.danger.default`）。`isLink` は末尾に右矢印アイコンを描画します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell バリエーション</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>必須</div>
      <div class="sim-cell__value">矢印 →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">ボーダーなし</div>
      <div class="sim-cell__value">値</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `title` | タイトル（メインテキスト） | `String?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |
| `value` | 右側の値（サブテキスト、右寄せ） | `String?` | `null` |
| `label` | タイトルの下の説明テキスト | `String?` | `null` |
| `bordered` | 底部の区切り線を描画するかどうか（`color.border.lighter`） | `Boolean` | `true` |
| `clickable` | クリック可能かどうか。true の場合は右側に矢印インジケーターを表示し、クリックに応答する | `Boolean` | `false` |
| `required` | タイトルの前に必須の赤いアスタリスクを表示するかどうか（`color.danger.default`） | `Boolean` | `false` |
| `icon` | 左側のアイコン名（ローカルのアイコン体系）。空の場合は描画しない | `String?` | `null` |
| `lhOnClick` | クリックコールバック。`clickable` が false の場合は発火しない | `(() -> Unit)?` | `null` |
| `titleContent` | タイトル内容（title を上書き、spec の default スロット） | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | 右側の値の内容（value を上書き、spec の value スロット） | `@Composable (() -> Unit)?` | `null` |

`clickable` は「右側の矢印インジケーターを描画するかどうか」と「行全体がクリックに応答するか」を同時に制御します。クリックが発火するのは `clickable = true` かつ `lhOnClick` が非空の場合のみです。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `cell.paddingVertical` | 縦向きパディング（`spacing.md` の参照） |
| `cell.paddingHorizontal` | 横向きパディング（`spacing.md`） |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | タイトル / 説明 / 値のフォントサイズ |
| `cell.gap` | アイコンとタイトルの間隔（`spacing.xs`） |
| `icon.size.medium` | 左側のアイコンと矢印インジケーターのサイズ |
| `cell.borderWidth` | 底部区切り線の幅 |
| `typography.lineHeight.primary` | テキストの行の高さ |
| `color.text.{primary,secondary}` | タイトル / 説明 / 値の色 |
| `color.danger.default` | 必須の赤いアスタリスク |
| `color.border.lighter` | 底部区切り線 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `title-and-value-render` | タイトルと右側の値がテキストを描画 | `LhCellTest.title_and_value_render`（Compose UI Test） |
| `label-renders-secondary-tone` | label はセカンダリテキスト色のトークンを使用 | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | 必須マークは危険色のトークンを使用 | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | 区切り線はボーダートークンを消費 | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | クリックが onClick を発火 | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | 無効状態は発火しない | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # JVM 部分
./gradlew :components:cell:connectedAndroidTest              # インタラクション部分（デバイスが必要）
```
