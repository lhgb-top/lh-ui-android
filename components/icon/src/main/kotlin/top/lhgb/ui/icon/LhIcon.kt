package top.lhgb.ui.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 图标组件：按名称渲染矢量符号，支持尺寸与语义色调，可点击。
 *
 * props 与 `lh-ui-core/specs/icon.json` 逐字对齐；行为遵循 `cases/icon.json`
 * 的 5 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * @param name 图标名（本地图标体系，见 [LhIcons]）；未登记的名称不绘制，不抛异常
 * @param modifier 修饰符
 * @param size 图标尺寸，映射到 icon.size.* 令牌
 * @param tone 图标语义色调；default 为主要文字色，其余取对应语义色
 * @param onClick 点击回调；不传时图标为纯展示（无点击区域语义）
 * @param contentDescription 无障碍描述；默认取图标名，显式传 null 可声明「装饰性图标」
 */
@Composable
fun LhIcon(
  name: String,
  modifier: Modifier = Modifier,
  size: LhIconSize = LhIconSize.medium,
  tone: LhIconTone = LhIconTone.default,
  onClick: (() -> Unit)? = null,
  contentDescription: String? = name,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(size, tone, colors) {
    LhIconDefaults.resolve(size, tone, colors)
  }
  val vector: ImageVector? = remember(name) { LhIcons.resolve(name) }
  if (vector == null) return  // 未登记图标名：安全跳过，不抛异常

  val interactionSource = remember { MutableInteractionSource() }

  Icon(
    imageVector = vector,
    contentDescription = contentDescription,
    modifier = modifier
      .size(appearance.size)
      .then(
        if (onClick != null) {
          Modifier.clickable(
            interactionSource = interactionSource,
            indication = ripple(color = appearance.color),
          ) { onClick() }
        } else {
          Modifier
        },
      )
      .semantics { role = Role.Image },
    tint = appearance.color,
  )
}
