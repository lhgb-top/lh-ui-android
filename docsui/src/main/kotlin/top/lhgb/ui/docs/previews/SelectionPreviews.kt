package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import top.lhgb.ui.checkbox.LhCheckbox
import top.lhgb.ui.checkbox.LhCheckboxSize
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.radio.LhRadio
import top.lhgb.ui.radio.LhRadioSize
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocCheckboxStatesPreview() {
  var checked by remember { mutableStateOf(false) }
  var indeterminate by remember { mutableStateOf(true) }

  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
    LhCheckbox(
      checked = checked,
      onCheckedChange = { checked = it; showToast("checkbox = $it") },
      label = "受控复选框",
    )
    // indeterminate：半选态，点击后变为选中（黄金用例 indeterminate-clicks-to-checked）
    LhCheckbox(
      checked = indeterminate,
      onCheckedChange = { indeterminate = it },
      indeterminate = true,
      label = "indeterminate 半选",
    )
    LhCheckbox(checked = false, onCheckedChange = {}, label = "disabled 禁用", disabled = true)
  }
}

@Composable
fun DocPreviewScope.DocCheckboxSizesPreview() {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
  ) {
    LhCheckboxSize.entries.forEach { size ->
      LhCheckbox(checked = true, onCheckedChange = {}, label = size.name, size = size)
    }
  }
}

@Composable
fun DocPreviewScope.DocRadioStatesAndGroupPreview() {
  var index by remember { androidx.compose.runtime.mutableIntStateOf(0) }
  val options = listOf("甲", "乙", "丙")

  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
    options.forEachIndexed { i, label ->
      LhRadio(
        checked = index == i,
        onChange = { index = i; showToast("选中 $label") },
        label = label,
        value = label,
      )
    }
    // disabled：不可交互（黄金用例 disabled-blocks-change）
    LhRadio(checked = false, onChange = {}, label = "disabled", value = "x", disabled = true)
  }
}

@Composable
fun DocPreviewScope.DocRadioSizesPreview() {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
  ) {
    LhRadioSize.entries.forEach { size ->
      LhRadio(checked = true, onChange = {}, label = size.name, value = size.name, size = size)
    }
  }
}
