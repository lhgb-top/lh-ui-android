package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconSize
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.tokens.LhTokens

private val registeredIcons = listOf(
  "search", "refresh", "check", "check-circle", "close", "clear", "star",
  "warning", "info", "plus", "minus", "arrow-left", "arrow-right", "chevron-right",
)

@Composable
private fun IconCell(name: String, tone: LhIconTone, size: LhIconSize, onClick: (() -> Unit)? = null) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    LhIcon(name = name, size = size, tone = tone, onClick = onClick)
    Text(
      text = name,
      color = top.lhgb.ui.theme.LhThemeColors.textSecondary,
      fontSize = LhTokens.Typography.FontSize.extraSmall,
    )
  }
}

@Composable
fun DocPreviewScope.DocIconSizeAndTonePreview() {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xl),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    LhIconSize.entries.forEach { size ->
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LhIcon(name = "search", size = size, tone = LhIconTone.primary)
        Text(
          text = size.name,
          color = top.lhgb.ui.theme.LhThemeColors.textSecondary,
          fontSize = LhTokens.Typography.FontSize.extraSmall,
        )
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocIconTonesPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
  ) {
    LhIconTone.entries.forEach { tone ->
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LhIcon(name = "check-circle", size = LhIconSize.large, tone = tone)
        Text(
          text = tone.name,
          color = top.lhgb.ui.theme.LhThemeColors.textSecondary,
          fontSize = LhTokens.Typography.FontSize.extraSmall,
        )
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocIconRegistryPreview() {
  Column(
    modifier = Modifier.fillMaxWidth().padding(top = LhTokens.Spacing.xs),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
  ) {
    FlowRow(
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
    ) {
      registeredIcons.forEach { name ->
        IconCell(name = name, tone = LhIconTone.default, size = LhIconSize.medium) {
          showToast("图标：$name")
        }
      }
    }
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    ) {
      Text(
        text = "未登记名安全跳过：",
        color = top.lhgb.ui.theme.LhThemeColors.textSecondary,
        fontSize = LhTokens.Typography.FontSize.small,
      )
      // not-a-real-icon 未登记，LhIcon 返回 null 不绘制、不触发回调
      LhIcon(
        name = "not-a-real-icon",
        modifier = Modifier.size(LhTokens.Icon.Size.medium),
        onClick = { showToast("不会触发") },
      )
    }
  }
}
