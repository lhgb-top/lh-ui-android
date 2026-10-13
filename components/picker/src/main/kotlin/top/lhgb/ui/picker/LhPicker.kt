package top.lhgb.ui.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlin.math.roundToInt
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 在组合中记住选择器的滚轮状态，初始可见项按 `value` 钳制后的索引对齐中央指示器。
 *
 * 暴露 [LazyListState] 让宿主（或测试）能精确驱动滚动。
 */
@Composable
fun rememberLhPickerListState(value: Int, options: List<String>): LazyListState =
  remember(value, options.size) {
    LazyListState(firstVisibleItemIndex = LhPickerDefaults.clamp(value, options.size))
  }

/**
 * 选择器组件（底部弹出的单列滚轮）。
 *
 * props 与 `lh-ui-core/specs/picker.json` 逐字对齐；行为遵循 `cases/picker.json`
 * 的 8 条黄金用例（钳制与令牌映射翻译为 JVM 单测，滚动与按钮交互翻译为
 * Compose UI Test）。
 *
 * 滚轮是一个可滚动列表，每项高 [LhPickerAppearance.itemHeight]，滚动停止后最靠近
 * 中央指示器的项即选中项。中央指示器为两条水平线，间距 = itemHeight。
 *
 * @param visible 是否显示；为 false 时不渲染任何内容
 * @param options 候选项文本数组（单列）
 * @param onValueChange 滚轮停止且选中项变化时触发，携带新索引
 * @param onConfirm 点击确认按钮时触发，携带当前选中索引
 * @param onCancel 点击取消按钮时触发
 * @param onClose 确认/取消后触发，宿主据此置 visible 为 false
 * @param modifier 修饰符（作用于面板）
 * @param value 选中项索引（0 基）；越界时钳制到候选项范围内
 * @param title 工具栏中间的标题
 * @param confirmText 确认按钮文字
 * @param cancelText 取消按钮文字
 * @param content 滚轮内容区（覆盖默认候选项渲染，spec 的 default 插槽）
 * @param toolbar 工具栏（覆盖默认确认/标题/取消，spec 的 toolbar 插槽）
 */
@Composable
fun LhPicker(
  visible: Boolean,
  options: List<String>,
  onValueChange: (Int) -> Unit,
  onConfirm: (Int) -> Unit,
  onCancel: () -> Unit,
  onClose: () -> Unit,
  modifier: Modifier = Modifier,
  value: Int = 0,
  title: String? = null,
  confirmText: String = "确认",
  cancelText: String = "取消",
  content: @Composable ((List<String>, Int) -> Unit)? = null,
  toolbar: @Composable (() -> Unit)? = null,
  listState: LazyListState = rememberLhPickerListState(value, options),
) {
  // spec：visible 为 false 时不渲染（不发射任何绘制指令）
  if (!visible) return

  val colors: LhColors = LhThemeColors
  val appearance = LhPickerDefaults.resolve(colors)
  val lastIndex = options.lastIndex.coerceAtLeast(0)
  val clamped = LhPickerDefaults.clamp(value, options.size)
  val topRoundedShape = RoundedCornerShape(
    topStart = appearance.borderRadius,
    topEnd = appearance.borderRadius,
  )

  // spec：滚轮滚动停止后，最靠近中央指示器的项成为选中项，变化时触发 onValueChange
  val density = LocalDensity.current
  LaunchedEffect(listState, options.size) {
    val itemHeightPx = with(density) { appearance.itemHeight.toPx() }
    snapshotFlow {
      // 中央指示器对应的项索引 = 首个可见项偏移 + (已滚动距离 / 项高) 四舍五入
      val scrolled = listState.firstVisibleItemIndex +
        listState.firstVisibleItemScrollOffset / itemHeightPx
      scrolled.roundToInt().coerceIn(0, lastIndex)
    }
      .distinctUntilChanged()
      .drop(1) // 初始选中不算「变化」
      .collect { onValueChange(it) }
  }

  Popup(alignment = Alignment.BottomCenter) {
    Column(modifier = Modifier.fillMaxSize()) {
      // 遮罩区（占满面板之上的空间）
      Box(
        modifier = Modifier
          .weight(1f)
          .fillMaxWidth()
          .background(color = appearance.overlayColor),
      )

      // 面板
      Column(
        modifier = modifier
          .fillMaxWidth()
          .height(appearance.height)
          .clip(topRoundedShape)
          .background(color = appearance.surfaceColor, shape = topRoundedShape),
      ) {
        // 工具栏（toolbar 插槽优先）
        if (toolbar != null) {
          toolbar()
        } else {
          PickerToolbar(
            appearance = appearance,
            title = title,
            confirmText = confirmText,
            cancelText = cancelText,
            onConfirm = { onConfirm(clamped); onClose() },
            onCancel = { onCancel(); onClose() },
          )
        }

        // 滚轮区（中央两条指示线 + 可滚动列表）
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .drawBehind {
              val center = this.size.height / 2f
              val half = appearance.itemHeight.toPx() / 2f
              val startX = appearance.paddingHorizontal.toPx()
              val endX = this.size.width - startX
              // 两条水平线夹住中央项，间距 = itemHeight
              drawLine(
                color = appearance.indicatorColor,
                start = Offset(startX, center - half),
                end = Offset(endX, center - half),
                strokeWidth = Stroke.HairlineWidth,
              )
              drawLine(
                color = appearance.indicatorColor,
                start = Offset(startX, center + half),
                end = Offset(endX, center + half),
                strokeWidth = Stroke.HairlineWidth,
              )
            },
          contentAlignment = Alignment.Center,
        ) {
          if (content != null) {
            content(options, clamped)
          } else {
            LazyColumn(
              state = listState,
              horizontalAlignment = Alignment.CenterHorizontally,
            ) {
              items(options) { option ->
                Text(
                  text = option,
                  color = appearance.itemColor,
                  fontSize = appearance.itemFontSize,
                  textAlign = TextAlign.Center,
                  modifier = Modifier
                    .fillMaxWidth()
                    .height(appearance.itemHeight)
                    .wrapContentHeight(align = Alignment.CenterVertically),
                )
              }
            }
          }
        }
      }
    }
  }
}

/** 工具栏：左取消、中标题、右确认 */
@Composable
private fun PickerToolbar(
  appearance: LhPickerAppearance,
  title: String?,
  confirmText: String,
  cancelText: String,
  onConfirm: () -> Unit,
  onCancel: () -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(appearance.toolbarHeight)
      .padding(horizontal = appearance.paddingHorizontal),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    LhButton(
      type = LhButtonType.default,
      onClick = onCancel,
      modifier = Modifier.weight(1f),
    ) { Text(cancelText) }

    if (!title.isNullOrEmpty()) {
      Text(
        text = title,
        color = appearance.titleColor,
        fontSize = appearance.itemFontSize,
        textAlign = TextAlign.Center,
        modifier = Modifier.weight(1f),
      )
    } else {
      Spacer(Modifier.weight(1f))
    }

    LhButton(
      type = LhButtonType.primary,
      onClick = onConfirm,
      modifier = Modifier.weight(1f),
    ) { Text(confirmText) }
  }
}
