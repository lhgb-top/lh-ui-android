package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTagPlainPreview
import top.lhgb.ui.docs.previews.DocTagSizeAndMarkPreview
import top.lhgb.ui.docs.previews.DocTagTypesPreview

val tagDoc = DocEntry(
  id = "tag",
  title = "وسم Tag",
  lede = "وسم بلون دلالي، يدعم النمط البسيط (plain) والعلامة ذات الزوايا الكاملة وزر الإغلاق.",
  group = DocGroup.BASIC,
  contract = "مصدر العقد: `lh-ui-core/specs/tag.json` · الحالات الذهبية: `cases/tag.json` (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الأنواع",
      prose = "ستة أنواع دلالية تحدد لون الخلفية ولون النص.",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"نجاح\") }",
      ),
    ),
    DocSection(
      title = "النمط البسيط (plain)",
      prose = "`plain` يعني خلفية شفافة + حد ونص باللون الدلالي.",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "الحجم والعلامة",
      prose = "ثلاثة أحجام تُربط برموز `tag.height` / `tag.paddingHorizontal` / `tag.fontSize`؛ و`mark` يأخذ نصف قطر يساوي نصف الارتفاع.",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "نص الوسم (كتابة مختصرة، واحد من `text` أو `content`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "النوع الدلالي", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "الحجم", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "ما إذا كان النمط بسيطًا (خلفية شفافة + حد بلون دلالي)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "ما إذا كانت الزوايا كلها دائرية (نصف الارتفاع)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "ما إذا كان سيُعرض زر الإغلاق", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "رد الإغلاق؛ لا يُطلق عندما تكون `closable` تساوي false", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "محتوى الوسم (يتجاوز text، فتحة default في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("القيمة", "الخلفية (غير plain)", "الحد/النص (plain)"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + حد `color.border.base`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("القيمة", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small` (20dp)", "`tag.paddingHorizontal.small` (8dp)", "`tag.fontSize.small` (12sp)")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium` (24dp)", "`tag.paddingHorizontal.medium` (10dp)", "`tag.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`tag.height.large` (28dp)", "`tag.paddingHorizontal.large` (12dp)", "`tag.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "ثلاث ارتفاعات")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "ثلاث حشوات أفقية")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "ثلاث أحجام خط")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "نصف قطر الزاوية وعرض الحد")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "خلفية / حد / نص دلالي")),
    DocRow(cells = listOf("`color.onPrimary`", "لون النص غير الخالص")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "ألوان التعطيل الثلاثة")),
    DocRow(cells = listOf("`spacing.xs`", "المسافة بين زر الإغلاق والنص")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "النوع الافتراضي يأخذ خلفية محايدة + لون النص الرئيسي + الحد الأساسي", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "النوع الرئيسي يستهلك الرموز الرئيسية", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain يستهلك رموز الخلفية الدلالية الفاتحة", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "الحجم الصغير يستهلك رموز small (تأكيد عكسي بعدم تطبيق large)", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round يجعل نصف القطر يساوي نصف الارتفاع", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "النقر على الإغلاق يطلق onClose", "`LhTagTest.closable_tap_emits_onClose` (Compose UI Test)")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # جزء JVM",
    "./gradlew :components:tag:connectedAndroidTest             # جزء التفاعل (يتطلب جهازًا)",
  ),
)
