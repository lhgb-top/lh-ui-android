package top.lhgb.ui.switch

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 开关组件（受控）。
 *
 * props 与 `lh-ui-core/specs/switch.json` 逐字对齐；行为遵循 `cases/switch.json`
 * 的 6 条黄金用例（已翻译为 JVM 单测 + Compose UI Test）。
 *
 * 点击命中轨道区域：翻转开关态并触发 `onChange` 携带新开关态；disabled 或 loading
 * 时不响应点击。
 *
 * @param checked 是否开启
 * @param onChange 开关态变更回调，携带新开关态
 * @param modifier 修饰符
 * @param disabled 是否禁用
 * @param loading 是否加载中（滑块位置改画加载环，且不响应点击）
 * @param size 尺寸档位
 */
@Composable
fun LhSwitch(
  checked: Boolean,
  onChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
  disabled: Boolean = false,
  loading: Boolean = false,
  size: LhSwitchSize = LhSwitchSize.medium,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(size, checked, disabled, loading, colors) {
    LhSwitchDefaults.resolve(size, checked, disabled, loading, colors)
  }
  val interactionSource = remember { MutableInteractionSource() }

  // 滑块行程：关闭时贴左侧（gap），开启时贴右侧
  val thumbOffset by animateDpAsState(
    targetValue = if (checked) appearance.width - appearance.thumbSize - appearance.gap else appearance.gap,
    animationSpec = tween(
      durationMillis = LhTokens.Motion.Duration.base,
      easing = LhTokens.Motion.Easing.easeInOut,
    ),
    label = "lh-switch-thumb-offset",
  )

  Box(
    modifier = modifier
      .size(width = appearance.width, height = appearance.height)
      .clip(CircleShape)
      .background(color = appearance.trackColor, shape = CircleShape)
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = appearance.enabled,
      ) {
        onChange(!checked)
      }
      .semantics {
        role = Role.Switch
        contentDescription = if (checked) "已开启" else "已关闭"
      },
    contentAlignment = Alignment.CenterStart,
  ) {
    if (loading) {
      // spec：loading 时滑块位置改画加载环（主色），描边宽复用 loading.strokeWidth 令牌
      CircularProgressIndicator(
        color = appearance.loadingColor,
        strokeWidth = LhTokens.Loading.strokeWidth,
        modifier = Modifier
          .size(appearance.thumbSize)
          .padding(start = thumbOffset),
      )
    } else {
      // 滑块：圆形，直径 = 高度 - 2 × gap
      Box(
        modifier = Modifier
          .size(appearance.thumbSize)
          .clip(CircleShape)
          .background(color = appearance.thumbColor, shape = CircleShape)
          .padding(start = thumbOffset),
      )
    }
  }
}
