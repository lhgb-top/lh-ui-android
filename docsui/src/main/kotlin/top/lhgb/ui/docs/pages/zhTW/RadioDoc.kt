package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Radio 單選鈕",
  lede = "受控單選鈕，選取後再次點選不會取消選取（單選語義）。",
  group = DocGroup.FORM,
  contract = "契約源：`lh-ui-core/specs/radio.json` · 黃金用例：`cases/radio.json`（6 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "狀態與單選群組",
      prose = "元件本身不含群組邏輯，單選互斥由宿主維護（用 `value` 標識群組內項）。",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"選項一\" to \"a\", \"選項二\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三檔圓環尺寸對應到 `radio.size.*` 令牌；內圓點直徑 = 尺寸 × `radio.dotScale`。",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "是否選取（必填）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "由未選取變為選取時觸發，攜帶 `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "是否停用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "標籤文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "單選值（群組內標識，元件本身不含群組邏輯）", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "圓環尺寸", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "標籤內容（覆蓋 label，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("值", "尺寸令牌", "內圓點直徑"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small`（16dp）", "16dp × `radio.dotScale`（0.5）")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium`（20dp）", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large`（24dp）", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "三檔圓環尺寸")),
    DocRow(cells = listOf("`radio.ringWidth`", "外環描邊寬")),
    DocRow(cells = listOf("`radio.dotScale`", "內圓點相對外環直徑的比例（分數）")),
    DocRow(cells = listOf("`radio.gap`", "圓環與標籤間距")),
    DocRow(cells = listOf("`color.primary.default`", "選取描邊與圓點色")),
    DocRow(cells = listOf("`color.border.base`", "未選描邊色")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "停用描邊 / 圓點 / 文字色")),
    DocRow(cells = listOf("`color.text.primary`", "標籤色")),
    DocRow(cells = listOf("`typography.fontSize.base`", "標籤字級")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "未選點選觸發 onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "已選點選不觸發", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "停用態不觸發", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "選取消費主色令牌", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "未選消費描邊令牌", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "小號消費 small 尺寸令牌", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # JVM 部分",
    "./gradlew :components:radio:connectedAndroidTest             # 互動部分（需裝置）",
  ),
)
