package top.lhgb.ui.picker

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 选择器外观解析结果。由 [LhPickerDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/picker.json）能在无设备的 JVM 单测里断言
 * 「尺寸/颜色 → 令牌」的映射与索引钳制逻辑。
 */
data class LhPickerAppearance(
  /** 面板高度 */
  val height: Dp,
  /** 候选项高度（滚轮单页高度） */
  val itemHeight: Dp,
  /** 顶部工具栏高度 */
  val toolbarHeight: Dp,
  val itemFontSize: TextUnit,
  /** 面板顶部圆角 */
  val borderRadius: Dp,
  /** 工具栏与指示线的水平内边距 */
  val paddingHorizontal: Dp,
  /** 面板底色 */
  val surfaceColor: Color,
  /** 遮罩色 */
  val overlayColor: Color,
  val titleColor: Color,
  /** 非选中项文字色 */
  val itemColor: Color,
  /** 选中项文字色（取主色，高亮中央项） */
  val itemColorSelected: Color,
  /** 中央指示器两条横线的颜色 */
  val indicatorColor: Color,
  val confirmColor: Color,
  val zIndex: Int,
  val backdropZIndex: Int,
)

/**
 * 选择器默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/picker.json` 的 behaviors：
 * - 面板高度 picker.height，顶部圆角 picker.borderRadius，底色 background.default
 * - 工具栏高度 picker.toolbarHeight：左取消、中标题、右确认
 * - 候选项高度 picker.itemHeight、字号 picker.itemFontSize；中央指示器两条水平线
 *   （border.lighter），间距 = picker.itemHeight
 * - 遮罩 background.overlay，层级 zIndex.modal / zIndex.modalBackdrop
 * - value 越界钳制：负数取 0，大于候选项数 - 1 取最后一项
 */
object LhPickerDefaults {

  /**
   * 解析选择器外观。
   *
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(colors: LhColors): LhPickerAppearance = LhPickerAppearance(
    height = LhTokens.Picker.height,
    itemHeight = LhTokens.Picker.itemHeight,
    toolbarHeight = LhTokens.Picker.toolbarHeight,
    itemFontSize = LhTokens.Picker.itemFontSize,
    borderRadius = LhTokens.Picker.borderRadius,
    paddingHorizontal = LhTokens.Spacing.md,
    surfaceColor = colors.backgroundDefault,
    overlayColor = colors.backgroundOverlay,
    titleColor = colors.textPrimary,
    itemColor = colors.textSecondary,
    itemColorSelected = colors.primary,
    indicatorColor = colors.borderLighter,
    confirmColor = colors.primary,
    zIndex = LhTokens.ZIndex.modal,
    backdropZIndex = LhTokens.ZIndex.modalBackdrop,
  )

  /**
   * spec：value 越界时钳制——负数取 0，大于候选项数 - 1 取最后一项。
   *
   * @param value 选中索引（0 基）
   * @param count 候选项数量
   */
  fun clamp(value: Int, count: Int): Int = when {
    count <= 0 -> 0
    else -> value.coerceIn(0, count - 1)
  }
}
