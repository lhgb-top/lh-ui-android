package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "مفتاح Switch",
  lede = "عنصر تبديل ثنائي الحالة بمسار على شكل كبسولة ومقبض دائري، يدعم حالة التحميل كعنصر نائب.",
  group = DocGroup.FORM,
  contract = "مصدر العقد: `lh-ui-core/specs/switch.json` · الحالات الذهبية: `cases/switch.json` (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الحالات",
      prose = "التشغيل يأخذ مسارًا باللون الرئيسي، والإيقاف يأخذ مسارًا بلون الحد؛ قطر المقبض = الارتفاع - 2 × `switch.gap`، وحركة الإزاحة تأخذ `motion.duration.base`.",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "الحجم",
      prose = "ثلاثة أحجام تُربط برموز `switch.width` / `switch.height`.",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "ما إذا كان مُشغّلاً (إلزامي)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "رد تغيير حالة المفتاح، يحمل الحالة الجديدة", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "ما إذا كان معطّلاً", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "ما إذا كان قيد التحميل (يُعرض حلقة تحميل في موضع المقبض، ولا يستجيب للنقر)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "الحجم", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("القيمة", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small` (36dp)", "`switch.height.small` (20dp)")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium` (44dp)", "`switch.height.medium` (24dp)")),
        DocRow(cells = listOf("`Large`", "`switch.width.large` (52dp)", "`switch.height.large` (28dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "ثلاثة عروض مسار")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "ثلاثة ارتفاعات مسار")),
    DocRow(cells = listOf("`switch.gap`", "الفراغ بين المقبض والمسار (قطر المقبض = الارتفاع - 2 × gap)")),
    DocRow(cells = listOf("`color.primary.default`", "لون مسار التشغيل")),
    DocRow(cells = listOf("`color.border.base`", "لون مسار الإيقاف")),
    DocRow(cells = listOf("`color.onPrimary`", "لون المقبض")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "لونا مسار / مقبض التعطيل")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "حركة إزاحة المقبض")),
    DocRow(cells = listOf("`loading.strokeWidth`", "عرض حلقة التحميل (إعادة استخدام)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "النقر يقلب الحالة ويطلق onChange", "`LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "النقر في حالة التشغيل يوقفه", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "الحالة المعطّلة لا تُطلق", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "حالة التحميل لا تُطلق", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "التشغيل يستهلك الرمز الرئيسي", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "الإيقاف يستهلك رمز الحد", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # جزء JVM",
    "./gradlew :components:switch:connectedAndroidTest            # جزء التفاعل (يتطلب جهازًا)",
  ),
)
