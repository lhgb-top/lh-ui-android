# السمات والوضع الداكن

## نموذج الرموز

المصدر الأصلي للرموز في السلالة الأصلية هو `lh-ui-core/tokens/*.json`، ويولّد codegen منتج Android:

| المنتج | المحتوى | شكل الوضع الداكن |
| --- | --- | --- |
| `LhTokens` | كامل اللون الفاتح: أحجام Button ولوحة Color وMotion وRadius وShadow وSpacing وTypography وZIndex | — |
| `LhDarkTokens` | الكائن الفرعي `Color` فقط (نموذج **الكائنات المتوازية**) | الداكن يغطّي الألوان فقط، بينما تبقى الأحجام والحركية وزوايا الدوران كما هي في الداكن |

> هذا يتطابق مع ios / harmony / windows (كائنات متوازية)، ويختلف عن شكل docs (دمج JSON السطحي).
 وقد صُمّمت طبقة السمات بناءً عليه: يحمل `LhColors` الألوان فقط، بينما تأخذ المكوّنات الأحجام مباشرة من `LhTokens`.

## LhColors

يقوم `LightLhColors` / `DarkLhColors` ببسط الكائنين المولّدين في لوحة ألوان واحدة غير قابلة للتغيير:

```kotlin
@Immutable
data class LhColors(
  val primary: Color, val onPrimary: Color,
  val success: Color, val warning: Color, val danger: Color, val info: Color,
  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */
)

val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)
val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)
```

## LhTheme

```kotlin
LhTheme(isDark = true) {
  // يُحقن LocalLhColors بـ DarkLhColors، فيستهلكها المكوّنات الداخلية تلقائيًا
}
```

عند عدم تغليفه بـ `LhTheme` يتم الرجوع إلى `LightLhColors` (الافتراضي هو الوضع الفاتح).

## الحساب المسبق للألوان الداكنة (مهم)

تستخدم سلالة الويب CSS `color-mix()` لحساب الألوان الداكنة بشكل ديناميكي، أما في الأصل فلا يوجد تتالي CSS، لذلك قام core بحساب التعابير الداكنة
**مسبقًا كقيم حرفية** (مثل `rgba(61,100,158,0.25)` و`#B1C1D8`). والنتيجة (الخطة الرئيسية R-n6):

> **عند تغيير لون preset الرئيسي، لا تتبع قيم الحساب المسبق الداكنة تلقائيًا**، بل يجب إعادة حسابها يدويًا وتحديث
`lh-ui-core/tokens/color-dark.json`، وتحديث *خط أساس لغة التصميم*.

## مرجع الرموز السريع

يوجد جدول الرموز الكامل (فاتح/داكن/شرح) في [مرجع رموز lh-ui-core](https://www.lhgb.top/lhui/core/tokens/)،
أو شغّل `pnpm codegen` في مستودع core ثم افتح `dist/docs/tokens.md`. ولتعيين الرموز على مستوى المكوّن انظر
[مرجع رموز Button](/ar/components/button#مرجع-الرموز).

## مسبق الإعداد المخصص (محجوز)

يدعم `LhTheme(colors = ...)` تمرير لوحة ألوان مخصصة، وستُفتح إمكانية السمات المسبقة الإعداد في إصدار لاحق مع
رموز السمات المسبقة الإعداد الخاصة بـ core.
