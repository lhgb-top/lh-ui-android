package top.lhgb.ui.docs.pages.ru

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Переключатель",
  lede = "Двухпозиционный переключатель «капсульная дорожка + круглый ползунок»; поддерживает состояние загрузки.",
  group = DocGroup.FORM,
  contract = "Источник контракта: `lh-ui-core/specs/switch.json` · Золотые сценарии: `cases/switch.json` (6 сценариев, переведены в JVM-модульные тесты + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Состояния",
      prose = "Во включенном состоянии дорожка берёт основной цвет, в выключенном — цвет границы; диаметр ползунка = высота - 2 × `switch.gap`, анимация перемещения — `motion.duration.base`.",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "Размеры",
      prose = "Три размера отображаются на токены `switch.width` / `switch.height`.",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Включён ли переключатель (обязательный)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Колбэк изменения состояния; передаёт новое состояние", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Отключён ли переключатель", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "Идёт ли загрузка (на месте ползунка показывается кольцо загрузки, нажатия не реагируют)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "Размер", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "Модификатор", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("Значение", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small` (36dp)", "`switch.height.small` (20dp)")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium` (44dp)", "`switch.height.medium` (24dp)")),
        DocRow(cells = listOf("`Large`", "`switch.width.large` (52dp)", "`switch.height.large` (28dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "Три ширины дорожки")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "Три высоты дорожки")),
    DocRow(cells = listOf("`switch.gap`", "Зазор между ползунком и дорожкой (диаметр ползунка = высота - 2 × gap)")),
    DocRow(cells = listOf("`color.primary.default`", "Цвет дорожки во включенном состоянии")),
    DocRow(cells = listOf("`color.border.base`", "Цвет дорожки в выключенном состоянии")),
    DocRow(cells = listOf("`color.onPrimary`", "Цвет ползунка")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "Цвета дорожки / ползунка в отключенном состоянии")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "Анимация перемещения ползунка")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Толщина кольца загрузки (повторно используется)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "Нажатие переключает состояние и emits onChange", "`LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "Нажатие во включенном состоянии выключает", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "Отключенное состояние не вызывает изменений", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "Состояние загрузки не вызывает изменений", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "Включенное состояние потребляет токен основного цвета", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "Выключенное состояние потребляет токен границы", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # JVM-часть",
    "./gradlew :components:switch:connectedAndroidTest            # Интерактивная часть (требуется устройство)",
  ),
)
