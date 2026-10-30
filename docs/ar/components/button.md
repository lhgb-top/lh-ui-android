# زر Button

زر إجراء أساسي. يُطلق إجراءً فوريًا، ويدعم النوع والحجم والتحميل والتعطيل والأيقونة وخلفية النمط الصيني.

> مصدر العقد: [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> الحالات الذهبية: [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button) (8 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الأنواع

ستة أنواع تحدد لون الخلفية ولون النص.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أنواع Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">زر افتراضي</button>
      <button class="sim-btn sim-btn--primary">زر رئيسي</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">زر نجاح</button>
      <button class="sim-btn sim-btn--warning">زر تحذير</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">زر خطر</button>
      <button class="sim-btn sim-btn--info">زر معلومات</button>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("زر رئيسي") }
```

## الأحجام

ثلاثة أحجام تُربط برموز `button.height` / `paddingHorizontal` / `fontSize` / `iconSize`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أحجام Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">زر صغير</button>
      <button class="sim-btn">زر متوسط</button>
      <button class="sim-btn sim-btn--large">زر كبير</button>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## الحالات

لا يُطلق `lhOnClick` لا في حالة التعطيل ولا أثناء التحميل؛ وعند اجتماعهما تكون **الأولوية للتعطيل** (الحالة الذهبية `disabled-precedes-loading`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>حالات Button</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">عادي</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>معطّل</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>جارٍ التحميل
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>التعطيل أولاً</button>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## الأيقونات وخلفية النمط

تُحلّ أسماء الأيقونات عبر نظام الأيقونات المحلي (`LhButtonIcons`، يدعم حاليًا search / refresh / check / close، والأسماء غير المسجلة لا تُرسم).
تستهلك خلفية النمط رمز `color.decoration`، وتُطبّق فوق لون الخلفية وتحت النص، فلا تؤثر على التباين.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>الأيقونات والأنماط</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>بحث</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>افتراضي</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">نمط رئيسي</button>
      <button class="sim-btn sim-btn--pattern">نمط افتراضي</button>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## السمتان الفاتحة والداكنة

بدّل سمة الموقع من الزاوية العلوية تتغير ألوان الأزرار داخل إطار هاتف المحاكي؛ وعلى الجهاز الحقيقي يتم التبديل عبر `LhTheme(isDark = true)`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>أزرار داكنة</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">زر رئيسي</button>
      <button class="sim-btn">زر افتراضي</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>معطّل</button>
      <button class="sim-btn sim-btn--success">نجاح</button>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي؛ جميع قيم الفاتح/الداكن مأخوذة من لقطة رموز core</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `lhOnClick` | رد النقر؛ لا يُطلق عندما تكون `disabled` أو `loading` تساوي true | `() -> Unit` | — |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |
| `type` | نوع الزر، يحدد لون الخلفية ولون النص | `LhButtonType` | `Default` |
| `lhSize` | حجم الزر، يُربط برموز الأحجام | `LhButtonSize` | `Medium` |
| `disabled` | ما إذا كان الزر معطّلاً | `Boolean` | `false` |
| `loading` | ما إذا كان الزر قيد التحميل (يعرض مؤشرًا ولا يطلق lhOnClick) | `Boolean` | `false` |
| `icon` | اسم الأيقونة (يُحلّ عبر نظام الأيقونات المحلي) | `String?` | `null` |
| `bgPattern` | ما إذا كان سيتم رسم نمط الخلفية الصيني | `Boolean` | `false` |
| `content` | محتوى نص الزر (فتحة default في العقد) | `@Composable () -> Unit` | — |

### LhButtonType

| القيمة | لون الخلفية | لون النص |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| القيمة | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small` (32dp) | `button.paddingHorizontal.small` (16dp) | `button.fontSize.small` (13sp) | `button.iconSize.small` (16dp) |
| `Medium` | `button.height.medium` (40dp) | `button.paddingHorizontal.medium` (20dp) | `button.fontSize.medium` (14sp) | `button.iconSize.medium` (18dp) |
| `Large` | `button.height.large` (48dp) | `button.paddingHorizontal.large` (24dp) | `button.fontSize.large` (16sp) | `button.iconSize.large` (20dp) |

تجاوزات الحالة المعطّلة: الخلفية `color.disabled.bg`، النص `color.disabled.text`، الحد `color.disabled.border`.

## مرجع الرموز

الرموز التي يستهلكها الزر (مطابقة واحدًا لواحد مع `tokenRefs` في `specs/button.json`):

| الرمز | الاستخدام |
| --- | --- |
| `button.height.{small,medium,large}` | ثلاث ارتفاعات |
| `button.paddingHorizontal.{small,medium,large}` | ثلاث حشوات أفقية |
| `button.fontSize.{small,medium,large}` | ثلاث أحجام خط |
| `button.iconSize.{small,medium,large}` | ثلاثة أحجام أيقونات |
| `button.borderRadius` / `button.borderWidth` | نصف قطر الزاوية (يتبع `radius.base`) وعرض الحد |
| `button.fontWeight` | وزن الخط (يتبع `typography.fontWeight.primary`) |
| `color.primary.default` / `color.onPrimary` | خلفية/نص النوع الرئيسي |
| `color.{success,warning,danger,info}.default` | خلفيات الأنواع الدلالية |
| `color.background.default` / `color.text.primary` / `color.border.base` | خلفية/نص/حد النوع الافتراضي |
| `color.disabled.{bg,text,border}` | ألوان التعطيل الثلاثة |
| `color.decoration` | لون النمط |
| `shadow.lighter` | ارتفاع (elevation) النوع الافتراضي |
| `motion.duration.fast` / `motion.easing.easeInOut` | حركة استجابة الضغط |

## مرجع الحالات الذهبية

مطابقة تأكيدات `cases/button.json` الثمانية للاختبارات المحلية:

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `click-emits-onclick` | النقر يطلق onClick مرة واحدة | `LhButtonTest.click_emits_onClick` (Compose UI Test) |
| `disabled-blocks-click` | الحالة المعطّلة لا تُطلق | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | حالة التحميل لا تُطلق | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | الأولوية للتعطيل | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | النوع الافتراضي يعرض نصًا | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | النوع الرئيسي يستهلك الرموز الرئيسية | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | الحجم الصغير يستهلك رموز الحجم small | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | خلفية النمط تستهلك لون الزخرفة | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | اسم الأيقونة المسجّل يعرض متجهًا؛ وغير المسجّل لا يُرسم | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # جزء JVM
./gradlew :components:button:connectedAndroidTest        # جزء التفاعل (يتطلب جهازًا)
```
