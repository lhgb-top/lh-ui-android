package top.lhgb.ui.docs.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import top.lhgb.ui.docs.model.DocTable
import top.lhgb.ui.tokens.LhTokens

/** 文档 UI 统一取色入口（明/暗由 LhTheme 切换，docs 令牌颜色全部 ref 到 color.*）。 */
private val code get() = LhTokens.Docs.Code
private val table get() = LhTokens.Docs.Table
private val heading get() = LhTokens.Docs.Heading
private val prose get() = LhTokens.Docs.Prose
private val card get() = LhTokens.Docs.Card

/** 从字体栈令牌取首个族名构造 Compose FontFamily（等宽代码字体）。 */
@Composable
fun rememberMonoFontFamily(): FontFamily = remember {
  val first = code.fontFamily.substringBefore(',').trim().trim('\'')
  FontFamily(android.graphics.Typeface.create(first, android.graphics.Typeface.NORMAL))
}

/** 标题阶：H1 页面标题 / H2 章节 / H3 小节。字号与颜色全部取令牌。 */
@Composable
fun DocHeading(text: String, level: Int = 2) {
  val fontSize = when (level) {
    1 -> heading.fontSizeH1
    2 -> heading.fontSizeH2
    else -> heading.fontSizeH3
  }
  Text(
    text = text,
    color = top.lhgb.ui.theme.LhThemeColors.textPrimary,
    fontSize = fontSize,
    fontWeight = FontWeight(LhTokens.Typography.FontWeight.bold),
    modifier = Modifier.padding(
      top = if (level == 1) LhTokens.Spacing.xs else LhTokens.Spacing.lg,
      bottom = LhTokens.Spacing.xs,
    ),
  )
}

/** 正文 / 次要说明。 */
@Composable
fun DocProse(text: String, secondary: Boolean = false) {
  Text(
    text = text,
    color = if (secondary) top.lhgb.ui.theme.LhThemeColors.textSecondary
    else top.lhgb.ui.theme.LhThemeColors.textRegular,
    fontSize = prose.fontSize,
    lineHeight = prose.fontSize * LhTokens.Typography.LineHeight.primary.toFloat(),
  )
}

/** 分割线：沿用 LhCell 的 drawBehind 范式，线宽取表格分割线令牌。 */
@Composable
fun DocDivider() {
  val colors = top.lhgb.ui.theme.LhThemeColors
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = LhTokens.Spacing.sm)
      .drawBehind {
        val y = size.height - table.borderWidth.toPx() / 2f
        drawLine(
          color = colors.borderLighter,
          start = Offset(0f, y),
          end = Offset(size.width, y),
          strokeWidth = table.borderWidth.toPx(),
          cap = StrokeCap.Square,
        )
      },
  )
}

/** 卡片：surface 底 + 描边 + 圆角，承载预览框等成组内容。 */
@Composable
fun DocCard(content: @Composable () -> Unit) {
  val shape = RoundedCornerShape(card.borderRadius)
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(shape)
      .background(top.lhgb.ui.theme.LhThemeColors.surface, shape)
      .padding(card.padding),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm),
  ) {
    content()
  }
}

/** 代码块：等宽字体 + 宣纸底 + 圆角，逐行渲染。 */
@Composable
fun DocCodeBlock(lines: List<String>) {
  val shape = RoundedCornerShape(code.borderRadius)
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(shape)
      .background(top.lhgb.ui.theme.LhThemeColors.backgroundDefault, shape)
      .padding(horizontal = code.paddingX, vertical = code.paddingY),
    verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
  ) {
    lines.forEach { line ->
      Text(
        text = line,
        color = top.lhgb.ui.theme.LhThemeColors.textRegular,
        fontSize = code.fontSize,
        fontFamily = rememberMonoFontFamily(),
        lineHeight = code.fontSize * LhTokens.Typography.LineHeight.primary.toFloat(),
      )
    }
  }
}

/**
 * 通用表格：表头行（页面底色）+ 数据行 + 横竖分割线。Props / 枚举 / 令牌 / 用例表复用。
 *
 * 移动端列宽策略（修复窄屏「表格被压没」）：列宽不按权重均分，而是先用
 * [rememberTextMeasurer] 量出每列的**自然宽度**（不限宽单行的最大值，含最小兜底），
 * 内容总宽放不下时整表横向滚动；放得下时再按权重把余量分掉，宽屏视觉与均分一致。
 */
