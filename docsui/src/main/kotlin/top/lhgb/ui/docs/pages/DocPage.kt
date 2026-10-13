package top.lhgb.ui.docs.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocPreviewScope
import top.lhgb.ui.docs.widgets.DocCodeBlock
import top.lhgb.ui.docs.widgets.DocDivider
import top.lhgb.ui.docs.widgets.DocHeading
import top.lhgb.ui.docs.widgets.DocPreviewFrame
import top.lhgb.ui.docs.widgets.DocProse
import top.lhgb.ui.docs.widgets.DocTable
import top.lhgb.ui.tokens.LhTokens

/**
 * 把一个 [DocEntry] 渲染为完整文档页：标题 → 契约源 → 示例段（散文 + 实时预览）
 * → API（Props + 附加表）→ 令牌对照 → 黄金用例 → 测试命令。
 *
 * 结构 1:1 对齐网页版 docs/components 各页的章节骨架。
 */
@Composable
fun DocPage(
  entry: DocEntry,
  showToast: (String) -> Unit,
  setToastPosition: (top.lhgb.ui.toast.LhToastPosition) -> Unit = {},
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = LhTokens.Spacing.md)
      .padding(bottom = LhTokens.Spacing.xxl),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    DocHeading(entry.title, level = 1)
    DocProse(entry.lede)
    if (entry.contract != null) {
      DocProse("契约源：${entry.contract}", secondary = true)
    }
    DocDivider()

    // 示例段
    entry.sections.forEach { section ->
      DocHeading(section.title, level = 2)
      DocProse(section.prose)
      if (section.preview != null) {
        DocPreviewFrame(scene = section.title) {
          val scope = DocPreviewScope(showToast, setToastPosition)
          section.preview(scope)
        }
      }
      if (section.code.isNotEmpty()) {
        DocCodeBlock(section.code)
      }
    }

    // API
    if (entry.props.isNotEmpty() || entry.enums.isNotEmpty()) {
      DocHeading("API", level = 2)
      if (entry.props.isNotEmpty()) {
        DocTable(
          top.lhgb.ui.docs.model.DocTable(
            title = "Props",
            headers = listOf("属性", "说明", "类型", "默认值"),
            rows = entry.props,
          ),
        )
      }
      entry.enums.forEach { DocTable(it) }
    }

    // 令牌对照
    if (entry.tokens.isNotEmpty()) {
      DocHeading("令牌对照", level = 2)
      DocTable(
        top.lhgb.ui.docs.model.DocTable(
          title = "令牌",
          headers = listOf("令牌", "用途"),
          rows = entry.tokens,
        ),
      )
    }

    // 黄金用例对照
    if (entry.cases.isNotEmpty()) {
      DocHeading("黄金用例对照", level = 2)
      DocTable(
        top.lhgb.ui.docs.model.DocTable(
          title = "用例",
          headers = listOf("case id", "断言", "本地测试"),
          rows = entry.cases,
        ),
      )
    }

    // 测试命令
    if (entry.commands.isNotEmpty()) {
      DocHeading("本地验证", level = 2)
      DocCodeBlock(entry.commands)
    }
  }
}
