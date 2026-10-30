# Inicio rápido

## Ejemplo mínimo

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* manejar el clic */ },
    ) {
      Text("Botón principal")
    }
  }
}
```

`LhTheme` es el contenedor de temas: todos los componentes Lh de su interior consumen los tokens automáticamente; sin envoltorio, se recurre a la paleta clara.

## Tipos y tamaños

```kotlin
// Seis tipos
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("Predeterminado") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("Principal") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("Éxito") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("Advertencia") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("Peligro") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("Información") }

// Tres tamaños
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("Pequeño") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("Mediano") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("Grande") }
```

## Estados

```kotlin
// Deshabilitado: no responde al clic, muestra el aspecto deshabilitado
LhButton(disabled = true, lhOnClick = {}) { Text("Deshabilitado") }

// Cargando: muestra un indicador, no dispara lhOnClick
LhButton(loading = true, lhOnClick = {}) { Text("Cargando") }

// Ambos presentes: la deshabilitación tiene prioridad (caso dorado disabled-precedes-loading)
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("Deshabilitado prioritario") }
```

## Icono y fondo de patrón

```kotlin
// El nombre del icono se resuelve contra el sistema de iconos local (véase LhButtonIcons; los nombres no registrados no se dibujan)
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("Buscar") }

// Fondo de patrón chino (consume el token color.decoration)
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("Fondo de patrón") }
```

## Temas claro y oscuro

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // Todos los componentes del interior cambian automáticamente a los tokens oscuros (valores precalculados por core, sin mezcla en tiempo de ejecución)
}
```

## Módulo de documentación nativa

Además de este sitio web, el repositorio incluye un **módulo de documentación nativa Android** (`:docsui`): sus páginas se alinean 1:1 con la estructura de este sitio, pero las vistas previas son **Compose real, renderizado en vivo e interactivo** (los botones se pueden pulsar, las entradas aceptan texto, los diálogos se abren), no una simulación HTML estática.

**Listo para integrar**: cualquier proyecto Android que añada la dependencia puede abrir la documentación completa con una sola línea, `LhDocs.launch(context)` (14 páginas de componentes + 5 páginas de guía + conmutación claro/oscuro + vistas previas en vivo):

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // o la coordenada Maven top.lhgb.ui:docsui

// cualquier punto de entrada (p. ej. la devolución de llamada de un botón «Documentación de componentes»)
LhDocs.launch(context)
```

El manifest de la biblioteca fusiona automáticamente una `LhDocsActivity` con `exported = false`, sin inyectar un icono de lanzador en la aplicación host.

**Empaquetado independiente**: `:docsapp` es un módulo de aplicación mínima (una sola Activity); al empaquetarlo por separado se obtiene la APP «documentación de lh-ui»:

```bash
./gradlew :docsapp:installDebug
```

## Vista previa de los ejemplos de componentes

La UI nativa no se puede renderizar en tiempo real en el navegador; este sitio muestra el aspecto con un **simulador de marco de teléfono en HTML estático en línea**:

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">Botón predeterminado</button>
      <button class="sim-btn sim-btn--primary">Botón principal</button>
      <button class="sim-btn sim-btn--success">Éxito</button>
      <button class="sim-btn sim-btn--warning">Advertencia</button>
      <button class="sim-btn sim-btn--danger">Peligro</button>
      <button class="sim-btn sim-btn--info">Información</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">Pequeño</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">Grande</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>Deshabilitado</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>Cargando
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>Buscar</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">Fondo de patrón</button>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo; todos los valores de estilo provienen de la instantánea de tokens de core, con los mismos valores que en el dispositivo real. El aspecto en dispositivo real se rige por <code>@Preview</code> y las capturas de pantalla del dispositivo.</p>

> El modo oscuro del simulador está vinculado con el sitio: cambia el tema en la esquina superior derecha y los colores del marco del teléfono cambian (los tokens oscuros están precalculados por core).
