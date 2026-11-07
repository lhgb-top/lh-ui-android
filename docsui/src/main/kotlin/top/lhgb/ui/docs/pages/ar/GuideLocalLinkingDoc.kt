package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "الربط المحلي",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "اصطلاح تخطيط العائلة",
      prose = "عند وجود جميع المستودعات تحت نفس الدليل الأصلي، تُربط الرموز محليًا مباشرة؛ وعند عدم وجود المستودعات الشقيقة (استنساخ مستودع واحد في CI) يتم الرجوع تلقائيًا إلى حزمة npm.\nوهذا يتطابق هيكليًا مع آلية حارس vite alias الخاصة بسلالة الويب (الخطة الرئيسية D18).",
      code = listOf(
        "lh-ui/                        # الدليل الأصلي للعائلة (ليس مستودعًا)",
        "├── lh-ui-core/               # المصدر الأصلي للرموز + codegen",
        "├── lh-ui-android/            # هذا المستودع",
        "├── lh-ui-ios/                # مستودع شقيق",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "حارس من ثلاث مستويات",
      prose = "يبحث `scripts/link-tokens.mjs` عن منتجات الرموز حسب الأولوية:",
    ),
    DocSection(
      title = "العمل اليومي",
      prose = "",
    ),
    DocSection(
      title = "بوابة CI",
      prose = "",
    ),
    DocSection(
      title = "ممنوعات",
      prose = "• ❌ التعديل اليدوي على `core/src/main/kotlin/top/lhgb/ui/tokens/` (منتجات مولّدة، مُتجاهَلة في gitignore)\n• ❌ إضافة ملفات الرموز المولّدة إلى المستودع\n• ❌ كتابة قيم حرفية للألوان/المسافات داخل المكوّنات (مثل `Color(0xFF2B4C7C)` و`16.dp` و`14.sp`)\n• ❌ كتابة شكل الاعتمادية `file:` / `link:` في `build.gradle.kts` وتنفيذها (الربط المحلي يقتصر على الجهاز المحلي)",
    ),
    DocSection(
      title = "استكشاف الأخطاء",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # التحقق من مزامنة الرموز (مقارنة عدد الملفات، إذا لم تكن مزامنة فكود الخروج 1)",
  ),
)
