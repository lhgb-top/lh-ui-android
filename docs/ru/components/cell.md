# Ячейка

Ячейка списка с трёхчастной компоновкой «иконка / заголовок + описание / правое значение + стрелка»; часто используется в сочетании со списками.

> Источник контракта: [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> Золотые сценарии: [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell) (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Базовое использование

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">Текст заголовка</div>
      <div class="sim-cell__value">Правое значение</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">Заголовок с описанием</div>
        <div class="sim-cell__label">Текст описания</div>
      </div>
      <div class="sim-cell__value">Значение</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Обязательное поле</div>
      <div class="sim-cell__value">Подробнее</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
LhCell(
  title = "Заголовок с описанием",
  label = "Текст описания",
  value = "Значение",
  isLink = true,
  lhOnClick = { /* переход */ },
)
```

## Обязательное поле и ссылка

`required` рисует красную звёздочку перед заголовком (`color.danger.default`); `isLink` рисует в конце значок стрелки вправо.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Варианты Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>Обязательное</div>
      <div class="sim-cell__value">Стрелка →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">Без рамки</div>
      <div class="sim-cell__value">Значение</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `title` | Заголовок (основной текст) | `String?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |
| `value` | Правое значение (вторичный текст, выравнивание по правому краю) | `String?` | `null` |
| `label` | Поясняющий текст под заголовком | `String?` | `null` |
| `bordered` | Рисовать ли разделительную линию снизу (`color.border.lighter`) | `Boolean` | `true` |
| `clickable` | Доступна ли ячейка для нажатия; при true показывает индикатор-стрелку справа и реагирует на нажатия | `Boolean` | `false` |
| `required` | Показывать ли красную звезду обязательного поля перед заголовком (`color.danger.default`) | `Boolean` | `false` |
| `icon` | Имя левой иконки (локальная система иконок); если пусто — не отрисовывается | `String?` | `null` |
| `lhOnClick` | Колбэк нажатия; не вызывается, если `clickable` равно false | `(() -> Unit)?` | `null` |
| `titleContent` | Содержимое заголовка (переопределяет title, слот default из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | Содержимое правого значения (переопределяет value, слот value из спецификации) | `@Composable (() -> Unit)?` | `null` |

`clickable` одновременно управляет «рисуется ли индикатор-стрелка справа» и «реагирует ли вся строка на нажатие» — нажатие срабатывает, только если `clickable = true` и `lhOnClick` не равен null.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `cell.paddingVertical` | Вертикальный внутренний отступ (ссылка на `spacing.md`) |
| `cell.paddingHorizontal` | Горизонтальный внутренний отступ (`spacing.md`) |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | Размеры шрифта заголовка / описания / значения |
| `cell.gap` | Расстояние между иконкой и заголовком (`spacing.xs`) |
| `icon.size.medium` | Размер левой иконки и индикаторной стрелки |
| `cell.borderWidth` | Толщина разделительной линии снизу |
| `typography.lineHeight.primary` | Межстрочный интервал текста |
| `color.text.{primary,secondary}` | Цвета заголовка / описания / значения |
| `color.danger.default` | Красная звезда обязательного поля |
| `color.border.lighter` | Разделительная линия снизу |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `title-and-value-render` | Заголовок и правое значение отрисовывают текст | `LhCellTest.title_and_value_render` (Compose UI Test) |
| `label-renders-secondary-tone` | label берёт токен вторичного цвета текста | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | Маркер обязательного поля берёт токен опасного цвета | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | Разделительная линия потребляет токен границы | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | Нажатие вызывает onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | Отключенное состояние не вызывает | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # JVM-часть
./gradlew :components:cell:connectedAndroidTest              # Интерактивная часть (требуется устройство)
```
