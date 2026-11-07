package top.lhgb.ui.docsapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import top.lhgb.ui.docs.LhDocsContent
import top.lhgb.ui.locale.LhAppLocale

/**
 * 独立文档 APP 的唯一入口（LAUNCHER）。
 *
 * 极薄壳：内容组合复用 docsui 的 [LhDocsContent]（edge-to-edge、跟随系统暗色、
 * 状态栏图标外观均托管其中），保证独立 APP 与嵌入宿主看到的文档 UI 完全一致。
 * 语言切换由 docsui 顶栏的地球按钮驱动（`LhAppLocale` 持久化 + 本 Activity
 * `attachBaseContext` 覆盖 + relaunch 即时生效）。
 */
class LhDocsAppActivity : ComponentActivity() {

  override fun attachBaseContext(newBase: Context) {
    // recreate() 不刷新 base context，切换语言走 finish + startActivity 重建，
    // 新实例重新走这里读到持久化的 override
    super.attachBaseContext(LhAppLocale.applyConfig(newBase))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      LhDocsContent()
    }
  }
}
