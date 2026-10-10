package com.lh.ui.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lh.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。这里覆盖 spec 中全部 type × size 组合 + 状态变体。
 */

@Preview(showBackground = true, name = "类型：default")
@Composable
private fun LhButtonTypeDefaultPreview() {
  LhTheme { LhButton(onClick = {}) { Text("默认按钮") } }
}

@Preview(showBackground = true, name = "类型：全部")
@Composable
private fun LhButtonAllTypesPreview() {
  LhTheme {
    Column {
      LhButton(type = LhButtonType.primary, onClick = {}) { Text("主要按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.success, onClick = {}) { Text("成功按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.warning, onClick = {}) { Text("警告按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.danger, onClick = {}) { Text("危险按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.info, onClick = {}) { Text("信息按钮") }
    }
  }
}

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhButtonSizesPreview() {
  LhTheme {
    Column {
      LhButton(size = LhButtonSize.small, onClick = {}) { Text("小型按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(size = LhButtonSize.medium, onClick = {}) { Text("中型按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(size = LhButtonSize.large, onClick = {}) { Text("大型按钮") }
    }
  }
}

@Preview(showBackground = true, name = "状态：禁用 / 加载中")
@Composable
private fun LhButtonStatesPreview() {
  LhTheme {
    Column {
      LhButton(type = LhButtonType.primary, disabled = true, onClick = {}) { Text("禁用按钮") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.primary, loading = true, onClick = {}) { Text("加载中") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.primary, disabled = true, loading = true, onClick = {}) { Text("禁用优先") }
    }
  }
}

@Preview(showBackground = true, name = "图标与花纹底")
@Composable
private fun LhButtonIconAndPatternPreview() {
  LhTheme {
    Column {
      LhButton(type = LhButtonType.primary, icon = "search", onClick = {}) { Text("搜索") }
      Spacer(Modifier.height(8.dp))
      LhButton(type = LhButtonType.primary, bgPattern = true, onClick = {}) { Text("花纹底") }
    }
  }
}
