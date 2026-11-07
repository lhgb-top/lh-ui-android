package top.lhgb.ui.text

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 文本外观解析结果。由 [LhTextDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/text.json）能在无设备的 JVM 单测里断言
 * 「level / weight / tone → 令牌」的映射。
 *
 * lineHeight 为字号令牌与 typography.lineHeight.primary 令牌相乘后的行高值。
 */
data class LhTextAppearance(
  /** 字号（typography.fontSize.<level> 令牌） */
  val fontSize: TextUnit,
  /** 行高 = 字号 × typography.lineHeight.primary */
  val lineHeight: TextUnit,
  /** 字重（typography.fontWeight.<weight> 令牌的 Int 值） */
  val fontWeight: Int,
  /** 文字色（color.text.<tone> 或 color.selected 令牌） */
  val textColor: Color,
)

/**
 * 文本默认值与令牌绑定（纯函数，无 Compose 渲染依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/text.json` 的 behaviors：
 * - level 映射：extraLarge/large/medium/base/small/extraSmall 分别取 typography.fontSize.<level>
 * - weight 映射：primary/bold 分别取 typography.fontWeight.<weight>
 * - tone 映射：取 color.text.<tone>；selected 取 color.selected
 * - 行高取 typography.lineHeight.primary（lineHeight = fontSize × lineHeight.primary）
 */
object LhTextDefaults {

  /**
   * 解析文本外观。
   *
   * @param level 字号级别
   * @param weight 字重
   * @param tone 文字色调
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    level: LhTextLevel,
    weight: LhTextWeight,
    tone: LhTextTone,
    colors: LhColors,
  ): LhTextAppearance {
    val fontSize = when (level) {
      LhTextLevel.extraLarge -> LhTokens.Typography.FontSize.extraLarge
      LhTextLevel.large -> LhTokens.Typography.FontSize.large
      LhTextLevel.medium -> LhTokens.Typography.FontSize.medium
      LhTextLevel.base -> LhTokens.Typography.FontSize.base
      LhTextLevel.small -> LhTokens.Typography.FontSize.small
      LhTextLevel.extraSmall -> LhTokens.Typography.FontSize.extraSmall
    }

    val fontWeight = when (weight) {
      LhTextWeight.primary -> LhTokens.Typography.FontWeight.primary
      LhTextWeight.bold -> LhTokens.Typography.FontWeight.bold
    }

    val textColor = when (tone) {
      LhTextTone.primary -> colors.textPrimary
      LhTextTone.regular -> colors.textRegular
      LhTextTone.secondary -> colors.textSecondary
      LhTextTone.placeholder -> colors.textPlaceholder
      LhTextTone.disabled -> colors.textDisabled
      LhTextTone.selected -> colors.selected
    }

    // 行高 = 字号 × typography.lineHeight.primary（sp × 无量纲倍数）
    val lineHeight = fontSize * LhTokens.Typography.LineHeight.primary.toFloat()

    return LhTextAppearance(
      fontSize = fontSize,
      lineHeight = lineHeight,
      fontWeight = fontWeight,
      textColor = textColor,
    )
  }
}
