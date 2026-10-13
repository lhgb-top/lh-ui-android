package top.lhgb.ui.checkbox

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "状态：未选 / 选中 / 半选")
@Composable
private fun LhCheckboxStatesPreview() {
  LhTheme {
    var checked by remember { mutableStateOf(false) }
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
      LhCheckbox(checked = false, onCheckedChange = {}, label = "未选中")
      LhCheckbox(checked = true, onCheckedChange = {}, label = "已选中")
      LhCheckbox(checked = false, indeterminate = true, onCheckedChange = {}, label = "半选")
      LhCheckbox(checked = true, disabled = true, onCheckedChange = {}, label = "禁用（选中）")
      LhCheckbox(checked = false, disabled = true, onCheckedChange = {}, label = "禁用（未选）")
      LhCheckbox(
        checked = checked,
        onCheckedChange = { checked = it },
        label = "可切换（点我）",
      )
    }
  }
}

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhCheckboxSizesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
      LhCheckbox(checked = true, onCheckedChange = {}, size = LhCheckboxSize.small)
      LhCheckbox(checked = true, onCheckedChange = {}, size = LhCheckboxSize.medium)
      LhCheckbox(checked = true, onCheckedChange = {}, size = LhCheckboxSize.large)
    }
  }
}

@Preview(showBackground = true, name = "半选各尺寸")
@Composable
private fun LhCheckboxIndeterminateSizesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
      LhCheckbox(checked = false, indeterminate = true, onCheckedChange = {}, size = LhCheckboxSize.small)
      LhCheckbox(checked = false, indeterminate = true, onCheckedChange = {}, size = LhCheckboxSize.medium)
      LhCheckbox(checked = false, indeterminate = true, onCheckedChange = {}, size = LhCheckboxSize.large)
    }
  }
}
