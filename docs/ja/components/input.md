# Input 入力フィールド

コントロールされる単一行入力フィールドです。サイズ、無効化、読み取り専用、クリア、パスワードのマスク、最大長をサポートします。

> 契約元: [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> ゴールデンケース: [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input)（8 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## 基本的な使い方

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="内容を入力してください" /></div>
    <div class="sim-row"><input class="sim-input" value="入力済みのテキスト" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="無効状態" disabled /></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "内容を入力してください",
  onValueChange = { value = it },
)
```

## サイズとフォーカス

3 段階のサイズが `input.height` / `input.paddingHorizontal` / `input.fontSize` トークンに対応します。フォーカス時に枠線は `color.focus` を取り、`input.focusRingWidth` まで太くなります。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium（フォーカス状態のデモ）" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `value` | コントロール値（必須） | `String` | — |
| `onValueChange` | 値変更コールバック（必須） | `(String) -> Unit` | — |
| `placeholder` | プレースホルダーのテキスト | `String?` | `null` |
| `type` | 入力タイプ（平文 / パスワード） | `LhInputType` | `Text` |
| `lhSize` | サイズ | `LhInputSize` | `Medium` |
| `disabled` | 無効かどうか | `Boolean` | `false` |
| `readonly` | 読み取り専用かどうか（編集不可だが無効状態のスタイルにはならない） | `Boolean` | `false` |
| `clearable` | クリアボタンを表示するかどうか | `Boolean` | `false` |
| `maxlength` | 最大長（超過分は切り詰め） | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | フォーカス / フォーカス外れのコールバック | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | 先頭 / 末尾のスロット | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

### LhInputSize

| 値 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small`（32dp） | `input.paddingHorizontal.small`（12dp） | `input.fontSize.small`（13sp） |
| `Medium` | `input.height.medium`（40dp） | `input.paddingHorizontal.medium`（16dp） | `input.fontSize.medium`（14sp） |
| `Large` | `input.height.large`（48dp） | `input.paddingHorizontal.large`（20dp） | `input.fontSize.large`（16sp） |

## トークン对照

| トークン | 用途 |
| --- | --- |
| `input.height.{small,medium,large}` | 3 段階の高さ |
| `input.paddingHorizontal.{small,medium,large}` | 3 段階の横向きパディング |
| `input.fontSize.{small,medium,large}` | 3 段階のフォントサイズ |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | 角丸 / 枠線幅 / フォーカス枠線幅 |
| `input.clearIconSize` | クリアアイコンのサイズ |
| `color.focus` | フォーカス枠線色 |
| `color.text.placeholder` | プレースホルダーのテキスト色 |
| `color.border.base` | デフォルトの枠線色 |
| `color.disabled.{bg,text,border}` | 無効状態の三色 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `input-emits-onvaluechange` | 入力が onValueChange を発火 | `LhInputTest.input_emits_onValueChange`（Compose UI Test） |
| `clearable-emits-empty-value` | クリアボタンが空の値を発火 | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | 無効状態では入力できない | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | 読み取り専用では入力できない | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | プレースホルダー色がプレースホルダートークンを取る | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | フォーカスがフォーカストークンを消費（フォーカス外れは中間色の枠線を取る） | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | small が small サイズトークンを消費 | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | 超過した入力は切り詰められる | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # JVM 部分
./gradlew :components:input:connectedAndroidTest              # インタラクション部分（デバイスが必要）
```
