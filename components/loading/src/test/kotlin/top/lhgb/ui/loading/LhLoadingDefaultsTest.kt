package top.lhgb.ui.loading

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/loading.json` 的 5 条断言。
 *
 * loading 是纯展示组件（spec 无 events），全部用例都是令牌映射类，
 * 在无设备的 JVM 环境断言「解析后的外观值 == 生成令牌的值」。
 */
class LhLoadingDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: medium-size-uses-medium-token（默认即 medium，反向断言 small 未应用）
  @Test
  fun medium_size_uses_medium_token() {
    val a = resolve()
    assertEquals(LhTokens.Loading.Size.medium, a.size)
    assertTrue(
      "medium 与 small 尺寸令牌必须不同，否则用例无意义",
      LhTokens.Loading.Size.medium != LhTokens.Loading.Size.small,
    )
  }

  // case: large-size-uses-large-token（反向断言 medium 未应用）
  @Test
  fun large_size_uses_large_token() {
    val a = resolve(size = LhLoadingSize.large)
    assertEquals(LhTokens.Loading.Size.large, a.size)
    assertTrue(
      "large 与 medium 尺寸令牌必须不同，否则用例无意义",
      LhTokens.Loading.Size.large != LhTokens.Loading.Size.medium,
    )
  }

  // case: primary-tone-uses-primary-token
  @Test
  fun primary_tone_uses_primary_token() {
    val a = resolve()
    assertEquals(LhTokens.Color.Primary.default, a.color)
  }

  // case: success-tone-uses-success-token
  @Test
  fun success_tone_uses_success_token() {
    val a = resolve(tone = LhLoadingTone.success)
    assertEquals(LhTokens.Color.Success.default, a.color)
  }

  /** 语义色调全量映射（spec tokenRefs 逐条覆盖） */
  @Test
  fun all_tones_map_to_their_tokens() {
    assertEquals(LhTokens.Color.Warning.default, resolve(tone = LhLoadingTone.warning).color)
    assertEquals(LhTokens.Color.Danger.default, resolve(tone = LhLoadingTone.danger).color)
    assertEquals(LhTokens.Color.Info.default, resolve(tone = LhLoadingTone.info).color)
  }

  // case: render-emits-ring-command —— 描边宽令牌被消费
  @Test
  fun stroke_width_uses_token() {
    val a = resolve()
    assertEquals(LhTokens.Loading.strokeWidth, a.strokeWidth)
    assertTrue("描边宽必须是正数", a.strokeWidth.value > 0f)
  }

  /** 暗色色板下色调取暗色令牌（并列对象模型） */
  @Test
  fun dark_tones_use_dark_tokens() {
    val a = resolve(tone = LhLoadingTone.primary, colors = dark)
    assertEquals(LhDarkTokens.Color.Primary.default, a.color)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(size = LhLoadingSize.large, colors = light)
    val darkAppearance = resolve(size = LhLoadingSize.large, colors = dark)
    assertEquals(lightAppearance.size, darkAppearance.size)
    assertEquals(lightAppearance.strokeWidth, darkAppearance.strokeWidth)
  }

  private fun resolve(
    size: LhLoadingSize = LhLoadingSize.medium,
    tone: LhLoadingTone = LhLoadingTone.primary,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhLoadingDefaults.resolve(size, tone, colors)
}
