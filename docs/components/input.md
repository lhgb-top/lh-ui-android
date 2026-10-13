# Input 输入框

受控单行输入框，支持尺寸、禁用、只读、清除、密码遮罩与最大长度。

> 契约源：[`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> 黄金用例：[`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input)（8 条，已翻译为 JVM 单测 + Compose UI Test）

## 基本用法

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="请输入内容" /></div>
    <div class="sim-row"><input class="sim-input" value="已填内容" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="禁用态" disabled /></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "请输入内容",
  onValueChange = { value = it },
)
```

## 尺寸与焦点

三档尺寸映射到 `input.height` / `input.paddingHorizontal` / `input.fontSize` 令牌；聚焦时描边取 `color.focus` 并加宽到 `input.focusRingWidth`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium（聚焦态演示）" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `value` | 受控值（必填） | `String` | — |
| `onValueChange` | 值变更回调（必填） | `(String) -> Unit` | — |
| `placeholder` | 占位文字 | `String?` | `null` |
| `type` | 输入类型（明文 / 密码） | `LhInputType` | `Text` |
| `size` | 尺寸 | `LhInputSize` | `Medium` |
| `disabled` | 是否禁用 | `Boolean` | `false` |
| `readonly` | 是否只读（不可编辑但不禁用样式） | `Boolean` | `false` |
| `clearable` | 是否显示清除按钮 | `Boolean` | `false` |
| `maxlength` | 最大长度（超长截断） | `Int` | `Int.MAX_VALUE` |
| `onFocus` / `onBlur` | 聚焦 / 失焦回调 | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | 前缀 / 后缀插槽 | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

### LhInputSize

| 值 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small`（32dp） | `input.paddingHorizontal.small`（12dp） | `input.fontSize.small`（13sp） |
| `Medium` | `input.height.medium`（40dp） | `input.paddingHorizontal.medium`（16dp） | `input.fontSize.medium`（14sp） |
| `Large` | `input.height.large`（48dp） | `input.paddingHorizontal.large`（20dp） | `input.fontSize.large`（16sp） |

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `input.height.{small,medium,large}` | 三档高度 |
| `input.paddingHorizontal.{small,medium,large}` | 三档横向内边距 |
| `input.fontSize.{small,medium,large}` | 三档字号 |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | 圆角 / 描边宽 / 焦点描边宽 |
| `input.clearIconSize` | 清除图标尺寸 |
| `color.focus` | 焦点描边色 |
| `color.text.placeholder` | 占位文字色 |
| `color.border.base` | 默认描边色 |
| `color.disabled.{bg,text,border}` | 禁用态三色 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `input-emits-onvaluechange` | 输入触发 onValueChange | `LhInputTest.input_emits_onValueChange`（Compose UI Test） |
| `clearable-emits-empty-value` | 清除按钮发射空值 | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | 禁用态不可输入 | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | 只读态不可输入 | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | 占位色取占位令牌 | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | 聚焦消费焦点令牌（失焦取中性描边） | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | 小号消费 small 尺寸令牌 | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | 超长输入截断 | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # JVM 部分
./gradlew :components:input:connectedAndroidTest              # 交互部分（需设备）
```
