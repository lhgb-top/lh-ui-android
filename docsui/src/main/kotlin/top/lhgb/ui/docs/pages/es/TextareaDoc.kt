package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Área de texto",
  lede = "Área de texto controlada de varias líneas: admite número de filas, contador de caracteres y longitud máxima.",
  group = DocGroup.FORM,
  contract = "Fuente del contrato: `lh-ui-core/specs/textarea.json` · Casos dorados: `cases/textarea.json` (6 casos, traducidos a pruebas unitarias JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "Uso básico",
      prose = "La altura mínima = número de filas × interlineado × tamaño de fuente; al superarla, aparece desplazamiento vertical.",
      preview = { DocTextareaBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhTextarea(",
        "  value = value,",
        "  rows = 3,",
        "  showCount = true,",
        "  maxlength = 200,",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "Contador de caracteres",
      prose = "Con `showCount = true` se renderiza un contador en la esquina inferior derecha (`textarea.countFontSize`, color `text.secondary`); `maxlength` trunca la entrada.",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "Valor controlado (obligatorio)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "Devolución de llamada de cambio de valor (obligatorio)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "Texto del marcador de posición", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "Número de filas (determina la altura mínima)", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "Si está deshabilitada", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "Si es de solo lectura", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "Si se muestra el contador de caracteres", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "Longitud máxima (trunca al superarla)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "Devoluciones de llamada de focus / pérdida de focus", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "Modifier", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "Relleno (`spacing.md`)")),
    DocRow(cells = listOf("`textarea.fontSize`", "Tamaño de fuente (`typography.fontSize.base`)")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "Radio de esquina / ancho del borde / ancho del anillo de focus")),
    DocRow(cells = listOf("`textarea.countFontSize`", "Tamaño de fuente del contador (`typography.fontSize.extraSmall`)")),
    DocRow(cells = listOf("`textarea.minHeight`", "Base de la altura mínima")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "Interlineado (cálculo del número de filas)")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "Colores del texto / el contador / el marcador de posición")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "Colores del borde en focus / predeterminado")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "Tres colores del estado deshabilitado")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "Al escribir se dispara onValueChange", "`LhTextareaTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-input`", "El estado deshabilitado no admite entrada", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "El contador renderiza «n / max»", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "Sin maxlength, solo se renderiza la longitud", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "El focus consume el token focus", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "Al superar la longitud máxima se trunca", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # parte JVM",
    "./gradlew :components:textarea:connectedAndroidTest           # parte de interacción (requiere dispositivo)",
  ),
)
