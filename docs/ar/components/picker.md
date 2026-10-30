# منتقي Picker

منتقي عجلة سفلي ذو عمود واحد، العنصر الأقرب إلى المؤشر المركزي بعد توقف التمرير هو العنصر المحدد.

> مصدر العقد: [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> الحالات الذهبية: [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker) (8 حالات، تُرجمت عمليات clamp وتعيينات الرموز إلى اختبارات JVM، وتُرجم تفاعلات التمرير والأزرار إلى Compose UI Test)

## الاستخدام

يظهر من الأسفل: منطقة الستارة في الأعلى واللوحة في الأسفل (بزوايا دائرية علوية). تتكون شريط الأدوات من ثلاثة أجزاء: «إلغاء / عنوان / تأكيد».

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">إلغاء</button>
        <div class="sim-picker__toolbar-title">اختر</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">تأكيد</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">الخيار الأول</div>
        <div class="sim-picker__item sim-picker__item--selected">الخيار الثاني</div>
        <div class="sim-picker__item">الخيار الثالث</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("الخيار الأول", "الخيار الثاني", "الخيار الثالث")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "اختر",
)
```

## آلية التحديد

العجلة قائمة قابلة للتمرير، ارتفاع كل عنصر `picker.itemHeight`؛ والمؤشر المركزي عبارة عن خطين أفقيين المسافة بينهما = itemHeight.
بعد توقف التمرير يُحسب فهرس التحديد بتقريب مسافة التمرير، وعند التغير يُطلق `onValueChange`.
عند خروج `value` عن النطاق يتم clamp إلى نطاق المرشحين بواسطة `LhPickerDefaults.clamp`.

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `visible` | ما إذا كان سيُعرض؛ عندما يكون false لا يُعرض أي محتوى | `Boolean` | — |
| `options` | مصفوفة نصوص المرشحين (عمود واحد) | `List<String>` | — |
| `onValueChange` | يُطلق عند توقف العجلة وتغير العنصر المحدد، ويحمل الفهرس الجديد | `(Int) -> Unit` | — |
| `onConfirm` | يُطلق عند النقر على زر التأكيد، ويحمل فهرس العنصر المحدد الحالي | `(Int) -> Unit` | — |
| `onCancel` | يُطلق عند النقر على زر الإلغاء | `() -> Unit` | — |
| `onClose` | يُطلق بعد التأكيد/الإلغاء، وبناءً عليه يضبط المضيف visible على false | `() -> Unit` | — |
| `value` | فهرس العنصر المحدد (ابتداء من 0)؛ عند الخروج عن النطاق يتم clamp | `Int` | `0` |
| `title` | العنوان في وسط شريط الأدوات | `String?` | `null` |
| `confirmText` | نص زر التأكيد | `String` | `"确认"` |
| `cancelText` | نص زر الإلغاء | `String` | `"取消"` |
| `content` | منطقة عجلة المحتوى (تتجاوز عرض المرشحين الافتراضي، فتحة default في العقد) | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | شريط الأدوات (يتجاوز التأكيد/العنوان/الإلغاء الافتراضيين، فتحة toolbar في العقد) | `@Composable (() -> Unit)?` | `null` |
| `listState` | حالة قائمة العجلة (تُوفَّر افتراضيًا بواسطة `rememberLhPickerListState(value, options)`؛ ويمكن حقنها خارجيًا في الاختبارات) | `LazyListState` | انظر لليسار |
| `modifier` | المُعدِّل (يُطبّق على اللوحة) | `Modifier` | `Modifier` |

خطاف الاختبار: يُرجع `rememberLhPickerListState(value, options)` كائن `LazyListState` تموضعه تمهيديًا وفق `value`،
ويمكن للاختبارات الآلية استخدام `listState.dispatchRawDelta(...)` لقيادة التمرير بشكل حتمي.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `picker.height` | ارتفاع اللوحة (260dp) |
| `picker.itemHeight` | ارتفاع كل عنصر (40dp، وهو أيضًا المسافة بين خطي المؤشر المركزي) |
| `picker.toolbarHeight` | ارتفاع شريط الأدوات (44dp) |
| `picker.itemFontSize` | حجم خط العناصر المرشحة |
| `picker.borderRadius` | نصف قطر زاوية اللوحة العلوية |
| `picker.paddingHorizontal` | الحشوة الأفقية للوحة (تتبع `spacing.md`) |
| `zIndex.modal` / `zIndex.modalBackdrop` | مطابقة دلالات الطبقات (يحمل `Popup` الخاص بـ Compose طبقة على مستوى النافذة افتراضيًا) |
| `color.background.overlay` | لون الستارة |
| `color.background.default` | لون خلفية اللوحة |
| `color.text.primary` / `color.text.regular` | لون العنوان / العناصر المرشحة |
| `color.border.lighter` | لون خطي المؤشر المركزي |
| `color.primary.default` | لون زر التأكيد |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | التأكيد يحمل الفهرس الحالي + onClose | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | بعد التأكيد يُطلق onClose | نفس ما سبق |
| `cancel-emits-oncancel` | الإلغاء يطلق onCancel + onClose | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | التمرير يغير التحديد ويطلق onValueChange | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | عندما تكون value سالبة يتم clamp إلى 0 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | عند خروج value عن النطاق يتم clamp إلى العنصر الأخير | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | العنوان يعرض نصًا | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | عند عدم الظهور لا يُعرض شيء | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # جزء JVM
./gradlew :components:picker:connectedAndroidTest               # جزء التفاعل (يتطلب جهازًا)
```
