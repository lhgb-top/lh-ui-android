package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Navbar ナビゲーションバー",
  lede = "ページ上部のタイトルバーです。左 / 中央 / 右の 3 段構成で、戻るボタンとタイトル表示によく使われます。",
  group = DocGroup.OVERLAY,
  contract = "契約元: `lh-ui-core/specs/navbar.json` · ゴールデンケース: `cases/navbar.json`（6 件、トークンマッピングは JVM 単体テストに、左右のタップは Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "使い方",
      prose = "3 段は等幅（各 `weight(1f)`）で、タイトルは利用可能な幅に対して水平方向の中央に配置されます。デフォルトで底部に区切り線が引かれます。",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"ページタイトル\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"戻る\",",
        "  rightText = \"その他\",",
        "  onLeftClick = { /* 戻る */ },",
        "  onRightClick = { /* その他 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "左右のコンテンツ",
      prose = "テキストとアイコンは同時に存在できます（アイコンが左、テキストが右、間隔は `spacing.xs`）。ある側に何もコンテンツがない場合はタップに応答しません（ゴールデンケース `no-left-content-no-onleftclick`：そのノードにはクリック可能というセマンティクスすら存在しません）。",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "中央のタイトル", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "左側の領域をタップしたときに発火", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "右側の領域をタップしたときに発火", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "左側のボタンテキスト。leftIcon と共存可能", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "左側のアイコン名（ローカルアイコン体系、例: `\"arrow-left\"`）", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "右側のボタンテキスト。rightIcon と共存可能", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "右側のアイコン名", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "底部の区切り線を描画するかどうか", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "タイトル内容（title を上書き、spec の default スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "左側の内容（leftText/leftIcon を上書き、spec の left スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "右側の内容（rightText/rightIcon を上書き、spec の right スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "ナビゲーションバーの高さ（44dp）")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "左右の横向きパディング（`spacing.md` に追従）")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "タイトルと両側のテキストのフォントサイズ")),
    DocRow(cells = listOf("`navbar.iconSize`", "両側のアイコンサイズ（`icon.size.medium` に追従）")),
    DocRow(cells = listOf("`navbar.borderWidth`", "底部の区切り線の幅")),
    DocRow(cells = listOf("`zIndex.sticky`", "上部固定の階層セマンティックアラインメント")),
    DocRow(cells = listOf("`color.background.default`", "背景色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "タイトル / 両側のテキスト色")),
    DocRow(cells = listOf("`color.border.lighter`", "底部の区切り線色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "左側のタップが onLeftClick を発火", "`LhNavbarTest.left_click_emits_onLeftClick`（Compose UI Test）")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "右側のタップが onRightClick を発火", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "左側にコンテンツがない場合は発火しない", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "タイトルがテキストを描画", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "区切り線がボーダートークンを消費", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "高さが navbar トークンを消費", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # JVM 部分",
    "./gradlew :components:navbar:connectedAndroidTest                # インタラクション部分（デバイスが必要）",
  ),
)
