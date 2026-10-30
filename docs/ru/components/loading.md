# Загрузка

Индикатор загрузки с кольцевой анимацией вращения; длительность берётся из `motion.duration.base` (миллисекунды).

> Источник контракта: [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> Золотые сценарии: [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading) (5 сценариев; чисто демонстрационный компонент, переведены в JVM-модульные тесты)

## Размеры и тона

Три размера отображаются на токены `loading.size.*`, толщина кольца берётся из `loading.strokeWidth`, тона — семантические цвета.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">Загрузка…</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `lhSize` | Диаметр индикатора | `LhLoadingSize` | `Medium` |
| `tone` | Цвет кольца | `LhLoadingTone` | `Primary` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

### LhLoadingSize

| Значение | Токен размера |
| --- | --- |
| `Small` | `loading.size.small` (16dp) |
| `Medium` | `loading.size.medium` (20dp) |
| `Large` | `loading.size.large` (24dp) |

### LhLoadingTone

| Значение | Токен цвета |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `loading.size.{small,medium,large}` | Три диаметра |
| `loading.strokeWidth` | Толщина кольца |
| `color.text.regular` | Тон default |
| `color.{primary,success,warning,danger,info}.default` | Семантические тона |
| `motion.duration.base` | Длительность одного оборота (на устройстве — целое число миллисекунд) |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `medium-size-uses-medium-token` | Средний размер берёт размерный токен medium | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | Большой размер берёт размерный токен large | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | Тон primary берёт токен основного цвета | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | Тон success берёт токен цвета успеха | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | Кольцевой индикатор отрисовывается (толщина кольца потребляет токен) | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # JVM-часть (чисто демонстрационный, интерактивных сценариев нет)
```
