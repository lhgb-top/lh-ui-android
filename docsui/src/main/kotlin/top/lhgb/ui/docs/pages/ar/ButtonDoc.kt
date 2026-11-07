package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "زر Button",
  lede = "زر إجراء أساسي. يُطلق إجراءً فوريًا، ويدعم النوع والحجم والتحميل والتعطيل والأيقونة وخلفية النمط الصيني.",
  group = DocGroup.BASIC,
  contract = "مصدر العقد: `lh-ui-core/specs/button.json` · الحالات الذهبية: `cases/button.json` (8 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الأنواع",
      prose = "ستة أنواع تحدد لون الخلفية ولون النص.",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"زر رئيسي\") }",
      ),
    ),
    DocSection(
      title = "الأحجام",
      prose = "ثلاثة أحجام تُربط برموز `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "الحالات",
      prose = "لا يُطلق `lhOnClick` لا في حالة التعطيل ولا أثناء التحميل؛ وعند اجتماعهما تكون الأولوية للتعطيل (الحالة الذهبية `disabled-precedes-loading`).",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "الأيقونات وخلفية النمط",
      prose = "تُحلّ أسماء الأيقونات عبر نظام الأيقونات المحلي (`LhButtonIcons`، يدعم حاليًا search / refresh / check / close، والأسماء غير المسجلة لا تُرسم).\nتستهلك خلفية النمط رمز `color.decoration`، وتُطبّق فوق لون الخلفية وتحت النص، فلا تؤثر على التباين.",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "السمتان الفاتحة والداكنة",
      prose = "بدّل سمة الموقع من الزاوية العلوية تتغير ألوان الأزرار داخل إطار هاتف المحاكي؛ وعلى الجهاز الحقيقي يتم التبديل عبر `LhTheme(isDark = true)`.",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "رد النقر؛ لا يُطلق عندما تكون `disabled` أو `loading` تساوي true", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "نوع الزر، يحدد لون الخلفية ولون النص", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "حجم الزر، يُربط برموز الأحجام", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "ما إذا كان الزر معطّلاً", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "ما إذا كان الزر قيد التحميل (يعرض مؤشرًا ولا يطلق lhOnClick)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "اسم الأيقونة (يُحلّ عبر نظام الأيقونات المحلي)", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "ما إذا كان سيتم رسم نمط الخلفية الصيني", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "محتوى نص الزر (فتحة default في العقد)", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("القيمة", "لون الخلفية", "لون النص"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("القيمة", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small` (32dp)", "`button.paddingHorizontal.small` (16dp)", "`button.fontSize.small` (13sp)", "`button.iconSize.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium` (40dp)", "`button.paddingHorizontal.medium` (20dp)", "`button.fontSize.medium` (14sp)", "`button.iconSize.medium` (18dp)")),
        DocRow(cells = listOf("`Large`", "`button.height.large` (48dp)", "`button.paddingHorizontal.large` (24dp)", "`button.fontSize.large` (16sp)", "`button.iconSize.large` (20dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "ثلاث ارتفاعات")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "ثلاث حشوات أفقية")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "ثلاث أحجام خط")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "ثلاثة أحجام أيقونات")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "نصف قطر الزاوية (يتبع `radius.base`) وعرض الحد")),
    DocRow(cells = listOf("`button.fontWeight`", "وزن الخط (يتبع `typography.fontWeight.primary`)")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "خلفية/نص النوع الرئيسي")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "خلفيات الأنواع الدلالية")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "خلفية/نص/حد النوع الافتراضي")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "ألوان التعطيل الثلاثة")),
    DocRow(cells = listOf("`color.decoration`", "لون النمط")),
    DocRow(cells = listOf("`shadow.lighter`", "ارتفاع (elevation) النوع الافتراضي")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "حركة استجابة الضغط")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "النقر يطلق onClick مرة واحدة", "`LhButtonTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-click`", "الحالة المعطّلة لا تُطلق", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "حالة التحميل لا تُطلق", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "الأولوية للتعطيل", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "النوع الافتراضي يعرض نصًا", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "النوع الرئيسي يستهلك الرموز الرئيسية", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "الحجم الصغير يستهلك رموز الحجم small", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "خلفية النمط تستهلك لون الزخرفة", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "اسم الأيقونة المسجّل يعرض متجهًا؛ وغير المسجّل لا يُرسم", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # جزء JVM",
    "./gradlew :components:button:connectedAndroidTest        # جزء التفاعل (يتطلب جهازًا)",
  ),
)
