package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTagPlainPreview
import top.lhgb.ui.docs.previews.DocTagSizeAndMarkPreview
import top.lhgb.ui.docs.previews.DocTagTypesPreview

val tagDoc = DocEntry(
  id = "tag",
  title = "Тег",
  lede = "Тег с семантическими цветами; поддерживает плоский (plain) режим, полностью скруглённую метку (mark) и кнопку закрытия.",
  group = DocGroup.BASIC,
  contract = "Источник контракта: `lh-ui-core/specs/tag.json` · Золотые сценарии: `cases/tag.json` (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Типы",
      prose = "Шесть семантических типов определяют цвет фона и текста.",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"Успех\") }",
      ),
    ),
    DocSection(
      title = "Плоский (plain)",
      prose = "С `plain` тег получает прозрачный фон + семантические границу и текст.",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "Размеры и метка",
      prose = "Три размера отображаются на токены `tag.height` / `tag.paddingHorizontal` / `tag.fontSize`; `mark` получает полное скругление радиусом в половину высоты.",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "Текст тега (удобная запись; альтернатива `content`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Семантический тип", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Размер", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "Плоский ли тег (прозрачный фон + семантическая граница)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "Полное ли скругление (половина высоты)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "Показывать ли кнопку закрытия", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "Колбэк закрытия; не вызывается, если `closable` равно false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "Содержимое тега (переопределяет text, слот default из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("Значение", "Фон (не plain)", "Граница/текст (plain)"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + граница `color.border.base`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("Значение", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small` (20dp)", "`tag.paddingHorizontal.small` (8dp)", "`tag.fontSize.small` (12sp)")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium` (24dp)", "`tag.paddingHorizontal.medium` (10dp)", "`tag.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`tag.height.large` (28dp)", "`tag.paddingHorizontal.large` (12dp)", "`tag.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "Три высоты")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "Три горизонтальных внутренних отступа")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "Три размера шрифта")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "Скругление и толщина границы")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Семантические цвета фона / границы / текста")),
    DocRow(cells = listOf("`color.onPrimary`", "Цвет текста для не-plain")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Три цвета отключенного состояния")),
    DocRow(cells = listOf("`spacing.xs`", "Расстояние между кнопкой закрытия и текстом")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "Тип по умолчанию берёт нейтральный фон + основной цвет текста + базовую границу", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "Основной тип потребляет токены основного цвета", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain потребляет токены семантического светлого фона", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "Малый размер потребляет токены small (обратное утверждение: large не применён)", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "При round скругление равно половине высоты", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "Нажатие на закрытие вызывает onClose", "`LhTagTest.closable_tap_emits_onClose` (Compose UI Test)")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # JVM-часть",
    "./gradlew :components:tag:connectedAndroidTest             # Интерактивная часть (требуется устройство)",
  ),
)
