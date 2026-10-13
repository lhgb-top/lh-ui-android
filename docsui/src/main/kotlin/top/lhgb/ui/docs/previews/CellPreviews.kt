package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import top.lhgb.ui.cell.LhCell
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocCellBasicPreview() {
  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.xs)) {
    LhCell(title = "标题", value = "值")
    LhCell(title = "带说明", label = "这是 label 说明文字", value = "详细")
    LhCell(title = "左侧图标", icon = "search", value = "搜到的值")
    // bordered = false：最后一行不画底部分隔线
    LhCell(title = "无边框", value = "最后一行", bordered = false)
  }
}

@Composable
fun DocPreviewScope.DocCellRequiredAndLinkPreview() {
  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.xs)) {
    // required：标题前缀红色星号（取 color.danger）
    LhCell(title = "必填项", required = true)
    // clickable + onClick：整行可点击，按压涟漪取 color.hoverBg
    LhCell(
      title = "可点击",
      value = "点我",
      clickable = true,
      onClick = { showToast("Cell 被点击") },
    )
  }
}
