# Icon 图标

图标基座组件。其他组件（cell / input / navbar 等）的图标都经由本组件的本地注册表渲染。

> 契约源：[`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> 黄金用例：[`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon)（5 条，已翻译为 JVM 单测 + Compose UI Test）

## 尺寸与色调

三档尺寸映射到 `icon.size.*` 令牌；色调 `default` 取 `color.text.primary`，其余取语义色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">默认色（text.primary）</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
LhIcon(name = "search", size = LhIconSize.large, tone = LhIconTone.primary)
```

## 语义色调

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 色调</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## 图标注册表

`LhIcons` 是本地图标名 → Material `ImageVector` 的注册表，未登记的图标名返回 `null`（不绘制，不抛异常）：

| 图标名 | Material 矢量 |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `name` | 图标名（`LhIcons` 注册表解析） | `String` | — |
| `size` | 图标尺寸 | `LhIconSize` | `Medium` |
| `tone` | 语义色调 | `LhIconTone` | `Default` |
| `contentDescription` | 无障碍描述；默认取图标名，显式传 `null` 声明为装饰性图标 | `String?` | `name` |
| `onClick` | 点击回调；为 null 时不可点击 | `(() -> Unit)?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

### LhIconSize

| 值 | 尺寸令牌 |
| --- | --- |
| `Small` | `icon.size.small`（16dp） |
| `Medium` | `icon.size.medium`（20dp） |
| `Large` | `icon.size.large`（24dp） |

### LhIconTone

| 值 | 颜色令牌 |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `icon.size.{small,medium,large}` | 三档尺寸 |
| `color.text.primary` | default 色调 |
| `color.{primary,success,warning,danger,info}.default` | 语义色调 |
| `color.text.{regular,secondary}` | regular / secondary 色调 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `click-emits-onclick` | 点击触发 onClick | `LhIconTest.click_emits_onClick`（Compose UI Test） |
| `default-tone-uses-text-primary` | 默认色调取主文字色令牌 | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | primary 色调取主色令牌 | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | 小号取 small 尺寸令牌（反向断言 large 未应用） | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | 已登记图标名解析为矢量；未登记名不绘制 | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # JVM 部分
./gradlew :components:icon:connectedAndroidTest            # 交互部分（需设备）
```
