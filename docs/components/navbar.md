# Navbar 导航栏

页面顶部标题栏，左 / 中 / 右三段布局，常用于返回与标题展示。

> 契约源：[`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> 黄金用例：[`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar)（6 条，令牌映射翻译为 JVM 单测，左右点击翻译为 Compose UI Test）

## 用法

三段等宽（各 `weight(1f)`），标题水平居中于可用宽度；默认绘制底部分隔线。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>返回</div>
      <div class="sim-navbar__title">页面标题</div>
      <div class="sim-navbar__side sim-navbar__side--right">更多</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
LhNavbar(
  title = "页面标题",
  leftIcon = "arrow-left",
  leftText = "返回",
  rightText = "更多",
  onLeftClick = { /* 返回 */ },
  onRightClick = { /* 更多 */ },
)
```

## 左右内容

文字与图标可同时存在（图标在左、文字在右，间距 `spacing.xs`）；**某一侧没有任何内容时不响应点击**
（黄金用例 `no-left-content-no-onleftclick`：该节点连可点击语义都不存在）。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar 变体</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">仅图标</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">文字</div>
      <div class="sim-navbar__title">无分隔线</div>
      <div class="sim-navbar__side sim-navbar__side--right">编辑</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `title` | 中间标题 | `String?` | `null` |
| `onLeftClick` | 点击左侧区域时触发 | `() -> Unit` | `{}` |
| `onRightClick` | 点击右侧区域时触发 | `() -> Unit` | `{}` |
| `leftText` | 左侧按钮文字；与 leftIcon 可共存 | `String?` | `null` |
| `leftIcon` | 左侧图标名（本地图标体系，如 `"arrow-left"`） | `String?` | `null` |
| `rightText` | 右侧按钮文字；与 rightIcon 可共存 | `String?` | `null` |
| `rightIcon` | 右侧图标名 | `String?` | `null` |
| `bordered` | 是否绘制底部分隔线 | `Boolean` | `true` |
| `titleContent` | 标题内容（覆盖 title，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | 左侧内容（覆盖 leftText/leftIcon，spec 的 left 插槽） | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | 右侧内容（覆盖 rightText/rightIcon，spec 的 right 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |

某一侧的 `*Content` / `*Text` / `*Icon` 全为空时，该侧不可点击也不绘制任何内容。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `navbar.height` | 导航栏高度（44dp） |
| `navbar.paddingHorizontal` | 左右横向内边距（跟随 `spacing.md`） |
| `navbar.titleFontSize` | 标题与两侧文字字号 |
| `navbar.iconSize` | 两侧图标尺寸（跟随 `icon.size.medium`） |
| `navbar.borderWidth` | 底部分隔线宽度 |
| `zIndex.sticky` | 吸顶层级语义对齐 |
| `color.background.default` | 底色 |
| `color.text.primary` / `color.text.regular` | 标题 / 两侧文字色 |
| `color.border.lighter` | 底部分隔线色 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | 点击左侧触发 onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick`（Compose UI Test） |
| `rightclick-emits-onrightclick` | 点击右侧触发 onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | 无左侧内容时不触发 | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | 标题渲染文字 | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | 分隔线消费描边令牌 | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | 高度消费 navbar 令牌 | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # JVM 部分
./gradlew :components:navbar:connectedAndroidTest                # 交互部分（需设备）
```
