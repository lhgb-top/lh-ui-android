package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Switch 스위치",
  lede = "캡슐 형태의 트랙 + 원형 썸으로 이루어진 두 상태 토글 컨트롤입니다. 로딩 상태 플레이스홀더를 지원합니다.",
  group = DocGroup.FORM,
  contract = "계약 출처: `lh-ui-core/specs/switch.json` · 골든 케이스: `cases/switch.json` (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "상태",
      prose = "켜기는 주요색 트랙, 끄기는 보더색 트랙을 사용합니다. 썸 지름 = 높이 - 2 × `switch.gap`이며, 이동 애니메이션은 `motion.duration.base`를 사용합니다.",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "크기",
      prose = "3단계 크기가 `switch.width` / `switch.height` 토큰에 매핑됩니다.",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "켜짐 여부(필수)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "스위치 상태 변경 콜백. 새 스위치 상태를 전달", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "비활성화 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "로딩 중 여부(썸 위치에 로딩 링을 표시, 탭에 응답하지 않음)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "크기", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("값", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small` (36dp)", "`switch.height.small` (20dp)")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium` (44dp)", "`switch.height.medium` (24dp)")),
        DocRow(cells = listOf("`Large`", "`switch.width.large` (52dp)", "`switch.height.large` (28dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "3단계 트랙 너비")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "3단계 트랙 높이")),
    DocRow(cells = listOf("`switch.gap`", "썸과 트랙 사이의 여백(썸 지름 = 높이 - 2 × gap)")),
    DocRow(cells = listOf("`color.primary.default`", "켜짐 트랙 색상")),
    DocRow(cells = listOf("`color.border.base`", "꺼짐 트랙 색상")),
    DocRow(cells = listOf("`color.onPrimary`", "썸 색상")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "비활성화 트랙 / 썸 색상")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "썸 이동 애니메이션")),
    DocRow(cells = listOf("`loading.strokeWidth`", "로딩 링 보더 두께(재사용)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "탭이 상태를 반전하고 onChange를 내보냄", "`LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "켜진 상태에서 탭하면 꺼짐", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "비활성화 상태는 트리거하지 않음", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "로딩 상태는 트리거하지 않음", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "켜짐이 주요 토큰을 소비", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "꺼짐이 보더 토큰을 소비", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # JVM 부분",
    "./gradlew :components:switch:connectedAndroidTest            # 인터랙션 부분(기기 필요)",
  ),
)
