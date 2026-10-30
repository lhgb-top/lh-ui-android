# Loading 載入

載入指示器，環形旋轉動畫，時長取 `motion.duration.base`（毫秒）。

> 契約源：[`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> 黃金用例：[`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading)（5 條，純展示元件，翻譯為 JVM 單測）

## 尺寸與色調

三檔尺寸對應到 `loading.size.*` 令牌，描邊寬取 `loading.strokeWidth`，色調取語義色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">載入中…</div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `lhSize` | 指示器直徑 | `LhLoadingSize` | `Medium` |
| `tone` | 環色 | `LhLoadingTone` | `Primary` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |

### LhLoadingSize

| 值 | 尺寸令牌 |
| --- | --- |
| `Small` | `loading.size.small`（16dp） |
| `Medium` | `loading.size.medium`（20dp） |
| `Large` | `loading.size.large`（24dp） |

### LhLoadingTone

| 值 | 顏色令牌 |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `loading.size.{small,medium,large}` | 三檔直徑 |
| `loading.strokeWidth` | 環描邊寬度 |
| `color.text.regular` | default 色調 |
| `color.{primary,success,warning,danger,info}.default` | 語義色調 |
| `motion.duration.base` | 旋轉一圈時長（端側為毫秒整數） |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `medium-size-uses-medium-token` | 中號取 medium 尺寸令牌 | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | 大號取 large 尺寸令牌 | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | primary 色調取主色令牌 | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | success 色調取成功色令牌 | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | 環形指示器渲染（描邊寬消費令牌） | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # JVM 部分（純展示，無互動用例）
```
