# Toast (تنبيه)

طبقة عائمة على شكل قرص حبري اللون تُدير ظهورها وتوقيتها بنفسها، لاستجابة خفيفة الوزن للعمليات. ولا تحجب عمليات المستخدم.

> مصدر العقد: [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> الحالات الذهبية: [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast) (6 حالات، 4 حالات آلة حالة + حالتا عرض، تُرجمت جميعها إلى اختبارات محلية)

## الاستخدام

التنبيه (toast) هو **آلة حالة ذاتية**: تُحمل الحالة بواسطة `LhToastState`، يُظهر المضيف عبر `show(text)` ويُخفي عبر `hide()`، وعند انتهاء المدة يُخفى تلقائيًا ويُطلق `onClose`. وعند الظهور يقوم المكوّن بقيادة `tick` ذاتيًا بإطار، ويمكن للمضيف استدعاءه بنفسه للسيطرة على التوقيت.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">تمت العملية بنجاح</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
val state = rememberLhToastState(onClose = { /* اختياري: تسجيل الإغلاق */ })
Column {
  LhButton(lhOnClick = { state.show("تم الحفظ") }) { Text("تشغيل") }
  LhToast(state = state)
}
```

## الأنواع الدلالية

عند نوع غير `default` يُرسم قبل النص نقطة دائرية بلون دلالي قطرها يساوي حجم الخط.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أنواع Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>تنبيه نجاح</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>تنبيه تحذير</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>تنبيه فشل</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>تنبيه رئيسي</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## التموضع

يأخذ `lhPosition` القيم `top` / `center` / `bottom`، مع هامش `spacing.xl` عند كل طرف؛ ويُشتق العرض من قياس النص ويُحصر عند 80% من العرض المتاح.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>تموضع Toast</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">أعلى</div>
    <div class="sim-toast sim-toast--bottom">أسفل</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `state` | آلة حالة التنبيه (تحمل الظهور والنص والتوقيت) | `LhToastState` | — |
| `type` | النوع الدلالي؛ عندما يكون غير default يُرسم نقطة دلالية اللون | `LhToastType` | `default` |
| `lhPosition` | التموضع داخل المنطقة المتاحة | `LhToastPosition` | `center` |
| `modifier` | المُعدِّل (يُطبّق على القرص) | `Modifier` | `Modifier` |

### LhToastState

| العضو | الشرح |
| --- | --- |
| `show(text: String)` | يُظهر ويعيد ضبط التوقيت |
| `hide()` | يُخفي فورًا، **دون إطلاق onClose** |
| `tick(deltaMs: Int): Boolean` | يُقدم التوقيت؛ عند الانتهاء يُخفي ويُطلق onClose ويرجع true |
| `visible` / `text` / `duration` | الحالة الحالية (قابلة للملاحظة) |

دالة المصنع: `rememberLhToastState(duration = 2000, onClose = {})`.

### LhToastType / LhToastPosition

| القيمة | لون النقطة |
| --- | --- |
| `default` | بلا نقطة |
| `primary` / `success` / `warning` / `danger` / `info` | اللون الدلالي المقابل |

`LhToastPosition`: `top` / `center` / `bottom`.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `toast.maxWidth` | الحد الأقصى لعرض القرص (240dp؛ فعليًا يُؤخذ الأصغر من 80% من العرض المتاح ومنه) |
| `toast.borderRadius` | نصف قطر زاوية القرص (يتبع `radius.base`) |
| `toast.paddingHorizontal` / `toast.paddingVertical` | حشوة القرص |
| `toast.fontSize` | حجم خط النص (قطر النقطة يساوي نفس القيمة) |
| `toast.gap` | المسافة بين النقطة والنص |
| `zIndex.toast` | مطابقة دلالات الطبقات (يحمل `Popup` الخاص بـ Compose طبقة على مستوى النافذة افتراضيًا) |
| `color.background.overlay` (تعيين ink في طبقة السمة) | لون خلفية القرص |
| `color.onPrimary` | لون النص |
| `color.{primary,success,warning,danger,info}.default` | ألوان النقاط الدلالية |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `show-makes-visible` | بعد show يصبح ظاهرًا ويعرض النص | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | عند انتهاء المدة يُخفى تلقائيًا ويُطلق onClose | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | قبل انتهاء المدة يبقى ظاهرًا ولا يُطلق onClose | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide لا يُطلق onClose | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | النوع success يستهلك لون النجاح | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | عند عدم الظهور لا يُعرض شيء | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # جزء JVM
./gradlew :components:toast:connectedAndroidTest                 # جزء التفاعل (يتطلب جهازًا)
```
