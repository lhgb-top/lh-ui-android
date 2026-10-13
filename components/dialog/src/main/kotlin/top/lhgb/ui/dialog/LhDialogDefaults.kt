package top.lhgb.ui.dialog

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 对话框外观解析结果。由 [LhDialogDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/dialog.json）能在无设备的 JVM 单测里断言
 * 「type → 令牌」的映射。
 */
data class LhDialogAppearance(
  val width: Dp,
  val borderRadius: Dp,
  val paddingHorizontal: Dp,
  val paddingVertical: Dp,
  val titleFontSize: TextUnit,
  val contentFontSize: TextUnit,
  val gap: Dp,
  /** 内容卡底色 */
  val surfaceColor: Color,
  /** 遮罩色 */
  val overlayColor: Color,
  val titleColor: Color,
  val contentColor: Color,
  /** 确认按钮映射到的按钮类型 */
  val confirmButtonType: LhButtonType,
  /** 层级（zIndex.modal，语义对齐：Compose Dialog 自带窗口级层级） */
  val zIndex: Int,
  val backdropZIndex: Int,
)

/**
 * 对话框默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/dialog.json` 的 behaviors：
 * - 内容卡宽度 dialog.width、圆角 dialog.borderRadius、内边距 dialog.padding*，
 *   底色 background.default，投影 shadow.base
 * - 遮罩取 background.overlay
 * - 标题 titleFontSize + text.primary，内容 contentFontSize + text.regular，间距 dialog.gap
 * - 确认按钮按 type 映射语义色；取消按钮固定 default
 * - 层级 zIndex.modal（遮罩 zIndex.modalBackdrop）
 */
object LhDialogDefaults {

  /**
   * 解析对话框外观。
   *
   * @param type 语义类型，决定确认按钮类型
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(type: LhDialogType, colors: LhColors): LhDialogAppearance = LhDialogAppearance(
    width = LhTokens.Dialog.width,
    borderRadius = LhTokens.Dialog.borderRadius,
    paddingHorizontal = LhTokens.Dialog.paddingHorizontal,
    paddingVertical = LhTokens.Dialog.paddingVertical,
    titleFontSize = LhTokens.Dialog.titleFontSize,
    contentFontSize = LhTokens.Dialog.contentFontSize,
    gap = LhTokens.Dialog.gap,
    surfaceColor = colors.backgroundDefault,
    overlayColor = colors.backgroundOverlay,
    titleColor = colors.textPrimary,
    contentColor = colors.textRegular,
    confirmButtonType = type.toButtonType(),
    zIndex = LhTokens.ZIndex.modal,
    backdropZIndex = LhTokens.ZIndex.modalBackdrop,
  )

  /** spec：确认按钮按 type 映射语义色（LhButtonType 与本枚举逐字同构） */
  private fun LhDialogType.toButtonType(): LhButtonType = when (this) {
    LhDialogType.default -> LhButtonType.default
    LhDialogType.primary -> LhButtonType.primary
    LhDialogType.success -> LhButtonType.success
    LhDialogType.warning -> LhButtonType.warning
    LhDialogType.danger -> LhButtonType.danger
    LhDialogType.info -> LhButtonType.info
  }
}
