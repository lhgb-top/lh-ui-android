package top.lhgb.ui.tag

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhTheme

/**
 * 组件预览（等价于文档示例的第一现场）。`@Preview` 在 Android Studio 中实时渲染，
 * 无需运行设备。
 */

@Preview(showBackground = true, name = "类型：全部")
@Composable
private fun LhTagAllTypesPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      LhTag(text = "默认")
      LhTag(text = "主色", type = LhTagType.primary)
      LhTag(text = "成功", type = LhTagType.success)
      LhTag(text = "警告", type = LhTagType.warning)
      LhTag(text = "危险", type = LhTagType.danger)
      LhTag(text = "信息", type = LhTagType.info)
    }
  }
}

@Preview(showBackground = true, name = "浅底形态：plain")
@Composable
private fun LhTagPlainPreview() {
  LhTheme {
    Row(
      modifier = Modifier.fillMaxWidth().padding(16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      LhTag(text = "主色", type = LhTagType.primary, plain = true)
      LhTag(text = "成功", type = LhTagType.success, plain = true)
      LhTag(text = "警告", type = LhTagType.warning, plain = true)
      LhTag(text = "危险", type = LhTagType.danger, plain = true)
      LhTag(text = "信息", type = LhTagType.info, plain = true)
    }
  }
}

@Preview(showBackground = true, name = "尺寸与胶囊")
@Composable
private fun LhTagSizesPreview() {
  LhTheme {
    Column(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        LhTag(text = "小", size = LhTagSize.small)
        LhTag(text = "中", size = LhTagSize.medium)
        LhTag(text = "大", size = LhTagSize.large)
      }
      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        LhTag(text = "胶囊", round = true)
        LhTag(text = "胶囊主色", type = LhTagType.primary, round = true)
      }
    }
  }
}

@Preview(showBackground = true, name = "可关闭")
@Composable
private fun LhTagClosablePreview() {
  LhTheme {
    Column(
      modifier = Modifier.padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      LhTag(text = "可关闭", closable = true, onClose = {})
      LhTag(text = "可关闭", type = LhTagType.primary, closable = true, onClose = {})
      LhTag(text = "浅底可关闭", type = LhTagType.success, plain = true, closable = true, onClose = {})
    }
  }
}
