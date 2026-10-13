package top.lhgb.ui.toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors
import top.lhgb.ui.tokens.LhTokens

private const val NANOS_PER_MILLI = 1_000_000

/**
 * 轻提示组件（自持可见性与计时的墨色胶囊浮层）。
 *
 * props 与 `lh-ui-core/specs/toast.json` 逐字对齐；行为遵循 `cases/toast.json`
 * 的 6 条黄金用例（状态机 4 条 + 渲染 2 条，全部翻译为 JVM 单测）。
 *
 * 状态由 [LhToastState] 承载：`show(text)` 使其可见并重置计时，`hide()` 立即隐藏
 * 但不触发 onClose，`tick(deltaMs)` 到期自动隐藏并触发 onClose。spec 说「宿主每帧
 * 调用 tick 推进计时」——本组件在可见时用帧回调自驱动，宿主也可自行调用
 * [LhToastState.tick] 接管。
 *
 * 指令缓冲层无 z 序：Compose [Popup] 自带窗口级层级，`zIndex.toast` 令牌作语义对齐
 * （宿主须在其他内容之后最后绘制）。
 *
 * @param state 轻提示状态机（承载可见性、文字与计时）
 * @param modifier 修饰符（作用于胶囊）
 * @param type 语义类型；非 default 时在文字前绘制对应语义色圆点
 * @param position 在可用区域内的定位；上下两端留 spacing.xl 边距
 */
@Composable
fun LhToast(
  state: LhToastState,
  modifier: Modifier = Modifier,
  type: LhToastType = LhToastType.default,
  position: LhToastPosition = LhToastPosition.center,
) {
  val colors: LhColors = LhThemeColors
  val appearance = LhToastDefaults.resolve(type, colors)

  // spec：宿主每帧调用 tick 推进计时。可见时按帧自驱动，到期自动隐藏并触发 onClose。
  LaunchedEffect(state.visible, state.duration) {
    var lastNanos = 0L
    while (state.visible) {
      withFrameNanos { now ->
        if (lastNanos != 0L) {
          val deltaMs = ((now - lastNanos) / NANOS_PER_MILLI).toInt()
          if (deltaMs > 0) state.tick(deltaMs)
        }
        lastNanos = now
      }
    }
  }

  // spec：不可见时不发射任何绘制指令
  if (!state.visible) return

  val marginPx = with(LocalDensity.current) { LhTokens.Spacing.xl.roundToPx() }
  val popupAlignment = when (position) {
    LhToastPosition.top -> Alignment.TopCenter
    LhToastPosition.center -> Alignment.Center
    LhToastPosition.bottom -> Alignment.BottomCenter
  }
  val popupOffset = when (position) {
    LhToastPosition.top -> IntOffset(0, marginPx)
    LhToastPosition.center -> IntOffset.Zero
    LhToastPosition.bottom -> IntOffset(0, -marginPx)
  }

  Popup(alignment = popupAlignment, offset = popupOffset) {
    Row(
      modifier = modifier
        // spec：宽度由文字测量得出并封顶可用宽度的 80%
        .fillMaxWidth(LhToastDefaults.maxWidthFraction)
        .clip(RoundedCornerShape(appearance.borderRadius))
        .background(color = appearance.backgroundColor, shape = RoundedCornerShape(appearance.borderRadius))
        .padding(
          horizontal = appearance.paddingHorizontal,
          vertical = appearance.paddingVertical,
        ),
      horizontalArrangement = Arrangement.spacedBy(appearance.gap),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      if (appearance.dotColor != null) {
        // spec：type 非 default 时在文字前绘制直径为字号的语义色圆点
        Box(
          modifier = Modifier
            .size(appearance.dotSize)
            .clip(CircleShape)
            .background(color = appearance.dotColor, shape = CircleShape),
        )
      }
      Text(
        text = state.text,
        color = appearance.textColor,
        fontSize = appearance.fontSize,
      )
    }
  }
}
