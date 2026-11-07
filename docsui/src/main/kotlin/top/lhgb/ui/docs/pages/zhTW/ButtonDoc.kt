package top.lhgb.ui.docs.pages.zhTW

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
  title = "Button 按鈕",
  lede = "基礎操作按鈕。觸發即時操作，支援類型、尺寸、載入、停用、圖示與國風花紋底。",
  group = DocGroup.BASIC,
  contract = "契約源：`lh-ui-core/specs/button.json` · 黃金用例：`cases/button.json`（8 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "類型",
      prose = "六種類型決定底色與文字色。",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"主要按鈕\") }",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三檔尺寸對應到 `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` 令牌。",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "狀態",
      prose = "停用與載入中都不觸發 `lhOnClick`；兩者共存時按停用優先處理（黃金用例 `disabled-precedes-loading`）。",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "圖示與花紋底",
      prose = "圖示名按本機圖示體系解析（`LhButtonIcons`，當前支援 search / refresh / check / close，未登記名不繪製）。\n花紋底消費 `color.decoration` 令牌，疊在底色之上、文字之下，不影響對比度。",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "明暗主題",
      prose = "切換站台右上角主題，模擬器手機框內的按鈕顏色即變；真機透過 `LhTheme(isDark = true)` 切換。",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "點選回呼；`disabled` 或 `loading` 為 true 時不觸發", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "按鈕類型，決定底色與文字色", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "按鈕尺寸，對應到尺寸令牌", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "是否停用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "是否載入中（顯示指示器，不觸發 lhOnClick）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "圖示名（本機圖示體系解析）", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "是否繪製國風花紋底紋", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "按鈕文字內容（spec 的 default 插槽）", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("值", "底色", "文字色"),
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
      headers = listOf("值", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small`（32dp）", "`button.paddingHorizontal.small`（16dp）", "`button.fontSize.small`（13sp）", "`button.iconSize.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium`（40dp）", "`button.paddingHorizontal.medium`（20dp）", "`button.fontSize.medium`（14sp）", "`button.iconSize.medium`（18dp）")),
        DocRow(cells = listOf("`Large`", "`button.height.large`（48dp）", "`button.paddingHorizontal.large`（24dp）", "`button.fontSize.large`（16sp）", "`button.iconSize.large`（20dp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "三檔高度")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "三檔橫向內邊距")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "三檔字級")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "三檔圖示尺寸")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "圓角（跟隨 `radius.base`）與描邊寬度")),
    DocRow(cells = listOf("`button.fontWeight`", "字重（跟隨 `typography.fontWeight.primary`）")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "主色類型底/文字")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "語義類型底色")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "預設類型底/文字/描邊")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "停用態三色")),
    DocRow(cells = listOf("`color.decoration`", "花紋底紋色")),
    DocRow(cells = listOf("`shadow.lighter`", "預設類型 elevation")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "按壓回饋動效")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "點選觸發 onClick ×1", "`LhButtonTest.click_emits_onClick`（Compose UI Test）")),
    DocRow(cells = listOf("`disabled-blocks-click`", "停用態不觸發", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "載入態不觸發", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "停用優先", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "預設類型渲染文字", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "主色類型消費主色令牌", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小號消費 small 尺寸令牌", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "花紋底消費裝飾色", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "已登記圖示名渲染向量；未登記名不繪製", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # JVM 部分",
    "./gradlew :components:button:connectedAndroidTest        # 互動部分（需裝置）",
  ),
)
