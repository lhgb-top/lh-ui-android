# Dialog ダイアログ

モーダルオーバーレイです。重要な確認やユーザーに集中してもらう必要がある内容に使います。ウィンドウレベルの階層は Compose の `Dialog` が管理します。

> 契約元: [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> ゴールデンケース: [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog)（8 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## タイプ

`type` が確認ボタンの色を決定します（`LhButtonType` と 1:1 で対応）。それ以外のレイアウトは変わりません。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">タイトル</div>
      <div class="sim-dialog__content">これは本文の内容で、現在の操作の影響を説明するものです。</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">キャンセル</button>
        <button class="sim-btn sim-btn--primary">確認</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* 確認のロジック */ },
  onCancel = {},
  onClose = { visible = false },
  title = "削除の確認",
  content = "削除後は復元できません。削除しますか？",
  type = LhDialogType.danger,
)
```

## セマンティックタイプとオーバーレイ

`type` が `danger` の場合、確認ボタンは危険色になります。`closable` はオーバーレイのタップ / 戻るボタンで `onClose` を発火するかどうかを制御します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">削除の確認</div>
      <div class="sim-dialog__content">削除後は復元できません。削除しますか？</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">キャンセル</button>
        <button class="sim-btn sim-btn--danger">確認</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

確認 / キャンセルボタンはクリック後、**先にビジネスコールバックを発火し、そのあと `onClose` を発火**します。ホストはこれに従って `visible` を false にします。

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `visible` | 表示するかどうか。false の場合は何も描画しない | `Boolean` | — |
| `onConfirm` | 確認ボタンクリック時に発火 | `() -> Unit` | — |
| `onCancel` | キャンセルボタンクリック時に発火 | `() -> Unit` | — |
| `onClose` | 確認 / キャンセル後、または（closable の場合）オーバーレイのタップで発火 | `() -> Unit` | — |
| `title` | タイトル | `String?` | `null` |
| `content` | 本文の内容 | `String?` | `null` |
| `type` | セマンティックタイプ。確認ボタンのタイプ（色）を決定 | `LhDialogType` | `default` |
| `confirmText` | 確認ボタンのテキスト。空の場合は確認ボタンを表示しない | `String` | `"确认"` |
| `cancelText` | キャンセルボタンのテキスト。空の場合はキャンセルボタンを表示しない | `String` | `"取消"` |
| `closable` | オーバーレイのタップ / 戻るボタンで onClose を発火するかどうか | `Boolean` | `false` |
| `titleContent` | タイトル内容（title を上書き、spec の title スロット） | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | 本文の内容（content を上書き、spec の default スロット） | `@Composable (() -> Unit)?` | `null` |
| `footer` | 底部のボタン領域（デフォルトのボタンを上書き、spec の footer スロット） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾子（内容カードに作用） | `Modifier` | `Modifier` |

### LhDialogType

| 値 | 確認ボタンのタイプ |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | 同名の `LhButtonType`。対応するセマンティック色を使用 |

## トークン对照

| トークン | 用途 |
| --- | --- |
| `dialog.width` | 内容カードの幅（320dp） |
| `dialog.borderRadius` | カードの角丸（`radius.base` に追従） |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | カードのパディング（`spacing.lg` に追従） |
| `dialog.titleFontSize` / `dialog.contentFontSize` | タイトル / 本文のフォントサイズ |
| `dialog.gap` | タイトル、本文、ボタン領域の間隔 |
| `zIndex.modal` / `zIndex.modalBackdrop` | 階層のセマンティックアラインメント（Compose `Dialog` がウィンドウレベルの階層を管理） |
| `color.background.overlay` | オーバーレイ色 |
| `color.background.default` | カードの背景色 |
| `color.text.primary` / `color.text.regular` | タイトル / 本文の色 |
| `shadow.base` | カードのシャドウ（Compose は spread 非対応のため、ぼかし半径を elevation に使用） |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `confirm-emits-onconfirm` | 確認が onConfirm + onClose を発火 | `LhDialogTest.confirm_emits_onConfirm_and_onClose`（Compose UI Test） |
| `confirm-also-emits-onclose` | 確認後に onClose を発火 | 同上 |
| `cancel-emits-oncancel` | キャンセルが onCancel + onClose を発火 | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | closable の場合、オーバーレイのタップで onClose を発火 | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | 非 closable の場合、オーバーレイのタップを握りつぶす | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | 非表示のときは描画しない | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | タイトルがテキストを描画 | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | danger タイプの確認ボタンは危険色を使用 | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # JVM 部分
./gradlew :components:dialog:connectedAndroidTest             # インタラクション部分（デバイスが必要）
```
