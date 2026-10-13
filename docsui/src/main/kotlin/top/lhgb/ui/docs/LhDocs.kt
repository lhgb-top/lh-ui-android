package top.lhgb.ui.docs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import top.lhgb.ui.theme.LhTheme

/**
 * 文档模块的入口 Activity。由宿主工程通过 [LhDocs.launch] 启动，或由 :docsapp 直接启动。
 *
 * manifest 由 :docsui 库自动合并到宿主（exported = false，不给宿主注入启动器图标）。
 */
class LhDocsActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      var dark by rememberSaveable { mutableStateOf(false) }
      LhTheme(dark = dark) {
        LhDocsHost(dark = dark, onDarkChange = { dark = it })
      }
    }
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
