package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "حقل إدخال Input",
  lede = "حقل إدخال أحادي السطر مُدار، يدعم الحجم والتعطيل والقراءة فقط والمسح وقناع كلمة المرور والحد الأقصى للطول.",
  group = DocGroup.FORM,
  contract = "مصدر العقد: `lh-ui-core/specs/input.json` · الحالات الذهبية: `cases/input.json` (8 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الاستخدام الأساسي",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"أدخل المحتوى\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "الحجم والتركيز",
      prose = "ثلاثة أحجام تُربط برموز `input.height` / `input.paddingHorizontal` / `input.fontSize`؛ عند التركيز يأخذ الحد لون `color.focus` ويتّسع إلى `input.focusRingWidth`.",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "القيمة المُدارة (إلزامي)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "رد تغيير القيمة (إلزامي)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "نص العنصر النائب", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "نوع الإدخال (نص ظاهر / كلمة مرور)", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "الحجم", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "ما إذا كان معطّلاً", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "ما إذا كان للقراءة فقط (غير قابل للتعديل لكن دون نمط التعطيل)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "ما إذا كان سيُعرض زر المسح", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "الحد الأقصى للطول (الزيادة تُقتطع)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "رد التركيز / فقدان التركيز", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "فتحتان أمامية / خلفية", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("القيمة", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small` (32dp)", "`input.paddingHorizontal.small` (12dp)", "`input.fontSize.small` (13sp)")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium` (40dp)", "`input.paddingHorizontal.medium` (16dp)", "`input.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`input.height.large` (48dp)", "`input.paddingHorizontal.large` (20dp)", "`input.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "ثلاث ارتفاعات")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "ثلاث حشوات أفقية")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "ثلاث أحجام خط")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "نصف قطر الزاوية / عرض الحد / عرض حد التركيز")),
    DocRow(cells = listOf("`input.clearIconSize`", "حجم أيقونة المسح")),
    DocRow(cells = listOf("`color.focus`", "لون حد التركيز")),
    DocRow(cells = listOf("`color.text.placeholder`", "لون نص العنصر النائب")),
    DocRow(cells = listOf("`color.border.base`", "لون الحد الافتراضي")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "ألوان التعطيل الثلاثة")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "الإدخال يطلق onValueChange", "`LhInputTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "زر المسح يطلق قيمة فارغة", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "الحالة المعطّلة لا تقبل الإدخال", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "حالة القراءة فقط لا تقبل الإدخال", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "لون العنصر النائب يأخذ رمز العنصر النائب", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "التركيز يستهلك رمز التركيز (فقدان التركيز يأخذ حدًا محايدًا)", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "الحجم الصغير يستهلك رموز الحجم small", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "الإدخال الزائد يُقتطع", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # جزء JVM",
    "./gradlew :components:input:connectedAndroidTest              # جزء التفاعل (يتطلب جهازًا)",
  ),
)
