---
layout: home

hero:
  image:
    src: /logo-banner.png
    alt: lh-ui 品牌横幅
  name: lh-ui-android
  text: Lignée native · Android
  tagline: Bibliothèque de composants en Kotlin + Jetpack Compose, pilotée par les jetons, alignée sur les contrats, gardée par les cas dorés
  actions:
    - theme: brand
      text: Démarrage rapide
      link: /fr/guide/quickstart
    - theme: alt
      text: Vue d'ensemble des composants
      link: /fr/components/button

features:
  - title: Piloté par les jetons
    details: "Aucune couleur ni espacement codés en dur dans les composants : ils consomment tous les LhTokens / LhDarkTokens générés par le codegen de core ; modifier un jeton se répercute immédiatement sur toutes les plateformes."
  - title: Contrats cohérents
    details: Le nommage des props est aligné mot pour mot avec lh-ui-core/specs, et les comportements sont partagés avec iOS / HarmonyOS / Windows et la lignée web.
  - title: Garde des cas dorés
    details: Les assertions de comportement de cases/button.json sont traduites en tests JVM JUnit et Compose UI Test ; le CI exécute le même lot d'assertions.
  - title: Thèmes clair et sombre
    details: "Bascule en une ligne avec LhTheme ; les jetons sombres sont précalculés par core : aucun mélange de couleur à l'exécution, aucun scintillement."
  - title: Langage de design de style chinois
    details: Couleur principale bleu 青花瓷 (porcelaine), fond blanc 宣纸 (papier xuan), touches de 藤黄 (gomme-gutte) et 朱砂 (cinabre), pile de polices serif ; mêmes sources et mêmes valeurs que la lignée web.
  - title: Compose natif
    details: "UI écrite à la main en pur Kotlin + Compose : aucun runtime JS, aucun pont HTML/CSS ; une UI déclarative citoyenne de première classe."
---
