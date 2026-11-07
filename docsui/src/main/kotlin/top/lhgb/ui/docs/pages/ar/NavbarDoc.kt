package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "شريط تنقل Navbar",
  lede = "شريط العنوان أعلى الصفحة، بتخطيط ثلاثي الأجزاء يسار / وسط / يمين، يُستخدم غالبًا للرجوع وعرض العنوان.",
  group = DocGroup.OVERLAY,
  contract = "مصدر العقد: `lh-ui-core/specs/navbar.json` · الحالات الذهبية: `cases/navbar.json` (6 حالات، تُرجمت تعيينات الرموز إلى اختبارات JVM، وتُرجمتا نقرتا اليسار واليمين إلى Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الاستخدام",
      prose = "ثلاثة أجزاء متساوية العرض (كل منها `weight(1f)`)، والعنوان في المنتصف أفقيًا ضمن العرض المتاح؛ ويُرسم افتراضيًا خط فاصل سفلي.",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"عنوان الصفحة\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"رجوع\",",
        "  rightText = \"المزيد\",",
        "  onLeftClick = { /* الرجوع */ },",
        "  onRightClick = { /* المزيد */ },",
        ")",
      ),
    ),
    DocSection(
      title = "المحتوى الجانبي",
      prose = "يمكن أن يتواجد النص والأيقونة معًا (الأيقونة على اليسار والنص على اليمين، والمسافة `spacing.xs`)؛ وعند عدم وجود أي محتوى على أحد الجانبين لا يستجيب للنقر\n(الحالة الذهبية `no-left-content-no-onleftclick`: تلك العقدة لا تمتلك حتى دلالة القابلية للنقر).",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "العنوان في المنتصف", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "يُطلق عند النقر على المنطقة اليسرى", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "يُطلق عند النقر على المنطقة اليمنى", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "نص الزر الأيسر؛ يمكن أن يتواجد مع leftIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "اسم الأيقونة اليسرى (نظام الأيقونات المحلي، مثل `\"arrow-left\"`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "نص الزر الأيمن؛ يمكن أن يتواجد مع rightIcon", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "اسم الأيقونة اليمنى", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "ما إذا كان سيُرسم خط فاصل سفلي", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "محتوى العنوان (يتجاوز title، فتحة default في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "المحتوى الأيسر (يتجاوز leftText/leftIcon، فتحة left في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "المحتوى الأيمن (يتجاوز rightText/rightIcon، فتحة right في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "ارتفاع شريط التنقل (44dp)")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "الحشوة الأفقية يسارًا ويمينًا (تتبع `spacing.md`)")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "حجم خط العنوان والنصين الجانبيين")),
    DocRow(cells = listOf("`navbar.iconSize`", "حجم الأيقونتين الجانبيتين (تتبع `icon.size.medium`)")),
    DocRow(cells = listOf("`navbar.borderWidth`", "عرض خط الفاصل السفلي")),
    DocRow(cells = listOf("`zIndex.sticky`", "مطابقة دلالات طبقة التثبيت العلوية")),
    DocRow(cells = listOf("`color.background.default`", "لون الخلفية")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "لون العنوان / النصين الجانبيين")),
    DocRow(cells = listOf("`color.border.lighter`", "لون خط الفاصل السفلي")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "النقر على اليسار يطلق onLeftClick", "`LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test)")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "النقر على اليمين يطلق onRightClick", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "عند عدم وجود محتوى أيسر لا يُطلق", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "العنوان يعرض نصًا", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "خط الفاصل يستهلك رمز الحد", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "الارتفاع يستهلك رمز navbar", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # جزء JVM",
    "./gradlew :components:navbar:connectedAndroidTest                # جزء التفاعل (يتطلب جهازًا)",
  ),
)
