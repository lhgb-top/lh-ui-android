# 快速開始

## 最小範例

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* 處理點選 */ },
    ) {
      Text("主要按鈕")
    }
  }
}
```

`LhTheme` 是主題容器，內部所有 Lh 元件自動消費令牌；不包裹時回退明色色板。

## 類型與尺寸

```kotlin
// 六種類型
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("預設") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("主要") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("成功") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("警告") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("危險") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("資訊") }

// 三種尺寸
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("小型") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("中型") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("大型") }
```

## 狀態

```kotlin
// 停用：不回應點選，呈現停用樣式
LhButton(disabled = true, lhOnClick = {}) { Text("停用") }

// 載入中：顯示指示器，不觸發 lhOnClick
LhButton(loading = true, lhOnClick = {}) { Text("載入中") }

// 兩者共存：按停用優先處理（黃金用例 disabled-precedes-loading）
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("停用優先") }
```

## 圖示與花紋底

```kotlin
// 圖示名按本機圖示體系解析（見 LhButtonIcons，未登記名不繪製）
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("搜尋") }

// 國風花紋底紋（消費 color.decoration 令牌）
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("花紋底") }
```

## 明暗主題

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // 內部所有元件自動切換暗色令牌（core 預計算值，無執行時調色）
}
```

## 原生文件模組

除本網頁文件外，倉內還有一套**安卓原生文件模組**（`:docsui`）：文件頁 1:1 對齊本站結構，但預覽是**真 Compose 即時渲染、可互動**的（按鈕可點選、輸入可打字、彈窗能彈出），而非靜態 HTML 模擬。

**拖入即用**：任意安卓工程依賴後，一行 `LhDocs.launch(context)` 即可開啟完整文件（14 元件頁 + 5 指南頁 + 明暗切換 + 即時預覽）：

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // 或 Maven 座標 top.lhgb.ui:docsui

// 任意入口（如「元件文件」按鈕的點選回呼）
LhDocs.launch(context)
```

庫的 manifest 自動合併一個 `exported = false` 的 `LhDocsActivity`，不給宿主注入啟動器圖示。

**獨立打包**：`:docsapp` 是極薄的 application 模組（只有一個 Activity），單獨打包即得「lh-ui 文件」APP：

```bash
./gradlew :docsapp:installDebug
```

## 元件範例預覽

原生 UI 無法在瀏覽器即時渲染，本站用**內聯靜態 HTML 手機框模擬器**展示外觀：

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">預設按鈕</button>
      <button class="sim-btn sim-btn--primary">主要按鈕</button>
      <button class="sim-btn sim-btn--success">成功</button>
      <button class="sim-btn sim-btn--warning">警告</button>
      <button class="sim-btn sim-btn--danger">危險</button>
      <button class="sim-btn sim-btn--info">資訊</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">小型</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">大型</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>停用</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>載入中
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>搜尋</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">花紋底</button>
    </div>
  </div>
</div>
<p class="sim-note">Android（Compose）外觀的 HTML 模擬預覽，非真機渲染；樣式值全部取自 core 令牌快照，與真機同源同值。真機外觀以 <code>@Preview</code> 與裝置截圖為準。</p>

> 模擬器的暗色與站台連動：右上角切換主題，手機框內顏色即變（暗色令牌由 core 預計算）。
