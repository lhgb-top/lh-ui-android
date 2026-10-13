package top.lhgb.ui.tag

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/tag.json` 的 6 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （closable-tap-emits-onclose）在 androidTest 的 Compose UI Test 里断言点击。
 */
class LhTagDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: default-type-renders-text —— 默认类型取中性底 + 主文字色 + 基础描边
  @Test
  fun default_type_uses_neutral_tokens() {
    val a = resolve()
    assertEquals("默认底色 = background.default", LhTokens.Color.Background.default, a.containerColor)
    assertEquals("默认文字 = text.primary", LhTokens.Color.Text.primary, a.contentColor)
    assertEquals("默认描边 = border.base", LhTokens.Color.Border.base, a.borderColor)
  }

  // case: primary-type-uses-primary-tokens
  @Test
  fun primary_type_uses_primary_tokens() {
    val a = resolve(type = LhTagType.primary)
    assertEquals("主色底 = color.primary.default", LhTokens.Color.Primary.default, a.containerColor)
    assertEquals("主色标签文字 = color.onPrimary", LhTokens.Color.onPrimary, a.contentColor)
  }

  // case: plain-uses-bg-tokens
  @Test
  fun plain_uses_bg_tokens() {
    val a = resolve(type = LhTagType.success, plain = true)
    assertEquals("浅底 = color.success.bg", LhTokens.Color.Success.bg, a.containerColor)
    assertEquals("浅底文字 = color.success.default", LhTokens.Color.Success.default, a.contentColor)
    assertEquals("浅底描边 = color.success.default", LhTokens.Color.Success.default, a.borderColor)
  }

  /** 实底形态下其余语义类型逐条覆盖（spec behaviors 的 type 映射） */
  @Test
  fun all_solid_types_map_to_their_tokens() {
    assertEquals(LhTokens.Color.Success.default, resolve(type = LhTagType.success).containerColor)
    assertEquals(LhTokens.Color.Warning.default, resolve(type = LhTagType.warning).containerColor)
    assertEquals(LhTokens.Color.Danger.default, resolve(type = LhTagType.danger).containerColor)
    assertEquals(LhTokens.Color.Info.default, resolve(type = LhTagType.info).containerColor)
    // 实底语义类型无描边
    assertEquals("实底语义类型描边应为透明", androidx.compose.ui.graphics.Color.Transparent, resolve(type = LhTagType.primary).borderColor)
  }

  // case: small-size-uses-small-tokens（反向断言 large 未应用）
  @Test
  fun small_size_uses_small_tokens() {
    val a = resolve(size = LhTagSize.small)
    assertEquals(LhTokens.Tag.Height.small, a.height)
    assertEquals(LhTokens.Tag.PaddingHorizontal.small, a.paddingHorizontal)
    assertEquals(LhTokens.Tag.FontSize.small, a.fontSize)
    assertTrue(
      "small 与 large 高度令牌必须不同，否则用例无意义",
      LhTokens.Tag.Height.small != LhTokens.Tag.Height.large,
    )
  }

  // case: round-makes-radius-half-height
  @Test
  fun round_makes_radius_half_height() {
    val a = resolve(round = true)
    assertEquals("胶囊态圆角 = 高度 / 2", a.height / 2f, a.borderRadius)
    assertNotRoundRadius(a)
  }

  @Test
  fun non_round_uses_border_radius_token() {
    val a = resolve(round = false)
    assertEquals(LhTokens.Tag.borderRadius, a.borderRadius)
  }

  /** 关闭符与文字间距取 spacing.xs（spec behaviors） */
  @Test
  fun close_gap_uses_spacing_xs_token() {
    val a = resolve()
    assertEquals(LhTokens.Spacing.xs, a.closeGap)
    assertTrue("关闭符尺寸应为正", a.closeSize.value > 0f)
  }

  /** 暗色色板下色调取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(type = LhTagType.primary, colors = dark)
    assertEquals(LhDarkTokens.Color.Primary.default, a.containerColor)
    assertEquals(LhTokens.Color.onPrimary, a.contentColor)
  }

  /** 暗色浅底形态 */
  @Test
  fun dark_plain_uses_dark_bg_tokens() {
    val a = resolve(type = LhTagType.success, plain = true, colors = dark)
    assertEquals(LhDarkTokens.Color.Success.bg, a.containerColor)
    assertEquals(LhDarkTokens.Color.Success.default, a.contentColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(size = LhTagSize.large, colors = light)
    val darkAppearance = resolve(size = LhTagSize.large, colors = dark)
    assertEquals(lightAppearance.height, darkAppearance.height)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.fontSize, darkAppearance.fontSize)
    // round 态圆角由高度派生，也应一致
    assertEquals(
      resolve(size = LhTagSize.large, round = true, colors = light).borderRadius,
      resolve(size = LhTagSize.large, round = true, colors = dark).borderRadius,
    )
  }

  private fun assertNotRoundRadius(a: LhTagAppearance) {
    assertTrue(
      "胶囊态圆角不应等于默认 borderRadius 令牌（高度一半更大）",
      a.borderRadius != LhTokens.Tag.borderRadius,
    )
  }

  private fun resolve(
    type: LhTagType = LhTagType.default,
    size: LhTagSize = LhTagSize.medium,
    plain: Boolean = false,
    round: Boolean = false,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhTagDefaults.resolve(type, size, plain, round, colors)
}
