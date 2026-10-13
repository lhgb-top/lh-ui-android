package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Checkbox 复选框",
  lede = "受控复选框，支持半选（indeterminate）与禁用。",
  group = DocGroup.FORM,
  contract = "契约源：`lh-ui-core/specs/checkbox.json` · 黄金用例：`cases/checkbox.json`（6 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "状态",
      prose = "选中 / 半选为实心主色底 + onPrimary 指示符（勾形 / 横杠），未选为只描边。",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"同意协议\",",
        ")",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三档方框尺寸映射到 `checkbox.size.*` 令牌。",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "是否选中（必填）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "选中态变更回调，携带新选中态", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "是否半选（不确定态）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "是否禁用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "标签文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`size`", "方框尺寸", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "标签内容（覆盖 label，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
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
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "三档方框尺寸")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "方框圆角与描边宽")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "勾形 / 横杠描边宽")),
    DocRow(cells = listOf("`checkbox.gap`", "方框与标签间距")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "选中底色 / 指示符色")),
    DocRow(cells = listOf("`color.border.base`", "未选描边色")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "禁用态三色")),
    DocRow(cells = listOf("`color.text.primary`", "标签色")),
    DocRow(cells = listOf("`typography.fontSize.base`", "标签字号")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "点击翻转并发射 onChange", "`LhCheckboxTest.tap_unchecked_emits_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "选中态点击取消", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "半选点击变选中", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "禁用态不触发", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "选中消费主色令牌", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "未选消费描边令牌", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # JVM 部分",
    "./gradlew :components:checkbox:connectedAndroidTest           # 交互部分（需设备）",
  ),
)
