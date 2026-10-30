# Радиокнопка

Управляемая радиокнопка; повторное нажатие в выбранном состоянии не снимает выбор (семантика одиночного выбора).

> Источник контракта: [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> Золотые сценарии: [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio) (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Состояния и группа

Компонент не содержит логики группы; взаимное исключение вариантов поддерживается хозяином (для идентификации элемента в группе используется `value`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>Не выбран</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>Выбран</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>Отключён (не выбран)</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>Отключён (выбран)</label></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("Вариант 1" to "a", "Вариант 2" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## Размеры

Три размера кольца отображаются на токены `radio.size.*`; диаметр внутренней точки = размер × `radio.dotScale`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `checked` | Выбран ли элемент (обязательный) | `Boolean` | — |
| `onChange` | Вызывается при переходе из невыбранного в выбранное состояние; передаёт `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | Отключён ли элемент | `Boolean` | `false` |
| `label` | Текст метки | `String?` | `null` |
| `value` | Значение элемента (идентификатор в группе; сам компонент логики группы не содержит) | `String?` | `null` |
| `lhSize` | Размер кольца | `LhRadioSize` | `Medium` |
| `labelContent` | Содержимое метки (переопределяет label, слот default из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

### LhRadioSize

| Значение | Токен размера | Диаметр внутренней точки |
| --- | --- | --- |
| `Small` | `radio.size.small` (16dp) | 16dp × `radio.dotScale` (0.5) |
| `Medium` | `radio.size.medium` (20dp) | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large` (24dp) | 24dp × `radio.dotScale` |

Область нажатия охватывает всю строку: выбор срабатывает и вызывается `onChange(true)`, только если элемент не выбран и не отключён.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `radio.size.{small,medium,large}` | Три размера кольца |
| `radio.ringWidth` | Толщина границы внешнего кольца |
| `radio.dotScale` | Доля внутренней точки относительно диаметра кольца (дробное значение) |
| `radio.gap` | Расстояние между кольцом и меткой |
| `color.primary.default` | Цвет границы и точки в выбранном состоянии |
| `color.border.base` | Цвет границы в невыбранном состоянии |
| `color.disabled.{border,text}` | Цвета границы / точки / текста в отключенном состоянии |
| `color.text.primary` | Цвет метки |
| `typography.fontSize.base` | Размер шрифта метки |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | Нажатие в невыбранном состоянии вызывает onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test) |
| `tap-checked-keeps-checked` | Нажатие в выбранном состоянии не вызывает колбэк | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | Отключенное состояние не вызывает изменений | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | Выбранное состояние потребляет токены основного цвета | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | Невыбранное состояние потребляет токен границы | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | Малый размер потребляет размерный токен small | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # JVM-часть
./gradlew :components:radio:connectedAndroidTest             # Интерактивная часть (требуется устройство)
```
