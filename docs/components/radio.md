# Radio 单选框

受控单选框，选中后再次点击不会取消选中（单选语义）。

> 契约源：[`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> 黄金用例：[`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio)（6 条，已翻译为 JVM 单测 + Compose UI Test）

## 状态与单选组

组件本身不含组逻辑，单选互斥由宿主维护（用 `value` 标识组内项）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>未选中</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>已选中</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>禁用（未选）</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>禁用（选中）</label></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("选项一" to "a", "选项二" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## 尺寸

三档圆环尺寸映射到 `radio.size.*` 令牌；内圆点直径 = 尺寸 × `radio.dotScale`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `checked` | 是否选中（必填） | `Boolean` | — |
| `onChange` | 由未选中变为选中时触发，携带 `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | 是否禁用 | `Boolean` | `false` |
| `label` | 标签文字 | `String?` | `null` |
| `value` | 单选值（组内标识，组件本身不含组逻辑） | `String?` | `null` |
| `size` | 圆环尺寸 | `LhRadioSize` | `Medium` |
| `labelContent` | 标签内容（覆盖 label，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

### LhRadioSize

| 值 | 尺寸令牌 | 内圆点直径 |
| --- | --- | --- |
| `Small` | `radio.size.small`（16dp） | 16dp × `radio.dotScale`（0.5） |
| `Medium` | `radio.size.medium`（20dp） | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large`（24dp） | 24dp × `radio.dotScale` |

点击命中整个行：仅当未选中且未禁用时变为选中并触发 `onChange(true)`。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `radio.size.{small,medium,large}` | 三档圆环尺寸 |
| `radio.ringWidth` | 外环描边宽 |
| `radio.dotScale` | 内圆点相对外环直径的比例（分数） |
| `radio.gap` | 圆环与标签间距 |
| `color.primary.default` | 选中描边与圆点色 |
| `color.border.base` | 未选描边色 |
| `color.disabled.{border,text}` | 禁用描边 / 圆点 / 文字色 |
| `color.text.primary` | 标签色 |
| `typography.fontSize.base` | 标签字号 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | 未选点击触发 onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true`（Compose UI Test） |
| `tap-checked-keeps-checked` | 已选点击不触发 | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | 禁用态不触发 | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 选中消费主色令牌 | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 未选消费描边令牌 | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | 小号消费 small 尺寸令牌 | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # JVM 部分
./gradlew :components:radio:connectedAndroidTest             # 交互部分（需设备）
```
