# Radio 單選鈕

受控單選鈕，選取後再次點選不會取消選取（單選語義）。

> 契約源：[`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> 黃金用例：[`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio)（6 條，已翻譯為 JVM 單測 + Compose UI Test）

## 狀態與單選群組

元件本身不含群組邏輯，單選互斥由宿主維護（用 `value` 標識群組內項）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>未選取</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>已選取</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>停用（未選）</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>停用（已選）</label></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("選項一" to "a", "選項二" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## 尺寸

三檔圓環尺寸對應到 `radio.size.*` 令牌；內圓點直徑 = 尺寸 × `radio.dotScale`。

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
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `checked` | 是否選取（必填） | `Boolean` | — |
| `onChange` | 由未選取變為選取時觸發，攜帶 `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | 是否停用 | `Boolean` | `false` |
| `label` | 標籤文字 | `String?` | `null` |
| `value` | 單選值（群組內標識，元件本身不含群組邏輯） | `String?` | `null` |
| `lhSize` | 圓環尺寸 | `LhRadioSize` | `Medium` |
| `labelContent` | 標籤內容（覆蓋 label，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

### LhRadioSize

| 值 | 尺寸令牌 | 內圓點直徑 |
| --- | --- | --- |
| `Small` | `radio.size.small`（16dp） | 16dp × `radio.dotScale`（0.5） |
| `Medium` | `radio.size.medium`（20dp） | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large`（24dp） | 24dp × `radio.dotScale` |

點選命中整個行：僅當未選取且未停用時變為選取並觸發 `onChange(true)`。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `radio.size.{small,medium,large}` | 三檔圓環尺寸 |
| `radio.ringWidth` | 外環描邊寬 |
| `radio.dotScale` | 內圓點相對外環直徑的比例（分數） |
| `radio.gap` | 圓環與標籤間距 |
| `color.primary.default` | 選取描邊與圓點色 |
| `color.border.base` | 未選描邊色 |
| `color.disabled.{border,text}` | 停用描邊 / 圓點 / 文字色 |
| `color.text.primary` | 標籤色 |
| `typography.fontSize.base` | 標籤字級 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | 未選點選觸發 onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true`（Compose UI Test） |
| `tap-checked-keeps-checked` | 已選點選不觸發 | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | 停用態不觸發 | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 選取消費主色令牌 | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 未選消費描邊令牌 | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | 小號消費 small 尺寸令牌 | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # JVM 部分
./gradlew :components:radio:connectedAndroidTest             # 互動部分（需裝置）
```
