# Picker 底部滚轮选择器

单列底部滚轮选择器，滚动停止后最靠近中央指示器的项即选中项。

> 契约源：[`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> 黄金用例：[`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker)（8 条，钳制与令牌映射翻译为 JVM 单测，滚动与按钮交互翻译为 Compose UI Test）

## 用法

底部弹出：遮罩区在上、面板在下（顶部圆角）。工具栏为「取消 / 标题 / 确认」三段。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">取消</button>
        <div class="sim-picker__toolbar-title">请选择</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">确认</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">选项一</div>
        <div class="sim-picker__item sim-picker__item--selected">选项二</div>
        <div class="sim-picker__item">选项三</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("选项一", "选项二", "选项三")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "请选择",
)
```

## 选中机制

滚轮是一个可滚动列表，每项高 `picker.itemHeight`；中央指示器为两条水平线，间距 = itemHeight。
滚动停止后，按已滚动距离四舍五入得到选中索引，变化时触发 `onValueChange`。
`value` 越界时由 `LhPickerDefaults.clamp` 钳制到候选范围内。

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `visible` | 是否显示；为 false 时不渲染任何内容 | `Boolean` | — |
| `options` | 候选项文本数组（单列） | `List<String>` | — |
| `onValueChange` | 滚轮停止且选中项变化时触发，携带新索引 | `(Int) -> Unit` | — |
| `onConfirm` | 点击确认按钮时触发，携带当前选中索引 | `(Int) -> Unit` | — |
| `onCancel` | 点击取消按钮时触发 | `() -> Unit` | — |
| `onClose` | 确认/取消后触发，宿主据此置 visible 为 false | `() -> Unit` | — |
| `value` | 选中项索引（0 基）；越界时钳制 | `Int` | `0` |
| `title` | 工具栏中间的标题 | `String?` | `null` |
| `confirmText` | 确认按钮文字 | `String` | `"确认"` |
| `cancelText` | 取消按钮文字 | `String` | `"取消"` |
| `content` | 滚轮内容区（覆盖默认候选渲染，spec 的 default 插槽） | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | 工具栏（覆盖默认确认/标题/取消，spec 的 toolbar 插槽） | `@Composable (() -> Unit)?` | `null` |
| `listState` | 滚轮列表状态（默认由 `rememberLhPickerListState(value, options)` 提供；测试时可外部注入） | `LazyListState` | 见左 |
| `modifier` | 修饰符（作用于面板） | `Modifier` | `Modifier` |

测试钩子：`rememberLhPickerListState(value, options)` 返回按 `value` 初始定位的 `LazyListState`，
仪器测试可用 `listState.dispatchRawDelta(...)` 确定性地驱动滚动。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `picker.height` | 面板高度（260dp） |
| `picker.itemHeight` | 每项高度（40dp，也是中央指示器间距） |
| `picker.toolbarHeight` | 工具栏高度（44dp） |
| `picker.itemFontSize` | 候选项字号 |
| `picker.borderRadius` | 面板顶部圆角 |
| `picker.paddingHorizontal` | 面板横向内边距（跟随 `spacing.md`） |
| `zIndex.modal` / `zIndex.modalBackdrop` | 层级语义对齐（Compose `Popup` 自带窗口级层级） |
| `color.background.overlay` | 遮罩色 |
| `color.background.default` | 面板底色 |
| `color.text.primary` / `color.text.regular` | 标题 / 候选项色 |
| `color.border.lighter` | 中央指示器线条色 |
| `color.primary.default` | 确认按钮色 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | 确认携带当前索引 + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose`（Compose UI Test） |
| `confirm-also-emits-onclose` | 确认后触发 onClose | 同上 |
| `cancel-emits-oncancel` | 取消触发 onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | 滚动改变选中并触发 onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | value 为负数时钳制到 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | value 越界时钳制到末项 | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | 标题渲染文字 | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | 不可见时不渲染 | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # JVM 部分
./gradlew :components:picker:connectedAndroidTest               # 交互部分（需设备）
```
