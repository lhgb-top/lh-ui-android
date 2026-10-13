package top.lhgb.ui.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。单选组逻辑由宿主维护，这里用一个 state 演示互斥。
 */

@Preview(showBackground = true, name = "状态：未选 / 选中 / 禁用")
@Composable
private fun LhRadioStatesPreview() {
  LhTheme {
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      LhRadio(checked = false, onChange = {}, label = "未选中")
      LhRadio(checked = true, onChange = {}, label = "已选中")
      LhRadio(checked = false, disabled = true, onChange = {}, label = "禁用（未选）")
      LhRadio(checked = true, disabled = true, onChange = {}, label = "禁用（选中）")
    }
  }
}

@Preview(showBackground = true, name = "单选组（宿主维护互斥）")
@Composable
private fun LhRadioGroupPreview() {
  LhTheme {
    val options = remember { listOf("选项一" to "a", "选项二" to "b", "选项三" to "c") }
    var selected by remember { mutableStateOf("a") }
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      options.forEach { (text, value) ->
        LhRadio(
          checked = selected == value,
          onChange = { selected = value },
          label = text,
          value = value,
        )
      }
    }
  }
}

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhRadioSizesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      LhRadio(checked = true, onChange = {}, size = LhRadioSize.small)
      LhRadio(checked = true, onChange = {}, size = LhRadioSize.medium)
      LhRadio(checked = true, onChange = {}, size = LhRadioSize.large)
    }
  }
}
