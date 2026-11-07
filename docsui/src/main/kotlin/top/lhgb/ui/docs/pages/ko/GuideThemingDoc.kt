package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "테마 및 다크 모드",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "토큰 모델",
      prose = "네이티브 혈통의 토큰 원본은 `lh-ui-core/tokens/*.json`이며, codegen이 Android 산출물을 생성합니다:\n이는 ios / harmony / windows와 일치(병렬 객체)하며, docs 측(단순 병합 JSON)과는 형태가 다릅니다.\n테마 레이어는 이에 따라 설계되었습니다: `LhColors`는 색상만 담당하고 크기는 컴포넌트가 `LhTokens`에서 직접 가져옵니다.",
    ),
    DocSection(
      title = "LhColors",
      prose = "`LightLhColors` / `DarkLhColors`는 생성된 두 object를 한 장의 불변 색상표로 평탄화합니다:",
      code = listOf(
        "@Immutable",
        "data class LhColors(",
        "  val primary: Color, val onPrimary: Color,",
        "  val success: Color, val warning: Color, val danger: Color, val info: Color,",
        "  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */",
        ")",
        "",
        "val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)",
        "val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)",
      ),
    ),
    DocSection(
      title = "LhTheme",
      prose = "`LhTheme`으로 감싸지 않으면 `LightLhColors`로 폴백합니다(기본값은 라이트).",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // LocalLhColors에 DarkLhColors를 주입하고 내부 컴포넌트가 자동으로 소비",
        "}",
      ),
    ),
    DocSection(
      title = "다크 사전 계산(중요)",
      prose = "Web 혈통의 다크는 CSS `color-mix()`로 동적으로 계산하지만, 네이티브에는 CSS 캐스케이드가 없기 때문에 core가 다크 식을\n리터럴로 미리 계산해 둡니다(예: `rgba(61,100,158,0.25)`, `#B1C1D8`). 결과(마스터 플랜 R-n6):\n프리셋 메인 컬러를 변경하더라도 다크 사전 계산 값은 자동으로 따라가지 않습니다. 반드시 다시 수동으로 계산하여\n`lh-ui-core/tokens/color-dark.json`을 업데이트하고 *디자인 언어 기준선*을 새로 고쳐야 합니다.",
    ),
    DocSection(
      title = "토큰 빠른 참조",
      prose = "전체 토큰 표(라이트 / 다크 / 설명)는 lh-ui-core 토큰 빠른 참조에서, 또는 core 저장소에서\n`pnpm codegen`을 실행한 뒤 `dist/docs/tokens.md`에서 확인할 수 있습니다. 컴포넌트 수준 토큰 매핑은\nButton 토큰 매핑을 참조하세요.",
    ),
    DocSection(
      title = "커스텀 프리셋(예정)",
      prose = "`LhTheme(colors = ...)`을 통해 커스텀 색상표를 전달할 수 있으며, 프리셋 테마 기능은 향후 버전에서 core의\n테마 프리셋 토큰과 함께 공개될 예정입니다.",
    ),
  ),
)
