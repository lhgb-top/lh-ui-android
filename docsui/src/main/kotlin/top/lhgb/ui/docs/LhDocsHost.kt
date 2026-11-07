package top.lhgb.ui.docs

import androidx.activity.compose.BackHandler
import android.app.Activity
import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import top.lhgb.ui.dialog.LhDialog
import top.lhgb.ui.locale.LhAppLocale
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import top.lhgb.ui.docs.pages.DocHomePage
import top.lhgb.ui.docs.pages.DocPage
import top.lhgb.ui.docs.widgets.rememberLhSerifFontFamily
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconSize
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.navbar.LhNavbar
import top.lhgb.ui.tokens.LhTokens
import top.lhgb.ui.toast.LhToast
import top.lhgb.ui.toast.LhToastPosition
import top.lhgb.ui.toast.LhToastState
import top.lhgb.ui.theme.LhThemeColors
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
@OptIn(ExperimentalLayoutApi::class)
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
  // 页面数据按当前 Locale 解析（DocRegistry 内部读 Configuration）
  val context = LocalContext.current
  val entry = currentId?.let { DocRegistry.byId(context, it) }
  val docTitle = stringResource(top.lhgb.ui.docsui.R.string.doc_title)
  val toggleThemeDesc = stringResource(top.lhgb.ui.docsui.R.string.doc_a11y_toggle_theme)
  val langDialogTitle = stringResource(top.lhgb.ui.docsui.R.string.doc_lang_title)
  var showLangDialog by remember { mutableStateOf(false) }

  /** 切换语言：持久化后 relaunch 当前 Activity（recreate() 不刷新 base context）。 */
  val onPickLanguage: (String) -> Unit = { code ->
    LhAppLocale.set(context, code)
    showLangDialog = false
    backStack.clear()
    val activity = context as? Activity
    if (activity != null) {
      context.startActivity(Intent(context, activity::class.java))
      activity.finish()
    }
  }

  // 系统返回键：详情页出栈回首页，首页时把返回交给系统（退出文档 / 回宿主）。
  BackHandler(enabled = backStack.isNotEmpty()) {
    backStack.removeLastOrNull()
  }

  val toast = rememberLhToastState()
  var toastPosition by remember { mutableStateOf(LhToastPosition.center) }

  val showToast: (String) -> Unit = { text -> toast.show(text) }
  val setToastPosition: (LhToastPosition) -> Unit = { position -> toastPosition = position }

  Box(modifier = modifier.fillMaxSize().background(LhThemeColors.backgroundPage)) {
    androidx.compose.foundation.layout.Column(modifier = Modifier.fillMaxSize()) {
      // 顶栏：dogfood 自家 LhNavbar。
      // 状态栏 inset 在宿主层处理（spec 是跨端契约，inset 属于平台集成层职责）：
      // 外层 Box 的底色把状态栏区域一并接管，edge-to-edge 下不露系统默认色带，
      // LhNavbar 自身内容经 statusBarsPadding 不再顶到系统状态栏下面。
      Box(modifier = Modifier.fillMaxWidth().background(LhThemeColors.backgroundDefault)) {
        LhNavbar(
          leftIcon = if (entry != null) "arrow-left" else null,
          onLeftClick = { backStack.removeLastOrNull() },
          modifier = Modifier.statusBarsPadding(),
          // 标题走 spec 的 default 插槽：在保持 navbar 令牌取值（titleFontSize +
          // text.primary）的前提下应用国风衬线 + semibold 字重
          titleContent = {
            Text(
              text = entry?.title ?: docTitle,
              color = LhThemeColors.textPrimary,
              fontSize = LhTokens.Navbar.titleFontSize,
              fontFamily = rememberLhSerifFontFamily(),
              fontWeight = FontWeight(LhTokens.Typography.FontWeight.bold),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
            )
          },
          // 右侧区域整体可激活（无障碍「右侧」节点）：点空白处也能切换暗色，
          // 内层 ghost 按钮消费触摸事件，二者不会重复触发
          onRightClick = { onDarkChange(!dark) },
          rightContent = {
            val iconShape = RoundedCornerShape(LhTokens.Radius.round)
            // 右侧两个 ghost 图标按钮：语言切换 + 明暗切换
            Row(
              horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              // 语言切换（globe 图标 + LhDialog 列出 9 语言，名称用各语言自称）
              Box(
                modifier = Modifier
                  .minimumInteractiveComponentSize()
                  .clip(iconShape)
                  .clickable { showLangDialog = true }
                  .semantics {
                    role = Role.Button
                    contentDescription = langDialogTitle
                  },
                contentAlignment = Alignment.Center,
              ) {
                LhIcon(
                  name = "globe",
                  lhSize = LhIconSize.medium,
                  tone = LhIconTone.primary,
                  contentDescription = null,
                )
              }
              // Ghost 图标按钮：无填充无描边、圆形 ripple、48dp 触控区，
              // moon/sun 图标 20dp 与左侧返回箭头对称
              Box(
                modifier = Modifier
                  .minimumInteractiveComponentSize()
                  .clip(iconShape)
                  .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(color = LhThemeColors.primary),
                  ) { onDarkChange(!dark) }
                  .semantics {
                    role = Role.Button
                    contentDescription = toggleThemeDesc
                  },
                contentAlignment = Alignment.Center,
              ) {
                LhIcon(
                  name = if (dark) "sun" else "moon",
                  lhSize = LhIconSize.medium,
                  tone = LhIconTone.primary,
                  contentDescription = null,
                )
              }
            }
          },
        )
      }

      // 内容区：首页列表或文档页，带入栈/出栈方向的滑动转场。
      // AnimatedContent 以 currentId 为 key：进详情页新页从右滑入，回首页新页从左滑入。
      Box(
        modifier = Modifier
          .fillMaxSize()
          .weight(1f)
          .navigationBarsPadding(),
      ) {
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
          val pageEntry = id?.let { DocRegistry.byId(context, it) }
          if (pageEntry != null) {
            DocPage(
              entry = pageEntry,
              showToast = showToast,
              setToastPosition = setToastPosition,
            )
          } else {
            DocHomePage(entries = DocRegistry.all(context), onOpen = { newId -> backStack.add(newId) })
          }
        }
      }
    }

    // 全局 Toast：所有预览的 showToast 最终落到这里
    LhToast(state = toast, lhPosition = toastPosition)

    // 语言切换对话框：9 语言（名称用各语言自称），复用 LhDialog dogfood
    LhDialog(
      visible = showLangDialog,
      onConfirm = {},
      onCancel = {},
      onClose = { showLangDialog = false },
      closable = true,
      title = langDialogTitle,
      confirmText = "",
      cancelText = "",
      contentContent = {
        val currentCode = LhAppLocale.code(context)
        Column(
          modifier = Modifier
            .heightIn(max = LhTokens.Spacing.xxl * 10)
            .verticalScroll(rememberScrollState()),
          verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
        ) {
          SUPPORTED_LANGUAGES.forEach { (code, nativeName) ->
            val interactionSource = remember(code) { MutableInteractionSource() }
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clickable(
                  interactionSource = interactionSource,
                  indication = ripple(color = LhThemeColors.primary),
                ) { onPickLanguage(code) }
                .padding(vertical = LhTokens.Spacing.sm),
              horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
              verticalAlignment = Alignment.CenterVertically,
            ) {
              Text(
                text = nativeName,
                color = LhThemeColors.textPrimary,
                fontSize = LhTokens.Docs.Prose.fontSize,
                modifier = Modifier.weight(1f),
              )
              if (code == currentCode) {
                LhIcon(
                  name = "check",
                  lhSize = LhIconSize.medium,
                  tone = LhIconTone.primary,
                  contentDescription = null,
                )
              }
            }
          }
        }
      },
    )
  }
}

/** 支持的语言（代码与 [LhAppLocale.SUPPORTED] 一致）→ 各语言的自称（无需翻译）。 */
private val SUPPORTED_LANGUAGES: List<Pair<String, String>> = listOf(
  "zh" to "简体中文",
  "en" to "English",
  "zh-TW" to "繁體中文",
  "ja" to "日本語",
  "ko" to "한국어",
  "es" to "Español",
  "fr" to "Français",
  "ru" to "Русский",
  "ar" to "العربية",
)

/** 令牌时长（毫秒，Motion.Duration.fast），包一层避免在动画 lambda 里写整型字面量。 */
private val motionDurationFast: Int get() = top.lhgb.ui.tokens.LhTokens.Motion.Duration.fast
