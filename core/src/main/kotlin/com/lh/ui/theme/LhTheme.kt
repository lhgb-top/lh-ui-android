package com.lh.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.lh.ui.tokens.LhDarkTokens
import com.lh.ui.tokens.LhTokens

/**
 * 主题色板：把 core 生成的 [LhTokens] / [LhDarkTokens] 平铺为一份不可变色板。
 *
 * 暗色令牌是**并列对象**模型（core codegen 的 android target 只在 LhDarkTokens 里
 * 放 Color 子对象，尺寸/动效/圆角暗色不覆盖）。本类只承载颜色，尺寸令牌由组件直接
 * 取 [LhTokens]（尺寸无暗色变体，取明色对象即可）。
 *
 * 构造函数公开（非 internal）是为了让单测能直接构造色板做断言；应用侧应通过
 * [LhTheme] / [LhTheme.colors] 消费，不应自建色板。
 */
@Immutable
data class LhColors(
  val primary: Color,
  val primaryHover: Color,
  val primaryActive: Color,
  val primaryBg: Color,
  val primaryBorder: Color,
  val onPrimary: Color,
  val success: Color,
  val successHover: Color,
  val successActive: Color,
  val successBg: Color,
  val warning: Color,
  val warningHover: Color,
  val warningActive: Color,
  val warningBg: Color,
  val danger: Color,
  val dangerHover: Color,
  val dangerActive: Color,
  val dangerBg: Color,
  val info: Color,
  val infoHover: Color,
  val infoActive: Color,
  val infoBg: Color,
  val textPrimary: Color,
  val textRegular: Color,
  val textSecondary: Color,
  val textPlaceholder: Color,
  val textDisabled: Color,
  val backgroundDefault: Color,
  val backgroundPage: Color,
  val backgroundDisabled: Color,
  val backgroundOverlay: Color,
  val borderBase: Color,
  val borderLight: Color,
  val borderLighter: Color,
  val borderExtraLight: Color,
  val disabledBg: Color,
  val disabledText: Color,
  val disabledBorder: Color,
  val decoration: Color,
  val hoverBg: Color,
  val selected: Color,
  val focus: Color,
)

/** 明色色板：逐字段取 [LhTokens.Color] */
val LightLhColors = LhColors(
  primary = LhTokens.Color.Primary.default,
  primaryHover = LhTokens.Color.Primary.hover,
  primaryActive = LhTokens.Color.Primary.active,
  primaryBg = LhTokens.Color.Primary.bg,
  primaryBorder = LhTokens.Color.Primary.border,
  onPrimary = LhTokens.Color.onPrimary,
  success = LhTokens.Color.Success.default,
  successHover = LhTokens.Color.Success.hover,
  successActive = LhTokens.Color.Success.active,
  successBg = LhTokens.Color.Success.bg,
  warning = LhTokens.Color.Warning.default,
  warningHover = LhTokens.Color.Warning.hover,
  warningActive = LhTokens.Color.Warning.active,
  warningBg = LhTokens.Color.Warning.bg,
  danger = LhTokens.Color.Danger.default,
  dangerHover = LhTokens.Color.Danger.hover,
  dangerActive = LhTokens.Color.Danger.active,
  dangerBg = LhTokens.Color.Danger.bg,
  info = LhTokens.Color.Info.default,
  infoHover = LhTokens.Color.Info.hover,
  infoActive = LhTokens.Color.Info.active,
  infoBg = LhTokens.Color.Info.bg,
  textPrimary = LhTokens.Color.Text.primary,
  textRegular = LhTokens.Color.Text.regular,
  textSecondary = LhTokens.Color.Text.secondary,
  textPlaceholder = LhTokens.Color.Text.placeholder,
  textDisabled = LhTokens.Color.Text.disabled,
  backgroundDefault = LhTokens.Color.Background.default,
  backgroundPage = LhTokens.Color.Background.page,
  backgroundDisabled = LhTokens.Color.Background.disabled,
  backgroundOverlay = LhTokens.Color.Background.overlay,
  borderBase = LhTokens.Color.Border.base,
  borderLight = LhTokens.Color.Border.light,
  borderLighter = LhTokens.Color.Border.lighter,
  borderExtraLight = LhTokens.Color.Border.extraLight,
  disabledBg = LhTokens.Color.Disabled.bg,
  disabledText = LhTokens.Color.Disabled.text,
  disabledBorder = LhTokens.Color.Disabled.border,
  decoration = LhTokens.Color.decoration,
  hoverBg = LhTokens.Color.hoverBg,
  selected = LhTokens.Color.selected,
  focus = LhTokens.Color.focus,
)

