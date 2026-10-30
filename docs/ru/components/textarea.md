# Текстовая область

Управляемая многострочная область ввода; поддерживает число строк, подсчёт символов и максимальную длину.

> Источник контракта: [`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> Золотые сценарии: [`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea) (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Базовое использование

Минимальная высота = количество строк × межстрочный интервал × размер шрифта; при превышении появляется вертикальная прокрутка.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="Введите многострочное содержимое" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">Первая строка
Вторая строка
Третья строка</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## Подсчёт символов

Если `showCount` равно true, в правом нижнем углу отрисовывается счётчик (`textarea.countFontSize`, цвет `text.secondary`); `maxlength` обрезает ввод.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Счётчик Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">Три</textarea>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве; на реальном устройстве в правом нижнем углу показывается счётчик «3 / 20»</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `value` | Управляемое значение (обязательный) | `String` | — |
| `onValueChange` | Колбэк изменения значения (обязательный) | `(String) -> Unit` | — |
| `placeholder` | Плейсхолдер | `String?` | `null` |
| `rows` | Количество строк (определяет минимальную высоту) | `Int` | `3` |
| `disabled` | Отключена ли область | `Boolean` | `false` |
| `readonly` | Режим «только чтение» | `Boolean` | `false` |
| `showCount` | Показывать ли подсчёт символов | `Boolean` | `false` |
| `maxlength` | Максимальная длина (превышение обрезается) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Колбэки фокуса / потери фокуса | `(() -> Unit)?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | Внутренние отступы (`spacing.md`) |
| `textarea.fontSize` | Размер шрифта (`typography.fontSize.base`) |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | Скругление / толщина границы / толщина границы в фокусе |
| `textarea.countFontSize` | Размер шрифта счётчика (`typography.fontSize.extraSmall`) |
| `textarea.minHeight` | Базис минимальной высоты |
| `typography.lineHeight.primary` | Межстрочный интервал (для пересчёта количества строк) |
| `color.text.{primary,secondary,placeholder}` | Цвета текста / счётчика / плейсхолдера |
| `color.focus` / `color.border.base` | Цвета границы в фокусе / по умолчанию |
| `color.disabled.{bg,text,border}` | Три цвета отключенного состояния |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `input-emits-onvaluechange` | Ввод вызывает onValueChange | `LhTextareaTest.input_emits_onValueChange` (Compose UI Test) |
| `disabled-blocks-input` | В отключенном состоянии ввод невозможен | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | Счётчик отрисовывает «n / max» | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | Без maxlength отрисовывается только длина | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | Фокус потребляет токен фокуса | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | Превышение максимальной длины обрезается | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # JVM-часть
./gradlew :components:textarea:connectedAndroidTest           # Интерактивная часть (требуется устройство)
```
