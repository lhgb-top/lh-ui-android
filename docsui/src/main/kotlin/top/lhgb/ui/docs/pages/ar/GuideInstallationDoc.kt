package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideInstallationDoc = DocEntry(
  id = "guide-installation",
  title = "التثبيت",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "متطلبات البيئة",
      prose = "• Android Studio Ladybug أو أحدث (AGP 8.7+)\n• Kotlin 2.1+، و Jetpack Compose (BOM 2024.12+)\n• compileSdk 35، و minSdk 24\n• أداة وقت البناء: Node.js >= 20 + pnpm (تُستخدم فقط لربط الرموز وموقع التوثيق، ولا تدخل وقت التشغيل)",
    ),
    DocSection(
      title = "إضافة الاعتمادية",
      prose = "في تخطيط العائلة، يتم الاعتماد مباشرة على الوحدات الفرعية:",
      code = listOf(
        "// settings.gradle.kts",
        "dependencyResolutionManagement {",
        "  repositories {",
        "    google()",
        "    mavenCentral()",
        "    maven(\"https://maven.pkg.github.com/lhgb-top/lh-ui-android\") // مستودع Maven للعائلة (انتقالي / شبكة داخلية)",
        "  }",
        "}",
        "// app/build.gradle.kts",
        "dependencies {",
        "  implementation(\"top.lhgb.ui:android:0.0.1\") // إحداثية واحدة وفق D22: المكوّنات + الرموز كلها داخله",
        "}",
        "// settings.gradle.kts",
        "include(\":core\")",
        "include(\":components:button\")",
        "project(\":core\").projectDir = file(\"../lh-ui-android/core\")",
        "project(\":components:button\").projectDir = file(\"../lh-ui-android/components/button\")",
      ),
    ),
    DocSection(
      title = "مزامنة الرموز",
      prose = "الرموز منتجات مولّدة، ويجب مزامنتها من `lh-ui-core` (المصدر الأصلي الوحيد، يُمنع التعديل اليدوي):\nيجلب `pnpm link:tokens` حارس وجود من ثلاث مستويات: `../lh-ui-core/dist/android` محليًا → منتج npm الخاص بـ `@lh-ui/core` → خطأ.",
    ),
    DocSection(
      title = "التحقق من التثبيت",
      prose = "",
    ),
  ),
  commands = listOf(

    "./gradlew :components:button:test                # اختبارات JVM (المنطق الخالص للحالات الذهبية)",
    "./gradlew :components:button:assembleDebug       # البناء",
  ),
)
