package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "منطقة نص Textarea",
  lede = "حقل إدخال متعدد الأسطر مُدار، يدعم عدد الأسطر وعدّ الأحرف والحد الأقصى للطول.",
  group = DocGroup.FORM,
  contract = "مصدر العقد: `lh-ui-core/specs/textarea.json` · الحالات الذهبية: `cases/textarea.json` (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الاستخدام الأساسي",
      prose = "الارتفاع الأدنى = عدد الأسطر × ارتفاع السطر × حجم الخط، وبعده يتم التمرير عموديًا.",
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
      title = "عدّ الأحرف",
      prose = "عندما تكون `showCount` تساوي true يُعرض عدّاد في الزاوية السفلية اليمنى (`textarea.countFontSize`، اللون `text.secondary`)؛ و`maxlength` يقتطع الإدخال.",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "القيمة المُدارة (إلزامي)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "رد تغيير القيمة (إلزامي)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "نص العنصر النائب", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "عدد الأسطر (يحدد الارتفاع الأدنى)", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "ما إذا كان معطّلاً", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "ما إذا كان للقراءة فقط", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "ما إذا كان سيُعرض عدّاد الأحرف", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "الحد الأقصى للطول (الزيادة تُقتطع)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "رد التركيز / فقدان التركيز", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "الحشوة (`spacing.md`)")),
    DocRow(cells = listOf("`textarea.fontSize`", "حجم الخط (`typography.fontSize.base`)")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "نصف قطر الزاوية / عرض الحد / عرض حد التركيز")),
    DocRow(cells = listOf("`textarea.countFontSize`", "حجم خط العدّاد (`typography.fontSize.extraSmall`)")),
    DocRow(cells = listOf("`textarea.minHeight`", "المرجع الأساسي للارتفاع الأدنى")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "ارتفاع السطر (تحويل عدد الأسطر)")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "ألوان النص / العدّاد / العنصر النائب")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "لون حد التركيز / الحد الافتراضي")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "ألوان التعطيل الثلاثة")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "الإدخال يطلق onValueChange", "`LhTextareaTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-input`", "الحالة المعطّلة لا تقبل الإدخال", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "العدّاد يعرض «n / max»", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "بدون maxlength يُعرض الطول فقط", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "التركيز يستهلك رمز التركيز", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "الإدخال الزائد يُقتطع", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # جزء JVM",
    "./gradlew :components:textarea:connectedAndroidTest           # جزء التفاعل (يتطلب جهازًا)",
  ),
)
