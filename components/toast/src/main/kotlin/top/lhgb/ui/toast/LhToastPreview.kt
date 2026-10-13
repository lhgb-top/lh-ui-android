package top.lhgb.ui.toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。轻提示是窗口级浮层，Preview 中用全屏容器承载；可见态由
 * `LaunchedEffect` 驱动 `show`（避免重组时反复重置计时）。
 */

@Preview(showBackground = true, name = "默认轻提示（可见态）")
@Composable
private fun LhToastDefaultPreview() {
  val state = rememberLhToastState()
  LaunchedEffect(Unit) { state.show("已保存") }
  LhTheme {
    Column(modifier = Modifier.fillMaxSize()) {
      LhToast(state)
    }
  }
}

@Preview(showBackground = true, name = "成功类型 · 顶部定位")
@Composable
private fun LhToastSuccessTopPreview() {
  val state = rememberLhToastState()
  LaunchedEffect(Unit) { state.show("操作成功") }
  LhTheme {
    Column(modifier = Modifier.fillMaxSize()) {
      LhToast(state, type = LhToastType.success, position = LhToastPosition.top)
    }
  }
}

@Preview(showBackground = true, name = "警告类型 · 居中定位")
@Composable
private fun LhToastWarningCenterPreview() {
  val state = rememberLhToastState()
  LaunchedEffect(Unit) { state.show("请注意输入内容") }
  LhTheme {
    Column(modifier = Modifier.fillMaxSize()) {
      LhToast(state, type = LhToastType.warning, position = LhToastPosition.center)
    }
  }
}

@Preview(showBackground = true, name = "危险类型 · 底部定位")
@Composable
private fun LhToastDangerBottomPreview() {
  val state = rememberLhToastState()
  LaunchedEffect(Unit) { state.show("删除失败，请重试") }
  LhTheme {
    Column(modifier = Modifier.fillMaxSize()) {
      LhToast(state, type = LhToastType.danger, position = LhToastPosition.bottom)
    }
  }
}

@Preview(showBackground = true, name = "宿主驱动：点击按钮 show")
@Composable
private fun LhToastHostDrivenPreview() {
  val state = rememberLhToastState()
  LhTheme {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Button(onClick = { state.show("轻提示已弹出") }) { Text("弹出轻提示") }
      LhToast(state)
    }
  }
}
