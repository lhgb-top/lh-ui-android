package top.lhgb.ui.docs.pages

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
  title = "Tag 标签",
  lede = "语义色标签，支持镂空、全圆角标记与关闭按钮。",
  group = DocGroup.BASIC,
  contract = "契约源：`lh-ui-core/specs/tag.json` · 黄金用例：`cases/tag.json`（6 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "类型",
      prose = "六种语义类型决定底色与文字色。",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"成功\") }",
      ),
    ),
    DocSection(
      title = "镂空（plain）",
      prose = "`plain` 为透明底 + 语义色描边与文字。",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "尺寸与标记",
      prose = "三档尺寸映射到 `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` 令牌；`mark` 取高度一半的全圆角。",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "标签文字（便捷写法，与 `content` 二选一）", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "语义类型", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`size`", "尺寸", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "是否镂空（透明底 + 语义色描边）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "是否全圆角（高度一半）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "是否显示关闭按钮", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "关闭回调；`closable` 为 false 时不触发", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "标签内容（覆盖 text，spec 的 default 插槽）", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("值", "底色（非 plain）", "描边/文字（plain）"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + `color.border.base` 描边", "`color.text.primary`")),
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
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "三档高度")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "三档横向内边距")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "三档字号")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "圆角与描边宽度")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "语义底色 / 描边 / 文字")),
    DocRow(cells = listOf("`color.onPrimary`", "非纯文字色")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "禁用态三色")),
    DocRow(cells = listOf("`spacing.xs`", "关闭按钮与文字间距")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "默认类型取中性底 + 主文字色 + 基础描边", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "主色类型消费主色令牌", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain 消费语义浅底令牌", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "小号消费 small 令牌（反向断言 large 未应用）", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round 圆角取高度一半", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "点击关闭触发 onClose", "`LhTagTest.closable_tap_emits_onClose`（Compose UI Test）")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # JVM 部分",
    "./gradlew :components:tag:connectedAndroidTest             # 交互部分（需设备）",
  ),
)
