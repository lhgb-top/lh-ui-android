package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "Значок",
  lede = "Базовый компонент иконок. Иконки других компонентов (cell / input / navbar и др.) отрисовываются через локальный реестр этого компонента.",
  group = DocGroup.BASIC,
  contract = "Источник контракта: `lh-ui-core/specs/icon.json` · Золотые сценарии: `cases/icon.json` (5 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Размеры и тона",
      prose = "Три размера отображаются на токены `icon.size.*`; тон `default` берёт `color.text.primary`, остальные — семантические цвета.",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "Семантические тона",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "Реестр иконок",
      prose = "`LhIcons` — локальный реестр «имя иконки → Material `ImageVector`»; незарегистрированные имена возвращают `null` (ничего не отрисовывается, исключение не выбрасывается):",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "Имя иконки (разрешается реестром `LhIcons`)", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "Размер иконки", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Семантический тон", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "Описание для доступности; по умолчанию берётся имя иконки, явный `null` объявляет иконку декоративной", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "Колбэк нажатия; при null иконка не реагирует на нажатия", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("Значение", "Токен размера"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`icon.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("Значение", "Токен цвета"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "Три размера")),
    DocRow(cells = listOf("`color.text.primary`", "Тон default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Семантические тона")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "Тона regular / secondary")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "Нажатие вызывает onClick", "`LhIconTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "Тон по умолчанию берёт токен основного цвета текста", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "Тон primary берёт токен основного цвета", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "Малый размер берёт размерный токен small (обратное утверждение: large не применён)", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "Зарегистрированное имя разрешается в вектор; незарегистрированное не отрисовывается", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # JVM-часть",
    "./gradlew :components:icon:connectedAndroidTest            # Интерактивная часть (требуется устройство)",
  ),
)
