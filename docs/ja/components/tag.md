# Tag タグ

セマンティック色のタグです。アウトライン、完全な角丸マーク、閉じるボタンをサポートします。

> 契約元: [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> ゴールデンケース: [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag)（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## タイプ

6 種類のセマンティックタイプが背景色とテキスト色を決定します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag タイプ</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">デフォルト</span><span class="sim-tag sim-tag--success">成功</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">警告</span><span class="sim-tag sim-tag--danger">危険</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">情報</span></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
LhTag(type = LhTagType.success) { Text("成功") }
```

## アウトライン（plain）

`plain` は透明な背景 + セマンティック色の枠線とテキストです。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">主要</span><span class="sim-tag sim-tag--plain sim-tag--success">成功</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">危険</span></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## サイズとマーク

3 段階のサイズが `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` トークンに対応します。`mark` は高さの半分の完全な角丸を取ります。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">マーク</span><span class="sim-tag sim-tag--success sim-tag--mark">成功マーク</span></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `text` | タグのテキスト（簡易書き方、`content` と二択） | `String?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |
| `type` | セマンティックタイプ | `LhTagType` | `Default` |
| `lhSize` | サイズ | `LhTagSize` | `Medium` |
| `plain` | アウトラインにするかどうか（透明な背景 + セマンティック色の枠線） | `Boolean` | `false` |
| `round` | 完全な角丸にするかどうか（高さの半分） | `Boolean` | `false` |
| `closable` | 閉じるボタンを表示するかどうか | `Boolean` | `false` |
| `onClose` | 閉じるコールバック。`closable` が false の場合は発火しない | `(() -> Unit)?` | `null` |
| `content` | タグ内容（text を上書き、spec の default スロット） | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| 値 | 背景色（plain 以外） | 枠線 / テキスト（plain） |
| --- | --- | --- |
| `Default` | `color.background.default` + `color.border.base` の枠線 | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| 値 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small`（20dp） | `tag.paddingHorizontal.small`（8dp） | `tag.fontSize.small`（12sp） |
| `Medium` | `tag.height.medium`（24dp） | `tag.paddingHorizontal.medium`（10dp） | `tag.fontSize.medium`（14sp） |
| `Large` | `tag.height.large`（28dp） | `tag.paddingHorizontal.large`（12dp） | `tag.fontSize.large`（16sp） |

## トークン对照

| トークン | 用途 |
| --- | --- |
| `tag.height.{small,medium,large}` | 3 段階の高さ |
| `tag.paddingHorizontal.{small,medium,large}` | 3 段階の横向きパディング |
| `tag.fontSize.{small,medium,large}` | 3 段階のフォントサイズ |
| `tag.borderRadius` / `tag.borderWidth` | 角丸と枠線の幅 |
| `color.{primary,success,warning,danger,info}.default` | セマンティックな背景色 / 枠線 / テキスト |
| `color.onPrimary` | ソリッドな背景上のテキスト色 |
| `color.disabled.{bg,text,border}` | 無効状態の三色 |
| `spacing.xs` | 閉じるボタンとテキストの間隔 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `default-type-renders-text` | デフォルトタイプが中間色の背景 + 主テキスト色 + ベーシックな枠線を取る | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | 主色タイプが主色トークンを消費 | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain がセマンティックな薄い背景トークンを消費 | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | small が small トークンを消費（large は適用されないという逆向きのアサーション） | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round の角丸が高さの半分を取る | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | 閉じるボタンのタップが onClose を発火 | `LhTagTest.closable_tap_emits_onClose`（Compose UI Test） |

```bash
./gradlew :components:tag:test                             # JVM 部分
./gradlew :components:tag:connectedAndroidTest             # インタラクション部分（デバイスが必要）
```
