package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideIntroductionDoc = DocEntry(
  id = "guide-introduction",
  title = "Введение",
  lede = "lh-ui-android — Android-реализация нативного направления семейства lh-ui: библиотека UI-компонентов на Kotlin + Jetpack Compose.",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Место в семействе",
      prose = "• Центр сборки: `lh-ui-core` — единый источник токенов нативного направления (`tokens/*.json`), компонентные контракты (`specs/`), золотые сценарии (`cases/`) и инструменты codegen. Он генерирует каркас токенов на Kotlin (`LhTokens` / `LhDarkTokens`) и не генерирует никакой реализации UI.\n• Задача этого репозитория: писать UI на Compose вручную, подключать тему, привязывать enum-значения props и значения по умолчанию, переводить золотые сценарии в локальные тесты.\n• Независимость от направления web: нет зависимости от `@lh-ui/share`, ограничения на порядок релизов взаёмно отсутствуют. Визуальная согласованность поддерживается вручную по *Базовому языку дизайна*.",
      code = listOf(
        "                        ┌── направление web: share (TS core + SCSS) → plus / vue2 / react / uniapp",
        "семейство lh-ui (один бренд)─────┤",
        "                        └── направление native: core (JSON-токены + контракты + codegen) → android / ios / harmony / windows",
        "                                                                    ↑ этот репозиторий",
      ),
    ),
    DocSection(
      title = "Дизайн-язык",
      prose = "Тема в китайском стиле: основной цвет «青花瓷» (фарфорово-синий, `#2B4C7C`), фон из бумаги «宣纸» (`#FAF6F0`), успех — «松花绿» (сосново-зелёный), предупреждение — «藤黄», опасность — «朱砂», стек шрифтов с засечками (Noto Serif SC → STSong → SimSun). Все визуальные значения — токены; в компонентах нет ни одного литерала.",
    ),
    DocSection(
      title = "Почему Compose",
      prose = "• Декларативный UI как полноценный гражданин платформы, ментально однородный со SwiftUI / ArkUI — легче согласовывать контракты между платформами\n• Никакого JS во время выполнения, никакого HTML/CSS-моста — соответствует требованиям нативной производительности и встраиваемости\n• `@Preview` — это документация, `@Composable` — это компонент: симметрия с опытом разработки на SFC из направления web",
    ),
    DocSection(
      title = "Текущее состояние",
      prose = "• Доставлены все 14 компонентов P0 (button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar). Каждый компонент — полный цикл в одной парадигме: потребление токенов + разбор внешнего вида + Compose UI + JVM-тесты + Compose UI Test + `@Preview` + страница документации.\n• Доставлен нативный модуль документации `:docsui`: подключай и используй (`LhDocs.launch(context)`) + отдельная сборка (`:docsapp`); предпросмотр — настоящий интерактивный Compose в реальном времени, см. Быстрый старт.",
    ),
  ),
)
