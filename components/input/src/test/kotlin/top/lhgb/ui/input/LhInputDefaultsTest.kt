package top.lhgb.ui.input

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/input.json` 的 8 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （input-emits-onvaluechange / clearable-emits-empty-value / disabled-blocks-input /
 * readonly-blocks-input / maxlength-truncates-input）在 androidTest 里断言。
 */
class LhInputDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: small-size-uses-small-tokens
  @Test
  fun small_size_uses_small_tokens() {
    val a = resolve(size = LhInputSize.small)
    assertEquals(LhTokens.Input.Height.small, a.height)
    assertEquals(LhTokens.Input.PaddingHorizontal.small, a.paddingHorizontal)
    assertEquals(LhTokens.Input.FontSize.small, a.fontSize)
    assertTrue(
      "small 与 large 高度令牌必须不同，否则用例无意义",
      LhTokens.Input.Height.small != LhTokens.Input.Height.large,
    )
  }

  /** 默认尺寸为 medium（spec 的 size 默认值） */
  @Test
  fun default_size_is_medium() {
    val a = resolve()
    assertEquals(LhTokens.Input.Height.medium, a.height)
    assertEquals(LhTokens.Input.PaddingHorizontal.medium, a.paddingHorizontal)
    assertEquals(LhTokens.Input.FontSize.medium, a.fontSize)
  }

  // case: placeholder-uses-placeholder-token
  @Test
  fun placeholder_uses_placeholder_token() {
    val a = resolve()
    assertEquals(LhTokens.Color.Text.placeholder, a.placeholderColor)
  }

  // case: focus-uses-focus-token —— 聚焦描边取 focus 色 + focusRingWidth 宽
  @Test
  fun focus_uses_focus_tokens() {
    val a = resolve(focused = true)
    assertEquals(LhTokens.Color.focus, a.borderColor)
    assertEquals(LhTokens.Input.focusRingWidth, a.borderWidth)
    // 失焦回 border.base + borderWidth
    val unfocused = resolve(focused = false)
    assertEquals(LhTokens.Color.Border.base, unfocused.borderColor)
    assertEquals(LhTokens.Input.borderWidth, unfocused.borderWidth)
    assertTrue(
      "聚焦描边宽必须与失焦不同，否则用例无意义",
      a.borderWidth != unfocused.borderWidth,
    )
  }

  /** 未聚焦时的中性底色与文字色（spec behaviors） */
  @Test
  fun unfocused_uses_neutral_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Color.Background.default, a.containerColor)
    assertEquals(LhTokens.Color.Text.primary, a.textColor)
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
    assertTrue("只读态 editable 标记仍为 true（由调用方拦截输入）", resolve(readonly = true).editable)
  }

  /** 圆角与清除图标尺寸取令牌 */
  @Test
  fun shape_and_clear_icon_use_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Input.borderRadius, a.borderRadius)
    assertEquals(LhTokens.Input.clearIconSize, a.clearIconSize)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(focused = true, colors = dark)
    assertEquals(LhDarkTokens.Color.focus, a.borderColor)
    assertEquals(LhDarkTokens.Color.Background.default, a.containerColor)
    assertEquals(LhDarkTokens.Color.Text.placeholder, a.placeholderColor)
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
    val lightAppearance = resolve(size = LhInputSize.large, colors = light)
    val darkAppearance = resolve(size = LhInputSize.large, colors = dark)
    assertEquals(lightAppearance.height, darkAppearance.height)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.fontSize, darkAppearance.fontSize)
    // 聚焦描边宽是尺寸类令牌，暗色也不覆盖
    assertEquals(
      resolve(focused = true, colors = light).borderWidth,
      resolve(focused = true, colors = dark).borderWidth,
    )
  }

  private fun resolve(
    size: LhInputSize = LhInputSize.medium,
    disabled: Boolean = false,
    focused: Boolean = false,
    readonly: Boolean = false,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhInputDefaults.resolve(size, disabled, focused, colors)
}
