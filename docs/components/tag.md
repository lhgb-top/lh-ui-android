# Tag 标签

语义色标签，支持镂空、全圆角标记与关闭按钮。

> 契约源：[`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> 黄金用例：[`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag)（6 条，已翻译为 JVM 单测 + Compose UI Test）

## 类型

六种语义类型决定底色与文字色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag 类型</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">默认</span><span class="sim-tag sim-tag--success">成功</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">警告</span><span class="sim-tag sim-tag--danger">危险</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">信息</span></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
LhTag(type = LhTagType.success) { Text("成功") }
```

## 镂空（plain）

`plain` 为透明底 + 语义色描边与文字。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">主要</span><span class="sim-tag sim-tag--plain sim-tag--success">成功</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">危险</span></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## 尺寸与标记

三档尺寸映射到 `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` 令牌；`mark` 取高度一半的全圆角。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">标记</span><span class="sim-tag sim-tag--success sim-tag--mark">成功标记</span></div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `text` | 标签文字（便捷写法，与 `content` 二选一） | `String?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |
| `type` | 语义类型 | `LhTagType` | `Default` |
| `size` | 尺寸 | `LhTagSize` | `Medium` |
| `plain` | 是否镂空（透明底 + 语义色描边） | `Boolean` | `false` |
| `round` | 是否全圆角（高度一半） | `Boolean` | `false` |
| `closable` | 是否显示关闭按钮 | `Boolean` | `false` |
| `onClose` | 关闭回调；`closable` 为 false 时不触发 | `(() -> Unit)?` | `null` |
| `content` | 标签内容（覆盖 text，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| 值 | 底色（非 plain） | 描边/文字（plain） |
| --- | --- | --- |
| `Default` | `color.background.default` + `color.border.base` 描边 | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| 值 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small`（20dp） | `tag.paddingHorizontal.small`（8dp） | `tag.fontSize.small`（12sp） |
| `Medium` | `tag.height.medium`（24dp） | `tag.paddingHorizontal.medium`（10dp） | `tag.fontSize.medium`（14sp） |
| `Large` | `tag.height.large`（28dp） | `tag.paddingHorizontal.large`（12dp） | `tag.fontSize.large`（16sp） |

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `tag.height.{small,medium,large}` | 三档高度 |
| `tag.paddingHorizontal.{small,medium,large}` | 三档横向内边距 |
| `tag.fontSize.{small,medium,large}` | 三档字号 |
| `tag.borderRadius` / `tag.borderWidth` | 圆角与描边宽度 |
| `color.{primary,success,warning,danger,info}.default` | 语义底色 / 描边 / 文字 |
| `color.onPrimary` | 非纯文字色 |
| `color.disabled.{bg,text,border}` | 禁用态三色 |
| `spacing.xs` | 关闭按钮与文字间距 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `default-type-renders-text` | 默认类型取中性底 + 主文字色 + 基础描边 | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | 主色类型消费主色令牌 | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain 消费语义浅底令牌 | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | 小号消费 small 令牌（反向断言 large 未应用） | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round 圆角取高度一半 | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | 点击关闭触发 onClose | `LhTagTest.closable_tap_emits_onClose`（Compose UI Test） |

```bash
./gradlew :components:tag:test                             # JVM 部分
./gradlew :components:tag:connectedAndroidTest             # 交互部分（需设备）
```
