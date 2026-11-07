package top.lhgb.ui.docs.pages.es

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast (notificación breve)",
  lede = "Superposición en píldora color tinta, con visibilidad y temporizador autogestionados, para retroalimentación ligera de operaciones. No bloquea la interacción del usuario.",
  group = DocGroup.OVERLAY,
  contract = "Fuente del contrato: `lh-ui-core/specs/toast.json` · Casos dorados: `cases/toast.json` (6 casos: 4 de máquina de estados + 2 de renderizado, todos traducidos a pruebas locales)",
  sections = listOf(
    DocSection(
      title = "Uso",
      prose = "Toast es una máquina de estados autogestionada: el estado lo gestiona `LhToastState`; el host llama a `show(text)` para mostrarlo y a `hide()` para ocultarlo; al vencer el temporizador se oculta automáticamente y se dispara `onClose`. Cuando es visible, el componente se autogestiona con `tick` fotograma a fotograma; el host también puede tomar el control del temporizador llamándolo manualmente.",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* opcional: registrar el cierre */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"Guardado\") }) { Text(\"Lanzar\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "Tipos semánticos",
      prose = "Con un tipo distinto de `default` se dibuja antes del texto un punto semántico de diámetro igual al tamaño de fuente.",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "Posición",
      prose = "`lhPosition` toma `top` / `center` / `bottom`, con un margen de `spacing.xl` en cada extremo; el ancho se obtiene de la medición del texto y se limita al 80% del ancho disponible.",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "Máquina de estados del toast (gestiona la visibilidad, el texto y el temporizador)", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "Tipo semántico; cuando es distinto de default, se dibuja un punto semántico", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "Posición dentro del área disponible", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "Modifier (aplicado a la píldora)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("Miembro", "Descripción"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "Muestra y reinicia el temporizador")),
        DocRow(cells = listOf("`hide()`", "Oculta inmediatamente, sin disparar onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "Avanza el temporizador; al vencer, oculta, dispara onClose y devuelve true")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "Estado actual (observable)")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("Valor", "Color del punto"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "Sin punto")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "Color semántico correspondiente")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "Ancho máximo de la píldora (240dp; en la práctica, el menor entre el 80% del ancho disponible y este valor)")),
    DocRow(cells = listOf("`toast.borderRadius`", "Radio de esquina de la píldora (sigue `radius.base`)")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "Relleno de la píldora")),
    DocRow(cells = listOf("`toast.fontSize`", "Tamaño de fuente del texto (el diámetro del punto toma este mismo valor)")),
    DocRow(cells = listOf("`toast.gap`", "Espacio entre el punto y el texto")),
    DocRow(cells = listOf("`zIndex.toast`", "Alineación semántica de la jerarquía (el `Popup` de Compose aporta el nivel de jerarquía de ventana)")),
    DocRow(cells = listOf("`color.background.overlay` (mapeo ink de la capa de temas)", "Color de fondo de la píldora")),
    DocRow(cells = listOf("`color.onPrimary`", "Color del texto")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "Colores semánticos del punto")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "Tras show, es visible y renderiza texto", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "Al vencer el temporizador se oculta y dispara onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "Mientras no vence el temporizador, se mantiene visible sin disparar onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide no dispara onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "El tipo success consume el color de éxito", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "Cuando no es visible, no se renderiza", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # parte JVM",
    "./gradlew :components:toast:connectedAndroidTest                 # parte de interacción (requiere dispositivo)",
  ),
)
