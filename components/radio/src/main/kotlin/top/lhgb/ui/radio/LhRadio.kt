package top.lhgb.ui.radio

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 单选框组件（受控，不含组逻辑）。
 *
 * props 与 `lh-ui-core/specs/radio.json` 逐字对齐；行为遵循 `cases/radio.json`
 * 的 6 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * 单选语义：仅当未选中且未禁用时，点击命中整个行（圆环 + 标签）才变为选中并触发
 * `onChange(true)`；已选中时点击保持选中且不触发。
 *
 * @param checked 是否选中
 * @param onChange 由未选中变为选中时触发，携带 `checked = true`
 * @param modifier 修饰符
 * @param disabled 是否禁用
 * @param label 文字标签（绘制在圆环右侧）
 * @param value 单选值（组内标识，组件本身不含组逻辑，由宿主维护单选组）
 * @param size 圆环尺寸档位
 * @param labelContent 标签内容（覆盖 label，spec 的 default 插槽）
 */
@Composable
fun LhRadio(
  checked: Boolean,
  onChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
  disabled: Boolean = false,
  label: String? = null,
  value: String? = null,
  size: LhRadioSize = LhRadioSize.medium,
  labelContent: @Composable (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(size, checked, disabled, colors) {
    LhRadioDefaults.resolve(size, checked, disabled, colors)
  }
  val interactionSource = remember { MutableInteractionSource() }

  Row(
    modifier = modifier
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = appearance.enabled,
      ) {
        // spec：仅未选中时点击才变为选中并触发 onChange(true)
        if (!checked) onChange(true)
      }
      .semantics {
        role = Role.RadioButton
        contentDescription = if (checked) "已选中" else "未选中"
      },
    horizontalArrangement = Arrangement.spacedBy(appearance.gap),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    RadioIndicator(
      checked = checked,
      appearance = appearance,
      modifier = Modifier
        .size(appearance.size)
        .clip(CircleShape),
    )

    if (labelContent != null) {
      labelContent()
    } else if (label != null) {
      Text(text = label, color = appearance.labelColor, fontSize = appearance.fontSize)
    }
  }
}

/** 外环描边 + 选中时的内圆点 */
@Composable
private fun RadioIndicator(
  checked: Boolean,
  appearance: LhRadioAppearance,
  modifier: Modifier = Modifier,
) {
  Canvas(modifier = modifier) {
    val w = this.size.width
    val h = this.size.height

    // 外环描边（未选中 border.base，选中 primary）
    drawCircle(
      color = appearance.ringColor,
      radius = (minOf(w, h) / 2f) - (appearance.ringWidth.toPx() / 2f),
      style = Stroke(width = appearance.ringWidth.toPx()),
    )

    // 选中时内部画实心圆点，直径 = 尺寸 × dotScale
    if (checked) {
      drawCircle(
        color = appearance.dotColor,
        radius = appearance.dotSize.toPx() / 2f,
      )
    }
  }
}
