package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Interruptor",
  lede = "Interruptor de dos estados con pista en cápsula y pulgar circular; admite el marcador de posición del estado de carga.",
  group = DocGroup.FORM,
  contract = "Fuente del contrato: `lh-ui-core/specs/switch.json` · Casos dorados: `cases/switch.json` (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Estados",
      prose = "Activado: la pista toma el color principal; desactivado: la pista toma el color del borde; el diámetro del pulgar = altura - 2 × `switch.gap`; la animación de desplazamiento toma `motion.duration.base`.",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "Tamaños",
      prose = "Tres tamaños se mapean a los tokens `switch.width` / `switch.height`.",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "Si está activado (obligatorio)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "Devolución de llamada de cambio de estado, con el nuevo valor", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "Si está deshabilitado", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "Si está cargando (se muestra un anillo de carga en la posición del pulgar; no responde a la pulsación)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "Tamaño", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("Valor", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small` (36dp)", "`switch.height.small` (20dp)")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium` (44dp)", "`switch.height.medium` (24dp)")),
        DocRow(cells = listOf("`Large`", "`switch.width.large` (52dp)", "`switch.height.large` (28dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "Tres anchos de pista")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "Tres alturas de pista")),
    DocRow(cells = listOf("`switch.gap`", "Hueco entre el pulgar y la pista (diámetro del pulgar = altura - 2 × gap)")),
    DocRow(cells = listOf("`color.primary.default`", "Color de la pista activada")),
    DocRow(cells = listOf("`color.border.base`", "Color de la pista desactivada")),
    DocRow(cells = listOf("`color.onPrimary`", "Color del pulgar")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "Colores de la pista / el pulgar deshabilitados")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "Animación de desplazamiento del pulgar")),
    DocRow(cells = listOf("`loading.strokeWidth`", "Ancho del anillo de carga (reutilizado)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "La pulsación alterna y dispara onChange", "`LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "Una pulsación estando activado lo desactiva", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "El estado deshabilitado no dispara", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "El estado de carga no dispara", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "El estado activado consume el token del color principal", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "El estado desactivado consume el token del borde", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # parte JVM",
    "./gradlew :components:switch:connectedAndroidTest            # parte de interacción (requiere dispositivo)",
  ),
)
