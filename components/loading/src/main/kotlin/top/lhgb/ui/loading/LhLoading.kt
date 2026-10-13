package top.lhgb.ui.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 加载指示器组件：环形旋转动画。
 *
 * props 与 `lh-ui-core/specs/loading.json` 逐字对齐；行为遵循 `cases/loading.json`
 * 的 5 条黄金用例（纯展示组件，全部翻译为 JVM 单测）。
 *
 * spec 的绘制指令是 `draw_ring`（半径为尺寸一半，描边宽 loading.strokeWidth，
 * 旋转进度由后端驱动）——端侧用自绘 Canvas 精确消费令牌：描边宽度取 strokeWidth
 * 令牌，直径取 size 令牌，旋转走 Compose 无限动画（时长 Motion.Duration.base 毫秒）。
 *
 * @param modifier 修饰符
 * @param size 指示器直径档位，映射到 loading.size.* 令牌
 * @param tone 环色，取对应语义色令牌
 */
@Composable
fun LhLoading(
  modifier: Modifier = Modifier,
  size: LhLoadingSize = LhLoadingSize.medium,
  tone: LhLoadingTone = LhLoadingTone.primary,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(size, tone, colors) {
    LhLoadingDefaults.resolve(size, tone, colors)
  }

  // spec：旋转进度由后端驱动。端侧等价物是无限旋转，时长取 Motion.Duration.base（毫秒）
  val transition = rememberInfiniteTransition(label = "lh-loading-rotation")
  val rotation by transition.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(
        durationMillis = LhTokens.Motion.Duration.base,
        easing = LinearEasing,
      ),
      repeatMode = RepeatMode.Restart,
    ),
    label = "lh-loading-rotation-angle",
  )

  Canvas(modifier = modifier.size(appearance.size)) {
    val canvasSize = this.size
    val strokePx = appearance.strokeWidth.toPx()
    val diameter = minOf(canvasSize.width, canvasSize.height) - strokePx
    val topLeft = Offset(
      x = (canvasSize.width - diameter) / 2f,
      y = (canvasSize.height - diameter) / 2f,
    )

    rotate(degrees = rotation - 90f) {
      drawArc(
        color = appearance.color,
        startAngle = 0f,
        sweepAngle = 270f,
        useCenter = false,
        topLeft = topLeft,
        size = Size(diameter, diameter),
        style = Stroke(width = strokePx, cap = StrokeCap.Round),
      )
    }
  }
}
