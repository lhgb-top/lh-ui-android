package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "Кнопка",
  lede = "Базовая кнопка действия. Запускает немедленное действие; поддерживает типы, размеры, состояние загрузки, отключение, иконки и китайский фоновый узор.",
  group = DocGroup.BASIC,
  contract = "Источник контракта: `lh-ui-core/specs/button.json` · Золотые сценарии: `cases/button.json` (8 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Типы",
      prose = "Шесть типов определяют цвет фона и текста.",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"Основная кнопка\") }",
      ),
    ),
    DocSection(
      title = "Размеры",
      prose = "Три размера отображаются на токены `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "Состояния",
      prose = "Ни отключенное состояние, ни загрузка не вызывают `lhOnClick`; если заданы оба, отключение имеет приоритет (золотой сценарий `disabled-precedes-loading`).",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "Иконки и фоновый узор",
      prose = "Имена иконок разрешаются локальной системой иконок (`LhButtonIcons`, сейчас поддерживаются search / refresh / check / close; незарегистрированные имена не отрисовываются).\nФоновый узор потребляет токен `color.decoration`: он накладывается поверх цвета фона, но под текстом и не влияет на контрастность.",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "Светлая и тёмная темы",
      prose = "Переключи тему сайта в правом верхнем углу — цвета кнопок в рамке телефона-симулятора изменятся; на реальном устройстве переключение выполняется через `LhTheme(isDark = true)`.",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "Колбэк нажатия; не вызывается, если `disabled` или `loading` равно true", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Тип кнопки; определяет цвет фона и текста", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Размер кнопки; отображается на размерные токены", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Отключена ли кнопка", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "Идёт ли загрузка (показывает индикатор и не вызывает lhOnClick)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Имя иконки (разрешается локальной системой иконок)", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "Рисовать ли китайский фоновый узор", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "Текстовое содержимое кнопки (слот default из спецификации)", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("Значение", "Фон", "Цвет текста"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("Значение", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small` (32dp)", "`button.paddingHorizontal.small` (16dp)", "`button.fontSize.small` (13sp)", "`button.iconSize.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium` (40dp)", "`button.paddingHorizontal.medium` (20dp)", "`button.fontSize.medium` (14sp)", "`button.iconSize.medium` (18dp)")),
        DocRow(cells = listOf("`Large`", "`button.height.large` (48dp)", "`button.paddingHorizontal.large` (24dp)", "`button.fontSize.large` (16sp)", "`button.iconSize.large` (20dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "Три высоты")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "Три горизонтальных внутренних отступа")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "Три размера шрифта")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "Три размера иконок")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "Скругление (следует за `radius.base`) и толщина границы")),
    DocRow(cells = listOf("`button.fontWeight`", "Насыщенность шрифта (следует за `typography.fontWeight.primary`)")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Фон / текст основного типа")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "Фоны семантических типов")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "Фон / текст / граница типа по умолчанию")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Три цвета отключенного состояния")),
    DocRow(cells = listOf("`color.decoration`", "Цвет узора")),
    DocRow(cells = listOf("`shadow.lighter`", "elevation типа по умолчанию")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "Анимация отклика на нажатие")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "Нажатие вызывает onClick ровно 1 раз", "`LhButtonTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-click`", "Отключенное состояние не вызывает", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "Состояние загрузки не вызывает", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "Отключение имеет приоритет", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "Тип по умолчанию отрисовывает текст", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "Основной тип потребляет токены основного цвета", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "Малый размер потребляет размерные токены small", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "Фоновый узор потребляет декоративный цвет", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "Зарегистрированное имя иконки отрисовывает вектор; незарегистрированное — ничего", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # JVM-часть",
    "./gradlew :components:button:connectedAndroidTest        # Интерактивная часть (требуется устройство)",
  ),
)
