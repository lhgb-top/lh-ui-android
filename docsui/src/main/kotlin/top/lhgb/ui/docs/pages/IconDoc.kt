package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "Icon 图标",
  lede = "图标基座组件。其他组件（cell / input / navbar 等）的图标都经由本组件的本地注册表渲染。",
  group = DocGroup.BASIC,
  contract = "契约源：`lh-ui-core/specs/icon.json` · 黄金用例：`cases/icon.json`（5 条，已翻译为 JVM 单测 + Compose UI Test）",
  sections = listOf(
    DocSection(
      title = "尺寸与色调",
      prose = "三档尺寸映射到 `icon.size.*` 令牌；色调 `default` 取 `color.text.primary`，其余取语义色。",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", size = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "语义色调",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "图标注册表",
      prose = "`LhIcons` 是本地图标名 → Material `ImageVector` 的注册表，未登记的图标名返回 `null`（不绘制，不抛异常）：",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "图标名（`LhIcons` 注册表解析）", "`String`", "—")),
    DocRow(cells = listOf("`size`", "图标尺寸", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "语义色调", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "无障碍描述；默认取图标名，显式传 `null` 声明为装饰性图标", "`String?`", "`name`")),
    DocRow(cells = listOf("`onClick`", "点击回调；为 null 时不可点击", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修饰符", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("值", "尺寸令牌"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`icon.size.large`（24dp）")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("值", "颜色令牌"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "三档尺寸")),
    DocRow(cells = listOf("`color.text.primary`", "default 色调")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "语义色调")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "regular / secondary 色调")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "点击触发 onClick", "`LhIconTest.click_emits_onClick`（Compose UI Test）")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "默认色调取主文字色令牌", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 色调取主色令牌", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "小号取 small 尺寸令牌（反向断言 large 未应用）", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "已登记图标名解析为矢量；未登记名不绘制", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # JVM 部分",
    "./gradlew :components:icon:connectedAndroidTest            # 交互部分（需设备）",
  ),
)
