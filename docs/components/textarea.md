# Textarea 文本域

受控多行输入框，支持行数、字数统计与最大长度。

> 契约源：[`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> 黄金用例：[`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea)（6 条，已翻译为 JVM 单测 + Compose UI Test）

## 基本用法

最小高度 = 行数 × 行高 × 字号，超出后纵向滚动。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="请输入多行内容" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">三行文字
第二行
第三行</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## 字数统计

`showCount` 为 true 时右下角渲染计数（`textarea.countFontSize`，色 `text.secondary`）；`maxlength` 截断输入。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea 计数</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">三行文</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染；真机右下角显示「3 / 20」计数</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `value` | 受控值（必填） | `String` | — |
| `onValueChange` | 值变更回调（必填） | `(String) -> Unit` | — |
| `placeholder` | 占位文字 | `String?` | `null` |
| `rows` | 行数（决定最小高度） | `Int` | `3` |
| `disabled` | 是否禁用 | `Boolean` | `false` |
| `readonly` | 是否只读 | `Boolean` | `false` |
| `showCount` | 是否显示字数统计 | `Boolean` | `false` |
| `maxlength` | 最大长度（超长截断） | `Int` | `Int.MAX_VALUE` |
| `onFocus` / `onBlur` | 聚焦 / 失焦回调 | `(() -> Unit)?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | 内边距（`spacing.md`） |
| `textarea.fontSize` | 字号（`typography.fontSize.base`） |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | 圆角 / 描边宽 / 焦点描边宽 |
| `textarea.countFontSize` | 计数字号（`typography.fontSize.extraSmall` |
| `textarea.minHeight` | 最小高度基准 |
| `typography.lineHeight.primary` | 行高（行数换算） |
| `color.text.{primary,secondary,placeholder}` | 文字 / 计数 / 占位色 |
| `color.focus` / `color.border.base` | 焦点 / 默认描边色 |
| `color.disabled.{bg,text,border}` | 禁用态三色 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `input-emits-onvaluechange` | 输入触发 onValueChange | `LhTextareaTest.input_emits_onValueChange`（Compose UI Test） |
| `disabled-blocks-input` | 禁用态不可输入 | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | 计数渲染「n / max」 | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | 无 maxlength 时只渲染长度 | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | 聚焦消费焦点令牌 | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | 超长输入截断 | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # JVM 部分
./gradlew :components:textarea:connectedAndroidTest           # 交互部分（需设备）
```
