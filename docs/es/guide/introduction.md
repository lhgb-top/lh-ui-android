# Introducción

**lh-ui-android** es la implementación Android del **linaje nativo** de la familia lh-ui: una biblioteca de componentes UI basada en **Kotlin + Jetpack Compose**.

## Posición en la familia

```
                     ┌── linaje web: share (core TS + SCSS) → plus / vue2 / react / uniapp
familia lh-ui ───────┤ (misma marca)
                     └── linaje nativo: core (tokens JSON + contratos + codegen) → android / ios / harmony / windows
                                                                                ↑ este repositorio
```

- **Centro de construcción**: [`lh-ui-core`](https://www.lhgb.top) es la fuente única de tokens del linaje nativo (`tokens/*.json`), los contratos de los componentes (`specs/`), los casos dorados (`cases/`) y las herramientas de codegen. Genera los esqueletos de tokens Kotlin (`LhTokens` / `LhDarkTokens`), **sin generar ninguna implementación de UI**.
- **Responsabilidades de este repositorio**: escribir a mano la UI en Compose, la integración de los temas, las enums de props y el enlace de los valores predeterminados, y traducir los casos dorados a pruebas locales.
- **Independiente del linaje web**: ninguna dependencia de `@lh-ui/share`; las releases no se condicionan mutuamente. La coherencia visual se mantiene alineándose manualmente con la *Línea base del lenguaje de diseño*.

## Lenguaje de diseño

Temática estilo chino: color principal azul 青花瓷 (porcelana azul y blanco) (`#2B4C7C`), fondo 宣纸白 (blanco papel de arroz) (`#FAF6F0`), éxito en verde 松花绿 (verde pino), advertencia 藤黄 (amarillo herbáceo), peligro 朱砂 (cinabre), pila de fuentes con serifa (Noto Serif SC → STSong → SimSun). Todos los valores visuales son tokens: no aparece ningún literal en los componentes.

## Por qué Compose

- UI declarativa, ciudadana de primera clase de la plataforma, mentalmente isomorfa a SwiftUI / ArkUI: los contratos multiplataforma son fáciles de alinear
- Sin runtime JS y sin capa de puenteo HTML/CSS: cumple las restricciones de rendimiento nativo y de entorno integrado
- `@Preview` sirve de documentación, `@Composable` es el componente: simétrico con la experiencia de desarrollo SFC del linaje web

## Estado actual

- **Los 14 componentes P0 ya están entregados** (button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar). Cada componente sigue el mismo paradigma de cadena completa: consumo de tokens + resolución del aspecto + UI en Compose + pruebas unitarias JVM + Compose UI Test + `@Preview` + una página de documentación.
- **El módulo de documentación nativa `:docsui` ya está entregado**: listo para integrar (`LhDocs.launch(context)`) + un empaquetado independiente (`:docsapp`). Las vistas previas son Compose real, interactivo y renderizado en vivo: véase [Inicio rápido](./quickstart).
