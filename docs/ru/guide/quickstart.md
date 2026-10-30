# Быстрый старт

## Минимальный пример

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* обработать нажатие */ },
    ) {
      Text("Основная кнопка")
    }
  }
}
```

`LhTheme` — контейнер темы: все Lh-компоненты внутри него автоматически потребляют токены; без обёртки используется светлая палитра.

## Типы и размеры

```kotlin
// Шесть типов
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("По умолчанию") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("Основная") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("Успех") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("Предупреждение") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("Опасность") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("Информация") }

// Три размера
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("Маленькая") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("Средняя") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("Большая") }
```

## Состояния

```kotlin
// Отключена: не реагирует на нажатия, отображает отключённый стиль
LhButton(disabled = true, lhOnClick = {}) { Text("Отключена") }

// Загрузка: показывает индикатор и не вызывает lhOnClick
LhButton(loading = true, lhOnClick = {}) { Text("Загрузка") }

// Оба состояния: отключение имеет приоритет (золотой сценарий disabled-precedes-loading)
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("Отключение важнее") }
```

## Иконки и фоновый узор

```kotlin
// Имена иконок разрешаются локальной системой иконок (см. LhButtonIcons; незарегистрированные имена не отрисовываются)
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("Поиск") }

// Китайский фоновый узор (потребляет токен color.decoration)
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("Фоновый узор") }
```

## Светлая и тёмная темы

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // Все компоненты внутри автоматически переключаются на тёмные токены (предвычислены в core, без расчёта цветов во время выполнения)
}
```

## Модуль нативной документации

Помимо этого веб-сайта, в репозитории есть **модуль документации на нативном Android** (`:docsui`): его страницы 1:1 повторяют структуру этого сайта, но предпросмотр в нём — **настоящий интерактивный Compose в реальном времени** (кнопки нажимаются, в поля можно вводить текст, диалоги открываются), а не статичная HTML-имитация.

**Подключай и используй**: после добавления зависимости в любой Android-проект одна строка `LhDocs.launch(context)` открывает полную документацию (14 страниц компонентов + 5 страниц руководства + переключение светлой/тёмной темы + предпросмотр в реальном времени):

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // или Maven-координата top.lhgb.ui:docsui

// Любая точка входа (например, колбэк нажатия кнопки «Документация компонентов»)
LhDocs.launch(context)
```

Манифест библиотеки автоматически добавляет `LhDocsActivity` с `exported = false` и не внедряет в приложение-хозяин иконку запускателя.

**Отдельная сборка**: `:docsapp` — максимально тонкий application-модуль (всего одна Activity); отдельная сборка даёт готовое приложение «документация lh-ui»:

```bash
./gradlew :docsapp:installDebug
```

## Предпросмотр примеров компонентов

Нативный UI невозможно отрендерить в браузере в реальном времени, поэтому внешний вид на этом сайте показан через **встроенный статичный HTML-симулятор в рамке телефона**:

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">По умолчанию</button>
      <button class="sim-btn sim-btn--primary">Основная кнопка</button>
      <button class="sim-btn sim-btn--success">Успех</button>
      <button class="sim-btn sim-btn--warning">Предупреждение</button>
      <button class="sim-btn sim-btn--danger">Опасность</button>
      <button class="sim-btn sim-btn--info">Информация</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Маленькая</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">Большая</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Отключена</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Загрузка
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Поиск</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Фоновый узор</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML-имитация внешнего вида Android (Compose), не отрисовка на реальном устройстве; все значения стилей взяты из снимка токенов core, то есть из того же источника и с теми же значениями, что и на реальном устройстве. Реальный внешний вид — по <code>@Preview</code> и снимкам экранов устройства.</p>

> Тёмный режим симулятора связан с темой сайта: переключи тему в правом верхнем углу, и цвета в рамке телефона изменятся (тёмные токены предвычислены в core).
