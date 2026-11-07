package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Радиокнопка",
  lede = "Управляемая радиокнопка; повторное нажатие в выбранном состоянии не снимает выбор (семантика одиночного выбора).",
  group = DocGroup.FORM,
  contract = "Источник контракта: `lh-ui-core/specs/radio.json` · Золотые сценарии: `cases/radio.json` (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Состояния и группа",
      prose = "Компонент не содержит логики группы; взаимное исключение вариантов поддерживается хозяином (для идентификации элемента в группе используется `value`).",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"Вариант 1\" to \"a\", \"Вариант 2\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "Размеры",
      prose = "Три размера кольца отображаются на токены `radio.size.*`; диаметр внутренней точки = размер × `radio.dotScale`.",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Выбран ли элемент (обязательный)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Вызывается при переходе из невыбранного в выбранное состояние; передаёт `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Отключён ли элемент", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Текст метки", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "Значение элемента (идентификатор в группе; сам компонент логики группы не содержит)", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Размер кольца", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Содержимое метки (переопределяет label, слот default из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("Значение", "Токен размера", "Диаметр внутренней точки"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small` (16dp)", "16dp × `radio.dotScale` (0.5)")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium` (20dp)", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large` (24dp)", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "Три размера кольца")),
    DocRow(cells = listOf("`radio.ringWidth`", "Толщина границы внешнего кольца")),
    DocRow(cells = listOf("`radio.dotScale`", "Доля внутренней точки относительно диаметра кольца (дробное значение)")),
    DocRow(cells = listOf("`radio.gap`", "Расстояние между кольцом и меткой")),
    DocRow(cells = listOf("`color.primary.default`", "Цвет границы и точки в выбранном состоянии")),
    DocRow(cells = listOf("`color.border.base`", "Цвет границы в невыбранном состоянии")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "Цвета границы / точки / текста в отключенном состоянии")),
    DocRow(cells = listOf("`color.text.primary`", "Цвет метки")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Размер шрифта метки")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "Нажатие в невыбранном состоянии вызывает onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "Нажатие в выбранном состоянии не вызывает колбэк", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "Отключенное состояние не вызывает изменений", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "Выбранное состояние потребляет токены основного цвета", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "Невыбранное состояние потребляет токен границы", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "Малый размер потребляет размерный токен small", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # JVM-часть",
    "./gradlew :components:radio:connectedAndroidTest             # Интерактивная часть (требуется устройство)",
  ),
)
