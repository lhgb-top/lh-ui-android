package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.text.LhText
import top.lhgb.ui.text.LhTextLevel
import top.lhgb.ui.text.LhTextTone
import top.lhgb.ui.text.LhTextWeight
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocTextLevelsPreview() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhTextLevel.entries.forEach { level -> LhText(content = level.name, level = level) }
  }
}

@Composable
fun DocPreviewScope.DocTextTonesPreview() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhTextTone.entries.forEach { tone -> LhText(content = tone.name, tone = tone) }
  }
}

@Composable
fun DocPreviewScope.DocTextWeightPreview() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    LhText(content = "primary")
    LhText(content = "bold", weight = LhTextWeight.bold)
  }
}
