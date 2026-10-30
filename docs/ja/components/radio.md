# Radio ラジオボタン

コントロールされるラジオボタンです。選択された後に再度タップしても選択は解除されません（ラジオのセマンティクス）。

> 契約元: [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> ゴールデンケース: [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio)（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## 状態とラジオグループ

コンポーネント自体はグループのロジックを持たず、ラジオの排他制御はホストが維持します（`value` でグループ内の項目を識別します）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>未選択</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>選択済み</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>無効（未選択）</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>無効（選択済み）</label></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("選択肢 1" to "a", "選択肢 2" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## サイズ

3 段階のリングサイズが `radio.size.*` トークンに対応します。内側の円点の直径 = サイズ × `radio.dotScale` です。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `checked` | 選択されているかどうか（必須） | `Boolean` | — |
| `onChange` | 未選択から選択に変化したときに発火。`checked = true` を伴う | `(Boolean) -> Unit` | — |
| `disabled` | 無効かどうか | `Boolean` | `false` |
| `label` | ラベルテキスト | `String?` | `null` |
| `value` | ラジオ値（グループ内の識別子、コンポーネント自体はグループのロジックを持たない） | `String?` | `null` |
| `lhSize` | リングサイズ | `LhRadioSize` | `Medium` |
| `labelContent` | ラベル内容（label を上書き、spec の default スロット） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

### LhRadioSize

| 値 | サイズトークン | 内側の円点の直径 |
| --- | --- | --- |
| `Small` | `radio.size.small`（16dp） | 16dp × `radio.dotScale`（0.5） |
| `Medium` | `radio.size.medium`（20dp） | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large`（24dp） | 24dp × `radio.dotScale` |

タップは行全体にヒットします。未選択かつ無効でない場合にのみ選択状態になり、`onChange(true)` を発火します。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `radio.size.{small,medium,large}` | 3 段階のリングサイズ |
| `radio.ringWidth` | 外側のリングの枠線幅 |
| `radio.dotScale` | 内側の円点の、外側のリング直径に対する割合（小数） |
| `radio.gap` | リングとラベルの間隔 |
| `color.primary.default` | 選択時の枠線と円点の色 |
| `color.border.base` | 未選択の枠線色 |
| `color.disabled.{border,text}` | 無効状態の枠線 / 円点 / テキスト色 |
| `color.text.primary` | ラベル色 |
| `typography.fontSize.base` | ラベルのフォントサイズ |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | 未選択のタップが onChange(true) を発火 | `LhRadioTest.tap_unchecked_emits_onchange_true`（Compose UI Test） |
| `tap-checked-keeps-checked` | 選択済みのタップは発火しない | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | 無効状態は発火しない | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 選択が主色トークンを消費 | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 未選択がボーダートークンを消費 | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | small が small サイズトークンを消費 | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # JVM 部分
./gradlew :components:radio:connectedAndroidTest             # インタラクション部分（デバイスが必要）
```
