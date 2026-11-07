package top.lhgb.ui.docs.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import top.lhgb.ui.docs.model.DocTable
import top.lhgb.ui.icon.LhIcon
import top.lhgb.ui.icon.LhIconSize
import top.lhgb.ui.icon.LhIconTone
import top.lhgb.ui.tokens.LhTokens

/** 文档 UI 统一取色入口（明/暗由 LhTheme 切换，docs 令牌颜色全部 ref 到 color.*）。 */
private val code get() = LhTokens.Docs.Code
private val table get() = LhTokens.Docs.Table
private val heading get() = LhTokens.Docs.Heading
private val prose get() = LhTokens.Docs.Prose
private val card get() = LhTokens.Docs.Card

/**
 * 等宽代码字体：Android 端对 [Docs.Code.fontFamily] 字体栈的解析。
 *
 * Gotcha：`Typeface.create(familyName, …)` 对不存在的族名静默回退到默认字体
 * （Source Code Pro 在 Android 系统字体中不存在），程序上无法逐族探测可用性；
 * 平台等宽（`FontFamily.Monospace`）是字体栈在本端的正确兜底解析。
 */
@Composable
fun rememberMonoFontFamily(): FontFamily = remember { FontFamily.Monospace }

/**
 * 国风衬线标题字体：Android 端对 `Typography.fontFamily` 字体栈的解析。
 *
 * Gotcha：与等宽字体同理，`Typeface.create("Noto Serif SC", …)` 无法探测族名是否
 * 存在（不存在时静默回退默认无衬线）；而 Compose 的 `FontFamily.Serif` 在 Android
 * 恰好解析到系统的 serif 家族（含 CJK 的 Noto Serif），即字体栈「首个可用族」的
 * 平台等价物。仅用于标题阶，正文保持无衬线保证小字可读性。
 */
@Composable
fun rememberLhSerifFontFamily(): FontFamily = remember { FontFamily.Serif }

/** 标题阶：H1 页面标题 / H2 章节 / H3 小节。字号与颜色全部取令牌，标题用国风衬线。 */
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
    fontFamily = rememberLhSerifFontFamily(),
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

/**
 * 代码块：等宽字体 + 宣纸底 + 圆角，逐行渲染。
 *
 * - 长代码行**不软换行**（softWrap = false），整块横向滚动，窄屏格式不被压折；
 *   内容放得下时自然不滚（宽屏短代码无多余滚动条）。
 * - [onCopied] 非空时右上角提供「一键复制」小图标按钮：一键写入全部代码行
 *   （换行拼接）到系统剪贴板，并回调一次反馈（如弹全局 Toast「已复制」）。
 */
@OptIn(ExperimentalLayoutApi::class)
@Suppress("DEPRECATION") // LocalClipboardManager：其替代品 LocalClipboard.setText 是 suspend + ClipEntry，
// 点击即复制的场景用同步 API 足够，避免引入协程作用域
@Composable
fun DocCodeBlock(lines: List<String>, onCopied: (() -> Unit)? = null) {
  val colors = top.lhgb.ui.theme.LhThemeColors
  val clipboard = LocalClipboardManager.current
  val shape = RoundedCornerShape(code.borderRadius)
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .clip(shape)
      .background(colors.backgroundDefault, shape),
  ) {
    if (onCopied != null) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        val interactionSource = remember { MutableInteractionSource() }
        Box(
          modifier = Modifier
            .minimumInteractiveComponentSize()
            .clickable(
              interactionSource = interactionSource,
              indication = ripple(color = colors.primary),
            ) {
              clipboard.setText(AnnotatedString(lines.joinToString("\n")))
              onCopied()
            }
            .semantics { role = Role.Button },
          contentAlignment = Alignment.Center,
        ) {
          LhIcon(
            name = "content-copy",
            lhSize = LhIconSize.small,
            tone = LhIconTone.secondary,
            contentDescription = stringResource(top.lhgb.ui.docsui.R.string.doc_a11y_copy),
          )
        }
      }
    }
    Column(
      modifier = Modifier
        .horizontalScroll(scrollState)
        .padding(horizontal = code.paddingX, vertical = code.paddingY),
      verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs),
    ) {
      lines.forEach { line ->
        Text(
          text = line,
          color = colors.textRegular,
          fontSize = code.fontSize,
          fontFamily = rememberMonoFontFamily(),
          softWrap = false,
          lineHeight = code.fontSize * LhTokens.Typography.LineHeight.primary.toFloat(),
        )
      }
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

  // 表标题：「Props」「LhButtonType」等，此前该字段未被渲染，多表并存时无法区分
  if (tableData.title.isNotEmpty()) {
    DocHeading(tableData.title, level = 3)
  }

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
    // 窄屏（手机竖屏）：表格折叠为字段卡片列表——每行一张卡，表头作字段小标签，
    // 竖排平铺，不再横向滚动；宽屏（平板/横屏）保持表格形态。
    if (maxWidth < table.narrowBreakpoint) {
      DocTableCardList(tableData)
      return@BoxWithConstraints
    }
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

/**
 * 窄屏表格形态：每行数据折叠为一张字段卡片。
 *
 * 卡片内按列竖排：表头字段名作小标签（次要文字色 + 表格字号 + 粗体），
 * 值跟在标签下方正常排布。所有颜色 / 尺寸 / 圆角取令牌，与 [DocCard] 同范式。
 */
@Composable
private fun DocTableCardList(tableData: DocTable) {
  val colors = top.lhgb.ui.theme.LhThemeColors
  Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.sm)) {
    tableData.rows.forEach { row ->
      DocCard {
        row.cells.forEachIndexed { i, cell ->
          val label = tableData.headers.getOrElse(i) { null }
          Column(verticalArrangement = Arrangement.spacedBy(LhTokens.Spacing.xs)) {
            if (label != null) {
              Text(
                text = label,
                color = colors.textSecondary,
                fontSize = table.fontSize,
                fontWeight = FontWeight(LhTokens.Typography.FontWeight.bold),
              )
            }
            Text(
              text = cell,
              color = colors.textRegular,
              fontSize = table.fontSize,
              lineHeight = table.fontSize * LhTokens.Typography.LineHeight.primary.toFloat(),
            )
          }
        }
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
