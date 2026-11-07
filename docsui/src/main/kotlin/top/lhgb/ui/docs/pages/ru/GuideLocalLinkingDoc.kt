package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "Локальная линковка",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Соглашение о семейной раскладке",
      prose = "Когда все репозитории лежат в одном родительском каталоге, токены подключаются напрямую локально; если репозиториев-братьев нет (CI клонирует один репозиторий), выполняется автоматический откат на npm-пакет.\nЭто эквивалентно механизму vite-alias с проверкой наличия в направлении web (генеральный план D18).",
      code = listOf(
        "lh-ui/                        # родительский каталог семейства (не репозиторий)",
        "├── lh-ui-core/               # источник токенов + codegen",
        "├── lh-ui-android/            # этот репозиторий",
        "├── lh-ui-ios/                # репозитории-братья",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "Трёхуровневая проверка наличия",
      prose = "`scripts/link-tokens.mjs` ищет артефакты токенов в порядке приоритета:",
    ),
    DocSection(
      title = "Повседневный процесс",
      prose = "",
    ),
    DocSection(
      title = "CI-гейт",
      prose = "",
    ),
    DocSection(
      title = "Запрещённые действия",
      prose = "• ❌ Ручное редактирование `core/src/main/kotlin/top/lhgb/ui/tokens/` (генерируемое, gitignore)\n• ❌ Коммит сгенерированных файлов токенов в репозиторий\n• ❌ Цвета/интервалы литералами в компонентах (например `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)\n• ❌ Запись зависимостей в форме `file:` / `link:` в `build.gradle.kts` и коммит (линковка только для локальной разработки)",
    ),
    DocSection(
      title = "Устранение неполадок",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # проверяет, синхронизированы ли токены (сравнение количества файлов; если нет — код выхода 1)",
  ),
)
