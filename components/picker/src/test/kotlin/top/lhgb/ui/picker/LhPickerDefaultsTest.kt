package top.lhgb.ui.picker

import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/picker.json` 的 8 条断言。
 *
 * 钳制（2 条）与令牌映射（title-renders-text / hidden-not-rendered 的外观前提）
 * 在此断言；按钮与滚动的交互（confirm-emits-onconfirm-with-value /
 * confirm-also-emits-onclose / cancel-emits-oncancel / scroll-emits-onvaluechange）
 * 在 androidTest 里断言。
 */
class LhPickerDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  // case: clamp-negative-value-to-zero
  @Test
  fun clamp_negative_value_to_zero() {
    assertEquals("负索引应钳制为 0", 0, LhPickerDefaults.clamp(-1, 2))
    assertEquals(0, LhPickerDefaults.clamp(-100, 3))
  }

  // case: clamp-overflow-value-to-last
  @Test
  fun clamp_overflow_value_to_last() {
    assertEquals("越界索引应钳制为最后一项", 2, LhPickerDefaults.clamp(99, 3))
    assertEquals(9, LhPickerDefaults.clamp(10_000, 10))
  }

  @Test
  fun clamp_in_range_value_unchanged() {
    assertEquals(1, LhPickerDefaults.clamp(1, 3))
    assertEquals(0, LhPickerDefaults.clamp(0, 3))
    assertEquals(2, LhPickerDefaults.clamp(2, 3))
  }

  @Test
  fun clamp_empty_options_is_zero() {
    assertEquals("无候选项时钳制为 0", 0, LhPickerDefaults.clamp(5, 0))
  }

  @Test
  fun layout_uses_picker_tokens() {
    val a = LhPickerDefaults.resolve(light)
    assertEquals(LhTokens.Picker.height, a.height)
    assertEquals(LhTokens.Picker.itemHeight, a.itemHeight)
    assertEquals(LhTokens.Picker.toolbarHeight, a.toolbarHeight)
    assertEquals(LhTokens.Picker.itemFontSize, a.itemFontSize)
    assertEquals(LhTokens.Picker.borderRadius, a.borderRadius)
  }

  @Test
  fun colors_use_global_tokens() {
    val a = LhPickerDefaults.resolve(light)
    assertEquals(LhTokens.Color.Background.default, a.surfaceColor)
    assertEquals(LhTokens.Color.Background.overlay, a.overlayColor)
    assertEquals(LhTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhTokens.Color.Text.secondary, a.itemColor)
    assertEquals(LhTokens.Color.Primary.default, a.itemColorSelected)
    assertEquals(LhTokens.Color.Border.lighter, a.indicatorColor)
  }

  @Test
  fun zindex_uses_modal_tokens() {
    val a = LhPickerDefaults.resolve(light)
    assertEquals(LhTokens.ZIndex.modal, a.zIndex)
    assertEquals(LhTokens.ZIndex.modalBackdrop, a.backdropZIndex)
    assertNotEquals("遮罩与内容层不应同级", a.zIndex, a.backdropZIndex)
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = LhPickerDefaults.resolve(dark)
    assertEquals(LhDarkTokens.Color.Background.default, a.surfaceColor)
    assertEquals(LhDarkTokens.Color.Background.overlay, a.overlayColor)
    assertEquals(LhDarkTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhDarkTokens.Color.Text.secondary, a.itemColor)
    assertEquals(LhDarkTokens.Color.Primary.default, a.itemColorSelected)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下面板尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = LhPickerDefaults.resolve(light)
    val darkAppearance = LhPickerDefaults.resolve(dark)
    assertEquals(lightAppearance.height, darkAppearance.height)
    assertEquals(lightAppearance.itemHeight, darkAppearance.itemHeight)
    assertEquals(lightAppearance.toolbarHeight, darkAppearance.toolbarHeight)
    assertEquals(lightAppearance.itemFontSize, darkAppearance.itemFontSize)
  }
}
