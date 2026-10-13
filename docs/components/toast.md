# Toast 轻提示

自持可见性与计时的墨色胶囊浮层，用于轻量级操作反馈。不阻塞用户操作。

> 契约源：[`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> 黄金用例：[`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast)（6 条，状态机 4 条 + 渲染 2 条，全部翻译为本地测试）

## 用法

toast 是**自持状态机**：状态由 `LhToastState` 承载，宿主调用 `show(text)` 显示、`hide()` 隐藏，
计时到期自动隐藏并触发 `onClose`。组件在可见时按帧自驱动 `tick`，宿主也可自行调用接管计时。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">操作成功</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
val state = rememberLhToastState(onClose = { /* 可选：记录关闭 */ })
Column {
  LhButton(onClick = { state.show("已保存") }) { Text("触发") }
  LhToast(state = state)
}
```

## 语义类型

非 `default` 类型时在文字前绘制直径等于字号的语义色圆点。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 类型</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>成功提示</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>警告提示</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>失败提示</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>主要提示</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## 定位

`position` 取 `top` / `center` / `bottom`，两端各留 `spacing.xl` 边距；宽度由文字测量得出并封顶可用宽度的 80%。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 定位</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">顶部</div>
    <div class="sim-toast sim-toast--bottom">底部</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `state` | 轻提示状态机（承载可见性、文字与计时） | `LhToastState` | — |
| `type` | 语义类型；非 default 时绘制语义色圆点 | `LhToastType` | `default` |
| `position` | 在可用区域内的定位 | `LhToastPosition` | `center` |
| `modifier` | 修饰符（作用于胶囊） | `Modifier` | `Modifier` |

### LhToastState

| 成员 | 说明 |
| --- | --- |
| `show(text: String)` | 显示并重置计时 |
| `hide()` | 立即隐藏，**不触发 onClose** |
| `tick(deltaMs: Int): Boolean` | 推进计时；到期时隐藏、触发 onClose 并返回 true |
| `visible` / `text` / `duration` | 当前状态（可观察） |

工厂：`rememberLhToastState(duration = 2000, onClose = {})`。

### LhToastType / LhToastPosition

| 值 | 圆点色 |
| --- | --- |
| `default` | 无圆点 |
| `primary` / `success` / `warning` / `danger` / `info` | 对应语义色 |

`LhToastPosition`：`top` / `center` / `bottom`。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `toast.maxWidth` | 胶囊最大宽度（240dp；实际取可用宽度 80% 与之较小） |
| `toast.borderRadius` | 胶囊圆角（跟随 `radius.base`） |
| `toast.paddingHorizontal` / `toast.paddingVertical` | 胶囊内边距 |
| `toast.fontSize` | 文字字号（圆点直径同此值） |
| `toast.gap` | 圆点与文字间距 |
| `zIndex.toast` | 层级语义对齐（Compose `Popup` 自带窗口级层级） |
| `color.background.overlay`（主题层 ink 映射） | 胶囊底色 |
| `color.onPrimary` | 文字色 |
| `color.{primary,success,warning,danger,info}.default` | 语义圆点色 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `show-makes-visible` | show 后可见且渲染文字 | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | 到期自动隐藏并触发 onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | 计时未到保持可见且不触发 onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide 不触发 onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | success 类型消费成功色 | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | 不可见时不渲染 | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # JVM 部分
./gradlew :components:toast:connectedAndroidTest                 # 交互部分（需设备）
```
