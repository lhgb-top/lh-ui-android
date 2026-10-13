package top.lhgb.ui.navbar

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/navbar.json` 的 6 条断言。
 *
 * 渲染类用例（title-renders-text / bordered-uses-border-token /
 * height-uses-navbar-height-token）在此断言为「解析后的外观值 == 生成令牌的值」；
 * 交互类用例（leftclick-emits-onleftclick / rightclick-emits-onrightclick /
 * no-left-content-no-onleftclick）在 androidTest 里断言。
 */
class LhNavbarDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: height-uses-navbar-height-token
  @Test
  fun height_uses_navbar_height_token() {
    assertEquals(LhTokens.Navbar.height, resolve().height)
  }

  @Test
  fun layout_uses_navbar_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Navbar.paddingHorizontal, a.paddingHorizontal)
    assertEquals(LhTokens.Navbar.titleFontSize, a.titleFontSize)
    assertEquals(LhTokens.Navbar.iconSize, a.iconSize)
    assertEquals(LhTokens.ZIndex.sticky, a.zIndex)
  }

  @Test
  fun colors_use_global_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Color.Background.default, a.backgroundColor)
    assertEquals(LhTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhTokens.Color.Text.primary, a.sideColor)
  }

  // case: bordered-uses-border-token
  @Test
  fun bordered_uses_border_token() {
    val a = resolve(bordered = true)
    assertEquals("分隔线色 = color.border.lighter", LhTokens.Color.Border.lighter, a.dividerColor)
    assertEquals("分隔线宽 = navbar.borderWidth", LhTokens.Navbar.borderWidth, a.borderWidth)
  }

  @Test
  fun not_bordered_has_no_divider() {
    val a = resolve(bordered = false)
    assertEquals("无分隔线时应透明（不绘制）", Color.Transparent, a.dividerColor)
    assertEquals(Dp.Unspecified, a.borderWidth)
  }

  // case: title-renders-text —— 标题样式取标题令牌（文字渲染见 androidTest）
  @Test
  fun title_appearance_uses_title_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Navbar.titleFontSize, a.titleFontSize)
    assertEquals(LhTokens.Color.Text.primary, a.titleColor)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(colors = dark)
    assertEquals(LhDarkTokens.Color.Background.default, a.backgroundColor)
    assertEquals(LhDarkTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhDarkTokens.Color.Border.lighter, a.dividerColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下导航栏尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(colors = light)
    val darkAppearance = resolve(colors = dark)
    assertEquals(lightAppearance.height, darkAppearance.height)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.titleFontSize, darkAppearance.titleFontSize)
    assertEquals(lightAppearance.iconSize, darkAppearance.iconSize)
  }

  private fun resolve(
    bordered: Boolean = true,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhNavbarDefaults.resolve(bordered, colors)
}
