package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "主题与暗色",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "令牌模型",
      prose = "原生血脉的令牌真源在 `lh-ui-core/tokens/*.json`，codegen 生成 Android 产物：\n这与 ios / harmony / windows 一致（并列对象），与 docs 端（浅合并 JSON）形态不同。\n主题层据此设计：`LhColors` 只承载颜色，尺寸由组件直接取 `LhTokens`。",
    ),
    DocSection(
      title = "LhColors",
      prose = "`LightLhColors` / `DarkLhColors` 把生成的两个 object 平铺为一份不可变色板：",
      code = listOf(
        "@Immutable",
        "data class LhColors(",
        "  val primary: Color, val onPrimary: Color,",
        "  val success: Color, val warning: Color, val danger: Color, val info: Color,",
        "  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */",
        ")",
        "",
        "val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)",
        "val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)",
      ),
    ),
    DocSection(
      title = "LhTheme",
      prose = "无 `LhTheme` 包裹时回退 `LightLhColors`（缺省即明色）。",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // LocalLhColors 注入 DarkLhColors，内部组件自动消费",
        "}",
      ),
    ),
    DocSection(
      title = "暗色预计算（重要）",
      prose = "web 血脉的暗色用 CSS `color-mix()` 动态求值，原生无 CSS 级联，故 core 已把暗色表达式\n预计算为字面量（如 `rgba(61,100,158,0.25)`、`#B1C1D8`）。后果（总纲 R-n6）：\n改预设主色时，暗色预计算值不会自动跟随，必须重新人工计算并更新\n`lh-ui-core/tokens/color-dark.json`，同步刷新《设计语言基线》。",
    ),
    DocSection(
      title = "令牌速查",
      prose = "完整令牌表（明/暗/说明）见 lh-ui-core 令牌速查，\n或在 core 仓跑 `pnpm codegen` 后查看 `dist/docs/tokens.md`。组件级令牌映射见\nButton 令牌对照。",
    ),
    DocSection(
      title = "自定义预设（预留）",
      prose = "`LhTheme(colors = ...)` 支持传入自定义色板，预设主题能力将在后续版本随 core 的\n主题预设令牌一起开放。",
    ),
  ),
)
