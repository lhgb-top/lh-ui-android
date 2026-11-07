package top.lhgb.ui.text

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/text.json` 的 5 条断言。
 *
 * 文本组件为纯展示型原语，5 条用例全部为令牌映射，均在 JVM 单测里断言。
 */
class LhTextDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: default-level-uses-base-tokens —— 默认级别消费 base 排版令牌
  @Test
  fun default_level_uses_base_tokens() {
    val a = resolve()
    assertEquals("默认字号 = typography.fontSize.base", LhTokens.Typography.FontSize.base, a.fontSize)
    assertEquals("默认字重 = typography.fontWeight.primary", LhTokens.Typography.FontWeight.primary, a.fontWeight)
    assertEquals("默认文字色 = color.text.primary", LhTokens.Color.Text.primary, a.textColor)
    assertEquals(
      "默认行高 = 字号 × typography.lineHeight.primary",
      LhTokens.Typography.FontSize.base * LhTokens.Typography.LineHeight.primary.toFloat(),
      a.lineHeight,
    )
  }

  // case: large-level-uses-large-token（反向断言 base 未应用）
  @Test
  fun large_level_uses_large_token() {
    val a = resolve(level = LhTextLevel.large)
    assertEquals("大字号 = typography.fontSize.large", LhTokens.Typography.FontSize.large, a.fontSize)
    assertNotEquals("base 字号未应用", LhTokens.Typography.FontSize.base, a.fontSize)
  }

  // case: bold-weight-uses-bold-token（反向断言 primary 未应用）
  @Test
  fun bold_weight_uses_bold_token() {
    val a = resolve(weight = LhTextWeight.bold)
    assertEquals("粗体字重 = typography.fontWeight.bold", LhTokens.Typography.FontWeight.bold, a.fontWeight)
    assertNotEquals("primary 字重未应用", LhTokens.Typography.FontWeight.primary, a.fontWeight)
  }

  // case: secondary-tone-uses-text-secondary
  @Test
  fun secondary_tone_uses_text_secondary() {
    val a = resolve(tone = LhTextTone.secondary)
    assertEquals("次要文字色 = color.text.secondary", LhTokens.Color.Text.secondary, a.textColor)
    assertNotEquals("color.text.primary 未应用", LhTokens.Color.Text.primary, a.textColor)
  }

  /** 全部色调逐条覆盖（spec behaviors 的 tone 映射；selected 取 color.selected） */
  @Test
  fun all_tones_map_to_their_tokens() {
    assertEquals(LhTokens.Color.Text.regular, resolve(tone = LhTextTone.regular).textColor)
    assertEquals(LhTokens.Color.Text.placeholder, resolve(tone = LhTextTone.placeholder).textColor)
    assertEquals(LhTokens.Color.Text.disabled, resolve(tone = LhTextTone.disabled).textColor)
    // selected 取 color.selected 而非 color.text.*
    assertEquals(LhTokens.Color.selected, resolve(tone = LhTextTone.selected).textColor)
    assertNotEquals(
      "selected 不应回落到 text.primary",
      LhTokens.Color.Text.primary,
      resolve(tone = LhTextTone.selected).textColor,
    )
  }

  /** 全部级别逐条覆盖（spec behaviors 的 level 映射） */
  @Test
  fun all_levels_map_to_their_tokens() {
    val expected = listOf(
      LhTextLevel.extraLarge to LhTokens.Typography.FontSize.extraLarge,
      LhTextLevel.large to LhTokens.Typography.FontSize.large,
      LhTextLevel.medium to LhTokens.Typography.FontSize.medium,
      LhTextLevel.base to LhTokens.Typography.FontSize.base,
      LhTextLevel.small to LhTokens.Typography.FontSize.small,
      LhTextLevel.extraSmall to LhTokens.Typography.FontSize.extraSmall,
    )
    for ((level, size) in expected) {
      assertEquals(level.name, size, resolve(level = level).fontSize)
    }
  }

  /** 行高始终 = 字号 × typography.lineHeight.primary，随级别缩放 */
  @Test
  fun line_height_scales_with_level() {
    for (level in LhTextLevel.entries) {
      val a = resolve(level = level)
      assertEquals(
        "lineHeight(${level.name}) = fontSize × lineHeight.primary",
        a.fontSize * LhTokens.Typography.LineHeight.primary.toFloat(),
        a.lineHeight,
      )
    }
  }

  /** 暗色主题下文字色取暗色令牌 */
  @Test
  fun dark_theme_uses_dark_text_colors() {
    val a = resolve(tone = LhTextTone.primary, colors = dark)
    assertEquals(dark.textPrimary, a.textColor)
    assertNotEquals(light.textPrimary, a.textColor)
  }

  private fun resolve(
    level: LhTextLevel = LhTextLevel.base,
    weight: LhTextWeight = LhTextWeight.primary,
    tone: LhTextTone = LhTextTone.primary,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhTextDefaults.resolve(level, weight, tone, colors)
}
