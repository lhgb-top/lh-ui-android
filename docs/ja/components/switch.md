# Switch スイッチ

カプセル型のトラック + 円形のつまみによる 2 状態切り替えコントロールです。読み込み中の表示をサポートします。

> 契約元: [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> ゴールデンケース: [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch)（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## 状態

オンは主色のトラック、オフはボーダー色のトラックを取ります。つまみの直径 = 高さ - 2 × `switch.gap` で、移動アニメーションは `motion.duration.base` を取ります。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>オフ</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>オン</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>無効（オフ）</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>無効（オン）</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>読み込み中（つまみの位置に読み込みリングが表示され、タップに応答しません）</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## サイズ

3 段階のサイズが `switch.width` / `switch.height` トークンに対応します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `checked` | オンかどうか（必須） | `Boolean` | — |
| `onChange` | スイッチ状態の変更コールバック。新しいスイッチ状態を伴う | `(Boolean) -> Unit` | — |
| `disabled` | 無効かどうか | `Boolean` | `false` |
| `loading` | 読み込み中かどうか（つまみの位置に読み込みリングが表示され、タップに応答しない） | `Boolean` | `false` |
| `lhSize` | サイズ | `LhSwitchSize` | `Medium` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

### LhSwitchSize

| 値 | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small`（36dp） | `switch.height.small`（20dp） |
| `Medium` | `switch.width.medium`（44dp） | `switch.height.medium`（24dp） |
| `Large` | `switch.width.large`（52dp） | `switch.height.large`（28dp） |

トラックはカプセル型（角丸は高さの半分）です。タップはトラック領域にヒットし、スイッチ状態を反転させて `onChange` を発火します。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `switch.width.{small,medium,large}` | 3 段階のトラック幅 |
| `switch.height.{small,medium,large}` | 3 段階のトラック高さ |
| `switch.gap` | つまみとトラックの間の余白（つまみの直径 = 高さ - 2 × gap） |
| `color.primary.default` | オンのトラック色 |
| `color.border.base` | オフのトラック色 |
| `color.onPrimary` | つまみの色 |
| `color.disabled.{bg,text}` | 無効状態のトラック / つまみの色 |
| `motion.duration.base` / `motion.easing.easeInOut` | つまみの移動アニメーション |
| `loading.strokeWidth` | 読み込みリングの枠線幅（再利用） |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | タップが反転し onChange を発火 | `LhSwitchTest.tap_unchecked_emits_true`（Compose UI Test） |
| `tap-checked-turns-off` | オン状態のタップでオフになる | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | 無効状態は発火しない | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | 読み込み中は発火しない | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | オンが主色トークンを消費 | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | オフがボーダートークンを消費 | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # JVM 部分
./gradlew :components:switch:connectedAndroidTest            # インタラクション部分（デバイスが必要）
```
