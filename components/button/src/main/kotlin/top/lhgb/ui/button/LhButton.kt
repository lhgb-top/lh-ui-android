package top.lhgb.ui.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.theme.LhThemeColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 图标名 → Material 图标的本地映射（spec：icon 是图标名，各端按本地图标体系渲染）。
 * 未登记的图标名返回 null（不绘制），不抛异常。
 */
object LhButtonIcons {
  private val icons = mapOf(
    "search" to Icons.Filled.Search,
    "refresh" to Icons.Filled.Refresh,
    "check" to Icons.Filled.Check,
    "close" to Icons.Filled.Close,
  )

  fun resolve(name: String?): ImageVector? = name?.let { icons[it] }
}

/**
 * 按钮组件：触发即时操作。
 *
 * props 与 `lh-ui-core/specs/button.json` 逐字对齐；行为遵循 `cases/button.json`
 * 的 8 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * @param onClick 点击回调；disabled 或 loading 为 true 时不触发
 * @param type 按钮类型，决定底色与文字色
 * @param size 按钮尺寸，映射到 button.height / paddingHorizontal / fontSize / iconSize 令牌
 * @param disabled 是否禁用；禁用时不响应点击并呈现禁用样式（disabled.* 令牌）
 * @param loading 是否加载中；加载中显示指示器且点击不触发 onClick
 * @param icon 图标名（本地图标体系，见 [LhButtonIcons]），按钮内左侧绘制
 * @param bgPattern 是否绘制国风花纹底纹（消费 color.decoration 令牌）
 * @param content 按钮文字内容（spec 的 default 插槽）
 */
@Composable
fun LhButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  type: LhButtonType = LhButtonType.default,
  size: LhButtonSize = LhButtonSize.medium,
  disabled: Boolean = false,
  loading: Boolean = false,
  icon: String? = null,
  bgPattern: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colors = LhThemeColors
  val appearance = remember(type, size, disabled, loading, bgPattern, colors) {
    LhButtonDefaults.resolve(type, size, disabled, loading, bgPattern, colors)
  }
  val interactionSource = remember { MutableInteractionSource() }
  val shape = remember(appearance.borderRadius) { RoundedCornerShape(appearance.borderRadius) }
  val resolvedIcon = remember(icon) { LhButtonIcons.resolve(icon) }

  Box(
    modifier = modifier
      .height(appearance.height)
      .clip(shape)
      .drawBehind {
        // 花纹底纹（先画，垫在底色之下，不影响文字对比度）
        // 注意：lambda 内的 size 会被外层 LhButton 的 size 参数（LhButtonSize）遮蔽，
        // 必须用 this.size 显式取 DrawScope 的画布尺寸。
        if (appearance.patternColor != null) {
          val step = LhTokens.Spacing.base.toPx()
          val patternBrush = Brush.linearGradient(
            colors = listOf(appearance.patternColor, Color.Transparent),
            start = Offset(0f, 0f),
            end = Offset(step, step),
          )
          val canvasW = this.size.width
          val canvasH = this.size.height
          var x = -canvasH
          while (x < canvasW) {
            drawLine(
              brush = patternBrush,
              start = Offset(x, 0f),
              end = Offset(x + canvasH, canvasH),
              strokeWidth = LhTokens.Button.borderWidth.toPx(),
            )
            x += step
          }
        }

        // 底色
        drawRect(color = appearance.containerColor)

        // 描边（默认类型有可见描边，其余类型边色为 Transparent，画了也不可见）
        if (appearance.borderColor != Color.Transparent) {
          drawRect(
            color = appearance.borderColor,
            style = Stroke(width = appearance.borderWidth.toPx()),
          )
        }
      }
      .then(
        if (appearance.enabled) {
          Modifier.clickable(
            interactionSource = interactionSource,
            indication = ripple(color = appearance.contentColor),
          ) { onClick() }
        } else {
          Modifier
        },
      )
      .padding(horizontal = appearance.paddingHorizontal)
      .semantics { role = Role.Button },
    contentAlignment = Alignment.Center,
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      if (loading) {
        CircularProgressIndicator(
          modifier = Modifier.size(appearance.iconSize),
          strokeWidth = LhTokens.Loading.strokeWidth,
          color = appearance.contentColor,
        )
      } else if (resolvedIcon != null) {
        Icon(
          imageVector = resolvedIcon,
          contentDescription = null,
          modifier = Modifier.size(appearance.iconSize),
          tint = appearance.contentColor,
        )
      }
      CompositionLocalProvider(LocalTextStyle provides LocalTextStyle.current.copy(fontSize = appearance.fontSize)) {
        content()
      }
    }
  }
}
