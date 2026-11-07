package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Checkbox 核取方塊",
  lede = "受控核取方塊，支援不確定態（indeterminate）與停用。",
  group = DocGroup.FORM,
  contract = "契約源：`lh-ui-core/specs/checkbox.json` · 黃金用例：`cases/checkbox.json`（6 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "狀態",
      prose = "選取 / 不確定態為實心主色底 + onPrimary 指示符（勾形 / 橫槓），未選為只描邊。",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"同意協議\",",
        ")",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三檔方框尺寸對應到 `checkbox.size.*` 令牌。",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "是否選取（必填）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "選取態變更回呼，攜帶新選取態", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "是否不確定態（indeterminate）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "是否停用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "標籤文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "方框尺寸", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "標籤內容（覆蓋 label，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("值", "尺寸令牌"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large`（24dp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "三檔方框尺寸")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "方框圓角與描邊寬")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "勾形 / 橫槓描邊寬")),
    DocRow(cells = listOf("`checkbox.gap`", "方框與標籤間距")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "選取底色 / 指示符色")),
    DocRow(cells = listOf("`color.border.base`", "未選描邊色")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "停用態三色")),
    DocRow(cells = listOf("`color.text.primary`", "標籤色")),
    DocRow(cells = listOf("`typography.fontSize.base`", "標籤字級")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "點選翻轉並發射 onChange", "`LhCheckboxTest.tap_unchecked_emits_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "選取態點選取消", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "不確定態點選變選取", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "停用態不觸發", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "選取消費主色令牌", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "未選消費描邊令牌", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # JVM 部分",
    "./gradlew :components:checkbox:connectedAndroidTest           # 互動部分（需裝置）",
  ),
)
