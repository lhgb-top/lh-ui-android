package top.lhgb.ui.icon

/**
 * 图标尺寸。枚举值与 `lh-ui-core/specs/icon.json` 的 prop `size` 逐字对齐（R-n5）。
 *
 * 映射到 icon.size.* 尺寸令牌。
 */
enum class LhIconSize {
  small,
  medium,
  large,
}

/**
 * 图标语义色调。枚举值与 `lh-ui-core/specs/icon.json` 的 prop `tone` 逐字对齐。
 *
 * - default 取主要文字色（color.text.primary）
 * - primary/success/warning/danger/info 取对应语义色
 * - regular/secondary 取对应文字色
 */
enum class LhIconTone {
  default,
  primary,
  success,
  warning,
  danger,
  info,
  regular,
  secondary,
}
