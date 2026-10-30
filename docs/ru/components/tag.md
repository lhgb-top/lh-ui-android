# Тег

Тег с семантическими цветами; поддерживает плоский (plain) режим, полностью скруглённую метку (mark) и кнопку закрытия.

> Источник контракта: [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> Золотые сценарии: [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag) (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Типы

Шесть семантических типов определяют цвет фона и текста.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Типы Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">По умолчанию</span><span class="sim-tag sim-tag--success">Успех</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">Предупреждение</span><span class="sim-tag sim-tag--danger">Опасность</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">Информация</span></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
LhTag(type = LhTagType.success) { Text("Успех") }
```

## Плоский (plain)

С `plain` тег получает прозрачный фон + семантические границу и текст.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">Основной</span><span class="sim-tag sim-tag--plain sim-tag--success">Успех</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">Опасность</span></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## Размеры и метка

Три размера отображаются на токены `tag.height` / `tag.paddingHorizontal` / `tag.fontSize`; `mark` получает полное скругление радиусом в половину высоты.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">Метка</span><span class="sim-tag sim-tag--success sim-tag--mark">Метка успеха</span></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `text` | Текст тега (удобная запись; альтернатива `content`) | `String?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |
| `type` | Семантический тип | `LhTagType` | `Default` |
| `lhSize` | Размер | `LhTagSize` | `Medium` |
| `plain` | Плоский ли тег (прозрачный фон + семантическая граница) | `Boolean` | `false` |
| `round` | Полное ли скругление (половина высоты) | `Boolean` | `false` |
| `closable` | Показывать ли кнопку закрытия | `Boolean` | `false` |
| `onClose` | Колбэк закрытия; не вызывается, если `closable` равно false | `(() -> Unit)?` | `null` |
| `content` | Содержимое тега (переопределяет text, слот default из спецификации) | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| Значение | Фон (не plain) | Граница/текст (plain) |
| --- | --- | --- |
| `Default` | `color.background.default` + граница `color.border.base` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| Значение | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small` (20dp) | `tag.paddingHorizontal.small` (8dp) | `tag.fontSize.small` (12sp) |
| `Medium` | `tag.height.medium` (24dp) | `tag.paddingHorizontal.medium` (10dp) | `tag.fontSize.medium` (14sp) |
| `Large` | `tag.height.large` (28dp) | `tag.paddingHorizontal.large` (12dp) | `tag.fontSize.large` (16sp) |

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `tag.height.{small,medium,large}` | Три высоты |
| `tag.paddingHorizontal.{small,medium,large}` | Три горизонтальных внутренних отступа |
| `tag.fontSize.{small,medium,large}` | Три размера шрифта |
| `tag.borderRadius` / `tag.borderWidth` | Скругление и толщина границы |
| `color.{primary,success,warning,danger,info}.default` | Семантические цвета фона / границы / текста |
| `color.onPrimary` | Цвет текста для не-plain |
| `color.disabled.{bg,text,border}` | Три цвета отключенного состояния |
| `spacing.xs` | Расстояние между кнопкой закрытия и текстом |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `default-type-renders-text` | Тип по умолчанию берёт нейтральный фон + основной цвет текста + базовую границу | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | Основной тип потребляет токены основного цвета | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain потребляет токены семантического светлого фона | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | Малый размер потребляет токены small (обратное утверждение: large не применён) | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | При round скругление равно половине высоты | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | Нажатие на закрытие вызывает onClose | `LhTagTest.closable_tap_emits_onClose` (Compose UI Test) |

```bash
./gradlew :components:tag:test                             # JVM-часть
./gradlew :components:tag:connectedAndroidTest             # Интерактивная часть (требуется устройство)
```
