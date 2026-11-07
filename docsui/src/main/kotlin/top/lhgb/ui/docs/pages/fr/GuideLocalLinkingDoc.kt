package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "Liaison locale",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Convention de disposition de la famille",
      prose = "Lorsque les dépôts partagent le même répertoire parent, les jetons sont reliés localement ; si les dépôts frères sont absents (clone CI d'un seul dépôt), le repli automatique vers le paquet npm s'applique.\nC'est isomorphe au mécanisme de garde par alias vite de la lignée web (plan directeur D18).",
      code = listOf(
        "lh-ui/                        # répertoire parent de la famille (pas un dépôt)",
        "├── lh-ui-core/               # source de vérité des jetons + codegen",
        "├── lh-ui-android/            # ce dépôt",
        "├── lh-ui-ios/                # dépôt frère",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "Garde à trois niveaux",
      prose = "`scripts/link-tokens.mjs` recherche les artefacts de jetons par ordre de priorité :",
    ),
    DocSection(
      title = "Flux quotidien",
      prose = "",
    ),
    DocSection(
      title = "Gate de CI",
      prose = "",
    ),
    DocSection(
      title = "Interdictions",
      prose = "• ❌ Modifier à la main `core/src/main/kotlin/top/lhgb/ui/tokens/` (artefact généré, gitignore)\n• ❌ Committer les fichiers de jetons générés\n• ❌ Écrire des littéraux de couleur/espacement dans les composants (par ex. `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)\n• ❌ Committer une dépendance `file:` / `link:` dans `build.gradle.kts` (la liaison locale reste locale)",
    ),
    DocSection(
      title = "Dépannage",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # vérifie que les jetons sont synchronisés (comparaison du nombre de fichiers, code de sortie 1 sinon)",
  ),
)
