package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Средство выбора",
  lede = "Средство выбора с одним колесом, всплывающее снизу экрана: после остановки прокрутки выбирается вариант, ближайший к центральному индикатору.",
  group = DocGroup.OVERLAY,
  contract = "Источник контракта: `lh-ui-core/specs/picker.json` · Золотые сценарии: `cases/picker.json` (8 сценариев; кламп и отображение токенов переведены в JVM-модульные тесты, прокрутка и нажатия кнопок — в Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Использование",
      prose = "Всплывает снизу: маска сверху, панель снизу (со скруглением сверху). Панель инструментов состоит из трёх частей «Отмена / Заголовок / Подтвердить».",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"Вариант 1\", \"Вариант 2\", \"Вариант 3\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"Выберите\",",
        ")",
      ),
    ),
    DocSection(
      title = "Механизм выбора",
      prose = "Колесо — это прокручиваемый список с высотой каждого варианта `picker.itemHeight`; центральный индикатор — две горизонтальные линии на расстоянии itemHeight друг от друга.\nПосле остановки прокрутки индекс выбора вычисляется округлением пройденного расстояния; при изменении вызывается `onValueChange`.\nЕсли `value` выходит за пределы диапазона, он ограничивается (clamp) до диапазона вариантов средством `LhPickerDefaults.clamp`.",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Показывать ли компонент; при false не отрисовывает ничего", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "Массив текстов вариантов (одна колонка)", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Вызывается, когда прокрутка остановилась и выбранный вариант изменился; передаёт новый индекс", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Вызывается при нажатии кнопки подтверждения; передаёт текущий выбранный индекс", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Вызывается при нажатии кнопки отмены", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Вызывается после подтверждения/отмены; по этому сигналу хозяин устанавливает visible в false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "Индекс выбранного варианта (с 0); при выходе за пределы диапазона ограничивается (clamp)", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "Заголовок по центру панели инструментов", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "Текст кнопки подтверждения", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Текст кнопки отмены", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "Область содержимого колеса (переопределяет отрисовку вариантов по умолчанию, слот default из спецификации)", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "Панель инструментов (переопределяет подтверждение/заголовок/отмену по умолчанию, слот toolbar из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "Состояние списка колеса (по умолчанию предоставляется `rememberLhPickerListState(value, options)`; в тестах можно внедрить снаружи)", "`LazyListState`", "см. описание слева")),
    DocRow(cells = listOf("`modifier`", "Модификатор (применяется к панели)", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "Высота панели (260dp)")),
    DocRow(cells = listOf("`picker.itemHeight`", "Высота каждого варианта (40dp; также расстояние между линиями центрального индикатора)")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "Высота панели инструментов (44dp)")),
    DocRow(cells = listOf("`picker.itemFontSize`", "Размер шрифта вариантов")),
    DocRow(cells = listOf("`picker.borderRadius`", "Скругление верхних углов панели")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "Горизонтальный внутренний отступ панели (следует за `spacing.md`)")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Семантика иерархии (оконко-уровневую иерархию обеспечивает Compose `Popup`)")),
    DocRow(cells = listOf("`color.background.overlay`", "Цвет маски")),
    DocRow(cells = listOf("`color.background.default`", "Цвет фона панели")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Цвета заголовка / вариантов")),
    DocRow(cells = listOf("`color.border.lighter`", "Цвет линий центрального индикатора")),
    DocRow(cells = listOf("`color.primary.default`", "Цвет кнопки подтверждения")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "Подтверждение передаёт текущий индекс + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "После подтверждения вызывается onClose", "То же")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Отмена вызывает onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "Прокрутка изменяет выбор и вызывает onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "Отрицательное значение ограничивается (clamp) до 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "Значение за пределами диапазона ограничивается (clamp) до последнего варианта", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "Заголовок отрисовывает текст", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "В скрытом состоянии ничего не отрисовывается", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # JVM-часть",
    "./gradlew :components:picker:connectedAndroidTest               # Интерактивная часть (требуется устройство)",
  ),
)
