# Loading 加载

加载指示器，环形旋转动画，时长取 `motion.duration.base`（毫秒）。

> 契约源：[`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> 黄金用例：[`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading)（5 条，纯展示组件，翻译为 JVM 单测）

## 尺寸与色调

三档尺寸映射到 `loading.size.*` 令牌，描边宽取 `loading.strokeWidth`，色调取语义色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">加载中…</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
LhLoading(size = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `size` | 指示器直径 | `LhLoadingSize` | `Medium` |
| `tone` | 环色 | `LhLoadingTone` | `Primary` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

### LhLoadingSize

| 值 | 尺寸令牌 |
| --- | --- |
| `Small` | `loading.size.small`（16dp） |
| `Medium` | `loading.size.medium`（20dp） |
| `Large` | `loading.size.large`（24dp） |

### LhLoadingTone

| 值 | 颜色令牌 |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `loading.size.{small,medium,large}` | 三档直径 |
| `loading.strokeWidth` | 环描边宽度 |
| `color.text.regular` | default 色调 |
| `color.{primary,success,warning,danger,info}.default` | 语义色调 |
| `motion.duration.base` | 旋转一周时长（端侧为毫秒整型） |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `medium-size-uses-medium-token` | 中号取 medium 尺寸令牌 | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | 大号取 large 尺寸令牌 | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | primary 色调取主色令牌 | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | success 色调取成功色令牌 | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | 环形指示器渲染（描边宽消费令牌） | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # JVM 部分（纯展示，无交互用例）
```
