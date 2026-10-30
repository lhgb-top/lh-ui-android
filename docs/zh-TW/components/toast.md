# Toast 輕提示

自持可見性與計時的墨色膠囊浮層，用於輕量級操作回饋。不阻塞使用者操作。

> 契約源：[`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> 黃金用例：[`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast)（6 條，狀態機 4 條 + 渲染 2 條，全部翻譯為本地測試）

## 用法

toast 是**自持狀態機**：狀態由 `LhToastState` 承載，宿主呼叫 `show(text)` 顯示、`hide()` 隱藏，
計時到期自動隱藏並觸發 `onClose`。元件在可見時按幀自驅動 `tick`，宿主也可自行呼叫接管計時。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">操作成功</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
val state = rememberLhToastState(onClose = { /* 可選：記錄關閉 */ })
Column {
  LhButton(lhOnClick = { state.show("已儲存") }) { Text("觸發") }
  LhToast(state = state)
}
```

## 語義類型

非 `default` 類型時在文字前繪製直徑等於字級的語義色圓點。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 類型</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>成功提示</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>警告提示</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>失敗提示</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>主要提示</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## 定位

`lhPosition` 取 `top` / `center` / `bottom`，兩端各留 `spacing.xl` 邊距；寬度由文字測量得出並封頂可用寬度的 80%。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 定位</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">頂部</div>
    <div class="sim-toast sim-toast--bottom">底部</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `state` | 輕提示狀態機（承載可見性、文字與計時） | `LhToastState` | — |
| `type` | 語義類型；非 default 時繪製語義色圓點 | `LhToastType` | `default` |
| `lhPosition` | 在可用區域內的定位 | `LhToastPosition` | `center` |
| `modifier` | 修飾符（作用於膠囊） | `Modifier` | `Modifier` |

### LhToastState

| 成員 | 說明 |
| --- | --- |
| `show(text: String)` | 顯示並重設計時 |
| `hide()` | 立即隱藏，**不觸發 onClose** |
| `tick(deltaMs: Int): Boolean` | 推進計時；到期時隱藏、觸發 onClose 並回傳 true |
| `visible` / `text` / `duration` | 當前狀態（可觀察） |

工廠：`rememberLhToastState(duration = 2000, onClose = {})`。

### LhToastType / LhToastPosition

| 值 | 圓點色 |
| --- | --- |
| `default` | 無圓點 |
| `primary` / `success` / `warning` / `danger` / `info` | 對應語義色 |

`LhToastPosition`：`top` / `center` / `bottom`。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `toast.maxWidth` | 膠囊最大寬度（240dp；實際取可用寬度 80% 與之較小） |
| `toast.borderRadius` | 膠囊圓角（跟隨 `radius.base`） |
| `toast.paddingHorizontal` / `toast.paddingVertical` | 膠囊內邊距 |
| `toast.fontSize` | 文字字級（圓點直徑同此值） |
| `toast.gap` | 圓點與文字間距 |
| `zIndex.toast` | 層級語義對齊（Compose `Popup` 自帶視窗級層級） |
| `color.background.overlay`（主題層 ink 映射） | 膠囊底色 |
| `color.onPrimary` | 文字色 |
| `color.{primary,success,warning,danger,info}.default` | 語義圓點色 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `show-makes-visible` | show 後可見且渲染文字 | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | 到期自動隱藏並觸發 onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | 計時未到保持可見且不觸發 onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide 不觸發 onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | success 類型消費成功色 | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | 不可見時不渲染 | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # JVM 部分
./gradlew :components:toast:connectedAndroidTest                 # 互動部分（需裝置）
```
