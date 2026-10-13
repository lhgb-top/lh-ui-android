package top.lhgb.ui.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "基础：标题 / 值 / 说明")
@Composable
private fun LhCellBasicPreview() {
  LhTheme {
    Column(modifier = Modifier.fillMaxWidth()) {
      LhCell(title = "标题")
      LhCell(title = "标题", value = "右侧值")
      LhCell(title = "标题", label = "说明文字", value = "右侧值")
    }
  }
}

@Preview(showBackground = true, name = "必填 / 图标 / 可点击")
@Composable
private fun LhCellFeaturesPreview() {
  LhTheme {
    Column(modifier = Modifier.fillMaxWidth()) {
      LhCell(title = "必填项", required = true)
      LhCell(title = "带图标", icon = "star")
      LhCell(title = "可点击", value = "点击我", clickable = true, onClick = {})
    }
  }
}

@Preview(showBackground = true, name = "无边框 / 组合")
@Composable
private fun LhCellCombinedPreview() {
  LhTheme {
    Column(modifier = Modifier.fillMaxWidth()) {
      LhCell(title = "无边框", value = "无", bordered = false)
      LhCell(
        title = "组合",
        label = "说明文字",
        value = "右侧值",
        required = true,
        icon = "star",
        clickable = true,
        onClick = {},
      )
      Spacer(Modifier.height(8.dp))
    }
  }
}
