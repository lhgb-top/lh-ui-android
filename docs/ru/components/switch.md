# Переключатель

Двухпозиционный переключатель «капсульная дорожка + круглый ползунок»; поддерживает состояние загрузки.

> Источник контракта: [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> Золотые сценарии: [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch) (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Состояния

Во включенном состоянии дорожка берёт основной цвет, в выключенном — цвет границы; диаметр ползунка = высота - 2 × `switch.gap`, анимация перемещения — `motion.duration.base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>Выключен</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Включён</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>Отключён (выкл.)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>Отключён (вкл.)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>Загрузка (на месте ползунка показывается кольцо загрузки, нажатия не реагируют)</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## Размеры

Три размера отображаются на токены `switch.width` / `switch.height`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `checked` | Включён ли переключатель (обязательный) | `Boolean` | — |
| `onChange` | Колбэк изменения состояния; передаёт новое состояние | `(Boolean) -> Unit` | — |
| `disabled` | Отключён ли переключатель | `Boolean` | `false` |
| `loading` | Идёт ли загрузка (на месте ползунка показывается кольцо загрузки, нажатия не реагируют) | `Boolean` | `false` |
| `lhSize` | Размер | `LhSwitchSize` | `Medium` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

### LhSwitchSize

| Значение | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small` (36dp) | `switch.height.small` (20dp) |
| `Medium` | `switch.width.medium` (44dp) | `switch.height.medium` (24dp) |
| `Large` | `switch.width.large` (52dp) | `switch.height.large` (28dp) |

Дорожка имеет форму капсулы (скругление равно половине высоты); область нажатия охватывает дорожку — переключатель меняет состояние и вызывает `onChange`.

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `switch.width.{small,medium,large}` | Три ширины дорожки |
| `switch.height.{small,medium,large}` | Три высоты дорожки |
| `switch.gap` | Зазор между ползунком и дорожкой (диаметр ползунка = высота - 2 × gap) |
| `color.primary.default` | Цвет дорожки во включенном состоянии |
| `color.border.base` | Цвет дорожки в выключенном состоянии |
| `color.onPrimary` | Цвет ползунка |
| `color.disabled.{bg,text}` | Цвета дорожки / ползунка в отключенном состоянии |
| `motion.duration.base` / `motion.easing.easeInOut` | Анимация перемещения ползунка |
| `loading.strokeWidth` | Толщина кольца загрузки (повторно используется) |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | Нажатие переключает состояние и emits onChange | `LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-turns-off` | Нажатие во включенном состоянии выключает | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | Отключенное состояние не вызывает изменений | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | Состояние загрузки не вызывает изменений | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | Включенное состояние потребляет токен основного цвета | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | Выключенное состояние потребляет токен границы | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # JVM-часть
./gradlew :components:switch:connectedAndroidTest            # Интерактивная часть (требуется устройство)
```
