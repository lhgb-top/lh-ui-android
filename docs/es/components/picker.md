# Selector

Selector de rueda inferior de una sola columna: al detenerse el desplazamiento, el elemento seleccionado es el más cercano al indicador central.

> Fuente del contrato: [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> Casos dorados: [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker) (8 casos: el clamp y el mapeo de tokens traducidos a pruebas unitarias JVM; la interacción de desplazamiento y de botones traducida a Compose UI Test)

## Uso

Aparece desde la parte inferior: la capa de cobertura arriba y el panel abajo (esquinas redondeadas en la parte superior). La barra de herramientas tiene tres secciones: «Cancelar / título / Confirmar».

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">Cancelar</button>
        <div class="sim-picker__toolbar-title">Seleccionar</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">Confirmar</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">Opción uno</div>
        <div class="sim-picker__item sim-picker__item--selected">Opción dos</div>
        <div class="sim-picker__item">Opción tres</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">Simulación HTML (apariencia de Android / Compose), no es un renderizado en dispositivo</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("Opción uno", "Opción dos", "Opción tres")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "Seleccionar",
)
```

## Mecanismo de selección

La rueda es una lista desplazable; la altura de cada elemento es `picker.itemHeight`; el indicador central son dos líneas horizontales separadas por una distancia igual a itemHeight.
Al detenerse el desplazamiento, el índice seleccionado se obtiene redondeando la distancia recorrida; cuando cambia, se dispara `onValueChange`.
Si `value` está fuera de rango, `LhPickerDefaults.clamp` lo limita (clamp) al rango de candidatos.

## API

### Props

| Prop | Descripción | Tipo | Predeterminado |
| --- | --- | --- | --- |
| `visible` | Si se muestra; cuando es false, no se renderiza nada | `Boolean` | — |
| `options` | Array de textos de los candidatos (una columna) | `List<String>` | — |
| `onValueChange` | Se dispara cuando la rueda se detiene y el elemento seleccionado cambia, con el nuevo índice | `(Int) -> Unit` | — |
| `onConfirm` | Se dispara al pulsar el botón de confirmación, con el índice seleccionado actual | `(Int) -> Unit` | — |
| `onCancel` | Se dispara al pulsar el botón de cancelación | `() -> Unit` | — |
| `onClose` | Se dispara tras confirmar/cancelar; el host, en consecuencia, pone visible a false | `() -> Unit` | — |
| `value` | Índice del elemento seleccionado (base 0); fuera de rango se limita con clamp | `Int` | `0` |
| `title` | Título central de la barra de herramientas | `String?` | `null` |
| `confirmText` | Texto del botón de confirmación | `String` | `"确认"` |
| `cancelText` | Texto del botón de cancelación | `String` | `"取消"` |
| `content` | Zona de contenido de la rueda (prevalece sobre el renderizado predeterminado de candidatos, slot default del spec) | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | Barra de herramientas (prevalece sobre la confirmación/título/cancelación predeterminados, slot toolbar del spec) | `@Composable (() -> Unit)?` | `null` |
| `listState` | Estado de la lista de la rueda (por defecto lo proporciona `rememberLhPickerListState(value, options)`; en pruebas se puede inyectar desde fuera) | `LazyListState` | Véase a la izquierda |
| `modifier` | Modifier (aplicado al panel) | `Modifier` | `Modifier` |

Gancho de pruebas: `rememberLhPickerListState(value, options)` devuelve un `LazyListState` con el posicionamiento inicial según `value`;
las pruebas instrumentadas pueden usar `listState.dispatchRawDelta(...)` para conducir el desplazamiento de forma determinista.

## Referencia de tokens

| Token | Uso |
| --- | --- |
| `picker.height` | Altura del panel (260dp) |
| `picker.itemHeight` | Altura de cada elemento (40dp; también es la separación del indicador central) |
| `picker.toolbarHeight` | Altura de la barra de herramientas (44dp) |
| `picker.itemFontSize` | Tamaño de fuente de los candidatos |
| `picker.borderRadius` | Radio de esquina superior del panel |
| `picker.paddingHorizontal` | Relleno horizontal del panel (sigue `spacing.md`) |
| `zIndex.modal` / `zIndex.modalBackdrop` | Alineación semántica de la jerarquía (el `Popup` de Compose aporta el nivel de jerarquía de ventana) |
| `color.background.overlay` | Color de la capa de cobertura |
| `color.background.default` | Color de fondo del panel |
| `color.text.primary` / `color.text.regular` | Colores del título / los candidatos |
| `color.border.lighter` | Color de las líneas del indicador central |
| `color.primary.default` | Color del botón de confirmación |

## Referencia de casos dorados

| case id | Aserción | Test local |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | Confirmar envía el índice actual + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | Tras confirmar se dispara onClose | Ídem |
| `cancel-emits-oncancel` | Cancelar dispara onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | El desplazamiento cambia la selección y dispara onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | Un value negativo se limita a 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | Un value fuera de rango se limita al último elemento | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | El título renderiza texto | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | Cuando no es visible, no se renderiza | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # parte JVM
./gradlew :components:picker:connectedAndroidTest               # parte de interacción (requiere dispositivo)
```
