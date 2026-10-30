# تحميل Loading

مؤشر تحميل بحلقة دوّارة، مدة دورته مأخوذة من `motion.duration.base` (بالميلي ثانية).

> مصدر العقد: [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> الحالات الذهبية: [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading) (5 حالات، مكوّن عرض خالص، تُرجمت إلى اختبارات JVM)

## الحجم والدرجة اللونية

ثلاثة أحجام تُربط برموز `loading.size.*`، وعرض الحلقة مأخوذ من `loading.strokeWidth`، والدرجة اللونية من الألوان الدلالية.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">جارٍ التحميل…</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `lhSize` | قطر المؤشر | `LhLoadingSize` | `Medium` |
| `tone` | لون الحلقة | `LhLoadingTone` | `Primary` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

### LhLoadingSize

| القيمة | رمز الحجم |
| --- | --- |
| `Small` | `loading.size.small` (16dp) |
| `Medium` | `loading.size.medium` (20dp) |
| `Large` | `loading.size.large` (24dp) |

### LhLoadingTone

| القيمة | رمز اللون |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `loading.size.{small,medium,large}` | ثلاثة أقطار |
| `loading.strokeWidth` | عرض حد الحلقة |
| `color.text.regular` | درجة default |
| `color.{primary,success,warning,danger,info}.default` | درجات لونية دلالية |
| `motion.duration.base` | مدة دورة كاملة (عدد صحيح بالميلي ثانية على جانب الجهاز) |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `medium-size-uses-medium-token` | الحجم المتوسط يأخذ رمز الحجم medium | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | الحجم الكبير يأخذ رمز الحجم large | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | درجة primary تأخذ رمز اللون الرئيسي | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | درجة success تأخذ رمز لون النجاح | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | عرض مؤشر الحلقة (عرض الحد يستهلك الرمز) | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # جزء JVM (عرض خالص، بلا حالات تفاعل)
```
