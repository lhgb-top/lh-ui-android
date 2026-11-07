package top.lhgb.ui.text

/**
 * 字号级别。枚举值与 `lh-ui-core/specs/text.json` 的 prop `level` 逐字对齐。
 *
 * 映射到 typography.fontSize.<level> 令牌。
 */
enum class LhTextLevel {
  extraLarge,
  large,
  medium,
  base,
  small,
  extraSmall,
}

/**
 * 字重。枚举值与 `lh-ui-core/specs/text.json` 的 prop `weight` 逐字对齐。
 *
 * 映射到 typography.fontWeight.<weight> 令牌。
 */
enum class LhTextWeight {
  primary,
  bold,
}

/**
 * 文字色调。枚举值与 `lh-ui-core/specs/text.json` 的 prop `tone` 逐字对齐。
 *
 * 映射到 color.text.<tone> 令牌；selected 取 color.selected。
 */
enum class LhTextTone {
  primary,
  regular,
  secondary,
  placeholder,
  disabled,
  selected,
}

/**
 * 水平对齐方式。枚举值与 `lh-ui-core/specs/text.json` 的 prop `lhAlign` 逐字对齐。
 */
enum class LhTextAlign {
  start,
  center,
  end,
}
