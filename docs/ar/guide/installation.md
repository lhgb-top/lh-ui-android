# التثبيت

## متطلبات البيئة

- Android Studio Ladybug أو أحدث (AGP 8.7+)
- Kotlin 2.1+، و Jetpack Compose (BOM 2024.12+)
- compileSdk 35، و minSdk 24
- أداة وقت البناء: Node.js >= 20 + pnpm (تُستخدم فقط لربط الرموز وموقع التوثيق، **ولا تدخل وقت التشغيل**)

## إضافة الاعتمادية

### Maven (بعد استكمال سلسلة النشر)

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // مستودع Maven للعائلة (انتقالي / شبكة داخلية)
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // إحداثية واحدة وفق D22: المكوّنات + الرموز كلها داخله
}
```

### التكامل من المصدر (المرحلة الحالية)

في تخطيط العائلة، يتم الاعتماد مباشرة على الوحدات الفرعية:

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## مزامنة الرموز

الرموز منتجات مولّدة، ويجب مزامنتها من `lh-ui-core` (المصدر الأصلي الوحيد، يُمنع التعديل اليدوي):

```bash
cd ../lh-ui-core && pnpm codegen   # توليد منتجات رموز dist/android
cd ../lh-ui-android
pnpm install                        # تثبيت أداة وقت البناء
pnpm link:tokens                    # مزامنة الرموز إلى core/src/main/kotlin/top/lhgb/ui/tokens/
```

يجلب `pnpm link:tokens` حارس وجود من ثلاث مستويات: `../lh-ui-core/dist/android` محليًا → منتج npm الخاص بـ `@lh-ui/core` → خطأ.

## التحقق من التثبيت

```bash
./gradlew :components:button:test                # اختبارات JVM (المنطق الخالص للحالات الذهبية)
./gradlew :components:button:assembleDebug       # البناء
```
