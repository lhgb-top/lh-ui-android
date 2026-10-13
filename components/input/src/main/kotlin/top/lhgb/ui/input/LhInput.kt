package top.lhgb.ui.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconSize
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 输入框组件（受控）。
 *
 * props 与 `lh-ui-core/specs/input.json` 逐字对齐；行为遵循 `cases/input.json`
 * 的 8 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * @param value 当前文本（受控）
 * @param onValueChange 文本变更回调；disabled/readonly 时不触发
 * @param modifier 修饰符
 * @param placeholder 占位文字（取 color.text.placeholder 令牌）
 * @param type 输入类型；password 以圆点掩码显示
 * @param size 输入框尺寸，映射到 input.height / paddingHorizontal / fontSize 令牌
 * @param disabled 是否禁用；禁用取 disabled.* 令牌且不响应输入与清除
 * @param readonly 是否只读；可聚焦但不接受输入
 * @param clearable 是否显示清除按钮（value 非空时出现，点击触发 onValueChange("")）
 * @param maxlength 最大字符数；超出部分被截断
 * @param prefix 前置内容（spec 的 prefix 插槽）
 * @param suffix 后置内容（spec 的 suffix 插槽）
 * @param onFocus 聚焦回调
 * @param onBlur 失焦回调
 */
@Composable
fun LhInput(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  placeholder: String? = null,
  type: LhInputType = LhInputType.text,
  size: LhInputSize = LhInputSize.medium,
  disabled: Boolean = false,
  readonly: Boolean = false,
  clearable: Boolean = false,
  maxlength: Int? = null,
  prefix: @Composable (() -> Unit)? = null,
  suffix: @Composable (() -> Unit)? = null,
  onFocus: (() -> Unit)? = null,
  onBlur: (() -> Unit)? = null,
) {
  val colors: LhColors = LhThemeColors
  val interactionSource = remember { MutableInteractionSource() }
  val focused by interactionSource.collectIsFocusedAsState()
  val appearance = remember(size, disabled, focused, colors) {
    LhInputDefaults.resolve(size, disabled, focused, colors)
  }
  val shape = remember(appearance.borderRadius) { RoundedCornerShape(appearance.borderRadius) }

  // maxlength 截断（spec：输入超出长度的字符被截断）
  val emit: (String) -> Unit = remember(onValueChange, maxlength) {
    { input ->
      val truncated = if (maxlength != null && input.length > maxlength) {
        input.take(maxlength)
      } else {
        input
      }
      if (truncated != value) onValueChange(truncated)
    }
  }

  Row(
    modifier = modifier
      .fillMaxWidth()
      .height(appearance.height)
      .clip(shape)
      .drawBehind {
        drawRect(color = appearance.containerColor)
        drawRect(
          color = appearance.borderColor,
          style = Stroke(width = appearance.borderWidth.toPx()),
        )
      }
      .padding(horizontal = appearance.paddingHorizontal),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(top.lhgb.ui.tokens.LhTokens.Spacing.sm),
  ) {
    if (prefix != null) prefix()

    BasicTextField(
      value = value,
      onValueChange = { input ->
        // readonly 可聚焦但不接受输入；disabled 由 enabled=false 拦截
        if (!disabled && !readonly) emit(input)
      },
      modifier = Modifier.weight(1f),
      enabled = !disabled,
      readOnly = readonly,
      singleLine = true,
      textStyle = TextStyle(
        color = appearance.textColor,
        fontSize = appearance.fontSize,
      ),
      cursorBrush = SolidColor(appearance.borderColor),
      visualTransformation = if (type == LhInputType.password) {
        PasswordVisualTransformation()
      } else {
        VisualTransformation.None
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = if (type == LhInputType.password) {
          androidx.compose.ui.text.input.KeyboardType.Password
        } else {
          androidx.compose.ui.text.input.KeyboardType.Text
        },
      ),
      interactionSource = interactionSource,
      decorationBox = { innerTextField ->
        if (value.isEmpty() && placeholder != null) {
          androidx.compose.material3.Text(
            text = placeholder,
            color = appearance.placeholderColor,
            fontSize = appearance.fontSize,
          )
        }
        innerTextField()
      },
    )

    // 清除按钮：clearable 且非空且非禁用时出现
    if (clearable && value.isNotEmpty() && !disabled) {
      LhIcon(
        name = "clear",
        size = LhIconSize.small,
        tone = LhIconTone.regular,
        contentDescription = "清除",
        onClick = { onValueChange("") },
        modifier = Modifier.size(appearance.clearIconSize),
      )
    }

    if (suffix != null) suffix()
  }
  androidx.compose.runtime.LaunchedEffect(focused) {
    if (focused) onFocus?.invoke() else onBlur?.invoke()
  }
}
