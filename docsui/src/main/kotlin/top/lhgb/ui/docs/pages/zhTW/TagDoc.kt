package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTagPlainPreview
import top.lhgb.ui.docs.previews.DocTagSizeAndMarkPreview
import top.lhgb.ui.docs.previews.DocTagTypesPreview

val tagDoc = DocEntry(
  id = "tag",
  title = "Tag 標籤",
  lede = "語義色標籤，支援鏤空、全圓角標記與關閉按鈕。",
  group = DocGroup.BASIC,
  contract = "契約源：`lh-ui-core/specs/tag.json` · 黃金用例：`cases/tag.json`（6 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "類型",
      prose = "六種語義類型決定底色與文字色。",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"成功\") }",
      ),
    ),
    DocSection(
      title = "鏤空（plain）",
      prose = "`plain` 為透明底 + 語義色描邊與文字。",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "尺寸與標記",
      prose = "三檔尺寸對應到 `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` 令牌；`mark` 取高度一半的全圓角。",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "標籤文字（便捷寫法，與 `content` 二選一）", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "語義類型", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "尺寸", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "是否鏤空（透明底 + 語義色描邊）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "是否全圓角（高度一半）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "是否顯示關閉按鈕", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "關閉回呼；`closable` 為 false 時不觸發", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "標籤內容（覆蓋 text，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("值", "底色（非 plain）", "描邊/文字（plain）"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + `color.border.base` 描邊", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("值", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small`（20dp）", "`tag.paddingHorizontal.small`（8dp）", "`tag.fontSize.small`（12sp）")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium`（24dp）", "`tag.paddingHorizontal.medium`（10dp）", "`tag.fontSize.medium`（14sp）")),
        DocRow(cells = listOf("`Large`", "`tag.height.large`（28dp）", "`tag.paddingHorizontal.large`（12dp）", "`tag.fontSize.large`（16sp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "三檔高度")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "三檔橫向內邊距")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "三檔字級")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "圓角與描邊寬度")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "語義底色 / 描邊 / 文字")),
    DocRow(cells = listOf("`color.onPrimary`", "非純文字色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "停用態三色")),
    DocRow(cells = listOf("`spacing.xs`", "關閉按鈕與文字間距")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "預設類型取中性底 + 主文字色 + 基礎描邊", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "主色類型消費主色令牌", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain 消費語義淺底令牌", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小號消費 small 令牌（反向斷言 large 未套用）", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round 圓角取高度一半", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "點選關閉觸發 onClose", "`LhTagTest.closable_tap_emits_onClose`（Compose UI Test）")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # JVM 部分",
    "./gradlew :components:tag:connectedAndroidTest             # 互動部分（需裝置）",
  ),
)
