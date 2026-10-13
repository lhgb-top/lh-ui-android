package top.lhgb.ui.picker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
 * 无需运行设备。选择器是窗口级浮层，Preview 中用全屏容器承载。
 */

private val previewOptions = listOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸")

@Preview(showBackground = true, name = "默认选择器（可见态）")
@Composable
private fun LhPickerDefaultPreview() {
  LhTheme {
    Column(modifier = Modifier.fillMaxSize()) {
      LhPicker(
        visible = true,
        options = previewOptions,
        title = "请选择天干",
        onValueChange = {},
        onConfirm = {},
        onCancel = {},
        onClose = {},
      )
    }
  }
}

@Preview(showBackground = true, name = "宿主驱动：点击按钮弹出")
@Composable
private fun LhPickerHostDrivenPreview() {
  var visible by remember { mutableStateOf(false) }
  var selected by remember { mutableStateOf(0) }
  LhTheme {
    Column(
      modifier = Modifier.fillMaxSize().padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Button(onClick = { visible = true }) { Text("弹出选择器（当前：${previewOptions[selected]}）") }
      LhPicker(
        visible = visible,
        options = previewOptions,
        value = selected,
        title = "请选择天干",
        onValueChange = { selected = it },
        onConfirm = {
          selected = it
          visible = false
        },
        onCancel = { visible = false },
        onClose = { visible = false },
      )
    }
  }
}
