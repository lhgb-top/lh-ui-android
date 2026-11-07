@file:Suppress("ClassName", "unused")

package top.lhgb.ui.core

/**
 * lhui 聚合编译垫片。
 *
 * 本仓的聚合发布模块 :lhui 把 core + 全部组件的 Kotlin 源码合并进单一 namespace
 * （top.lhgb.ui.lhui）编译；core 的 res 在聚合后以 lhui 的命名空间生成实际的
 * `top.lhgb.ui.lhui.R`，而组件源码里全限定引用的是 `top.lhgb.ui.core.R`（独立
 * 模块编译时由 core 自身生成）。为了让聚合编译通过，这里把 core 命名空间的 R
 * 桥接到实际生成的 R——字段值是编译期常量，运行时零开销。
 *
 * **新增 core 字符串时必须同步在这里登记**（与 `core/src/main/res/values/strings.xml`
 * 一一对应）。独立模块编译（:core 等其他模块）不经过本文件，不受影响。
 */
object R {
  object string {
    const val lh_confirm: Int = top.lhgb.ui.lhui.R.string.lh_confirm
    const val lh_cancel: Int = top.lhgb.ui.lhui.R.string.lh_cancel
    const val lh_a11y_left: Int = top.lhgb.ui.lhui.R.string.lh_a11y_left
    const val lh_a11y_right: Int = top.lhgb.ui.lhui.R.string.lh_a11y_right
    const val lh_a11y_close: Int = top.lhgb.ui.lhui.R.string.lh_a11y_close
    const val lh_a11y_clear: Int = top.lhgb.ui.lhui.R.string.lh_a11y_clear
    const val lh_a11y_overlay: Int = top.lhgb.ui.lhui.R.string.lh_a11y_overlay
    const val lh_state_on: Int = top.lhgb.ui.lhui.R.string.lh_state_on
    const val lh_state_off: Int = top.lhgb.ui.lhui.R.string.lh_state_off
    const val lh_state_checked: Int = top.lhgb.ui.lhui.R.string.lh_state_checked
    const val lh_state_unchecked: Int = top.lhgb.ui.lhui.R.string.lh_state_unchecked
    const val lh_state_indeterminate: Int = top.lhgb.ui.lhui.R.string.lh_state_indeterminate
  }
}