@Composable
fun DocTable(tableData: DocTable) {
  val colors = top.lhgb.ui.theme.LhThemeColors
  val density = LocalDensity.current
  val textMeasurer = rememberTextMeasurer()
  val scrollState = rememberScrollState()
  val headers = tableData.headers

  // 单元格最小内容宽度兜底：两倍内边距 + 一个基础间距令牌，保证任何列都至少能放下几个字。
  val minCellWidth = table.cellPaddingX * 2 + LhTokens.Spacing.md
  val horizontalPaddingPx = with(density) { (table.cellPaddingX * 2).toPx() }
  val minCellWidthPx = with(density) { minCellWidth.toPx() }

  // 列自然宽度：表头与全部数据行逐格取最大值（不限宽单行的最大值）。
  val naturalCellWidths = remember(tableData, textMeasurer, density) {
    val widths = IntArray(headers.size) { 0 }
    headers.forEachIndexed { i, header ->
      widths[i] = maxOf(
        widths[i],
        textMeasurer.measure(
          AnnotatedString(header),
          style = TextStyle(fontSize = table.fontSize, fontWeight = FontWeight(LhTokens.Typography.FontWeight.bold)),
          constraints = Constraints(),
        ).size.width,
      )
    }
    tableData.rows.forEach { row ->
      row.cells.forEachIndexed { i, cell ->
        if (i >= headers.size) return@forEachIndexed
        widths[i] = maxOf(
          widths[i],
          textMeasurer.measure(
            AnnotatedString(cell),
            style = TextStyle(fontSize = table.fontSize),
            constraints = Constraints(),
          ).size.width,
        )
      }
    }
    widths.map { natural ->
      (natural + horizontalPaddingPx).coerceAtLeast(minCellWidthPx)
    }
  }

  BoxWithConstraints(modifier = Modifier.fillMaxWidth().padding(vertical = LhTokens.Spacing.xs)) {
    val available = constraints.maxWidth.toFloat().coerceAtLeast(0f)
    // 放得下：按权重把余量分掉（首列 1.4，其余 1.0），宽屏视觉与均分一致；
    // 放不下：保持自然宽度，整表横向滚动，绝不把列压到无法阅读。
    val cellWidths: List<Float> = if (naturalCellWidths.sum() <= available && headers.isNotEmpty()) {
      val weights = headers.map { if (it == headers.first()) 1.4f else 1f }
      val weightSum = weights.sum()
      val slack = available - naturalCellWidths.sum()
      naturalCellWidths.mapIndexed { i, w -> w + slack * weights[i] / weightSum }
    } else {
      naturalCellWidths
    }
    val tableWidthDp = with(density) { cellWidths.sum().toDp() }
    val cellWidthsDp = cellWidths.map { with(density) { it.toDp() } }

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(scrollState),
    ) {
      // 表头
      DocTableRow(
        cells = headers,
        cellWidths = cellWidthsDp,
        tableWidth = tableWidthDp,
        header = true,
      )
      // 数据行
      tableData.rows.forEach { row ->
        DocTableRow(
          cells = row.cells,
          cellWidths = cellWidthsDp,
          tableWidth = tableWidthDp,
          header = false,
          showTopLine = true,
        )
      }
      // 底部封口线
      if (tableData.rows.isNotEmpty()) {
        Box(
          modifier = Modifier
            .width(tableWidthDp)
            .drawBehind {
              val w = table.borderWidth.toPx()
              drawLine(
                color = colors.borderLighter,
                start = Offset(0f, size.height - w / 2f),
                end = Offset(size.width, size.height - w / 2f),
                strokeWidth = w,
                cap = StrokeCap.Square,
              )
            },
        )
      }
    }
  }
}

@Composable
private fun DocTableRow(
  cells: List<String>,
  cellWidths: List<Dp>,
  tableWidth: Dp,
  header: Boolean,
  showTopLine: Boolean = false,
) {
  val colors = top.lhgb.ui.theme.LhThemeColors
  Row(modifier = Modifier.width(tableWidth)) {
    cells.forEachIndexed { i, cellText ->
      val bg = if (header) colors.backgroundPage else colors.backgroundDefault
      Box(
        modifier = Modifier
          .width(cellWidths.getOrElse(i) { LhTokens.Docs.Table.cellPaddingX * 2 })
          .background(bg)
          .drawBehind {
            val w = table.borderWidth.toPx()
            if (showTopLine) {
              drawLine(
                color = colors.borderLighter,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = w,
                cap = StrokeCap.Square,
              )
            }
            drawLine(
              color = colors.borderLighter,
              start = Offset(size.width - w / 2f, 0f),
              end = Offset(size.width - w / 2f, size.height),
              strokeWidth = w,
              cap = StrokeCap.Square,
            )
          }
          .padding(horizontal = table.cellPaddingX, vertical = table.cellPaddingY),
        contentAlignment = Alignment.TopStart,
      ) {
        Text(
          text = cellText,
          color = if (header) colors.textPrimary else colors.textRegular,
          fontSize = table.fontSize,
          fontWeight = if (header) FontWeight(LhTokens.Typography.FontWeight.bold) else null,
          lineHeight = table.fontSize * LhTokens.Typography.LineHeight.primary.toFloat(),
        )
      }
    }
  }
}
