# Navbar 導覽列

頁面頂部標題列，左 / 中 / 右三段版面配置，常用於返回與標題展示。

> 契約源：[`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> 黃金用例：[`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar)（6 條，令牌對應翻譯為 JVM 單測，左右點選翻譯為 Compose UI Test）

## 用法

三段等寬（各 `weight(1f)`），標題水平置中於可用寬度；預設繪製底部分隔線。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>返回</div>
      <div class="sim-navbar__title">頁面標題</div>
      <div class="sim-navbar__side sim-navbar__side--right">更多</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
LhNavbar(
  title = "頁面標題",
  leftIcon = "arrow-left",
  leftText = "返回",
  rightText = "更多",
  onLeftClick = { /* 返回 */ },
  onRightClick = { /* 更多 */ },
)
```

## 左右內容

文字與圖示可同時存在（圖示在左、文字在右，間距 `spacing.xs`）；**某一側沒有任何內容時不回應點選**
（黃金用例 `no-left-content-no-onleftclick`：該節點連可點選語義都不存在）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar 變體</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">僅圖示</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">文字</div>
      <div class="sim-navbar__title">無分隔線</div>
      <div class="sim-navbar__side sim-navbar__side--right">編輯</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `title` | 中間標題 | `String?` | `null` |
| `onLeftClick` | 點選左側區域時觸發 | `() -> Unit` | `{}` |
| `onRightClick` | 點選右側區域時觸發 | `() -> Unit` | `{}` |
| `leftText` | 左側按鈕文字；與 leftIcon 可共存 | `String?` | `null` |
| `leftIcon` | 左側圖示名（本機圖示體系，如 `"arrow-left"`） | `String?` | `null` |
| `rightText` | 右側按鈕文字；與 rightIcon 可共存 | `String?` | `null` |
| `rightIcon` | 右側圖示名 | `String?` | `null` |
| `bordered` | 是否繪製底部分隔線 | `Boolean` | `true` |
| `titleContent` | 標題內容（覆蓋 title，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | 左側內容（覆蓋 leftText/leftIcon，spec 的 left 插槽） | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | 右側內容（覆蓋 rightText/rightIcon，spec 的 right 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

某一側的 `*Content` / `*Text` / `*Icon` 全為空時，該側不可點選也不繪製任何內容。

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `navbar.height` | 導覽列高度（44dp） |
| `navbar.paddingHorizontal` | 左右橫向內邊距（跟隨 `spacing.md`） |
| `navbar.titleFontSize` | 標題與兩側文字字級 |
| `navbar.iconSize` | 兩側圖示尺寸（跟隨 `icon.size.medium`） |
| `navbar.borderWidth` | 底部分隔線寬度 |
| `zIndex.sticky` | 吸頂層級語義對齊 |
| `color.background.default` | 底色 |
| `color.text.primary` / `color.text.regular` | 標題 / 兩側文字色 |
| `color.border.lighter` | 底部分隔線色 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | 點選左側觸發 onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick`（Compose UI Test） |
| `rightclick-emits-onrightclick` | 點選右側觸發 onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | 無左側內容時不觸發 | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | 標題渲染文字 | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | 分隔線消費描邊令牌 | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | 高度消費 navbar 令牌 | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # JVM 部分
./gradlew :components:navbar:connectedAndroidTest                # 互動部分（需裝置）
```
