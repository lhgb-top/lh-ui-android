package top.lhgb.ui.docs

import android.app.Application
import top.lhgb.ui.locale.LhAppLocale

/**
 * 文档模块推荐的 Application 基类：在 `attachBaseContext` 里应用 [LhAppLocale]
 * 持久化的语言 override。
 *
 * 宿主在 manifest 声明 `android:name="top.lhgb.ui.docs.LhDocsApplication"`（或继承它
 * 装载自家逻辑）后，文档 Activity 的非 Activity 资源（如首页之外的字符串）也跟随
 * 用户选择的语言。文档 Activity 自身也会按需 `attachBaseContext` 覆盖。
 *
 * 不声明本类也能使用文档模块，此时语言仅跟随系统。
 */
open class LhDocsApplication : Application() {

  override fun attachBaseContext(base: android.content.Context) {
    super.attachBaseContext(LhAppLocale.applyConfig(base))
  }
}
