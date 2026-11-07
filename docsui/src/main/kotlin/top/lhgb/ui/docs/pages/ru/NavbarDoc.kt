package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Панель навигации",
  lede = "Заголовочная панель в верхней части страницы с трёхчастной компоновкой «слева / центр / справа»; часто используется для возврата назад и отображения заголовка.",
  group = DocGroup.OVERLAY,
  contract = "Источник контракта: `lh-ui-core/specs/navbar.json` · Золотые сценарии: `cases/navbar.json` (6 сценариев; отображение токенов переведено в JVM-модульные тесты, нажатия левой/правой сторон — в Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Использование",
      prose = "Три части равны по ширине (каждая `weight(1f)`), заголовок центрируется по горизонтали в доступной ширине; по умолчанию рисуется разделительная линия снизу.",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"Заголовок страницы\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"Назад\",",
        "  rightText = \"Ещё\",",
        "  onLeftClick = { /* назад */ },",
        "  onRightClick = { /* ещё */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Левое и правое содержимое",
      prose = "Текст и иконка могут присутствовать одновременно (иконка слева, текст справа, расстояние `spacing.xs`); если на какой-либо стороне нет содержимого, она не реагирует на нажатия\n(золотой сценарий `no-left-content-no-onleftclick`: у такого узла нет даже кликабельной семантики).",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Заголовок по центру", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "Вызывается при нажатии на левую область", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "Вызывается при нажатии на правую область", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "Текст левой кнопки; может сочетаться с leftIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "Имя левой иконки (локальная система иконок, например `\"arrow-left\"`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "Текст правой кнопки; может сочетаться с rightIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "Имя правой иконки", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Рисовать ли разделительную линию снизу", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "Содержимое заголовка (переопределяет title, слот default из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "Содержимое слева (переопределяет leftText/leftIcon, слот left из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "Содержимое справа (переопределяет rightText/rightIcon, слот right из спецификации)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "Высота панели навигации (44dp)")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "Горизонтальные отступы слева/справа (следует за `spacing.md`)")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "Размер шрифта заголовка и текста по сторонам")),
    DocRow(cells = listOf("`navbar.iconSize`", "Размер иконок по сторонам (следует за `icon.size.medium`)")),
    DocRow(cells = listOf("`navbar.borderWidth`", "Толщина разделительной линии снизу")),
    DocRow(cells = listOf("`zIndex.sticky`", "Семантика иерархии «прилипающей» панели")),
    DocRow(cells = listOf("`color.background.default`", "Цвет фона")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Цвета заголовка / текста по сторонам")),
    DocRow(cells = listOf("`color.border.lighter`", "Цвет разделительной линии снизу")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "Нажатие слева вызывает onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test)")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "Нажатие справа вызывает onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "При отсутствии левого содержимого колбэк не вызывается", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "Заголовок отрисовывает текст", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "Разделительная линия потребляет токен границы", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "Высота потребляет токен navbar", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # JVM-часть",
    "./gradlew :components:navbar:connectedAndroidTest                # Интерактивная часть (требуется устройство)",
  ),
)
