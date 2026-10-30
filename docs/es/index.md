---
layout: home

hero:
  name: lh-ui-android
  text: Linaje nativo · Android
  tagline: Biblioteca de componentes en Kotlin + Jetpack Compose, impulsada por tokens, alineada con los contratos, resguardada por casos dorados
  actions:
    - theme: brand
      text: Inicio rápido
      link: /es/guide/quickstart
    - theme: alt
      text: Vista general de componentes
      link: /es/components/button

features:
  - title: Impulsada por tokens
    details: "Ningún valor de color o espaciado codificado de forma fija en los componentes: todos consumen los LhTokens / LhDarkTokens generados por el codegen de core. Al cambiar un token, el efecto se aplica al instante en toda la plataforma."
  - title: Contratos coherentes
    details: El nombrado de las props está alineado palabra por palabra con lh-ui-core/specs, compartiendo comportamiento con iOS / HarmonyOS / Windows y el linaje web.
  - title: Resguardo de casos dorados
    details: Las aserciones de comportamiento de cases/button.json se traducen a JVM JUnit y Compose UI Test; la CI ejecuta el mismo conjunto de aserciones.
  - title: Temas claro y oscuro
    details: "LhTheme se alterna en una línea; los tokens oscuros están precalculados por core: sin mezcla de colores en tiempo de ejecución, sin parpadeo."
  - title: Lenguaje de diseño estilo chino
    details: Color principal azul 青花瓷 (porcelana azul y blanco), fondo 宣纸白 (blanco papel de arroz), toques 藤黄 (amarillo herbáceo) y 朱砂 (cinabre), pila de fuentes con serifa, mismos valores que el linaje web.
  - title: Compose nativo
    details: UI escrita a mano en Kotlin + Compose puro, sin runtime JS, sin capa de puenteo HTML/CSS, UI declarativa de primera clase.
---
