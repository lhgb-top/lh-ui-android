# وسم Tag

وسم بلون دلالي، يدعم النمط البسيط (plain) والعلامة ذات الزوايا الكاملة وزر الإغلاق.

> مصدر العقد: [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> الحالات الذهبية: [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag) (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الأنواع

ستة أنواع دلالية تحدد لون الخلفية ولون النص.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أنواع Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">افتراضي</span><span class="sim-tag sim-tag--success">نجاح</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">تحذير</span><span class="sim-tag sim-tag--danger">خطر</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">معلومات</span></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
LhTag(type = LhTagType.success) { Text("نجاح") }
```

## النمط البسيط (plain)

`plain` يعني خلفية شفافة + حد ونص باللون الدلالي.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">رئيسي</span><span class="sim-tag sim-tag--plain sim-tag--success">نجاح</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">خطر</span></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## الحجم والعلامة

ثلاثة أحجام تُربط برموز `tag.height` / `tag.paddingHorizontal` / `tag.fontSize`؛ و`mark` يأخذ نصف قطر يساوي نصف الارتفاع.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Tag</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">علامة</span><span class="sim-tag sim-tag--success sim-tag--mark">علامة نجاح</span></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `text` | نص الوسم (كتابة مختصرة، واحد من `text` أو `content`) | `String?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |
| `type` | النوع الدلالي | `LhTagType` | `Default` |
| `lhSize` | الحجم | `LhTagSize` | `Medium` |
| `plain` | ما إذا كان النمط بسيطًا (خلفية شفافة + حد بلون دلالي) | `Boolean` | `false` |
| `round` | ما إذا كانت الزوايا كلها دائرية (نصف الارتفاع) | `Boolean` | `false` |
| `closable` | ما إذا كان سيُعرض زر الإغلاق | `Boolean` | `false` |
| `onClose` | رد الإغلاق؛ لا يُطلق عندما تكون `closable` تساوي false | `(() -> Unit)?` | `null` |
| `content` | محتوى الوسم (يتجاوز text، فتحة default في العقد) | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| القيمة | الخلفية (غير plain) | الحد/النص (plain) |
| --- | --- | --- |
| `Default` | `color.background.default` + حد `color.border.base` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| القيمة | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small` (20dp) | `tag.paddingHorizontal.small` (8dp) | `tag.fontSize.small` (12sp) |
| `Medium` | `tag.height.medium` (24dp) | `tag.paddingHorizontal.medium` (10dp) | `tag.fontSize.medium` (14sp) |
| `Large` | `tag.height.large` (28dp) | `tag.paddingHorizontal.large` (12dp) | `tag.fontSize.large` (16sp) |

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `tag.height.{small,medium,large}` | ثلاث ارتفاعات |
| `tag.paddingHorizontal.{small,medium,large}` | ثلاث حشوات أفقية |
| `tag.fontSize.{small,medium,large}` | ثلاث أحجام خط |
| `tag.borderRadius` / `tag.borderWidth` | نصف قطر الزاوية وعرض الحد |
| `color.{primary,success,warning,danger,info}.default` | خلفية / حد / نص دلالي |
| `color.onPrimary` | لون النص غير الخالص |
| `color.disabled.{bg,text,border}` | ألوان التعطيل الثلاثة |
| `spacing.xs` | المسافة بين زر الإغلاق والنص |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `default-type-renders-text` | النوع الافتراضي يأخذ خلفية محايدة + لون النص الرئيسي + الحد الأساسي | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | النوع الرئيسي يستهلك الرموز الرئيسية | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain يستهلك رموز الخلفية الدلالية الفاتحة | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | الحجم الصغير يستهلك رموز small (تأكيد عكسي بعدم تطبيق large) | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round يجعل نصف القطر يساوي نصف الارتفاع | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | النقر على الإغلاق يطلق onClose | `LhTagTest.closable_tap_emits_onClose` (Compose UI Test) |

```bash
./gradlew :components:tag:test                             # جزء JVM
./gradlew :components:tag:connectedAndroidTest             # جزء التفاعل (يتطلب جهازًا)
```
