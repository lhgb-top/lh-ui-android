package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.input.LhInput
import top.lhgb.ui.input.LhInputSize
import top.lhgb.ui.input.LhInputType
import top.lhgb.ui.tag.LhTag
import top.lhgb.ui.tokens.LhTokens

@Composable
fun DocPreviewScope.DocInputBasicPreview() {
  var text by remember { mutableStateOf("") }
  var pwd by remember { mutableStateOf("") }
  var readonly by remember { mutableStateOf("只读内容") }

  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    LhInput(
      value = text,
      onValueChange = { text = it },
      placeholder = "受控输入（实时回显）",
      clearable = true,
      prefix = { LhIcon(name = "search") },
    )
    LhInput(
      value = pwd,
      onValueChange = { pwd = it },
      placeholder = "密码框（type = password）",
      type = LhInputType.password,
    )
    // readonly：可聚焦复制但不改值；suffix 是自定义插槽
    LhInput(
      value = readonly,
      onValueChange = { readonly = it },
      suffix = { LhTag(text = "只读") },
      readonly = true,
    )
    LhInput(value = "", onValueChange = {}, placeholder = "disabled 禁用", disabled = true)
  }
}

@Composable
fun DocPreviewScope.DocInputSizeAndFocusPreview() {
  var limited by remember { mutableStateOf("") }

  Column(verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    LhInputSize.entries.forEach { size ->
      LhInput(value = "", onValueChange = {}, placeholder = "size = ${size.name}", size = size)
    }
    // maxlength：超出长度的输入被截断（黄金用例 maxlength-truncates-input）
    LhInput(
      value = limited,
      onValueChange = { limited = it },
      placeholder = "maxlength = 5（超长截断）",
      maxlength = 5,
    )
  }
}
