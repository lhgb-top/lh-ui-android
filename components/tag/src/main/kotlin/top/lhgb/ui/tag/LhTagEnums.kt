package top.lhgb.ui.tag

/**
 * 标签类型。枚举值与 `lh-ui-core/specs/tag.json` 的 prop `type` 逐字对齐（R-n5）。
 *
 * 决定底色、文字色与描边色；plain 形态下取对应语义浅底。
 */
enum class LhTagType {
  default,
  primary,
  success,
  warning,
  danger,
  info,
}

/**
 * 标签尺寸。枚举值与 `lh-ui-core/specs/tag.json` 的 prop `size` 逐字对齐。
 *
 * 映射到 tag.height / paddingHorizontal / fontSize 令牌。
 */
enum class LhTagSize {
  small,
  medium,
  large,
}
