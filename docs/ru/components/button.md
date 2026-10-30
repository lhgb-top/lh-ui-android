# Кнопка

Базовая кнопка действия. Запускает немедленное действие; поддерживает типы, размеры, состояние загрузки, отключение, иконки и китайский фоновый узор.

> Источник контракта: [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> Золотые сценарии: [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button) (8 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Типы

Шесть типов определяют цвет фона и текста.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Типы Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">По умолчанию</button>
      <button class="sim-btn sim-btn--primary">Основная кнопка</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">Успех</button>
      <button class="sim-btn sim-btn--warning">Предупреждение</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">Опасность</button>
      <button class="sim-btn sim-btn--info">Информация</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("Основная кнопка") }
```

## Размеры

Три размера отображаются на токены `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Маленькая кнопка</button>
      <button class="sim-btn">Средняя кнопка</button>
      <button class="sim-btn sim-btn--large">Большая кнопка</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## Состояния

Ни отключенное состояние, ни загрузка не вызывают `lhOnClick`; если заданы оба, **отключение имеет приоритет** (золотой сценарий `disabled-precedes-loading`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Состояния Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Обычная</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Отключена</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Загрузка
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Отключение важнее</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## Иконки и фоновый узор

Имена иконок разрешаются локальной системой иконок (`LhButtonIcons`, сейчас поддерживаются search / refresh / check / close; незарегистрированные имена не отрисовываются).
Фоновый узор потребляет токен `color.decoration`: он накладывается поверх цвета фона, но под текстом и не влияет на контрастность.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Иконки и узоры</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Поиск</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>По умолчанию</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Основная с узором</button>
      <button class="sim-btn sim-btn--pattern">Узор по умолчанию</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## Светлая и тёмная темы

Переключи тему сайта в правом верхнем углу — цвета кнопок в рамке телефона-симулятора изменятся; на реальном устройстве переключение выполняется через `LhTheme(isDark = true)`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Тёмная кнопка</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">Основная кнопка</button>
      <button class="sim-btn">По умолчанию</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Отключена</button>
      <button class="sim-btn sim-btn--success">Успех</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве; светлые/тёмные значения взяты из снимка токенов core</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `lhOnClick` | Колбэк нажатия; не вызывается, если `disabled` или `loading` равно true | `() -> Unit` | — |
| `modifier` | Модификатор | `Modifier` | `Modifier` |
| `type` | Тип кнопки; определяет цвет фона и текста | `LhButtonType` | `Default` |
| `lhSize` | Размер кнопки; отображается на размерные токены | `LhButtonSize` | `Medium` |
| `disabled` | Отключена ли кнопка | `Boolean` | `false` |
| `loading` | Идёт ли загрузка (показывает индикатор и не вызывает lhOnClick) | `Boolean` | `false` |
| `icon` | Имя иконки (разрешается локальной системой иконок) | `String?` | `null` |
| `bgPattern` | Рисовать ли китайский фоновый узор | `Boolean` | `false` |
| `content` | Текстовое содержимое кнопки (слот default из спецификации) | `@Composable () -> Unit` | — |

### LhButtonType

| Значение | Фон | Цвет текста |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| Значение | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small` (32dp) | `button.paddingHorizontal.small` (16dp) | `button.fontSize.small` (13sp) | `button.iconSize.small` (16dp) |
| `Medium` | `button.height.medium` (40dp) | `button.paddingHorizontal.medium` (20dp) | `button.fontSize.medium` (14sp) | `button.iconSize.medium` (18dp) |
| `Large` | `button.height.large` (48dp) | `button.paddingHorizontal.large` (24dp) | `button.fontSize.large` (16sp) | `button.iconSize.large` (20dp) |

Переопределения отключенного состояния: фон `color.disabled.bg`, текст `color.disabled.text`, граница `color.disabled.border`.

## Справочник токенов

Токены, потребляемые кнопкой (построчно соответствует `tokenRefs` из `specs/button.json`):

| Токен | Назначение |
| --- | --- |
| `button.height.{small,medium,large}` | Три высоты |
| `button.paddingHorizontal.{small,medium,large}` | Три горизонтальных внутренних отступа |
| `button.fontSize.{small,medium,large}` | Три размера шрифта |
| `button.iconSize.{small,medium,large}` | Три размера иконок |
| `button.borderRadius` / `button.borderWidth` | Скругление (следует за `radius.base`) и толщина границы |
| `button.fontWeight` | Насыщенность шрифта (следует за `typography.fontWeight.primary`) |
| `color.primary.default` / `color.onPrimary` | Фон / текст основного типа |
| `color.{success,warning,danger,info}.default` | Фоны семантических типов |
| `color.background.default` / `color.text.primary` / `color.border.base` | Фон / текст / граница типа по умолчанию |
| `color.disabled.{bg,text,border}` | Три цвета отключенного состояния |
| `color.decoration` | Цвет узора |
| `shadow.lighter` | elevation типа по умолчанию |
| `motion.duration.fast` / `motion.easing.easeInOut` | Анимация отклика на нажатие |

## Справочник золотых сценариев

Соответствие 8 утверждений из `cases/button.json` локальным тестам:

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `click-emits-onclick` | Нажатие вызывает onClick ровно 1 раз | `LhButtonTest.click_emits_onClick` (Compose UI Test) |
| `disabled-blocks-click` | Отключенное состояние не вызывает | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | Состояние загрузки не вызывает | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | Отключение имеет приоритет | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | Тип по умолчанию отрисовывает текст | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | Основной тип потребляет токены основного цвета | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | Малый размер потребляет размерные токены small | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | Фоновый узор потребляет декоративный цвет | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | Зарегистрированное имя иконки отрисовывает вектор; незарегистрированное — ничего | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # JVM-часть
./gradlew :components:button:connectedAndroidTest        # Интерактивная часть (требуется устройство)
```
