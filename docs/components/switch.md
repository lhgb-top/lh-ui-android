# Switch 开关

胶囊轨道 + 圆形滑块的两态切换控件，支持加载态占位。

> 契约源：[`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> 黄金用例：[`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch)（6 条，已翻译为 JVM 单测 + Compose UI Test）

## 状态

开启取主色轨道，关闭取描边色轨道；滑块直径 = 高度 - 2 × `switch.gap`，位移动画取 `motion.duration.base`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>关闭</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>开启</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>禁用（关）</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>禁用（开）</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>加载中（滑块位置显示加载环，不响应点击）</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## 尺寸

三档尺寸映射到 `switch.width` / `switch.height` 令牌。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `checked` | 是否开启（必填） | `Boolean` | — |
| `onChange` | 开关态变更回调，携带新开关态 | `(Boolean) -> Unit` | — |
| `disabled` | 是否禁用 | `Boolean` | `false` |
| `loading` | 是否加载中（滑块位置显示加载环，不响应点击） | `Boolean` | `false` |
| `size` | 尺寸 | `LhSwitchSize` | `Medium` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

### LhSwitchSize

| 值 | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small`（36dp） | `switch.height.small`（20dp） |
| `Medium` | `switch.width.medium`（44dp） | `switch.height.medium`（24dp） |
| `Large` | `switch.width.large`（52dp） | `switch.height.large`（28dp） |

轨道为胶囊（圆角取高度一半）；点击命中轨道区域，翻转开关态并触发 `onChange`。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `switch.width.{small,medium,large}` | 三档轨道宽度 |
| `switch.height.{small,medium,large}` | 三档轨道高度 |
| `switch.gap` | 滑块与轨道之间的留白（滑块直径 = 高度 - 2 × gap） |
| `color.primary.default` | 开启轨道色 |
| `color.border.base` | 关闭轨道色 |
| `color.onPrimary` | 滑块色 |
| `color.disabled.{bg,text}` | 禁用轨道 / 滑块色 |
| `motion.duration.base` / `motion.easing.easeInOut` | 滑块位移动画 |
| `loading.strokeWidth` | 加载环描边宽（复用） |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | 点击翻转并发射 onChange | `LhSwitchTest.tap_unchecked_emits_true`（Compose UI Test） |
| `tap-checked-turns-off` | 开启态点击关闭 | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | 禁用态不触发 | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | 加载态不触发 | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | 开启消费主色令牌 | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | 关闭消费描边令牌 | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # JVM 部分
./gradlew :components:switch:connectedAndroidTest            # 交互部分（需设备）
```
