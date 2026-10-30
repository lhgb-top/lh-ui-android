# زر اختيار Radio

زر اختيار مُدار، النقر عليه مرة أخرى بعد التحديد لا يلغي التحديد (دلالة الاختيار الواحد).

> مصدر العقد: [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> الحالات الذهبية: [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio) (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الحالات ومجموعة الاختيار

لا يحتوي المكوّن نفسه على منطق المجموعة، فالتزام خيارات المجموعة (التحديد الواحد فقط) يُحافظ عليه المضيف (يُستخدم `value` لتعريف العنصر داخل المجموعة).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>غير محدد</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>محدد</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>معطّل (غير محدد)</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>معطّل (محدد)</label></div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("الخيار الأول" to "a", "الخيار الثاني" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## الحجم

ثلاثة أحجام للحلقة تُربط برموز `radio.size.*`؛ قطر النقطة الداخلية = الحجم × `radio.dotScale`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `checked` | ما إذا كان محددًا (إلزامي) | `Boolean` | — |
| `onChange` | يُطلق عند الانتقال من عدم التحديد إلى التحديد، ويحمل `checked = true` | `(Boolean) -> Unit` | — |
| `disabled` | ما إذا كان معطّلاً | `Boolean` | `false` |
| `label` | نص العلامة | `String?` | `null` |
| `value` | قيمة الاختيار (معرّف داخل المجموعة، لا يحتوي المكوّن نفسه على منطق مجموعة) | `String?` | `null` |
| `lhSize` | حجم الحلقة | `LhRadioSize` | `Medium` |
| `labelContent` | محتوى العلامة (يتجاوز label، فتحة default في العقد) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

### LhRadioSize

| القيمة | رمز الحجم | قطر النقطة الداخلية |
| --- | --- | --- |
| `Small` | `radio.size.small` (16dp) | 16dp × `radio.dotScale` (0.5) |
| `Medium` | `radio.size.medium` (20dp) | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large` (24dp) | 24dp × `radio.dotScale` |

النقر يصيب الصف بأكمله: يتحول إلى محدد ويطلق `onChange(true)` فقط عندما يكون غير محدد وغير معطّل.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `radio.size.{small,medium,large}` | ثلاثة أحجام حلقة |
| `radio.ringWidth` | عرض حد الحلقة الخارجية |
| `radio.dotScale` | نسبة النقطة الداخلية إلى قطر الحلقة الخارجية (كسر) |
| `radio.gap` | المسافة بين الحلقة والعلامة |
| `color.primary.default` | لون حد التحديد والنقطة |
| `color.border.base` | لون حد عدم التحديد |
| `color.disabled.{border,text}` | ألوان حد التعطيل / النقطة / النص |
| `color.text.primary` | لون العلامة |
| `typography.fontSize.base` | حجم خط العلامة |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | النقر على غير محدد يطلق onChange(true) | `LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test) |
| `tap-checked-keeps-checked` | النقر على محدد لا يُطلق | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | الحالة المعطّلة لا تُطلق | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | التحديد يستهلك الرموز الرئيسية | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | عدم التحديد يستهلك رمز الحد | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | الحجم الصغير يستهلك رمز الحجم small | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # جزء JVM
./gradlew :components:radio:connectedAndroidTest             # جزء التفاعل (يتطلب جهازًا)
```
