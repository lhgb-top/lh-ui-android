package top.lhgb.ui.docs.pages.ar

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "مربع حوار Dialog",
  lede = "طبقة عائمة شرطية (modal)، للتأكيدات المهمة أو المحتوى الذي يتطلب تركيز المستخدم. تُدار الطبقة على مستوى النافذة بواسطة `Dialog` الخاصة بـ Compose.",
  group = DocGroup.OVERLAY,
  contract = "مصدر العقد: `lh-ui-core/specs/dialog.json` · الحالات الذهبية: `cases/dialog.json` (8 حالات، تُرجمت إلى اختبارات JVM + Compose UI Test)",
  sections = listOf(
    DocSection(
      title = "الأنواع",
      prose = "يحدد `type` لون زر التأكيد (مطابقة واحد لواحد مع `LhButtonType`)، دون أي تغيير في باقي التخطيط.",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* منطق التأكيد */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"تأكيد الحذف\",",
        "  content = \"لا يمكن التراجع عن الحذف، هل أنت متأكد من الحذف؟\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "النوع الدلالي والستارة",
      prose = "عندما يكون `type` بقيمة `danger` يأخذ زر التأكيد لون الخطر؛ ويتحكم `closable` فيما إذا كان النقر على الستارة أو الضغط على زر الرجوع يُطلق `onClose`.\nبعد النقر على زر التأكيد / الإلغاء يحدث أولًا استدعاء رد العمل التجاري، ثم استدعاء `onClose`، وبناءً عليه يضبط المضيف `visible` على false.",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "ما إذا كان سيُعرض؛ عندما يكون false لا يُعرض أي محتوى", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "يُطلق عند النقر على زر التأكيد", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "يُطلق عند النقر على زر الإلغاء", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "يُطلق بعد التأكيد/الإلغاء أو (عند تفعيل closable) عند النقر على الستارة", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "العنوان", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "محتوى النص", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "النوع الدلالي، يحدد نوع (لون) زر التأكيد", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "نص زر التأكيد؛ عند الفراغ لا يُعرض زر التأكيد", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "نص زر الإلغاء؛ عند الفراغ لا يُعرض زر الإلغاء", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "ما إذا كان النقر على الستارة / الضغط على زر الرجوع يُطلق onClose", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "محتوى العنوان (يتجاوز title، فتحة title في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "محتوى النص (يتجاوز content، فتحة default في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "منطقة الأزرار السفلية (تتجاوز الأزرار الافتراضية، فتحة footer في العقد)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "المُعدِّل (يُطبّق على بطاقة المحتوى)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("القيمة", "نوع زر التأكيد"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "`LhButtonType` بنفس الاسم، ويأخذ اللون الدلالي المقابل")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "عرض بطاقة المحتوى (320dp)")),
    DocRow(cells = listOf("`dialog.borderRadius`", "نصف قطر زاوية البطاقة (يتبع `radius.base`)")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "حشوة البطاقة (تتبع `spacing.lg`)")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "حجم خط العنوان / النص")),
    DocRow(cells = listOf("`dialog.gap`", "المسافة بين العنوان والنص ومنطقة الأزرار")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "مطابقة دلالات الطبقات (يُدير `Dialog` الخاص بـ Compose الطبقة على مستوى النافذة)")),
    DocRow(cells = listOf("`color.background.overlay`", "لون الستارة")),
    DocRow(cells = listOf("`color.background.default`", "لون خلفية البطاقة")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "لون العنوان / النص")),
    DocRow(cells = listOf("`shadow.base`", "ظل البطاقة (لا يدعم Compose الانتشار (spread)، فيُؤخذ نصف قطر التمويه كـ elevation)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "التأكيد يطلق onConfirm + onClose", "`LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "بعد التأكيد يُطلق onClose", "نفس ما سبق")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "الإلغاء يطلق onCancel + onClose", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "عند تفعيل closable يُطلق النقر على الستارة onClose", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "عند عدم تفعيل closable يُبتلع النقر على الستارة", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "عند عدم الظهور لا يُعرض شيء", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "العنوان يعرض نصًا", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "زر تأكيد النوع danger يأخذ لون الخطر", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # جزء JVM",
    "./gradlew :components:dialog:connectedAndroidTest             # جزء التفاعل (يتطلب جهازًا)",
  ),
)
