package top.lhgb.ui.toast

/**
 * 轻提示语义类型。枚举值与 `lh-ui-core/specs/toast.json` 的 prop `type` 逐字对齐（R-n5）。
 *
 * 非 default 时在文字前绘制对应语义色圆点。
 */
enum class LhToastType {
  default,
  success,
  warning,
  danger,
  info,
}

/**
 * 轻提示定位。枚举值与 `lh-ui-core/specs/toast.json` 的 prop `position` 逐字对齐（R-n5）。
 *
 * 上下两端留 spacing.xl 边距。
 */
enum class LhToastPosition {
  top,
  center,
  bottom,
}
