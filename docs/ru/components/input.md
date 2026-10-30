# Поле ввода

Управляемое однострочное поле ввода; поддерживает размеры, отключение, режим «только чтение», очистку, маскирование пароля и максимальную длину.

> Источник контракта: [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> Золотые сценарии: [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input) (8 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Базовое использование

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="Введите содержимое" /></div>
    <div class="sim-row"><input class="sim-input" value="Заполненное значение" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="Отключённое состояние" disabled /></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "Введите содержимое",
  onValueChange = { value = it },
)
```

## Размеры и фокус

Три размера отображаются на токены `input.height` / `input.paddingHorizontal` / `input.fontSize`; в фокусе граница берёт `color.focus` и расширяется до `input.focusRingWidth`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium (демонстрация фокуса)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `value` | Управляемое значение (обязательный) | `String` | — |
| `onValueChange` | Колбэк изменения значения (обязательный) | `(String) -> Unit` | — |
| `placeholder` | Плейсхолдер | `String?` | `null` |
| `type` | Тип ввода (открытый текст / пароль) | `LhInputType` | `Text` |
| `lhSize` | Размер | `LhInputSize` | `Medium` |
| `disabled` | Отключено ли поле | `Boolean` | `false` |
| `readonly` | Режим «только чтение» (не редактируется, но без отключённого стиля) | `Boolean` | `false` |
| `clearable` | Показывать ли кнопку очистки | `Boolean` | `false` |
| `maxlength` | Максимальная длина (превышение обрезается) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Колбэки фокуса / потери фокуса | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | Слоты префикса / суффикса | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

### LhInputSize

| Значение | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small` (32dp) | `input.paddingHorizontal.small` (12dp) | `input.fontSize.small` (13sp) |
| `Medium` | `input.height.medium` (40dp) | `input.paddingHorizontal.medium` (16dp) | `input.fontSize.medium` (14sp) |
| `Large` | `input.height.large` (48dp) | `input.paddingHorizontal.large` (20dp) | `input.fontSize.large` (16sp) |

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `input.height.{small,medium,large}` | Три высоты |
| `input.paddingHorizontal.{small,medium,large}` | Три горизонтальных внутренних отступа |
| `input.fontSize.{small,medium,large}` | Три размера шрифта |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | Скругление / толщина границы / толщина границы в фокусе |
| `input.clearIconSize` | Размер иконки очистки |
| `color.focus` | Цвет границы в фокусе |
| `color.text.placeholder` | Цвет плейсхолдера |
| `color.border.base` | Цвет границы по умолчанию |
| `color.disabled.{bg,text,border}` | Три цвета отключенного состояния |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `input-emits-onvaluechange` | Ввод вызывает onValueChange | `LhInputTest.input_emits_onValueChange` (Compose UI Test) |
| `clearable-emits-empty-value` | Кнопка очистки передаёт пустое значение | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | В отключенном состоянии ввод невозможен | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | В режиме «только чтение» ввод невозможен | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | Цвет плейсхолдера берётся из токена плейсхолдера | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | Фокус потребляет токен фокуса (без фокуса — нейтральная граница) | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | Малый размер потребляет размерные токены small | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | Превышение максимальной длины обрезается | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # JVM-часть
./gradlew :components:input:connectedAndroidTest              # Интерактивная часть (требуется устройство)
```
