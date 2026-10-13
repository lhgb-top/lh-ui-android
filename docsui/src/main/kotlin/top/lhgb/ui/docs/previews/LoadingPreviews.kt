package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.loading.LhLoading
import top.lhgb.ui.loading.LhLoadingSize
import top.lhgb.ui.loading.LhLoadingTone
import top.lhgb.ui.tokens.LhTokens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocLoadingSizeAndTonePreview() {
  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
  ) {
    LhLoadingTone.entries.forEach { tone ->
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
      ) {
        Text(
          text = tone.name,
          color = top.lhgb.ui.theme.LhThemeColors.textSecondary,
          fontSize = LhTokens.Typography.FontSize.small,
          modifier = Modifier.weight(1f),
        )
        LhLoadingSize.entries.forEach { size -> LhLoading(tone = tone, size = size) }
      }
    }
  }
}
