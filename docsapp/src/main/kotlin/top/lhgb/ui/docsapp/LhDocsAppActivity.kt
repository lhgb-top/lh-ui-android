package top.lhgb.ui.docsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import top.lhgb.ui.docs.LhDocsHost
import top.lhgb.ui.theme.LhTheme

/**
 * 独立文档 APP 的唯一入口（LAUNCHER）。:docsapp 是极薄的 application 模块，
 * 完整文档能力全部在 :docsui 库里——本 Activity 只负责挂 LhTheme + 导航宿主。
 */
class LhDocsAppActivity : ComponentActivity() {

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
