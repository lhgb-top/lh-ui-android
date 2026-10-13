package top.lhgb.ui.docs

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.docs.pages.DocHomePage
import top.lhgb.ui.docs.pages.DocPage
import top.lhgb.ui.navbar.LhNavbar
import top.lhgb.ui.toast.LhToast
import top.lhgb.ui.toast.LhToastPosition
import top.lhgb.ui.toast.LhToastState
import top.lhgb.ui.toast.rememberLhToastState

/**
 * 文档模块的导航宿主：自管返回栈 + 顶栏（LhNavbar dogfood）+ 暗色切换 + 全局 Toast。
 *
 * 零额外依赖：返回栈手写（[rememberSaveable] 的 id 栈），不引入 Navigation Compose，
 * 保持「拖入即用」。宿主工程只需在 Activity 里 setContent { LhTheme { LhDocsHost(...) } }。
 *
 * 系统返回键与顶栏返回箭头走同一个出栈逻辑：详情页按返回回首页，首页按返回才退出。
 *
 * @param dark 当前是否暗色（由宿主持有，切换会重建 LhTheme）
 * @param onDarkChange 暗色切换回调
 */
@Composable
fun LhDocsHost(
  dark: Boolean,
  onDarkChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
) {
  // 返回栈：页 id 栈，空栈 = 首页。
  // SnapshotStateList 不能被默认 rememberSaveable 保存，用 listSaver 显式序列化为 List<String>。
  val backStack = rememberSaveable(
    saver = listSaver<MutableList<String>, String>(
      save = { it.toList() },
      restore = { it.toMutableStateList() },
    ),
  ) { mutableStateListOf<String>() }
  val currentId = backStack.lastOrNull()
  val entry = currentId?.let { DocRegistry.byId(it) }

  // 系统返回键：详情页出栈回首页，首页时把返回交给系统（退出文档 / 回宿主）。
  BackHandler(enabled = backStack.isNotEmpty()) {
    backStack.removeLastOrNull()
  }

  val toast = rememberLhToastState()
  var toastPosition by remember { mutableStateOf(LhToastPosition.center) }

  val showToast: (String) -> Unit = { text -> toast.show(text) }
  val setToastPosition: (LhToastPosition) -> Unit = { position -> toastPosition = position }

  Box(modifier = modifier.fillMaxSize()) {
    androidx.compose.foundation.layout.Column(modifier = Modifier.fillMaxSize()) {
      // 顶栏：dogfood 自家 LhNavbar
      LhNavbar(
        title = entry?.title ?: "lh-ui 文档",
        leftIcon = if (entry != null) "arrow-left" else null,
        onLeftClick = { backStack.removeLastOrNull() },
        rightContent = {
          LhButton(
            size = top.lhgb.ui.button.LhButtonSize.small,
            onClick = { onDarkChange(!dark) },
          ) { androidx.compose.material3.Text(if (dark) "亮色" else "暗色") }
        },
      )

      // 内容区：首页列表或文档页，带入栈/出栈方向的滑动转场。
      // AnimatedContent 以 currentId 为 key：进详情页新页从右滑入，回首页新页从左滑入。
      Box(modifier = Modifier.fillMaxSize().weight(1f)) {
        AnimatedContent(
          targetState = currentId,
          transitionSpec = {
            if (targetState == null) {
              // 回首页：首页从左侧滑入，详情页向右滑出
              slideInHorizontally(animationSpec = tween(motionDurationFast)) { fullWidth -> -fullWidth } +
                fadeIn(animationSpec = tween(motionDurationFast)) togetherWith
                slideOutHorizontally(animationSpec = tween(motionDurationFast)) { fullWidth -> fullWidth } +
                fadeOut(animationSpec = tween(motionDurationFast))
            } else {
              // 进详情页：详情页从右侧滑入，首页向左滑出
              slideInHorizontally(animationSpec = tween(motionDurationFast)) { fullWidth -> fullWidth } +
                fadeIn(animationSpec = tween(motionDurationFast)) togetherWith
                slideOutHorizontally(animationSpec = tween(motionDurationFast)) { fullWidth -> -fullWidth } +
                fadeOut(animationSpec = tween(motionDurationFast))
            }
          },
          modifier = Modifier.fillMaxSize(),
          contentKey = { it },
        ) { id ->
          val pageEntry = id?.let { DocRegistry.byId(it) }
          if (pageEntry != null) {
            DocPage(
              entry = pageEntry,
              showToast = showToast,
              setToastPosition = setToastPosition,
            )
          } else {
            DocHomePage(entries = DocRegistry.all, onOpen = { newId -> backStack.add(newId) })
          }
        }
      }
    }

    // 全局 Toast：所有预览的 showToast 最终落到这里
    LhToast(state = toast, position = toastPosition)
  }
}

/** 令牌时长（毫秒，Motion.Duration.fast），包一层避免在动画 lambda 里写整型字面量。 */
private val motionDurationFast: Int get() = top.lhgb.ui.tokens.LhTokens.Motion.Duration.fast
