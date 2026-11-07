package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "مربع اختيار Checkbox",
  lede = "مربّع اختيار مُدار، يدعم الحالة الجزئية (indeterminate) والتعطيل.",
  group = DocGroup.FORM,
  contract = "مصدر العقد: `lh-ui-core/specs/checkbox.json` · الحالات الذهبية: `cases/checkbox.json` (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الحالات",
      prose = "التحديد / التحديد الجزئي يعني خلفية رئيسية مصمتة + مؤشر onPrimary (علامة صح / شريط أفقي)، وعدم التحديد يعني حدًا فقط.",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"أوافق على الاتفاقية\",",
        ")",
      ),
    ),
    DocSection(
      title = "الحجم",
      prose = "ثلاثة أحجام للمربّع تُربط برموز `checkbox.size.*`.",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "ما إذا كان محددًا (إلزامي)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "رد تغيير حالة التحديد، يحمل حالة التحديد الجديدة", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "ما إذا كان التحديد جزئيًا (حالة غير محددة)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "ما إذا كان معطّلاً", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "نص العلامة", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "حجم المربّع", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "محتوى العلامة (يتجاوز label، فتحة default في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("القيمة", "رمز الحجم"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large` (24dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "ثلاثة أحجام مربّع")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "نصف قطر زاوية المربّع وعرض الحد")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "عرض حد علامة الصح / الشريط الأفقي")),
    DocRow(cells = listOf("`checkbox.gap`", "المسافة بين المربّع والعلامة")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "لون خلفية التحديد / لون المؤشر")),
    DocRow(cells = listOf("`color.border.base`", "لون حد عدم التحديد")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "ألوان التعطيل الثلاثة")),
    DocRow(cells = listOf("`color.text.primary`", "لون العلامة")),
    DocRow(cells = listOf("`typography.fontSize.base`", "حجم خط العلامة")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "النقر يقلب الحالة ويطلق onChange", "`LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "النقر في الحالة المحددة يلغي التحديد", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "النقر في الحالة الجزئية يحوّلها إلى محددة", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "الحالة المعطّلة لا تُطلق", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "التحديد يستهلك الرموز الرئيسية", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "عدم التحديد يستهلك رمز الحد", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # جزء JVM",
    "./gradlew :components:checkbox:connectedAndroidTest           # جزء التفاعل (يتطلب جهازًا)",
  ),
)
