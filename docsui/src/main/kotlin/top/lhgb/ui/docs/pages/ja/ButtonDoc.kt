package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "Button ボタン",
  lede = "基本的な操作ボタンです。即時操作をトリガーし、タイプ、サイズ、読み込み、無効化、アイコン、中華風の紋様背景をサポートします。",
  group = DocGroup.BASIC,
  contract = "契約元: `lh-ui-core/specs/button.json` · ゴールデンケース: `cases/button.json`（8 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "タイプ",
      prose = "6 種類のタイプが背景色とテキスト色を決定します。",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"プライマリボタン\") }",
      ),
    ),
    DocSection(
      title = "サイズ",
      prose = "3 段階のサイズが `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` トークンに対応します。",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "状態",
      prose = "無効と読み込み中はどちらも `lhOnClick` を発火しません。両方指定された場合は無効が優先されます（ゴールデンケース `disabled-precedes-loading`）。",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "アイコンと紋様背景",
      prose = "アイコン名はローカルのアイコン体系で解決されます（`LhButtonIcons`、現在は search / refresh / check / close をサポート、未登録名は描画されません）。\n紋様背景は `color.decoration` トークンを消費し、背景色の上、テキストの下に重なり、コントラストに影響しません。",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "ライト / ダークテーマ",
      prose = "サイト右上のテーマを切り替えると、シミュレーターの携帯フレーム内のボタン色が変わります。実機では `LhTheme(isDark = true)` で切り替えます。",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "クリックコールバック。`disabled` または `loading` が true の場合は発火しない", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "ボタンタイプ。背景色とテキスト色を決定", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "ボタンサイズ。サイズトークンに対応", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "無効かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "読み込み中かどうか（インジケーターを表示し、lhOnClick を発火しない）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "アイコン名（ローカルのアイコン体系で解決）", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "中華風の紋様背景を描画するかどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "ボタンのテキスト内容（spec の default スロット）", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("値", "背景色", "テキスト色"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("値", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small`（32dp）", "`button.paddingHorizontal.small`（16dp）", "`button.fontSize.small`（13sp）", "`button.iconSize.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium`（40dp）", "`button.paddingHorizontal.medium`（20dp）", "`button.fontSize.medium`（14sp）", "`button.iconSize.medium`（18dp）")),
        DocRow(cells = listOf("`Large`", "`button.height.large`（48dp）", "`button.paddingHorizontal.large`（24dp）", "`button.fontSize.large`（16sp）", "`button.iconSize.large`（20dp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "3 段階の高さ")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "3 段階の横向きパディング")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "3 段階のフォントサイズ")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "3 段階のアイコンサイズ")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "角丸（`radius.base` に追従）とボーダー幅")),
    DocRow(cells = listOf("`button.fontWeight`", "フォントの太さ（`typography.fontWeight.primary` に追従）")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "プライマリタイプの背景 / テキスト")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "セマンティックタイプの背景色")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "デフォルトタイプの背景 / テキスト / ボーダー")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "無効状態の三色")),
    DocRow(cells = listOf("`color.decoration`", "紋様の色")),
    DocRow(cells = listOf("`shadow.lighter`", "デフォルトタイプの elevation")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "プレスフィードバックのモーション")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "クリックが onClick ×1 を発火", "`LhButtonTest.click_emits_onClick`（Compose UI Test）")),
    DocRow(cells = listOf("`disabled-blocks-click`", "無効状態は発火しない", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "読み込み状態は発火しない", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "無効が優先", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "デフォルトタイプがテキストを描画", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "プライマリタイプがプライマリトークンを消費", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小サイズが small サイズトークンを消費", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "紋様背景が装飾色を消費", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "登録済みアイコン名はベクターを描画、未登録名は描画しない", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # JVM 部分",
    "./gradlew :components:button:connectedAndroidTest        # インタラクション部分（デバイスが必要）",
  ),
)
