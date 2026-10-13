package top.lhgb.ui.input

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 输入框外观解析结果。由 [LhInputDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/input.json）能在无设备的 JVM 单测里断言
 * 「size/disabled/focused → 令牌」的映射。
 */
data class LhInputAppearance(
  val height: Dp,
  val paddingHorizontal: Dp,
  val fontSize: TextUnit,
  val containerColor: Color,
  val textColor: Color,
  val placeholderColor: Color,
  val borderColor: Color,
  val borderWidth: Dp,
  val borderRadius: Dp,
  val clearIconSize: Dp,
  /** 是否可编辑（disabled 不可编辑；readonly 可聚焦但不接受输入，由调用方在回调里拦截） */
  val editable: Boolean,
)

/**
 * 输入框默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/input.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 input.height.<size> 等尺寸令牌
 * - disabled 为 true 时取 disabled.* 令牌，不响应输入与清除
 * - 聚焦时描边色取 color.focus、宽度 input.focusRingWidth；失焦回 color.border.base + input.borderWidth
 */
object LhInputDefaults {

  /**
   * 解析输入框外观。
   *
   * @param size 输入框尺寸
   * @param disabled 是否禁用
   * @param focused 是否聚焦（决定描边色与宽度）
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    size: LhInputSize,
    disabled: Boolean,
    focused: Boolean,
    colors: LhColors,
  ): LhInputAppearance {
    // size → 尺寸令牌
    val height: Dp
    val paddingHorizontal: Dp
    val fontSize: TextUnit
    when (size) {
      LhInputSize.small -> {
        height = LhTokens.Input.Height.small
        paddingHorizontal = LhTokens.Input.PaddingHorizontal.small
        fontSize = LhTokens.Input.FontSize.small
      }
      LhInputSize.medium -> {
        height = LhTokens.Input.Height.medium
        paddingHorizontal = LhTokens.Input.PaddingHorizontal.medium
        fontSize = LhTokens.Input.FontSize.medium
      }
      LhInputSize.large -> {
        height = LhTokens.Input.Height.large
        paddingHorizontal = LhTokens.Input.PaddingHorizontal.large
        fontSize = LhTokens.Input.FontSize.large
      }
    }

    // 状态 gate：禁用取 disabled.* 令牌；聚焦描边取 focus 色 + focusRingWidth；否则中性描边
    val containerColor: Color
    val textColor: Color
    val placeholderColor: Color
    val borderColor: Color
    val borderWidth: Dp
    when {
      disabled -> {
        containerColor = colors.disabledBg
        textColor = colors.disabledText
        placeholderColor = colors.disabledText
        borderColor = colors.disabledBorder
        borderWidth = LhTokens.Input.borderWidth
      }
      focused -> {
        containerColor = colors.backgroundDefault
        textColor = colors.textPrimary
        placeholderColor = colors.textPlaceholder
        borderColor = colors.focus
        borderWidth = LhTokens.Input.focusRingWidth
      }
      else -> {
        containerColor = colors.backgroundDefault
        textColor = colors.textPrimary
        placeholderColor = colors.textPlaceholder
        borderColor = colors.borderBase
        borderWidth = LhTokens.Input.borderWidth
      }
    }

    return LhInputAppearance(
      height = height,
      paddingHorizontal = paddingHorizontal,
      fontSize = fontSize,
      containerColor = containerColor,
      textColor = textColor,
      placeholderColor = placeholderColor,
      borderColor = borderColor,
      borderWidth = borderWidth,
      borderRadius = LhTokens.Input.borderRadius,
      clearIconSize = LhTokens.Input.clearIconSize,
      editable = !disabled,
    )
  }
}
