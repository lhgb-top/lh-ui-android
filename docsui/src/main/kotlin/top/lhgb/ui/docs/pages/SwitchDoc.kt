package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Switch 开关",
  lede = "胶囊轨道 + 圆形滑块的两态切换控件，支持加载态占位。",
  group = DocGroup.FORM,
  contract = "契约源：`lh-ui-core/specs/switch.json` · 黄金用例：`cases/switch.json`（6 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "状态",
      prose = "开启取主色轨道，关闭取描边色轨道；滑块直径 = 高度 - 2 × `switch.gap`，位移动画取 `motion.duration.base`。",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三档尺寸映射到 `switch.width` / `switch.height` 令牌。",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "是否开启（必填）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "开关态变更回调，携带新开关态", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "是否禁用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "是否加载中（滑块位置显示加载环，不响应点击）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`size`", "尺寸", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("值", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small`（36dp）", "`switch.height.small`（20dp）")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium`（44dp）", "`switch.height.medium`（24dp）")),
        DocRow(cells = listOf("`Large`", "`switch.width.large`（52dp）", "`switch.height.large`（28dp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "三档轨道宽度")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "三档轨道高度")),
    DocRow(cells = listOf("`switch.gap`", "滑块与轨道之间的留白（滑块直径 = 高度 - 2 × gap）")),
    DocRow(cells = listOf("`color.primary.default`", "开启轨道色")),
    DocRow(cells = listOf("`color.border.base`", "关闭轨道色")),
    DocRow(cells = listOf("`color.onPrimary`", "滑块色")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "禁用轨道 / 滑块色")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "滑块位移动画")),
    DocRow(cells = listOf("`loading.strokeWidth`", "加载环描边宽（复用）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "点击翻转并发射 onChange", "`LhSwitchTest.tap_unchecked_emits_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "开启态点击关闭", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "禁用态不触发", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "加载态不触发", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "开启消费主色令牌", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "关闭消费描边令牌", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # JVM 部分",
    "./gradlew :components:switch:connectedAndroidTest            # 交互部分（需设备）",
  ),
)
