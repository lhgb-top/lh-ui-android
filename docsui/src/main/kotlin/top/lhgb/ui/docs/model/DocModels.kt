package top.lhgb.ui.docs.model

import androidx.compose.runtime.Composable

/**
 * 文档分组：对齐网页版 sidebar 的三分组 + 指南分组。
 */
enum class DocGroup(val label: String) {
  GUIDE("指南"),
  BASIC("基础组件"),
  FORM("表单组件"),
  OVERLAY("反馈与层叠"),
}

/** 通用表格行：cells 数量必须与表头数量一致。 */
data class DocRow(val cells: List<String>)

/** 通用表格：Props / 枚举 / 令牌对照 / 黄金用例全部复用此结构。 */
data class DocTable(
  val title: String,
  val headers: List<String>,
  val rows: List<DocRow>,
)

/**
 * 文档页的一个示例段：标题 + 散文 + 实时预览（真 Compose 渲染，可交互）。
 *
 * [preview] 运行在 [DocPreviewScope] 中，可通过 [DocPreviewScope.showToast] 弹出全局
 * Toast 做点击反馈——与网页版的「sim-phone + sim-note」同构，但预览是真实组件。
 */
data class DocSection(
  val title: String,
  val prose: String,
  val preview: (@Composable DocPreviewScope.() -> Unit)? = null,
  /** 段落内的 kotlin 代码示例（网页版 ```kotlin 块），没有则为空。 */
  val code: List<String> = emptyList(),
)

/**
 * 预览作用域：给实时预览提供宿主能力。
 *
 * - [showToast]：弹出全局 Toast 做点击反馈；
 * - [setToastPosition]：切换宿主全局 Toast 的定位（Toast 文档页的「定位」段需要）。
 */
class DocPreviewScope(
  val showToast: (String) -> Unit,
  val setToastPosition: (top.lhgb.ui.toast.LhToastPosition) -> Unit = {},
)

/**
 * 一个组件 / 指南页的完整描述。数据驱动：新增组件只在 [DocRegistry] 加一条。
 */
data class DocEntry(
  val id: String,
  val title: String,
  val lede: String,
  val group: DocGroup,
  /** 契约源引用，如「specs/button.json · cases/button.json（8 条）」。 */
  val contract: String? = null,
  val sections: List<DocSection> = emptyList(),
  /** API 小节下的 Props 表（固定 4 列：属性 / 说明 / 类型 / 默认值）。 */
  val props: List<DocRow> = emptyList(),
  /** 枚举 / 状态机 / 注册表等附加表。 */
  val enums: List<DocTable> = emptyList(),
  /** 令牌对照表（2 列：令牌 / 用途）。 */
  val tokens: List<DocRow> = emptyList(),
  /** 黄金用例对照表（3 列：case id / 断言 / 本地测试）。 */
  val cases: List<DocRow> = emptyList(),
  /** 测试命令块（gradlew 行）。 */
  val commands: List<String> = emptyList(),
)
