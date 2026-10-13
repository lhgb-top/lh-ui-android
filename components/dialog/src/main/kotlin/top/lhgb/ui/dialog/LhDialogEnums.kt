package top.lhgb.ui.dialog

/**
 * 对话框语义类型。枚举值与 `lh-ui-core/specs/dialog.json` 的 prop `type` 逐字对齐（R-n5）。
 *
 * 决定确认按钮的类型（颜色）：default 取中性底，其余取对应语义色。
 */
enum class LhDialogType {
  default,
  primary,
  success,
  warning,
  danger,
  info,
}
