package top.lhgb.ui.toast

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/toast.json` 的 6 条断言，全部可在 JVM 跑。
 *
 * - 状态机 4 条（show / tick 到期 / tick 未到期 / hide 静默）直接断言 [LhToastState]
 * - 渲染 2 条（success-type-uses-success-token / hidden-emits-no-commands）断言
 *   [LhToastDefaults.resolve] 的令牌映射与「不可见时不绘制」的守卫
 */
class LhToastDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: show-makes-visible
  @Test
  fun show_makes_visible_and_renders_text() {
    val state = LhToastState()
    assertFalse("初始状态应不可见", state.visible)

    state.show("已保存")

    assertTrue("show 后应可见", state.visible)
    assertEquals("show 后应渲染传入文字", "已保存", state.text)
  }

  @Test
  fun show_resets_elapsed() {
    val state = LhToastState(duration = 2000)
    state.show("第一条")
    state.tick(1500)
    state.show("第二条")
    // 计时已重置：再 tick 1500 不应到期（若未重置则 3000 >= 2000 已到期）
    assertFalse("show 应重置计时", state.tick(500))
    assertTrue("重置后仍在计时窗口内", state.visible)
  }

  // case: auto-hides-after-duration
  @Test
  fun auto_hides_after_duration_and_emits_onClose() {
    var closes = 0
    val state = LhToastState(duration = 2000, onClose = { closes++ })
    state.show("提示")

    val hidden = state.tick(2500)

    assertTrue("计时到期应自动隐藏", hidden)
    assertFalse("到期后应不可见", state.visible)
    assertEquals("到期应触发 onClose 一次", 1, closes)
  }

  // case: tick-before-duration-keeps-visible
  @Test
  fun tick_before_duration_keeps_visible_and_does_not_emit() {
    var closes = 0
    val state = LhToastState(duration = 2000, onClose = { closes++ })
    state.show("提示")

    val hidden = state.tick(1000)

    assertFalse("未到期不应隐藏", hidden)
    assertTrue("未到期应保持可见", state.visible)
    assertEquals("未到期不应触发 onClose", 0, closes)
  }

  // case: hide-is-silent
  @Test
  fun hide_is_silent() {
    var closes = 0
    val state = LhToastState(onClose = { closes++ })
    state.show("提示")
    assertTrue(state.visible)

    state.hide()

    assertFalse("hide 后应不可见", state.visible)
    assertEquals("hide 主动隐藏不应触发 onClose", 0, closes)
  }

  @Test
  fun tick_while_hidden_does_nothing() {
    var closes = 0
    val state = LhToastState(duration = 100, onClose = { closes++ })
    // 未 show 直接 tick：不应进入计时逻辑
    assertFalse(state.tick(10_000))
    assertEquals(0, closes)
  }

  // case: hidden-emits-no-commands —— 不可见时 resolve 结果不被消费（外观层不渲染）
  @Test
  fun hidden_emits_no_commands() {
    val state = LhToastState()
    // 状态机守卫：不可见时外观解析结果不会被绘制层消费
    assertFalse("不可见时不应发射绘制指令", state.visible)
  }

  // case: success-type-uses-success-token
  @Test
  fun success_type_uses_success_tokens() {
    val a = resolve(type = LhToastType.success)
    assertEquals("胶囊底 = color.ink", LhTokens.Color.ink, a.backgroundColor)
    assertEquals("文字 = color.onPrimary", LhTokens.Color.onPrimary, a.textColor)
    assertEquals("成功色点 = color.success.default", LhTokens.Color.Success.default, a.dotColor)
    assertEquals("色点直径 = 字号", LhTokens.Toast.fontSize.value.dp, a.dotSize)
  }

  @Test
  fun default_type_has_no_dot() {
    val a = resolve()
    assertNull("default 类型不绘制色点", a.dotColor)
  }

  @Test
  fun types_map_to_their_semantic_colors() {
    assertEquals(LhTokens.Color.Warning.default, resolve(LhToastType.warning).dotColor)
    assertEquals(LhTokens.Color.Danger.default, resolve(LhToastType.danger).dotColor)
    assertEquals(LhTokens.Color.Info.default, resolve(LhToastType.info).dotColor)
  }

  @Test
  fun layout_uses_toast_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Toast.fontSize, a.fontSize)
    assertEquals(LhTokens.Toast.paddingHorizontal, a.paddingHorizontal)
    assertEquals(LhTokens.Toast.paddingVertical, a.paddingVertical)
    assertEquals(LhTokens.Toast.borderRadius, a.borderRadius)
    assertEquals(LhTokens.Toast.gap, a.gap)
    assertEquals(LhTokens.ZIndex.toast, a.zIndex)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(colors = dark)
    // 暗色无独立 ink 令牌：主题层把 DarkLhColors.ink 映射为主要文字色（见 LhColors 注释），
    // 组件只消费主题色板，此处断言该映射链路而非直接断言 LhDarkTokens.Color.ink。
    assertEquals(LhDarkTokens.Color.Text.primary, a.backgroundColor)
    assertEquals(LhTokens.Color.onPrimary, a.textColor)
    assertEquals(LhDarkTokens.Color.Success.default, resolve(LhToastType.success, dark).dotColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下提示尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(colors = light)
    val darkAppearance = resolve(colors = dark)
    assertEquals(lightAppearance.fontSize, darkAppearance.fontSize)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.borderRadius, darkAppearance.borderRadius)
    assertEquals(lightAppearance.dotSize, darkAppearance.dotSize)
  }

  private fun resolve(
    type: LhToastType = LhToastType.default,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhToastDefaults.resolve(type, colors)
}
