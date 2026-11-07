package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "Enlace local",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Convención de disposición de la familia",
      prose = "Cuando todos los repositorios comparten el mismo directorio padre, los tokens se conectan localmente en directo; cuando un repositorio hermano no existe (clone monorepositorio en CI), se recurre automáticamente al paquete npm.\nMecanismo isomorfo a la guarda de vite alias del linaje web (plan maestro D18).",
      code = listOf(
        "lh-ui/                        # directorio padre de la familia (no es un repositorio)",
        "├── lh-ui-core/               # fuente única de tokens + codegen",
        "├── lh-ui-android/            # este repositorio",
        "├── lh-ui-ios/                # repositorio hermano",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "Guarda de tres niveles",
      prose = "`scripts/link-tokens.mjs` busca los artefactos de tokens por orden de prioridad:",
    ),
    DocSection(
      title = "Flujo diario",
      prose = "",
    ),
    DocSection(
      title = "Guarda CI",
      prose = "",
    ),
    DocSection(
      title = "Prohibiciones",
      prose = "• ❌ Modificar a mano `core/src/main/kotlin/top/lhgb/ui/tokens/` (artefacto generado, gitignore)\n• ❌ Hacer commit de los archivos de tokens generados\n• ❌ Escribir literales de color/espaciado en los componentes (como `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)\n• ❌ Escribir dependencias con forma `file:` / `link:` en `build.gradle.kts` y subirlas al repositorio (la depuración local es solo para el entorno local)",
    ),
    DocSection(
      title = "Solución de problemas",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # comprueba si los tokens están sincronizados (comparación del número de archivos; código de salida 1 si no lo están)",
  ),
)
