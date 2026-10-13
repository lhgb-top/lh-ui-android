# Cell 单元格

列表单元格，图标 / 标题 + 描述 / 右值 + 箭头的三段式布局，常与列表组合使用。

> 契约源：[`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> 黄金用例：[`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell)（6 条，已翻译为 JVM 单测 + Compose UI Test）

## 基本用法

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">标题文字</div>
      <div class="sim-cell__value">右侧值</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">带描述的标题</div>
        <div class="sim-cell__label">描述文字</div>
      </div>
      <div class="sim-cell__value">值</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>必填项</div>
      <div class="sim-cell__value">详细</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
LhCell(
  title = "带描述的标题",
  label = "描述文字",
  value = "值",
  isLink = true,
  onClick = { /* 跳转 */ },
)
```

## 必填与链接

`required` 在标题前绘制红色星号（`color.danger.default`）；`isLink` 在末尾绘制右箭头图标。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell 变体</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>必填</div>
      <div class="sim-cell__value">箭头 →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">无边框</div>
      <div class="sim-cell__value">值</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `title` | 标题（主文字） | `String?` | `null` |
| `modifier` | 修饰符 | `Modifier` | `Modifier` |
| `value` | 右侧值（次文字，右对齐） | `String?` | `null` |
| `label` | 标题下方的说明文字 | `String?` | `null` |
| `bordered` | 是否绘制底部分隔线（`color.border.lighter`） | `Boolean` | `true` |
| `clickable` | 是否可点击；为 true 时显示右侧指示箭头并响应点击 | `Boolean` | `false` |
| `required` | 是否在标题前显示必填红星（`color.danger.default`） | `Boolean` | `false` |
| `icon` | 左侧图标名（本地图标体系）；空时不绘制 | `String?` | `null` |
| `onClick` | 点击回调；`clickable` 为 false 时不触发 | `(() -> Unit)?` | `null` |
| `titleContent` | 标题内容（覆盖 title，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | 右侧值内容（覆盖 value，spec 的 value 插槽） | `@Composable (() -> Unit)?` | `null` |

`clickable` 同时控制「右侧指示箭头是否绘制」与「整行是否响应点击」——只有 `clickable = true` 且 `onClick` 非空时点击才触发。

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `cell.paddingVertical` | 纵向内边距（`spacing.md` 引用） |
| `cell.paddingHorizontal` | 横向内边距（`spacing.md`） |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | 标题 / 描述 / 值字号 |
| `cell.gap` | 图标与标题间距（`spacing.xs`） |
| `icon.size.medium` | 左侧图标与指示箭头尺寸 |
| `cell.borderWidth` | 底分隔线宽度 |
| `typography.lineHeight.primary` | 文字行高 |
| `color.text.{primary,secondary}` | 标题 / 描述 / 值颜色 |
| `color.danger.default` | 必填红星 |
| `color.border.lighter` | 底分隔线 |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `title-and-value-render` | 标题与右值渲染文字 | `LhCellTest.title_and_value_render`（Compose UI Test） |
| `label-renders-secondary-tone` | label 取次要文字色令牌 | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | 必填标记取危险色令牌 | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | 分隔线消费描边令牌 | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | 点击触发 onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | 禁用态不触发 | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # JVM 部分
./gradlew :components:cell:connectedAndroidTest              # 交互部分（需设备）
```
