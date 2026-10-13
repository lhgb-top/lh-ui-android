package top.lhgb.ui.docs.previews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonSize
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.tokens.LhTokens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocButtonTypesPreview() {
  var count by remember { mutableIntStateOf(0) }
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    LhButtonType.entries.forEach { type ->
      LhButton(
        type = type,
        onClick = {
          count++
          showToast("点击了 ${type.name}（$count）")
        },
      ) { Text(type.name) }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocButtonSizesPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    LhButtonSize.entries.forEach { size ->
      LhButton(
        type = LhButtonType.primary,
        size = size,
        onClick = { showToast("size = ${size.name}") },
      ) { Text(size.name) }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocButtonStatesPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    LhButton(type = LhButtonType.primary, onClick = { showToast("正常按钮") }) { Text("正常") }
    LhButton(type = LhButtonType.primary, disabled = true, onClick = {}) { Text("禁用") }
    LhButton(type = LhButtonType.primary, loading = true, onClick = {}) { Text("加载中") }
    // 禁用优先：disabled 与 loading 共存时按禁用处理（黄金用例 disabled-precedes-loading）
    LhButton(type = LhButtonType.primary, disabled = true, loading = true, onClick = {}) {
      Text("禁用优先")
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocButtonIconAndPatternPreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    LhButton(
      type = LhButtonType.primary,
      icon = "search",
      onClick = { showToast("带图标的按钮") },
    ) { Text("搜索") }
    LhButton(icon = "refresh", onClick = { showToast("默认类型 + 图标") }) { Text("刷新") }
    LhButton(
      type = LhButtonType.primary,
      bgPattern = true,
      onClick = { showToast("国风花纹底") },
    ) { Text("主要花纹底") }
    LhButton(bgPattern = true, onClick = { showToast("国风花纹底") }) { Text("默认花纹底") }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DocPreviewScope.DocButtonThemePreview() {
  FlowRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    LhButton(type = LhButtonType.primary, onClick = {}) { Text("主要按钮") }
    LhButton(onClick = {}) { Text("默认按钮") }
    LhButton(type = LhButtonType.primary, disabled = true, onClick = {}) { Text("禁用") }
    LhButton(type = LhButtonType.success, onClick = {}) { Text("成功") }
  }
}
