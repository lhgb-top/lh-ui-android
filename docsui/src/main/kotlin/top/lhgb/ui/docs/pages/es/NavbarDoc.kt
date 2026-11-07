package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Barra de navegación",
  lede = "Barra de título superior de la página, con disposición de tres secciones (izquierda / centro / derecha), se usa a menudo para la navegación hacia atrás y la presentación del título.",
  group = DocGroup.OVERLAY,
  contract = "Fuente del contrato: `lh-ui-core/specs/navbar.json` · Casos dorados: `cases/navbar.json` (6 casos: el mapeo de tokens traducido a pruebas unitarias JVM; las pulsaciones izquierda y derecha traducidas a Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Uso",
      prose = "Las tres secciones son de igual anchura (cada una con `weight(1f)`); el título se centra horizontalmente en el ancho disponible; de forma predeterminada se dibuja la línea separadora inferior.",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"Título de la página\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"Atrás\",",
        "  rightText = \"Más\",",
        "  onLeftClick = { /* hacia atrás */ },",
        "  onRightClick = { /* más */ },",
        ")",
      ),
    ),
    DocSection(
      title = "Contenido de los lados",
      prose = "El texto y el icono pueden coexistir (el icono a la izquierda y el texto a la derecha, separados por `spacing.xs`); cuando uno de los lados no tiene ningún contenido, no responde a la pulsación\n(caso dorado `no-left-content-no-onleftclick`: ese nodo ni siquiera existe semánticamente como clicable).",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "Título central", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "Se dispara al pulsar la zona izquierda", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "Se dispara al pulsar la zona derecha", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "Texto del botón izquierdo; puede coexistir con leftIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "Nombre del icono izquierdo (sistema de iconos local, p. ej. `\"arrow-left\"`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "Texto del botón derecho; puede coexistir con rightIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "Nombre del icono derecho", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "Si se dibuja la línea separadora inferior", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "Contenido del título (prevalece sobre title, slot default del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "Contenido del lado izquierdo (prevalece sobre leftText/leftIcon, slot left del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "Contenido del lado derecho (prevalece sobre rightText/rightIcon, slot right del spec)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "Altura de la barra de navegación (44dp)")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "Relleno horizontal de los lados (sigue `spacing.md`)")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "Tamaño de fuente del título y de los textos de los lados")),
    DocRow(cells = listOf("`navbar.iconSize`", "Tamaño de los iconos de los lados (sigue `icon.size.medium`)")),
    DocRow(cells = listOf("`navbar.borderWidth`", "Ancho de la línea separadora inferior")),
    DocRow(cells = listOf("`zIndex.sticky`", "Alineación semántica de la jerarquía fija en la parte superior")),
    DocRow(cells = listOf("`color.background.default`", "Color de fondo")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "Colores del título / los textos de los lados")),
    DocRow(cells = listOf("`color.border.lighter`", "Color de la línea separadora inferior")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "La pulsación izquierda dispara onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test)")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "La pulsación derecha dispara onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "Sin contenido izquierdo, no se dispara", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "El título renderiza texto", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "La línea separadora consume el token del borde", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "La altura consume el token navbar", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # parte JVM",
    "./gradlew :components:navbar:connectedAndroidTest                # parte de interacción (requiere dispositivo)",
  ),
)
