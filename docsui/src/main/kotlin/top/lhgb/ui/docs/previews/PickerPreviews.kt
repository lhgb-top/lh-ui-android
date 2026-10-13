package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.picker.LhPicker
import top.lhgb.ui.tokens.LhTokens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocPickerBasicPreview() {
  var visible by remember { androidx.compose.runtime.mutableStateOf(false) }
  var index by remember { mutableIntStateOf(0) }
  val options = remember { listOf("甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸") }

  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
      LhButton(type = LhButtonType.primary, onClick = { visible = true }) {
        Text("请选择天干（当前：${options[index]}）")
      }
    }
  }

  LhPicker(
    visible = visible,
    options = options,
    value = index,
    title = "请选择天干",
    onValueChange = { index = it },
    onConfirm = { visible = false; showToast("确认：${options[index]}") },
    onCancel = { visible = false },
    onClose = { visible = false },
  )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocPickerSelectionPreview() {
  var visible by remember { androidx.compose.runtime.mutableStateOf(false) }
  var index by remember { mutableIntStateOf(2) }
  val options = remember { listOf("北京", "上海", "广州", "深圳", "杭州", "成都") }

  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
      // value 预选：打开时滚轮停在「广州」（index = 2）
      LhButton(type = LhButtonType.primary, onClick = { visible = true }) {
        Text("预选城市（当前：${options[index]}）")
      }
    }
  }

  LhPicker(
    visible = visible,
    options = options,
    value = index,
    title = "选择城市",
    onValueChange = { index = it },
    onConfirm = { visible = false; showToast("确认：${options[index]}") },
    onCancel = { visible = false },
    onClose = { visible = false },
  )
}
