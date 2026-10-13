package top.lhgb.ui.switch

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 开关外观解析结果。由 [LhSwitchDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/switch.json）能在无设备的 JVM 单测里断言
 * 「checked/disabled/size → 令牌」的映射。
 */
data class LhSwitchAppearance(
  /** 轨道宽度 */
  val width: Dp,
  /** 轨道高度 */
  val height: Dp,
  /** 滑块与轨道之间的留白 */
  val gap: Dp,
  /** 滑块直径（= 高度 - 2 × gap） */
  val thumbSize: Dp,
  /** 轨道色 */
  val trackColor: Color,
  /** 滑块色 */
  val thumbColor: Color,
  /** 加载环色（主色） */
  val loadingColor: Color,
  /** 是否可交互（disabled 或 loading 时为 false） */
  val enabled: Boolean,
)

/**
 * 开关默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/switch.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 switch.width.<size> 与 switch.height.<size>
 * - 轨道为胶囊（圆角取高度一半）：开启 primary，关闭 border.base，禁用 disabled.bg
 * - 滑块直径 = 高度 - 2 × switch.gap，取 onPrimary；禁用取 text.disabled
 * - loading 时滑块位置改画加载环（主色），且不响应点击
 */
object LhSwitchDefaults {

  /**
   * 解析开关外观。
   *
   * @param size 尺寸档位
   * @param checked 是否开启
   * @param disabled 是否禁用
   * @param loading 是否加载中
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    size: LhSwitchSize,
    checked: Boolean,
    disabled: Boolean,
    loading: Boolean,
    colors: LhColors,
  ): LhSwitchAppearance {
    val width: Dp = when (size) {
      LhSwitchSize.small -> LhTokens.Switch.Width.small
      LhSwitchSize.medium -> LhTokens.Switch.Width.medium
      LhSwitchSize.large -> LhTokens.Switch.Width.large
    }
    val height: Dp = when (size) {
      LhSwitchSize.small -> LhTokens.Switch.Height.small
      LhSwitchSize.medium -> LhTokens.Switch.Height.medium
      LhSwitchSize.large -> LhTokens.Switch.Height.large
    }

    val trackColor: Color
    val thumbColor: Color
    when {
      disabled -> {
        trackColor = colors.disabledBg
        thumbColor = colors.textDisabled
      }
      checked -> {
        trackColor = colors.primary
        thumbColor = colors.onPrimary
      }
      else -> {
        trackColor = colors.borderBase
        thumbColor = colors.onPrimary
      }
    }

    return LhSwitchAppearance(
      width = width,
      height = height,
      gap = LhTokens.Switch.gap,
      // 滑块直径 = 高度 - 2 × gap
      thumbSize = height - (LhTokens.Switch.gap * 2),
      trackColor = trackColor,
      thumbColor = thumbColor,
      loadingColor = colors.primary,
      // spec：disabled 或 loading 时都不响应点击
      enabled = !disabled && !loading,
    )
  }
}
