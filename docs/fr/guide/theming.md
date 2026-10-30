# Thèmes et mode sombre

## Modèle de jetons

La source de vérité des jetons de la lignée native se trouve dans `lh-ui-core/tokens/*.json` ; le codegen génère les artefacts Android :

| Artefact | Contenu | Forme sombre |
| --- | --- | --- |
| `LhTokens` | Palette claire complète : dimensions Button, palette Color, Motion, Radius, Shadow, Spacing, Typography, ZIndex | — |
| `LhDarkTokens` | Uniquement le sous-objet `Color` (modèle d'**objets côte à côte**) | Le sombre ne couvre que les couleurs ; dimensions, animations et arrondis sont inchangés en sombre |

> Identique à ios / harmony / windows (objets côte à côte), différent du côté documentation (fusion JSON superficielle).
> La couche de thème est conçue en conséquence : `LhColors` ne porte que les couleurs, les dimensions sont prises directement par les composants depuis `LhTokens`.

## LhColors

`LightLhColors` / `DarkLhColors` aplatissent les deux object générés en une palette immuable :

```kotlin
@Immutable
data class LhColors(
  val primary: Color, val onPrimary: Color,
  val success: Color, val warning: Color, val danger: Color, val info: Color,
  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */
)

val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)
val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)
```

## LhTheme

```kotlin
LhTheme(isDark = true) {
  // LocalLhColors injecte DarkLhColors, les composants internes le consomment automatiquement
}
```

Sans encapsulation par `LhTheme`, le repli est `LightLhColors` (clair par défaut).

## Précalcul du sombre (important)

Le sombre de la lignée web est évalué dynamiquement en CSS via `color-mix()` ; le natif n'ayant pas de cascade CSS, core a donc **précalculé les expressions sombres en littéraux** (par ex. `rgba(61,100,158,0.25)`, `#B1C1D8`). Conséquence (plan directeur R-n6) :

> **Lorsque vous modifiez la couleur principale du préréglage, les valeurs sombres précalculées ne suivent pas automatiquement** : il faut les recalculer manuellement et mettre à jour
> `lh-ui-core/tokens/color-dark.json`, puis rafraîchir la *Base du langage de design*.

## Aperçu des jetons

Le tableau complet des jetons (clair/sombre/description) se trouve dans l'[aperçu des jetons de lh-ui-core](https://www.lhgb.top/lhui/core/tokens/),
ou dans `dist/docs/tokens.md` après avoir exécuté `pnpm codegen` dans le dépôt core. Le mappage des jetons au niveau des composants se trouve dans
[la référence des jetons de Button](/fr/components/button#reference-des-jetons).

## Préréglages personnalisés (réservé)

`LhTheme(colors = ...)` accepte une palette personnalisée ; la capacité de préréglages de thème sera ouverte dans les prochaines versions, avec les jetons de préréglage de thème de core.
