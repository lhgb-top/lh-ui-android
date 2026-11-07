package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "Temas y modo oscuro",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Modelo de tokens",
      prose = "La fuente única de tokens del linaje nativo está en `lh-ui-core/tokens/*.json`; el codegen genera los artefactos Android:\nEsto es coherente con ios / harmony / windows (objetos paralelos) y difiere del lado de la documentación (fusión JSON superficial).\nLa capa de temas está diseñada en consecuencia: `LhColors` solo transporta los colores; las dimensiones se toman directamente de `LhTokens` en los componentes.",
    ),
    DocSection(
      title = "LhColors",
      prose = "`LightLhColors` / `DarkLhColors` aplanan los dos object generados en una paleta inmutable:",
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
      prose = "Sin envoltura de `LhTheme` se recurre a `LightLhColors` (claro por defecto).",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // LocalLhColors inyecta DarkLhColors; los componentes del interior lo consumen automáticamente",
        "}",
      ),
    ),
    DocSection(
      title = "Precálculo del oscuro (importante)",
      prose = "El modo oscuro del linaje web se evalúa dinámicamente con la función CSS `color-mix()`; al no existir una cascada CSS en nativo, core ya ha precalculado las expresiones oscuras como literales (p. ej., `rgba(61,100,158,0.25)`, `#B1C1D8`). Consecuencia (plan maestro R-n6):\nAl cambiar el color principal del preset, los valores oscuros precalculados no lo siguen automáticamente: hay que recalcularlos a mano y actualizar\n`lh-ui-core/tokens/color-dark.json`, y luego refrescar la *Línea base del lenguaje de diseño*.",
    ),
    DocSection(
      title = "Consulta rápida de tokens",
      prose = "La tabla completa de tokens (claro/oscuro/descripción) está en la consulta rápida de tokens de lh-ui-core, o en `dist/docs/tokens.md` tras ejecutar `pnpm codegen` en el repositorio core. El mapeo de tokens a nivel de componente está en Referencia de tokens de Button.",
    ),
    DocSection(
      title = "Presets personalizados (reservado)",
      prose = "`LhTheme(colors = ...)` admite una paleta personalizada; la capacidad de presets temáticos se abrirá en próximas versiones, junto con los tokens de preset temático de core.",
    ),
  ),
)
