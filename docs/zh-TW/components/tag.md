# Tag 標籤

語義色標籤，支援鏤空、全圓角標記與關閉按鈕。

> 契約源：[`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> 黃金用例：[`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag)（6 條，已翻譯為 JVM 單測 + Compose UI Test）

## 類型

六種語義類型決定底色與文字色。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag 類型</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">預設</span><span class="sim-tag sim-tag--success">成功</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">警告</span><span class="sim-tag sim-tag--danger">危險</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">資訊</span></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

```kotlin
LhTag(type = LhTagType.success) { Text("成功") }
```

## 鏤空（plain）

`plain` 為透明底 + 語義色描邊與文字。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">主要</span><span class="sim-tag sim-tag--plain sim-tag--success">成功</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">危險</span></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## 尺寸與標記

三檔尺寸對應到 `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` 令牌；`mark` 取高度一半的全圓角。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag 尺寸</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">標記</span><span class="sim-tag sim-tag--success sim-tag--mark">成功標記</span></div>
  </div>
</div>
<p class="sim-note">HTML 模擬預覽（Android / Compose 外觀），非真機渲染</p>

## API

### Props

| 屬性 | 說明 | 類型 | 預設值 |
| --- | --- | --- | --- |
| `text` | 標籤文字（便捷寫法，與 `content` 二選一） | `String?` | `null` |
| `modifier` | 修飾符 | `Modifier` | `Modifier` |
| `type` | 語義類型 | `LhTagType` | `Default` |
| `lhSize` | 尺寸 | `LhTagSize` | `Medium` |
| `plain` | 是否鏤空（透明底 + 語義色描邊） | `Boolean` | `false` |
| `round` | 是否全圓角（高度一半） | `Boolean` | `false` |
| `closable` | 是否顯示關閉按鈕 | `Boolean` | `false` |
| `onClose` | 關閉回呼；`closable` 為 false 時不觸發 | `(() -> Unit)?` | `null` |
| `content` | 標籤內容（覆蓋 text，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| 值 | 底色（非 plain） | 描邊/文字（plain） |
| --- | --- | --- |
| `Default` | `color.background.default` + `color.border.base` 描邊 | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| 值 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small`（20dp） | `tag.paddingHorizontal.small`（8dp） | `tag.fontSize.small`（12sp） |
| `Medium` | `tag.height.medium`（24dp） | `tag.paddingHorizontal.medium`（10dp） | `tag.fontSize.medium`（14sp） |
| `Large` | `tag.height.large`（28dp） | `tag.paddingHorizontal.large`（12dp） | `tag.fontSize.large`（16sp） |

## 令牌對照

| 令牌 | 用途 |
| --- | --- |
| `tag.height.{small,medium,large}` | 三檔高度 |
| `tag.paddingHorizontal.{small,medium,large}` | 三檔橫向內邊距 |
| `tag.fontSize.{small,medium,large}` | 三檔字級 |
| `tag.borderRadius` / `tag.borderWidth` | 圓角與描邊寬度 |
| `color.{primary,success,warning,danger,info}.default` | 語義底色 / 描邊 / 文字 |
| `color.onPrimary` | 非純文字色 |
| `color.disabled.{bg,text,border}` | 停用態三色 |
| `spacing.xs` | 關閉按鈕與文字間距 |

## 黃金用例對照

| case id | 斷言 | 本地測試 |
| --- | --- | --- |
| `default-type-renders-text` | 預設類型取中性底 + 主文字色 + 基礎描邊 | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | 主色類型消費主色令牌 | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain 消費語義淺底令牌 | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | 小號消費 small 令牌（反向斷言 large 未套用） | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round 圓角取高度一半 | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | 點選關閉觸發 onClose | `LhTagTest.closable_tap_emits_onClose`（Compose UI Test） |

```bash
./gradlew :components:tag:test                             # JVM 部分
./gradlew :components:tag:connectedAndroidTest             # 互動部分（需裝置）
```
