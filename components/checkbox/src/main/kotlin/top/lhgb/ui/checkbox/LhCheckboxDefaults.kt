package top.lhgb.ui.checkbox

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 复选框外观解析结果。由 [LhCheckboxDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/checkbox.json）能在无设备的 JVM 单测里断言
 * 「checked/indeterminate/disabled/size → 令牌」的映射。
 */
data class LhCheckboxAppearance(
  /** 方框边长 */
  val boxSize: Dp,
  val borderRadius: Dp,
  val borderWidth: Dp,
  /** 勾形 / 横杠描边宽度 */
  val checkWidth: Dp,
  val gap: Dp,
  val fontSize: TextUnit,
  val boxColor: Color,
  val borderColor: Color,
  /** 指示符（勾 / 横杠）颜色 */
  val indicatorColor: Color,
  val labelColor: Color,
  /** 是否可交互 */
  val enabled: Boolean,
)

/**
 * 复选框默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/checkbox.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 checkbox.size.<size> 方框尺寸令牌
 * - 未选中：只描边（color.border.base），无填充
 * - 选中/不确定：实心 primary 底 + onPrimary 指示符；选中画勾形，不确定画横杠
 * - disabled：底/边/指示符/文字全部取 disabled.* 令牌
 * - 方框与文字间距取 checkbox.gap
 */
object LhCheckboxDefaults {

  /**
   * 解析复选框外观。
   *
   * @param size 方框尺寸档位
   * @param checked 是否选中
   * @param indeterminate 是否半选（不确定态）
   * @param disabled 是否禁用
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    size: LhCheckboxSize,
    checked: Boolean,
    indeterminate: Boolean,
    disabled: Boolean,
    colors: LhColors,
  ): LhCheckboxAppearance {
    val boxSize: Dp = when (size) {
      LhCheckboxSize.small -> LhTokens.Checkbox.Size.small
      LhCheckboxSize.medium -> LhTokens.Checkbox.Size.medium
      LhCheckboxSize.large -> LhTokens.Checkbox.Size.large
    }

    // 选中或不确定 = 实心 primary 底 + onPrimary 指示符；未选中 = 只描边
    val active = checked || indeterminate

    val boxColor: Color
    val borderColor: Color
    val indicatorColor: Color
    val labelColor: Color
    when {
      disabled -> {
        boxColor = colors.disabledBg
        borderColor = colors.disabledBorder
        indicatorColor = colors.disabledText
        labelColor = colors.disabledText
      }
      active -> {
        boxColor = colors.primary
        borderColor = colors.primary
        indicatorColor = colors.onPrimary
        labelColor = colors.textPrimary
      }
      else -> {
        // 未选中：只描边，无填充（底色透明）
        boxColor = androidx.compose.ui.graphics.Color.Transparent
        borderColor = colors.borderBase
        indicatorColor = colors.onPrimary
        labelColor = colors.textPrimary
      }
    }

    return LhCheckboxAppearance(
      boxSize = boxSize,
      borderRadius = LhTokens.Checkbox.borderRadius,
      borderWidth = LhTokens.Checkbox.borderWidth,
      checkWidth = LhTokens.Checkbox.checkWidth,
      gap = LhTokens.Checkbox.gap,
      fontSize = LhTokens.Typography.FontSize.base,
      boxColor = boxColor,
      borderColor = borderColor,
      indicatorColor = indicatorColor,
      labelColor = labelColor,
      enabled = !disabled,
    )
  }
}
