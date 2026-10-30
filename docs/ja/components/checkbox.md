# Checkbox チェックボックス

コントロールされるチェックボックスです。半選（indeterminate）と無効化をサポートします。

> 契約元: [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> ゴールデンケース: [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox)（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## 状態

選択 / 半選はソリッドなプライマリ背景 + onPrimary のインジケーター（チェックマーク / 横棒）、未選択はボーダーのみです。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>未選択</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>選択済み</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>半選</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>無効（選択）</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>無効（未選択）</label></div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "規約に同意する",
)
```

## サイズ

3 段階のボックスサイズが `checkbox.size.*` トークンに対応します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `checked` | 選択されているかどうか（必須） | `Boolean` | — |
| `onCheckedChange` | 選択状態の変更コールバック。新しい選択状態を伴う | `(Boolean) -> Unit` | — |
| `indeterminate` | 半選択（不確定状態）かどうか | `Boolean` | `false` |
| `disabled` | 無効かどうか | `Boolean` | `false` |
| `label` | ラベルテキスト | `String?` | `null` |
| `lhSize` | ボックスサイズ | `LhCheckboxSize` | `Medium` |
| `labelContent` | ラベル内容（label を上書き、spec の default スロット） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

### LhCheckboxSize

| 値 | サイズトークン |
| --- | --- |
| `Small` | `checkbox.size.small`（16dp） |
| `Medium` | `checkbox.size.medium`（20dp） |
| `Large` | `checkbox.size.large`（24dp） |

タップは行全体（ボックス + ラベル）にヒットします。半選状態のタップは選択状態になり、それ以外は選択状態を反転させます。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `checkbox.size.{small,medium,large}` | 3 段階のボックスサイズ |
| `checkbox.borderRadius` / `checkbox.borderWidth` | ボックスの角丸とボーダー幅 |
| `checkbox.checkWidth` | チェックマーク / 横棒のボーダー幅 |
| `checkbox.gap` | ボックスとラベルの間隔 |
| `color.primary.default` / `color.onPrimary` | 選択背景色 / インジケーター色 |
| `color.border.base` | 未選択のボーダー色 |
| `color.disabled.{bg,border,text}` | 無効状態の三色 |
| `color.text.primary` | ラベル色 |
| `typography.fontSize.base` | ラベルのフォントサイズ |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | タップが反転し onChange を発火 | `LhCheckboxTest.tap_unchecked_emits_true`（Compose UI Test） |
| `tap-checked-unchecks` | 選択状態のタップで選択解除 | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | 半選のタップで選択状態になる | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | 無効状態は発火しない | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 選択がプライマリトークンを消費 | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 未選択がボーダートークンを消費 | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # JVM 部分
./gradlew :components:checkbox:connectedAndroidTest           # インタラクション部分（デバイスが必要）
```
