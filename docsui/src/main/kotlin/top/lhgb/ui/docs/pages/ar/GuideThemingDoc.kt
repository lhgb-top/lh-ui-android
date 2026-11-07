package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "السمات والوضع الداكن",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "نموذج الرموز",
      prose = "المصدر الأصلي للرموز في السلالة الأصلية هو `lh-ui-core/tokens/*.json`، ويولّد codegen منتج Android:\nهذا يتطابق مع ios / harmony / windows (كائنات متوازية)، ويختلف عن شكل docs (دمج JSON السطحي).\nوقد صُمّمت طبقة السمات بناءً عليه: يحمل `LhColors` الألوان فقط، بينما تأخذ المكوّنات الأحجام مباشرة من `LhTokens`.",
    ),
    DocSection(
      title = "LhColors",
      prose = "يقوم `LightLhColors` / `DarkLhColors` ببسط الكائنين المولّدين في لوحة ألوان واحدة غير قابلة للتغيير:",
      code = listOf(
        "@Immutable",
        "data class LhColors(",
        "  val primary: Color, val onPrimary: Color,",
        "  val success: Color, val warning: Color, val danger: Color, val info: Color,",
        "  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */",
        ")",
        "",
        "val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)",
        "val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)",
      ),
    ),
    DocSection(
      title = "LhTheme",
      prose = "عند عدم تغليفه بـ `LhTheme` يتم الرجوع إلى `LightLhColors` (الافتراضي هو الوضع الفاتح).",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // يُحقن LocalLhColors بـ DarkLhColors، فيستهلكها المكوّنات الداخلية تلقائيًا",
        "}",
      ),
    ),
    DocSection(
      title = "الحساب المسبق للألوان الداكنة (مهم)",
      prose = "تستخدم سلالة الويب CSS `color-mix()` لحساب الألوان الداكنة بشكل ديناميكي، أما في الأصل فلا يوجد تتالي CSS، لذلك قام core بحساب التعابير الداكنة\nمسبقًا كقيم حرفية (مثل `rgba(61,100,158,0.25)` و`#B1C1D8`). والنتيجة (الخطة الرئيسية R-n6):\nعند تغيير لون preset الرئيسي، لا تتبع قيم الحساب المسبق الداكنة تلقائيًا، بل يجب إعادة حسابها يدويًا وتحديث\n`lh-ui-core/tokens/color-dark.json`، وتحديث *خط أساس لغة التصميم*.",
    ),
    DocSection(
      title = "مرجع الرموز السريع",
      prose = "يوجد جدول الرموز الكامل (فاتح/داكن/شرح) في مرجع رموز lh-ui-core،\nأو شغّل `pnpm codegen` في مستودع core ثم افتح `dist/docs/tokens.md`. ولتعيين الرموز على مستوى المكوّن انظر\nمرجع رموز Button.",
    ),
    DocSection(
      title = "مسبق الإعداد المخصص (محجوز)",
      prose = "يدعم `LhTheme(colors = ...)` تمرير لوحة ألوان مخصصة، وستُفتح إمكانية السمات المسبقة الإعداد في إصدار لاحق مع\nرموز السمات المسبقة الإعداد الخاصة بـ core.",
    ),
  ),
)
