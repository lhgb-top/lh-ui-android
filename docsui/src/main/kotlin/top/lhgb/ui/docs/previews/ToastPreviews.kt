package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.tokens.LhTokens
import top.lhgb.ui.toast.LhToastPosition
import top.lhgb.ui.toast.LhToastType

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocToastBasicPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    LhButton(type = top.lhgb.ui.button.LhButtonType.primary, onClick = { showToast("这是一条轻提示") }) {
      Text("弹出 Toast")
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocToastTypesPreview() {
  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
      LhToastType.entries.forEach { type ->
        LhButton(onClick = { showToast("${type.name} 提示") }) { Text(type.name) }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocToastPositionPreview() {
  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
      LhButton(onClick = {
        setToastPosition(LhToastPosition.top)
        showToast("顶部提示")
      }) { Text("top") }
      LhButton(onClick = {
        setToastPosition(LhToastPosition.center)
        showToast("中间提示")
      }) { Text("center") }
      LhButton(onClick = {
        setToastPosition(LhToastPosition.bottom)
        showToast("底部提示")
      }) { Text("bottom") }
    }
  }
}
