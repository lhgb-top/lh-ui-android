package top.lhgb.ui.textarea

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/textarea.json` 的 6 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （input-emits-onvaluechange / disabled-blocks-input / maxlength-truncates-input）
 * 在 androidTest 里断言。
 */
class LhTextareaDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  /** 尺寸与字号取令牌（spec tokenRefs） */
  @Test
  fun dimensions_use_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Textarea.paddingHorizontal, a.paddingHorizontal)
    assertEquals(LhTokens.Textarea.paddingVertical, a.paddingVertical)
    assertEquals(LhTokens.Textarea.fontSize, a.fontSize)
    assertEquals(LhTokens.Textarea.minHeight, a.minHeight)
    // case: medium-size-uses-base-tokens 的等价断言：字号取 typography 基准
    assertEquals(LhTokens.Typography.FontSize.base, a.fontSize)
  }

  // case: focus-uses-focus-token
  @Test
  fun focus_uses_focus_tokens() {
    val a = resolve(focused = true)
    assertEquals(LhTokens.Color.focus, a.borderColor)
    assertEquals(LhTokens.Textarea.focusRingWidth, a.borderWidth)
    val unfocused = resolve(focused = false)
    assertEquals(LhTokens.Color.Border.base, unfocused.borderColor)
    assertEquals(LhTokens.Textarea.borderWidth, unfocused.borderWidth)
    assertTrue(
      "聚焦描边宽必须与失焦不同，否则用例无意义",
      a.borderWidth != unfocused.borderWidth,
    )
  }

  // case: disabled-blocks-input —— 禁用取 disabled.* 令牌且不可编辑
  @Test
  fun disabled_uses_disabled_tokens_and_is_not_editable() {
    val a = resolve(disabled = true)
    assertEquals(LhTokens.Color.Disabled.bg, a.containerColor)
    assertEquals(LhTokens.Color.Disabled.text, a.textColor)
    assertEquals(LhTokens.Color.Disabled.border, a.borderColor)
    assertFalse("禁用态应不可编辑", a.editable)
  }

  @Test
  fun enabled_is_editable() {
    assertTrue("默认状态应可编辑", resolve().editable)
  }

  /** 占位色取令牌（spec behaviors） */
  @Test
  fun placeholder_uses_placeholder_token() {
    assertEquals(LhTokens.Color.Text.placeholder, resolve().placeholderColor)
  }

  // case: showcount-renders-count —— 字数统计取 countFontSize 与次要文字色
  @Test
  fun count_uses_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Textarea.countFontSize, a.countFontSize)
    assertEquals(LhTokens.Color.Text.secondary, a.countColor)
  }

  // case: showcount-renders-count —— 格式「当前/最大」
  @Test
  fun count_text_with_maxlength() {
    assertEquals("2/10", LhTextareaDefaults.countText("两行", 10))
  }

  // case: showcount-without-maxlength-renders-length-only
  @Test
  fun count_text_without_maxlength_shows_length_only() {
    assertEquals("3", LhTextareaDefaults.countText("三行文", null))
  }

  /** 行高取 typography.lineHeight.primary 令牌 */
  @Test
  fun line_height_uses_token() {
    val a = resolve()
    assertEquals(LhTokens.Typography.LineHeight.primary.toFloat(), a.lineHeight, 0.001f)
    assertTrue("行高必须 > 1", a.lineHeight > 1f)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(focused = true, colors = dark)
    assertEquals(LhDarkTokens.Color.focus, a.borderColor)
    assertEquals(LhDarkTokens.Color.Background.default, a.containerColor)
    assertEquals(LhDarkTokens.Color.Text.secondary, a.countColor)
  }

  /** 暗色禁用态 */
  @Test
  fun dark_disabled_uses_dark_tokens() {
    val a = resolve(disabled = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Disabled.bg, a.containerColor)
    assertEquals(LhDarkTokens.Color.Disabled.text, a.textColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(colors = light)
    val darkAppearance = resolve(colors = dark)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.paddingVertical, darkAppearance.paddingVertical)
    assertEquals(lightAppearance.fontSize, darkAppearance.fontSize)
    assertEquals(lightAppearance.minHeight, darkAppearance.minHeight)
    assertEquals(
      resolve(focused = true, colors = light).borderWidth,
      resolve(focused = true, colors = dark).borderWidth,
    )
  }

  private fun resolve(
    disabled: Boolean = false,
    focused: Boolean = false,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhTextareaDefaults.resolve(disabled, focused, colors)
}
