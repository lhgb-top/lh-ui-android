# مفتاح Switch

عنصر تبديل ثنائي الحالة بمسار على شكل كبسولة ومقبض دائري، يدعم حالة التحميل كعنصر نائب.

> مصدر العقد: [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> الحالات الذهبية: [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch) (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الحالات

التشغيل يأخذ مسارًا باللون الرئيسي، والإيقاف يأخذ مسارًا بلون الحد؛ قطر المقبض = الارتفاع - 2 × `switch.gap`، وحركة الإزاحة تأخذ `motion.duration.base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>إيقاف</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>تشغيل</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>معطّل (إيقاف)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>معطّل (تشغيل)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>جارٍ التحميل (يُعرض حلقة تحميل في موضع المقبض، ولا يستجيب للنقر)</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## الحجم

ثلاثة أحجام تُربط برموز `switch.width` / `switch.height`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `checked` | ما إذا كان مُشغّلاً (إلزامي) | `Boolean` | — |
| `onChange` | رد تغيير حالة المفتاح، يحمل الحالة الجديدة | `(Boolean) -> Unit` | — |
| `disabled` | ما إذا كان معطّلاً | `Boolean` | `false` |
| `loading` | ما إذا كان قيد التحميل (يُعرض حلقة تحميل في موضع المقبض، ولا يستجيب للنقر) | `Boolean` | `false` |
| `lhSize` | الحجم | `LhSwitchSize` | `Medium` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

### LhSwitchSize

| القيمة | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small` (36dp) | `switch.height.small` (20dp) |
| `Medium` | `switch.width.medium` (44dp) | `switch.height.medium` (24dp) |
| `Large` | `switch.width.large` (52dp) | `switch.height.large` (28dp) |

المسار على شكل كبسولة (نصف قطر الزاوية يساوي نصف الارتفاع)؛ والنقر يصيب منطقة المسار، فيقلب حالة المفتاح ويطلق `onChange`.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `switch.width.{small,medium,large}` | ثلاثة عروض مسار |
| `switch.height.{small,medium,large}` | ثلاثة ارتفاعات مسار |
| `switch.gap` | الفراغ بين المقبض والمسار (قطر المقبض = الارتفاع - 2 × gap) |
| `color.primary.default` | لون مسار التشغيل |
| `color.border.base` | لون مسار الإيقاف |
| `color.onPrimary` | لون المقبض |
| `color.disabled.{bg,text}` | لونا مسار / مقبض التعطيل |
| `motion.duration.base` / `motion.easing.easeInOut` | حركة إزاحة المقبض |
| `loading.strokeWidth` | عرض حلقة التحميل (إعادة استخدام) |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | النقر يقلب الحالة ويطلق onChange | `LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-turns-off` | النقر في حالة التشغيل يوقفه | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | الحالة المعطّلة لا تُطلق | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | حالة التحميل لا تُطلق | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | التشغيل يستهلك الرمز الرئيسي | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | الإيقاف يستهلك رمز الحد | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # جزء JVM
./gradlew :components:switch:connectedAndroidTest            # جزء التفاعل (يتطلب جهازًا)
```
