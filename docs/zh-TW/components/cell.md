# Cell 儲存格

列表儲存格，圖示 / 標題 + 描述 / 右值 + 箭頭的三段式版面配置，常與列表組合使用。

> 契約源：[`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> 黃金用例：[`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell)（6 條，已翻譯為 JVM 單測 + Compose UI Test）

## 基本用法

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">標題文字</div>
      <div class="sim-cell__value">右側值</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">帶描述的標題</div>
        <div class="sim-cell__label">描述文字</div>
      </div>
      <div class="sim-cell__value">值</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>必填項</div>
      <div class="sim-cell__value">詳細</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
LhCell(
  title = "帶描述的標題",
  label = "描述文字",
  value = "值",
  isLink = true,
  lhOnClick = { /* 跳轉 */ },
)
```

## 必填與連結

`required` 在標題前繪製紅色星號（`color.danger.default`）；`isLink` 在末尾繪製右箭頭圖示。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell 變體</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>必填</div>
      <div class="sim-cell__value">箭頭 →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">無邊框</div>
      <div class="sim-cell__value">值</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `title` | 標題（主文字） | `String?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |
| `value` | 右側值（次文字，右對齊） | `String?` | `null` |
| `label` | 標題下方的說明文字 | `String?` | `null` |
| `bordered` | 是否繪製底部分隔線（`color.border.lighter`） | `Boolean` | `true` |
| `clickable` | 是否可點選；為 true 時顯示右側指示箭頭並回應點選 | `Boolean` | `false` |
| `required` | 是否在標題前顯示必填紅星（`color.danger.default`） | `Boolean` | `false` |
| `icon` | 左側圖示名（本機圖示體系）；空時不繪製 | `String?` | `null` |
| `lhOnClick` | 點選回呼；`clickable` 為 false 時不觸發 | `(() -> Unit)?` | `null` |
| `titleContent` | 標題內容（覆蓋 title，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | 右側值內容（覆蓋 value，spec 的 value 插槽） | `@Composable (() -> Unit)?` | `null` |

`clickable` 同時控制「右側指示箭頭是否繪製」與「整行是否回應點選」——只有 `clickable = true` 且 `lhOnClick` 非空時點選才觸發。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `cell.paddingVertical` | 縱向內邊距（`spacing.md` 參照） |
| `cell.paddingHorizontal` | 橫向內邊距（`spacing.md`） |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | 標題 / 描述 / 值字級 |
| `cell.gap` | 圖示與標題間距（`spacing.xs`） |
| `icon.size.medium` | 左側圖示與指示箭頭尺寸 |
| `cell.borderWidth` | 底分隔線寬度 |
| `typography.lineHeight.primary` | 文字行高 |
| `color.text.{primary,secondary}` | 標題 / 描述 / 值顏色 |
| `color.danger.default` | 必填紅星 |
| `color.border.lighter` | 底分隔線 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `title-and-value-render` | 標題與右值渲染文字 | `LhCellTest.title_and_value_render`（Compose UI Test） |
| `label-renders-secondary-tone` | label 取次要文字色令牌 | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | 必填標記取危險色令牌 | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | 分隔線消費描邊令牌 | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | 點選觸發 onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | 停用態不觸發 | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # JVM 部分
./gradlew :components:cell:connectedAndroidTest              # 互動部分（需裝置）
```
