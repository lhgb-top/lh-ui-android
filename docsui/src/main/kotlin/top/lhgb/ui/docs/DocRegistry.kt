package top.lhgb.ui.docs

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.locale.LhAppLocale
import top.lhgb.ui.docs.model.DocGroup

/**
 * 文档页面注册表：数据驱动，新增组件只在生成链路加一条（docs md →
 * `scripts/gen-doc-pages.py` → pages/&lt;lang&gt;/ 页面数据）。
 *
 * 页面数据按语言解析：[all]/[byId] 依据 [LhAppLocale] 当前语言选取对应语言的
 * DocEntry 列表（回退中文）。分组为结构性 UI，跨语言共享 [groups]。
 */
object DocRegistry {

  /** 当前语言的全部页面（顺序：指南 → 基础 → 表单 → 反馈与层叠，对齐网页版 sidebar）。 */
  fun all(context: android.content.Context): List<DocEntry> =
    top.lhgb.ui.docs.pages.docEntriesFor(LhAppLocale.code(context))

  /** 按 id 查当前语言的页；找不到返回 null（调用方负责兜底）。 */
  fun byId(context: android.content.Context, id: String): DocEntry? =
    all(context).firstOrNull { it.id == id }

  /** 全部分组（顺序即首页渲染顺序）。 */
  val groups: List<DocGroup> = DocGroup.entries
}
