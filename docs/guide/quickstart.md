# 快速开始

## 最小示例

```kotlin
import com.lh.ui.button.LhButton
import com.lh.ui.button.LhButtonType
import com.lh.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      onClick = { /* 处理点击 */ },
    ) {
      Text("主要按钮")
    }
  }
}
```

`LhTheme` 是主题容器，内部所有 Lh 组件自动消费令牌；不包裹时回退明色色板。

## 类型与尺寸

```kotlin
// 六种类型
LhButton(type = LhButtonType.Default, onClick = {}) { Text("默认") }
LhButton(type = LhButtonType.Primary, onClick = {}) { Text("主要") }
LhButton(type = LhButtonType.Success, onClick = {}) { Text("成功") }
LhButton(type = LhButtonType.Warning, onClick = {}) { Text("警告") }
LhButton(type = LhButtonType.Danger, onClick = {}) { Text("危险") }
LhButton(type = LhButtonType.Info, onClick = {}) { Text("信息") }

// 三种尺寸
LhButton(size = LhButtonSize.Small, onClick = {}) { Text("小型") }
LhButton(size = LhButtonSize.Medium, onClick = {}) { Text("中型") }
LhButton(size = LhButtonSize.Large, onClick = {}) { Text("大型") }
```

## 状态

```kotlin
// 禁用：不响应点击，呈现禁用样式
LhButton(disabled = true, onClick = {}) { Text("禁用") }

// 加载中：显示指示器，不触发 onClick
LhButton(loading = true, onClick = {}) { Text("加载中") }

// 两者共存：按禁用优先处理（黄金用例 disabled-precedes-loading）
LhButton(disabled = true, loading = true, onClick = {}) { Text("禁用优先") }
```

## 图标与花纹底

```kotlin
// 图标名按本地图标体系解析（见 LhButtonIcons，未登记名不绘制）
LhButton(type = LhButtonType.Primary, icon = "search", onClick = {}) { Text("搜索") }

// 国风花纹底纹（消费 color.decoration 令牌）
LhButton(type = LhButtonType.Primary, bgPattern = true, onClick = {}) { Text("花纹底") }
```

## 明暗主题

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // 内部所有组件自动切换暗色令牌（core 预计算值，无运行时调色）
}
```

## 组件示例预览

原生 UI 无法在浏览器实时渲染，本站用**内联静态 HTML 手机框模拟器**展示外观：

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">默认按钮</button>
      <button class="sim-btn sim-btn--primary">主要按钮</button>
      <button class="sim-btn sim-btn--success">成功</button>
      <button class="sim-btn sim-btn--warning">警告</button>
      <button class="sim-btn sim-btn--danger">危险</button>
      <button class="sim-btn sim-btn--info">信息</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">小型</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">大型</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled">禁用</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading">
        <span class="sim-btn__spinner"></span>加载中
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>搜索</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">花纹底</button>
    </div>
  </div>
</div>
<p class="sim-note">Android（Compose）外观的 HTML 模拟预览，非真机渲染；样式值全部取自 core 令牌快照，与真机同源同值。真机外观以 <code>@Preview</code> 与设备截图为准。</p>

> 模拟器的暗色与站点联动：右上角切换主题，手机框内颜色即变（暗色令牌由 core 预计算）。
