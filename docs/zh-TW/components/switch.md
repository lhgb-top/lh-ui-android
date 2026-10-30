# Switch 開關

膠囊軌道 + 圓形滑塊的兩態切換控制項，支援載入中佔位。

> 契約源：[`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> 黃金用例：[`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch)（6 條，已翻譯為 JVM 單測 + Compose UI Test）

## 狀態

開啟取主色軌道，關閉取描邊色軌道；滑塊直徑 = 高度 - 2 × `switch.gap`，位移動畫取 `motion.duration.base`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>關閉</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>開啟</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>停用（關）</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>停用（開）</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>載入中（滑塊位置顯示載入環，不回應點選）</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## 尺寸

三檔尺寸對應到 `switch.width` / `switch.height` 令牌。

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
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `checked` | 是否開啟（必填） | `Boolean` | — |
| `onChange` | 開關態變更回呼，攜帶新開關態 | `(Boolean) -> Unit` | — |
| `disabled` | 是否停用 | `Boolean` | `false` |
| `loading` | 是否載入中（滑塊位置顯示載入環，不回應點選） | `Boolean` | `false` |
| `lhSize` | 尺寸 | `LhSwitchSize` | `Medium` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

### LhSwitchSize

| 值 | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small`（36dp） | `switch.height.small`（20dp） |
| `Medium` | `switch.width.medium`（44dp） | `switch.height.medium`（24dp） |
| `Large` | `switch.width.large`（52dp） | `switch.height.large`（28dp） |

軌道為膠囊（圓角取高度一半）；點選命中軌道區域，翻轉開關態並觸發 `onChange`。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `switch.width.{small,medium,large}` | 三檔軌道寬度 |
| `switch.height.{small,medium,large}` | 三檔軌道高度 |
| `switch.gap` | 滑塊與軌道之間的留白（滑塊直徑 = 高度 - 2 × gap） |
| `color.primary.default` | 開啟軌道色 |
| `color.border.base` | 關閉軌道色 |
| `color.onPrimary` | 滑塊色 |
| `color.disabled.{bg,text}` | 停用軌道 / 滑塊色 |
| `motion.duration.base` / `motion.easing.easeInOut` | 滑塊位移動畫 |
| `loading.strokeWidth` | 載入環描邊寬（複用） |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | 點選翻轉並發射 onChange | `LhSwitchTest.tap_unchecked_emits_true`（Compose UI Test） |
| `tap-checked-turns-off` | 開啟態點選關閉 | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | 停用態不觸發 | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | 載入態不觸發 | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | 開啟消費主色令牌 | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | 關閉消費描邊令牌 | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # JVM 部分
./gradlew :components:switch:connectedAndroidTest            # 互動部分（需裝置）
```
