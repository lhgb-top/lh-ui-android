# شريط تنقل Navbar

شريط العنوان أعلى الصفحة، بتخطيط ثلاثي الأجزاء يسار / وسط / يمين، يُستخدم غالبًا للرجوع وعرض العنوان.

> مصدر العقد: [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> الحالات الذهبية: [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar) (6 حالات، تُرجمت تعيينات الرموز إلى اختبارات JVM، وتُرجمتا نقرتا اليسار واليمين إلى Compose UI Test)

## الاستخدام

ثلاثة أجزاء متساوية العرض (كل منها `weight(1f)`)، والعنوان في المنتصف أفقيًا ضمن العرض المتاح؛ ويُرسم افتراضيًا خط فاصل سفلي.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>رجوع</div>
      <div class="sim-navbar__title">عنوان الصفحة</div>
      <div class="sim-navbar__side sim-navbar__side--right">المزيد</div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
LhNavbar(
  title = "عنوان الصفحة",
  leftIcon = "arrow-left",
  leftText = "رجوع",
  rightText = "المزيد",
  onLeftClick = { /* الرجوع */ },
  onRightClick = { /* المزيد */ },
)
```

## المحتوى الجانبي

يمكن أن يتواجد النص والأيقونة معًا (الأيقونة على اليسار والنص على اليمين، والمسافة `spacing.xs`)؛ **وعند عدم وجود أي محتوى على أحد الجانبين لا يستجيب للنقر**
(الحالة الذهبية `no-left-content-no-onleftclick`: تلك العقدة لا تمتلك حتى دلالة القابلية للنقر).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>متغيرات Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">أيقونة فقط</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">نص</div>
      <div class="sim-navbar__title">بلا خط فاصل</div>
      <div class="sim-navbar__side sim-navbar__side--right">تحرير</div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `title` | العنوان في المنتصف | `String?` | `null` |
| `onLeftClick` | يُطلق عند النقر على المنطقة اليسرى | `() -> Unit` | `{}` |
| `onRightClick` | يُطلق عند النقر على المنطقة اليمنى | `() -> Unit` | `{}` |
| `leftText` | نص الزر الأيسر؛ يمكن أن يتواجد مع leftIcon | `String?` | `null` |
| `leftIcon` | اسم الأيقونة اليسرى (نظام الأيقونات المحلي، مثل `"arrow-left"`) | `String?` | `null` |
| `rightText` | نص الزر الأيمن؛ يمكن أن يتواجد مع rightIcon | `String?` | `null` |
| `rightIcon` | اسم الأيقونة اليمنى | `String?` | `null` |
| `bordered` | ما إذا كان سيُرسم خط فاصل سفلي | `Boolean` | `true` |
| `titleContent` | محتوى العنوان (يتجاوز title، فتحة default في العقد) | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | المحتوى الأيسر (يتجاوز leftText/leftIcon، فتحة left في العقد) | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | المحتوى الأيمن (يتجاوز rightText/rightIcon، فتحة right في العقد) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | المُعدِّل | `Modifier` | `Modifier` |

عندما تكون `*Content` / `*Text` / `*Icon` الخاصة بأحد الجانبين فارغة كلها، فإن ذلك الجانب يكون غير قابل للنقر ولا يُرسم فيه أي محتوى.

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `navbar.height` | ارتفاع شريط التنقل (44dp) |
| `navbar.paddingHorizontal` | الحشوة الأفقية يسارًا ويمينًا (تتبع `spacing.md`) |
| `navbar.titleFontSize` | حجم خط العنوان والنصين الجانبيين |
| `navbar.iconSize` | حجم الأيقونتين الجانبيتين (تتبع `icon.size.medium`) |
| `navbar.borderWidth` | عرض خط الفاصل السفلي |
| `zIndex.sticky` | مطابقة دلالات طبقة التثبيت العلوية |
| `color.background.default` | لون الخلفية |
| `color.text.primary` / `color.text.regular` | لون العنوان / النصين الجانبيين |
| `color.border.lighter` | لون خط الفاصل السفلي |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | النقر على اليسار يطلق onLeftClick | `LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test) |
| `rightclick-emits-onrightclick` | النقر على اليمين يطلق onRightClick | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | عند عدم وجود محتوى أيسر لا يُطلق | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | العنوان يعرض نصًا | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | خط الفاصل يستهلك رمز الحد | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | الارتفاع يستهلك رمز navbar | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # جزء JVM
./gradlew :components:navbar:connectedAndroidTest                # جزء التفاعل (يتطلب جهازًا)
```
