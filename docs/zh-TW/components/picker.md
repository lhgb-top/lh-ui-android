# Picker 選擇器

單列底部滾輪選擇器，捲動停止後最靠近中央指示器的項即選取項。

> 契約源：[`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> 黃金用例：[`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker)（8 條，鉗制與令牌對應翻譯為 JVM 單測，捲動與按鈕互動翻譯為 Compose UI Test）

## 用法

底部彈出：遮罩區在上、面板在下（頂部圓角）。工具列為「取消 / 標題 / 確認」三段。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">取消</button>
        <div class="sim-picker__toolbar-title">請選擇</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">確認</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">選項一</div>
        <div class="sim-picker__item sim-picker__item--selected">選項二</div>
        <div class="sim-picker__item">選項三</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("選項一", "選項二", "選項三")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "請選擇",
)
```

## 選取機制

滾輪是一個可捲動列表，每項高 `picker.itemHeight`；中央指示器為兩條水平線，間距 = itemHeight。
捲動停止後，按已捲動距離四捨五入得到選取索引，變化時觸發 `onValueChange`。
`value` 越界時由 `LhPickerDefaults.clamp` 鉗制到候選範圍內。

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `visible` | 是否顯示；為 false 時不渲染任何內容 | `Boolean` | — |
| `options` | 候選項文字陣列（單列） | `List<String>` | — |
| `onValueChange` | 滾輪停止且選取項變化時觸發，攜帶新索引 | `(Int) -> Unit` | — |
| `onConfirm` | 點選確認按鈕時觸發，攜帶當前選取索引 | `(Int) -> Unit` | — |
| `onCancel` | 點選取消按鈕時觸發 | `() -> Unit` | — |
| `onClose` | 確認/取消後觸發，宿主據此置 visible 為 false | `() -> Unit` | — |
| `value` | 選取項索引（0 基）；越界時鉗制 | `Int` | `0` |
| `title` | 工具列中間的標題 | `String?` | `null` |
| `confirmText` | 確認按鈕文字 | `String` | `"确认"` |
| `cancelText` | 取消按鈕文字 | `String` | `"取消"` |
| `content` | 滾輪內容區（覆蓋預設候選渲染，spec 的 default 插槽） | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | 工具列（覆蓋預設確認/標題/取消，spec 的 toolbar 插槽） | `@Composable (() -> Unit)?` | `null` |
| `listState` | 滾輪列表狀態（預設由 `rememberLhPickerListState(value, options)` 提供；測試時可外部注入） | `LazyListState` | 見左 |
| `modifier` | 修飾符（作用於面板） | `Modifier` | `Modifier` |

測試鉤子：`rememberLhPickerListState(value, options)` 回傳按 `value` 初始定位的 `LazyListState`，
儀器測試可用 `listState.dispatchRawDelta(...)` 確定性地驅動捲動。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `picker.height` | 面板高度（260dp） |
| `picker.itemHeight` | 每項高度（40dp，也是中央指示器間距） |
| `picker.toolbarHeight` | 工具列高度（44dp） |
| `picker.itemFontSize` | 候選項字級 |
| `picker.borderRadius` | 面板頂部圓角 |
| `picker.paddingHorizontal` | 面板橫向內邊距（跟隨 `spacing.md`） |
| `zIndex.modal` / `zIndex.modalBackdrop` | 層級語義對齊（Compose `Popup` 自帶視窗級層級） |
| `color.background.overlay` | 遮罩色 |
| `color.background.default` | 面板底色 |
| `color.text.primary` / `color.text.regular` | 標題 / 候選項色 |
| `color.border.lighter` | 中央指示器線條色 |
| `color.primary.default` | 確認按鈕色 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | 確認攜帶當前索引 + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose`（Compose UI Test） |
| `confirm-also-emits-onclose` | 確認後觸發 onClose | 同上 |
| `cancel-emits-oncancel` | 取消觸發 onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | 捲動改變選取並觸發 onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | value 為負數時鉗制到 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | value 越界時鉗制到末項 | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | 標題渲染文字 | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | 不可見時不渲染 | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # JVM 部分
./gradlew :components:picker:connectedAndroidTest               # 互動部分（需裝置）
```
