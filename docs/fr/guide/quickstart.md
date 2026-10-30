# Démarrage rapide

## Exemple minimal

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* gérer le clic */ },
    ) {
      Text("Bouton principal")
    }
  }
}
```

`LhTheme` est le conteneur de thème : tous les composants Lh qu'il encapsule consomment les jetons automatiquement ; sans encapsulation, la palette claire est utilisée par repli.

## Types et dimensions

```kotlin
// Six types
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("Par défaut") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("Principal") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("Succès") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("Avertissement") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("Danger") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("Info") }

// Trois dimensions
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("Petit") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("Moyen") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("Grand") }
```

## États

```kotlin
// Désactivé : ne répond pas au clic, affiche l'apparence désactivée
LhButton(disabled = true, lhOnClick = {}) { Text("Désactivé") }

// Chargement : affiche un indicateur, ne déclenche pas lhOnClick
LhButton(loading = true, lhOnClick = {}) { Text("Chargement") }

// Les deux réunis : le désactivé prime (cas doré disabled-precedes-loading)
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("Désactivé prioritaire") }
```

## Icône et motif décoratif

```kotlin
// Le nom de l'icône est résolu par le système d'icônes local (voir LhButtonIcons ; les noms non enregistrés ne sont pas dessinés)
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("Rechercher") }

// Motif décoratif style chinois (consomme le jeton color.decoration)
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("Motif décoratif") }
```

## Thèmes clair et sombre

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // Tous les composants encapsulés basculent automatiquement sur les jetons sombres (valeurs précalculées par core, aucun mélange à l'exécution)
}
```

## Module de documentation natif

Outre cette documentation web, le dépôt contient aussi un **module de documentation Android natif** (`:docsui`) : ses pages de documentation reprennent la structure de ce site à l'identique, mais les aperçus sont **du vrai Compose rendu en temps réel et interactif** (boutons cliquables, saisie au clavier, boîtes de dialogue qui s'ouvrent), et non une simulation HTML statique.

**Intégration immédiate** : après avoir ajouté la dépendance à n'importe quel projet Android, une seule ligne `LhDocs.launch(context)` ouvre la documentation complète (14 pages de composants + 5 pages de guide + bascule clair/sombre + aperçus en direct) :

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // ou la coordonnée Maven top.lhgb.ui:docsui

// N'importe quel point d'entrée (par ex. le callback de clic d'un bouton « Documentation des composants »)
LhDocs.launch(context)
```

Le manifest de la bibliothèque fusionne automatiquement une `LhDocsActivity` avec `exported = false`, sans injecter d'icône de lanceur chez l'hôte.

**Empaquetage autonome** : `:docsapp` est un module application extrêmement léger (une seule Activity) ; l'empaqueter seul produit l'application « lh-ui documentation » :

```bash
./gradlew :docsapp:installDebug
```

## Aperçu des exemples de composants

L'UI native ne peut pas être rendue en temps réel dans le navigateur ; ce site présente l'apparence via un **simulateur de cadre de téléphone en HTML statique intégré** :

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">Bouton par défaut</button>
      <button class="sim-btn sim-btn--primary">Bouton principal</button>
      <button class="sim-btn sim-btn--success">Succès</button>
      <button class="sim-btn sim-btn--warning">Avertissement</button>
      <button class="sim-btn sim-btn--danger">Danger</button>
      <button class="sim-btn sim-btn--info">Info</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Petit</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">Grand</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Désactivé</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Chargement
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Rechercher</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Motif décoratif</button>
    </div>
  </div>
</div>
<p class="sim-note">Aperçu HTML simulé de l'apparence Android (Compose), pas un rendu sur appareil réel ; toutes les valeurs de style proviennent de l'instantané des jetons core, mêmes valeurs que sur appareil réel. L'apparence réelle fait référence à <code>@Preview</code> et aux captures d'écran de l'appareil.</p>

> Le mode sombre du simulateur est lié au site : basculez le thème en haut à droite et les couleurs du cadre du téléphone changent (les jetons sombres sont précalculés par core).
