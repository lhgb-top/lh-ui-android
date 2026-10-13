package top.lhgb.ui.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "基础状态")
@Composable
private fun LhInputBasicPreview() {
  LhTheme {
    var text by remember { mutableStateOf("") }
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      LhInput(value = text, onValueChange = { text = it }, placeholder = "请输入内容")
      LhInput(value = "已填写", onValueChange = {})
      LhInput(value = "禁用态", onValueChange = {}, disabled = true)
      LhInput(value = "只读态", onValueChange = {}, readonly = true)
    }
  }
}

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhInputSizesPreview() {
  LhTheme {
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      LhInput(value = "", onValueChange = {}, placeholder = "小号", size = LhInputSize.small)
      LhInput(value = "", onValueChange = {}, placeholder = "中号", size = LhInputSize.medium)
      LhInput(value = "", onValueChange = {}, placeholder = "大号", size = LhInputSize.large)
    }
  }
}

@Preview(showBackground = true, name = "可清除 / 密码 / 前后缀")
@Composable
private fun LhInputFeaturesPreview() {
  LhTheme {
    var clearable by remember { mutableStateOf("可清除的文字") }
    var password by remember { mutableStateOf("secret") }
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      LhInput(value = clearable, onValueChange = { clearable = it }, clearable = true)
      LhInput(value = password, onValueChange = { password = it }, type = LhInputType.password)
      LhInput(
        value = "带前缀",
        onValueChange = {},
        prefix = { LhIcon(name = "search", tone = LhIconTone.secondary, contentDescription = null) },
        suffix = { Text("后缀") },
      )
    }
  }
}
