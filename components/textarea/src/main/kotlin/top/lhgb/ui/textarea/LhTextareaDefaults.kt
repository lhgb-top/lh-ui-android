package top.lhgb.ui.textarea

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 多行输入框外观解析结果。由 [LhTextareaDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/textarea.json）能在无设备的 JVM 单测里断言
 * 「disabled/focused → 令牌」的映射。
 */
data class LhTextareaAppearance(
  val paddingHorizontal: Dp,
  val paddingVertical: Dp,
  val fontSize: TextUnit,
  val countFontSize: TextUnit,
  val containerColor: Color,
  val textColor: Color,
  val placeholderColor: Color,
  val countColor: Color,
  val borderColor: Color,
  val borderWidth: Dp,
  val borderRadius: Dp,
  val minHeight: Dp,
  /** 行高倍数（typography.lineHeight.primary），用于由 rows 推导可见高度 */
  val lineHeight: Float,
  /** 是否可编辑 */
  val editable: Boolean,
)

/**
 * 多行输入框默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/textarea.json` 的 behaviors：
 * - 高度由 rows 决定初始可见行数，最小高度 textarea.minHeight
 * - 聚焦时描边色取 color.focus、宽度 textarea.focusRingWidth；失焦回 color.border.base + textarea.borderWidth
 * - showCount 字数统计取 textarea.countFontSize 与 color.text.secondary
 * - disabled 为 true 时取 disabled.* 令牌
 */
object LhTextareaDefaults {

  /**
   * 解析多行输入框外观。
   *
   * @param disabled 是否禁用
   * @param focused 是否聚焦（决定描边色与宽度）
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    disabled: Boolean,
    focused: Boolean,
    colors: LhColors,
  ): LhTextareaAppearance {
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
        borderWidth = LhTokens.Textarea.borderWidth
      }
      focused -> {
        containerColor = colors.backgroundDefault
        textColor = colors.textPrimary
        placeholderColor = colors.textPlaceholder
        borderColor = colors.focus
        borderWidth = LhTokens.Textarea.focusRingWidth
      }
      else -> {
        containerColor = colors.backgroundDefault
        textColor = colors.textPrimary
        placeholderColor = colors.textPlaceholder
        borderColor = colors.borderBase
        borderWidth = LhTokens.Textarea.borderWidth
      }
    }

    return LhTextareaAppearance(
      paddingHorizontal = LhTokens.Textarea.paddingHorizontal,
      paddingVertical = LhTokens.Textarea.paddingVertical,
      fontSize = LhTokens.Textarea.fontSize,
      countFontSize = LhTokens.Textarea.countFontSize,
      containerColor = containerColor,
      textColor = textColor,
      placeholderColor = placeholderColor,
      countColor = colors.textSecondary,
      borderColor = borderColor,
      borderWidth = borderWidth,
      borderRadius = LhTokens.Textarea.borderRadius,
      minHeight = LhTokens.Textarea.minHeight,
      lineHeight = LhTokens.Typography.LineHeight.primary.toFloat(),
      editable = !disabled,
    )
  }

  /**
   * 计算字数统计文案。
   *
   * - 声明 maxlength 时格式「当前/最大」
   * - 未声明 maxlength 时只显示当前长度（spec behaviors）
   */
  fun countText(value: String, maxlength: Int?): String =
    if (maxlength != null) "${value.length}/$maxlength" else "${value.length}"
}
