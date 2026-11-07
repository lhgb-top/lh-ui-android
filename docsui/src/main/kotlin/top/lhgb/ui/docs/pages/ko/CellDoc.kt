package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCellBasicPreview
import top.lhgb.ui.docs.previews.DocCellRequiredAndLinkPreview

val cellDoc = DocEntry(
  id = "cell",
  title = "Cell 셀",
  lede = "리스트 셀입니다. 아이콘 / 제목 + 설명 / 우측 값 + 화살표의 3단 레이아웃으로, 주로 리스트와 조합해 사용합니다.",
  group = DocGroup.BASIC,
  contract = "계약 출처: `lh-ui-core/specs/cell.json` · 골든 케이스: `cases/cell.json` (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "기본 사용법",
      prose = "",
      preview = { DocCellBasicPreview() },
      code = listOf(
        "LhCell(",
        "  title = \"설명이 있는 제목\",",
        "  label = \"설명 글자\",",
        "  value = \"값\",",
        "  isLink = true,",
        "  lhOnClick = { /* 이동 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "필수 표시와 링크",
      prose = "`required`는 제목 앞에 빨간색 별표(`color.danger.default`)를 그리고, `isLink`는 끝에 우측 화살표 아이콘을 그립니다.",
      preview = { DocCellRequiredAndLinkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "제목(주 글자)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`value`", "우측 값(보조 글자, 우측 정렬)", "`String?`", "`null`")),
    DocRow(cells = listOf("`label`", "제목 아래의 설명 글자", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "하단 구분선을 그릴지 여부(`color.border.lighter`)", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`clickable`", "탭 가능 여부. true인 경우 우측 안내 화살표를 표시하고 탭에 응답", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`required`", "제목 앞에 필수 빨간 별표를 표시할지 여부(`color.danger.default`)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "좌측 아이콘 이름(로컬 아이콘 체계). 비어 있으면 그리지 않음", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhOnClick`", "탭 콜백. `clickable`이 false인 경우 트리거하지 않음", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`titleContent`", "제목 내용(title을 덮어씀, spec의 default 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`valueContent`", "우측 값 내용(value를 덮어씀, spec의 value 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`cell.paddingVertical`", "세로 패딩(`spacing.md` 참조)")),
    DocRow(cells = listOf("`cell.paddingHorizontal`", "가로 패딩(`spacing.md`)")),
    DocRow(cells = listOf("`cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue`", "제목 / 설명 / 값 폰트 크기")),
    DocRow(cells = listOf("`cell.gap`", "아이콘과 제목 사이의 간격(`spacing.xs`)")),
    DocRow(cells = listOf("`icon.size.medium`", "좌측 아이콘과 안내 화살표 크기")),
    DocRow(cells = listOf("`cell.borderWidth`", "하단 구분선 두께")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "글자 줄 높이")),
    DocRow(cells = listOf("`color.text.{primary,secondary}`", "제목 / 설명 / 값 색상")),
    DocRow(cells = listOf("`color.danger.default`", "필수 빨간 별표")),
    DocRow(cells = listOf("`color.border.lighter`", "하단 구분선")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`title-and-value-render`", "제목과 우측 값이 글자를 렌더링", "`LhCellTest.title_and_value_render` (Compose UI Test)")),
    DocRow(cells = listOf("`label-renders-secondary-tone`", "label이 보조 글자색 토큰을 사용", "`LhCellDefaultsTest.label_uses_secondary_text_token`")),
    DocRow(cells = listOf("`required-uses-danger-token`", "필수 표시가 위험 색 토큰을 사용", "`LhCellDefaultsTest.required_uses_danger_token`")),
    DocRow(cells = listOf("`bordered-emits-line-command`", "구분선이 보더 토큰을 소비", "`LhCellDefaultsTest.bordered_uses_border_lighter_token`")),
    DocRow(cells = listOf("`clickable-emits-onclick`", "탭이 onClick을 트리거", "`LhCellTest.clickable_emits_onClick`")),
    DocRow(cells = listOf("`not-clickable-no-onclick`", "탭할 수 없는 상태는 트리거하지 않음", "`LhCellTest.not_clickable_no_onClick`")),
  ),
  commands = listOf(

    "./gradlew :components:cell:test                              # JVM 부분",
    "./gradlew :components:cell:connectedAndroidTest              # 인터랙션 부분(기기 필요)",
  ),
)
