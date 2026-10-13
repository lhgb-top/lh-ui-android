package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Radio 单选框",
  lede = "受控单选框，选中后再次点击不会取消选中（单选语义）。",
  group = DocGroup.FORM,
  contract = "契约源：`lh-ui-core/specs/radio.json` · 黄金用例：`cases/radio.json`（6 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "状态与单选组",
      prose = "组件本身不含组逻辑，单选互斥由宿主维护（用 `value` 标识组内项）。",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"选项一\" to \"a\", \"选项二\" to \"b\").forEach { (text, value) ->",
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
      prose = "三档圆环尺寸映射到 `radio.size.*` 令牌；内圆点直径 = 尺寸 × `radio.dotScale`。",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "是否选中（必填）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "由未选中变为选中时触发，携带 `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "是否禁用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "标签文字", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "单选值（组内标识，组件本身不含组逻辑）", "`String?`", "`null`")),
    DocRow(cells = listOf("`size`", "圆环尺寸", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "标签内容（覆盖 label，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("值", "尺寸令牌", "内圆点直径"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small`（16dp）", "16dp × `radio.dotScale`（0.5）")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium`（20dp）", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large`（24dp）", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "三档圆环尺寸")),
    DocRow(cells = listOf("`radio.ringWidth`", "外环描边宽")),
    DocRow(cells = listOf("`radio.dotScale`", "内圆点相对外环直径的比例（分数）")),
    DocRow(cells = listOf("`radio.gap`", "圆环与标签间距")),
    DocRow(cells = listOf("`color.primary.default`", "选中描边与圆点色")),
    DocRow(cells = listOf("`color.border.base`", "未选描边色")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "禁用描边 / 圆点 / 文字色")),
    DocRow(cells = listOf("`color.text.primary`", "标签色")),
    DocRow(cells = listOf("`typography.fontSize.base`", "标签字号")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "未选点击触发 onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "已选点击不触发", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "禁用态不触发", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "选中消费主色令牌", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "未选消费描边令牌", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "小号消费 small 尺寸令牌", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # JVM 部分",
    "./gradlew :components:radio:connectedAndroidTest             # 交互部分（需设备）",
  ),
)
