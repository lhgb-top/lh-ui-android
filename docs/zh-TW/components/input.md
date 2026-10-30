# Input 輸入框

受控單行輸入框，支援尺寸、停用、唯讀、清除、密碼遮罩與最大長度。

> 契約源：[`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> 黃金用例：[`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input)（8 條，已翻譯為 JVM 單測 + Compose UI Test）

## 基本用法

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="請輸入內容" /></div>
    <div class="sim-row"><input class="sim-input" value="已填內容" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="停用態" disabled /></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "請輸入內容",
  onValueChange = { value = it },
)
```

## 尺寸與焦點

三檔尺寸對應到 `input.height` / `input.paddingHorizontal` / `input.fontSize` 令牌；聚焦時描邊取 `color.focus` 並加寬到 `input.focusRingWidth`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium（聚焦態示範）" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `value` | 受控值（必填） | `String` | — |
| `onValueChange` | 值變更回呼（必填） | `(String) -> Unit` | — |
| `placeholder` | 佔位文字 | `String?` | `null` |
| `type` | 輸入類型（明文 / 密碼） | `LhInputType` | `Text` |
| `lhSize` | 尺寸 | `LhInputSize` | `Medium` |
| `disabled` | 是否停用 | `Boolean` | `false` |
| `readonly` | 是否唯讀（不可編輯但不停用樣式） | `Boolean` | `false` |
| `clearable` | 是否顯示清除按鈕 | `Boolean` | `false` |
| `maxlength` | 最大長度（超長截斷） | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | 聚焦 / 失焦回呼 | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | 前綴 / 後綴插槽 | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

### LhInputSize

| 值 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small`（32dp） | `input.paddingHorizontal.small`（12dp） | `input.fontSize.small`（13sp） |
| `Medium` | `input.height.medium`（40dp） | `input.paddingHorizontal.medium`（16dp） | `input.fontSize.medium`（14sp） |
| `Large` | `input.height.large`（48dp） | `input.paddingHorizontal.large`（20dp） | `input.fontSize.large`（16sp） |

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `input.height.{small,medium,large}` | 三檔高度 |
| `input.paddingHorizontal.{small,medium,large}` | 三檔橫向內邊距 |
| `input.fontSize.{small,medium,large}` | 三檔字級 |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | 圓角 / 描邊寬 / 焦點描邊寬 |
| `input.clearIconSize` | 清除圖示尺寸 |
| `color.focus` | 焦點描邊色 |
| `color.text.placeholder` | 佔位文字色 |
| `color.border.base` | 預設描邊色 |
| `color.disabled.{bg,text,border}` | 停用態三色 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `input-emits-onvaluechange` | 輸入觸發 onValueChange | `LhInputTest.input_emits_onValueChange`（Compose UI Test） |
| `clearable-emits-empty-value` | 清除按鈕發射空值 | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | 停用態不可輸入 | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | 唯讀態不可輸入 | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | 佔位色取佔位令牌 | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | 聚焦消費焦點令牌（失焦取中性描邊） | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | 小號消費 small 尺寸令牌 | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | 超長輸入截斷 | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # JVM 部分
./gradlew :components:input:connectedAndroidTest              # 互動部分（需裝置）
```
