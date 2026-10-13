package top.lhgb.ui.radio

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 单选框外观解析结果。由 [LhRadioDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/radio.json）能在无设备的 JVM 单测里断言
 * 「checked/disabled/size → 令牌」的映射。
 */
data class LhRadioAppearance(
  /** 外环直径 */
  val size: Dp,
  /** 外环描边宽度 */
  val ringWidth: Dp,
  /** 内圆点直径（已由 dotScale 换算好的绝对尺寸） */
  val dotSize: Dp,
  /** 圆环与文字间距 */
  val gap: Dp,
  val fontSize: TextUnit,
  /** 外环描边色 */
  val ringColor: Color,
  /** 内圆点色（未选中时不绘制） */
  val dotColor: Color,
  val labelColor: Color,
  /** 是否可交互 */
  val enabled: Boolean,
)

/**
 * 单选框默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/radio.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 radio.size.<size> 圆环尺寸令牌
 * - 外环描边：未选中取 color.border.base，选中取 color.primary.default，宽 radio.ringWidth
 * - 选中时内部画实心圆点，直径 = 尺寸 × radio.dotScale，取 color.primary.default
 * - disabled：外环 disabled.border、圆点 disabled.text、文字 text.disabled，且不响应点击
 * - 圆环与文字间距取 radio.gap
 */
object LhRadioDefaults {

  /**
   * 解析单选框外观。
   *
   * @param size 圆环尺寸档位
   * @param checked 是否选中
   * @param disabled 是否禁用
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    size: LhRadioSize,
    checked: Boolean,
    disabled: Boolean,
    colors: LhColors,
  ): LhRadioAppearance {
    val ringSize: Dp = when (size) {
      LhRadioSize.small -> LhTokens.Radio.Size.small
      LhRadioSize.medium -> LhTokens.Radio.Size.medium
      LhRadioSize.large -> LhTokens.Radio.Size.large
    }

    val ringColor: Color
    val dotColor: Color
    val labelColor: Color
    when {
      disabled -> {
        ringColor = colors.disabledBorder
        dotColor = colors.disabledText
        labelColor = colors.textDisabled
      }
      checked -> {
        ringColor = colors.primary
        dotColor = colors.primary
        labelColor = colors.textPrimary
      }
      else -> {
        ringColor = colors.borderBase
        // 未选中不绘制圆点，颜色保留主色以便单测断言「未选中不消费 primary」（不绘制即不消费）
        dotColor = colors.primary
        labelColor = colors.textPrimary
      }
    }

    return LhRadioAppearance(
      size = ringSize,
      ringWidth = LhTokens.Radio.ringWidth,
      // 内圆点直径 = 外环尺寸 × dotScale（分数令牌换算为绝对尺寸）
      dotSize = ringSize * LhTokens.Radio.dotScale,
      gap = LhTokens.Radio.gap,
      fontSize = LhTokens.Typography.FontSize.base,
      ringColor = ringColor,
      dotColor = dotColor,
      labelColor = labelColor,
      enabled = !disabled,
    )
  }
}
