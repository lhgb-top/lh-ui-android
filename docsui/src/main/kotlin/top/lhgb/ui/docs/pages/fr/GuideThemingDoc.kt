package top.lhgb.ui.docs.pages.fr

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "Thèmes et mode sombre",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Modèle de jetons",
      prose = "La source de vérité des jetons de la lignée native se trouve dans `lh-ui-core/tokens/*.json` ; le codegen génère les artefacts Android :\nIdentique à ios / harmony / windows (objets côte à côte), différent du côté documentation (fusion JSON superficielle).\nLa couche de thème est conçue en conséquence : `LhColors` ne porte que les couleurs, les dimensions sont prises directement par les composants depuis `LhTokens`.",
    ),
    DocSection(
      title = "LhColors",
      prose = "`LightLhColors` / `DarkLhColors` aplatissent les deux object générés en une palette immuable :",
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
      prose = "Sans encapsulation par `LhTheme`, le repli est `LightLhColors` (clair par défaut).",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // LocalLhColors injecte DarkLhColors, les composants internes le consomment automatiquement",
        "}",
      ),
    ),
    DocSection(
      title = "Précalcul du sombre (important)",
      prose = "Le sombre de la lignée web est évalué dynamiquement en CSS via `color-mix()` ; le natif n'ayant pas de cascade CSS, core a donc précalculé les expressions sombres en littéraux (par ex. `rgba(61,100,158,0.25)`, `#B1C1D8`). Conséquence (plan directeur R-n6) :\nLorsque vous modifiez la couleur principale du préréglage, les valeurs sombres précalculées ne suivent pas automatiquement : il faut les recalculer manuellement et mettre à jour\n`lh-ui-core/tokens/color-dark.json`, puis rafraîchir la *Base du langage de design*.",
    ),
    DocSection(
      title = "Aperçu des jetons",
      prose = "Le tableau complet des jetons (clair/sombre/description) se trouve dans l'aperçu des jetons de lh-ui-core,\nou dans `dist/docs/tokens.md` après avoir exécuté `pnpm codegen` dans le dépôt core. Le mappage des jetons au niveau des composants se trouve dans\nla référence des jetons de Button.",
    ),
    DocSection(
      title = "Préréglages personnalisés (réservé)",
      prose = "`LhTheme(colors = ...)` accepte une palette personnalisée ; la capacité de préréglages de thème sera ouverte dans les prochaines versions, avec les jetons de préréglage de thème de core.",
    ),
  ),
)
