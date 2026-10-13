package top.lhgb.ui.navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "三段布局：左返回 / 中标题 / 右编辑")
@Composable
private fun LhNavbarFullPreview() {
  LhTheme {
    Column(modifier = Modifier.fillMaxWidth().padding(0.dp)) {
      LhNavbar(
        title = "青花瓷",
        leftIcon = "arrow-left",
        rightText = "编辑",
        onLeftClick = {},
        onRightClick = {},
      )
    }
  }
}

@Preview(showBackground = true, name = "仅标题（无两侧内容）")
@Composable
private fun LhNavbarTitleOnlyPreview() {
  LhTheme {
    LhNavbar(title = "设置", onLeftClick = {}, onRightClick = {})
  }
}

@Preview(showBackground = true, name = "无分隔线")
@Composable
private fun LhNavbarBorderlessPreview() {
  LhTheme {
    LhNavbar(
      title = "无边框",
      leftText = "返回",
      bordered = false,
      onLeftClick = {},
      onRightClick = {},
    )
  }
}

@Preview(showBackground = true, name = "插槽覆盖标题与两侧")
@Composable
private fun LhNavbarSlotsPreview() {
  LhTheme {
    LhNavbar(
      onLeftClick = {},
      onRightClick = {},
      titleContent = { Text("自定义标题", color = top.lhgb.ui.theme.LhThemeColors.primary) },
      leftContent = { Text("左", color = top.lhgb.ui.theme.LhThemeColors.textSecondary) },
      rightContent = { Text("右", color = top.lhgb.ui.theme.LhThemeColors.textSecondary) },
    )
  }
}
