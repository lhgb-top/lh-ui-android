# Icon 圖示

圖示基座元件。其他元件（cell / input / navbar 等）的圖示都經由本元件的本機註冊表渲染。

> 契約源：[`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> 黃金用例：[`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon)（5 條，已翻譯為 JVM 單測 + Compose UI Test）

## 尺寸與色調

三檔尺寸對應到 `icon.size.*` 令牌；色調 `default` 取 `color.text.primary`，其餘取語義色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">預設色（text.primary）</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## 語義色調

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 色調</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## 圖示註冊表

`LhIcons` 是本機圖示名 → Material `ImageVector` 的註冊表，未登記的圖示名回傳 `null`（不繪製，不拋異常）：

| 圖示名 | Material 向量 |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `name` | 圖示名（`LhIcons` 註冊表解析） | `String` | — |
| `lhSize` | 圖示尺寸 | `LhIconSize` | `Medium` |
| `tone` | 語義色調 | `LhIconTone` | `Default` |
| `contentDescription` | 無障礙描述；預設取圖示名，顯式傳 `null` 宣告為裝飾性圖示 | `String?` | `name` |
| `lhOnClick` | 點選回呼；為 null 時不可點選 | `(() -> Unit)?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

### LhIconSize

| 值 | 尺寸令牌 |
| --- | --- |
| `Small` | `icon.size.small`（16dp） |
| `Medium` | `icon.size.medium`（20dp） |
| `Large` | `icon.size.large`（24dp） |

### LhIconTone

| 值 | 顏色令牌 |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `icon.size.{small,medium,large}` | 三檔尺寸 |
| `color.text.primary` | default 色調 |
| `color.{primary,success,warning,danger,info}.default` | 語義色調 |
| `color.text.{regular,secondary}` | regular / secondary 色調 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `click-emits-onclick` | 點選觸發 onClick | `LhIconTest.click_emits_onClick`（Compose UI Test） |
| `default-tone-uses-text-primary` | 預設色調取主文字色令牌 | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | primary 色調取主色令牌 | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | 小號取 small 尺寸令牌（反向斷言 large 未套用） | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | 已登記圖示名解析為向量；未登記名不繪製 | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # JVM 部分
./gradlew :components:icon:connectedAndroidTest            # 互動部分（需裝置）
```
