# Picker ピッカー

単一列のボトムホイールピッカーです。スクロール停止後に中央のインジケーターに最も近い項目が選択されます。

> 契約元: [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> ゴールデンケース: [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker)（8 件、クランプとトークンマッピングは JVM 単体テストに、スクロールとボタン操作は Compose UI Test に翻訳済み）

## 使い方

ボトムシートとして表示されます。上にマスク領域、下にパネル（上部は角丸）が配置されます。ツールバーは「キャンセル / タイトル / 確認」の 3 段構成です。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">キャンセル</button>
        <div class="sim-picker__toolbar-title">選択してください</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">確認</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">選択肢 1</div>
        <div class="sim-picker__item sim-picker__item--selected">選択肢 2</div>
        <div class="sim-picker__item">選択肢 3</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("選択肢 1", "選択肢 2", "選択肢 3")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "選択してください",
)
```

## 選択の仕組み

ホイールはスクロール可能なリストで、各項目の高さは `picker.itemHeight` です。中央のインジケーターは 2 本の水平線で、その間隔は itemHeight に等しくなります。
スクロール停止後、スクロールした距離を四捨五入して選択インデックスを求め、変化があったときに `onValueChange` を発火します。
`value` が範囲外のときは `LhPickerDefaults.clamp` によって候補範囲内にクランプされます。

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `visible` | 表示するかどうか。false の場合は何も描画しない | `Boolean` | — |
| `options` | 候補項目のテキスト配列（単一列） | `List<String>` | — |
| `onValueChange` | ホイールが停止し選択項目が変化したときに発火。新しいインデックスを伴う | `(Int) -> Unit` | — |
| `onConfirm` | 確認ボタンをタップしたときに発火。現在の選択インデックスを伴う | `(Int) -> Unit` | — |
| `onCancel` | キャンセルボタンをタップしたときに発火 | `() -> Unit` | — |
| `onClose` | 確認 / キャンセル後に発火。ホストはこれに従って visible を false にする | `() -> Unit` | — |
| `value` | 選択項目のインデックス（0 基点）。範囲外のときはクランプ | `Int` | `0` |
| `title` | ツールバー中央のタイトル | `String?` | `null` |
| `confirmText` | 確認ボタンのテキスト | `String` | `"确认"` |
| `cancelText` | キャンセルボタンのテキスト | `String` | `"取消"` |
| `content` | ホイールの内容領域（デフォルトの候補レンダリングを上書き、spec の default スロット） | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | ツールバー（デフォルトの確認 / タイトル / キャンセルを上書き、spec の toolbar スロット） | `@Composable (() -> Unit)?` | `null` |
| `listState` | ホイールのリスト状態（デフォルトは `rememberLhPickerListState(value, options)` が提供。テスト時に外部から注入可能） | `LazyListState` | 左記の通り |
| `modifier` | 修飾子（パネルに作用） | `Modifier` | `Modifier` |

テスト用フック: `rememberLhPickerListState(value, options)` は `value` の位置に初期配置された `LazyListState` を返します。
インストルメンテーションテストでは `listState.dispatchRawDelta(...)` を使って決定的にスクロールを駆動できます。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `picker.height` | パネルの高さ（260dp） |
| `picker.itemHeight` | 各項目の高さ（40dp、中央インジケーターの間隔でもある） |
| `picker.toolbarHeight` | ツールバーの高さ（44dp） |
| `picker.itemFontSize` | 候補項目のフォントサイズ |
| `picker.borderRadius` | パネル上部の角丸 |
| `picker.paddingHorizontal` | パネルの横向きパディング（`spacing.md` に追従） |
| `zIndex.modal` / `zIndex.modalBackdrop` | 階層のセマンティックアラインメント（Compose `Popup` がウィンドウレベルの階層を内包） |
| `color.background.overlay` | マスク色 |
| `color.background.default` | パネルの背景色 |
| `color.text.primary` / `color.text.regular` | タイトル / 候補項目の色 |
| `color.border.lighter` | 中央インジケーターの線色 |
| `color.primary.default` | 確認ボタンの色 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | 確認が現在のインデックス + onClose を発火 | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose`（Compose UI Test） |
| `confirm-also-emits-onclose` | 確認後に onClose を発火 | 同上 |
| `cancel-emits-oncancel` | キャンセルが onCancel + onClose を発火 | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | スクロールが選択を変え onValueChange を発火 | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | value が負数のとき 0 にクランプ | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | value が範囲外のとき末項にクランプ | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | タイトルがテキストを描画 | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | 非表示のときは描画しない | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # JVM 部分
./gradlew :components:picker:connectedAndroidTest               # インタラクション部分（デバイスが必要）
```
