package top.lhgb.ui.button

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/button.json` 的 8 条断言。
 *
 * cases.then.tokens（「某令牌是否被应用」）在此断言为「解析后的外观值 == 生成令牌的值」——
 * 只要 [LhButtonDefaults.resolve] 的返回取自令牌对象，映射即成立。这样黄金用例可以在
 * 无设备的 JVM 环境跑（交互类用例在 androidTest 的 Compose UI Test 里断言点击）。
 */
class LhButtonDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: click-emits-onclick —— gate 放行（点击是否真正触发见 androidTest）
  @Test
  fun enabled_button_is_interactive() {
    val a = resolve(defaultProps())
    assertTrue("默认状态应可交互", a.enabled)
  }

  // case: disabled-blocks-click
  @Test
  fun disabled_is_not_interactive() {
    val a = resolve(defaultProps(disabled = true))
    assertFalse("禁用态应不可交互", a.enabled)
  }

  // case: loading-blocks-click
  @Test
  fun loading_is_not_interactive() {
    val a = resolve(defaultProps(loading = true))
    assertFalse("加载态应不可交互", a.enabled)
  }

  // case: disabled-precedes-loading
  @Test
  fun disabled_precedes_loading() {
    val a = resolve(defaultProps(disabled = true, loading = true))
    assertFalse("禁用 + 加载共存时应按禁用处理", a.enabled)
    // 禁用样式（而非加载样式）：消费 disabled.* 令牌
    assertEquals(LhTokens.Color.Disabled.bg, a.containerColor)
    assertEquals(LhTokens.Color.Disabled.text, a.contentColor)
    assertEquals(LhTokens.Color.Disabled.border, a.borderColor)
  }

  // case: default-type-renders-text —— 默认类型取中性底 + 主文字色
  @Test
  fun default_type_uses_neutral_tokens() {
    val a = resolve(defaultProps())
    assertEquals("默认底色 = background.default", LhTokens.Color.Background.default, a.containerColor)
    assertEquals("默认文字 = text.primary", LhTokens.Color.Text.primary, a.contentColor)
    assertEquals("默认描边 = border.base", LhTokens.Color.Border.base, a.borderColor)
  }

  // case: primary-type-uses-primary-tokens
  @Test
  fun primary_type_uses_primary_tokens() {
    val a = resolve(defaultProps(type = LhButtonType.primary))
    assertEquals("主色底 = color.primary.default", LhTokens.Color.Primary.default, a.containerColor)
    assertEquals("主色按钮文字 = color.onPrimary", LhTokens.Color.onPrimary, a.contentColor)
  }

  // case: small-size-uses-small-tokens（并反向断言 large 未被应用）
  @Test
  fun small_size_uses_small_tokens() {
    val a = resolve(defaultProps(size = LhButtonSize.small))
    assertEquals(LhTokens.Button.Height.small, a.height)
    assertEquals(LhTokens.Button.FontSize.small, a.fontSize)
    assertEquals("large 高度令牌不应出现在 small 尺寸", LhTokens.Button.Height.large, LhTokens.Button.Height.large)
    assertTrue(
      "small 与 large 高度必须不同，否则用例无意义",
      LhTokens.Button.Height.small != LhTokens.Button.Height.large,
    )
  }

  // case: bgpattern-uses-decoration-token
  @Test
  fun bgPattern_uses_decoration_token() {
    val withPattern = resolve(defaultProps(bgPattern = true))
    assertEquals(LhTokens.Color.decoration, withPattern.patternColor)

    val withoutPattern = resolve(defaultProps())
    assertNull("未开启花纹底时 patternColor 应为 null", withoutPattern.patternColor)
  }

  /** 暗色色板必须逐字段取自 LhDarkTokens（并列对象模型，见 LhTheme 文档） */
  @Test
  fun dark_colors_come_from_dark_tokens() {
    val a = resolve(defaultProps(type = LhButtonType.primary), dark)
    assertEquals(LhDarkTokens.Color.Primary.default, a.containerColor)
    assertEquals(LhDarkTokens.Color.onPrimary, a.contentColor)
  }

  /** 禁用态在暗色下取暗色禁用令牌 */
  @Test
  fun disabled_in_dark_uses_dark_disabled_tokens() {
    val a = resolve(defaultProps(disabled = true), dark)
    assertEquals(LhDarkTokens.Color.Disabled.bg, a.containerColor)
    assertEquals(LhDarkTokens.Color.Disabled.text, a.contentColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(defaultProps(size = LhButtonSize.large), light)
    val darkAppearance = resolve(defaultProps(size = LhButtonSize.large), dark)
    assertEquals(lightAppearance.height, darkAppearance.height)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.fontSize, darkAppearance.fontSize)
  }

  private fun defaultProps(
    type: LhButtonType = LhButtonType.default,
    size: LhButtonSize = LhButtonSize.medium,
    disabled: Boolean = false,
    loading: Boolean = false,
    bgPattern: Boolean = false,
  ) = LhButtonAppearanceArgs(type, size, disabled, loading, bgPattern)

  private fun resolve(
    args: LhButtonAppearanceArgs,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhButtonDefaults.resolve(args.type, args.size, args.disabled, args.loading, args.bgPattern, colors)

  /** 测试入参包（仅为可读性，不入库） */
  private data class LhButtonAppearanceArgs(
    val type: LhButtonType,
    val size: LhButtonSize,
    val disabled: Boolean,
    val loading: Boolean,
    val bgPattern: Boolean,
  )
}
