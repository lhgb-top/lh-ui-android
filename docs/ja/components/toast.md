# Toast 通知

可視性とタイミングを自前管理する墨色のカプセル型オーバーレイです。軽量な操作フィードバックに使われます。ユーザーの操作はブロックしません。

> 契約元: [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> ゴールデンケース: [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast)（6 件、状態マシン 4 件 + レンダリング 2 件、すべてローカルテストに翻訳済み）

## 使い方

toast は**自己完結型の状態マシン**です。状態は `LhToastState` が保持し、ホストは `show(text)` で表示、`hide()` で非表示にし、
タイミングの期限が来ると自動的に非表示になり `onClose` を発火します。コンポーネントは表示中はフレームごとに `tick` を自駆動しますが、ホストが自ら呼び出してタイミングを引き受けることもできます。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">操作は成功しました</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
val state = rememberLhToastState(onClose = { /* 任意：閉じたことを記録 */ })
Column {
  LhButton(lhOnClick = { state.show("保存しました") }) { Text("表示") }
  LhToast(state = state)
}
```

## セマンティックタイプ

`default` 以外のタイプでは、テキストの前にフォントサイズと同じ直径のセマンティック色の円点が描画されます。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast タイプ</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>成功</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>警告</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>失敗</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>主要</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## 位置

`lhPosition` は `top` / `center` / `bottom` を取り、上下の両端にそれぞれ `spacing.xl` のマージンを確保します。幅はテキストの計測から求められ、利用可能な幅の 80% を上限とします。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 位置</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">上部</div>
    <div class="sim-toast sim-toast--bottom">下部</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `state` | 通知の状態マシン（可視性、テキスト、タイミングを保持） | `LhToastState` | — |
| `type` | セマンティックタイプ。default 以外のときセマンティック色の円点を描画 | `LhToastType` | `default` |
| `lhPosition` | 利用可能領域内での位置 | `LhToastPosition` | `center` |
| `modifier` | 修飾子（カプセルに作用） | `Modifier` | `Modifier` |

### LhToastState

| メンバー | 説明 |
| --- | --- |
| `show(text: String)` | 表示し、タイミングをリセットする |
| `hide()` | すぐに非表示にする。**onClose は発火しない** |
| `tick(deltaMs: Int): Boolean` | タイミングを進める。期限が来たら非表示にし、onClose を発火して true を返す |
| `visible` / `text` / `duration` | 現在の状態（観察可能） |

ファクトリ: `rememberLhToastState(duration = 2000, onClose = {})`。

### LhToastType / LhToastPosition

| 値 | 円点の色 |
| --- | --- |
| `default` | 円点なし |
| `primary` / `success` / `warning` / `danger` / `info` | 対応するセマンティック色 |

`LhToastPosition`: `top` / `center` / `bottom`。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `toast.maxWidth` | カプセルの最大幅（240dp。実際は利用可能な幅の 80% との小さい方を取る） |
| `toast.borderRadius` | カプセルの角丸（`radius.base` に追従） |
| `toast.paddingHorizontal` / `toast.paddingVertical` | カプセルのパディング |
| `toast.fontSize` | テキストのフォントサイズ（円点の直径も同じ値） |
| `toast.gap` | 円点とテキストの間隔 |
| `zIndex.toast` | 階層のセマンティックアラインメント（Compose `Popup` がウィンドウレベルの階層を内包） |
| `color.background.overlay`（テーマレイヤーの ink マッピング） | カプセルの背景色 |
| `color.onPrimary` | テキスト色 |
| `color.{primary,success,warning,danger,info}.default` | セマンティックな円点の色 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `show-makes-visible` | show 後に可視になりテキストを描画 | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | 期限が来ると自動的に非表示になり onClose を発火 | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | 期限内は可視を保ち onClose を発火しない | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide は onClose を発火しない | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | success タイプが成功色を消費 | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | 非表示のときは何も描画しない | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # JVM 部分
./gradlew :components:toast:connectedAndroidTest                 # インタラクション部分（デバイスが必要）
```
