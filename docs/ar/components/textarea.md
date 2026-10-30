# منطقة نص Textarea

حقل إدخال متعدد الأسطر مُدار، يدعم عدد الأسطر وعدّ الأحرف والحد الأقصى للطول.

> مصدر العقد: [`lh-ui-core/specs/textarea.json`](https://www.lhgb.top/lhui/core/specs/textarea) ·
> الحالات الذهبية: [`cases/textarea.json`](https://www.lhgb.top/lhui/core/cases/textarea) (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الاستخدام الأساسي

الارتفاع الأدنى = عدد الأسطر × ارتفاع السطر × حجم الخط، وبعده يتم التمرير عموديًا.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" placeholder="أدخل محتوى متعدد الأسطر" rows="3"></textarea>
    </div>
    <div class="sim-row">
      <textarea class="sim-textarea" rows="3">السطر الأول
السطر الثاني
السطر الثالث</textarea>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var value by remember { mutableStateOf("") }
LhTextarea(
  value = value,
  rows = 3,
  showCount = true,
  maxlength = 200,
  onValueChange = { value = it },
)
```

## عدّ الأحرف

عندما تكون `showCount` تساوي true يُعرض عدّاد في الزاوية السفلية اليمنى (`textarea.countFontSize`، اللون `text.secondary`)؛ و`maxlength` يقتطع الإدخال.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>عدّاد Textarea</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <textarea class="sim-textarea" rows="2">ثلاث كلمات</textarea>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي؛ على الجهاز الحقيقي يُعرض العدّاد «3 / 20» في الزاوية السفلية اليمنى</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `value` | القيمة المُدارة (إلزامي) | `String` | — |
| `onValueChange` | رد تغيير القيمة (إلزامي) | `(String) -> Unit` | — |
| `placeholder` | نص العنصر النائب | `String?` | `null` |
| `rows` | عدد الأسطر (يحدد الارتفاع الأدنى) | `Int` | `3` |
| `disabled` | ما إذا كان معطّلاً | `Boolean` | `false` |
| `readonly` | ما إذا كان للقراءة فقط | `Boolean` | `false` |
| `showCount` | ما إذا كان سيُعرض عدّاد الأحرف | `Boolean` | `false` |
| `maxlength` | الحد الأقصى للطول (الزيادة تُقتطع) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | رد التركيز / فقدان التركيز | `(() -> Unit)?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `textarea.paddingVertical` / `textarea.paddingHorizontal` | الحشوة (`spacing.md`) |
| `textarea.fontSize` | حجم الخط (`typography.fontSize.base`) |
| `textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth` | نصف قطر الزاوية / عرض الحد / عرض حد التركيز |
| `textarea.countFontSize` | حجم خط العدّاد (`typography.fontSize.extraSmall`) |
| `textarea.minHeight` | المرجع الأساسي للارتفاع الأدنى |
| `typography.lineHeight.primary` | ارتفاع السطر (تحويل عدد الأسطر) |
| `color.text.{primary,secondary,placeholder}` | ألوان النص / العدّاد / العنصر النائب |
| `color.focus` / `color.border.base` | لون حد التركيز / الحد الافتراضي |
| `color.disabled.{bg,text,border}` | ألوان التعطيل الثلاثة |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `input-emits-onvaluechange` | الإدخال يطلق onValueChange | `LhTextareaTest.input_emits_onValueChange` (Compose UI Test) |
| `disabled-blocks-input` | الحالة المعطّلة لا تقبل الإدخال | `LhTextareaTest.disabled_blocks_input` |
| `showcount-renders-count` | العدّاد يعرض «n / max» | `LhTextareaTest.showcount_renders_count` |
| `showcount-without-maxlength-renders-length-only` | بدون maxlength يُعرض الطول فقط | `LhTextareaTest.showcount_without_maxlength_renders_length_only` |
| `focus-uses-focus-token` | التركيز يستهلك رمز التركيز | `LhTextareaDefaultsTest.focus_uses_focus_tokens` |
| `maxlength-truncates-input` | الإدخال الزائد يُقتطع | `LhTextareaTest.maxlength_truncates_input` |

```bash
./gradlew :components:textarea:test                            # جزء JVM
./gradlew :components:textarea:connectedAndroidTest           # جزء التفاعل (يتطلب جهازًا)
```
