package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast (короткое уведомление)",
  lede = "Наложение в виде пилюли цвета туши, самостоятельно управляющее видимостью и таймером; используется для лёгкой обратной связи по действию. Не блокирует действия пользователя.",
  group = DocGroup.OVERLAY,
  contract = "Источник контракта: `lh-ui-core/specs/toast.json` · Золотые сценарии: `cases/toast.json` (6 сценариев: 4 для конечного автомата + 2 для отрисовки; все переведены в локальные тесты)",
  sections = listOf(
    DocSection(
      title = "Использование",
      prose = "toast — самостоятельный конечный автомат: состояние хранится в `LhToastState`, хозяин вызывает `show(text)` для отображения и `hide()` для скрытия;\nпо истечении таймера происходит автоматическое скрытие и вызывается `onClose`. В видимом состоянии компонент самостоятельно вызывает `tick` кадр за кадром; хозяин также может вызывать его сам, перехватив управление таймером.",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* опционально: зафиксировать закрытие */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"Сохранено\") }) { Text(\"Показать\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "Семантические типы",
      prose = "Для типов, отличных от `default`, перед текстом отрисовывается семантическая точка диаметром, равным размеру шрифта.",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "Позиционирование",
      prose = "`lhPosition` принимает `top` / `center` / `bottom`; у краёв остаётся отступ `spacing.xl`; ширина определяется измерением текста и ограничивается 80% доступной ширины.",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "Конечный автомат уведомления (хранит видимость, текст и таймер)", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "Семантический тип; для значений кроме default отрисовывается семантическая точка", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "Позиция в доступной области", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "Модификатор (применяется к пилюле)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("Член", "Описание"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "Показывает уведомление и сбрасывает таймер")),
        DocRow(cells = listOf("`hide()`", "Скрывает немедленно, не вызывая onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "Продвигает таймер; по истечении скрывает, вызывает onClose и возвращает true")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "Текущее состояние (наблюдаемо)")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("Значение", "Цвет точки"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "Без точки")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "Соответствующий семантический цвет")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "Максимальная ширина пилюли (240dp; фактически берётся меньшее из 80% доступной ширины и этого значения)")),
    DocRow(cells = listOf("`toast.borderRadius`", "Скругление пилюли (следует за `radius.base`)")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "Внутренние отступы пилюли")),
    DocRow(cells = listOf("`toast.fontSize`", "Размер шрифта текста (диаметр точки равен этому значению)")),
    DocRow(cells = listOf("`toast.gap`", "Расстояние между точкой и текстом")),
    DocRow(cells = listOf("`zIndex.toast`", "Семантика иерархии (оконко-уровневую иерархию обеспечивает Compose `Popup`)")),
    DocRow(cells = listOf("`color.background.overlay` (отображение туши на уровне темы)", "Цвет фона пилюли")),
    DocRow(cells = listOf("`color.onPrimary`", "Цвет текста")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Цвета семантических точек")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "После show уведомление видно и отрисовывает текст", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "По истечении автоматическое скрытие и вызов onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "До истечения остаётся видимым и не вызывает onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide не вызывает onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "Тип success потребляет цвет успеха", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "В скрытом состоянии ничего не отрисовывается", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # JVM-часть",
    "./gradlew :components:toast:connectedAndroidTest                 # Интерактивная часть (требуется устройство)",
  ),
)
