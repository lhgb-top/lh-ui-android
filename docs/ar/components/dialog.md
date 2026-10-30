# مربع حوار Dialog

طبقة عائمة شرطية (modal)، للتأكيدات المهمة أو المحتوى الذي يتطلب تركيز المستخدم. تُدار الطبقة على مستوى النافذة بواسطة `Dialog` الخاصة بـ Compose.

> مصدر العقد: [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> الحالات الذهبية: [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog) (8 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)

## الأنواع

يحدد `type` لون زر التأكيد (مطابقة واحد لواحد مع `LhButtonType`)، دون أي تغيير في باقي التخطيط.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">عنوان</div>
      <div class="sim-dialog__content">هذا نص محتوى يوضح تأثير العملية الحالية.</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">إلغاء</button>
        <button class="sim-btn sim-btn--primary">تأكيد</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* منطق التأكيد */ },
  onCancel = {},
  onClose = { visible = false },
  title = "تأكيد الحذف",
  content = "لا يمكن التراجع عن الحذف، هل أنت متأكد من الحذف؟",
  type = LhDialogType.danger,
)
```

## النوع الدلالي والستارة

عندما يكون `type` بقيمة `danger` يأخذ زر التأكيد لون الخطر؛ ويتحكم `closable` فيما إذا كان النقر على الستارة أو الضغط على زر الرجوع يُطلق `onClose`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">تأكيد الحذف</div>
      <div class="sim-dialog__content">لا يمكن التراجع عن الحذف، هل أنت متأكد من الحذف؟</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">إلغاء</button>
        <button class="sim-btn sim-btn--danger">تأكيد</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">معاينة محاكاة HTML (مظهر Android / Compose)، ليست رسمًا على جهاز حقيقي</p>

بعد النقر على زر التأكيد / الإلغاء يحدث **أولًا استدعاء رد العمل التجاري، ثم استدعاء `onClose`**، وبناءً عليه يضبط المضيف `visible` على false.

## API

### Props

| الخاصية | الشرح | النوع | القيمة الافتراضية |
| --- | --- | --- | --- |
| `visible` | ما إذا كان سيُعرض؛ عندما يكون false لا يُعرض أي محتوى | `Boolean` | — |
| `onConfirm` | يُطلق عند النقر على زر التأكيد | `() -> Unit` | — |
| `onCancel` | يُطلق عند النقر على زر الإلغاء | `() -> Unit` | — |
| `onClose` | يُطلق بعد التأكيد/الإلغاء أو (عند تفعيل closable) عند النقر على الستارة | `() -> Unit` | — |
| `title` | العنوان | `String?` | `null` |
| `content` | محتوى النص | `String?` | `null` |
| `type` | النوع الدلالي، يحدد نوع (لون) زر التأكيد | `LhDialogType` | `default` |
| `confirmText` | نص زر التأكيد؛ عند الفراغ لا يُعرض زر التأكيد | `String` | `"确认"` |
| `cancelText` | نص زر الإلغاء؛ عند الفراغ لا يُعرض زر الإلغاء | `String` | `"取消"` |
| `closable` | ما إذا كان النقر على الستارة / الضغط على زر الرجوع يُطلق onClose | `Boolean` | `false` |
| `titleContent` | محتوى العنوان (يتجاوز title، فتحة title في العقد) | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | محتوى النص (يتجاوز content، فتحة default في العقد) | `@Composable (() -> Unit)?` | `null` |
| `footer` | منطقة الأزرار السفلية (تتجاوز الأزرار الافتراضية، فتحة footer في العقد) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | المُعدِّل (يُطبّق على بطاقة المحتوى) | `Modifier` | `Modifier` |

### LhDialogType

| القيمة | نوع زر التأكيد |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | `LhButtonType` بنفس الاسم، ويأخذ اللون الدلالي المقابل |

## مرجع الرموز

| الرمز | الاستخدام |
| --- | --- |
| `dialog.width` | عرض بطاقة المحتوى (320dp) |
| `dialog.borderRadius` | نصف قطر زاوية البطاقة (يتبع `radius.base`) |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | حشوة البطاقة (تتبع `spacing.lg`) |
| `dialog.titleFontSize` / `dialog.contentFontSize` | حجم خط العنوان / النص |
| `dialog.gap` | المسافة بين العنوان والنص ومنطقة الأزرار |
| `zIndex.modal` / `zIndex.modalBackdrop` | مطابقة دلالات الطبقات (يُدير `Dialog` الخاص بـ Compose الطبقة على مستوى النافذة) |
| `color.background.overlay` | لون الستارة |
| `color.background.default` | لون خلفية البطاقة |
| `color.text.primary` / `color.text.regular` | لون العنوان / النص |
| `shadow.base` | ظل البطاقة (لا يدعم Compose الانتشار (spread)، فيُؤخذ نصف قطر التمويه كـ elevation) |

## مرجع الحالات الذهبية

| معرّف الحالة | التأكيد | الاختبار المحلي |
| --- | --- | --- |
| `confirm-emits-onconfirm` | التأكيد يطلق onConfirm + onClose | `LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | بعد التأكيد يُطلق onClose | نفس ما سبق |
| `cancel-emits-oncancel` | الإلغاء يطلق onCancel + onClose | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | عند تفعيل closable يُطلق النقر على الستارة onClose | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | عند عدم تفعيل closable يُبتلع النقر على الستارة | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | عند عدم الظهور لا يُعرض شيء | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | العنوان يعرض نصًا | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | زر تأكيد النوع danger يأخذ لون الخطر | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # جزء JVM
./gradlew :components:dialog:connectedAndroidTest             # جزء التفاعل (يتطلب جهازًا)
```
