package top.lhgb.ui.docs.pages.zhTW

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
  title = "Icon 圖示",
  lede = "圖示基座元件。其他元件（cell / input / navbar 等）的圖示都經由本元件的本機註冊表渲染。",
  group = DocGroup.BASIC,
  contract = "契約源：`lh-ui-core/specs/icon.json` · 黃金用例：`cases/icon.json`（5 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "尺寸與色調",
      prose = "三檔尺寸對應到 `icon.size.*` 令牌；色調 `default` 取 `color.text.primary`，其餘取語義色。",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "語義色調",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "圖示註冊表",
      prose = "`LhIcons` 是本機圖示名 → Material `ImageVector` 的註冊表，未登記的圖示名回傳 `null`（不繪製，不拋異常）：",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "圖示名（`LhIcons` 註冊表解析）", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "圖示尺寸", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "語義色調", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "無障礙描述；預設取圖示名，顯式傳 `null` 宣告為裝飾性圖示", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "點選回呼；為 null 時不可點選", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("值", "尺寸令牌"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`icon.size.large`（24dp）")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("值", "顏色令牌"),
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
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "三檔尺寸")),
    DocRow(cells = listOf("`color.text.primary`", "default 色調")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "語義色調")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "regular / secondary 色調")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "點選觸發 onClick", "`LhIconTest.click_emits_onClick`（Compose UI Test）")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "預設色調取主文字色令牌", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 色調取主色令牌", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "小號取 small 尺寸令牌（反向斷言 large 未套用）", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "已登記圖示名解析為向量；未登記名不繪製", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # JVM 部分",
    "./gradlew :components:icon:connectedAndroidTest            # 互動部分（需裝置）",
  ),
)
