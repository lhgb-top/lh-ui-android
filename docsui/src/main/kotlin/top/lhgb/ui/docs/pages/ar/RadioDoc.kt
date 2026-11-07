package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "زر اختيار Radio",
  lede = "زر اختيار مُدار، النقر عليه مرة أخرى بعد التحديد لا يلغي التحديد (دلالة الاختيار الواحد).",
  group = DocGroup.FORM,
  contract = "مصدر العقد: `lh-ui-core/specs/radio.json` · الحالات الذهبية: `cases/radio.json` (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الحالات ومجموعة الاختيار",
      prose = "لا يحتوي المكوّن نفسه على منطق المجموعة، فالتزام خيارات المجموعة (التحديد الواحد فقط) يُحافظ عليه المضيف (يُستخدم `value` لتعريف العنصر داخل المجموعة).",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"الخيار الأول\" to \"a\", \"الخيار الثاني\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "الحجم",
      prose = "ثلاثة أحجام للحلقة تُربط برموز `radio.size.*`؛ قطر النقطة الداخلية = الحجم × `radio.dotScale`.",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "ما إذا كان محددًا (إلزامي)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "يُطلق عند الانتقال من عدم التحديد إلى التحديد، ويحمل `checked = true`", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "ما إذا كان معطّلاً", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "نص العلامة", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "قيمة الاختيار (معرّف داخل المجموعة، لا يحتوي المكوّن نفسه على منطق مجموعة)", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "حجم الحلقة", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "محتوى العلامة (يتجاوز label، فتحة default في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("القيمة", "رمز الحجم", "قطر النقطة الداخلية"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small` (16dp)", "16dp × `radio.dotScale` (0.5)")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium` (20dp)", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large` (24dp)", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "ثلاثة أحجام حلقة")),
    DocRow(cells = listOf("`radio.ringWidth`", "عرض حد الحلقة الخارجية")),
    DocRow(cells = listOf("`radio.dotScale`", "نسبة النقطة الداخلية إلى قطر الحلقة الخارجية (كسر)")),
    DocRow(cells = listOf("`radio.gap`", "المسافة بين الحلقة والعلامة")),
    DocRow(cells = listOf("`color.primary.default`", "لون حد التحديد والنقطة")),
    DocRow(cells = listOf("`color.border.base`", "لون حد عدم التحديد")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "ألوان حد التعطيل / النقطة / النص")),
    DocRow(cells = listOf("`color.text.primary`", "لون العلامة")),
    DocRow(cells = listOf("`typography.fontSize.base`", "حجم خط العلامة")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "النقر على غير محدد يطلق onChange(true)", "`LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "النقر على محدد لا يُطلق", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "الحالة المعطّلة لا تُطلق", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "التحديد يستهلك الرموز الرئيسية", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "عدم التحديد يستهلك رمز الحد", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "الحجم الصغير يستهلك رمز الحجم small", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # جزء JVM",
    "./gradlew :components:radio:connectedAndroidTest             # جزء التفاعل (يتطلب جهازًا)",
  ),
)
