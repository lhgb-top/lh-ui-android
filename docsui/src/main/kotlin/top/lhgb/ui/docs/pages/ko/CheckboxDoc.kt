package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Checkbox 체크박스",
  lede = "제어되는 체크박스입니다. 중간 상태(indeterminate)와 비활성화를 지원합니다.",
  group = DocGroup.FORM,
  contract = "계약 출처: `lh-ui-core/specs/checkbox.json` · 골든 케이스: `cases/checkbox.json` (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "상태",
      prose = "선택 / 중간 상태는 채워진 주요색 배경 + onPrimary 인디케이터(체크 표시 / 가로 막대), 미선택은 보더만 그립니다.",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"약관에 동의\",",
        ")",
      ),
    ),
    DocSection(
      title = "크기",
      prose = "3단계 상자 크기가 `checkbox.size.*` 토큰에 매핑됩니다.",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "선택 여부(필수)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "선택 상태 변경 콜백. 새 선택 상태를 전달", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "중간 상태(불확정 상태) 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "비활성화 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "라벨 글자", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "상자 크기", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "라벨 내용(label을 덮어씀, spec의 default 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("값", "크기 토큰"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large` (24dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "3단계 상자 크기")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "상자 코너 반경과 보더 두께")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "체크 표시 / 가로 막대 보더 두께")),
    DocRow(cells = listOf("`checkbox.gap`", "상자와 라벨 사이의 간격")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "선택 배경 / 인디케이터 색상")),
    DocRow(cells = listOf("`color.border.base`", "미선택 보더 색상")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "비활성화 상태의 세 가지 색")),
    DocRow(cells = listOf("`color.text.primary`", "라벨 색상")),
    DocRow(cells = listOf("`typography.fontSize.base`", "라벨 폰트 크기")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "탭이 상태를 반전하고 onChange를 내보냄", "`LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "선택된 상태에서 탭하면 선택 해제", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "중간 상태에서 탭하면 선택됨으로 변경", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "비활성화 상태는 트리거하지 않음", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "선택 상태가 주요 토큰을 소비", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "미선택이 보더 토큰을 소비", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # JVM 부분",
    "./gradlew :components:checkbox:connectedAndroidTest           # 인터랙션 부분(기기 필요)",
  ),
)
