package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "Inicio rápido",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Ejemplo mínimo",
      prose = "`LhTheme` es el contenedor de temas: todos los componentes Lh de su interior consumen los tokens automáticamente; sin envoltorio, se recurre a la paleta clara.",
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
        "      lhOnClick = { /* manejar el clic */ },",
        "    ) {",
        "      Text(\"Botón principal\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "Tipos y tamaños",
      prose = "",
      code = listOf(
        "// Seis tipos",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"Predeterminado\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"Principal\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"Éxito\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"Advertencia\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"Peligro\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"Información\") }",
        "",
        "// Tres tamaños",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"Pequeño\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"Mediano\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"Grande\") }",
      ),
    ),
    DocSection(
      title = "Estados",
      prose = "",
      code = listOf(
        "// Deshabilitado: no responde al clic, muestra el aspecto deshabilitado",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"Deshabilitado\") }",
        "",
        "// Cargando: muestra un indicador, no dispara lhOnClick",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"Cargando\") }",
        "",
        "// Ambos presentes: la deshabilitación tiene prioridad (caso dorado disabled-precedes-loading)",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"Deshabilitado prioritario\") }",
      ),
    ),
    DocSection(
      title = "Icono y fondo de patrón",
      prose = "",
      code = listOf(
        "// El nombre del icono se resuelve contra el sistema de iconos local (véase LhButtonIcons; los nombres no registrados no se dibujan)",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"Buscar\") }",
        "",
        "// Fondo de patrón chino (consume el token color.decoration)",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"Fondo de patrón\") }",
      ),
    ),
    DocSection(
      title = "Temas claro y oscuro",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // Todos los componentes del interior cambian automáticamente a los tokens oscuros (valores precalculados por core, sin mezcla en tiempo de ejecución)",
        "}",
      ),
    ),
    DocSection(
      title = "Módulo de documentación nativa",
      prose = "Además de este sitio web, el repositorio incluye un módulo de documentación nativa Android (`:docsui`): sus páginas se alinean 1:1 con la estructura de este sitio, pero las vistas previas son Compose real, renderizado en vivo e interactivo (los botones se pueden pulsar, las entradas aceptan texto, los diálogos se abren), no una simulación HTML estática.\nListo para integrar: cualquier proyecto Android que añada la dependencia puede abrir la documentación completa con una sola línea, `LhDocs.launch(context)` (14 páginas de componentes + 5 páginas de guía + conmutación claro/oscuro + vistas previas en vivo):\nEl manifest de la biblioteca fusiona automáticamente una `LhDocsActivity` con `exported = false`, sin inyectar un icono de lanzador en la aplicación host.\nEmpaquetado independiente: `:docsapp` es un módulo de aplicación mínima (una sola Activity); al empaquetarlo por separado se obtiene la APP «documentación de lh-ui»:",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // o la coordenada Maven top.lhgb.ui:docsui",
        "",
        "// cualquier punto de entrada (p. ej. la devolución de llamada de un botón «Documentación de componentes»)",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "Vista previa de los ejemplos de componentes",
      prose = "La UI nativa no se puede renderizar en tiempo real en el navegador; este sitio muestra el aspecto con un simulador de marco de teléfono en HTML estático en línea:\nEl modo oscuro del simulador está vinculado con el sitio: cambia el tema en la esquina superior derecha y los colores del marco del teléfono cambian (los tokens oscuros están precalculados por core).",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
