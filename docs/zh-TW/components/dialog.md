# Dialog 對話框

模態浮層，用於重要確認或需要使用者聚焦的內容。視窗級層級由 Compose `Dialog` 託管。

> 契約源：[`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> 黃金用例：[`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog)（8 條，已翻譯為 JVM 單測 + Compose UI Test）

## 類型

`type` 決定確認按鈕的顏色（1:1 對應到 `LhButtonType`），其餘版面配置不變。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">標題</div>
      <div class="sim-dialog__content">這是一段正文內容，用於說明當前操作的影響。</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">取消</button>
        <button class="sim-btn sim-btn--primary">確認</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* 確認邏輯 */ },
  onCancel = {},
  onClose = { visible = false },
  title = "刪除確認",
  content = "刪除後不可復原，確定刪除嗎？",
  type = LhDialogType.danger,
)
```

## 語義類型與遮罩

`type` 走 `danger` 時確認按鈕取危險色；`closable` 控制點遮罩 / 按返回鍵是否觸發 `onClose`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">刪除確認</div>
      <div class="sim-dialog__content">刪除後不可復原，確定刪除嗎？</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">取消</button>
        <button class="sim-btn sim-btn--danger">確認</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

確認 / 取消按鈕點選後**先觸發業務回呼，再觸發 `onClose`**，宿主據此把 `visible` 置為 false。

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `visible` | 是否顯示；為 false 時不渲染任何內容 | `Boolean` | — |
| `onConfirm` | 點選確認按鈕時觸發 | `() -> Unit` | — |
| `onCancel` | 點選取消按鈕時觸發 | `() -> Unit` | — |
| `onClose` | 確認/取消後或（closable 時）點遮罩觸發 | `() -> Unit` | — |
| `title` | 標題 | `String?` | `null` |
| `content` | 正文內容 | `String?` | `null` |
| `type` | 語義類型，決定確認按鈕的類型（顏色） | `LhDialogType` | `default` |
| `confirmText` | 確認按鈕文字；為空時不顯示確認按鈕 | `String` | `"确认"` |
| `cancelText` | 取消按鈕文字；為空時不顯示取消按鈕 | `String` | `"取消"` |
| `closable` | 點遮罩 / 按返回鍵是否觸發 onClose | `Boolean` | `false` |
| `titleContent` | 標題內容（覆蓋 title，spec 的 title 插槽） | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | 正文內容（覆蓋 content，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `footer` | 底部按鈕區（覆蓋預設按鈕，spec 的 footer 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾符（作用於內容卡） | `Modifier` | `Modifier` |

### LhDialogType

| 值 | 確認按鈕類型 |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | 同名 `LhButtonType`，取對應語義色 |

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `dialog.width` | 內容卡寬度（320dp） |
| `dialog.borderRadius` | 卡片圓角（跟隨 `radius.base`） |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | 卡片內邊距（跟隨 `spacing.lg`） |
| `dialog.titleFontSize` / `dialog.contentFontSize` | 標題 / 正文字級 |
| `dialog.gap` | 標題、正文、按鈕區之間的間距 |
| `zIndex.modal` / `zIndex.modalBackdrop` | 層級語義對齊（Compose `Dialog` 託管視窗級層級） |
| `color.background.overlay` | 遮罩色 |
| `color.background.default` | 卡片底色 |
| `color.text.primary` / `color.text.regular` | 標題 / 正文色 |
| `shadow.base` | 卡片投影（Compose 不支援 spread，取模糊半徑做 elevation） |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `confirm-emits-onconfirm` | 確認觸發 onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose`（Compose UI Test） |
| `confirm-also-emits-onclose` | 確認後觸發 onClose | 同上 |
| `cancel-emits-oncancel` | 取消觸發 onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | closable 時點遮罩觸發 onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | 非 closable 時吞掉遮罩點選 | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | 不可見時不渲染 | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | 標題渲染文字 | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | danger 類型確認按鈕取危險色 | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # JVM 部分
./gradlew :components:dialog:connectedAndroidTest             # 互動部分（需裝置）
```
