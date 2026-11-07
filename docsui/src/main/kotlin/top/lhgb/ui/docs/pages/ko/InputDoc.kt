package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocInputBasicPreview
import top.lhgb.ui.docs.previews.DocInputSizeAndFocusPreview

val inputDoc = DocEntry(
  id = "input",
  title = "Input 입력 상자",
  lede = "제어되는 한 줄 입력 상자입니다. 크기, 비활성화, 읽기 전용, 지우기, 비밀번호 마스킹, 최대 길이를 지원합니다.",
  group = DocGroup.FORM,
  contract = "계약 출처: `lh-ui-core/specs/input.json` · 골든 케이스: `cases/input.json` (8건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "기본 사용법",
      prose = "",
      preview = { DocInputBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhInput(",
        "  value = value,",
        "  placeholder = \"내용을 입력하세요\",",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "크기와 포커스",
      prose = "3단계 크기가 `input.height` / `input.paddingHorizontal` / `input.fontSize` 토큰에 매핑됩니다. 포커스 시 보더는 `color.focus`를 사용하고 `input.focusRingWidth`까지 두꺼워집니다.",
      preview = { DocInputSizeAndFocusPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "제어되는 값(필수)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "값 변경 콜백(필수)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "플레이스홀더 글자", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "입력 유형(일반 텍스트 / 비밀번호)", "`LhInputType`", "`Text`")),
    DocRow(cells = listOf("`lhSize`", "크기", "`LhInputSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "비활성화 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "읽기 전용 여부(편집은 불가하되 비활성화 스타일은 적용하지 않음)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`clearable`", "지우기 버튼 표시 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "최대 길이(초과 시 잘림)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "포커스 / 포커스 아웃 콜백", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`prefix` / `suffix`", "접두 / 접미 슬롯", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhInputSize",
      headers = listOf("값", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`input.height.small` (32dp)", "`input.paddingHorizontal.small` (12dp)", "`input.fontSize.small` (13sp)")),
        DocRow(cells = listOf("`Medium`", "`input.height.medium` (40dp)", "`input.paddingHorizontal.medium` (16dp)", "`input.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`input.height.large` (48dp)", "`input.paddingHorizontal.large` (20dp)", "`input.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`input.height.{small,medium,large}`", "3단계 높이")),
    DocRow(cells = listOf("`input.paddingHorizontal.{small,medium,large}`", "3단계 가로 패딩")),
    DocRow(cells = listOf("`input.fontSize.{small,medium,large}`", "3단계 폰트 크기")),
    DocRow(cells = listOf("`input.borderRadius` / `input.borderWidth` / `input.focusRingWidth`", "코너 반경 / 보더 두께 / 포커스 보더 두께")),
    DocRow(cells = listOf("`input.clearIconSize`", "지우기 아이콘 크기")),
    DocRow(cells = listOf("`color.focus`", "포커스 보더 색상")),
    DocRow(cells = listOf("`color.text.placeholder`", "플레이스홀더 글자 색상")),
    DocRow(cells = listOf("`color.border.base`", "기본 보더 색상")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "비활성화 상태의 세 가지 색")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "입력이 onValueChange를 트리거", "`LhInputTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`clearable-emits-empty-value`", "지우기 버튼이 빈 값을 내보냄", "`LhInputTest.clearable_emits_empty_value`")),
    DocRow(cells = listOf("`disabled-blocks-input`", "비활성화 상태에서는 입력 불가", "`LhInputTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`readonly-blocks-input`", "읽기 전용 상태에서는 입력 불가", "`LhInputTest.readonly_blocks_input`")),
    DocRow(cells = listOf("`placeholder-uses-placeholder-token`", "플레이스홀더 색이 플레이스홀더 토큰을 사용", "`LhInputDefaultsTest.placeholder_uses_placeholder_token`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "포커스가 포커스 토큰을 소비(포커스 아웃 시 중성 보더 사용)", "`LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "소형이 small 크기 토큰을 소비", "`LhInputDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "최대 길이를 초과한 입력은 잘림", "`LhInputTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:input:test                              # JVM 부분",
    "./gradlew :components:input:connectedAndroidTest              # 인터랙션 부분(기기 필요)",
  ),
)
