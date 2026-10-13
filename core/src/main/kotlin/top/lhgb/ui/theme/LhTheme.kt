package top.lhgb.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

/**
 * 明/暗色板的注入点。默认值取明色板（未在 [LhTheme] 包裹下使用组件时兜底，不崩）。
 */
val LocalLhColors: ProvidableCompositionLocal<LhColors> = compositionLocalOf { LightLhColors }

/**
 * 当前主题色板。组件里统一读这个短名（等价于 `LocalLhColors.current`），
 * 明暗切换时组件代码完全不用改——色板由 [LhTheme] 注入。
 */
val LhThemeColors: LhColors
  @Composable get() = LocalLhColors.current

/**
 * 主题包裹器：把明/暗令牌注入 CompositionLocal，子树组件用 [LhThemeColors] 读取。
 *
 * @param dark 是否暗色模式；显式传入 [colors] 时该参数被忽略
 * @param colors 自定义色板（一般不需要，测试里构造固定色板时用）
 * @param content 子组件树
 */
@Composable
fun LhTheme(
  dark: Boolean = false,
  colors: LhColors = if (dark) DarkLhColors else LightLhColors,
  content: @Composable () -> Unit,
) {
  CompositionLocalProvider(LocalLhColors provides colors) {
    content()
  }
}
