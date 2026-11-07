package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "أيقونة Icon",
  lede = "مكوّن قاعدة الأيقونات. أيقونات المكوّنات الأخرى (cell / input / navbar وغيرها) تُعرض جميعها عبر السجل المحلي لهذا المكوّن.",
  group = DocGroup.BASIC,
  contract = "مصدر العقد: `lh-ui-core/specs/icon.json` · الحالات الذهبية: `cases/icon.json` (5 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الحجم والدرجة اللونية",
      prose = "ثلاثة أحجام تُربط برموز `icon.size.*`؛ درجة `default` تأخذ `color.text.primary`، والباقي يأخذ الألوان الدلالية.",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "الدرجات اللونية الدلالية",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "سجل الأيقونات",
      prose = "`LhIcons` هو سجل يربط أسماء الأيقونات المحلية بمتجهات Material `ImageVector`، واسم الأيقونة غير المسجّل يُرجع `null` (لا يُرسم ولا يُطرح استثناء):",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "اسم الأيقونة (يُحلّ عبر سجل `LhIcons`)", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "حجم الأيقونة", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "الدرجة اللونية الدلالية", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "وصف إمكانية الوصول؛ يأخذ افتراضيًا اسم الأيقونة، وتمرير `null` صراحةً يُعلّمها أيقونة زخرفية", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "رد النقر؛ عندما يكون null لا يمكن النقر عليه", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("القيمة", "رمز الحجم"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`icon.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("القيمة", "رمز اللون"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "ثلاثة أحجام")),
    DocRow(cells = listOf("`color.text.primary`", "درجة default")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "درجات لونية دلالية")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "درجتا regular / secondary")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "النقر يطلق onClick", "`LhIconTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "الدرجة الافتراضية تأخذ رمز لون النص الرئيسي", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "درجة primary تأخذ رمز اللون الرئيسي", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "الحجم الصغير يأخذ رمز الحجم small (تأكيد عكسي بعدم تطبيق large)", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "اسم الأيقونة المسجّل يُحلّ إلى متجه؛ وغير المسجّل لا يُرسم", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # جزء JVM",
    "./gradlew :components:icon:connectedAndroidTest            # جزء التفاعل (يتطلب جهازًا)",
  ),
)
