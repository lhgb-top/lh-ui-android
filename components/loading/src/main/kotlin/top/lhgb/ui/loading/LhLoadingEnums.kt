package top.lhgb.ui.loading

/**
 * 加载指示器尺寸。枚举值与 `lh-ui-core/specs/loading.json` 的 prop `size` 逐字对齐（R-n5）。
 *
 * 映射到 loading.size.* 直径令牌。
 */
enum class LhLoadingSize {
  small,
  medium,
  large,
}

/**
 * 加载指示器环色。枚举值与 `lh-ui-core/specs/loading.json` 的 prop `tone` 逐字对齐。
 *
 * 取对应语义色令牌（color.<tone>.default）。
 */
enum class LhLoadingTone {
  primary,
  success,
  warning,
  danger,
  info,
}
