# 主题与暗色

## 令牌模型

原生血脉的令牌真源在 `lh-ui-core/tokens/*.json`，codegen 生成 Android 产物：

| 产物 | 内容 | 暗色形态 |
| --- | --- | --- |
| `LhTokens` | 明色全量：Button 尺寸、Color 色板、Motion、Radius、Shadow、Spacing、Typography、ZIndex | — |
| `LhDarkTokens` | 仅 `Color` 子对象（**并列对象**模型） | 暗色只覆盖颜色，尺寸/动效/圆角暗色不变 |

> 这与 ios / harmony 一致（并列对象），与 C 端（宏重定义覆盖）、docs 端（浅合并 JSON）形态不同。
> 主题层据此设计：`LhColors` 只承载颜色，尺寸由组件直接取 `LhTokens`。

## LhColors

`LightLhColors` / `DarkLhColors` 把生成的两个 object 平铺为一份不可变色板：

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
  // LocalLhColors 注入 DarkLhColors，内部组件自动消费
}
```

无 `LhTheme` 包裹时回退 `LightLhColors`（缺省即明色）。

## 暗色预计算（重要）

web 血脉的暗色用 CSS `color-mix()` 动态求值，原生无 CSS 级联，故 core 已把暗色表达式
**预计算为字面量**（如 `rgba(61,100,158,0.25)`、`#B1C1D8`）。后果（总纲 R-n6）：

> **改预设主色时，暗色预计算值不会自动跟随**，必须重新人工计算并更新
> `lh-ui-core/tokens/color-dark.json`，同步刷新《设计语言基线》。

## 令牌速查

完整令牌表（明/暗/说明）见 [lh-ui-core 令牌速查](https://www.lhgb.top/lhui/core/tokens/)，
或在 core 仓跑 `pnpm codegen` 后查看 `dist/docs/tokens.md`。组件级令牌映射见
[Button 令牌对照](/components/button#令牌对照)。

## 自定义预设（预留）

`LhTheme(colors = ...)` 支持传入自定义色板，预设主题能力将在后续版本随 core 的
主题预设令牌一起开放。
