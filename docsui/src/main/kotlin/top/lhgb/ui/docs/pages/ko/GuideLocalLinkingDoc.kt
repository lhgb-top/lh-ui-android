package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "로컬 연결",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "패밀리 레이아웃 규칙",
      prose = "각 저장소가 동일한 상위 디렉터리에 있으면 토큰은 로컬 직접 연결됩니다. 형제 저장소가 존재하지 않는 경우(CI의 단일 저장소 clone)에는\n자동으로 npm 패키지로 폴백합니다.\n이는 Web 혈통의 vite alias 가드 메커니즘과 동형입니다(마스터 플랜 D18).",
      code = listOf(
        "lh-ui/                        # 패밀리 상위 디렉터리(저장소가 아님)",
        "├── lh-ui-core/               # 토큰 원본 + codegen",
        "├── lh-ui-android/            # 이 저장소",
        "├── lh-ui-ios/                # 형제 저장소",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "3단계 가드",
      prose = "`scripts/link-tokens.mjs`가 우선순위 순서로 토큰 산출물을 찾습니다:",
    ),
    DocSection(
      title = "일상 흐름",
      prose = "",
    ),
    DocSection(
      title = "CI 게이트",
      prose = "",
    ),
    DocSection(
      title = "금지 사항",
      prose = "• ❌ `core/src/main/kotlin/top/lhgb/ui/tokens/`를 수동으로 수정(생성물, gitignore)\n• ❌ 생성된 토큰 파일을 저장소에 커밋\n• ❌ 컴포넌트에 색상 / 간격 리터럴을 직접 작성(예: `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)\n• ❌ `file:` / `link:` 의존성 형태를 `build.gradle.kts`에 작성하여 커밋(로컬 연결은 로컬에만)",
    ),
    DocSection(
      title = "문제 해결",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # 토큰이 동기화되었는지 검사(파일 수 비교, 미동기화 시 종료 코드 1)",
  ),
)
