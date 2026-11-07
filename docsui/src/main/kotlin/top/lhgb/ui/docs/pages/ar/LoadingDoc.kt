package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "تحميل Loading",
  lede = "مؤشر تحميل بحلقة دوّارة، مدة دورته مأخوذة من `motion.duration.base` (بالميلي ثانية).",
  group = DocGroup.BASIC,
  contract = "مصدر العقد: `lh-ui-core/specs/loading.json` · الحالات الذهبية: `cases/loading.json` (5 حالات، مكوّن عرض خالص، تُرجمت إلى اختبارات JVM)",
  sections = listOf(
    DocSection(
      title = "الحجم والدرجة اللونية",
      prose = "ثلاثة أحجام تُربط برموز `loading.size.*`، وعرض الحلقة مأخوذ من `loading.strokeWidth`، والدرجة اللونية من الألوان الدلالية.",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "قطر المؤشر", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "لون الحلقة", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("القيمة", "رمز الحجم"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`loading.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("القيمة", "رمز اللون"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.regular`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "ثلاثة أقطار")),
    DocRow(cells = listOf("`loading.strokeWidth`", "عرض حد الحلقة")),
    DocRow(cells = listOf("`color.text.regular`", "درجة default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "درجات لونية دلالية")),
    DocRow(cells = listOf("`motion.duration.base`", "مدة دورة كاملة (عدد صحيح بالميلي ثانية على جانب الجهاز)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "الحجم المتوسط يأخذ رمز الحجم medium", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "الحجم الكبير يأخذ رمز الحجم large", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "درجة primary تأخذ رمز اللون الرئيسي", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "درجة success تأخذ رمز لون النجاح", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "عرض مؤشر الحلقة (عرض الحد يستهلك الرمز)", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # جزء JVM (عرض خالص، بلا حالات تفاعل)",
  ),
)
