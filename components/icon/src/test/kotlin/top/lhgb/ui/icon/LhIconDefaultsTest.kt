package top.lhgb.ui.icon

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/icon.json` 的 5 条断言。
 *
 * 令牌类用例在此断言为「解析后的外观值 == 生成令牌的值」——只要
 * [LhIconDefaults.resolve] 的返回取自令牌对象，映射即成立。这样黄金用例
 * 可以在无设备的 JVM 环境跑（交互类用例在 androidTest 的 Compose UI Test 里断言点击）。
 */
class LhIconDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: default-tone-uses-text-primary
  @Test
  fun default_tone_uses_text_primary_token() {
    val a = resolve(tone = LhIconTone.default)
    assertEquals("default 色调 = color.text.primary", LhTokens.Color.Text.primary, a.color)
  }

  // case: primary-tone-uses-primary-token
  @Test
  fun primary_tone_uses_primary_token() {
    val a = resolve(tone = LhIconTone.primary)
    assertEquals("primary 色调 = color.primary.default", LhTokens.Color.Primary.default, a.color)
  }

  /** 语义色调全量映射（spec behaviors 的 tone 映射逐条覆盖） */
  @Test
  fun all_tones_map_to_their_tokens() {
    assertEquals(LhTokens.Color.Success.default, resolve(tone = LhIconTone.success).color)
    assertEquals(LhTokens.Color.Warning.default, resolve(tone = LhIconTone.warning).color)
    assertEquals(LhTokens.Color.Danger.default, resolve(tone = LhIconTone.danger).color)
    assertEquals(LhTokens.Color.Info.default, resolve(tone = LhIconTone.info).color)
    assertEquals(LhTokens.Color.Text.regular, resolve(tone = LhIconTone.regular).color)
    assertEquals(LhTokens.Color.Text.secondary, resolve(tone = LhIconTone.secondary).color)
  }

  // case: small-size-uses-small-token（并反向断言 large 未被应用）
  @Test
  fun small_size_uses_small_token() {
    val a = resolve(size = LhIconSize.small)
    assertEquals(LhTokens.Icon.Size.small, a.size)
    assertTrue(
      "small 与 large 尺寸令牌必须不同，否则用例无意义",
      LhTokens.Icon.Size.small != LhTokens.Icon.Size.large,
    )
  }

  /** 尺寸映射全量（spec behaviors 的 size 映射逐条覆盖） */
  @Test
  fun all_sizes_map_to_their_tokens() {
    assertEquals(LhTokens.Icon.Size.small, resolve(size = LhIconSize.small).size)
    assertEquals(LhTokens.Icon.Size.medium, resolve(size = LhIconSize.medium).size)
    assertEquals(LhTokens.Icon.Size.large, resolve(size = LhIconSize.large).size)
  }

  // case: render-emits-icon-command —— 图标名能解析到本地图标矢量
  @Test
  fun registered_name_resolves_to_vector() {
    assertTrue("star 应已登记", LhIcons.contains("star"))
    assertFalse("未登记名不应解析成功", LhIcons.contains("not-registered-icon"))
    assertFalse("null 名不应解析成功", LhIcons.contains(null))
  }

  /** 暗色色板下色调取暗色令牌（并列对象模型） */
  @Test
  fun dark_tones_use_dark_tokens() {
    val a = resolve(tone = LhIconTone.primary, colors = dark)
    assertEquals(LhDarkTokens.Color.Primary.default, a.color)
    assertEquals("暗色 default 色调 = 暗色主要文字色", LhDarkTokens.Color.Text.primary, resolve(tone = LhIconTone.default, colors = dark).color)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(size = LhIconSize.large, colors = light)
    val darkAppearance = resolve(size = LhIconSize.large, colors = dark)
    assertEquals(lightAppearance.size, darkAppearance.size)
  }

  private fun resolve(
    size: LhIconSize = LhIconSize.medium,
    tone: LhIconTone = LhIconTone.default,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhIconDefaults.resolve(size, tone, colors)
}
