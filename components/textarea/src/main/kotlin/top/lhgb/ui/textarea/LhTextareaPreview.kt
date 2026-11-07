package top.lhgb.ui.textarea

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import top.lhgb.ui.theme.LhTheme
import top.lhgb.ui.tokens.LhTokens

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "基础状态")
@Composable
private fun LhTextareaBasicPreview() {
  LhTheme {
    var text by remember { mutableStateOf("") }
    Column(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
    ) {
      LhTextarea(value = text, onValueChange = { text = it }, placeholder = "请输入多行内容…")
      LhTextarea(value = "已填写的内容", onValueChange = {})
      LhTextarea(value = "禁用态", onValueChange = {}, disabled = true)
      LhTextarea(value = "只读态", onValueChange = {}, readonly = true)
    }
  }
}

@Preview(showBackground = true, name = "字数统计")
@Composable
private fun LhTextareaCountPreview() {
  LhTheme {
    var withMax by remember { mutableStateOf("两行") }
    Column(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
    ) {
      LhTextarea(value = withMax, onValueChange = { withMax = it }, showCount = true, maxlength = 10)
      LhTextarea(value = "无上限", onValueChange = {}, showCount = true)
    }
  }
}

@Preview(showBackground = true, name = "行数：2 / 3 / 5")
@Composable
private fun LhTextareaRowsPreview() {
  LhTheme {
    Column(
      modifier = Modifier.fillMaxWidth().padding(LhTokens.Spacing.base),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
    ) {
      LhTextarea(value = "", onValueChange = {}, placeholder = "2 行", rows = 2)
      LhTextarea(value = "", onValueChange = {}, placeholder = "3 行", rows = 3)
      LhTextarea(value = "", onValueChange = {}, placeholder = "5 行", rows = 5)
    }
  }
}
