package top.lhgb.ui.loading

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 加载指示器外观解析结果。由 [LhLoadingDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/loading.json）能在无设备的 JVM 单测里断言
 * 「size/tone → 令牌」的映射。
 */
data class LhLoadingAppearance(
  /** 直径 */
  val size: Dp,
  /** 描边宽度 */
  val strokeWidth: Dp,
  val color: Color,
)

/**
 * 加载指示器默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/loading.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 loading.size.<size> 直径令牌
 * - 描边宽取 loading.strokeWidth
 * - tone 映射：取对应语义色令牌
 */
object LhLoadingDefaults {

  /**
   * 解析加载指示器外观。
   *
   * @param size 指示器直径档位
   * @param tone 环色
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    size: LhLoadingSize,
    tone: LhLoadingTone,
    colors: LhColors,
  ): LhLoadingAppearance {
    val resolvedSize: Dp = when (size) {
      LhLoadingSize.small -> LhTokens.Loading.Size.small
      LhLoadingSize.medium -> LhTokens.Loading.Size.medium
      LhLoadingSize.large -> LhTokens.Loading.Size.large
    }

    val resolvedColor: Color = when (tone) {
      LhLoadingTone.primary -> colors.primary
      LhLoadingTone.success -> colors.success
      LhLoadingTone.warning -> colors.warning
      LhLoadingTone.danger -> colors.danger
      LhLoadingTone.info -> colors.info
    }

    return LhLoadingAppearance(
      size = resolvedSize,
      strokeWidth = LhTokens.Loading.strokeWidth,
      color = resolvedColor,
    )
  }
}
