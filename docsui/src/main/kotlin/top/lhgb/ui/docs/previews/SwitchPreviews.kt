package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.`switch`.LhSwitch
import top.lhgb.ui.`switch`.LhSwitchSize
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocSwitchStatesPreview() {
  var on by remember { mutableStateOf(false) }
  var loading by remember { mutableStateOf(false) }

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
  ) {
    LhSwitch(checked = on, onChange = { on = it; showToast("switch = $it") })
    // loading：滑块转圈指示器，交互仍生效
    LhSwitch(checked = loading, onChange = { loading = it }, loading = true)
    LhSwitch(checked = false, onChange = {}, disabled = true)
  }
}

@Composable
fun DocPreviewScope.DocSwitchSizesPreview() {
  var onSmall by remember { mutableStateOf(true) }
  var onMedium by remember { mutableStateOf(true) }
  var onLarge by remember { mutableStateOf(true) }

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
  ) {
    LhSwitch(checked = onSmall, onChange = { onSmall = it }, size = LhSwitchSize.small)
    LhSwitch(checked = onMedium, onChange = { onMedium = it }, size = LhSwitchSize.medium)
    LhSwitch(checked = onLarge, onChange = { onLarge = it }, size = LhSwitchSize.large)
  }
}
