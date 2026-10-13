package top.lhgb.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。Dialog 是窗口级浮层，Preview 中用一个全屏容器承载。
 */

@Preview(showBackground = true, name = "默认对话框")
@Composable
private fun LhDialogDefaultPreview() {
  LhTheme {
    var visible by remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
      LhDialog(
        visible = visible,
        title = "提示",
        content = "这是一个模态对话框，确认后关闭。",
        onConfirm = { visible = false },
        onCancel = { visible = false },
        onClose = { visible = false },
      )
    }
  }
}

@Preview(showBackground = true, name = "语义类型：danger")
@Composable
private fun LhDialogDangerPreview() {
  LhTheme {
    var visible by remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
      LhDialog(
        visible = visible,
        title = "删除确认",
        content = "删除后不可恢复，确定要继续吗？",
        type = LhDialogType.danger,
        confirmText = "删除",
        cancelText = "取消",
        onConfirm = { visible = false },
        onCancel = { visible = false },
        onClose = { visible = false },
      )
    }
  }
}

@Preview(showBackground = true, name = "自定义底部按钮（footer 插槽）")
@Composable
private fun LhDialogFooterSlotPreview() {
  LhTheme {
    var visible by remember { mutableStateOf(true) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
      LhDialog(
        visible = visible,
        title = "自定义底部",
        content = "footer 插槽覆盖默认按钮。",
        onConfirm = {},
        onCancel = {},
        onClose = { visible = false },
        footer = {
          androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            LhButton(type = LhButtonType.primary, onClick = { visible = false }) {
              Text("知道了")
            }
          }
        },
      )
    }
  }
}
