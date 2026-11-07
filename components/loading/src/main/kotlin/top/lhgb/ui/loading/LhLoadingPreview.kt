package top.lhgb.ui.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import top.lhgb.ui.theme.LhTheme
import top.lhgb.ui.tokens.LhTokens

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhLoadingSizesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xl),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      LhLoading(lhSize = LhLoadingSize.small)
      LhLoading(lhSize = LhLoadingSize.medium)
      LhLoading(lhSize = LhLoadingSize.large)
    }
  }
}

@Preview(showBackground = true, name = "色调：全部")
@Composable
private fun LhLoadingTonesPreview() {
  LhTheme {
    Column(
      modifier = Modifier.padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
    ) {
      LhLoading(tone = LhLoadingTone.primary)
      LhLoading(tone = LhLoadingTone.success)
      LhLoading(tone = LhLoadingTone.warning)
      LhLoading(tone = LhLoadingTone.danger)
      LhLoading(tone = LhLoadingTone.info)
    }
  }
}

@Preview(showBackground = true, name = "行内加载")
@Composable
private fun LhLoadingInlinePreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      LhLoading(lhSize = LhLoadingSize.small)
      Text("加载中…")
    }
  }
}
