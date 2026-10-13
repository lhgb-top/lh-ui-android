package top.lhgb.ui.cell

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 单元格外观解析结果。由 [LhCellDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/cell.json）能在无设备的 JVM 单测里断言
 * 「required/bordered → 令牌」的映射。
 */
data class LhCellAppearance(
  val paddingHorizontal: Dp,
  val paddingVertical: Dp,
  val gap: Dp,
  val titleFontSize: TextUnit,
  val labelFontSize: TextUnit,
  val valueFontSize: TextUnit,
  val titleColor: Color,
  val labelColor: Color,
  val valueColor: Color,
  /** 必填红星色（color.danger.default） */
  val requiredColor: Color,
  /** 分隔线色（color.border.lighter） */
  val dividerColor: Color,
  val dividerWidth: Dp,
  val iconSize: Dp,
  val lineHeight: Float,
)

/**
 * 单元格默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/cell.json` 的 behaviors：
 * - 标题取 cell.fontSizeTitle 与 color.text.primary
 * - 说明取 cell.fontSizeLabel 与 color.text.secondary
 * - 值取 cell.fontSizeValue 与 color.text.secondary 并右对齐
 * - required 为 true 时红星取 color.danger.default
 * - 左侧图标尺寸取 icon.size.medium
 * - 分隔线取 color.border.lighter
 * - 行高取 typography.lineHeight.primary
 */
object LhCellDefaults {

  /**
   * 解析单元格外观。
   *
   * @param required 是否必填（红星取 danger 色）
   * @param bordered 是否绘制底部分隔线
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    required: Boolean,
    bordered: Boolean,
    colors: LhColors,
  ): LhCellAppearance {
    return LhCellAppearance(
      paddingHorizontal = LhTokens.Cell.paddingHorizontal,
      paddingVertical = LhTokens.Cell.paddingVertical,
      gap = LhTokens.Cell.gap,
      titleFontSize = LhTokens.Cell.fontSizeTitle,
      labelFontSize = LhTokens.Cell.fontSizeLabel,
      valueFontSize = LhTokens.Cell.fontSizeValue,
      titleColor = colors.textPrimary,
      labelColor = colors.textSecondary,
      valueColor = colors.textSecondary,
      requiredColor = colors.danger,
      dividerColor = colors.borderLighter,
      dividerWidth = LhTokens.Cell.borderWidth,
      iconSize = LhTokens.Icon.Size.medium,
      lineHeight = LhTokens.Typography.LineHeight.primary.toFloat(),
    )
  }
}
