package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Dialog 대화상자",
  lede = "모달 오버레이입니다. 중요한 확인이나 사용자에게 집중이 필요한 내용에 사용합니다. 윈도우 레벨 계층은 Compose `Dialog`가 관리합니다.",
  group = DocGroup.OVERLAY,
  contract = "계약 출처: `lh-ui-core/specs/dialog.json` · 골든 케이스: `cases/dialog.json` (8건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "유형",
      prose = "`type`이 확인 버튼의 색상을 결정합니다(`LhButtonType`와 1:1로 매핑). 그 외의 레이아웃은 변하지 않습니다.",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* 확인 로직 */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"삭제 확인\",",
        "  content = \"삭제 후에는 되돌릴 수 없습니다. 삭제하시겠습니까?\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "시맨틱 유형과 오버레이",
      prose = "`type`이 `danger`인 경우 확인 버튼은 위험 색상을 사용합니다. `closable`은 오버레이 탭 / 뒤로 가기 버튼으로 `onClose`를 트리거할지 여부를 제어합니다.\n확인 / 취소 버튼은 탭 후 비즈니스 콜백을 먼저 트리거하고, 그 다음 `onClose`를 트리거합니다. 호스트는 이에 따라 `visible`을 false로 설정합니다.",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "표시 여부. false인 경우 아무것도 렌더링하지 않음", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "확인 버튼 탭 시 트리거", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "취소 버튼 탭 시 트리거", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "확인 / 취소 후, 또는 (closable인 경우) 오버레이 탭 시 트리거", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "제목", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "본문 내용", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "시맨틱 유형. 확인 버튼의 유형(색상)을 결정", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "확인 버튼 글자. 비어 있는 경우 확인 버튼을 표시하지 않음", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "취소 버튼 글자. 비어 있는 경우 취소 버튼을 표시하지 않음", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "오버레이 탭 / 뒤로 가기 버튼으로 onClose를 트리거할지 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "제목 내용(title을 덮어씀, spec의 title 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "본문 내용(content를 덮어씀, spec의 default 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "하단 버튼 영역(기본 버튼을 덮어씀, spec의 footer 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자(내용 카드에 적용)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("값", "확인 버튼 유형"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "같은 이름의 `LhButtonType`. 해당 시맨틱 색상 사용")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "내용 카드 너비(320dp)")),
    DocRow(cells = listOf("`dialog.borderRadius`", "카드 코너 반경(`radius.base`를 따름)")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "카드 패딩(`spacing.lg`를 따름)")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "제목 / 본문 폰트 크기")),
    DocRow(cells = listOf("`dialog.gap`", "제목, 본문, 버튼 영역 사이의 간격")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "계층 시맨틱 정렬(Compose `Dialog`가 윈도우 레벨 계층을 관리)")),
    DocRow(cells = listOf("`color.background.overlay`", "오버레이 색상")),
    DocRow(cells = listOf("`color.background.default`", "카드 배경색")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "제목 / 본문 색상")),
    DocRow(cells = listOf("`shadow.base`", "카드 그림자(Compose는 spread를 지원하지 않으므로 흐림 반경을 elevation으로 사용)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "확인이 onConfirm + onClose를 트리거", "`LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "확인 후 onClose를 트리거", "동일")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "취소가 onCancel + onClose를 트리거", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "closable인 경우 오버레이 탭이 onClose를 트리거", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "closable이 아닌 경우 오버레이 탭을 무시", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "숨겨진 경우 렌더링하지 않음", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "제목이 글자를 렌더링", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "danger 유형의 확인 버튼이 위험 색상을 사용", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # JVM 부분",
    "./gradlew :components:dialog:connectedAndroidTest             # 인터랙션 부분(기기 필요)",
  ),
)
