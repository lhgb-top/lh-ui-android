package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocNavbarBasicPreview
import top.lhgb.ui.docs.previews.DocNavbarSlotsPreview

val navbarDoc = DocEntry(
  id = "navbar",
  title = "Navbar 내비게이션 바",
  lede = "페이지 상단의 제목 표시줄입니다. 좌 / 중 / 우의 3단 레이아웃으로, 주로 뒤로 가기와 제목 표시에 사용합니다.",
  group = DocGroup.OVERLAY,
  contract = "계약 출처: `lh-ui-core/specs/navbar.json` · 골든 케이스: `cases/navbar.json` (6건, 토큰 매핑은 JVM 단위 테스트로, 좌우 탭은 Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "사용법",
      prose = "3단이 동일한 너비(각 `weight(1f)`)를 가지며, 제목은 사용 가능 너비 기준으로 수평 중앙에 놓입니다. 기본적으로 하단 구분선을 그립니다.",
      preview = { DocNavbarBasicPreview() },
      code = listOf(
        "LhNavbar(",
        "  title = \"페이지 제목\",",
        "  leftIcon = \"arrow-left\",",
        "  leftText = \"뒤로\",",
        "  rightText = \"더보기\",",
        "  onLeftClick = { /* 뒤로 가기 */ },",
        "  onRightClick = { /* 더보기 */ },",
        ")",
      ),
    ),
    DocSection(
      title = "좌우 내용",
      prose = "글자와 아이콘을 동시에 사용할 수 있습니다(아이콘이 왼쪽, 글자가 오른쪽, 간격 `spacing.xs`). 어느 한쪽에 내용이 없으면 탭에 응답하지 않습니다\n(골든 케이스 `no-left-content-no-onleftclick`: 해당 노드에 탭 가능하다는 의미조차 존재하지 않습니다).",
      preview = { DocNavbarSlotsPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`title`", "중앙 제목", "`String?`", "`null`")),
    DocRow(cells = listOf("`onLeftClick`", "좌측 영역 탭 시 트리거", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`onRightClick`", "우측 영역 탭 시 트리거", "`() -> Unit`", "`{}`")),
    DocRow(cells = listOf("`leftText`", "좌측 버튼 글자. leftIcon과 공존 가능", "`String?`", "`null`")),
    DocRow(cells = listOf("`leftIcon`", "좌측 아이콘 이름(로컬 아이콘 체계, 예: `\"arrow-left\"`)", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightText`", "우측 버튼 글자. rightIcon과 공존 가능", "`String?`", "`null`")),
    DocRow(cells = listOf("`rightIcon`", "우측 아이콘 이름", "`String?`", "`null`")),
    DocRow(cells = listOf("`bordered`", "하단 구분선을 그릴지 여부", "`Boolean`", "`true`")),
    DocRow(cells = listOf("`titleContent`", "제목 내용(title을 덮어씀, spec의 default 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`leftContent`", "좌측 내용(leftText/leftIcon을 덮어씀, spec의 left 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`rightContent`", "우측 내용(rightText/rightIcon을 덮어씀, spec의 right 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`navbar.height`", "내비게이션 바 높이(44dp)")),
    DocRow(cells = listOf("`navbar.paddingHorizontal`", "좌우 가로 패딩(`spacing.md`를 따름)")),
    DocRow(cells = listOf("`navbar.titleFontSize`", "제목과 양쪽 글자의 폰트 크기")),
    DocRow(cells = listOf("`navbar.iconSize`", "양쪽 아이콘 크기(`icon.size.medium`를 따름)")),
    DocRow(cells = listOf("`navbar.borderWidth`", "하단 구분선 두께")),
    DocRow(cells = listOf("`zIndex.sticky`", "상단 고정 계층 시맨틱 정렬")),
    DocRow(cells = listOf("`color.background.default`", "배경색")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "제목 / 양쪽 글자 색상")),
    DocRow(cells = listOf("`color.border.lighter`", "하단 구분선 색상")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`leftclick-emits-onleftclick`", "좌측 탭이 onLeftClick을 트리거", "`LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test)")),
    DocRow(cells = listOf("`rightclick-emits-onrightclick`", "우측 탭이 onRightClick을 트리거", "`LhNavbarTest.right_click_emits_onRightClick`")),
    DocRow(cells = listOf("`no-left-content-no-onleftclick`", "좌측 내용이 없으면 트리거하지 않음", "`LhNavbarTest.no_left_content_does_not_emit_onLeftClick`")),
    DocRow(cells = listOf("`title-renders-text`", "제목이 글자를 렌더링", "`LhNavbarTest.title_renders_text`")),
    DocRow(cells = listOf("`bordered-uses-border-token`", "구분선이 보더 토큰을 소비", "`LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider`")),
    DocRow(cells = listOf("`height-uses-navbar-height-token`", "높이가 navbar 토큰을 소비", "`LhNavbarDefaultsTest.height_uses_navbar_height_token`")),
  ),
  commands = listOf(

    "./gradlew :components:navbar:test                                 # JVM 부분",
    "./gradlew :components:navbar:connectedAndroidTest                # 인터랙션 부분(기기 필요)",
  ),
)
