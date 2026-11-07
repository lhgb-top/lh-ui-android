package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Загрузка",
  lede = "Индикатор загрузки с кольцевой анимацией вращения; длительность берётся из `motion.duration.base` (миллисекунды).",
  group = DocGroup.BASIC,
  contract = "Источник контракта: `lh-ui-core/specs/loading.json` · Золотые сценарии: `cases/loading.json` (5 сценариев; чисто демонстрационный компонент, переведены в JVM-модульные тесты)",
  sections = listOf(
    DocSection(
      title = "Размеры и тона",
      prose = "Три размера отображаются на токены `loading.size.*`, толщина кольца берётся из `loading.strokeWidth`, тона — семантические цвета.",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "Диаметр индикатора", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "Цвет кольца", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("Значение", "Токен размера"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`loading.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("Значение", "Токен цвета"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.regular`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "Три диаметра")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Толщина кольца")),
    DocRow(cells = listOf("`color.text.regular`", "Тон default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Семантические тона")),
    DocRow(cells = listOf("`motion.duration.base`", "Длительность одного оборота (на устройстве — целое число миллисекунд)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "Средний размер берёт размерный токен medium", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "Большой размер берёт размерный токен large", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "Тон primary берёт токен основного цвета", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "Тон success берёт токен цвета успеха", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "Кольцевой индикатор отрисовывается (толщина кольца потребляет токен)", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # JVM-часть (чисто демонстрационный, интерактивных сценариев нет)",
  ),
)
