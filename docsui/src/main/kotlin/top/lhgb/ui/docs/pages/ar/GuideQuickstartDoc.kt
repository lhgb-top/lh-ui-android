package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "البدء السريع",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "أصغر مثال",
      prose = "`LhTheme` هو حاوية السمة، وكل مكوّنات Lh بداخله تستهلك الرموز تلقائيًا؛ وعند عدم تغليفه يتم الرجوع إلى لوحة الألوان الفاتحة.",
      code = listOf(
        "import top.lhgb.ui.button.LhButton",
        "import top.lhgb.ui.button.LhButtonType",
        "import top.lhgb.ui.theme.LhTheme",
        "",
        "@Composable",
        "fun Demo() {",
        "  LhTheme {",
        "    LhButton(",
        "      type = LhButtonType.Primary,",
        "      lhOnClick = { /* معالجة النقر */ },",
        "    ) {",
        "      Text(\"زر رئيسي\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "النوع والحجم",
      prose = "",
      code = listOf(
        "// ستة أنواع",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"افتراضي\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"رئيسي\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"نجاح\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"تحذير\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"خطر\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"معلومات\") }",
        "",
        "// ثلاثة أحجام",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"صغير\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"متوسط\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"كبير\") }",
      ),
    ),
    DocSection(
      title = "الحالات",
      prose = "",
      code = listOf(
        "// التعطيل: لا يستجيب للنقر ويعرض نمط التعطيل",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"معطّل\") }",
        "",
        "// التحميل: يعرض مؤشرًا ولا يطلق lhOnClick",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"جارٍ التحميل\") }",
        "",
        "// عند اجتماعهما: التعطيل له الأولوية (الحالة الذهبية disabled-precedes-loading)",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"التعطيل أولاً\") }",
      ),
    ),
    DocSection(
      title = "الأيقونات وخلفية النمط",
      prose = "",
      code = listOf(
        "// اسم الأيقونة يُحلّ عبر نظام الأيقونات المحلي (انظر LhButtonIcons، الأسماء غير المسجلة لا تُرسم)",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"بحث\") }",
        "",
        "// نمط الخلفية الصيني (يستهلك رمز color.decoration)",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"خلفية نمط\") }",
      ),
    ),
    DocSection(
      title = "السمتان الفاتحة والداكنة",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // كل المكوّنات بداخله تتبدل تلقائيًا إلى الرموز الداكنة (قيم محسوبة مسبقًا في core، بلا مزج ألوان وقت التشغيل)",
        "}",
      ),
    ),
    DocSection(
      title = "وحدة التوثيق الأصلية",
      prose = "إلى جانب توثيق الويب هذا، يوجد في المستودع وحدة توثيق أصلية لأندرويد (`:docsui`): تطابقت صفحات التوثيق بنسبة 1:1 بنية هذا الموقع، لكن المعاينات فيها سلسلة Compose حية وتفاعلية (يمكن نقر الأزرار والكتابة في الحقول وفتح الحوارات) بدل محاكاة HTML الثابتة.\nجاهزة للسحب والإفلات: بعد إضافة الاعتمادية إلى أي مشروع أندرويد، يفتح سطر واحد `LhDocs.launch(context)` التوثيق الكامل (14 صفحة مكوّن + 5 صفحات دليل + تبديل فاتح/داكن + معاينة حية):\nيقوم manifest الخاص بالمكتبة بدمج `LhDocsActivity` بقيمة `exported = false` تلقائيًا، دون حقن أيقونة مشغّل في التطبيق المضيف.\nبناء مستقل: `:docsapp` هو وحدة application رفيعة جدًا (تحتوي على Activity واحد فقط)، وبناءها منفردًا ينتج تطبيق «توثيق lh-ui»:",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // أو إحداثية Maven top.lhgb.ui:docsui",
        "",
        "// أي نقطة دخول (مثل نقر زر «توثيق المكوّنات»)",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "معاينة أمثلة المكوّنات",
      prose = "لا يمكن عرض الواجهة الأصلية مباشرة في المتصفح، لذا يستخدم هذا الموقع محاكي إطار هاتف بـ HTML ثابت مضمّن لإظهار المظهر:\nالمحاكي الداكن متصل بالموقع: بدّل السمة من الزاوية العلوية تتغير الألوان داخل إطار الهاتف (الرموز الداكنة محسوبة مسبقًا في core).",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
