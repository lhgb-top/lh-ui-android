package top.lhgb.ui.tag

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 标签组件：小体积状态标记。
 *
 * props 与 `lh-ui-core/specs/tag.json` 逐字对齐；行为遵循 `cases/tag.json`
 * 的 6 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * @param text 标签文字（spec 的 default 插槽的便捷形态；与 [content] 互斥，优先 text）
 * @param modifier 修饰符
 * @param type 语义类型，决定底色、文字色与描边色
 * @param size 标签尺寸，映射到 tag.height / paddingHorizontal / fontSize 令牌
 * @param plain 是否浅底描边形态
 * @param round 是否胶囊形态（圆角取高度一半）
 * @param closable 是否显示关闭符；点击关闭符触发 [onClose]
 * @param onClose 关闭符点击回调
 * @param content 标签内容（覆盖 text，spec 的 default 插槽）
 */
@Composable
fun LhTag(
  text: String? = null,
  modifier: Modifier = Modifier,
  type: LhTagType = LhTagType.default,
  size: LhTagSize = LhTagSize.medium,
  plain: Boolean = false,
  round: Boolean = false,
  closable: Boolean = false,
  onClose: (() -> Unit)? = null,
  content: @Composable (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(type, size, plain, round, colors) {
    LhTagDefaults.resolve(type, size, plain, round, colors)
  }
  val shape = remember(appearance.borderRadius) { RoundedCornerShape(appearance.borderRadius) }

  Row(
    modifier = modifier
      .height(appearance.height)
      .clip(shape)
      .drawBehind {
        drawRect(color = appearance.containerColor)
        if (appearance.borderColor != Color.Transparent) {
          drawRect(
            color = appearance.borderColor,
            style = Stroke(width = appearance.borderWidth.toPx()),
          )
        }
      }
      .padding(horizontal = appearance.paddingHorizontal)
      .semantics { role = Role.Image },
    horizontalArrangement = Arrangement.spacedBy(appearance.closeGap),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    if (content != null) {
      CompositionLocalProvider(LocalTextStyle provides LocalTextStyle.current.copy(fontSize = appearance.fontSize, color = appearance.contentColor)) {
        content()
      }
    } else if (text != null) {
      Text(text = text, color = appearance.contentColor, fontSize = appearance.fontSize)
    }

    if (closable) {
      // spec：尾部绘制关闭符（两条对角线），点击其区域触发 onClose
      val interactionSource = remember { MutableInteractionSource() }
      Box(
        modifier = Modifier
          .size(appearance.closeSize + appearance.closeGap)
          .clip(RoundedCornerShape(50))
          .clickable(
            interactionSource = interactionSource,
            indication = null,
          ) { onClose?.invoke() }
          .semantics { contentDescription = "关闭" },
        contentAlignment = Alignment.Center,
      ) {
        Canvas(modifier = Modifier.size(appearance.closeSize)) {
          val edge = this.size.minDimension
          val strokeW = appearance.borderWidth.toPx() * 1.5f
          drawLine(
            color = appearance.contentColor,
            start = Offset(0f, 0f),
            end = Offset(edge, edge),
            strokeWidth = strokeW,
            cap = StrokeCap.Round,
          )
          drawLine(
            color = appearance.contentColor,
            start = Offset(edge, 0f),
            end = Offset(0f, edge),
            strokeWidth = strokeW,
            cap = StrokeCap.Round,
          )
        }
      }
    }
  }
}
