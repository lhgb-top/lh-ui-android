package top.lhgb.ui.textarea

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 多行输入框组件（受控）。
 *
 * props 与 `lh-ui-core/specs/textarea.json` 逐字对齐；行为遵循 `cases/textarea.json`
 * 的 6 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * @param value 当前文本（受控）
 * @param onValueChange 文本变更回调；disabled/readonly 时不触发
 * @param modifier 修饰符
 * @param placeholder 占位文字
 * @param rows 初始可见行数（决定最小高度，内容超出自动增高）
 * @param disabled 是否禁用
 * @param readonly 是否只读
 * @param showCount 是否在右下角显示字数统计
 * @param maxlength 最大字符数；超出部分被截断
 * @param onFocus 聚焦回调
 * @param onBlur 失焦回调
 */
@Composable
fun LhTextarea(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  placeholder: String? = null,
  rows: Int = 3,
  disabled: Boolean = false,
  readonly: Boolean = false,
  showCount: Boolean = false,
  maxlength: Int? = null,
  onFocus: (() -> Unit)? = null,
  onBlur: (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val interactionSource = remember { MutableInteractionSource() }
  val focused by interactionSource.collectIsFocusedAsState()
  val appearance = remember(disabled, focused, colors) {
    LhTextareaDefaults.resolve(disabled, focused, colors)
  }
  val shape = remember(appearance.borderRadius) { RoundedCornerShape(appearance.borderRadius) }

  // rows → 初始可见高度（行数 × 行高 × 字号），不小于 minHeight 令牌
  val rowsHeight = remember(rows, appearance) {
    (rows * appearance.lineHeight * appearance.fontSize.value).dp
  }

  // maxlength 截断（spec：超过 maxlength 的输入被截断）
  val emit: (String) -> Unit = remember(onValueChange, maxlength) {
    { input ->
      val truncated = if (maxlength != null && input.length > maxlength) input.take(maxlength) else input
      if (truncated != value) onValueChange(truncated)
    }
  }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = maxOf(rowsHeight, appearance.minHeight))
      .clip(shape)
      .drawBehind {
        drawRect(color = appearance.containerColor)
        drawRect(
          color = appearance.borderColor,
          style = Stroke(width = appearance.borderWidth.toPx()),
        )
      }
      .padding(
        horizontal = appearance.paddingHorizontal,
        vertical = appearance.paddingVertical,
      ),
  ) {
    BasicTextField(
      value = value,
      onValueChange = { input ->
        if (!disabled && !readonly) emit(input)
      },
      modifier = Modifier.fillMaxWidth(),
      enabled = !disabled,
      readOnly = readonly,
      textStyle = TextStyle(
        color = appearance.textColor,
        fontSize = appearance.fontSize,
        lineHeight = appearance.fontSize * appearance.lineHeight,
      ),
      cursorBrush = SolidColor(appearance.borderColor),
      interactionSource = interactionSource,
      decorationBox = { innerTextField ->
        if (value.isEmpty() && placeholder != null) {
          androidx.compose.material3.Text(
            text = placeholder,
            color = appearance.placeholderColor,
            fontSize = appearance.fontSize,
            lineHeight = appearance.fontSize * appearance.lineHeight,
          )
        }
        innerTextField()
      },
    )

    // 字数统计：右下角
    if (showCount) {
      androidx.compose.material3.Text(
        text = LhTextareaDefaults.countText(value, maxlength),
        color = appearance.countColor,
        fontSize = appearance.countFontSize,
        modifier = Modifier.align(Alignment.BottomEnd),
      )
    }
  }

  LaunchedEffect(focused) {
    if (focused) onFocus?.invoke() else onBlur?.invoke()
  }
}
