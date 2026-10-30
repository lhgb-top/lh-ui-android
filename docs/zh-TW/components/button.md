# Button 按鈕

基礎操作按鈕。觸發即時操作，支援類型、尺寸、載入、停用、圖示與國風花紋底。

> 契約源：[`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> 黃金用例：[`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button)（8 條，已翻譯為 JVM 單測 + Compose UI Test）

## 類型

六種類型決定底色與文字色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 類型</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">預設按鈕</button>
      <button class="sim-btn sim-btn--primary">主要按鈕</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">成功按鈕</button>
      <button class="sim-btn sim-btn--warning">警告按鈕</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">危險按鈕</button>
      <button class="sim-btn sim-btn--info">資訊按鈕</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("主要按鈕") }
```

## 尺寸

三檔尺寸對應到 `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` 令牌。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">小型按鈕</button>
      <button class="sim-btn">中型按鈕</button>
      <button class="sim-btn sim-btn--large">大型按鈕</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## 狀態

停用與載入中都不觸發 `lhOnClick`；兩者共存時按**停用優先**處理（黃金用例 `disabled-precedes-loading`）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 狀態</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">正常</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>停用</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>載入中
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>停用優先</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## 圖示與花紋底

圖示名按本機圖示體系解析（`LhButtonIcons`，當前支援 search / refresh / check / close，未登記名不繪製）。
花紋底消費 `color.decoration` 令牌，疊在底色之上、文字之下，不影響對比度。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>圖示與花紋</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>搜尋</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>預設</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">主要花紋底</button>
      <button class="sim-btn sim-btn--pattern">預設花紋底</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## 明暗主題

切換站台右上角主題，模擬器手機框內的按鈕顏色即變；真機透過 `LhTheme(isDark = true)` 切換。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>暗色按鈕</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">主要按鈕</button>
      <button class="sim-btn">預設按鈕</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>停用</button>
      <button class="sim-btn sim-btn--success">成功</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染；明/暗值全部取自 core 令牌快照</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `lhOnClick` | 點選回呼；`disabled` 或 `loading` 為 true 時不觸發 | `() -> Unit` | — |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |
| `type` | 按鈕類型，決定底色與文字色 | `LhButtonType` | `Default` |
| `lhSize` | 按鈕尺寸，對應到尺寸令牌 | `LhButtonSize` | `Medium` |
| `disabled` | 是否停用 | `Boolean` | `false` |
| `loading` | 是否載入中（顯示指示器，不觸發 lhOnClick） | `Boolean` | `false` |
| `icon` | 圖示名（本機圖示體系解析） | `String?` | `null` |
| `bgPattern` | 是否繪製國風花紋底紋 | `Boolean` | `false` |
| `content` | 按鈕文字內容（spec 的 default 插槽） | `@Composable () -> Unit` | — |

### LhButtonType

| 值 | 底色 | 文字色 |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| 值 | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small`（32dp） | `button.paddingHorizontal.small`（16dp） | `button.fontSize.small`（13sp） | `button.iconSize.small`（16dp） |
| `Medium` | `button.height.medium`（40dp） | `button.paddingHorizontal.medium`（20dp） | `button.fontSize.medium`（14sp） | `button.iconSize.medium`（18dp） |
| `Large` | `button.height.large`（48dp） | `button.paddingHorizontal.large`（24dp） | `button.fontSize.large`（16sp） | `button.iconSize.large`（20dp） |

停用態覆蓋：底色 `color.disabled.bg`、文字 `color.disabled.text`、描邊 `color.disabled.border`。

## 令牌對照

按鈕消費的令牌（與 `specs/button.json` 的 `tokenRefs` 逐條對齊）：

| 令牌 | 用途 |
| --- | --- |
| `button.height.{small,medium,large}` | 三檔高度 |
| `button.paddingHorizontal.{small,medium,large}` | 三檔橫向內邊距 |
| `button.fontSize.{small,medium,large}` | 三檔字級 |
| `button.iconSize.{small,medium,large}` | 三檔圖示尺寸 |
| `button.borderRadius` / `button.borderWidth` | 圓角（跟隨 `radius.base`）與描邊寬度 |
| `button.fontWeight` | 字重（跟隨 `typography.fontWeight.primary`） |
| `color.primary.default` / `color.onPrimary` | 主色類型底/文字 |
| `color.{success,warning,danger,info}.default` | 語義類型底色 |
| `color.background.default` / `color.text.primary` / `color.border.base` | 預設類型底/文字/描邊 |
| `color.disabled.{bg,text,border}` | 停用態三色 |
| `color.decoration` | 花紋底紋色 |
| `shadow.lighter` | 預設類型 elevation |
| `motion.duration.fast` / `motion.easing.easeInOut` | 按壓回饋動效 |

## 黃金用例對照

`cases/button.json` 的 8 條斷言與本地測試的對應關係：

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `click-emits-onclick` | 點選觸發 onClick ×1 | `LhButtonTest.click_emits_onClick`（Compose UI Test） |
| `disabled-blocks-click` | 停用態不觸發 | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | 載入態不觸發 | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | 停用優先 | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | 預設類型渲染文字 | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | 主色類型消費主色令牌 | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | 小號消費 small 尺寸令牌 | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | 花紋底消費裝飾色 | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | 已登記圖示名渲染向量；未登記名不繪製 | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # JVM 部分
./gradlew :components:button:connectedAndroidTest        # 互動部分（需裝置）
```
