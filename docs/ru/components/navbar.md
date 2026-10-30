# Панель навигации

Заголовочная панель в верхней части страницы с трёхчастной компоновкой «слева / центр / справа»; часто используется для возврата назад и отображения заголовка.

> Источник контракта: [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> Золотые сценарии: [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar) (6 сценариев; отображение токенов переведено в JVM-модульные тесты, нажатия левой/правой сторон — в Compose UI Test)

## Использование

Три части равны по ширине (каждая `weight(1f)`), заголовок центрируется по горизонтали в доступной ширине; по умолчанию рисуется разделительная линия снизу.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>Назад</div>
      <div class="sim-navbar__title">Заголовок страницы</div>
      <div class="sim-navbar__side sim-navbar__side--right">Ещё</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
LhNavbar(
  title = "Заголовок страницы",
  leftIcon = "arrow-left",
  leftText = "Назад",
  rightText = "Ещё",
  onLeftClick = { /* назад */ },
  onRightClick = { /* ещё */ },
)
```

## Левое и правое содержимое

Текст и иконка могут присутствовать одновременно (иконка слева, текст справа, расстояние `spacing.xs`); **если на какой-либо стороне нет содержимого, она не реагирует на нажатия**
(золотой сценарий `no-left-content-no-onleftclick`: у такого узла нет даже кликабельной семантики).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Варианты Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">Только иконка</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">Текст</div>
      <div class="sim-navbar__title">Без разделителя</div>
      <div class="sim-navbar__side sim-navbar__side--right">Изменить</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `title` | Заголовок по центру | `String?` | `null` |
| `onLeftClick` | Вызывается при нажатии на левую область | `() -> Unit` | `{}` |
| `onRightClick` | Вызывается при нажатии на правую область | `() -> Unit` | `{}` |
| `leftText` | Текст левой кнопки; может сочетаться с leftIcon | `String?` | `null` |
| `leftIcon` | Имя левой иконки (локальная система иконок, например `"arrow-left"`) | `String?` | `null` |
| `rightText` | Текст правой кнопки; может сочетаться с rightIcon | `String?` | `null` |
| `rightIcon` | Имя правой иконки | `String?` | `null` |
| `bordered` | Рисовать ли разделительную линию снизу | `Boolean` | `true` |
| `titleContent` | Содержимое заголовка (переопределяет title, слот default из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | Содержимое слева (переопределяет leftText/leftIcon, слот left из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | Содержимое справа (переопределяет rightText/rightIcon, слот right из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

Если все `*Content` / `*Text` / `*Icon` одной из сторон пусты, эта сторона не реагирует на нажатия и ничего не отрисовывает.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `navbar.height` | Высота панели навигации (44dp) |
| `navbar.paddingHorizontal` | Горизонтальные отступы слева/справа (следует за `spacing.md`) |
| `navbar.titleFontSize` | Размер шрифта заголовка и текста по сторонам |
| `navbar.iconSize` | Размер иконок по сторонам (следует за `icon.size.medium`) |
| `navbar.borderWidth` | Толщина разделительной линии снизу |
| `zIndex.sticky` | Семантика иерархии «прилипающей» панели |
| `color.background.default` | Цвет фона |
| `color.text.primary` / `color.text.regular` | Цвета заголовка / текста по сторонам |
| `color.border.lighter` | Цвет разделительной линии снизу |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | Нажатие слева вызывает onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test) |
| `rightclick-emits-onrightclick` | Нажатие справа вызывает onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | При отсутствии левого содержимого колбэк не вызывается | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | Заголовок отрисовывает текст | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | Разделительная линия потребляет токен границы | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | Высота потребляет токен navbar | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # JVM-часть
./gradlew :components:navbar:connectedAndroidTest                # Интерактивная часть (требуется устройство)
```
