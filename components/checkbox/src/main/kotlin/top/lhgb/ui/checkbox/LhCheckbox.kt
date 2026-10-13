package top.lhgb.ui.checkbox

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 复选框组件（受控）。
 *
 * props 与 `lh-ui-core/specs/checkbox.json` 逐字对齐；行为遵循 `cases/checkbox.json`
 * 的 6 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * 点击命中整个行（方框 + 标签区域）：indeterminate 变为选中，否则翻转选中态。
 *
 * @param checked 是否选中
 * @param onCheckedChange 选中态变更回调，携带新选中态
 * @param modifier 修饰符
 * @param indeterminate 是否半选（不确定态）
 * @param disabled 是否禁用
 * @param label 标签文字（spec 的 default 插槽的便捷形态）
 * @param size 方框尺寸档位
 * @param labelContent 标签内容（覆盖 label，spec 的 default 插槽）
 */
@Composable
fun LhCheckbox(
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
  indeterminate: Boolean = false,
  disabled: Boolean = false,
  label: String? = null,
  size: LhCheckboxSize = LhCheckboxSize.medium,
  labelContent: @Composable (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(size, checked, indeterminate, disabled, colors) {
    LhCheckboxDefaults.resolve(size, checked, indeterminate, disabled, colors)
  }
  val interactionSource = remember { MutableInteractionSource() }

  Row(
    modifier = modifier
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = appearance.enabled,
      ) {
        // spec：indeterminate 变为选中，否则翻转选中态
        onCheckedChange(if (indeterminate) true else !checked)
      }
      .semantics {
        role = Role.Checkbox
        contentDescription = if (indeterminate) "半选" else if (checked) "已选中" else "未选中"
      },
    horizontalArrangement = Arrangement.spacedBy(appearance.gap),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    CheckboxIndicator(
      checked = checked,
      indeterminate = indeterminate,
      appearance = appearance,
      modifier = Modifier
        .size(appearance.boxSize)
        .clip(RoundedCornerShape(appearance.borderRadius)),
    )

    if (labelContent != null) {
      labelContent()
    } else if (label != null) {
      Text(text = label, color = appearance.labelColor, fontSize = appearance.fontSize)
    }
  }
}

/** 方框 + 指示符：选中画勾形（两条折线），不确定画横杠，未选中只描边 */
@Composable
private fun CheckboxIndicator(
  checked: Boolean,
  indeterminate: Boolean,
  appearance: LhCheckboxAppearance,
  modifier: Modifier = Modifier,
) {
  Canvas(modifier = modifier) {
    val w = this.size.width
    val h = this.size.height
    val strokeW = appearance.borderWidth.toPx()

    // 底色（未选中为透明，不绘制）
    if (appearance.boxColor != androidx.compose.ui.graphics.Color.Transparent) {
      drawRect(color = appearance.boxColor)
    }
    // 描边
    drawRect(
      color = appearance.borderColor,
      style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeW),
    )

    // 指示符
    if (indeterminate) {
      // 横杠：垂直居中一条水平线
      drawLine(
        color = appearance.indicatorColor,
        start = Offset(w * 0.22f, h * 0.5f),
        end = Offset(w * 0.78f, h * 0.5f),
        strokeWidth = appearance.checkWidth.toPx(),
        cap = StrokeCap.Round,
      )
    } else if (checked) {
      // 勾形两条折线：左下 → 中下 → 右上
      drawLine(
        color = appearance.indicatorColor,
        start = Offset(w * 0.18f, h * 0.52f),
        end = Offset(w * 0.42f, h * 0.76f),
        strokeWidth = appearance.checkWidth.toPx(),
        cap = StrokeCap.Round,
      )
      drawLine(
        color = appearance.indicatorColor,
        start = Offset(w * 0.42f, h * 0.76f),
        end = Offset(w * 0.84f, h * 0.26f),
        strokeWidth = appearance.checkWidth.toPx(),
        cap = StrokeCap.Round,
      )
    }
  }
}
