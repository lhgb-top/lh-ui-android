package top.lhgb.ui.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import top.lhgb.ui.theme.LhTheme
import top.lhgb.ui.tokens.LhTokens

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。这里覆盖 spec 中全部 type × size 组合 + 状态变体。
 */

@Preview(showBackground = true, name = "类型：default")
@Composable
private fun LhButtonTypeDefaultPreview() {
  LhTheme { LhButton(lhOnClick = {}) { Text("默认按钮") } }
}

@Preview(showBackground = true, name = "类型：全部")
@Composable
private fun LhButtonAllTypesPreview() {
  LhTheme {
    Column {
      LhButton(type = LhButtonType.primary, lhOnClick = {}) { Text("主要按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.success, lhOnClick = {}) { Text("成功按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.warning, lhOnClick = {}) { Text("警告按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.danger, lhOnClick = {}) { Text("危险按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.info, lhOnClick = {}) { Text("信息按钮") }
    }
  }
}

@Preview(showBackground = true, name = "尺寸：small / medium / large")
@Composable
private fun LhButtonSizesPreview() {
  LhTheme {
    Column {
      LhButton(lhSize = LhButtonSize.small, lhOnClick = {}) { Text("小型按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(lhSize = LhButtonSize.medium, lhOnClick = {}) { Text("中型按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(lhSize = LhButtonSize.large, lhOnClick = {}) { Text("大型按钮") }
    }
  }
}

@Preview(showBackground = true, name = "状态：禁用 / 加载中")
@Composable
private fun LhButtonStatesPreview() {
  LhTheme {
    Column {
      LhButton(type = LhButtonType.primary, disabled = true, lhOnClick = {}) { Text("禁用按钮") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.primary, loading = true, lhOnClick = {}) { Text("加载中") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.primary, disabled = true, loading = true, lhOnClick = {}) { Text("禁用优先") }
    }
  }
}

@Preview(showBackground = true, name = "图标与花纹底")
@Composable
private fun LhButtonIconAndPatternPreview() {
  LhTheme {
    Column {
      LhButton(type = LhButtonType.primary, icon = "search", lhOnClick = {}) { Text("搜索") }
      Spacer(Modifier.height(LhTokens.Spacing.sm))
      LhButton(type = LhButtonType.primary, bgPattern = true, lhOnClick = {}) { Text("花纹底") }
    }
  }
}
