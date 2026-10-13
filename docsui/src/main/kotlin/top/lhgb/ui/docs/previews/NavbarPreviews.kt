package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.navbar.LhNavbar
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocNavbarBasicPreview() {
  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.md)) {
    // bordered = true（默认）：底部画 border.lighter 分隔线
    LhNavbar(
      title = "带边框",
      leftIcon = "arrow-left",
      rightText = "更多",
      onLeftClick = { showToast("点击了左侧返回") },
      onRightClick = { showToast("点击了右侧更多") },
    )
  }
}

@Composable
fun DocPreviewScope.DocNavbarSlotsPreview() {
  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.md)) {
    // bordered = false：无底部分隔线；左右内容用文字 / 图标插槽自由组合
    LhNavbar(
      title = "无边框",
      bordered = false,
      leftText = "返回",
      rightIcon = "plus",
      onLeftClick = { showToast("点击了左侧返回") },
      onRightClick = { showToast("点击了右侧新增") },
    )
  }
}
