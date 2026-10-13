package top.lhgb.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 图标外观解析结果。由 [LhIconDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/icon.json）能在无设备的 JVM 单测里断言
 * 「size/tone → 令牌」的映射。
 */
data class LhIconAppearance(
  val size: Dp,
  val color: Color,
)

/**
 * 图标默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/icon.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 icon.size.<size> 尺寸令牌
 * - tone 映射：default 取 color.text.primary；primary/success/warning/danger/info
 *   取对应语义色；regular/secondary 取对应文字色
 */
object LhIconDefaults {

  /**
   * 解析图标外观。
   *
   * @param size 图标尺寸
   * @param tone 图标语义色调
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    size: LhIconSize,
    tone: LhIconTone,
    colors: LhColors,
  ): LhIconAppearance {
    val resolvedSize: Dp = when (size) {
      LhIconSize.small -> LhTokens.Icon.Size.small
      LhIconSize.medium -> LhTokens.Icon.Size.medium
      LhIconSize.large -> LhTokens.Icon.Size.large
    }

    val resolvedColor: Color = when (tone) {
      LhIconTone.default -> colors.textPrimary
      LhIconTone.primary -> colors.primary
      LhIconTone.success -> colors.success
      LhIconTone.warning -> colors.warning
      LhIconTone.danger -> colors.danger
      LhIconTone.info -> colors.info
      LhIconTone.regular -> colors.textRegular
      LhIconTone.secondary -> colors.textSecondary
    }

    return LhIconAppearance(size = resolvedSize, color = resolvedColor)
  }
}
