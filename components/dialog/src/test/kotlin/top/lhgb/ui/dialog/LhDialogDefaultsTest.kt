package top.lhgb.ui.dialog

import top.lhgb.ui.button.LhButtonDefaults
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.button.LhButtonSize
import top.lhgb.ui.theme.DarkLhColors
import top.lhgb.ui.theme.LightLhColors
import top.lhgb.ui.tokens.LhDarkTokens
import top.lhgb.ui.tokens.LhTokens
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * 黄金用例翻译：`lh-ui-core/cases/dialog.json` 的 8 条断言。
 *
 * 令牌/渲染类用例在此断言为「解析后的外观值 == 生成令牌的值」；交互类用例
 * （confirm-emits-onconfirm / cancel-emits-oncancel / confirm-also-emits-onclose /
 * closable-overlay-emits-onclose / not-closable-blocks-overlay-close）在 androidTest
 * 里断言。
 */
class LhDialogDefaultsTest {

  private val light = LightLhColors
  private val dark = DarkLhColors

  @Test
  fun layout_uses_dialog_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Dialog.width, a.width)
    assertEquals(LhTokens.Dialog.borderRadius, a.borderRadius)
    assertEquals(LhTokens.Dialog.paddingHorizontal, a.paddingHorizontal)
    assertEquals(LhTokens.Dialog.paddingVertical, a.paddingVertical)
    assertEquals(LhTokens.Dialog.titleFontSize, a.titleFontSize)
    assertEquals(LhTokens.Dialog.contentFontSize, a.contentFontSize)
    assertEquals(LhTokens.Dialog.gap, a.gap)
  }

  @Test
  fun colors_use_global_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Color.Background.default, a.surfaceColor)
    assertEquals(LhTokens.Color.Background.overlay, a.overlayColor)
    assertEquals(LhTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhTokens.Color.Text.regular, a.contentColor)
  }

  @Test
  fun zindex_uses_modal_tokens() {
    val a = resolve()
    assertEquals(LhTokens.ZIndex.modal, a.zIndex)
    assertEquals(LhTokens.ZIndex.modalBackdrop, a.backdropZIndex)
    // 层级语义：遮罩必须低于内容层
    assertNotEquals("遮罩与内容层不应同级", a.zIndex, a.backdropZIndex)
  }

  // case: title-renders-text —— 标题样式取标题令牌（文字渲染见 androidTest）
  @Test
  fun title_appearance_uses_title_tokens() {
    val a = resolve()
    assertEquals(LhTokens.Dialog.titleFontSize, a.titleFontSize)
    assertEquals(LhTokens.Color.Text.primary, a.titleColor)
  }

  // case: danger-type-uses-danger-token —— 确认按钮按 type 映射语义色
  @Test
  fun danger_type_maps_to_danger_button() {
    val a = resolve(type = LhDialogType.danger)
    assertEquals(LhButtonType.danger, a.confirmButtonType)
    // 交叉断言：映射后的按钮外观确实消费 danger 令牌
    val buttonAppearance = LhButtonDefaults.resolve(
      LhButtonType.danger,
      LhButtonSize.medium,
      disabled = false,
      loading = false,
      bgPattern = false,
      light,
    )
    assertEquals(LhTokens.Color.Danger.default, buttonAppearance.containerColor)
  }

  @Test
  fun default_type_maps_to_default_button() {
    assertEquals(LhButtonType.default, resolve().confirmButtonType)
  }

  @Test
  fun all_types_map_to_their_button_types() {
    LhDialogType.entries.associateWith { resolve(type = it).confirmButtonType }.forEach { (dialogType, buttonType) ->
      assertEquals("对话框类型 ${dialogType.name} 应映射到同名按钮类型", dialogType.name, buttonType.name)
    }
  }

  /** 暗色色板下颜色取暗色令牌（并列对象模型） */
  @Test
  fun dark_colors_use_dark_tokens() {
    val a = resolve(colors = dark)
    assertEquals(LhDarkTokens.Color.Background.default, a.surfaceColor)
    assertEquals(LhDarkTokens.Color.Background.overlay, a.overlayColor)
    assertEquals(LhDarkTokens.Color.Text.primary, a.titleColor)
    assertEquals(LhDarkTokens.Color.Text.regular, a.contentColor)
  }

  /** 尺寸令牌暗色不覆盖：明暗色板下对话框尺寸应一致 */
  @Test
  fun dimensions_are_identical_in_dark_mode() {
    val lightAppearance = resolve(colors = light)
    val darkAppearance = resolve(colors = dark)
    assertEquals(lightAppearance.width, darkAppearance.width)
    assertEquals(lightAppearance.borderRadius, darkAppearance.borderRadius)
    assertEquals(lightAppearance.paddingHorizontal, darkAppearance.paddingHorizontal)
    assertEquals(lightAppearance.titleFontSize, darkAppearance.titleFontSize)
  }

  private fun resolve(
    type: LhDialogType = LhDialogType.default,
    colors: top.lhgb.ui.theme.LhColors = light,
  ) = LhDialogDefaults.resolve(type, colors)
}
