package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.dialog.LhDialog
import top.lhgb.ui.dialog.LhDialogType
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.tokens.LhTokens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocDialogTypesPreview() {
  var default by remember { mutableStateOf(false) }
  var primary by remember { mutableStateOf(false) }

  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
      LhButton(type = LhButtonType.primary, onClick = { default = true }) { Text("默认弹窗") }
      LhButton(type = LhButtonType.primary, onClick = { primary = true }) { Text("主要弹窗") }
    }
  }

  LhDialog(
    visible = default,
    title = "默认弹窗",
    content = "这是一个默认类型的对话框，确认会触发 onConfirm。",
    onConfirm = { default = false; showToast("已确认") },
    onCancel = { default = false },
    onClose = { default = false },
  )
  LhDialog(
    visible = primary,
    type = LhDialogType.primary,
    title = "主要弹窗",
    content = "primary 类型的确认按钮取主色令牌。",
    onConfirm = { primary = false; showToast("已确认") },
    onCancel = { primary = false },
    onClose = { primary = false },
  )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocDialogSemanticAndMaskPreview() {
  var success by remember { mutableStateOf(false) }
  var warning by remember { mutableStateOf(false) }
  var danger by remember { mutableStateOf(false) }

  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
      LhButton(type = LhButtonType.success, onClick = { success = true }) { Text("成功") }
      LhButton(type = LhButtonType.warning, onClick = { warning = true }) { Text("警告") }
      LhButton(type = LhButtonType.danger, onClick = { danger = true }) { Text("危险") }
    }
  }

  LhDialog(
    visible = success,
    type = LhDialogType.success,
    title = "成功",
    content = "success 类型：确认按钮与标题色取 success 令牌，遮罩压暗其余内容。",
    onConfirm = { success = false; showToast("操作成功") },
    onCancel = { success = false },
    onClose = { success = false },
  )
  LhDialog(
    visible = warning,
    type = LhDialogType.warning,
    title = "警告",
    content = "warning 类型用于风险较高但可撤销的操作。",
    onConfirm = { warning = false; showToast("已继续") },
    onCancel = { warning = false },
    onClose = { warning = false },
  )
  LhDialog(
    visible = danger,
    type = LhDialogType.danger,
    title = "危险操作",
    content = "此操作不可撤销，确认继续？",
    confirmText = "删除",
    onConfirm = { danger = false; showToast("已删除") },
    onCancel = { danger = false },
    onClose = { danger = false },
  )
}
