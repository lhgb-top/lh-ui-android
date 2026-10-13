package top.lhgb.ui.switch

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
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "状态：开 / 关 / 禁用 / 加载中")
@Composable
private fun LhSwitchStatesPreview() {
  LhTheme {
    var on by remember { mutableStateOf(false) }
    Column(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalAlignment = Alignment.Start,
    ) {
      LhSwitch(checked = false, onChange = {})
      LhSwitch(checked = true, onChange = {})
      LhSwitch(checked = false, disabled = true, onChange = {})
      LhSwitch(checked = true, disabled = true, onChange = {})
      LhSwitch(checked = false, loading = true, onChange = {})
      LhSwitch(checked = true, loading = true, onChange = {})
      LhSwitch(checked = on, onChange = { on = it })
    }
  }
}

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhSwitchSizesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      LhSwitch(checked = true, onChange = {}, size = LhSwitchSize.small)
      LhSwitch(checked = true, onChange = {}, size = LhSwitchSize.medium)
      LhSwitch(checked = true, onChange = {}, size = LhSwitchSize.large)
    }
  }
}
