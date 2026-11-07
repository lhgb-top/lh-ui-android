package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Текстовая область",
  lede = "Управляемая многострочная область ввода; поддерживает число строк, подсчёт символов и максимальную длину.",
  group = DocGroup.FORM,
  contract = "Источник контракта: `lh-ui-core/specs/textarea.json` · Золотые сценарии: `cases/textarea.json` (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Базовое использование",
      prose = "Минимальная высота = количество строк × межстрочный интервал × размер шрифта; при превышении появляется вертикальная прокрутка.",
      preview = { DocTextareaBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhTextarea(",
        "  value = value,",
        "  rows = 3,",
        "  showCount = true,",
        "  maxlength = 200,",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Подсчёт символов",
      prose = "Если `showCount` равно true, в правом нижнем углу отрисовывается счётчик (`textarea.countFontSize`, цвет `text.secondary`); `maxlength` обрезает ввод.",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Управляемое значение (обязательный)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Колбэк изменения значения (обязательный)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Плейсхолдер", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "Количество строк (определяет минимальную высоту)", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "Отключена ли область", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "Режим «только чтение»", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "Показывать ли подсчёт символов", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Максимальная длина (превышение обрезается)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Колбэки фокуса / потери фокуса", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "Внутренние отступы (`spacing.md`)")),
    DocRow(cells = listOf("`textarea.fontSize`", "Размер шрифта (`typography.fontSize.base`)")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "Скругление / толщина границы / толщина границы в фокусе")),
    DocRow(cells = listOf("`textarea.countFontSize`", "Размер шрифта счётчика (`typography.fontSize.extraSmall`)")),
    DocRow(cells = listOf("`textarea.minHeight`", "Базис минимальной высоты")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Межстрочный интервал (для пересчёта количества строк)")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "Цвета текста / счётчика / плейсхолдера")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "Цвета границы в фокусе / по умолчанию")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Три цвета отключенного состояния")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "Ввод вызывает onValueChange", "`LhTextareaTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-input`", "В отключенном состоянии ввод невозможен", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "Счётчик отрисовывает «n / max»", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "Без maxlength отрисовывается только длина", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "Фокус потребляет токен фокуса", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "Превышение максимальной длины обрезается", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # JVM-часть",
    "./gradlew :components:textarea:connectedAndroidTest           # Интерактивная часть (требуется устройство)",
  ),
)
