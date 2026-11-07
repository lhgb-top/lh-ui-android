package top.lhgb.ui.icon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import top.lhgb.ui.theme.LhTheme
import top.lhgb.ui.tokens.LhTokens

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。覆盖 spec 中全部 tone × size 组合。
 */

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhIconSizesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.base),
    ) {
      LhIcon(name = "star", lhSize = LhIconSize.small)
      LhIcon(name = "star", lhSize = LhIconSize.medium)
      LhIcon(name = "star", lhSize = LhIconSize.large)
    }
  }
}

@Preview(showBackground = true, name = "色调：全部")
@Composable
private fun LhIconTonesPreview() {
  LhTheme {
    Column(
      modifier = Modifier.padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    ) {
      listOf(
        "default" to LhIconTone.default,
        "primary" to LhIconTone.primary,
        "success" to LhIconTone.success,
        "warning" to LhIconTone.warning,
        "danger" to LhIconTone.danger,
        "info" to LhIconTone.info,
        "regular" to LhIconTone.regular,
        "secondary" to LhIconTone.secondary,
      ).forEach { (label, tone) ->
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
        ) {
          LhIcon(name = "star", tone = tone)
          Text(label)
        }
      }
    }
  }
}

@Preview(showBackground = true, name = "可点击")
@Composable
private fun LhIconClickablePreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.base),
    ) {
      LhIcon(name = "search", tone = LhIconTone.primary, lhOnClick = {})
      LhIcon(name = "plus", tone = LhIconTone.primary, lhOnClick = {})
      LhIcon(name = "arrow-left", tone = LhIconTone.primary, lhOnClick = {})
    }
  }
}

@Preview(showBackground = true, name = "未登记名：安全不绘制")
@Composable
private fun LhIconUnknownNamePreview() {
  LhTheme {
    Column(modifier = Modifier.padding(LhTokens.Spacing.base)) {
      Text("下方有一个未登记名的图标（不绘制、不崩）：")
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhIcon(name = "not-registered-icon")
    }
  }
}
