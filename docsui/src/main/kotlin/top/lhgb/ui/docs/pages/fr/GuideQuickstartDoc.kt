package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "Démarrage rapide",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Exemple minimal",
      prose = "`LhTheme` est le conteneur de thème : tous les composants Lh qu'il encapsule consomment les jetons automatiquement ; sans encapsulation, la palette claire est utilisée par repli.",
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
        "      lhOnClick = { /* gérer le clic */ },",
        "    ) {",
        "      Text(\"Bouton principal\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "Types et dimensions",
      prose = "",
      code = listOf(
        "// Six types",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"Par défaut\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"Principal\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"Succès\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"Avertissement\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"Danger\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"Info\") }",
        "",
        "// Trois dimensions",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"Petit\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"Moyen\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"Grand\") }",
      ),
    ),
    DocSection(
      title = "États",
      prose = "",
      code = listOf(
        "// Désactivé : ne répond pas au clic, affiche l'apparence désactivée",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"Désactivé\") }",
        "",
        "// Chargement : affiche un indicateur, ne déclenche pas lhOnClick",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"Chargement\") }",
        "",
        "// Les deux réunis : le désactivé prime (cas doré disabled-precedes-loading)",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"Désactivé prioritaire\") }",
      ),
    ),
    DocSection(
      title = "Icône et motif décoratif",
      prose = "",
      code = listOf(
        "// Le nom de l'icône est résolu par le système d'icônes local (voir LhButtonIcons ; les noms non enregistrés ne sont pas dessinés)",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"Rechercher\") }",
        "",
        "// Motif décoratif style chinois (consomme le jeton color.decoration)",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"Motif décoratif\") }",
      ),
    ),
    DocSection(
      title = "Thèmes clair et sombre",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // Tous les composants encapsulés basculent automatiquement sur les jetons sombres (valeurs précalculées par core, aucun mélange à l'exécution)",
        "}",
      ),
    ),
    DocSection(
      title = "Module de documentation natif",
      prose = "Outre cette documentation web, le dépôt contient aussi un module de documentation Android natif (`:docsui`) : ses pages de documentation reprennent la structure de ce site à l'identique, mais les aperçus sont du vrai Compose rendu en temps réel et interactif (boutons cliquables, saisie au clavier, boîtes de dialogue qui s'ouvrent), et non une simulation HTML statique.\nIntégration immédiate : après avoir ajouté la dépendance à n'importe quel projet Android, une seule ligne `LhDocs.launch(context)` ouvre la documentation complète (14 pages de composants + 5 pages de guide + bascule clair/sombre + aperçus en direct) :\nLe manifest de la bibliothèque fusionne automatiquement une `LhDocsActivity` avec `exported = false`, sans injecter d'icône de lanceur chez l'hôte.\nEmpaquetage autonome : `:docsapp` est un module application extrêmement léger (une seule Activity) ; l'empaqueter seul produit l'application « lh-ui documentation » :",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // ou la coordonnée Maven top.lhgb.ui:docsui",
        "",
        "// N'importe quel point d'entrée (par ex. le callback de clic d'un bouton « Documentation des composants »)",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "Aperçu des exemples de composants",
      prose = "L'UI native ne peut pas être rendue en temps réel dans le navigateur ; ce site présente l'apparence via un simulateur de cadre de téléphone en HTML statique intégré :\nLe mode sombre du simulateur est lié au site : basculez le thème en haut à droite et les couleurs du cadre du téléphone changent (les jetons sombres sont précalculés par core).",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
