package top.lhgb.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 对话框组件（模态浮层）。
 *
 * props 与 `lh-ui-core/specs/dialog.json` 逐字对齐；行为遵循 `cases/dialog.json`
 * 的 8 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * 窗口级层级由 Compose [Dialog] 托管，`zIndex.modal` / `zIndex.modalBackdrop`
 * 令牌作语义对齐（端侧不手动叠层级）。确认/取消分别触发 onConfirm/onCancel，
 * 随后触发 onClose；closable 时点遮罩（或按返回键）触发 onClose，否则吞掉。
 *
 * @param visible 是否显示；为 false 时不渲染任何内容
 * @param onConfirm 点击确认按钮时触发
 * @param onCancel 点击取消按钮时触发
 * @param onClose 确认/取消后或（closable 时）点遮罩触发，宿主据此置 visible 为 false
 * @param modifier 修饰符（作用于内容卡）
 * @param title 标题
 * @param content 正文内容
 * @param type 语义类型，决定确认按钮的类型（颜色）
 * @param confirmText 确认按钮文字；为空时不显示确认按钮
 * @param cancelText 取消按钮文字；为空时不显示取消按钮
 * @param closable 点击遮罩区域是否触发 onClose
 * @param titleContent 标题内容（覆盖 title，spec 的 title 插槽）
 * @param contentContent 正文内容（覆盖 content，spec 的 default 插槽）
 * @param footer 底部按钮区（覆盖默认确认/取消按钮，spec 的 footer 插槽）
 */
@Composable
fun LhDialog(
  visible: Boolean,
  onConfirm: () -> Unit,
  onCancel: () -> Unit,
  onClose: () -> Unit,
  modifier: Modifier = Modifier,
  title: String? = null,
  content: String? = null,
  type: LhDialogType = LhDialogType.default,
  confirmText: String = "确认",
  cancelText: String = "取消",
  closable: Boolean = false,
  titleContent: @Composable (() -> Unit)? = null,
  contentContent: @Composable (() -> Unit)? = null,
  footer: @Composable (() -> Unit)? = null,
) {
  // spec：visible 为 false 时不渲染（不发射任何绘制指令）
  if (!visible) return

  val colors: LhColors = LhThemeColors
  val appearance = LhDialogDefaults.resolve(type, colors)
  val cardShape = RoundedCornerShape(appearance.borderRadius)
  val elevationDp = with(LocalDensity.current) { LhTokens.Shadow.base.blurRadius.toDp() }

  Dialog(
    onDismissRequest = { if (closable) onClose() },
    properties = DialogProperties(dismissOnBackPress = closable),
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(color = appearance.overlayColor)
        .clickable { if (closable) onClose() }
        .semantics { contentDescription = "遮罩" },
      contentAlignment = Alignment.Center,
    ) {
      Column(
        modifier = modifier
          .width(appearance.width)
          .clip(cardShape)
          .background(color = appearance.surfaceColor, shape = cardShape)
          // spec：投影 shadow.base（Compose Shadow 不支持扩展量，取模糊半径做 elevation）
          .shadow(
            elevation = elevationDp,
            shape = cardShape,
            ambientColor = LhTokens.Shadow.base.color,
            spotColor = LhTokens.Shadow.base.color,
          )
          .padding(
            horizontal = appearance.paddingHorizontal,
            vertical = appearance.paddingVertical,
          ),
        verticalArrangement = Arrangement.spacedBy(appearance.gap),
      ) {
        // 标题（title 插槽优先）
        if (titleContent != null) {
          titleContent()
        } else if (!title.isNullOrEmpty()) {
          Text(
            text = title,
            color = appearance.titleColor,
            fontSize = appearance.titleFontSize,
          )
        }

        // 正文（default 插槽优先）
        if (contentContent != null) {
          contentContent()
        } else if (!content.isNullOrEmpty()) {
          Text(
            text = content,
            color = appearance.contentColor,
            fontSize = appearance.contentFontSize,
          )
        }

        // 底部按钮区（footer 插槽优先）
        if (footer != null) {
          footer()
        } else if (confirmText.isNotEmpty() || cancelText.isNotEmpty()) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
          ) {
            if (cancelText.isNotEmpty()) {
              LhButton(
                type = LhButtonType.default,
                onClick = {
                  onCancel()
                  onClose()
                },
              ) {
                Text(cancelText)
              }
            }
            if (confirmText.isNotEmpty()) {
              if (cancelText.isNotEmpty()) Spacer(Modifier.width(LhTokens.Spacing.base))
              LhButton(
                type = appearance.confirmButtonType,
                onClick = {
                  onConfirm()
                  onClose()
                },
              ) {
                Text(confirmText)
              }
            }
          }
        }
      }
    }
  }
}
