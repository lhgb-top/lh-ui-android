# Toast (короткое уведомление)

Наложение в виде пилюли цвета туши, самостоятельно управляющее видимостью и таймером; используется для лёгкой обратной связи по действию. Не блокирует действия пользователя.

> Источник контракта: [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> Золотые сценарии: [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast) (6 сценариев: 4 для конечного автомата + 2 для отрисовки; все переведены в локальные тесты)

## Использование

toast — **самостоятельный конечный автомат**: состояние хранится в `LhToastState`, хозяин вызывает `show(text)` для отображения и `hide()` для скрытия;
по истечении таймера происходит автоматическое скрытие и вызывается `onClose`. В видимом состоянии компонент самостоятельно вызывает `tick` кадр за кадром; хозяин также может вызывать его сам, перехватив управление таймером.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">Действие выполнено</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
val state = rememberLhToastState(onClose = { /* опционально: зафиксировать закрытие */ })
Column {
  LhButton(lhOnClick = { state.show("Сохранено") }) { Text("Показать") }
  LhToast(state = state)
}
```

## Семантические типы

Для типов, отличных от `default`, перед текстом отрисовывается семантическая точка диаметром, равным размеру шрифта.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Типы Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>Успех</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>Предупреждение</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>Ошибка</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>Основное сообщение</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## Позиционирование

`lhPosition` принимает `top` / `center` / `bottom`; у краёв остаётся отступ `spacing.xl`; ширина определяется измерением текста и ограничивается 80% доступной ширины.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Позиции Toast</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">Сверху</div>
    <div class="sim-toast sim-toast--bottom">Снизу</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `state` | Конечный автомат уведомления (хранит видимость, текст и таймер) | `LhToastState` | — |
| `type` | Семантический тип; для значений кроме default отрисовывается семантическая точка | `LhToastType` | `default` |
| `lhPosition` | Позиция в доступной области | `LhToastPosition` | `center` |
| `modifier` | Модификатор (применяется к пилюле) | `Modifier` | `Modifier` |

### LhToastState

| Член | Описание |
| --- | --- |
| `show(text: String)` | Показывает уведомление и сбрасывает таймер |
| `hide()` | Скрывает немедленно, **не вызывая onClose** |
| `tick(deltaMs: Int): Boolean` | Продвигает таймер; по истечении скрывает, вызывает onClose и возвращает true |
| `visible` / `text` / `duration` | Текущее состояние (наблюдаемо) |

Фабрика: `rememberLhToastState(duration = 2000, onClose = {})`.

### LhToastType / LhToastPosition

| Значение | Цвет точки |
| --- | --- |
| `default` | Без точки |
| `primary` / `success` / `warning` / `danger` / `info` | Соответствующий семантический цвет |

`LhToastPosition`: `top` / `center` / `bottom`.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `toast.maxWidth` | Максимальная ширина пилюли (240dp; фактически берётся меньшее из 80% доступной ширины и этого значения) |
| `toast.borderRadius` | Скругление пилюли (следует за `radius.base`) |
| `toast.paddingHorizontal` / `toast.paddingVertical` | Внутренние отступы пилюли |
| `toast.fontSize` | Размер шрифта текста (диаметр точки равен этому значению) |
| `toast.gap` | Расстояние между точкой и текстом |
| `zIndex.toast` | Семантика иерархии (оконко-уровневую иерархию обеспечивает Compose `Popup`) |
| `color.background.overlay` (отображение туши на уровне темы) | Цвет фона пилюли |
| `color.onPrimary` | Цвет текста |
| `color.{primary,success,warning,danger,info}.default` | Цвета семантических точек |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `show-makes-visible` | После show уведомление видно и отрисовывает текст | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | По истечении автоматическое скрытие и вызов onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | До истечения остаётся видимым и не вызывает onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide не вызывает onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | Тип success потребляет цвет успеха | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | В скрытом состоянии ничего не отрисовывается | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # JVM-часть
./gradlew :components:toast:connectedAndroidTest                 # Интерактивная часть (требуется устройство)
```
