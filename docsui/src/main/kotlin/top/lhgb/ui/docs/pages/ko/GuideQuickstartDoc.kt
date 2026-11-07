package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "빠른 시작",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "최소 예제",
      prose = "`LhTheme`은 테마 컨테이너이며, 내부의 모든 Lh 컴포넌트가 자동으로 토큰을 소비합니다. 감싸지 않으면 라이트 색상표로 폴백합니다.",
      code = listOf(
        "import top.lhgb.ui.button.LhButton",
        "import top.lhgb.ui.button.LhButtonType",
        "import top.lhgb.ui.theme.LhTheme",
        "",
        "@Composable",
        "fun Demo() {",
        "  LhTheme {",
        "    LhButton(",
        "      type = LhButtonType.Primary,",
        "      lhOnClick = { /* 탭 처리 */ },",
        "    ) {",
        "      Text(\"주요 버튼\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "유형과 크기",
      prose = "",
      code = listOf(
        "// 6가지 유형",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"기본\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"주요\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"성공\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"경고\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"위험\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"정보\") }",
        "",
        "// 3가지 크기",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"소형\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"중형\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"대형\") }",
      ),
    ),
    DocSection(
      title = "상태",
      prose = "",
      code = listOf(
        "// 비활성화: 탭에 응답하지 않고 비활성화 스타일 표시",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"비활성화\") }",
        "",
        "// 로딩 중: 인디케이터를 표시하고 lhOnClick을 트리거하지 않음",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"로딩 중\") }",
        "",
        "// 둘 다 지정: 비활성화가 우선(골든 케이스 disabled-precedes-loading)",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"비활성화 우선\") }",
      ),
    ),
    DocSection(
      title = "아이콘과 문양 배경",
      prose = "",
      code = listOf(
        "// 아이콘 이름은 로컬 아이콘 체계에서 해석(LhButtonIcons 참조, 미등록 이름은 그리지 않음)",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"검색\") }",
        "",
        "// 중국풍 문양 배경(color.decoration 토큰을 소비)",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"문양 배경\") }",
      ),
    ),
    DocSection(
      title = "라이트 / 다크 테마",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // 내부의 모든 컴포넌트가 자동으로 다크 토큰으로 전환(core가 미리 계산한 값, 런타임 조색 없음)",
        "}",
      ),
    ),
    DocSection(
      title = "네이티브 문서 모듈",
      prose = "이 웹 문서 외에 저장소 안에 Android 네이티브 문서 모듈(`:docsui`)이 있습니다. 문서 페이지는 이 사이트의 구조와 1:1로 맞춰져 있지만, 미리보기는 정적 HTML 시뮬레이션이 아니라 실제 Compose 실시간 렌더링이며 인터랙티브합니다(버튼 탭, 입력, 다이얼로그 팝업 가능).\n드래그하여 바로 사용: 아무 Android 프로젝트에서나 의존성을 추가한 뒤, 한 줄의 `LhDocs.launch(context)`로 전체 문서를 열 수 있습니다(14개 컴포넌트 페이지 + 5개 가이드 페이지 + 라이트 / 다크 전환 + 실시간 미리보기):\n라이브러리의 manifest에는 `exported = false`인 `LhDocsActivity`가 자동으로 병합되며, 호스트에 런처 아이콘을 주입하지 않습니다.\n독립 패키징: `:docsapp`는 매우 얇은 application 모듈(단일 Activity)이며, 단독으로 패키징하면 \"lh-ui 문서\" APP을 얻을 수 있습니다:",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // 또는 Maven 좌표 top.lhgb.ui:docsui",
        "",
        "// 임의 진입점(예: \"컴포넌트 문서\" 버튼의 탭 콜백)",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "컴포넌트 예제 미리보기",
      prose = "네이티브 UI는 브라우저에서 실시간 렌더링할 수 없으므로, 이 사이트는 인라인 정적 HTML 폰 프레임 시뮬레이터로 외관을 보여줍니다:\n시뮬레이터의 다크 모드는 사이트와 연동됩니다. 우측 상단에서 테마를 전환하면 폰 프레임 내의 색상이 함께 바뀝니다(다크 토큰은 core가 미리 계산).",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
