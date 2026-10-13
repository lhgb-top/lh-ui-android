package top.lhgb.ui.docs.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import top.lhgb.ui.tokens.LhTokens

/**
 * 手机框实时预览：承载**真实可交互的 Compose 组件**，替代网页文档的静态 HTML 模拟器。
 *
 * 结构对齐网页版 sim-phone：状态栏（9:41 + 场景名）+ 圆角屏幕区。宽度填满内容可用宽度。
 */
@Composable
fun DocPreviewFrame(
  scene: String,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Column(modifier = modifier.fillMaxWidth()) {
    val colors = top.lhgb.ui.theme.LhThemeColors
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(LhTokens.Spacing.lg))
        .background(colors.backgroundDefault)
        .drawBehind {
          // 屏幕外框描边
          val w = LhTokens.Docs.Card.borderWidth.toPx()
          drawLine(
            color = colors.borderLighter,
            start = Offset(0f, size.height - w / 2f),
            end = Offset(size.width, size.height - w / 2f),
            strokeWidth = w,
            cap = StrokeCap.Square,
          )
        },
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        // 状态栏
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(colors.backgroundPage)
            .padding(
              horizontal = LhTokens.Spacing.md,
              vertical = LhTokens.Spacing.xs,
            ),
          horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Text(
            text = "9:41",
            color = colors.textPrimary,
            fontSize = LhTokens.Typography.FontSize.small,
          )
          Text(
            text = scene,
            color = colors.textSecondary,
            fontSize = LhTokens.Typography.FontSize.small,
          )
        }
        // 屏幕内容区
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(LhTokens.Spacing.md),
        ) {
          content()
        }
      }
    }
    Text(
      text = "实时预览（真 Compose 渲染，可交互）",
      color = colors.textSecondary,
      fontSize = LhTokens.Docs.Prose.fontSize,
      modifier = Modifier.padding(top = LhTokens.Spacing.xs),
    )
  }
}
