package top.lhgb.ui.cell

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/cell.json` 的 6 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （clickable-emits-onclick / not-clickable-no-onclick）在 androidTest 里断言点击。
 */
class LhCellDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: title-and-value-render —— 标题与值取对应字号令牌
  @Test
  fun title_and_value_use_font_size_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Cell.fontSizeTitle, a.titleFontSize)
    assertEquals(LhTokens.Cell.fontSizeValue, a.valueFontSize)
    // case: label-renders-secondary-tone
    assertEquals(LhTokens.Cell.fontSizeLabel, a.labelFontSize)
  }

  // case: label-renders-secondary-tone —— 说明取次要文字色令牌
  @Test
  fun label_uses_secondary_text_token() {
    val a = resolve()
    assertEquals(LhTokens.Color.Text.secondary, a.labelColor)
  }

  /** 标题取主要文字色，值取次要文字色（spec behaviors） */
  @Test
  fun title_and_value_colors_use_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhTokens.Color.Text.secondary, a.valueColor)
  }

  // case: required-uses-danger-token
  @Test
  fun required_uses_danger_token() {
    val a = resolve(required = true)
    assertEquals(LhTokens.Color.Danger.default, a.requiredColor)
    // required=false 时也应有兜底色（渲染侧按 required 决定是否画）
    assertEquals(LhTokens.Color.Danger.default, resolve().requiredColor)
  }

  // case: bordered-emits-line-command —— 分隔线取 border.lighter 令牌
  @Test
  fun bordered_uses_border_lighter_token() {
    val a = resolve(bordered = true)
    assertEquals(LhTokens.Color.Border.lighter, a.dividerColor)
    assertEquals(LhTokens.Cell.borderWidth, a.dividerWidth)
    assertTrue("分隔线宽必须是正数", a.dividerWidth.value > 0f)
  }

  /** 内边距与间距取令牌（spec tokenRefs） */
  @Test
  fun paddings_and_gap_use_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Cell.paddingHorizontal, a.paddingHorizontal)
    assertEquals(LhTokens.Cell.paddingVertical, a.paddingVertical)
    assertEquals(LhTokens.Cell.gap, a.gap)
  }

  /** 图标尺寸取 icon.size.medium 令牌（spec behaviors） */
  @Test
  fun icon_size_uses_token() {
    val a = resolve()
    assertEquals(LhTokens.Icon.Size.medium, a.iconSize)
  }

  /** 行高取 typography.lineHeight.primary 令牌（spec tokenRefs） */
  @Test
  fun line_height_uses_token() {
    val a = resolve()
    assertEquals(LhTokens.Typography.LineHeight.primary.toFloat(), a.lineHeight, 0.001f)
    assertTrue("行高必须 > 1（行盒高于字号）", a.lineHeight > 1f)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(required = true, bordered = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhDarkTokens.Color.Text.secondary, a.labelColor)
    assertEquals(LhDarkTokens.Color.Danger.default, a.requiredColor)
    assertEquals(LhDarkTokens.Color.Border.lighter, a.dividerColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(colors = light)
    val darkAppearance = resolve(colors = dark)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.paddingVertical, darkAppearance.paddingVertical)
    assertEquals(lightAppearance.titleFontSize, darkAppearance.titleFontSize)
    assertEquals(lightAppearance.dividerWidth, darkAppearance.dividerWidth)
  }

  private fun resolve(
    required: Boolean = false,
    bordered: Boolean = true,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhCellDefaults.resolve(required, bordered, colors)
}
