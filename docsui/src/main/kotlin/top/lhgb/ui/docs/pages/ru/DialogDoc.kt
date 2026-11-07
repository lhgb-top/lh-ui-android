package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Диалог",
  lede = "Модальное наложение для важных подтверждений или контента, требующего фокуса пользователя. Оконно-уровневая иерархия управляется Compose `Dialog`.",
  group = DocGroup.OVERLAY,
  contract = "Источник контракта: `lh-ui-core/specs/dialog.json` · Золотые сценарии: `cases/dialog.json` (8 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Типы",
      prose = "`type` определяет цвет кнопки подтверждения (отображение 1:1 на `LhButtonType`), остальная компоновка не меняется.",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* логика подтверждения */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"Подтверждение удаления\",",
        "  content = \"Удаление необратимо. Удалить?\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "Семантический тип и маска",
      prose = "При `type` равном `danger` кнопка подтверждения берёт опасный цвет; `closable` управляет, вызывает ли нажатие на маску / кнопка «Назад» колбэк `onClose`.\nПосле нажатия кнопок «Подтвердить» / «Отмена» сначала вызывается бизнес-колбэк, затем `onClose` — по этому сигналу хозяин устанавливает `visible` в false.",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "Показывать ли диалог; при false не отрисовывает ничего", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "Вызывается при нажатии кнопки подтверждения", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "Вызывается при нажатии кнопки отмены", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "Вызывается после подтверждения/отмены или (если closable) при нажатии на маску", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "Заголовок", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "Основное содержимое", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "Семантический тип; определяет тип (цвет) кнопки подтверждения", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "Текст кнопки подтверждения; если пусто — кнопка не отображается", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "Текст кнопки отмены; если пусто — кнопка не отображается", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "Вызывает ли нажатие на маску / кнопка «Назад» колбэк onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "Содержимое заголовка (переопределяет title, слот title из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "Основное содержимое (переопределяет content, слот default из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "Зона кнопок внизу (переопределяет кнопки по умолчанию, слот footer из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор (применяется к карточке содержимого)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("Значение", "Тип кнопки подтверждения"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "Одноимённый `LhButtonType`, берёт соответствующий семантический цвет")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "Ширина карточки содержимого (320dp)")),
    DocRow(cells = listOf("`dialog.borderRadius`", "Скругление карточки (следует за `radius.base`)")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "Внутренние отступы карточки (следуют за `spacing.lg`)")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "Размеры шрифта заголовка / основного текста")),
    DocRow(cells = listOf("`dialog.gap`", "Расстояния между заголовком, текстом и зоной кнопок")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "Семантика иерархии (оконко-уровневую иерархию управляет Compose `Dialog`)")),
    DocRow(cells = listOf("`color.background.overlay`", "Цвет маски")),
    DocRow(cells = listOf("`color.background.default`", "Цвет фона карточки")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Цвета заголовка / основного текста")),
    DocRow(cells = listOf("`shadow.base`", "Тень карточки (Compose не поддерживает spread; для elevation берётся радиус размытия)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "Подтверждение вызывает onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "После подтверждения вызывается onClose", "То же")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "Отмена вызывает onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "При closable нажатие на маску вызывает onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "При не-closable нажатие на маску поглощается", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "В скрытом состоянии ничего не отрисовывается", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "Заголовок отрисовывает текст", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "Кнопка подтверждения типа danger берёт опасный цвет", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # JVM-часть",
    "./gradlew :components:dialog:connectedAndroidTest             # Интерактивная часть (требуется устройство)",
  ),
)
