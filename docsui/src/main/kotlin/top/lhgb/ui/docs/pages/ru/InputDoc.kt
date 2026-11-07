package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Поле ввода",
  lede = "Управляемое однострочное поле ввода; поддерживает размеры, отключение, режим «только чтение», очистку, маскирование пароля и максимальную длину.",
  group = DocGroup.FORM,
  contract = "Источник контракта: `lh-ui-core/specs/input.json` · Золотые сценарии: `cases/input.json` (8 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Базовое использование",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"Введите содержимое\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Размеры и фокус",
      prose = "Три размера отображаются на токены `input.height` / `input.paddingHorizontal` / `input.fontSize`; в фокусе граница берёт `color.focus` и расширяется до `input.focusRingWidth`.",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Управляемое значение (обязательный)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Колбэк изменения значения (обязательный)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Плейсхолдер", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Тип ввода (открытый текст / пароль)", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "Размер", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Отключено ли поле", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "Режим «только чтение» (не редактируется, но без отключённого стиля)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "Показывать ли кнопку очистки", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Максимальная длина (превышение обрезается)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Колбэки фокуса / потери фокуса", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "Слоты префикса / суффикса", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("Значение", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small` (32dp)", "`input.paddingHorizontal.small` (12dp)", "`input.fontSize.small` (13sp)")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium` (40dp)", "`input.paddingHorizontal.medium` (16dp)", "`input.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`input.height.large` (48dp)", "`input.paddingHorizontal.large` (20dp)", "`input.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "Три высоты")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "Три горизонтальных внутренних отступа")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "Три размера шрифта")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "Скругление / толщина границы / толщина границы в фокусе")),
    DocRow(cells = listOf("`input.clearIconSize`", "Размер иконки очистки")),
    DocRow(cells = listOf("`color.focus`", "Цвет границы в фокусе")),
    DocRow(cells = listOf("`color.text.placeholder`", "Цвет плейсхолдера")),
    DocRow(cells = listOf("`color.border.base`", "Цвет границы по умолчанию")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Три цвета отключенного состояния")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "Ввод вызывает onValueChange", "`LhInputTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "Кнопка очистки передаёт пустое значение", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "В отключенном состоянии ввод невозможен", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "В режиме «только чтение» ввод невозможен", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "Цвет плейсхолдера берётся из токена плейсхолдера", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "Фокус потребляет токен фокуса (без фокуса — нейтральная граница)", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "Малый размер потребляет размерные токены small", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "Превышение максимальной длины обрезается", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # JVM-часть",
    "./gradlew :components:input:connectedAndroidTest              # Интерактивная часть (требуется устройство)",
  ),
)
