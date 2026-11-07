package top.lhgb.ui.locale

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

/**
 * 应用级语言（Locale）解析与持久化。
 *
 * 支持集与网页文档站的 locales 一一对应：`zh`（根，简体中文）、`en`、`zh-TW`、`ja`、
 * `ko`、`es`、`fr`、`ru`、`ar`。`values/` 默认中文，与 web 站 root = zh-CN 同口径。
 *
 * 消费方式：
 * - 读当前语言：[code]（从 Context 的 Configuration 解析，应用内 override 生效时也是它）
 * - 写语言：[set] 持久化后配合 Activity / Application 的 `attachBaseContext` 走 [applyConfig]
 *
 * 设计原则：零额外依赖（不引入 appcompat），靠 Configuration 覆盖 + 新建 Activity
 * 重新走 `attachBaseContext` 生效，保持「拖入即用」承诺。
 */
object LhAppLocale {

  private const val PREF_NAME = "lh_app_locale"
  private const val KEY_LOCALE = "locale"

  /** 支持的语言代码（BCP 47 子标签写法，用于 SharedPreferences 持久化）。 */
  val SUPPORTED: List<String> = listOf("zh", "en", "zh-TW", "ja", "ko", "es", "fr", "ru", "ar")

  /** 默认语言（回退目标，与文档根目录中文一致）。 */
  const val DEFAULT: String = "zh"

  /**
   * 解析 [context] 当前的语言代码：精确匹配支持集 → 繁体分支判定 → 语言级匹配 → 回退 [DEFAULT]。
   * 例：`zh-CN` → `zh`；`zh-TW`/`zh-Hant` → `zh-TW`；`en-US` → `en`；`de-DE` → `zh`。
   */
  fun code(context: Context): String = resolve(currentTag(context))

  /** 记录用户选择（持久化）。生效需配合 [applyConfig] 与 Activity 重建（见 [restartDocs]）。 */
  fun set(context: Context, code: String) {
    context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
      .edit()
      .putString(KEY_LOCALE, code)
      .apply()
  }

  /** 已持久化的选择；未设置（跟随系统）返回 null。 */
  fun stored(context: Context): String? =
    context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_LOCALE, null)

  /**
   * 在 `attachBaseContext` 里调用：存在持久化 override 时返回包了新 Configuration 的
   * Context，否则原样返回 [base]。
   *
   * Application 与每一个 文档 Activity 都应调用（Activity 级是关键——新 Activity 创建时
   * 会重新走 `attachBaseContext`，立即生效；`recreate()` 不会刷新 base context，故切换走
   * `finish()` + `startActivity()` 而不是 `recreate()`）。
   */
  fun applyConfig(base: Context): Context {
    val stored = base.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
      .getString(KEY_LOCALE, null) ?: return base
    if (!SUPPORTED.contains(stored)) return base
    val locale = Locale.forLanguageTag(stored)
    val config = Configuration(base.resources.configuration)
    config.setLocale(locale)
    config.setLayoutDirection(locale)
    return base.createConfigurationContext(config)
  }

  /** 把系统 Locale 标签归一到支持集；不支持的语言回退 [DEFAULT]。 */
  fun resolve(tag: String?): String {
    if (tag.isNullOrBlank()) return DEFAULT
    val norm = tag.replace('_', '-').trim()
    SUPPORTED.firstOrNull { it.equals(norm, ignoreCase = true) }?.let { return it }
    val lang = norm.substringBefore('-').lowercase()
    if (lang == "zh") {
      // 繁体分支：zh-TW / zh-Hant 归 zh-TW，其余（zh-CN / zh-Hans 等）归 zh
      return if (norm.contains("tw", ignoreCase = true) || norm.contains("hant", ignoreCase = true)) "zh-TW" else "zh"
    }
    return SUPPORTED.firstOrNull { it.equals(lang, ignoreCase = true) } ?: DEFAULT
  }

  private fun currentTag(context: Context): String? {
    val locales = context.resources.configuration.locales
    return if (locales.isEmpty) null else locales[0].toLanguageTag()
  }
}
