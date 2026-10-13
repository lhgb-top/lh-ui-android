# Dialog 对话框

模态浮层，用于重要确认或需要用户聚焦的内容。窗口级层级由 Compose `Dialog` 托管。

> 契约源：[`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> 黄金用例：[`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog)（8 条，已翻译为 JVM 单测 + Compose UI Test）

## 类型

`type` 决定确认按钮的颜色（1:1 映射到 `LhButtonType`），其余布局不变。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">标题</div>
      <div class="sim-dialog__content">这是一段正文内容，用于说明当前操作的影响。</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">取消</button>
        <button class="sim-btn sim-btn--primary">确认</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* 确认逻辑 */ },
  onCancel = {},
  onClose = { visible = false },
  title = "删除确认",
  content = "删除后不可恢复，确定删除吗？",
  type = LhDialogType.danger,
)
```

## 语义类型与遮罩

`type` 走 `danger` 时确认按钮取危险色；`closable` 控制点遮罩 / 按返回键是否触发 `onClose`。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">删除确认</div>
      <div class="sim-dialog__content">删除后不可恢复，确定删除吗？</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">取消</button>
        <button class="sim-btn sim-btn--danger">确认</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 模拟预览（Android / Compose 外观），非真机渲染</p>

确认 / 取消按钮点击后**先触发业务回调，再触发 `onClose`**，宿主据此把 `visible` 置为 false。

## API

### Props

| 属性 | 说明 | 类型 | 默认值 |
| --- | --- | --- | --- |
| `visible` | 是否显示；为 false 时不渲染任何内容 | `Boolean` | — |
| `onConfirm` | 点击确认按钮时触发 | `() -> Unit` | — |
| `onCancel` | 点击取消按钮时触发 | `() -> Unit` | — |
| `onClose` | 确认/取消后或（closable 时）点遮罩触发 | `() -> Unit` | — |
| `title` | 标题 | `String?` | `null` |
| `content` | 正文内容 | `String?` | `null` |
| `type` | 语义类型，决定确认按钮的类型（颜色） | `LhDialogType` | `default` |
| `confirmText` | 确认按钮文字；为空时不显示确认按钮 | `String` | `"确认"` |
| `cancelText` | 取消按钮文字；为空时不显示取消按钮 | `String` | `"取消"` |
| `closable` | 点遮罩 / 按返回键是否触发 onClose | `Boolean` | `false` |
| `titleContent` | 标题内容（覆盖 title，spec 的 title 插槽） | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | 正文内容（覆盖 content，spec 的 default 插槽） | `@Composable (() -> Unit)?` | `null` |
| `footer` | 底部按钮区（覆盖默认按钮，spec 的 footer 插槽） | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 修饰符（作用于内容卡） | `Modifier` | `Modifier` |

### LhDialogType

| 值 | 确认按钮类型 |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | 同名 `LhButtonType`，取对应语义色 |

## 令牌对照

| 令牌 | 用途 |
| --- | --- |
| `dialog.width` | 内容卡宽度（320dp） |
| `dialog.borderRadius` | 卡片圆角（跟随 `radius.base`） |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | 卡片内边距（跟随 `spacing.lg`） |
| `dialog.titleFontSize` / `dialog.contentFontSize` | 标题 / 正文字号 |
| `dialog.gap` | 标题、正文、按钮区之间的间距 |
| `zIndex.modal` / `zIndex.modalBackdrop` | 层级语义对齐（Compose `Dialog` 托管窗口级层级） |
| `color.background.overlay` | 遮罩色 |
| `color.background.default` | 卡片底色 |
| `color.text.primary` / `color.text.regular` | 标题 / 正文色 |
| `shadow.base` | 卡片投影（Compose 不支持 spread，取模糊半径做 elevation） |

## 黄金用例对照

| case id | 断言 | 本地测试 |
| --- | --- | --- |
| `confirm-emits-onconfirm` | 确认触发 onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose`（Compose UI Test） |
| `confirm-also-emits-onclose` | 确认后触发 onClose | 同上 |
| `cancel-emits-oncancel` | 取消触发 onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | closable 时点遮罩触发 onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | 非 closable 时吞掉遮罩点击 | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | 不可见时不渲染 | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | 标题渲染文字 | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | danger 类型确认按钮取危险色 | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # JVM 部分
./gradlew :components:dialog:connectedAndroidTest             # 交互部分（需设备）
```
