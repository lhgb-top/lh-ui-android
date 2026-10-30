# Button ボタン

基本的な操作ボタンです。即時操作をトリガーし、タイプ、サイズ、読み込み、無効化、アイコン、中華風の紋様背景をサポートします。

> 契約元: [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> ゴールデンケース: [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button)（8 件、JVM 単体テスト + Compose UI Test に翻訳済み）

## タイプ

6 種類のタイプが背景色とテキスト色を決定します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button タイプ</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">デフォルトボタン</button>
      <button class="sim-btn sim-btn--primary">プライマリボタン</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">成功ボタン</button>
      <button class="sim-btn sim-btn--warning">警告ボタン</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">危険ボタン</button>
      <button class="sim-btn sim-btn--info">情報ボタン</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("プライマリボタン") }
```

## サイズ

3 段階のサイズが `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` トークンに対応します。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button サイズ</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">小サイズボタン</button>
      <button class="sim-btn">中サイズボタン</button>
      <button class="sim-btn sim-btn--large">大サイズボタン</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## 状態

無効と読み込み中はどちらも `lhOnClick` を発火しません。両方指定された場合は**無効が優先**されます（ゴールデンケース `disabled-precedes-loading`）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 状態</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">通常</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>無効</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>読み込み中
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>無効が優先</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## アイコンと紋様背景

アイコン名はローカルのアイコン体系で解決されます（`LhButtonIcons`、現在は search / refresh / check / close をサポート、未登録名は描画されません）。
紋様背景は `color.decoration` トークンを消費し、背景色の上、テキストの下に重なり、コントラストに影響しません。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>アイコンと紋様</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>検索</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>デフォルト</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">プライマリ紋様背景</button>
      <button class="sim-btn sim-btn--pattern">デフォルト紋様背景</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## ライト / ダークテーマ

サイト右上のテーマを切り替えると、シミュレーターの携帯フレーム内のボタン色が変わります。実機では `LhTheme(isDark = true)` で切り替えます。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>ダークボタン</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">プライマリボタン</button>
      <button class="sim-btn">デフォルトボタン</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>無効</button>
      <button class="sim-btn sim-btn--success">成功</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません。ライト / ダークの値はすべて core のトークンスナップショットから取得</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `lhOnClick` | クリックコールバック。`disabled` または `loading` が true の場合は発火しない | `() -> Unit` | — |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |
| `type` | ボタンタイプ。背景色とテキスト色を決定 | `LhButtonType` | `Default` |
| `lhSize` | ボタンサイズ。サイズトークンに対応 | `LhButtonSize` | `Medium` |
| `disabled` | 無効かどうか | `Boolean` | `false` |
| `loading` | 読み込み中かどうか（インジケーターを表示し、lhOnClick を発火しない） | `Boolean` | `false` |
| `icon` | アイコン名（ローカルのアイコン体系で解決） | `String?` | `null` |
| `bgPattern` | 中華風の紋様背景を描画するかどうか | `Boolean` | `false` |
| `content` | ボタンのテキスト内容（spec の default スロット） | `@Composable () -> Unit` | — |

### LhButtonType

| 値 | 背景色 | テキスト色 |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| 値 | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small`（32dp） | `button.paddingHorizontal.small`（16dp） | `button.fontSize.small`（13sp） | `button.iconSize.small`（16dp） |
| `Medium` | `button.height.medium`（40dp） | `button.paddingHorizontal.medium`（20dp） | `button.fontSize.medium`（14sp） | `button.iconSize.medium`（18dp） |
| `Large` | `button.height.large`（48dp） | `button.paddingHorizontal.large`（24dp） | `button.fontSize.large`（16sp） | `button.iconSize.large`（20dp） |

無効状態の上書き: 背景 `color.disabled.bg`、テキスト `color.disabled.text`、ボーダー `color.disabled.border`。

## トークン对照

ボタンが消費するトークン（`specs/button.json` の `tokenRefs` と一対一で対応）:

| トークン | 用途 |
| --- | --- |
| `button.height.{small,medium,large}` | 3 段階の高さ |
| `button.paddingHorizontal.{small,medium,large}` | 3 段階の横向きパディング |
| `button.fontSize.{small,medium,large}` | 3 段階のフォントサイズ |
| `button.iconSize.{small,medium,large}` | 3 段階のアイコンサイズ |
| `button.borderRadius` / `button.borderWidth` | 角丸（`radius.base` に追従）とボーダー幅 |
| `button.fontWeight` | フォントの太さ（`typography.fontWeight.primary` に追従） |
| `color.primary.default` / `color.onPrimary` | プライマリタイプの背景 / テキスト |
| `color.{success,warning,danger,info}.default` | セマンティックタイプの背景色 |
| `color.background.default` / `color.text.primary` / `color.border.base` | デフォルトタイプの背景 / テキスト / ボーダー |
| `color.disabled.{bg,text,border}` | 無効状態の三色 |
| `color.decoration` | 紋様の色 |
| `shadow.lighter` | デフォルトタイプの elevation |
| `motion.duration.fast` / `motion.easing.easeInOut` | プレスフィードバックのモーション |

## ゴールデンケース对照

`cases/button.json` の 8 件のアサーションとローカルテストの対応関係:

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `click-emits-onclick` | クリックが onClick ×1 を発火 | `LhButtonTest.click_emits_onClick`（Compose UI Test） |
| `disabled-blocks-click` | 無効状態は発火しない | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | 読み込み状態は発火しない | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | 無効が優先 | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | デフォルトタイプがテキストを描画 | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | プライマリタイプがプライマリトークンを消費 | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | 小サイズが small サイズトークンを消費 | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | 紋様背景が装飾色を消費 | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | 登録済みアイコン名はベクターを描画、未登録名は描画しない | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # JVM 部分
./gradlew :components:button:connectedAndroidTest        # インタラクション部分（デバイスが必要）
```
