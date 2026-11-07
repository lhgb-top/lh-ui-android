package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideIntroductionDoc = DocEntry(
  id = "guide-introduction",
  title = "Introduction",
  lede = "lh-ui-android est l'implémentation Android de la lignée native de la famille lh-ui : une bibliothèque de composants UI basée sur Kotlin + Jetpack Compose.",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Place dans la famille",
      prose = "• Centre de compilation : `lh-ui-core` est la source de vérité des jetons de la lignée native (`tokens/*.json`), les contrats des composants (`specs/`), les cas dorés (`cases/`) et les outils de codegen. Il génère les squelettes de jetons Kotlin (`LhTokens` / `LhDarkTokens`) et ne génère aucune implémentation UI.\n• Rôle de ce dépôt : écrire l'UI Compose à la main, l'intégration des thèmes, les énumérations de props et les valeurs par défaut, et traduire les cas dorés en tests locaux.\n• Indépendance vis-à-vis de la lignée web : aucune dépendance à `@lh-ui/share`, aucune contrainte de release entre les deux. La cohérence visuelle est assurée manuellement via la *Base du langage de design*.",
      code = listOf(
        "                        ┌── lignée web : share (core TS + SCSS) → plus / vue2 / react / uniapp",
        "famille lh-ui (même marque) ─────┤",
        "                        └── lignée native : core (jetons JSON + contrats + codegen) → android / ios / harmony / windows",
        "                                                                    ↑ ce dépôt",
      ),
    ),
    DocSection(
      title = "Langage de design",
      prose = "Thème de style chinois : couleur principale bleu 青花瓷 (porcelaine) (`#2B4C7C`), fond blanc 宣纸 (papier xuan) (`#FAF6F0`), succès en 松花绿 (vert pin), avertissement en 藤黄 (gomme-gutte), danger en 朱砂 (cinabre), pile de polices serif (Noto Serif SC → STSong → SimSun). Toutes les valeurs visuelles sont des jetons : aucun littéral n'apparaît dans les composants.",
    ),
    DocSection(
      title = "Pourquoi Compose",
      prose = "• UI déclarative citoyenne de première classe de la plateforme, isomorphe mentalement à SwiftUI / ArkUI, ce qui facilite l'alignement des contrats entre plateformes\n• Aucun runtime JS, aucun pont HTML/CSS : répond aux exigences de performance native et de convivialité embarquée\n• `@Preview` sert de documentation, `@Composable` sert de composant : une expérience de développement symétrique à celle des SFC de la lignée web",
    ),
    DocSection(
      title = "État actuel",
      prose = "• Les 14 composants P0 sont tous livrés (button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar), chacun sur la même chaîne complète : consommation des jetons + résolution de l'apparence + UI Compose + tests unitaires JVM + Compose UI Test + `@Preview` + page de documentation.\n• Le module de documentation natif `:docsui` est livré : prêt à l'emploi (`LhDocs.launch(context)`) et empaquetable seul (`:docsapp`), avec des aperçus en vrai rendu Compose temps réel et interactifs ; voir Démarrage rapide.",
    ),
  ),
)
