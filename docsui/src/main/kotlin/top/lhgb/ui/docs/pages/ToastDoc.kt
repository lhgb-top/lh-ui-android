package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast 轻提示",
  lede = "自持可见性与计时的墨色胶囊浮层，用于轻量级操作反馈。不阻塞用户操作。",
  group = DocGroup.OVERLAY,
  contract = "契约源：`lh-ui-core/specs/toast.json` · 黄金用例：`cases/toast.json`（6 条，状态机 4 条 + 渲染 2 条，全部翻译为本地测试）",
  sections = listOf(
    DocSection(
      title = "用法",
      prose = "toast 是自持状态机：状态由 `LhToastState` 承载，宿主调用 `show(text)` 显示、`hide()` 隐藏，\n计时到期自动隐藏并触发 `onClose`。组件在可见时按帧自驱动 `tick`，宿主也可自行调用接管计时。",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* 可选：记录关闭 */ })",
        "Column {",
        "  LhButton(onClick = { state.show(\"已保存\") }) { Text(\"触发\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "语义类型",
      prose = "非 `default` 类型时在文字前绘制直径等于字号的语义色圆点。",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "定位",
      prose = "`position` 取 `top` / `center` / `bottom`，两端各留 `spacing.xl` 边距；宽度由文字测量得出并封顶可用宽度的 80%。",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "轻提示状态机（承载可见性、文字与计时）", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "语义类型；非 default 时绘制语义色圆点", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`position`", "在可用区域内的定位", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "修饰符（作用于胶囊）", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("成员", "说明"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "显示并重置计时")),
        DocRow(cells = listOf("`hide()`", "立即隐藏，不触发 onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "推进计时；到期时隐藏、触发 onClose 并返回 true")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "当前状态（可观察）")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("值", "圆点色"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "无圆点")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "对应语义色")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "胶囊最大宽度（240dp；实际取可用宽度 80% 与之较小）")),
    DocRow(cells = listOf("`toast.borderRadius`", "胶囊圆角（跟随 `radius.base`）")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "胶囊内边距")),
    DocRow(cells = listOf("`toast.fontSize`", "文字字号（圆点直径同此值）")),
    DocRow(cells = listOf("`toast.gap`", "圆点与文字间距")),
    DocRow(cells = listOf("`zIndex.toast`", "层级语义对齐（Compose `Popup` 自带窗口级层级）")),
    DocRow(cells = listOf("`color.background.overlay`（主题层 ink 映射）", "胶囊底色")),
    DocRow(cells = listOf("`color.onPrimary`", "文字色")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "语义圆点色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "show 后可见且渲染文字", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "到期自动隐藏并触发 onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "计时未到保持可见且不触发 onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide 不触发 onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "success 类型消费成功色", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "不可见时不渲染", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # JVM 部分",
    "./gradlew :components:toast:connectedAndroidTest                 # 交互部分（需设备）",
  ),
)
