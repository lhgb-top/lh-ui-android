package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.textarea.LhTextarea
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocTextareaBasicPreview() {
  var text by remember { mutableStateOf("") }

  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    LhTextarea(
      value = text,
      onValueChange = { text = it },
      placeholder = "多行输入，rows = 3",
    )
    LhTextarea(value = "禁用态内容", onValueChange = {}, disabled = true)
  }
}

@Composable
fun DocPreviewScope.DocTextareaCounterPreview() {
  var counted by remember { mutableStateOf("已经有一些内容了") }

  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    // showCount：右下角字数统计；maxlength：超长截断
    LhTextarea(
      value = counted,
      onValueChange = { counted = it },
      showCount = true,
      maxlength = 20,
    )
  }
}