/** 暗色色板：逐字段取 [LhDarkTokens.Color]（并列对象，仅颜色覆盖） */
val DarkLhColors = LhColors(
  primary = LhDarkTokens.Color.Primary.default,
  primaryHover = LhDarkTokens.Color.Primary.hover,
  primaryActive = LhDarkTokens.Color.Primary.active,
  primaryBg = LhDarkTokens.Color.Primary.bg,
  primaryBorder = LhDarkTokens.Color.Primary.border,
  onPrimary = LhDarkTokens.Color.onPrimary,
  success = LhDarkTokens.Color.Success.default,
  successHover = LhDarkTokens.Color.Success.hover,
  successActive = LhDarkTokens.Color.Success.active,
  successBg = LhDarkTokens.Color.Success.bg,
  warning = LhDarkTokens.Color.Warning.default,
  warningHover = LhDarkTokens.Color.Warning.hover,
  warningActive = LhDarkTokens.Color.Warning.active,
  warningBg = LhDarkTokens.Color.Warning.bg,
  danger = LhDarkTokens.Color.Danger.default,
  dangerHover = LhDarkTokens.Color.Danger.hover,
  dangerActive = LhDarkTokens.Color.Danger.active,
  dangerBg = LhDarkTokens.Color.Danger.bg,
  info = LhDarkTokens.Color.Info.default,
  infoHover = LhDarkTokens.Color.Info.hover,
  infoActive = LhDarkTokens.Color.Info.active,
  infoBg = LhDarkTokens.Color.Info.bg,
  textPrimary = LhDarkTokens.Color.Text.primary,
  textRegular = LhDarkTokens.Color.Text.regular,
  textSecondary = LhDarkTokens.Color.Text.secondary,
  textPlaceholder = LhDarkTokens.Color.Text.placeholder,
  textDisabled = LhDarkTokens.Color.Text.disabled,
  backgroundDefault = LhDarkTokens.Color.Background.default,
  backgroundPage = LhDarkTokens.Color.Background.page,
  backgroundDisabled = LhDarkTokens.Color.Background.disabled,
  backgroundOverlay = LhDarkTokens.Color.Background.overlay,
  borderBase = LhDarkTokens.Color.Border.base,
  borderLight = LhDarkTokens.Color.Border.light,
  borderLighter = LhDarkTokens.Color.Border.lighter,
  borderExtraLight = LhDarkTokens.Color.Border.extraLight,
  disabledBg = LhDarkTokens.Color.Disabled.bg,
  disabledText = LhDarkTokens.Color.Disabled.text,
  disabledBorder = LhDarkTokens.Color.Disabled.border,
  decoration = LhDarkTokens.Color.decoration,
  hoverBg = LhDarkTokens.Color.hoverBg,
  selected = LhDarkTokens.Color.selected,
  focus = LhDarkTokens.Color.focus,
)

/**
 * 主题 CompositionLocal。默认值给明色，避免 [LhTheme] 未包裹时崩溃；
 * 缺省即明色与「明色优先」的设计调性一致。
 */
val LocalLhColors = compositionLocalOf { LightLhColors }

/**
 * lh-ui 主题容器。一行包裹即让内部所有 Lh 组件消费正确的明/暗色板。
 *
 * @param isDark 是否暗色模式。暗色色板取 core 预计算值（无运行时调色，见总纲 R-n6）
 * @param colors 显式覆盖色板（预设主题入口，预留扩展）
 */
@Composable
fun LhTheme(
  isDark: Boolean = false,
  colors: LhColors? = null,
  content: @Composable () -> Unit,
) {
  val resolved = colors ?: if (isDark) DarkLhColors else LightLhColors
  androidx.compose.runtime.CompositionLocalProvider(LocalLhColors provides resolved) {
    content()
  }
}

/** 当前主题色板 */
val LhThemeColors: LhColors
  @Composable get() = LocalLhColors.current
