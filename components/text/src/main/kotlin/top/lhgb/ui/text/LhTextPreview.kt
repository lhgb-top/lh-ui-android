package top.lhgb.ui.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import top.lhgb.ui.theme.LhTheme
import top.lhgb.ui.tokens.LhTokens

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "级别：全部")
@Composable
private fun LhTextAllLevelsPreview() {
  LhTheme {
    Column(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    ) {
      LhText(content = "特大 extraLarge", level = LhTextLevel.extraLarge)
      LhText(content = "大 large", level = LhTextLevel.large)
      LhText(content = "中 medium", level = LhTextLevel.medium)
      LhText(content = "正文 base（默认）")
      LhText(content = "小 small", level = LhTextLevel.small)
      LhText(content = "特小 extraSmall", level = LhTextLevel.extraSmall)
    }
  }
}

@Preview(showBackground = true, name = "色调")
@Composable
private fun LhTextTonesPreview() {
  LhTheme {
    Column(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    ) {
      LhText(content = "主要 primary", tone = LhTextTone.primary)
      LhText(content = "常规 regular", tone = LhTextTone.regular)
      LhText(content = "次要 secondary", tone = LhTextTone.secondary)
      LhText(content = "占位 placeholder", tone = LhTextTone.placeholder)
      LhText(content = "禁用 disabled", tone = LhTextTone.disabled)
      LhText(content = "选中 selected", tone = LhTextTone.selected)
    }
  }
}

@Preview(showBackground = true, name = "字重与对齐")
@Composable
private fun LhTextWeightAndAlignPreview() {
  LhTheme {
    Column(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    ) {
      LhText(content = "常规字重 primary")
      LhText(content = "加粗 bold", weight = LhTextWeight.bold)
      LhText(content = "居中 center", lhAlign = LhTextAlign.center, modifier = Modifier.fillMaxWidth())
    }
  }
}
