package top.lhgb.ui.input

/**
 * 输入框尺寸。枚举值与 `lh-ui-core/specs/input.json` 的 prop `size` 逐字对齐（R-n5）。
 *
 * 映射到 input.height / paddingHorizontal / fontSize 令牌。
 */
enum class LhInputSize {
  small,
  medium,
  large,
}

/**
 * 输入框类型。枚举值与 `lh-ui-core/specs/input.json` 的 prop `type` 逐字对齐。
 *
 * password 以圆点掩码显示已有文本。
 */
enum class LhInputType {
  text,
  password,
}
