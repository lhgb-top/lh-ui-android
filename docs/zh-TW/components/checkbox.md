# Checkbox 核取方塊

受控核取方塊，支援不確定態（indeterminate）與停用。

> 契約源：[`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> 黃金用例：[`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox)（6 條，已翻譯為 JVM 單測 + Compose UI Test）

## 狀態

選取 / 不確定態為實心主色底 + onPrimary 指示符（勾形 / 橫槓），未選為只描邊。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>未選取</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>已選取</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>不確定態</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>停用（已選）</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>停用（未選）</label></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "同意協議",
)
```

## 尺寸

三檔方框尺寸對應到 `checkbox.size.*` 令牌。

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
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `checked` | 是否選取（必填） | `Boolean` | — |
| `onCheckedChange` | 選取態變更回呼，攜帶新選取態 | `(Boolean) -> Unit` | — |
| `indeterminate` | 是否不確定態（indeterminate） | `Boolean` | `false` |
| `disabled` | 是否停用 | `Boolean` | `false` |
| `label` | 標籤文字 | `String?` | `null` |
| `lhSize` | 方框尺寸 | `LhCheckboxSize` | `Medium` |
| `labelContent` | 標籤內容（覆蓋 label，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

### LhCheckboxSize

| 值 | 尺寸令牌 |
| --- | --- |
| `Small` | `checkbox.size.small`（16dp） |
| `Medium` | `checkbox.size.medium`（20dp） |
| `Large` | `checkbox.size.large`（24dp） |

點選命中整個行（方框 + 標籤）：不確定態點選變為選取，否則翻轉選取態。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `checkbox.size.{small,medium,large}` | 三檔方框尺寸 |
| `checkbox.borderRadius` / `checkbox.borderWidth` | 方框圓角與描邊寬 |
| `checkbox.checkWidth` | 勾形 / 橫槓描邊寬 |
| `checkbox.gap` | 方框與標籤間距 |
| `color.primary.default` / `color.onPrimary` | 選取底色 / 指示符色 |
| `color.border.base` | 未選描邊色 |
| `color.disabled.{bg,border,text}` | 停用態三色 |
| `color.text.primary` | 標籤色 |
| `typography.fontSize.base` | 標籤字級 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | 點選翻轉並發射 onChange | `LhCheckboxTest.tap_unchecked_emits_true`（Compose UI Test） |
| `tap-checked-unchecks` | 選取態點選取消 | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | 不確定態點選變選取 | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | 停用態不觸發 | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 選取消費主色令牌 | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 未選消費描邊令牌 | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # JVM 部分
./gradlew :components:checkbox:connectedAndroidTest           # 互動部分（需裝置）
```
