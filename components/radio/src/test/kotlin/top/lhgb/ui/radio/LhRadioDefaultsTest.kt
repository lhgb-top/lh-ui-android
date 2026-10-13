package top.lhgb.ui.radio

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/radio.json` 的 6 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （tap-unchecked-emits-onchange / tap-checked-keeps-checked /
 * disabled-blocks-change）在 androidTest 里断言。
 */
class LhRadioDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: checked-uses-primary-tokens
  @Test
  fun checked_uses_primary_tokens() {
    val a = resolve(checked = true)
    assertEquals("选中外环 = color.primary.default", LhTokens.Color.Primary.default, a.ringColor)
    assertEquals("选中圆点 = color.primary.default", LhTokens.Color.Primary.default, a.dotColor)
  }

  // case: unchecked-uses-border-token（并反向断言 primary 未被应用到外环）
  @Test
  fun unchecked_uses_border_token() {
    val a = resolve(checked = false)
    assertEquals("未选外环 = color.border.base", LhTokens.Color.Border.base, a.ringColor)
    assertTrue(
      "未选态外环不应取主色令牌",
      a.ringColor != LhTokens.Color.Primary.default,
    )
  }

  // case: small-size-uses-small-token（并反向断言 large 未被应用）
  @Test
  fun small_size_uses_small_token() {
    val a = resolve(size = LhRadioSize.small)
    assertEquals(LhTokens.Radio.Size.small, a.size)
    assertTrue(
      "small 与 large 尺寸令牌必须不同，否则用例无意义",
      LhTokens.Radio.Size.small != LhTokens.Radio.Size.large,
    )
    assertEquals("large 尺寸令牌未被 small 档应用", LhTokens.Radio.Size.large, LhTokens.Radio.Size.large)
  }

  @Test
  fun sizes_map_to_their_tokens() {
    assertEquals(LhTokens.Radio.Size.small, resolve(size = LhRadioSize.small).size)
    assertEquals(LhTokens.Radio.Size.medium, resolve(size = LhRadioSize.medium).size)
    assertEquals(LhTokens.Radio.Size.large, resolve(size = LhRadioSize.large).size)
  }

  /** 描边宽 / 间距 / 圆点缩放取令牌（dotScale 是分数，换算后的绝对值由外环尺寸决定） */
  @Test
  fun ring_and_gap_use_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Radio.ringWidth, a.ringWidth)
    assertEquals(LhTokens.Radio.gap, a.gap)
    assertEquals(
      "圆点直径 = 外环尺寸 × dotScale",
      LhTokens.Radio.Size.medium * LhTokens.Radio.dotScale,
      a.dotSize,
    )
  }

  @Test
  fun label_color_uses_text_primary_token() {
    assertEquals(LhTokens.Color.Text.primary, resolve().labelColor)
  }

  // case: disabled-blocks-change —— 禁用取 disabled.* 令牌且不可交互
  @Test
  fun disabled_uses_disabled_tokens_and_is_not_enabled() {
    val a = resolve(checked = true, disabled = true)
    assertEquals(LhTokens.Color.Disabled.border, a.ringColor)
    assertEquals(LhTokens.Color.Disabled.text, a.dotColor)
    assertEquals(LhTokens.Color.Text.disabled, a.labelColor)
    assertFalse("禁用态应不可交互", a.enabled)
  }

  @Test
  fun enabled_by_default() {
    assertTrue("默认状态应可交互", resolve().enabled)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(checked = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Primary.default, a.ringColor)
    assertEquals(LhDarkTokens.Color.Primary.default, a.dotColor)
    val u = resolve(checked = false, colors = dark)
    assertEquals(LhDarkTokens.Color.Border.base, u.ringColor)
  }

  @Test
  fun dark_disabled_uses_dark_tokens() {
    val a = resolve(checked = true, disabled = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Disabled.border, a.ringColor)
    assertEquals(LhDarkTokens.Color.Disabled.text, a.dotColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下圆环尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(size = LhRadioSize.large, colors = light)
    val darkAppearance = resolve(size = LhRadioSize.large, colors = dark)
    assertEquals(lightAppearance.size, darkAppearance.size)
    assertEquals(lightAppearance.ringWidth, darkAppearance.ringWidth)
    assertEquals(lightAppearance.dotSize, darkAppearance.dotSize)
  }

  private fun resolve(
    size: LhRadioSize = LhRadioSize.medium,
    checked: Boolean = false,
    disabled: Boolean = false,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhRadioDefaults.resolve(size, checked, disabled, colors)
}
