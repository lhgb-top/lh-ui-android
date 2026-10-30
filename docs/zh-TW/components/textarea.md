# Textarea 文字區

受控多行輸入框，支援行數、字數統計與最大長度。

> 契約源：[`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> 黃金用例：[`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea)（6 條，已翻譯為 JVM 單測 + Compose UI Test）

## 基本用法

最小高度 = 行數 × 行高 × 字級，超出後縱向捲動。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="請輸入多行內容" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">三行文字
第二行
第三行</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

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

## 字數統計

`showCount` 為 true 時右下角渲染計數（`textarea.countFontSize`，色 `text.secondary`）；`maxlength` 截斷輸入。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea 計數</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">三行文</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染；真機右下角顯示「3 / 20」計數</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `value` | 受控值（必填） | `String` | — |
| `onValueChange` | 值變更回呼（必填） | `(String) -> Unit` | — |
| `placeholder` | 佔位文字 | `String?` | `null` |
| `rows` | 行數（決定最小高度） | `Int` | `3` |
| `disabled` | 是否停用 | `Boolean` | `false` |
| `readonly` | 是否唯讀 | `Boolean` | `false` |
| `showCount` | 是否顯示字數統計 | `Boolean` | `false` |
| `maxlength` | 最大長度（超長截斷） | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | 聚焦 / 失焦回呼 | `(() -> Unit)?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | 內邊距（`spacing.md`） |
| `textarea.fontSize` | 字級（`typography.fontSize.base`） |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | 圓角 / 描邊寬 / 焦點描邊寬 |
| `textarea.countFontSize` | 計數字級（`typography.fontSize.extraSmall` |
| `textarea.minHeight` | 最小高度基準 |
| `typography.lineHeight.primary` | 行高（行數換算） |
| `color.text.{primary,secondary,placeholder}` | 文字 / 計數 / 佔位色 |
| `color.focus` / `color.border.base` | 焦點 / 預設描邊色 |
| `color.disabled.{bg,text,border}` | 停用態三色 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `input-emits-onvaluechange` | 輸入觸發 onValueChange | `LhTextareaTest.input_emits_onValueChange`（Compose UI Test） |
| `disabled-blocks-input` | 停用態不可輸入 | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | 計數渲染「n / max」 | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | 無 maxlength 時只渲染長度 | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | 聚焦消費焦點令牌 | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | 超長輸入截斷 | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # JVM 部分
./gradlew :components:textarea:connectedAndroidTest           # 互動部分（需裝置）
```
