package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Picker 피커",
  lede = "단일 열의 하단 휠 피커입니다. 스크롤이 멈추면 중앙 인디케이터에 가장 가까운 항목이 선택 항목이 됩니다.",
  group = DocGroup.OVERLAY,
  contract = "계약 출처: `lh-ui-core/specs/picker.json` · 골든 케이스: `cases/picker.json` (8건, 클램프와 토큰 매핑은 JVM 단위 테스트로, 스크롤과 버튼 인터랙션은 Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "사용법",
      prose = "하단에서 올라옵니다. 상단에 마스크, 하단에 패널(상단 코너 둥글게)이 위치합니다. 툴바는 \"취소 / 제목 / 확인\"의 3단 구성입니다.",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"옵션 1\", \"옵션 2\", \"옵션 3\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"선택하세요\",",
        ")",
      ),
    ),
    DocSection(
      title = "선택 메커니즘",
      prose = "휠은 스크롤 가능한 리스트이며, 각 항목의 높이는 `picker.itemHeight`입니다. 중앙 인디케이터는 두 개의 가로선으로, 간격 = itemHeight입니다.\n스크롤이 멈추면 스크롤한 거리를 반올림해 선택 인덱스를 구하고, 변경 시 `onValueChange`를 트리거합니다.\n`value`가 범위를 벗어나면 `LhPickerDefaults.clamp`가 후보 범위 내로 클램프합니다.",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "표시 여부. false인 경우 아무 내용도 렌더링하지 않음", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "후보 항목 텍스트 배열(단일 열)", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "휠이 멈추고 선택 항목이 변경되었을 때 트리거. 새 인덱스를 전달", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "확인 버튼 탭 시 트리거. 현재 선택 인덱스를 전달", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "취소 버튼 탭 시 트리거", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "확인 / 취소 후 트리거. 호스트는 이에 따라 visible을 false로 설정", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "선택 항목 인덱스(0부터 시작). 범위를 벗어나면 클램프", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "툴바 중앙의 제목", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "확인 버튼 글자", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "취소 버튼 글자", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "휠 내용 영역(기본 후보 렌더링을 덮어씀, spec의 default 슬롯)", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "툴바(기본 확인 / 제목 / 취소를 덮어씀, spec의 toolbar 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "휠 리스트 상태(기본값은 `rememberLhPickerListState(value, options)`로 제공. 테스트 시 외부에서 주입 가능)", "`LazyListState`", "좌측 참조")),
    DocRow(cells = listOf("`modifier`", "수정자(패널에 적용)", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "패널 높이(260dp)")),
    DocRow(cells = listOf("`picker.itemHeight`", "각 항목의 높이(40dp, 중앙 인디케이터 간격이기도 함)")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "툴바 높이(44dp)")),
    DocRow(cells = listOf("`picker.itemFontSize`", "후보 항목 폰트 크기")),
    DocRow(cells = listOf("`picker.borderRadius`", "패널 상단 코너 반경")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "패널 가로 패딩(`spacing.md`를 따름)")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "계층 시맨틱 정렬(Compose `Popup`이 윈도우 레벨 계층을 자체 관리)")),
    DocRow(cells = listOf("`color.background.overlay`", "마스크 색상")),
    DocRow(cells = listOf("`color.background.default`", "패널 배경색")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "제목 / 후보 항목 색상")),
    DocRow(cells = listOf("`color.border.lighter`", "중앙 인디케이터 선 색상")),
    DocRow(cells = listOf("`color.primary.default`", "확인 버튼 색상")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "확인이 현재 인덱스 + onClose를 트리거", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test)")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "확인 후 onClose를 트리거", "동일")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "취소가 onCancel + onClose를 트리거", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "스크롤이 선택을 변경하고 onValueChange를 트리거", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "value가 음수인 경우 0으로 클램프", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "value가 범위를 벗어나면 마지막 항목으로 클램프", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "제목이 글자를 렌더링", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "보이지 않을 때 렌더링하지 않음", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # JVM 부분",
    "./gradlew :components:picker:connectedAndroidTest               # 인터랙션 부분(기기 필요)",
  ),
)
