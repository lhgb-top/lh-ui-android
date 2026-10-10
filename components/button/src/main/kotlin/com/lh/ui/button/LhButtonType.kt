package com.lh.ui.button

/**
 * 按钮类型。枚举值与 `lh-ui-core/specs/button.json` 的 prop `type` 逐字对齐（R-n5）。
 *
 * 决定底色与文字色：primary 取主色底 + onPrimary 文字；其余类型取对应语义色。
 */
enum class LhButtonType {
  default,
  primary,
  success,
  warning,
  danger,
  info,
}

/**
 * 按钮尺寸。枚举值与 `lh-ui-core/specs/button.json` 的 prop `size` 逐字对齐。
 *
 * 映射到 button.height / paddingHorizontal / fontSize / iconSize 令牌。
 */
enum class LhButtonSize {
  small,
  medium,
  large,
}
