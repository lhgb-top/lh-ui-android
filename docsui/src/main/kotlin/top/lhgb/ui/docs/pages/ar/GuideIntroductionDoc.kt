package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideIntroductionDoc = DocEntry(
  id = "guide-introduction",
  title = "مقدمة",
  lede = "lh-ui-android هو تنفيذ Android لـ سلالة أصلية من عائلة lh-ui: مكتبة مكوّنات واجهة مبنية على Kotlin + Jetpack Compose.",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "موقعها داخل العائلة",
      prose = "• مركز وقت البناء: `lh-ui-core` هو المصدر الأصلي للرموز للسلالة الأصلية (`tokens/*.json`)، وعقود المكوّنات (`specs/`)، والحالات الذهبية (`cases/`)، وأدوات codegen. يولّد هياكل رموز Kotlin (`LhTokens` / `LhDarkTokens`)، و لا يولّد أي تنفيذ لواجهة المستخدم.\n• مسؤولية هذا المستودع: كتابة واجهات Compose يدويًا، ودمج السمات، وربط تعدادات props مع القيم الافتراضية، وترجمة الحالات الذهبية إلى اختبارات محلية.\n• استقلال عن سلالة الويب: لا يعتمد على `@lh-ui/share`، ولا يفرض الطرفان أي ترتيب إصدار على الآخر. يتم الحفاظ على الاتساق البصري عبر المطابقة اليدوية مع *خط أساس لغة التصميم*.",
      code = listOf(
        "                        ┌── سلالة الويب: share (TS core + SCSS) → plus / vue2 / react / uniapp",
        "عائلة lh-ui (علامة واحدة)├──",
        "                        └── سلالة أصلية: core (رموز JSON + عقود + codegen) → android / ios / harmony / windows",
        "                                                                    ↑ هذا المستودع",
      ),
    ),
    DocSection(
      title = "لغة التصميم",
      prose = "سمة النمط الصيني: لون رئيسي 青花瓷 (سيراميك أزرق) (`#2B4C7C`)، خلفية 宣纸白 (أبيض ورق الأرز) (`#FAF6F0`)، 松花绿 (أخضر الصنوبر) للنجاح، 藤黄 (أصفر الكامبوج) للتحذير، 朱砂 (أحمر الزنجفر) للخطر، مع حزمة خطوط مذيّلة (Noto Serif SC → STSong → SimSun). كل قيمة بصرية هي رمز، ولا تظهر أي قيمة حرفية داخل المكوّنات.",
    ),
    DocSection(
      title = "لماذا Compose",
      prose = "• واجهة توضيحية (declarative) من الدرجة الأولى في المنصة، وتتطابق ذهنيًا مع SwiftUI / ArkUI، مما يسهّل مطابقة العقود عبر الأطراف.\n• لا يوجد وقت تشغيل JS ولا جسر HTML/CSS، مما يلبي متطلبات الأداء الأصلي والملاءمة للأنظمة المضمّنة.\n• `@Preview` هو التوثيق، و`@Composable` هو المكوّن، بشكل متماثل تمامًا مع تجربة تطوير SFC الخاصة بسلالة الويب.",
    ),
    DocSection(
      title = "الحالة الحالية",
      prose = "• جميع مكوّنات P0 الأربعة عشر مسلّمة (button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar)، وكل مكوّن يتبع نفس النموذج الكامل: استهلاك الرموز + تحليل المظهر + Compose UI + اختبارات JVM + Compose UI Test + `@Preview` + صفحة توثيق.\n• وحدة التوثيق الأصلية `:docsui` مسلّمة: جاهزة للسحب والإفلات (`LhDocs.launch(context)`) مع بناء مستقل (`:docsapp`)، والمعاينات هي معاينات Compose حقيقية حية وتفاعلية — انظر البدء السريع.",
    ),
  ),
)
