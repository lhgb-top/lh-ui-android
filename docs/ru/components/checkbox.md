# Флажок

Управляемый флажок; поддерживает неопределённое состояние (indeterminate) и отключение.

> Источник контракта: [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> Золотые сценарии: [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox) (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Состояния

Выбранное / неопределённое состояния — залитый фон основного цвета + индикатор onPrimary (галочка / горизонтальная черта); невыбранное состояние — только граница.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>Не выбран</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>Выбран</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>Неопределённое</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Отключён (выбран)</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>Отключён (не выбран)</label></div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "Согласен с условиями",
)
```

## Размеры

Три размера квадратика отображаются на токены `checkbox.size.*`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `checked` | Выбран ли флажок (обязательный) | `Boolean` | — |
| `onCheckedChange` | Колбэк изменения состояния; передаёт новое состояние | `(Boolean) -> Unit` | — |
| `indeterminate` | Находится ли флажок в неопределённом состоянии | `Boolean` | `false` |
| `disabled` | Отключён ли флажок | `Boolean` | `false` |
| `label` | Текст метки | `String?` | `null` |
| `lhSize` | Размер квадратика | `LhCheckboxSize` | `Medium` |
| `labelContent` | Содержимое метки (переопределяет label, слот default из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

### LhCheckboxSize

| Значение | Токен размера |
| --- | --- |
| `Small` | `checkbox.size.small` (16dp) |
| `Medium` | `checkbox.size.medium` (20dp) |
| `Large` | `checkbox.size.large` (24dp) |

Область нажатия охватывает всю строку (квадратик + метка): нажатие в неопределённом состоянии переводит флажок в выбранное, иначе состояние переключается на противоположное.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `checkbox.size.{small,medium,large}` | Три размера квадратика |
| `checkbox.borderRadius` / `checkbox.borderWidth` | Скругление и толщина границы квадратика |
| `checkbox.checkWidth` | Толщина галочки / горизонтальной черты |
| `checkbox.gap` | Расстояние между квадратиком и меткой |
| `color.primary.default` / `color.onPrimary` | Цвет фона выбранного состояния / цвет индикатора |
| `color.border.base` | Цвет границы невыбранного состояния |
| `color.disabled.{bg,border,text}` | Три цвета отключенного состояния |
| `color.text.primary` | Цвет метки |
| `typography.fontSize.base` | Размер шрифта метки |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | Нажатие переключает состояние и emits onChange | `LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-unchecks` | Нажатие в выбранном состоянии снимает выбор | `LhCheckboxTest.tap_checked_emits_false` |
| `tap-indeterminate-emits-true` | Нажатие в неопределённом состоянии переводит в выбранное | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | Отключенное состояние не вызывает изменений | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | Выбранное состояние потребляет токены основного цвета | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | Невыбранное состояние потребляет токен границы | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # JVM-часть
./gradlew :components:checkbox:connectedAndroidTest           # Интерактивная часть (требуется устройство)
```
