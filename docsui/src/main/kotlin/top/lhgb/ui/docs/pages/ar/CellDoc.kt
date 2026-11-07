package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "خلية Cell",
  lede = "خلة قائمة، بتخطيط ثلاثي الأجزاء: أيقونة / عنوان + وصف / قيمة يمينية + سهم، وتُستخدم غالبًا مع القوائم.",
  group = DocGroup.BASIC,
  contract = "مصدر العقد: `lh-ui-core/specs/cell.json` · الحالات الذهبية: `cases/cell.json` (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الاستخدام الأساسي",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"عنوان مع وصف\",",
        "  label = \"نص الوصف\",",
        "  value = \"قيمة\",",
        "  isLink = true,",
        "  lhOnClick = { /* الانتقال */ },",
        ")",
      ),
    ),
    DocSection(
      title = "الإلزام والربط",
      prose = "يرسم `required` نجمة حمراء قبل العنوان (`color.danger.default`)؛ ويرسم `isLink` أيقونة سهم يميني في النهاية.",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "العنوان (النص الرئيسي)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "القيمة اليمينية (نص ثانوي، محاذاة لليمين)", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "نص الشرح أسفل العنوان", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "ما إذا كان سيُرسم خط فاصل سفلي (`color.border.lighter`)", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "ما إذا كان قابلاً للنقر؛ عندما يكون true يعرض سهمًا مؤشرًا يمينيًا ويستجيب للنقر", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "ما إذا كان سيُعرض نجمة إلزامية حمراء قبل العنوان (`color.danger.default`)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "اسم الأيقونة اليسرى (نظام الأيقونات المحلي)؛ عند الفراغ لا تُرسم", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "رد النقر؛ لا يُطلق عندما تكون `clickable` تساوي false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "محتوى العنوان (يتجاوز title، فتحة default في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "محتوى القيمة اليمينية (يتجاوز value، فتحة value في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "الحشوة الرأسية (مرجع `spacing.md`)")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "الحشوة الأفقية (`spacing.md`)")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "أحجام خط العنوان / الوصف / القيمة")),
    DocRow(cells = listOf("`cell.gap`", "المسافة بين الأيقونة والعنوان (`spacing.xs`)")),
    DocRow(cells = listOf("`icon.size.medium`", "حجم الأيقونة اليسرى وسهم المؤشر")),
    DocRow(cells = listOf("`cell.borderWidth`", "عرض خط الفاصل السفلي")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "ارتفاع سطر النص")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "ألوان العنوان / الوصف / القيمة")),
    DocRow(cells = listOf("`color.danger.default`", "نجمة الإلزام")),
    DocRow(cells = listOf("`color.border.lighter`", "خط الفاصل السفلي")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "العنوان والقيمة يعرضان نصًا", "`LhCellTest.title_and_value_render` (Compose UI Test)")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label يأخذ رمز لون النص الثانوي", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "علامة الإلزام تأخذ رمز لون الخطر", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "خط الفاصل يستهلك رمز الحد", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "النقر يطلق onClick", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "الحالة غير القابلة للنقر لا تُطلق", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # جزء JVM",
    "./gradlew :components:cell:connectedAndroidTest              # جزء التفاعل (يتطلب جهازًا)",
  ),
)
