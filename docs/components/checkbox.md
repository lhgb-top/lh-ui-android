# Checkbox 复选框

受控复选框，支持半选（indeterminate）与禁用。

> 契约源：[`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> 黄金用例：[`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox)（6 条，已翻译为 JVM 单测 + Compose UI Test）

## 状态

选中 / 半选为实心主色底 + onPrimary 指示符（勾形 / 横杠），未选为只描边。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>未选中</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>已选中</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>半选</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>禁用（选中）</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>禁用（未选）</label></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "同意协议",
)
```

## 尺寸

三档方框尺寸映射到 `checkbox.size.*` 令牌。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `checked` | 是否选中（必填） | `Boolean` | — |
| `onCheckedChange` | 选中态变更回调，携带新选中态 | `(Boolean) -> Unit` | — |
| `indeterminate` | 是否半选（不确定态） | `Boolean` | `false` |
| `disabled` | 是否禁用 | `Boolean` | `false` |
| `label` | 标签文字 | `String?` | `null` |
| `size` | 方框尺寸 | `LhCheckboxSize` | `Medium` |
| `labelContent` | 标签内容（覆盖 label，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

### LhCheckboxSize

| 值 | 尺寸令牌 |
| --- | --- |
| `Small` | `checkbox.size.small`（16dp） |
| `Medium` | `checkbox.size.medium`（20dp） |
| `Large` | `checkbox.size.large`（24dp） |

点击命中整个行（方框 + 标签）：半选态点击变为选中，否则翻转选中态。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `checkbox.size.{small,medium,large}` | 三档方框尺寸 |
| `checkbox.borderRadius` / `checkbox.borderWidth` | 方框圆角与描边宽 |
| `checkbox.checkWidth` | 勾形 / 横杠描边宽 |
| `checkbox.gap` | 方框与标签间距 |
| `color.primary.default` / `color.onPrimary` | 选中底色 / 指示符色 |
| `color.border.base` | 未选描边色 |
| `color.disabled.{bg,border,text}` | 禁用态三色 |
| `color.text.primary` | 标签色 |
| `typography.fontSize.base` | 标签字号 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | 点击翻转并发射 onChange | `LhCheckboxTest.tap_unchecked_emits_true`（Compose UI Test） |
| `tap-checked-unchecks` | 选中态点击取消 | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | 半选点击变选中 | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | 禁用态不触发 | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 选中消费主色令牌 | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 未选消费描边令牌 | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # JVM 部分
./gradlew :components:checkbox:connectedAndroidTest           # 交互部分（需设备）
```
