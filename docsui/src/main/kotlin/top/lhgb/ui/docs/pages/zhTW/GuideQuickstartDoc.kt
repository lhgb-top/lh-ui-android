package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "快速開始",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "最小範例",
      prose = "`LhTheme` 是主題容器，內部所有 Lh 元件自動消費令牌；不包裹時回退明色色板。",
      code = listOf(
        "import top.lhgb.ui.button.LhButton",
        "import top.lhgb.ui.button.LhButtonType",
        "import top.lhgb.ui.theme.LhTheme",
        "",
        "@Composable",
        "fun Demo() {",
        "  LhTheme {",
        "    LhButton(",
        "      type = LhButtonType.Primary,",
        "      lhOnClick = { /* 處理點選 */ },",
        "    ) {",
        "      Text(\"主要按鈕\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "類型與尺寸",
      prose = "",
      code = listOf(
        "// 六種類型",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"預設\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"主要\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"成功\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"警告\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"危險\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"資訊\") }",
        "",
        "// 三種尺寸",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"小型\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"中型\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"大型\") }",
      ),
    ),
    DocSection(
      title = "狀態",
      prose = "",
      code = listOf(
        "// 停用：不回應點選，呈現停用樣式",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"停用\") }",
        "",
        "// 載入中：顯示指示器，不觸發 lhOnClick",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"載入中\") }",
        "",
        "// 兩者共存：按停用優先處理（黃金用例 disabled-precedes-loading）",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"停用優先\") }",
      ),
    ),
    DocSection(
      title = "圖示與花紋底",
      prose = "",
      code = listOf(
        "// 圖示名按本機圖示體系解析（見 LhButtonIcons，未登記名不繪製）",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"搜尋\") }",
        "",
        "// 國風花紋底紋（消費 color.decoration 令牌）",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"花紋底\") }",
      ),
    ),
    DocSection(
      title = "明暗主題",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // 內部所有元件自動切換暗色令牌（core 預計算值，無執行時調色）",
        "}",
      ),
    ),
    DocSection(
      title = "原生文件模組",
      prose = "除本網頁文件外，倉內還有一套安卓原生文件模組（`:docsui`）：文件頁 1:1 對齊本站結構，但預覽是真 Compose 即時渲染、可互動的（按鈕可點選、輸入可打字、彈窗能彈出），而非靜態 HTML 模擬。\n拖入即用：任意安卓工程依賴後，一行 `LhDocs.launch(context)` 即可開啟完整文件（14 元件頁 + 5 指南頁 + 明暗切換 + 即時預覽）：\n庫的 manifest 自動合併一個 `exported = false` 的 `LhDocsActivity`，不給宿主注入啟動器圖示。\n獨立打包：`:docsapp` 是極薄的 application 模組（只有一個 Activity），單獨打包即得「lh-ui 文件」APP：",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // 或 Maven 座標 top.lhgb.ui:docsui",
        "",
        "// 任意入口（如「元件文件」按鈕的點選回呼）",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "元件範例預覽",
      prose = "原生 UI 無法在瀏覽器即時渲染，本站用內聯靜態 HTML 手機框模擬器展示外觀：\n模擬器的暗色與站台連動：右上角切換主題，手機框內顏色即變（暗色令牌由 core 預計算）。",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
