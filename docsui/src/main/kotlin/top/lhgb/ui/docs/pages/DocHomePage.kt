package top.lhgb.ui.docs.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import top.lhgb.ui.docs.widgets.DocCard
import top.lhgb.ui.docs.widgets.DocDivider
import top.lhgb.ui.docs.widgets.DocHeading
import top.lhgb.ui.docs.widgets.DocProse
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconSize
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.tokens.LhTokens

/**
 * 文档首页：按分组列出全部页面入口（对齐网页版 sidebar 的三分组 + 指南）。
 *
 * 每个分组是一张卡片，组内条目用分割线分隔；条目行带 ripple 与 chevron-right，
 * 明确的 affordance + 够大的触控区域，移动端交互不「飘」。
 */
@Composable
fun DocHomePage(
  entries: List<top.lhgb.ui.docs.model.DocEntry>,
  onOpen: (String) -> Unit,
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = LhTokens.Spacing.md)
      .padding(bottom = LhTokens.Spacing.xxl),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.lg),
  ) {
    Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
      DocHeading("lh-ui-android 组件文档", level = 1)
      DocProse("原生血脉 · Kotlin + Jetpack Compose 组件库，令牌驱动、契约对齐、黄金用例守护。")
      DocProse("预览均为真 Compose 实时渲染，可交互。", secondary = true)
    }

    top.lhgb.ui.docs.model.DocGroup.entries.forEach { group ->
      val groupEntries = entries.filter { it.group == group }
      if (groupEntries.isEmpty()) return@forEach
      Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
        DocHeading(group.label, level = 2)
        DocCard {
          groupEntries.forEachIndexed { index, entry ->
            if (index > 0) DocDivider()
            DocHomeItem(entry.title, entry.lede) { onOpen(entry.id) }
          }
        }
      }
    }
  }
}

@Composable
private fun DocHomeItem(title: String, lede: String, onClick: () -> Unit) {
  val colors = top.lhgb.ui.theme.LhThemeColors
  val interactionSource = remember { MutableInteractionSource() }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(
        interactionSource = interactionSource,
        indication = ripple(color = colors.primary),
        onClick = onClick,
      )
      .padding(vertical = LhTokens.Spacing.md),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(LhTokens.Spacing.md),
  ) {
    Column(
      modifier = Modifier.weight(1f),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    ) {
      Text(
        text = title,
        color = colors.textPrimary,
        fontSize = LhTokens.Docs.Prose.fontSize,
        fontWeight = FontWeight(LhTokens.Typography.FontWeight.bold),
      )
      DocProse(lede, secondary = true)
    }
    // 右侧箭头：明确的「可点击」affordance
    LhIcon(
      name = "chevron-right",
      size = LhIconSize.medium,
      tone = LhIconTone.secondary,
      contentDescription = null,
    )
  }
}
