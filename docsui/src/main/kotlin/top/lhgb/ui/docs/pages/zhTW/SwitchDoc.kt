package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Switch 開關",
  lede = "膠囊軌道 + 圓形滑塊的兩態切換控制項，支援載入中佔位。",
  group = DocGroup.FORM,
  contract = "契約源：`lh-ui-core/specs/switch.json` · 黃金用例：`cases/switch.json`（6 條，已翻譯為 JVM 單測 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "狀態",
      prose = "開啟取主色軌道，關閉取描邊色軌道；滑塊直徑 = 高度 - 2 × `switch.gap`，位移動畫取 `motion.duration.base`。",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三檔尺寸對應到 `switch.width` / `switch.height` 令牌。",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "是否開啟（必填）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "開關態變更回呼，攜帶新開關態", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "是否停用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "是否載入中（滑塊位置顯示載入環，不回應點選）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "尺寸", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "修飾符", "`Modifier`", "`Modifier`")),
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
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "三檔軌道寬度")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "三檔軌道高度")),
    DocRow(cells = listOf("`switch.gap`", "滑塊與軌道之間的留白（滑塊直徑 = 高度 - 2 × gap）")),
    DocRow(cells = listOf("`color.primary.default`", "開啟軌道色")),
    DocRow(cells = listOf("`color.border.base`", "關閉軌道色")),
    DocRow(cells = listOf("`color.onPrimary`", "滑塊色")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "停用軌道 / 滑塊色")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "滑塊位移動畫")),
    DocRow(cells = listOf("`loading.strokeWidth`", "載入環描邊寬（複用）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "點選翻轉並發射 onChange", "`LhSwitchTest.tap_unchecked_emits_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "開啟態點選關閉", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "停用態不觸發", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "載入態不觸發", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "開啟消費主色令牌", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "關閉消費描邊令牌", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # JVM 部分",
    "./gradlew :components:switch:connectedAndroidTest            # 互動部分（需裝置）",
  ),
)
