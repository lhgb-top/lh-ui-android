# البدء السريع

## أصغر مثال

```kotlin
import top.lhgb.ui.button.LhButton
import top.lhgb.ui.button.LhButtonType
import top.lhgb.ui.theme.LhTheme

@Composable
fun Demo() {
  LhTheme {
    LhButton(
      type = LhButtonType.Primary,
      lhOnClick = { /* معالجة النقر */ },
    ) {
      Text("زر رئيسي")
    }
  }
}
```

`LhTheme` هو حاوية السمة، وكل مكوّنات Lh بداخله تستهلك الرموز تلقائيًا؛ وعند عدم تغليفه يتم الرجوع إلى لوحة الألوان الفاتحة.

## النوع والحجم

```kotlin
// ستة أنواع
LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text("افتراضي") }
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("رئيسي") }
LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text("نجاح") }
LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text("تحذير") }
LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text("خطر") }
LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text("معلومات") }

// ثلاثة أحجام
LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text("صغير") }
LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text("متوسط") }
LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text("كبير") }
```

## الحالات

```kotlin
// التعطيل: لا يستجيب للنقر ويعرض نمط التعطيل
LhButton(disabled = true, lhOnClick = {}) { Text("معطّل") }

// التحميل: يعرض مؤشرًا ولا يطلق lhOnClick
LhButton(loading = true, lhOnClick = {}) { Text("جارٍ التحميل") }

// عند اجتماعهما: التعطيل له الأولوية (الحالة الذهبية disabled-precedes-loading)
LhButton(disabled = true, loading = true, lhOnClick = {}) { Text("التعطيل أولاً") }
```

## الأيقونات وخلفية النمط

```kotlin
// اسم الأيقونة يُحلّ عبر نظام الأيقونات المحلي (انظر LhButtonIcons، الأسماء غير المسجلة لا تُرسم)
LhButton(type = LhButtonType.Primary, icon = "search", lhOnClick = {}) { Text("بحث") }

// نمط الخلفية الصيني (يستهلك رمز color.decoration)
LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text("خلفية نمط") }
```

## السمتان الفاتحة والداكنة

```kotlin
val isDark by remember { mutableStateOf(false) }

LhTheme(isDark = isDark) {
  // كل المكوّنات بداخله تتبدل تلقائيًا إلى الرموز الداكنة (قيم محسوبة مسبقًا في core، بلا مزج ألوان وقت التشغيل)
}
```

## وحدة التوثيق الأصلية

إلى جانب توثيق الويب هذا، يوجد في المستودع **وحدة توثيق أصلية لأندرويد** (`:docsui`): تطابقت صفحات التوثيق بنسبة 1:1 بنية هذا الموقع، لكن المعاينات فيها **سلسلة Compose حية وتفاعلية** (يمكن نقر الأزرار والكتابة في الحقول وفتح الحوارات) بدل محاكاة HTML الثابتة.

**جاهزة للسحب والإفلات**: بعد إضافة الاعتمادية إلى أي مشروع أندرويد، يفتح سطر واحد `LhDocs.launch(context)` التوثيق الكامل (14 صفحة مكوّن + 5 صفحات دليل + تبديل فاتح/داكن + معاينة حية):

```kotlin
// build.gradle.kts
implementation(project(":docsui"))  // أو إحداثية Maven top.lhgb.ui:docsui

// أي نقطة دخول (مثل نقر زر «توثيق المكوّنات»)
LhDocs.launch(context)
```

يقوم manifest الخاص بالمكتبة بدمج `LhDocsActivity` بقيمة `exported = false` تلقائيًا، دون حقن أيقونة مشغّل في التطبيق المضيف.

**بناء مستقل**: `:docsapp` هو وحدة application رفيعة جدًا (تحتوي على Activity واحد فقط)، وبناءها منفردًا ينتج تطبيق «توثيق lh-ui»:

```bash
./gradlew :docsapp:installDebug
```

## معاينة أمثلة المكوّنات

لا يمكن عرض الواجهة الأصلية مباشرة في المتصفح، لذا يستخدم هذا الموقع **محاكي إطار هاتف بـ HTML ثابت مضمّن** لإظهار المظهر:

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>lh-ui-android</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">زر افتراضي</button>
      <button class="sim-btn sim-btn--primary">زر رئيسي</button>
      <button class="sim-btn sim-btn--success">نجاح</button>
      <button class="sim-btn sim-btn--warning">تحذير</button>
      <button class="sim-btn sim-btn--danger">خطر</button>
      <button class="sim-btn sim-btn--info">معلومات</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">صغير</button>
      <button class="sim-btn sim-btn--primary sim-btn--large">كبير</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>معطّل</button>
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>جارٍ التحميل
      </button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>بحث</button>
      <button class="sim-btn sim-btn--primary sim-btn--pattern">خلفية نمط</button>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي؛ جميع قيم الأنماط مأخوذة من لقطة رموز core، بنفس المصدر والقيم على الجهاز الحقيقي. المظهر على الجهاز الحقيقي يُحدَّد بواسطة <code>@Preview</code> ولقطات الشاشة من الأجهزة.</p>

> المحاكي الداكن متصل بالموقع: بدّل السمة من الزاوية العلوية تتغير الألوان داخل إطار الهاتف (الرموز الداكنة محسوبة مسبقًا في core).
