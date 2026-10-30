# Temas y modo oscuro

## Modelo de tokens

La fuente única de tokens del linaje nativo está en `lh-ui-core/tokens/*.json`; el codegen genera los artefactos Android:

| Artefacto | Contenido | Forma oscura |
| --- | --- | --- |
| `LhTokens` | Paleta clara completa: dimensiones de Button, paleta Color, Motion, Radius, Shadow, Spacing, Typography, ZIndex | — |
| `LhDarkTokens` | Únicamente el subobjeto `Color` (modelo de **objetos paralelos**) | El oscuro solo cubre los colores; las dimensiones, animaciones y radios son iguales en modo oscuro |

> Esto es coherente con ios / harmony / windows (objetos paralelos) y difiere del lado de la documentación (fusión JSON superficial).
> La capa de temas está diseñada en consecuencia: `LhColors` solo transporta los colores; las dimensiones se toman directamente de `LhTokens` en los componentes.

## LhColors

`LightLhColors` / `DarkLhColors` aplanan los dos object generados en una paleta inmutable:

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
  // LocalLhColors inyecta DarkLhColors; los componentes del interior lo consumen automáticamente
}
```

Sin envoltura de `LhTheme` se recurre a `LightLhColors` (claro por defecto).

## Precálculo del oscuro (importante)

El modo oscuro del linaje web se evalúa dinámicamente con la función CSS `color-mix()`; al no existir una cascada CSS en nativo, core ya ha **precalculado las expresiones oscuras como literales** (p. ej., `rgba(61,100,158,0.25)`, `#B1C1D8`). Consecuencia (plan maestro R-n6):

> **Al cambiar el color principal del preset, los valores oscuros precalculados no lo siguen automáticamente**: hay que recalcularlos a mano y actualizar
> `lh-ui-core/tokens/color-dark.json`, y luego refrescar la *Línea base del lenguaje de diseño*.

## Consulta rápida de tokens

La tabla completa de tokens (claro/oscuro/descripción) está en la [consulta rápida de tokens de lh-ui-core](https://www.lhgb.top/lhui/core/tokens/), o en `dist/docs/tokens.md` tras ejecutar `pnpm codegen` en el repositorio core. El mapeo de tokens a nivel de componente está en [Referencia de tokens de Button](/es/components/button#referencia-de-tokens).

## Presets personalizados (reservado)

`LhTheme(colors = ...)` admite una paleta personalizada; la capacidad de presets temáticos se abrirá en próximas versiones, junto con los tokens de preset temático de core.
