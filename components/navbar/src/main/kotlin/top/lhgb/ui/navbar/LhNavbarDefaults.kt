package top.lhgb.ui.navbar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 导航栏外观解析结果。由 [LhNavbarDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/navbar.json）能在无设备的 JVM 单测里断言
 * 「尺寸/颜色 → 令牌」的映射。
 */
data class LhNavbarAppearance(
  val height: Dp,
  val paddingHorizontal: Dp,
  val titleFontSize: TextUnit,
  val iconSize: Dp,
  val borderWidth: Dp,
  val backgroundColor: Color,
  val titleColor: Color,
  val sideColor: Color,
  val dividerColor: Color,
  /** 层级（zIndex.sticky，语义对齐：宿主将其作为页面顶部固定栏） */
  val zIndex: Int,
)

/**
 * 导航栏默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/navbar.json` 的 behaviors：
 * - 高度 navbar.height，左右内边距 navbar.paddingHorizontal，底色 background.default
 * - 标题取 navbar.titleFontSize + text.primary，水平居中于可用宽度
 * - 左/右文字取 text.primary，图标尺寸 navbar.iconSize
 * - bordered 为 true 时底部画分隔线（border.lighter，宽 navbar.borderWidth）
 * - 层级取 zIndex.sticky
 */
object LhNavbarDefaults {

  /**
   * 解析导航栏外观。
   *
   * @param bordered 是否绘制底部分隔线
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(bordered: Boolean, colors: LhColors): LhNavbarAppearance = LhNavbarAppearance(
    height = LhTokens.Navbar.height,
    paddingHorizontal = LhTokens.Navbar.paddingHorizontal,
    titleFontSize = LhTokens.Navbar.titleFontSize,
    iconSize = LhTokens.Navbar.iconSize,
    borderWidth = if (bordered) LhTokens.Navbar.borderWidth else Dp.Unspecified,
    backgroundColor = colors.backgroundDefault,
    titleColor = colors.textPrimary,
    sideColor = colors.textPrimary,
    dividerColor = if (bordered) colors.borderLighter else Color.Transparent,
    zIndex = LhTokens.ZIndex.sticky,
  )
}
