package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.tag.LhTag
import top.lhgb.ui.tag.LhTagSize
import top.lhgb.ui.tag.LhTagType
import top.lhgb.ui.tokens.LhTokens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocTagTypesPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhTagType.entries.forEach { type -> LhTag(text = type.name, type = type) }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocTagPlainPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhTagType.entries.forEach { type -> LhTag(text = type.name, type = type, plain = true) }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocTagSizeAndMarkPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhTagSize.entries.forEach { size ->
      LhTag(text = size.name, type = LhTagType.primary, size = size)
    }
  }
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhTag(text = "胶囊", type = LhTagType.primary, round = true)
    LhTag(
      text = "可关闭",
      type = LhTagType.danger,
      closable = true,
      onClose = { showToast("标签已关闭") },
    )
  }
}
