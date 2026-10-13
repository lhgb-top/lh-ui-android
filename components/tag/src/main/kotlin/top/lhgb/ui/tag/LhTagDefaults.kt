package top.lhgb.ui.tag

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 标签外观解析结果。由 [LhTagDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/tag.json）能在无设备的 JVM 单测里断言
 * 「type/size/plain/round → 令牌」的映射。
 */
data class LhTagAppearance(
  val height: Dp,
  val paddingHorizontal: Dp,
  val fontSize: TextUnit,
  val containerColor: Color,
  val contentColor: Color,
  val borderColor: Color,
  val borderWidth: Dp,
  /** 圆角；round 态为高度一半（胶囊），否则为 tag.borderRadius */
  val borderRadius: Dp,
  /** 关闭符尺寸（正方形对角线两条线） */
  val closeSize: Dp,
  /** 关闭符与文字间距 */
  val closeGap: Dp,
)

/**
 * 标签默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/tag.json` 的 behaviors：
 * - size 映射：small/medium/large 分别取 tag.height.<size> 等尺寸令牌
 * - plain 为 false 时：default 类型取 backgroundDefault 底 + textPrimary 字 + borderBase 描边；
 *   其余类型取语义色实心底 + onPrimary 字、无描边
 * - plain 为 true 时：default 类型同上；其余类型取语义 bg 浅底 + 语义 default 字 + 语义 default 描边
 * - round 为 true 时圆角取高度一半，否则取 tag.borderRadius
 * - 关闭符与文字间距取 spacing.xs
 */
object LhTagDefaults {

  /**
   * 解析标签外观。
   *
   * @param type 语义类型
   * @param size 标签尺寸
   * @param plain 是否浅底描边形态
   * @param round 是否胶囊形态（圆角取高度一半）
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(
    type: LhTagType,
    size: LhTagSize,
    plain: Boolean,
    round: Boolean,
    colors: LhColors,
  ): LhTagAppearance {
    // type + plain → 底色 / 文字色 / 描边色
    val (container, content, border) = when (type) {
      LhTagType.default -> Triple(colors.backgroundDefault, colors.textPrimary, colors.borderBase)
      LhTagType.primary -> if (plain) {
        Triple(colors.primaryBg, colors.primary, colors.primary)
      } else {
        Triple(colors.primary, colors.onPrimary, Color.Transparent)
      }
      LhTagType.success -> if (plain) {
        Triple(colors.successBg, colors.success, colors.success)
      } else {
        Triple(colors.success, colors.onPrimary, Color.Transparent)
      }
      LhTagType.warning -> if (plain) {
        Triple(colors.warningBg, colors.warning, colors.warning)
      } else {
        Triple(colors.warning, colors.onPrimary, Color.Transparent)
      }
      LhTagType.danger -> if (plain) {
        Triple(colors.dangerBg, colors.danger, colors.danger)
      } else {
        Triple(colors.danger, colors.onPrimary, Color.Transparent)
      }
      LhTagType.info -> if (plain) {
        Triple(colors.infoBg, colors.info, colors.info)
      } else {
        Triple(colors.info, colors.onPrimary, Color.Transparent)
      }
    }

    // size → 尺寸令牌
    val height: Dp
    val paddingHorizontal: Dp
    val fontSize: TextUnit
    when (size) {
      LhTagSize.small -> {
        height = LhTokens.Tag.Height.small
        paddingHorizontal = LhTokens.Tag.PaddingHorizontal.small
        fontSize = LhTokens.Tag.FontSize.small
      }
      LhTagSize.medium -> {
        height = LhTokens.Tag.Height.medium
        paddingHorizontal = LhTokens.Tag.PaddingHorizontal.medium
        fontSize = LhTokens.Tag.FontSize.medium
      }
      LhTagSize.large -> {
        height = LhTokens.Tag.Height.large
        paddingHorizontal = LhTokens.Tag.PaddingHorizontal.large
        fontSize = LhTokens.Tag.FontSize.large
      }
    }

    return LhTagAppearance(
      height = height,
      paddingHorizontal = paddingHorizontal,
      fontSize = fontSize,
      containerColor = container,
      contentColor = content,
      borderColor = border,
      borderWidth = LhTokens.Tag.borderWidth,
      borderRadius = if (round) height / 2f else LhTokens.Tag.borderRadius,
      closeSize = LhTokens.Tag.Height.small * 0.5f,
      closeGap = LhTokens.Spacing.xs,
    )
  }
}
