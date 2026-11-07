package top.lhgb.ui.docs

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import top.lhgb.ui.locale.LhAppLocale
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import top.lhgb.ui.theme.LhTheme

/**
 * 文档模块的入口 Activity。由宿主工程通过 [LhDocs.launch] 启动，或由 :docsapp 直接启动。
 *
 * manifest 由 :docsui 库自动合并到宿主（exported = false，不给宿主注入启动器图标）。
 */
class LhDocsActivity : ComponentActivity() {

  override fun attachBaseContext(newBase: Context) {
    // Activity 级覆盖：切换语言后新建的 Activity 走这里生效（recreate() 不刷新 base context，
    // 故切换用 finish + startActivity 重建，而不是 recreate）
    super.attachBaseContext(LhAppLocale.applyConfig(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // edge-to-edge：顶栏/内容自管 inset（LhDocsHost 内 statusBarsPadding /
    // navigationBarsPadding），状态栏区域由根背景色接管，不留系统默认色带
    enableEdgeToEdge()
    setContent {
      LhDocsContent()
    }
  }
}

/**
 * 文档模块的内容组合：LhTheme 包裹 [LhDocsHost]，自持暗色状态并同步系统状态栏图标外观。
 *
 * - 暗色默认**跟随系统**（[isSystemInDarkTheme]）；用户手动切换后由
 *   [rememberSaveable] 记住，旋转 / 重建不被系统切换再覆盖。
 * - 状态栏图标外观随主题反转：亮色主题用深色图标、暗色主题用浅色图标。
 *
 * 供 [LhDocsActivity] 与自建文档入口的宿主复用（:docsapp 的 Activity 即是）。
 */
@Composable
fun LhDocsContent() {
  // 首次打开跟随系统暗色（需在 @Composable 上下文读取），rememberSaveable 记住用户手动选择
  val systemDark = isSystemInDarkTheme()
  var dark by rememberSaveable { mutableStateOf(systemDark) }

  val view = LocalView.current
  SideEffect {
    val window = (view.context as? Activity)?.window ?: return@SideEffect
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !dark
  }

  LhTheme(dark = dark) {
    LhDocsHost(dark = dark, onDarkChange = { dark = it })
  }
}

/**
 * 文档模块的公共 API：拖入即用的唯一入口。
 *
 * 宿主工程集成步骤：
 * 1. `implementation(project(":docsui"))`（或 Maven 坐标）
 * 2. 任意位置调用 [launch]，例如某个「组件文档」按钮的点击回调：
 *    ```kotlin
 *    LhDocs.launch(context)
 *    ```
 */
object LhDocs {

  /**
   * 启动原生文档页（组件文档 + 指南 + 明暗切换 + 实时可交互预览）。
   *
   * 从非 Activity 上下文启动时自动加 NEW_TASK 标志。
   */
  fun launch(context: Context) {
    val intent = Intent(context, LhDocsActivity::class.java)
    if (context !is android.app.Activity) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
  }
}
