package top.lhgb.ui.cell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 单元格组件：列表行。
 *
 * props 与 `lh-ui-core/specs/cell.json` 逐字对齐；行为遵循 `cases/cell.json`
 * 的 6 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * 三段式布局：左侧图标 / 中间标题+说明 / 右侧值+箭头。
 *
 * @param title 标题（主文字）
 * @param modifier 修饰符
 * @param value 右侧值（次文字，右对齐）
 * @param label 标题下方的说明文字
 * @param bordered 是否绘制底部分隔线（color.border.lighter）
 * @param clickable 是否可点击；为 true 时显示右侧指示箭头并响应点击
 * @param required 是否在标题前显示必填红星（color.danger.default）
 * @param icon 左侧图标名（本地图标体系）；空时不绘制
 * @param onClick 点击回调；clickable 为 false 时不触发
 * @param titleContent 标题内容（覆盖 title，spec 的 default 插槽）
 * @param valueContent 右侧值内容（覆盖 value，spec 的 value 插槽）
 */
@Composable
fun LhCell(
  title: String? = null,
  modifier: Modifier = Modifier,
  value: String? = null,
  label: String? = null,
  bordered: Boolean = true,
  clickable: Boolean = false,
  required: Boolean = false,
  icon: String? = null,
  onClick: (() -> Unit)? = null,
  titleContent: @Composable (() -> Unit)? = null,
  valueContent: @Composable (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(bordered, required, colors) {
    LhCellDefaults.resolve(required, bordered, colors)
  }
  val interactionSource = remember { MutableInteractionSource() }

  Row(
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .then(
        if (clickable && onClick != null) {
          Modifier.clickable(
            interactionSource = interactionSource,
            indication = ripple(color = colors.hoverBg),
          ) { onClick() }
        } else {
          Modifier
        },
      )
      .padding(
        horizontal = appearance.paddingHorizontal,
        vertical = appearance.paddingVertical,
      )
      .drawBehind {
        if (bordered) {
          val y = this.size.height - appearance.dividerWidth.toPx() / 2f
          drawLine(
            color = appearance.dividerColor,
            start = Offset(0f, y),
            end = Offset(this.size.width, y),
            strokeWidth = appearance.dividerWidth.toPx(),
            cap = StrokeCap.Square,
          )
        }
      }
      .semantics { role = Role.Button },
    horizontalArrangement = Arrangement.spacedBy(appearance.gap),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    // 左：图标
    if (icon != null) {
      LhIcon(
        name = icon,
        size = top.lhgb.ui.icon.LhIconSize.medium,
        tone = top.lhgb.ui.icon.LhIconTone.regular,
        contentDescription = null,
      )
    }

    // 中：标题 + 说明（权重 1，把右侧值挤到尾部）
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.spacedBy(appearance.gap),
    ) {
      if (titleContent != null) {
        titleContent()
      } else if (title != null) {
        Row(verticalAlignment = Alignment.Top) {
          if (required) {
            Text(
              text = "*",
              color = appearance.requiredColor,
              fontSize = appearance.titleFontSize,
            )
          }
          Text(
            text = title,
            color = appearance.titleColor,
            fontSize = appearance.titleFontSize,
            lineHeight = appearance.titleFontSize * appearance.lineHeight,
          )
        }
      }
      if (label != null) {
        Text(
          text = label,
          color = appearance.labelColor,
          fontSize = appearance.labelFontSize,
          lineHeight = appearance.labelFontSize * appearance.lineHeight,
        )
      }
    }

    // 右：值 + 箭头
    if (valueContent != null) {
      valueContent()
    } else if (value != null) {
      Text(
        text = value,
        color = appearance.valueColor,
        fontSize = appearance.valueFontSize,
        textAlign = TextAlign.End,
        lineHeight = appearance.valueFontSize * appearance.lineHeight,
      )
    }
    if (clickable) {
      ChevronIcon(color = appearance.valueColor, size = appearance.iconSize)
    }
  }
}

/** 右侧指示箭头：两条折线（spec：clickable 为 true 时绘制指示箭头） */
@Composable
private fun ChevronIcon(color: Color, size: androidx.compose.ui.unit.Dp) {
  Box(
    modifier = Modifier.size(size),
    contentAlignment = Alignment.Center,
  ) {
    androidx.compose.foundation.Canvas(modifier = Modifier.size(size)) {
      val w = this.size.width
      val h = this.size.height
      // 描边宽随尺寸缩放（图标的 1/16），不引入字面量
      val stroke = w / 16f
      // 折线：上 (0.3w, 0.25h) → 中 (0.7w, 0.5h) → 下 (0.3w, 0.75h)
      val midX = w * 0.7f
      val topY = h * 0.25f
      val midY = h * 0.5f
      val bottomY = h * 0.75f
      val startX = w * 0.3f
      drawLine(color = color, start = Offset(startX, topY), end = Offset(midX, midY), strokeWidth = stroke, cap = StrokeCap.Round)
      drawLine(color = color, start = Offset(midX, midY), end = Offset(startX, bottomY), strokeWidth = stroke, cap = StrokeCap.Round)
    }
  }
}
