# Área de texto

Área de texto controlada de varias líneas: admite número de filas, contador de caracteres y longitud máxima.

> Fuente del contrato: [`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> Casos dorados: [`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea) (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)

## Uso básico

La altura mínima = número de filas × interlineado × tamaño de fuente; al superarla, aparece desplazamiento vertical.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="Introduce el contenido multilínea" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">Texto de tres líneas
Segunda línea
Tercera línea</textarea>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## Contador de caracteres

Con `showCount = true` se renderiza un contador en la esquina inferior derecha (`textarea.countFontSize`, color `text.secondary`); `maxlength` trunca la entrada.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Contador de Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">青花瓷</textarea>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo; en el dispositivo, la esquina inferior derecha muestra el contador «3 / 20»</p>

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `value` | Valor controlado (obligatorio) | `String` | — |
| `onValueChange` | Devolución de llamada de cambio de valor (obligatorio) | `(String) -> Unit` | — |
| `placeholder` | Texto del marcador de posición | `String?` | `null` |
| `rows` | Número de filas (determina la altura mínima) | `Int` | `3` |
| `disabled` | Si está deshabilitada | `Boolean` | `false` |
| `readonly` | Si es de solo lectura | `Boolean` | `false` |
| `showCount` | Si se muestra el contador de caracteres | `Boolean` | `false` |
| `maxlength` | Longitud máxima (trunca al superarla) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | Devoluciones de llamada de focus / pérdida de focus | `(() -> Unit)?` | `null` |
| `modifier` | Modifier | `Modifier` | `Modifier` |

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | Relleno (`spacing.md`) |
| `textarea.fontSize` | Tamaño de fuente (`typography.fontSize.base`) |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | Radio de esquina / ancho del borde / ancho del anillo de focus |
| `textarea.countFontSize` | Tamaño de fuente del contador (`typography.fontSize.extraSmall`) |
| `textarea.minHeight` | Base de la altura mínima |
| `typography.lineHeight.primary` | Interlineado (cálculo del número de filas) |
| `color.text.{primary,secondary,placeholder}` | Colores del texto / el contador / el marcador de posición |
| `color.focus` / `color.border.base` | Colores del borde en focus / predeterminado |
| `color.disabled.{bg,text,border}` | Tres colores del estado deshabilitado |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `input-emits-onvaluechange` | Al escribir se dispara onValueChange | `LhTextareaTest.input_emits_onValueChange` (Compose UI Test) |
| `disabled-blocks-input` | El estado deshabilitado no admite entrada | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | El contador renderiza «n / max» | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | Sin maxlength, solo se renderiza la longitud | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | El focus consume el token focus | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | Al superar la longitud máxima se trunca | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # parte JVM
./gradlew :components:textarea:connectedAndroidTest           # parte de interacción (requiere dispositivo)
```
