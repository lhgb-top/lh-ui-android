package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "Темы и тёмный режим",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Модель токенов",
      prose = "Единый источник токенов нативного направления — `lh-ui-core/tokens/*.json`, codegen генерирует Android-артефакты:\nТак же, как в ios / harmony / windows (параллельные объекты), в отличие от сайта документации (поверхностное слияние JSON).\nТематический слой спроектирован соответственно: `LhColors` хранит только цвета, а размеры компоненты берут напрямую из `LhTokens`.",
    ),
    DocSection(
      title = "LhColors",
      prose = "`LightLhColors` / `DarkLhColors` разворачивают два сгенерированных object в одну неизменяемую палитру:",
      code = listOf(
        "@Immutable",
        "data class LhColors(",
        "  val primary: Color, val onPrimary: Color,",
        "  val success: Color, val warning: Color, val danger: Color, val info: Color,",
        "  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */",
        ")",
        "",
        "val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)",
        "val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)",
      ),
    ),
    DocSection(
      title = "LhTheme",
      prose = "Без обёртки `LhTheme` используется откат на `LightLhColors` (по умолчанию светлый режим).",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // LocalLhColors внедряет DarkLhColors, компоненты внутри потребляют его автоматически",
        "}",
      ),
    ),
    DocSection(
      title = "Предвычисление тёмного режима (важно)",
      prose = "В направлении web тёмный режим вычисляется динамически через CSS `color-mix()`, но в нативном нет CSS-каскада, поэтому core предвычислил тёмные выражения в литералы (например `rgba(61,100,158,0.25)`, `#B1C1D8`). Следствие (генеральный план R-n6):\nПри изменении основного цвета пресета предвычисленные тёмные значения не обновляются автоматически — их нужно пересчитать вручную и обновить\n`lh-ui-core/tokens/color-dark.json`, затем обновить *Базовый стандарт языка дизайна*.",
    ),
    DocSection(
      title = "Справочник токенов",
      prose = "Полная таблица токенов (светлый/тёмный/описание) — в справочнике токенов lh-ui-core,\nлибо в `dist/docs/tokens.md` после запуска `pnpm codegen` в репозитории core. Отображение токенов на уровне компонентов — в\nсправочнике токенов кнопки Button.",
    ),
    DocSection(
      title = "Пользовательские пресеты (зарезервировано)",
      prose = "`LhTheme(colors = ...)` принимает пользовательскую палитру; возможность пресетов темы будет открыта в следующих версиях вместе с токенами пресетов темы из core.",
    ),
  ),
)
