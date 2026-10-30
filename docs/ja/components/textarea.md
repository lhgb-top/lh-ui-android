# Textarea テキストエリア

コントロールされる複数行入力フィールドです。行数、文字数カウント、最大長をサポートします。

> 契約元: [`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> ゴールデンケース: [`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea)（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## 基本的な使い方

最小の高さ = 行数 × 行の高さ × フォントサイズで、超過すると縦方向にスクロールします。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="複数行の内容を入力してください" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">1 行目のテキスト
2 行目
3 行目</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## 文字数カウント

`showCount` が true のとき、右下にカウントが描画されます（`textarea.countFontSize`、色は `text.secondary`）。`maxlength` が入力を切り詰めます。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea カウント</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">日本語</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません。実機の右下には「3 / 20」のカウントが表示されます</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `value` | コントロール値（必須） | `String` | — |
| `onValueChange` | 値変更コールバック（必須） | `(String) -> Unit` | — |
| `placeholder` | プレースホルダーのテキスト | `String?` | `null` |
| `rows` | 行数（最小の高さを決定） | `Int` | `3` |
| `disabled` | 無効かどうか | `Boolean` | `false` |
| `readonly` | 読み取り専用かどうか | `Boolean` | `false` |
| `showCount` | 文字数カウントを表示するかどうか | `Boolean` | `false` |
| `maxlength` | 最大長（超過分は切り詰め） | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | フォーカス / フォーカス外れのコールバック | `(() -> Unit)?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

## トークン对照

| トークン | 用途 |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | パディング（`spacing.md`） |
| `textarea.fontSize` | フォントサイズ（`typography.fontSize.base`） |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | 角丸 / 枠線幅 / フォーカス枠線幅 |
| `textarea.countFontSize` | カウントのフォントサイズ（`typography.fontSize.extraSmall` |
| `textarea.minHeight` | 最小の高さの基準値 |
| `typography.lineHeight.primary` | 行の高さ（行数からの換算） |
| `color.text.{primary,secondary,placeholder}` | テキスト / カウント / プレースホルダーの色 |
| `color.focus` / `color.border.base` | フォーカス / デフォルトの枠線色 |
| `color.disabled.{bg,text,border}` | 無効状態の三色 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `input-emits-onvaluechange` | 入力が onValueChange を発火 | `LhTextareaTest.input_emits_onValueChange`（Compose UI Test） |
| `disabled-blocks-input` | 無効状態では入力できない | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | カウントが「n / max」を描画 | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | maxlength がない場合は長さのみ描画 | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | フォーカスがフォーカストークンを消費 | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | 超過した入力は切り詰められる | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # JVM 部分
./gradlew :components:textarea:connectedAndroidTest           # インタラクション部分（デバイスが必要）
```
