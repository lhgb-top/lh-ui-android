package top.lhgb.ui.checkbox

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/checkbox.json` 的 6 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （tap-toggles-and-emits-onchange / tap-checked-unchecks /
 * disabled-blocks-change / indeterminate-becomes-checked）在 androidTest 里断言。
 */
class LhCheckboxDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: checked-uses-primary-tokens
  @Test
  fun checked_uses_primary_tokens() {
    val a = resolve(checked = true)
    assertEquals("选中底色 = color.primary.default", LhTokens.Color.Primary.default, a.boxColor)
    assertEquals("选中描边 = color.primary.default", LhTokens.Color.Primary.default, a.borderColor)
    assertEquals("选中指示符 = color.onPrimary", LhTokens.Color.onPrimary, a.indicatorColor)
  }

  // case: unchecked-uses-border-token
  @Test
  fun unchecked_uses_border_token() {
    val a = resolve(checked = false)
    assertEquals("未选描边 = color.border.base", LhTokens.Color.Border.base, a.borderColor)
    assertEquals(
      "未选底色应为透明（只描边无填充）",
      androidx.compose.ui.graphics.Color.Transparent,
      a.boxColor,
    )
  }

  /** 半选态同样取 primary 实心底（spec behaviors：选中/不确定实心 primary 底） */
  @Test
  fun indeterminate_uses_primary_tokens() {
    val a = resolve(indeterminate = true)
    assertEquals(LhTokens.Color.Primary.default, a.boxColor)
    assertEquals(LhTokens.Color.onPrimary, a.indicatorColor)
  }

  /** 尺寸映射：small/medium/large 取对应方框尺寸令牌 */
  @Test
  fun sizes_map_to_their_tokens() {
    assertEquals(LhTokens.Checkbox.Size.small, resolve(size = LhCheckboxSize.small).boxSize)
    assertEquals(LhTokens.Checkbox.Size.medium, resolve(size = LhCheckboxSize.medium).boxSize)
    assertEquals(LhTokens.Checkbox.Size.large, resolve(size = LhCheckboxSize.large).boxSize)
    assertTrue(
      "small 与 large 尺寸令牌必须不同，否则用例无意义",
      LhTokens.Checkbox.Size.small != LhTokens.Checkbox.Size.large,
    )
  }

  /** 圆角 / 描边宽 / 勾宽 / 间距取令牌 */
  @Test
  fun shape_and_gap_use_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Checkbox.borderRadius, a.borderRadius)
    assertEquals(LhTokens.Checkbox.borderWidth, a.borderWidth)
    assertEquals(LhTokens.Checkbox.checkWidth, a.checkWidth)
    assertEquals(LhTokens.Checkbox.gap, a.gap)
  }

  @Test
  fun label_color_uses_text_primary_token() {
    assertEquals(LhTokens.Color.Text.primary, resolve().labelColor)
  }

  // case: disabled-blocks-change —— 禁用取 disabled.* 令牌且不可交互
  @Test
  fun disabled_uses_disabled_tokens_and_is_not_enabled() {
    val a = resolve(checked = true, disabled = true)
    assertEquals(LhTokens.Color.Disabled.bg, a.boxColor)
    assertEquals(LhTokens.Color.Disabled.border, a.borderColor)
    assertEquals(LhTokens.Color.Disabled.text, a.indicatorColor)
    assertEquals(LhTokens.Color.Disabled.text, a.labelColor)
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
    assertEquals(LhDarkTokens.Color.Primary.default, a.boxColor)
    assertEquals(LhTokens.Color.onPrimary, a.indicatorColor)
    val u = resolve(checked = false, colors = dark)
    assertEquals(LhDarkTokens.Color.Border.base, u.borderColor)
  }

  /** 暗色禁用态 */
  @Test
  fun dark_disabled_uses_dark_tokens() {
    val a = resolve(checked = true, disabled = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Disabled.bg, a.boxColor)
    assertEquals(LhDarkTokens.Color.Disabled.text, a.labelColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下方框尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(size = LhCheckboxSize.large, colors = light)
    val darkAppearance = resolve(size = LhCheckboxSize.large, colors = dark)
    assertEquals(lightAppearance.boxSize, darkAppearance.boxSize)
    assertEquals(lightAppearance.borderRadius, darkAppearance.borderRadius)
    assertEquals(lightAppearance.checkWidth, darkAppearance.checkWidth)
  }

  private fun resolve(
    size: LhCheckboxSize = LhCheckboxSize.medium,
    checked: Boolean = false,
    indeterminate: Boolean = false,
    disabled: Boolean = false,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhCheckboxDefaults.resolve(size, checked, indeterminate, disabled, colors)
}
