package top.lhgb.ui.text

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import top.lhgb.ui.theme.LhColors
import top.lhgb.ui.theme.LhThemeColors

/**
 * 文本组件：消费全局排版令牌的展示型原语。
 *
 * props 与 `lh-ui-core/specs/text.json` 逐字对齐；行为遵循 `cases/text.json`
 * 的 5 条黄金用例（已翻译为 JVM 单测）。
 *
 * spec 提供默认插槽，`content` 字符串形态即其便捷表达；文本内容直接渲染，
 * 无富文本 / 换行转义。
 *
 * @param content 文本内容
 * @param modifier 修饰符
 * @param level 字号级别，映射到 typography.fontSize.<level> 令牌
 * @param weight 字重，映射到 typography.fontWeight.<weight> 令牌
 * @param tone 文字色调，映射到 color.text.<tone> 或 color.selected 令牌
 * @param lhAlign 水平对齐方式（Compose 的 TextAlign 映射）
 */
@Composable
fun LhText(
  content: String = "",
  modifier: Modifier = Modifier,
  level: LhTextLevel = LhTextLevel.base,
  weight: LhTextWeight = LhTextWeight.primary,
  tone: LhTextTone = LhTextTone.primary,
  lhAlign: LhTextAlign = LhTextAlign.start,
) {
  val colors: LhColors = LhThemeColors
  val appearance = remember(level, weight, tone, colors) {
    LhTextDefaults.resolve(level, weight, tone, colors)
  }

  val textAlign = when (lhAlign) {
    LhTextAlign.start -> TextAlign.Start
    LhTextAlign.center -> TextAlign.Center
    LhTextAlign.end -> TextAlign.End
  }

  Text(
    text = content,
    color = appearance.textColor,
    fontSize = appearance.fontSize,
    fontWeight = FontWeight(appearance.fontWeight),
    lineHeight = appearance.lineHeight,
    textAlign = textAlign,
    // spec：layout 高度 = 字号 × 行高；用 height 锁定行盒，align 在其内对齐
    // lineHeight 是 TextUnit（sp 语义），height 需要 Dp，经 LocalDensity 换算
    modifier = modifier
      .height(with(LocalDensity.current) { appearance.lineHeight.toDp() })
      .semantics { contentDescription = content },
  )
}
