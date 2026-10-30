# Значок

Базовый компонент иконок. Иконки других компонентов (cell / input / navbar и др.) отрисовываются через локальный реестр этого компонента.

> Источник контракта: [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> Золотые сценарии: [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon) (5 сценариев, переведены в JVM-модульные тесты + Compose UI Test)

## Размеры и тона

Три размера отображаются на токены `icon.size.*`; тон `default` берёт `color.text.primary`, остальные — семантические цвета.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Размеры Icon</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">Цвет по умолчанию (text.primary)</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## Семантические тона

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Тона Icon</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве</p>

## Реестр иконок

`LhIcons` — локальный реестр «имя иконки → Material `ImageVector`»; незарегистрированные имена возвращают `null` (ничего не отрисовывается, исключение не выбрасывается):

| Имя иконки | Вектор Material |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| Проп | Описание | Тип | По умолчанию |
| --- | --- | --- | --- |
| `name` | Имя иконки (разрешается реестром `LhIcons`) | `String` | — |
| `lhSize` | Размер иконки | `LhIconSize` | `Medium` |
| `tone` | Семантический тон | `LhIconTone` | `Default` |
| `contentDescription` | Описание для доступности; по умолчанию берётся имя иконки, явный `null` объявляет иконку декоративной | `String?` | `name` |
| `lhOnClick` | Колбэк нажатия; при null иконка не реагирует на нажатия | `(() -> Unit)?` | `null` |
| `modifier` | Модификатор | `Modifier` | `Modifier` |

### LhIconSize

| Значение | Токен размера |
| --- | --- |
| `Small` | `icon.size.small` (16dp) |
| `Medium` | `icon.size.medium` (20dp) |
| `Large` | `icon.size.large` (24dp) |

### LhIconTone

| Значение | Токен цвета |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## Справочник токенов

| Токен | Назначение |
| --- | --- |
| `icon.size.{small,medium,large}` | Три размера |
| `color.text.primary` | Тон default |
| `color.{primary,success,warning,danger,info}.default` | Семантические тона |
| `color.text.{regular,secondary}` | Тона regular / secondary |

## Справочник золотых сценариев

| case id | Утверждение | Локальный тест |
| --- | --- | --- |
| `click-emits-onclick` | Нажатие вызывает onClick | `LhIconTest.click_emits_onClick` (Compose UI Test) |
| `default-tone-uses-text-primary` | Тон по умолчанию берёт токен основного цвета текста | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | Тон primary берёт токен основного цвета | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | Малый размер берёт размерный токен small (обратное утверждение: large не применён) | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | Зарегистрированное имя разрешается в вектор; незарегистрированное не отрисовывается | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # JVM-часть
./gradlew :components:icon:connectedAndroidTest            # Интерактивная часть (требуется устройство)
```
