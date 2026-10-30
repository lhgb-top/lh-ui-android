# 主題與暗色

## 令牌模型

原生血脈的令牌真源在 `lh-ui-core/tokens/*.json`，codegen 生成 Android 產物：

| 產物 | 內容 | 暗色形態 |
| --- | --- | --- |
| `LhTokens` | 明色全量：Button 尺寸、Color 色板、Motion、Radius、Shadow、Spacing、Typography、ZIndex | — |
| `LhDarkTokens` | 僅 `Color` 子物件（**並列物件**模型） | 暗色只覆蓋顏色，尺寸/動效/圓角暗色不變 |

> 這與 ios / harmony / windows 一致（並列物件），與 docs 端（淺合併 JSON）形態不同。
> 主題層據此設計：`LhColors` 只承載顏色，尺寸由元件直接取 `LhTokens`。

## LhColors

`LightLhColors` / `DarkLhColors` 把生成的兩個 object 展平為一份不可變色板：

```kotlin
@Immutable
data class LhColors(
  val primary: Color, val onPrimary: Color,
  val success: Color, val warning: Color, val danger: Color, val info: Color,
  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */
)

val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)
val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)
```

## LhTheme

```kotlin
LhTheme(isDark = true) {
  // LocalLhColors 注入 DarkLhColors，內部元件自動消費
}
```

無 `LhTheme` 包裹時回退 `LightLhColors`（缺省即明色）。

## 暗色預計算（重要）

web 血脈的暗色用 CSS `color-mix()` 動態求值，原生無 CSS 級聯，故 core 已把暗色表達式
**預計算為字面量**（如 `rgba(61,100,158,0.25)`、`#B1C1D8`）。後果（總綱 R-n6）：

> **改預設主色時，暗色預計算值不會自動跟隨**，必須重新人工計算並更新
> `lh-ui-core/tokens/color-dark.json`，同步重新整理《設計語言基線》。

## 令牌速查

完整令牌表（明/暗/說明）見 [lh-ui-core 令牌速查](https://www.lhgb.top/lhui/core/tokens/)，
或在 core 倉跑 `pnpm codegen` 後查看 `dist/docs/tokens.md`。元件級令牌對應見
[Button 令牌對照](/zh-TW/components/button#令牌對照)。

## 自訂預設（預留）

`LhTheme(colors = ...)` 支援傳入自訂色板，預設主題能力將在後續版本隨 core 的
主題預設令牌一起開放。
