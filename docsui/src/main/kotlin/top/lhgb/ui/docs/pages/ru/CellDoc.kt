package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Ячейка",
  lede = "Ячейка списка с трёхчастной компоновкой «иконка / заголовок + описание / правое значение + стрелка»; часто используется в сочетании со списками.",
  group = DocGroup.BASIC,
  contract = "Источник контракта: `lh-ui-core/specs/cell.json` · Золотые сценарии: `cases/cell.json` (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Базовое использование",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"Заголовок с описанием\",",
        "  label = \"Текст описания\",",
        "  value = \"Значение\",",
        "  isLink = true,",
        "  lhOnClick = { /* переход */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Обязательное поле и ссылка",
      prose = "`required` рисует красную звёздочку перед заголовком (`color.danger.default`); `isLink` рисует в конце значок стрелки вправо.",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Заголовок (основной текст)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "Правое значение (вторичный текст, выравнивание по правому краю)", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "Поясняющий текст под заголовком", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Рисовать ли разделительную линию снизу (`color.border.lighter`)", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "Доступна ли ячейка для нажатия; при true показывает индикатор-стрелку справа и реагирует на нажатия", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "Показывать ли красную звезду обязательного поля перед заголовком (`color.danger.default`)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Имя левой иконки (локальная система иконок); если пусто — не отрисовывается", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "Колбэк нажатия; не вызывается, если `clickable` равно false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "Содержимое заголовка (переопределяет title, слот default из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "Содержимое правого значения (переопределяет value, слот value из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "Вертикальный внутренний отступ (ссылка на `spacing.md`)")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "Горизонтальный внутренний отступ (`spacing.md`)")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "Размеры шрифта заголовка / описания / значения")),
    DocRow(cells = listOf("`cell.gap`", "Расстояние между иконкой и заголовком (`spacing.xs`)")),
    DocRow(cells = listOf("`icon.size.medium`", "Размер левой иконки и индикаторной стрелки")),
    DocRow(cells = listOf("`cell.borderWidth`", "Толщина разделительной линии снизу")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Межстрочный интервал текста")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "Цвета заголовка / описания / значения")),
    DocRow(cells = listOf("`color.danger.default`", "Красная звезда обязательного поля")),
    DocRow(cells = listOf("`color.border.lighter`", "Разделительная линия снизу")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "Заголовок и правое значение отрисовывают текст", "`LhCellTest.title_and_value_render` (Compose UI Test)")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label берёт токен вторичного цвета текста", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "Маркер обязательного поля берёт токен опасного цвета", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "Разделительная линия потребляет токен границы", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "Нажатие вызывает onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "Отключенное состояние не вызывает", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # JVM-часть",
    "./gradlew :components:cell:connectedAndroidTest              # Интерактивная часть (требуется устройство)",
  ),
)
