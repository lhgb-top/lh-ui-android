package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "Botón",
  lede = "Botón de acción básico. Dispara una acción inmediata y admite tipo, tamaño, carga, deshabilitación, icono y fondo de patrón chino.",
  group = DocGroup.BASIC,
  contract = "Fuente del contrato: `lh-ui-core/specs/button.json` · Casos dorados: `cases/button.json` (8 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Tipos",
      prose = "Seis tipos determinan el color de fondo y el color del texto.",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"Botón principal\") }",
      ),
    ),
    DocSection(
      title = "Tamaños",
      prose = "Tres tamaños se mapean a los tokens `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "Estados",
      prose = "Ni el estado deshabilitado ni el de carga disparan `lhOnClick`; cuando ambos están presentes, la deshabilitación tiene prioridad (caso dorado `disabled-precedes-loading`).",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "Icono y fondo de patrón",
      prose = "El nombre del icono se resuelve contra el sistema de iconos local (`LhButtonIcons`, admite actualmente search / refresh / check / close; los nombres no registrados no se dibujan).\nEl fondo de patrón consume el token `color.decoration`: se superpone al color de fondo y queda debajo del texto, sin afectar al contraste.",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "Temas claro y oscuro",
      prose = "Cambia el tema del sitio en la esquina superior derecha y los colores de los botones dentro del marco del simulador cambian; en el dispositivo, se alterna con `LhTheme(isDark = true)`.",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "Devolución de llamada al pulsar; no se dispara cuando `disabled` o `loading` es true", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "Tipo de botón, determina el color de fondo y del texto", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "Tamaño del botón, mapeado a los tokens de tamaño", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "Si está deshabilitado", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "Si está cargando (muestra un indicador, no dispara lhOnClick)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "Nombre del icono (resuelto por el sistema de iconos local)", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "Si se dibuja el patrón decorativo chino", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "Contenido de texto del botón (el slot default del spec)", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("Valor", "Fondo", "Color del texto"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("Valor", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small` (32dp)", "`button.paddingHorizontal.small` (16dp)", "`button.fontSize.small` (13sp)", "`button.iconSize.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium` (40dp)", "`button.paddingHorizontal.medium` (20dp)", "`button.fontSize.medium` (14sp)", "`button.iconSize.medium` (18dp)")),
        DocRow(cells = listOf("`Large`", "`button.height.large` (48dp)", "`button.paddingHorizontal.large` (24dp)", "`button.fontSize.large` (16sp)", "`button.iconSize.large` (20dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "Tres alturas")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "Tres rellenos horizontales")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "Tres tamaños de fuente")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "Tres tamaños de icono")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "Radio de esquina (sigue `radius.base`) y ancho del borde")),
    DocRow(cells = listOf("`button.fontWeight`", "Peso de la fuente (sigue `typography.fontWeight.primary`)")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "Fondo / texto del tipo primary")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "Fondos de los tipos semánticos")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "Fondo / texto / borde del tipo predeterminado")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Tres colores del estado deshabilitado")),
    DocRow(cells = listOf("`color.decoration`", "Color del patrón")),
    DocRow(cells = listOf("`shadow.lighter`", "Elevación del tipo predeterminado")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "Animación de retroalimentación de la pulsación")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "La pulsación dispara onClick una sola vez", "`LhButtonTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-click`", "El estado deshabilitado no dispara", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "El estado de carga no dispara", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "La deshabilitación tiene prioridad", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "El tipo predeterminado renderiza texto", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "El tipo primary consume los tokens del color principal", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "El tamaño pequeño consume los tokens de tamaño small", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "El fondo de patrón consume el color decorativo", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "Un nombre registrado renderiza un vector; uno no registrado no dibuja nada", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # parte JVM",
    "./gradlew :components:button:connectedAndroidTest        # parte de interacción (requiere dispositivo)",
  ),
)
