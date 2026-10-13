package top.lhgb.ui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconSize
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 导航栏组件（页面顶部标题栏，左中右三段布局）。
 *
 * props 与 `lh-ui-core/specs/navbar.json` 逐字对齐；行为遵循 `cases/navbar.json`
 * 的 6 条黄金用例（令牌映射翻译为 JVM 单测，左右点击翻译为 Compose UI Test）。
 *
 * @param title 中间标题
 * @param onLeftClick 点击左侧区域时触发
 * @param onRightClick 点击右侧区域时触发
 * @param modifier 修饰符
 * @param leftText 左侧按钮文字；与 leftIcon 二者非空时都显示
 * @param leftIcon 左侧图标名（本地图标体系，如 "arrow-left"）
 * @param rightText 右侧按钮文字；与 rightIcon 二者非空时都显示
 * @param rightIcon 右侧图标名
 * @param bordered 是否绘制底部分隔线
 * @param titleContent 标题内容（覆盖 title，spec 的 default 插槽）
 * @param leftContent 左侧内容（覆盖 leftText/leftIcon，spec 的 left 插槽）
 * @param rightContent 右侧内容（覆盖 rightText/rightIcon，spec 的 right 插槽）
 */
@Composable
fun LhNavbar(
  title: String? = null,
  onLeftClick: () -> Unit = {},
  onRightClick: () -> Unit = {},
  modifier: Modifier = Modifier,
  leftText: String? = null,
  leftIcon: String? = null,
  rightText: String? = null,
  rightIcon: String? = null,
  bordered: Boolean = true,
  titleContent: @Composable (() -> Unit)? = null,
  leftContent: @Composable (() -> Unit)? = null,
  rightContent: @Composable (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(bordered, colors) {
    LhNavbarDefaults.resolve(bordered, colors)
  }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(appearance.height)
      .background(color = appearance.backgroundColor)
      .drawBehind {
        // spec：bordered 为 true 时在底部绘制分隔线（border.lighter，宽 navbar.borderWidth）
        if (bordered) {
          val y = this.size.height - appearance.borderWidth.toPx() / 2f
          drawLine(
            color = appearance.dividerColor,
            start = Offset(0f, y),
            end = Offset(this.size.width, y),
            strokeWidth = appearance.borderWidth.toPx(),
          )
        }
      },
    contentAlignment = Alignment.Center,
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .height(appearance.height)
        .padding(horizontal = appearance.paddingHorizontal),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      // 左侧：无内容时不响应点击
      NavbarSide(
        content = leftContent,
        text = leftText,
        icon = leftIcon,
        appearance = appearance,
        onClick = onLeftClick,
        contentDescription = "左侧",
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.weight(1f),
      )

      // 中间标题：水平居中于可用宽度
      Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center,
      ) {
        if (titleContent != null) {
          titleContent()
        } else if (!title.isNullOrEmpty()) {
          Text(
            text = title,
            color = appearance.titleColor,
            fontSize = appearance.titleFontSize,
          )
        }
      }

      // 右侧：无内容时不响应点击
      NavbarSide(
        content = rightContent,
        text = rightText,
        icon = rightIcon,
        appearance = appearance,
        onClick = onRightClick,
        contentDescription = "右侧",
        horizontalAlignment = Alignment.End,
        modifier = Modifier.weight(1f),
      )
    }
  }
}

/** 左/右一侧：文字 + 图标（二者非空时都显示），有内容时可点击 */
@Composable
private fun NavbarSide(
  content: @Composable (() -> Unit)?,
  text: String?,
  icon: String?,
  appearance: LhNavbarAppearance,
  onClick: () -> Unit,
  contentDescription: String,
  horizontalAlignment: Alignment.Horizontal,
  modifier: Modifier = Modifier,
) {
  val hasCustomContent = content != null
  val hasBuiltinContent = !text.isNullOrEmpty() || !icon.isNullOrEmpty()
  val hasContent = hasCustomContent || hasBuiltinContent

  Box(
    modifier = modifier
      .then(
        if (hasContent) {
          val interactionSource = remember { MutableInteractionSource() }
          Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
          ).semantics {
            role = Role.Button
            this.contentDescription = contentDescription
          }
        } else {
          Modifier
        },
      ),
    contentAlignment = Alignment.Center,
  ) {
    if (hasCustomContent) {
      content!!()
    } else if (hasBuiltinContent) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
      ) {
        if (!icon.isNullOrEmpty()) {
          LhIcon(
            name = icon,
            size = LhIconSize.medium,
            tone = LhIconTone.default,
          )
        }
        if (!text.isNullOrEmpty()) {
          Text(text = text, color = appearance.sideColor, fontSize = appearance.titleFontSize)
        }
      }
    }
  }
}
