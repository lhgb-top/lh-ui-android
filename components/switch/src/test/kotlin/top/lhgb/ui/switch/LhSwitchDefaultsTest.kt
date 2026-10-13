package top.lhgb.ui.switch

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/switch.json` 的 6 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （tap-toggles-and-emits-onchange / tap-checked-turns-off /
 * disabled-blocks-change / loading-blocks-change）在 androidTest 里断言。
 */
class LhSwitchDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: checked-uses-primary-token（并反向断言 border.base 未被应用）
  @Test
  fun checked_uses_primary_token() {
    val a = resolve(checked = true)
    assertEquals("开启轨道 = color.primary.default", LhTokens.Color.Primary.default, a.trackColor)
    assertEquals("滑块 = color.onPrimary", LhTokens.Color.onPrimary, a.thumbColor)
    assertTrue("开启态轨道不应取描边令牌", a.trackColor != LhTokens.Color.Border.base)
  }

  // case: unchecked-uses-border-token（并反向断言 primary 未被应用）
  @Test
  fun unchecked_uses_border_token() {
    val a = resolve(checked = false)
    assertEquals("关闭轨道 = color.border.base", LhTokens.Color.Border.base, a.trackColor)
    assertTrue("关闭态轨道不应取主色令牌", a.trackColor != LhTokens.Color.Primary.default)
  }

  @Test
  fun sizes_map_to_their_tokens() {
    val small = resolve(size = LhSwitchSize.small)
    val medium = resolve(size = LhSwitchSize.medium)
    val large = resolve(size = LhSwitchSize.large)
    assertEquals(LhTokens.Switch.Width.small, small.width)
    assertEquals(LhTokens.Switch.Height.small, small.height)
    assertEquals(LhTokens.Switch.Width.medium, medium.width)
    assertEquals(LhTokens.Switch.Height.medium, medium.height)
    assertEquals(LhTokens.Switch.Width.large, large.width)
    assertEquals(LhTokens.Switch.Height.large, large.height)
    assertTrue(
      "small 与 large 宽度令牌必须不同，否则用例无意义",
      LhTokens.Switch.Width.small != LhTokens.Switch.Width.large,
    )
  }

  /** 滑块直径 = 高度 - 2 × gap 令牌 */
  @Test
  fun thumb_size_is_height_minus_two_gaps() {
    val a = resolve(size = LhSwitchSize.large)
    assertEquals(
      LhTokens.Switch.Height.large - (LhTokens.Switch.gap * 2),
      a.thumbSize,
    )
  }

  @Test
  fun loading_color_is_primary_token() {
    assertEquals(LhTokens.Color.Primary.default, resolve().loadingColor)
  }

  @Test
  fun enabled_by_default() {
    assertTrue("默认状态应可交互", resolve().enabled)
  }

  // case: disabled-blocks-change
  @Test
  fun disabled_uses_disabled_tokens_and_is_not_enabled() {
    val a = resolve(checked = true, disabled = true)
    assertEquals(LhTokens.Color.Disabled.bg, a.trackColor)
    assertEquals(LhTokens.Color.Text.disabled, a.thumbColor)
    assertFalse("禁用态应不可交互", a.enabled)
  }

  // case: loading-blocks-change
  @Test
  fun loading_is_not_enabled() {
    val a = resolve(loading = true)
    assertFalse("加载态应不可交互", a.enabled)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(checked = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Primary.default, a.trackColor)
    assertEquals(LhTokens.Color.onPrimary, a.thumbColor)
    val u = resolve(checked = false, colors = dark)
    assertEquals(LhDarkTokens.Color.Border.base, u.trackColor)
  }

  @Test
  fun dark_disabled_uses_dark_tokens() {
    val a = resolve(checked = true, disabled = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Disabled.bg, a.trackColor)
    assertEquals(LhDarkTokens.Color.Text.disabled, a.thumbColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下轨道尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(size = LhSwitchSize.large, colors = light)
    val darkAppearance = resolve(size = LhSwitchSize.large, colors = dark)
    assertEquals(lightAppearance.width, darkAppearance.width)
    assertEquals(lightAppearance.height, darkAppearance.height)
    assertEquals(lightAppearance.thumbSize, darkAppearance.thumbSize)
  }

  private fun resolve(
    size: LhSwitchSize = LhSwitchSize.medium,
    checked: Boolean = false,
    disabled: Boolean = false,
    loading: Boolean = false,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhSwitchDefaults.resolve(size, checked, disabled, loading, colors)
}
