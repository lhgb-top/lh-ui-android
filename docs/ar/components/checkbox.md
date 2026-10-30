# مربع اختيار Checkbox

مربّع اختيار مُدار، يدعم الحالة الجزئية (indeterminate) والتعطيل.

> مصدر العقد: [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> الحالات الذهبية: [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox) (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الحالات

التحديد / التحديد الجزئي يعني خلفية رئيسية مصمتة + مؤشر onPrimary (علامة صح / شريط أفقي)، وعدم التحديد يعني حدًا فقط.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>غير محدد</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>محدد</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>تحديد جزئي</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>معطّل (محدد)</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>معطّل (غير محدد)</label></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "أوافق على الاتفاقية",
)
```

## الحجم

ثلاثة أحجام للمربّع تُربط برموز `checkbox.size.*`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `checked` | ما إذا كان محددًا (إلزامي) | `Boolean` | — |
| `onCheckedChange` | رد تغيير حالة التحديد، يحمل حالة التحديد الجديدة | `(Boolean) -> Unit` | — |
| `indeterminate` | ما إذا كان التحديد جزئيًا (حالة غير محددة) | `Boolean` | `false` |
| `disabled` | ما إذا كان معطّلاً | `Boolean` | `false` |
| `label` | نص العلامة | `String?` | `null` |
| `lhSize` | حجم المربّع | `LhCheckboxSize` | `Medium` |
| `labelContent` | محتوى العلامة (يتجاوز label، فتحة default في العقد) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

### LhCheckboxSize

| القيمة | رمز الحجم |
| --- | --- |
| `Small` | `checkbox.size.small` (16dp) |
| `Medium` | `checkbox.size.medium` (20dp) |
| `Large` | `checkbox.size.large` (24dp) |

النقر يصيب الصف بأكمله (المربّع + العلامة): النقر في الحالة الجزئية يحوّلها إلى محددة، وإلا يقلب حالة التحديد.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `checkbox.size.{small,medium,large}` | ثلاثة أحجام مربّع |
| `checkbox.borderRadius` / `checkbox.borderWidth` | نصف قطر زاوية المربّع وعرض الحد |
| `checkbox.checkWidth` | عرض حد علامة الصح / الشريط الأفقي |
| `checkbox.gap` | المسافة بين المربّع والعلامة |
| `color.primary.default` / `color.onPrimary` | لون خلفية التحديد / لون المؤشر |
| `color.border.base` | لون حد عدم التحديد |
| `color.disabled.{bg,border,text}` | ألوان التعطيل الثلاثة |
| `color.text.primary` | لون العلامة |
| `typography.fontSize.base` | حجم خط العلامة |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | النقر يقلب الحالة ويطلق onChange | `LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-unchecks` | النقر في الحالة المحددة يلغي التحديد | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | النقر في الحالة الجزئية يحوّلها إلى محددة | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | الحالة المعطّلة لا تُطلق | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | التحديد يستهلك الرموز الرئيسية | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | عدم التحديد يستهلك رمز الحد | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # جزء JVM
./gradlew :components:checkbox:connectedAndroidTest           # جزء التفاعل (يتطلب جهازًا)
```
