package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "Быстрый старт",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Минимальный пример",
      prose = "`LhTheme` — контейнер темы: все Lh-компоненты внутри него автоматически потребляют токены; без обёртки используется светлая палитра.",
      code = listOf(
        "import top.lhgb.ui.button.LhButton",
        "import top.lhgb.ui.button.LhButtonType",
        "import top.lhgb.ui.theme.LhTheme",
        "",
        "@Composable",
        "fun Demo() {",
        "  LhTheme {",
        "    LhButton(",
        "      type = LhButtonType.Primary,",
        "      lhOnClick = { /* обработать нажатие */ },",
        "    ) {",
        "      Text(\"Основная кнопка\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "Типы и размеры",
      prose = "",
      code = listOf(
        "// Шесть типов",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"По умолчанию\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"Основная\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"Успех\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"Предупреждение\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"Опасность\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"Информация\") }",
        "",
        "// Три размера",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"Маленькая\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"Средняя\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"Большая\") }",
      ),
    ),
    DocSection(
      title = "Состояния",
      prose = "",
      code = listOf(
        "// Отключена: не реагирует на нажатия, отображает отключённый стиль",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"Отключена\") }",
        "",
        "// Загрузка: показывает индикатор и не вызывает lhOnClick",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"Загрузка\") }",
        "",
        "// Оба состояния: отключение имеет приоритет (золотой сценарий disabled-precedes-loading)",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"Отключение важнее\") }",
      ),
    ),
    DocSection(
      title = "Иконки и фоновый узор",
      prose = "",
      code = listOf(
        "// Имена иконок разрешаются локальной системой иконок (см. LhButtonIcons; незарегистрированные имена не отрисовываются)",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"Поиск\") }",
        "",
        "// Китайский фоновый узор (потребляет токен color.decoration)",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"Фоновый узор\") }",
      ),
    ),
    DocSection(
      title = "Светлая и тёмная темы",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // Все компоненты внутри автоматически переключаются на тёмные токены (предвычислены в core, без расчёта цветов во время выполнения)",
        "}",
      ),
    ),
    DocSection(
      title = "Модуль нативной документации",
      prose = "Помимо этого веб-сайта, в репозитории есть модуль документации на нативном Android (`:docsui`): его страницы 1:1 повторяют структуру этого сайта, но предпросмотр в нём — настоящий интерактивный Compose в реальном времени (кнопки нажимаются, в поля можно вводить текст, диалоги открываются), а не статичная HTML-имитация.\nПодключай и используй: после добавления зависимости в любой Android-проект одна строка `LhDocs.launch(context)` открывает полную документацию (14 страниц компонентов + 5 страниц руководства + переключение светлой/тёмной темы + предпросмотр в реальном времени):\nМанифест библиотеки автоматически добавляет `LhDocsActivity` с `exported = false` и не внедряет в приложение-хозяин иконку запускателя.\nОтдельная сборка: `:docsapp` — максимально тонкий application-модуль (всего одна Activity); отдельная сборка даёт готовое приложение «документация lh-ui»:",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // или Maven-координата top.lhgb.ui:docsui",
        "",
        "// Любая точка входа (например, колбэк нажатия кнопки «Документация компонентов»)",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "Предпросмотр примеров компонентов",
      prose = "Нативный UI невозможно отрендерить в браузере в реальном времени, поэтому внешний вид на этом сайте показан через встроенный статичный HTML-симулятор в рамке телефона:\nТёмный режим симулятора связан с темой сайта: переключи тему в правом верхнем углу, и цвета в рамке телефона изменятся (тёмные токены предвычислены в core).",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
