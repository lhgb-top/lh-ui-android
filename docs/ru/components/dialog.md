# Диалог

Модальное наложение для важных подтверждений или контента, требующего фокуса пользователя. Оконно-уровневая иерархия управляется Compose `Dialog`.

> Источник контракта: [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> Золотые сценарии: [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog) (8 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Типы

`type` определяет цвет кнопки подтверждения (отображение 1:1 на `LhButtonType`), остальная компоновка не меняется.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Заголовок</div>
      <div class="sim-dialog__content">Это основной текст, поясняющий последствия текущего действия.</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Отмена</button>
        <button class="sim-btn sim-btn--primary">Подтвердить</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* логика подтверждения */ },
  onCancel = {},
  onClose = { visible = false },
  title = "Подтверждение удаления",
  content = "Удаление необратимо. Удалить?",
  type = LhDialogType.danger,
)
```

## Семантический тип и маска

При `type` равном `danger` кнопка подтверждения берёт опасный цвет; `closable` управляет, вызывает ли нажатие на маску / кнопка «Назад» колбэк `onClose`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">Подтверждение удаления</div>
      <div class="sim-dialog__content">Удаление необратимо. Удалить?</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">Отмена</button>
        <button class="sim-btn sim-btn--danger">Подтвердить</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

После нажатия кнопок «Подтвердить» / «Отмена» **сначала вызывается бизнес-колбэк, затем `onClose`** — по этому сигналу хозяин устанавливает `visible` в false.

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `visible` | Показывать ли диалог; при false не отрисовывает ничего | `Boolean` | — |
| `onConfirm` | Вызывается при нажатии кнопки подтверждения | `() -> Unit` | — |
| `onCancel` | Вызывается при нажатии кнопки отмены | `() -> Unit` | — |
| `onClose` | Вызывается после подтверждения/отмены или (если closable) при нажатии на маску | `() -> Unit` | — |
| `title` | Заголовок | `String?` | `null` |
| `content` | Основное содержимое | `String?` | `null` |
| `type` | Семантический тип; определяет тип (цвет) кнопки подтверждения | `LhDialogType` | `default` |
| `confirmText` | Текст кнопки подтверждения; если пусто — кнопка не отображается | `String` | `"确认"` |
| `cancelText` | Текст кнопки отмены; если пусто — кнопка не отображается | `String` | `"取消"` |
| `closable` | Вызывает ли нажатие на маску / кнопка «Назад» колбэк onClose | `Boolean` | `false` |
| `titleContent` | Содержимое заголовка (переопределяет title, слот title из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | Основное содержимое (переопределяет content, слот default из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `footer` | Зона кнопок внизу (переопределяет кнопки по умолчанию, слот footer из спецификации) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Модификатор (применяется к карточке содержимого) | `Modifier` | `Modifier` |

### LhDialogType

| Значение | Тип кнопки подтверждения |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | Одноимённый `LhButtonType`, берёт соответствующий семантический цвет |

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `dialog.width` | Ширина карточки содержимого (320dp) |
| `dialog.borderRadius` | Скругление карточки (следует за `radius.base`) |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | Внутренние отступы карточки (следуют за `spacing.lg`) |
| `dialog.titleFontSize` / `dialog.contentFontSize` | Размеры шрифта заголовка / основного текста |
| `dialog.gap` | Расстояния между заголовком, текстом и зоной кнопок |
| `zIndex.modal` / `zIndex.modalBackdrop` | Семантика иерархии (оконко-уровневую иерархию управляет Compose `Dialog`) |
| `color.background.overlay` | Цвет маски |
| `color.background.default` | Цвет фона карточки |
| `color.text.primary` / `color.text.regular` | Цвета заголовка / основного текста |
| `shadow.base` | Тень карточки (Compose не поддерживает spread; для elevation берётся радиус размытия) |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `confirm-emits-onconfirm` | Подтверждение вызывает onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | После подтверждения вызывается onClose | То же |
| `cancel-emits-oncancel` | Отмена вызывает onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | При closable нажатие на маску вызывает onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | При не-closable нажатие на маску поглощается | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | В скрытом состоянии ничего не отрисовывается | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | Заголовок отрисовывает текст | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | Кнопка подтверждения типа danger берёт опасный цвет | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # JVM-часть
./gradlew :components:dialog:connectedAndroidTest             # Интерактивная часть (требуется устройство)
```
