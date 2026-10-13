package top.lhgb.ui.toast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.tokens.LhTokens

/**
 * 轻提示外观解析结果。由 [LhToastDefaults.resolve] 纯函数产出，Composable 只负责画。
 * 分离解析与渲染，让黄金用例（cases/toast.json）能在无设备的 JVM 单测里断言
 * 「type → 令牌」的映射。
 */
data class LhToastAppearance(
  val fontSize: TextUnit,
  val paddingHorizontal: Dp,
  val paddingVertical: Dp,
  val borderRadius: Dp,
  /** 类型色点与文字之间的间距 */
  val gap: Dp,
  val backgroundColor: Color,
  val textColor: Color,
  /** 类型色点颜色；default 类型为 null（不绘制） */
  val dotColor: Color?,
  /** 色点直径 = 字号（spec：直径为字号的语义色圆点） */
  val dotSize: Dp,
  /** 层级（zIndex.toast，语义对齐：宿主须在其他内容之后最后绘制） */
  val zIndex: Int,
)

/**
 * 轻提示默认值与令牌绑定（纯函数，无 Compose 依赖）。
 *
 * 令牌映射严格对齐 `lh-ui-core/specs/toast.json` 的 behaviors：
 * - 胶囊底取 color.ink，文字取 color.onPrimary，圆角取 toast.borderRadius
 * - type 非 default 时在文字前绘制直径为字号的语义色圆点，间距取 toast.gap
 * - 层级取 zIndex.toast
 */
object LhToastDefaults {

  /** spec：layout 宽度由文字测量得出并封顶可用宽度的 80%（规范规定的比例，非设计令牌） */
  const val maxWidthFraction = 0.8f

  /**
   * 解析轻提示外观。
   *
   * @param type 语义类型
   * @param colors 当前主题色板（明/暗）
   */
  fun resolve(type: LhToastType, colors: LhColors): LhToastAppearance {
    val dotColor: Color? = when (type) {
      LhToastType.default -> null
      LhToastType.success -> colors.success
      LhToastType.warning -> colors.warning
      LhToastType.danger -> colors.danger
      LhToastType.info -> colors.info
    }

    return LhToastAppearance(
      fontSize = LhTokens.Toast.fontSize,
      paddingHorizontal = LhTokens.Toast.paddingHorizontal,
      paddingVertical = LhTokens.Toast.paddingVertical,
      borderRadius = LhTokens.Toast.borderRadius,
      gap = LhTokens.Toast.gap,
      backgroundColor = colors.ink,
      textColor = colors.onPrimary,
      dotColor = dotColor,
      // spec：色点直径 = 字号（sp 数值即 dp 直径）
      dotSize = LhTokens.Toast.fontSize.value.dp,
      zIndex = LhTokens.ZIndex.toast,
    )
  }
}

/**
 * 轻提示状态机（自持可见性与计时）。
 *
 * spec 的 `show(text)` / `hide()` / `tick(deltaMs)` 指令在此落地：
 * - [show] 使其可见并重置计时
 * - [hide] 立即隐藏但不触发 onClose
 * - [tick] 累加已用时长，达到 [duration] 时自动隐藏并触发 onClose
 *
 * 用 Compose 可观察状态承载，`LhToast` 组合件据此重组；状态机本身是纯 JVM 逻辑，
 * 黄金用例（cases/toast.json 的 4 条 custom 用例）可在无设备环境直接断言。
 *
 * @param duration 自动隐藏时长（毫秒）
 * @param onClose 计时到期自动隐藏时触发；[hide] 主动隐藏不触发
 */
class LhToastState(
  duration: Int = DEFAULT_DURATION,
  private val onClose: () -> Unit = {},
) {
  var visible: Boolean by mutableStateOf(false)
    private set

  var text: String by mutableStateOf("")
    private set

  /** 自动隐藏时长（毫秒） */
  var duration: Int by mutableStateOf(duration)

  private var elapsedMs by mutableIntStateOf(0)

  /** spec：show(text) 使其可见并重置计时 */
  fun show(text: String) {
    this.text = text
    visible = true
    elapsedMs = 0
  }

  /** spec：hide() 立即隐藏但不触发 onClose */
  fun hide() {
    visible = false
  }

  /**
   * spec：tick(deltaMs) 累加已用时长，达到 duration 时自动隐藏并触发 onClose。
   *
   * @return 本次 tick 是否触发了自动隐藏
   */
  fun tick(deltaMs: Int): Boolean {
    if (!visible) return false
    elapsedMs += deltaMs
    return if (elapsedMs >= duration) {
      visible = false
      onClose()
      true
    } else {
      false
    }
  }

  companion object {
    /** spec prop `duration` 的默认值（毫秒） */
    const val DEFAULT_DURATION = 2000
  }
}

/**
 * 在组合中记住一个轻提示状态。
 *
 * @param duration 自动隐藏时长（毫秒）
 * @param onClose 计时到期自动隐藏时触发
 */
@Composable
fun rememberLhToastState(
  duration: Int = LhToastState.DEFAULT_DURATION,
  onClose: () -> Unit = {},
): LhToastState = androidx.compose.runtime.remember(duration) { LhToastState(duration, onClose) }
