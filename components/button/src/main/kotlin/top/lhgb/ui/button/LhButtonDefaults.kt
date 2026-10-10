package top.lhgb.ui.button

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 按钮外观解析结果：尺寸 + 颜色 + 状态。由 [resolve] 纯函数产出，
 * Composable 只负责把它画出来。分离解析与渲染，是为了让黄金用例（cases/button.json）
 * 能在无设备的 JVM 单测里断言「type/size/disabled → 令牌」的映射。
 */
data class LhButtonAppearance(
  val height: Dp,
  val paddingHorizontal: Dp,
  val fontSize: TextUnit,
  val iconSize: Dp,
  val containerColor: Color,
  val contentColor: Color,
  val borderColor: Color,
  val borderWidth: Dp,
  val borderRadius: Dp,
  /** 花纹底纹色；bgPattern=false 时为 null（不绘制底纹） */
  val patternColor: Color?,
  /** 是否可交互（disabled 与 loading 任一为 true 即不可交互，disabled 优先） */
  val enabled: Boolean,
)

/**
 * 按钮默认值与令牌绑定（纯函数，无 Compose 依赖逻辑）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/button.json` 的 behaviors：
 * - disabled 为 true 时：呈现禁用样式（disabled.* 令牌），不触发 onClick
 * - loading 为 true 时：显示加载指示器，不触发 onClick
 * - disabled 与 loading 同时为 true 时按 disabled 优先处理
 * - size 映射：small/medium/large 分别取 button.height.<size> 等尺寸令牌
 * - type 映射：primary 取主色底 + onPrimary 文字；其余类型取对应语义色
 * - bgPattern 为 true 时以 color.decoration 绘制底纹，不影响文字对比度
 */
object LhButtonDefaults {

  /**
   * 解析按钮外观。
   *
   * @param type 按钮类型
   * @param size 按钮尺寸
   * @param disabled 是否禁用
   * @param loading 是否加载中
   * @param bgPattern 是否绘制国风花纹底纹
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    type: LhButtonType,
    size: LhButtonSize,
    disabled: Boolean,
    loading: Boolean,
    bgPattern: Boolean,
    colors: LhColors,
  ): LhButtonAppearance {
    // 状态 gate：disabled 优先于 loading（黄金用例 disabled-precedes-loading）
    val enabled = !disabled && !loading

    // type → 底色 / 文字色 / 描边色（禁用态覆盖）
    val (container, content, border) = if (!enabled) {
      Triple(colors.disabledBg, colors.disabledText, colors.disabledBorder)
    } else {
      when (type) {
        LhButtonType.default -> Triple(colors.backgroundDefault, colors.textPrimary, colors.borderBase)
        LhButtonType.primary -> Triple(colors.primary, colors.onPrimary, Color.Transparent)
        LhButtonType.success -> Triple(colors.success, colors.onPrimary, Color.Transparent)
        LhButtonType.warning -> Triple(colors.warning, colors.onPrimary, Color.Transparent)
        LhButtonType.danger -> Triple(colors.danger, colors.onPrimary, Color.Transparent)
        LhButtonType.info -> Triple(colors.info, colors.onPrimary, Color.Transparent)
      }
    }

    // size → 尺寸令牌
    val height: Dp
    val paddingHorizontal: Dp
    val fontSize: TextUnit
    val iconSize: Dp
    when (size) {
      LhButtonSize.small -> {
        height = LhTokens.Button.Height.small
        paddingHorizontal = LhTokens.Button.PaddingHorizontal.small
        fontSize = LhTokens.Button.FontSize.small
        iconSize = LhTokens.Button.IconSize.small
      }
      LhButtonSize.medium -> {
        height = LhTokens.Button.Height.medium
        paddingHorizontal = LhTokens.Button.PaddingHorizontal.medium
        fontSize = LhTokens.Button.FontSize.medium
        iconSize = LhTokens.Button.IconSize.medium
      }
      LhButtonSize.large -> {
        height = LhTokens.Button.Height.large
        paddingHorizontal = LhTokens.Button.PaddingHorizontal.large
        fontSize = LhTokens.Button.FontSize.large
        iconSize = LhTokens.Button.IconSize.large
      }
    }

    return LhButtonAppearance(
      height = height,
      paddingHorizontal = paddingHorizontal,
      containerColor = container,
      contentColor = content,
      borderColor = border,
      borderWidth = LhTokens.Button.borderWidth,
      borderRadius = LhTokens.Button.borderRadius,
      patternColor = if (bgPattern) colors.decoration else null,
      enabled = enabled,
    )
  }
}
