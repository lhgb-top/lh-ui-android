# Средство выбора

Средство выбора с одним колесом, всплывающее снизу экрана: после остановки прокрутки выбирается вариант, ближайший к центральному индикатору.

> Источник контракта: [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> Золотые сценарии: [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker) (8 сценариев; кламп и отображение токенов переведены в JVM-модульные тесты, прокрутка и нажатия кнопок — в Compose UI Test)

## Использование

Всплывает снизу: маска сверху, панель снизу (со скруглением сверху). Панель инструментов состоит из трёх частей «Отмена / Заголовок / Подтвердить».

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">Отмена</button>
        <div class="sim-picker__toolbar-title">Выберите</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">Подтвердить</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">Вариант 1</div>
        <div class="sim-picker__item sim-picker__item--selected">Вариант 2</div>
        <div class="sim-picker__item">Вариант 3</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("Вариант 1", "Вариант 2", "Вариант 3")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "Выберите",
)
```

## Механизм выбора

Колесо — это прокручиваемый список с высотой каждого варианта `picker.itemHeight`; центральный индикатор — две горизонтальные линии на расстоянии itemHeight друг от друга.
После остановки прокрутки индекс выбора вычисляется округлением пройденного расстояния; при изменении вызывается `onValueChange`.
Если `value` выходит за пределы диапазона, он ограничивается (clamp) до диапазона вариантов средством `LhPickerDefaults.clamp`.

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `visible` | Показывать ли компонент; при false не отрисовывает ничего | `Boolean` | — |
| `options` | Массив текстов вариантов (одна колонка) | `List<String>` | — |
| `onValueChange` | Вызывается, когда прокрутка остановилась и выбранный вариант изменился; передаёт новый индекс | `(Int) -> Unit` | — |
| `onConfirm` | Вызывается при нажатии кнопки подтверждения; передаёт текущий выбранный индекс | `(Int) -> Unit` | — |
| `onCancel` | Вызывается при нажатии кнопки отмены | `() -> Unit` | — |
| `onClose` | Вызывается после подтверждения/отмены; по этому сигналу хозяин устанавливает visible в false | `() -> Unit` | — |
| `value` | Индекс выбранного варианта (с 0); при выходе за пределы диапазона ограничивается (clamp) | `Int` | `0` |
| `title` | Заголовок по центру панели инструментов | `String?` | `null` |
| `confirmText` | Текст кнопки подтверждения | `String` | `"确认"` |
| `cancelText` | Текст кнопки отмены | `String` | `"取消"` |
| `content` | Область содержимого колеса (переопределяет отрисовку вариантов по умолчанию, слот default из спецификации) | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | Панель инструментов (переопределяет подтверждение/заголовок/отмену по умолчанию, слот toolbar из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `listState` | Состояние списка колеса (по умолчанию предоставляется `rememberLhPickerListState(value, options)`; в тестах можно внедрить снаружи) | `LazyListState` | см. описание слева |
| `modifier` | Модификатор (применяется к панели) | `Modifier` | `Modifier` |

Тестовый хук: `rememberLhPickerListState(value, options)` возвращает `LazyListState` с начальной позицией по `value`;
в инструментированных тестах прокрутку можно детерминированно управлять через `listState.dispatchRawDelta(...)`.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `picker.height` | Высота панели (260dp) |
| `picker.itemHeight` | Высота каждого варианта (40dp; также расстояние между линиями центрального индикатора) |
| `picker.toolbarHeight` | Высота панели инструментов (44dp) |
| `picker.itemFontSize` | Размер шрифта вариантов |
| `picker.borderRadius` | Скругление верхних углов панели |
| `picker.paddingHorizontal` | Горизонтальный внутренний отступ панели (следует за `spacing.md`) |
| `zIndex.modal` / `zIndex.modalBackdrop` | Семантика иерархии (оконко-уровневую иерархию обеспечивает Compose `Popup`) |
| `color.background.overlay` | Цвет маски |
| `color.background.default` | Цвет фона панели |
| `color.text.primary` / `color.text.regular` | Цвета заголовка / вариантов |
| `color.border.lighter` | Цвет линий центрального индикатора |
| `color.primary.default` | Цвет кнопки подтверждения |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | Подтверждение передаёт текущий индекс + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | После подтверждения вызывается onClose | То же |
| `cancel-emits-oncancel` | Отмена вызывает onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | Прокрутка изменяет выбор и вызывает onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | Отрицательное значение ограничивается (clamp) до 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | Значение за пределами диапазона ограничивается (clamp) до последнего варианта | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | Заголовок отрисовывает текст | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | В скрытом состоянии ничего не отрисовывается | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # JVM-часть
./gradlew :components:picker:connectedAndroidTest               # Интерактивная часть (требуется устройство)
```
