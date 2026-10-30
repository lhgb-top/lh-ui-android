# Barra de navegación

Barra de título superior de la página, con disposición de tres secciones (izquierda / centro / derecha), se usa a menudo para la navegación hacia atrás y la presentación del título.

> Fuente del contrato: [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> Casos dorados: [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar) (6 casos: el mapeo de tokens traducido a pruebas unitarias JVM; las pulsaciones izquierda y derecha traducidas a Compose UI Test)

## Uso

Las tres secciones son de igual anchura (cada una con `weight(1f)`); el título se centra horizontalmente en el ancho disponible; de forma predeterminada se dibuja la línea separadora inferior.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>Atrás</div>
      <div class="sim-navbar__title">Título de la página</div>
      <div class="sim-navbar__side sim-navbar__side--right">Más</div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
LhNavbar(
  title = "Título de la página",
  leftIcon = "arrow-left",
  leftText = "Atrás",
  rightText = "Más",
  onLeftClick = { /* hacia atrás */ },
  onRightClick = { /* más */ },
)
```

## Contenido de los lados

El texto y el icono pueden coexistir (el icono a la izquierda y el texto a la derecha, separados por `spacing.xs`); **cuando uno de los lados no tiene ningún contenido, no responde a la pulsación**
(caso dorado `no-left-content-no-onleftclick`: ese nodo ni siquiera existe semánticamente como clicable).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Variantes de Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">Solo icono</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">Texto</div>
      <div class="sim-navbar__title">Sin separadora</div>
      <div class="sim-navbar__side sim-navbar__side--right">Editar</div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `title` | Título central | `String?` | `null` |
| `onLeftClick` | Se dispara al pulsar la zona izquierda | `() -> Unit` | `{}` |
| `onRightClick` | Se dispara al pulsar la zona derecha | `() -> Unit` | `{}` |
| `leftText` | Texto del botón izquierdo; puede coexistir con leftIcon | `String?` | `null` |
| `leftIcon` | Nombre del icono izquierdo (sistema de iconos local, p. ej. `"arrow-left"`) | `String?` | `null` |
| `rightText` | Texto del botón derecho; puede coexistir con rightIcon | `String?` | `null` |
| `rightIcon` | Nombre del icono derecho | `String?` | `null` |
| `bordered` | Si se dibuja la línea separadora inferior | `Boolean` | `true` |
| `titleContent` | Contenido del título (prevalece sobre title, slot default del spec) | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | Contenido del lado izquierdo (prevalece sobre leftText/leftIcon, slot left del spec) | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | Contenido del lado derecho (prevalece sobre rightText/rightIcon, slot right del spec) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

Cuando `*Content` / `*Text` / `*Icon` de un lado están todos vacíos, ese lado no es clicable ni dibuja ningún contenido.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `navbar.height` | Altura de la barra de navegación (44dp) |
| `navbar.paddingHorizontal` | Relleno horizontal de los lados (sigue `spacing.md`) |
| `navbar.titleFontSize` | Tamaño de fuente del título y de los textos de los lados |
| `navbar.iconSize` | Tamaño de los iconos de los lados (sigue `icon.size.medium`) |
| `navbar.borderWidth` | Ancho de la línea separadora inferior |
| `zIndex.sticky` | Alineación semántica de la jerarquía fija en la parte superior |
| `color.background.default` | Color de fondo |
| `color.text.primary` / `color.text.regular` | Colores del título / los textos de los lados |
| `color.border.lighter` | Color de la línea separadora inferior |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | La pulsación izquierda dispara onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test) |
| `rightclick-emits-onrightclick` | La pulsación derecha dispara onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | Sin contenido izquierdo, no se dispara | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | El título renderiza texto | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | La línea separadora consume el token del borde | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | La altura consume el token navbar | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # parte JVM
./gradlew :components:navbar:connectedAndroidTest                # parte de interacción (requiere dispositivo)
```
