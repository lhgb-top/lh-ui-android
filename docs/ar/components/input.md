# حقل إدخال Input

حقل إدخال أحادي السطر مُدار، يدعم الحجم والتعطيل والقراءة فقط والمسح وقناع كلمة المرور والحد الأقصى للطول.

> مصدر العقد: [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> الحالات الذهبية: [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input) (8 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الاستخدام الأساسي

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="أدخل المحتوى" /></div>
    <div class="sim-row"><input class="sim-input" value="محتوى مُدخل" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="حالة تعطيل" disabled /></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "أدخل المحتوى",
  onValueChange = { value = it },
)
```

## الحجم والتركيز

ثلاثة أحجام تُربط برموز `input.height` / `input.paddingHorizontal` / `input.fontSize`؛ عند التركيز يأخذ الحد لون `color.focus` ويتّسع إلى `input.focusRingWidth`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium (عرض حالة التركيز)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `value` | القيمة المُدارة (إلزامي) | `String` | — |
| `onValueChange` | رد تغيير القيمة (إلزامي) | `(String) -> Unit` | — |
| `placeholder` | نص العنصر النائب | `String?` | `null` |
| `type` | نوع الإدخال (نص ظاهر / كلمة مرور) | `LhInputType` | `Text` |
| `lhSize` | الحجم | `LhInputSize` | `Medium` |
| `disabled` | ما إذا كان معطّلاً | `Boolean` | `false` |
| `readonly` | ما إذا كان للقراءة فقط (غير قابل للتعديل لكن دون نمط التعطيل) | `Boolean` | `false` |
| `clearable` | ما إذا كان سيُعرض زر المسح | `Boolean` | `false` |
| `maxlength` | الحد الأقصى للطول (الزيادة تُقتطع) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | رد التركيز / فقدان التركيز | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | فتحتان أمامية / خلفية | `@Composable (() -> Unit)?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

### LhInputSize

| القيمة | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small` (32dp) | `input.paddingHorizontal.small` (12dp) | `input.fontSize.small` (13sp) |
| `Medium` | `input.height.medium` (40dp) | `input.paddingHorizontal.medium` (16dp) | `input.fontSize.medium` (14sp) |
| `Large` | `input.height.large` (48dp) | `input.paddingHorizontal.large` (20dp) | `input.fontSize.large` (16sp) |

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `input.height.{small,medium,large}` | ثلاث ارتفاعات |
| `input.paddingHorizontal.{small,medium,large}` | ثلاث حشوات أفقية |
| `input.fontSize.{small,medium,large}` | ثلاث أحجام خط |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | نصف قطر الزاوية / عرض الحد / عرض حد التركيز |
| `input.clearIconSize` | حجم أيقونة المسح |
| `color.focus` | لون حد التركيز |
| `color.text.placeholder` | لون نص العنصر النائب |
| `color.border.base` | لون الحد الافتراضي |
| `color.disabled.{bg,text,border}` | ألوان التعطيل الثلاثة |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `input-emits-onvaluechange` | الإدخال يطلق onValueChange | `LhInputTest.input_emits_onValueChange` (Compose UI Test) |
| `clearable-emits-empty-value` | زر المسح يطلق قيمة فارغة | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | الحالة المعطّلة لا تقبل الإدخال | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | حالة القراءة فقط لا تقبل الإدخال | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | لون العنصر النائب يأخذ رمز العنصر النائب | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | التركيز يستهلك رمز التركيز (فقدان التركيز يأخذ حدًا محايدًا) | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | الحجم الصغير يستهلك رموز الحجم small | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | الإدخال الزائد يُقتطع | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # جزء JVM
./gradlew :components:input:connectedAndroidTest              # جزء التفاعل (يتطلب جهازًا)
```
