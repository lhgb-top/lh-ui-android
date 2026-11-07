package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "منتقي Picker",
  lede = "منتقي عجلة سفلي ذو عمود واحد، العنصر الأقرب إلى المؤشر المركزي بعد توقف التمرير هو العنصر المحدد.",
  group = DocGroup.OVERLAY,
  contract = "مصدر العقد: `lh-ui-core/specs/picker.json` · الحالات الذهبية: `cases/picker.json` (8 حالات، تُرجمت عمليات clamp وتعيينات الرموز إلى اختبارات JVM، وتُرجم تفاعلات التمرير والأزرار إلى Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الاستخدام",
      prose = "يظهر من الأسفل: منطقة الستارة في الأعلى واللوحة في الأسفل (بزوايا دائرية علوية). تتكون شريط الأدوات من ثلاثة أجزاء: «إلغاء / عنوان / تأكيد».",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"الخيار الأول\", \"الخيار الثاني\", \"الخيار الثالث\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"اختر\",",
        ")",
      ),
    ),
    DocSection(
      title = "آلية التحديد",
      prose = "العجلة قائمة قابلة للتمرير، ارتفاع كل عنصر `picker.itemHeight`؛ والمؤشر المركزي عبارة عن خطين أفقيين المسافة بينهما = itemHeight.\nبعد توقف التمرير يُحسب فهرس التحديد بتقريب مسافة التمرير، وعند التغير يُطلق `onValueChange`.\nعند خروج `value` عن النطاق يتم clamp إلى نطاق المرشحين بواسطة `LhPickerDefaults.clamp`.",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "ما إذا كان سيُعرض؛ عندما يكون false لا يُعرض أي محتوى", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "مصفوفة نصوص المرشحين (عمود واحد)", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "يُطلق عند توقف العجلة وتغير العنصر المحدد، ويحمل الفهرس الجديد", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "يُطلق عند النقر على زر التأكيد، ويحمل فهرس العنصر المحدد الحالي", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "يُطلق عند النقر على زر الإلغاء", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "يُطلق بعد التأكيد/الإلغاء، وبناءً عليه يضبط المضيف visible على false", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "فهرس العنصر المحدد (ابتداء من 0)؛ عند الخروج عن النطاق يتم clamp", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "العنوان في وسط شريط الأدوات", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "نص زر التأكيد", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "نص زر الإلغاء", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "منطقة عجلة المحتوى (تتجاوز عرض المرشحين الافتراضي، فتحة default في العقد)", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "شريط الأدوات (يتجاوز التأكيد/العنوان/الإلغاء الافتراضيين، فتحة toolbar في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "حالة قائمة العجلة (تُوفَّر افتراضيًا بواسطة `rememberLhPickerListState(value, options)`؛ ويمكن حقنها خارجيًا في الاختبارات)", "`LazyListState`", "انظر لليسار")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل (يُطبّق على اللوحة)", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "ارتفاع اللوحة (260dp)")),
    DocRow(cells = listOf("`picker.itemHeight`", "ارتفاع كل عنصر (40dp، وهو أيضًا المسافة بين خطي المؤشر المركزي)")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "ارتفاع شريط الأدوات (44dp)")),
    DocRow(cells = listOf("`picker.itemFontSize`", "حجم خط العناصر المرشحة")),
    DocRow(cells = listOf("`picker.borderRadius`", "نصف قطر زاوية اللوحة العلوية")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "الحشوة الأفقية للوحة (تتبع `spacing.md`)")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "مطابقة دلالات الطبقات (يحمل `Popup` الخاص بـ Compose طبقة على مستوى النافذة افتراضيًا)")),
    DocRow(cells = listOf("`color.background.overlay`", "لون الستارة")),
    DocRow(cells = listOf("`color.background.default`", "لون خلفية اللوحة")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "لون العنوان / العناصر المرشحة")),
    DocRow(cells = listOf("`color.border.lighter`", "لون خطي المؤشر المركزي")),
    DocRow(cells = listOf("`color.primary.default`", "لون زر التأكيد")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "التأكيد يحمل الفهرس الحالي + onClose", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "بعد التأكيد يُطلق onClose", "نفس ما سبق")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "الإلغاء يطلق onCancel + onClose", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "التمرير يغير التحديد ويطلق onValueChange", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "عندما تكون value سالبة يتم clamp إلى 0", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "عند خروج value عن النطاق يتم clamp إلى العنصر الأخير", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "العنوان يعرض نصًا", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "عند عدم الظهور لا يُعرض شيء", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # جزء JVM",
    "./gradlew :components:picker:connectedAndroidTest               # جزء التفاعل (يتطلب جهازًا)",
  ),
)
