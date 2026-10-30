# Navbar ナビゲーションバー

ページ上部のタイトルバーです。左 / 中央 / 右の 3 段構成で、戻るボタンとタイトル表示によく使われます。

> 契約元: [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> ゴールデンケース: [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar)（6 件、トークンマッピングは JVM 単体テストに、左右のタップは Compose UI Test に翻訳済み）

## 使い方

3 段は等幅（各 `weight(1f)`）で、タイトルは利用可能な幅に対して水平方向の中央に配置されます。デフォルトで底部に区切り線が引かれます。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>戻る</div>
      <div class="sim-navbar__title">ページタイトル</div>
      <div class="sim-navbar__side sim-navbar__side--right">その他</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
LhNavbar(
  title = "ページタイトル",
  leftIcon = "arrow-left",
  leftText = "戻る",
  rightText = "その他",
  onLeftClick = { /* 戻る */ },
  onRightClick = { /* その他 */ },
)
```

## 左右のコンテンツ

テキストとアイコンは同時に存在できます（アイコンが左、テキストが右、間隔は `spacing.xs`）。**ある側に何もコンテンツがない場合はタップに応答しません**（ゴールデンケース `no-left-content-no-onleftclick`：そのノードにはクリック可能というセマンティクスすら存在しません）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar バリエーション</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">アイコンのみ</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">テキスト</div>
      <div class="sim-navbar__title">区切り線なし</div>
      <div class="sim-navbar__side sim-navbar__side--right">編集</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `title` | 中央のタイトル | `String?` | `null` |
| `onLeftClick` | 左側の領域をタップしたときに発火 | `() -> Unit` | `{}` |
| `onRightClick` | 右側の領域をタップしたときに発火 | `() -> Unit` | `{}` |
| `leftText` | 左側のボタンテキスト。leftIcon と共存可能 | `String?` | `null` |
| `leftIcon` | 左側のアイコン名（ローカルアイコン体系、例: `"arrow-left"`） | `String?` | `null` |
| `rightText` | 右側のボタンテキスト。rightIcon と共存可能 | `String?` | `null` |
| `rightIcon` | 右側のアイコン名 | `String?` | `null` |
| `bordered` | 底部の区切り線を描画するかどうか | `Boolean` | `true` |
| `titleContent` | タイトル内容（title を上書き、spec の default スロット） | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | 左側の内容（leftText/leftIcon を上書き、spec の left スロット） | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | 右側の内容（rightText/rightIcon を上書き、spec の right スロット） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

ある側の `*Content` / `*Text` / `*Icon` がすべて空のとき、その側はタップ不可能になり、何も描画されません。

## トークン对照

| トークン | 用途 |
| --- | --- |
| `navbar.height` | ナビゲーションバーの高さ（44dp） |
| `navbar.paddingHorizontal` | 左右の横向きパディング（`spacing.md` に追従） |
| `navbar.titleFontSize` | タイトルと両側のテキストのフォントサイズ |
| `navbar.iconSize` | 両側のアイコンサイズ（`icon.size.medium` に追従） |
| `navbar.borderWidth` | 底部の区切り線の幅 |
| `zIndex.sticky` | 上部固定の階層セマンティックアラインメント |
| `color.background.default` | 背景色 |
| `color.text.primary` / `color.text.regular` | タイトル / 両側のテキスト色 |
| `color.border.lighter` | 底部の区切り線色 |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | 左側のタップが onLeftClick を発火 | `LhNavbarTest.left_click_emits_onLeftClick`（Compose UI Test） |
| `rightclick-emits-onrightclick` | 右側のタップが onRightClick を発火 | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | 左側にコンテンツがない場合は発火しない | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | タイトルがテキストを描画 | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | 区切り線がボーダートークンを消費 | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | 高さが navbar トークンを消費 | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # JVM 部分
./gradlew :components:navbar:connectedAndroidTest                # インタラクション部分（デバイスが必要）
```
