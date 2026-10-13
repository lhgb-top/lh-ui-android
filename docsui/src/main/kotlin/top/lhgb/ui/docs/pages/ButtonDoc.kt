package top.lhgb.ui.docs.pages

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
  title = "Button 按钮",
  lede = "基础操作按钮。触发即时操作，支持类型、尺寸、加载、禁用、图标与国风花纹底。",
  group = DocGroup.BASIC,
  contract = "契约源：`lh-ui-core/specs/button.json` · 黄金用例：`cases/button.json`（8 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "类型",
      prose = "六种类型决定底色与文字色。",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, onClick = {}) { Text(\"主要按钮\") }",
      ),
    ),
    DocSection(
      title = "尺寸",
      prose = "三档尺寸映射到 `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` 令牌。",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "状态",
      prose = "禁用与加载中都不触发 `onClick`；两者共存时按禁用优先处理（黄金用例 `disabled-precedes-loading`）。",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "图标与花纹底",
      prose = "图标名按本地图标体系解析（`LhButtonIcons`，当前支持 search / refresh / check / close，未登记名不绘制）。\n花纹底消费 `color.decoration` 令牌，叠在底色之上、文字之下，不影响对比度。",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "明暗主题",
      prose = "切换站点右上角主题，模拟器手机框内的按钮颜色即变；真机通过 `LhTheme(isDark = true)` 切换。",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`onClick`", "点击回调；`disabled` 或 `loading` 为 true 时不触发", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "按钮类型，决定底色与文字色", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`size`", "按钮尺寸，映射到尺寸令牌", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "是否禁用", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "是否加载中（显示指示器，不触发 onClick）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "图标名（本地图标体系解析）", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "是否绘制国风花纹底纹", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "按钮文字内容（spec 的 default 插槽）", "`@Composable () -> Unit`", "—")),
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
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "三档高度")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "三档横向内边距")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "三档字号")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "三档图标尺寸")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "圆角（跟随 `radius.base`）与描边宽度")),
    DocRow(cells = listOf("`button.fontWeight`", "字重（跟随 `typography.fontWeight.primary`）")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "主色类型底/文字")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "语义类型底色")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "默认类型底/文字/描边")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "禁用态三色")),
    DocRow(cells = listOf("`color.decoration`", "花纹底纹色")),
    DocRow(cells = listOf("`shadow.lighter`", "默认类型 elevation")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "按压反馈动效")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "点击触发 onClick ×1", "`LhButtonTest.click_emits_onClick`（Compose UI Test）")),
    DocRow(cells = listOf("`disabled-blocks-click`", "禁用态不触发", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "加载态不触发", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "禁用优先", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "默认类型渲染文字", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "主色类型消费主色令牌", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小号消费 small 尺寸令牌", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "花纹底消费装饰色", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "已登记图标名渲染矢量；未登记名不绘制", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # JVM 部分",
    "./gradlew :components:button:connectedAndroidTest        # 交互部分（需设备）",
  ),
)
