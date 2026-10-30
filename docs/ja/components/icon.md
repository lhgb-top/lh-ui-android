# Icon アイコン

アイコンの基盤コンポーネントです。他のコンポーネント（cell / input / navbar など）のアイコンはすべて、このコンポーネントのローカルレジストリ経由で描画されます。

> 契約元: [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> ゴールデンケース: [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon)（5 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## サイズと色調

3 段階のサイズが `icon.size.*` トークンに対応します。色調 `default` は `color.text.primary` を、それ以外はセマンティック色を使用します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">デフォルト色（text.primary）</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## セマンティックな色調

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 色調</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## アイコンレジストリ

`LhIcons` はローカルのアイコン名 → Material の `ImageVector` へのレジストリです。未登録のアイコン名は `null` を返します（描画せず、例外も投げない）:

| アイコン名 | Material ベクター |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `name` | アイコン名（`LhIcons` レジストリで解決） | `String` | — |
| `lhSize` | アイコンサイズ | `LhIconSize` | `Medium` |
| `tone` | セマンティックな色調 | `LhIconTone` | `Default` |
| `contentDescription` | アクセシビリティの説明。デフォルトはアイコン名、明示的に `null` を渡すと装飾的なアイコンとして宣言 | `String?` | `name` |
| `lhOnClick` | クリックコールバック。null の場合はクリック不可 | `(() -> Unit)?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

### LhIconSize

| 値 | サイズトークン |
| --- | --- |
| `Small` | `icon.size.small`（16dp） |
| `Medium` | `icon.size.medium`（20dp） |
| `Large` | `icon.size.large`（24dp） |

### LhIconTone

| 値 | 色トークン |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## トークン对照

| トークン | 用途 |
| --- | --- |
| `icon.size.{small,medium,large}` | 3 段階のサイズ |
| `color.text.primary` | default 色調 |
| `color.{primary,success,warning,danger,info}.default` | セマンティックな色調 |
| `color.text.{regular,secondary}` | regular / secondary 色調 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `click-emits-onclick` | クリックが onClick を発火 | `LhIconTest.click_emits_onClick`（Compose UI Test） |
| `default-tone-uses-text-primary` | デフォルトの色調はメインテキスト色のトークンを使用 | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | primary 色調はプライマリ色のトークンを使用 | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | 小サイズは small サイズトークンを使用（large が適用されていないことの逆アサーション） | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | 登録済みアイコン名はベクターに解決、未登録名は描画しない | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # JVM 部分
./gradlew :components:icon:connectedAndroidTest            # インタラクション部分（デバイスが必要）
```
