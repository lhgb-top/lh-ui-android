# Liaison locale

## Convention de disposition de la famille

```
lh-ui/                        # répertoire parent de la famille (pas un dépôt)
├── lh-ui-core/               # source de vérité des jetons + codegen
├── lh-ui-android/            # ce dépôt
├── lh-ui-ios/                # dépôt frère
├── lh-ui-harmony/
└── lh-ui-windows/
```

Lorsque les dépôts partagent le même répertoire parent, les jetons sont **reliés localement** ; si les dépôts frères sont absents (clone CI d'un seul dépôt), le **repli automatique vers le paquet npm** s'applique.
C'est isomorphe au mécanisme de garde par alias vite de la lignée web (plan directeur D18).

## Garde à trois niveaux

`scripts/link-tokens.mjs` recherche les artefacts de jetons par ordre de priorité :

| Priorité | Source | Description |
| --- | --- | --- |
| 1 | `../lh-ui-core/dist/android` | liaison locale : modifier un jeton prend effet immédiatement |
| 2 | `node_modules/@lh-ui/core/dist/android` | repli npm : le `files:["dist"]` de `@lh-ui/core` inclut les artefacts des quatre plateformes |
| 3 | — | erreur et sortie : sans jetons, la construction est impossible |

## Flux quotidien

```bash
# 1. modifier les jetons (dans le dépôt core)
cd ../lh-ui-core
vim tokens/color.json           # ou spacing / typography / ...

# 2. régénérer + vérifier
pnpm codegen                    # génère les artefacts dist/ des quatre plateformes
pnpm verify                     # schéma + références + contraste WCAG + cohérence des contrats

# 3. revenir dans ce dépôt pour synchroniser
cd ../lh-ui-android
pnpm link:tokens                # écrase core/src/main/kotlin/top/lhgb/ui/tokens/

# 4. régression
./gradlew :components:button:test
```

## Gate de CI

```bash
pnpm link:tokens:check          # vérifie que les jetons sont synchronisés (comparaison du nombre de fichiers, code de sortie 1 sinon)
```

## Interdictions

- ❌ Modifier à la main `core/src/main/kotlin/top/lhgb/ui/tokens/` (artefact généré, gitignore)
- ❌ Committer les fichiers de jetons générés
- ❌ Écrire des littéraux de couleur/espacement dans les composants (par ex. `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)
- ❌ Committer une dépendance `file:` / `link:` dans `build.gradle.kts` (la liaison locale reste locale)

## Dépannage

| Symptôme | Cause | Solution |
| --- | --- | --- |
| Erreur de compilation `unresolved reference: LhTokens` | jetons non synchronisés | `pnpm link:tokens` |
| Valeur de jeton inattendue | le dépôt core n'a pas été régénéré | `pnpm codegen` dans le dépôt core, puis relier à nouveau |
| `link:tokens` signale « artefact de jetons introuvable » | dépôt frère absent et paquet npm non installé | vérifier la disposition de la famille ou `pnpm i -D @lh-ui/core` |
| Couleur sombre incorrecte | couleur principale claire modifiée sans mise à jour du précalcul sombre | mettre à jour `color-dark.json` et relancer (R-n6) |
