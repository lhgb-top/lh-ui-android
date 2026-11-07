package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Флажок",
  lede = "Управляемый флажок; поддерживает неопределённое состояние (indeterminate) и отключение.",
  group = DocGroup.FORM,
  contract = "Источник контракта: `lh-ui-core/specs/checkbox.json` · Золотые сценарии: `cases/checkbox.json` (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Состояния",
      prose = "Выбранное / неопределённое состояния — залитый фон основного цвета + индикатор onPrimary (галочка / горизонтальная черта); невыбранное состояние — только граница.",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"Согласен с условиями\",",
        ")",
      ),
    ),
    DocSection(
      title = "Размеры",
      prose = "Три размера квадратика отображаются на токены `checkbox.size.*`.",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Выбран ли флажок (обязательный)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "Колбэк изменения состояния; передаёт новое состояние", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "Находится ли флажок в неопределённом состоянии", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "Отключён ли флажок", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "Текст метки", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "Размер квадратика", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "Содержимое метки (переопределяет label, слот default из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("Значение", "Токен размера"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large` (24dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "Три размера квадратика")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "Скругление и толщина границы квадратика")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "Толщина галочки / горизонтальной черты")),
    DocRow(cells = listOf("`checkbox.gap`", "Расстояние между квадратиком и меткой")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Цвет фона выбранного состояния / цвет индикатора")),
    DocRow(cells = listOf("`color.border.base`", "Цвет границы невыбранного состояния")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "Три цвета отключенного состояния")),
    DocRow(cells = listOf("`color.text.primary`", "Цвет метки")),
    DocRow(cells = listOf("`typography.fontSize.base`", "Размер шрифта метки")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "Нажатие переключает состояние и emits onChange", "`LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "Нажатие в выбранном состоянии снимает выбор", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`tap-indeterminate-emits-true`", "Нажатие в неопределённом состоянии переводит в выбранное", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "Отключенное состояние не вызывает изменений", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "Выбранное состояние потребляет токены основного цвета", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "Невыбранное состояние потребляет токен границы", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # JVM-часть",
    "./gradlew :components:checkbox:connectedAndroidTest           # Интерактивная часть (требуется устройство)",
  ),
)
