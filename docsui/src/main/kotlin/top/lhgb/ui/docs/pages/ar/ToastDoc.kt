package top.lhgb.ui.docs.pages.ar

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
  title = "Toast (تنبيه)",
  lede = "طبقة عائمة على شكل قرص حبري اللون تُدير ظهورها وتوقيتها بنفسها، لاستجابة خفيفة الوزن للعمليات. ولا تحجب عمليات المستخدم.",
  group = DocGroup.OVERLAY,
  contract = "مصدر العقد: `lh-ui-core/specs/toast.json` · الحالات الذهبية: `cases/toast.json` (6 حالات، 4 حالات آلة حالة + حالتا عرض، تُرجمت جميعها إلى اختبارات محلية)",
  sections = listOf(
    DocSection(
      title = "الاستخدام",
      prose = "التنبيه (toast) هو آلة حالة ذاتية: تُحمل الحالة بواسطة `LhToastState`، يُظهر المضيف عبر `show(text)` ويُخفي عبر `hide()`، وعند انتهاء المدة يُخفى تلقائيًا ويُطلق `onClose`. وعند الظهور يقوم المكوّن بقيادة `tick` ذاتيًا بإطار، ويمكن للمضيف استدعاءه بنفسه للسيطرة على التوقيت.",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* اختياري: تسجيل الإغلاق */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"تم الحفظ\") }) { Text(\"تشغيل\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "الأنواع الدلالية",
      prose = "عند نوع غير `default` يُرسم قبل النص نقطة دائرية بلون دلالي قطرها يساوي حجم الخط.",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "التموضع",
      prose = "يأخذ `lhPosition` القيم `top` / `center` / `bottom`، مع هامش `spacing.xl` عند كل طرف؛ ويُشتق العرض من قياس النص ويُحصر عند 80% من العرض المتاح.",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "آلة حالة التنبيه (تحمل الظهور والنص والتوقيت)", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "النوع الدلالي؛ عندما يكون غير default يُرسم نقطة دلالية اللون", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "التموضع داخل المنطقة المتاحة", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل (يُطبّق على القرص)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("العضو", "الشرح"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "يُظهر ويعيد ضبط التوقيت")),
        DocRow(cells = listOf("`hide()`", "يُخفي فورًا، دون إطلاق onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "يُقدم التوقيت؛ عند الانتهاء يُخفي ويُطلق onClose ويرجع true")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "الحالة الحالية (قابلة للملاحظة)")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("القيمة", "لون النقطة"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "بلا نقطة")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "اللون الدلالي المقابل")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "الحد الأقصى لعرض القرص (240dp؛ فعليًا يُؤخذ الأصغر من 80% من العرض المتاح ومنه)")),
    DocRow(cells = listOf("`toast.borderRadius`", "نصف قطر زاوية القرص (يتبع `radius.base`)")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "حشوة القرص")),
    DocRow(cells = listOf("`toast.fontSize`", "حجم خط النص (قطر النقطة يساوي نفس القيمة)")),
    DocRow(cells = listOf("`toast.gap`", "المسافة بين النقطة والنص")),
    DocRow(cells = listOf("`zIndex.toast`", "مطابقة دلالات الطبقات (يحمل `Popup` الخاص بـ Compose طبقة على مستوى النافذة افتراضيًا)")),
    DocRow(cells = listOf("`color.background.overlay` (تعيين ink في طبقة السمة)", "لون خلفية القرص")),
    DocRow(cells = listOf("`color.onPrimary`", "لون النص")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "ألوان النقاط الدلالية")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "بعد show يصبح ظاهرًا ويعرض النص", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "عند انتهاء المدة يُخفى تلقائيًا ويُطلق onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "قبل انتهاء المدة يبقى ظاهرًا ولا يُطلق onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide لا يُطلق onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "النوع success يستهلك لون النجاح", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "عند عدم الظهور لا يُعرض شيء", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # جزء JVM",
    "./gradlew :components:toast:connectedAndroidTest                 # جزء التفاعل (يتطلب جهازًا)",
  ),
)
