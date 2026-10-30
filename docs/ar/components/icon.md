# أيقونة Icon

مكوّن قاعدة الأيقونات. أيقونات المكوّنات الأخرى (cell / input / navbar وغيرها) تُعرض جميعها عبر السجل المحلي لهذا المكوّن.

> مصدر العقد: [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> الحالات الذهبية: [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon) (5 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الحجم والدرجة اللونية

ثلاثة أحجام تُربط برموز `icon.size.*`؛ درجة `default` تأخذ `color.text.primary`، والباقي يأخذ الألوان الدلالية.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Icon</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">اللون الافتراضي (text.primary)</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## الدرجات اللونية الدلالية

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>درجات Icon اللونية</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## سجل الأيقونات

`LhIcons` هو سجل يربط أسماء الأيقونات المحلية بمتجهات Material `ImageVector`، واسم الأيقونة غير المسجّل يُرجع `null` (لا يُرسم ولا يُطرح استثناء):

| اسم الأيقونة | متجه Material |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `name` | اسم الأيقونة (يُحلّ عبر سجل `LhIcons`) | `String` | — |
| `lhSize` | حجم الأيقونة | `LhIconSize` | `Medium` |
| `tone` | الدرجة اللونية الدلالية | `LhIconTone` | `Default` |
| `contentDescription` | وصف إمكانية الوصول؛ يأخذ افتراضيًا اسم الأيقونة، وتمرير `null` صراحةً يُعلّمها أيقونة زخرفية | `String?` | `name` |
| `lhOnClick` | رد النقر؛ عندما يكون null لا يمكن النقر عليه | `(() -> Unit)?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

### LhIconSize

| القيمة | رمز الحجم |
| --- | --- |
| `Small` | `icon.size.small` (16dp) |
| `Medium` | `icon.size.medium` (20dp) |
| `Large` | `icon.size.large` (24dp) |

### LhIconTone

| القيمة | رمز اللون |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `icon.size.{small,medium,large}` | ثلاثة أحجام |
| `color.text.primary` | درجة default |
| `color.{primary,success,warning,danger,info}.default` | درجات لونية دلالية |
| `color.text.{regular,secondary}` | درجتا regular / secondary |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `click-emits-onclick` | النقر يطلق onClick | `LhIconTest.click_emits_onClick` (Compose UI Test) |
| `default-tone-uses-text-primary` | الدرجة الافتراضية تأخذ رمز لون النص الرئيسي | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | درجة primary تأخذ رمز اللون الرئيسي | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | الحجم الصغير يأخذ رمز الحجم small (تأكيد عكسي بعدم تطبيق large) | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | اسم الأيقونة المسجّل يُحلّ إلى متجه؛ وغير المسجّل لا يُرسم | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # جزء JVM
./gradlew :components:icon:connectedAndroidTest            # جزء التفاعل (يتطلب جهازًا)
```
