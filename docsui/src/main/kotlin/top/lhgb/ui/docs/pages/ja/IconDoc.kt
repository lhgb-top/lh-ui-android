package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "Icon アイコン",
  lede = "アイコンの基盤コンポーネントです。他のコンポーネント（cell / input / navbar など）のアイコンはすべて、このコンポーネントのローカルレジストリ経由で描画されます。",
  group = DocGroup.BASIC,
  contract = "契約元: `lh-ui-core/specs/icon.json` · ゴールデンケース: `cases/icon.json`（5 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "サイズと色調",
      prose = "3 段階のサイズが `icon.size.*` トークンに対応します。色調 `default` は `color.text.primary` を、それ以外はセマンティック色を使用します。",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "セマンティックな色調",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "アイコンレジストリ",
      prose = "`LhIcons` はローカルのアイコン名 → Material の `ImageVector` へのレジストリです。未登録のアイコン名は `null` を返します（描画せず、例外も投げない）:",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "アイコン名（`LhIcons` レジストリで解決）", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "アイコンサイズ", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "セマンティックな色調", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "アクセシビリティの説明。デフォルトはアイコン名、明示的に `null` を渡すと装飾的なアイコンとして宣言", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "クリックコールバック。null の場合はクリック不可", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("値", "サイズトークン"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`icon.size.large`（24dp）")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("値", "色トークン"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "3 段階のサイズ")),
    DocRow(cells = listOf("`color.text.primary`", "default 色調")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "セマンティックな色調")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "regular / secondary 色調")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "クリックが onClick を発火", "`LhIconTest.click_emits_onClick`（Compose UI Test）")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "デフォルトの色調はメインテキスト色のトークンを使用", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 色調はプライマリ色のトークンを使用", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "小サイズは small サイズトークンを使用（large が適用されていないことの逆アサーション）", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "登録済みアイコン名はベクターに解決、未登録名は描画しない", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # JVM 部分",
    "./gradlew :components:icon:connectedAndroidTest            # インタラクション部分（デバイスが必要）",
  ),
)
