# خلية Cell

خلة قائمة، بتخطيط ثلاثي الأجزاء: أيقونة / عنوان + وصف / قيمة يمينية + سهم، وتُستخدم غالبًا مع القوائم.

> مصدر العقد: [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> الحالات الذهبية: [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell) (6 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الاستخدام الأساسي

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">نص العنوان</div>
      <div class="sim-cell__value">قيمة يمينية</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">عنوان مع وصف</div>
        <div class="sim-cell__label">نص الوصف</div>
      </div>
      <div class="sim-cell__value">قيمة</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>حقل إلزامي</div>
      <div class="sim-cell__value">تفاصيل</div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
LhCell(
  title = "عنوان مع وصف",
  label = "نص الوصف",
  value = "قيمة",
  isLink = true,
  lhOnClick = { /* الانتقال */ },
)
```

## الإلزام والربط

يرسم `required` نجمة حمراء قبل العنوان (`color.danger.default`)؛ ويرسم `isLink` أيقونة سهم يميني في النهاية.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>متغيرات Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>إلزامي</div>
      <div class="sim-cell__value">سهم ←</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">بلا حدود</div>
      <div class="sim-cell__value">قيمة</div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `title` | العنوان (النص الرئيسي) | `String?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |
| `value` | القيمة اليمينية (نص ثانوي، محاذاة لليمين) | `String?` | `null` |
| `label` | نص الشرح أسفل العنوان | `String?` | `null` |
| `bordered` | ما إذا كان سيُرسم خط فاصل سفلي (`color.border.lighter`) | `Boolean` | `true` |
| `clickable` | ما إذا كان قابلاً للنقر؛ عندما يكون true يعرض سهمًا مؤشرًا يمينيًا ويستجيب للنقر | `Boolean` | `false` |
| `required` | ما إذا كان سيُعرض نجمة إلزامية حمراء قبل العنوان (`color.danger.default`) | `Boolean` | `false` |
| `icon` | اسم الأيقونة اليسرى (نظام الأيقونات المحلي)؛ عند الفراغ لا تُرسم | `String?` | `null` |
| `lhOnClick` | رد النقر؛ لا يُطلق عندما تكون `clickable` تساوي false | `(() -> Unit)?` | `null` |
| `titleContent` | محتوى العنوان (يتجاوز title، فتحة default في العقد) | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | محتوى القيمة اليمينية (يتجاوز value، فتحة value في العقد) | `@Composable (() -> Unit)?` | `null` |

يتحكم `clickable` في الوقت نفسه في «ما إذا كان سيُرسم سهم المؤشر يمينيًا» و«ما إذا كانت الصف بأكملها تستجيب للنقر» — لا يُطلق النقر إلا عندما يكون `clickable = true` و`lhOnClick` غير فارغ.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `cell.paddingVertical` | الحشوة الرأسية (مرجع `spacing.md`) |
| `cell.paddingHorizontal` | الحشوة الأفقية (`spacing.md`) |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | أحجام خط العنوان / الوصف / القيمة |
| `cell.gap` | المسافة بين الأيقونة والعنوان (`spacing.xs`) |
| `icon.size.medium` | حجم الأيقونة اليسرى وسهم المؤشر |
| `cell.borderWidth` | عرض خط الفاصل السفلي |
| `typography.lineHeight.primary` | ارتفاع سطر النص |
| `color.text.{primary,secondary}` | ألوان العنوان / الوصف / القيمة |
| `color.danger.default` | نجمة الإلزام |
| `color.border.lighter` | خط الفاصل السفلي |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `title-and-value-render` | العنوان والقيمة يعرضان نصًا | `LhCellTest.title_and_value_render` (Compose UI Test) |
| `label-renders-secondary-tone` | label يأخذ رمز لون النص الثانوي | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | علامة الإلزام تأخذ رمز لون الخطر | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | خط الفاصل يستهلك رمز الحد | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | النقر يطلق onClick | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | الحالة غير القابلة للنقر لا تُطلق | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # جزء JVM
./gradlew :components:cell:connectedAndroidTest              # جزء التفاعل (يتطلب جهازًا)
```
