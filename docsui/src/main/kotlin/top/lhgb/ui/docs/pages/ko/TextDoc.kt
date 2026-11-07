package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextLevelsPreview
import top.lhgb.ui.docs.previews.DocTextTonesPreview
import top.lhgb.ui.docs.previews.DocTextWeightPreview

val textDoc = DocEntry(
  id = "text",
  title = "Text 텍스트",
  lede = "텍스트 표시 원시 컴포넌트: 전역 타이포그래피 토큰을 소비하며 크기 레벨, 자중, 색조, 정렬을 지원.",
  group = DocGroup.BASIC,
  contract = "계약 원본: `lh-ui-core/specs/text.json` · 골든 케이스: `cases/text.json`(5건, JVM 단위 테스트로 번역됨)",
  sections = listOf(
    DocSection(
      title = "크기 레벨",
      prose = "`level`은 `typography.fontSize.<level>` 토큰에 매핑되며 기본값은 `base`.",
      preview = { DocTextLevelsPreview() },
      code = listOf(
        "LhText(content = \"Title text\", level = LhTextLevel.large)",
      ),
    ),
    DocSection(
      title = "자중",
      prose = "`weight`는 `typography.fontWeight.<weight>` 토큰에 매핑(primary 400 / bold 600).",
      preview = { DocTextWeightPreview() },
    ),
    DocSection(
      title = "색조",
      prose = "`tone`은 `color.text.<tone>` 토큰에 매핑. `selected`는 `color.selected` 사용.",
      preview = { DocTextTonesPreview() },
    ),
    DocSection(
      title = "정렬과 행 높이",
      prose = "`lhAlign`은 행 박스 안에서 정렬. 행 높이는 항상 `typography.lineHeight.primary`(크기 × 1.8).",
      code = listOf(
        "LhText(content = \"Centered\", lhAlign = LhTextAlign.center)",
      ),
    ),
    DocSection(
      title = "Props",
      prose = "",
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`typography.fontSize.<level>`", "six size levels")),
    DocRow(cells = listOf("`typography.fontWeight.{primary,bold}`", "two weights")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "line height (font size × 1.8)")),
    DocRow(cells = listOf("`color.text.<tone>` / `color.selected`", "text tones")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-level-uses-base-tokens`", "default level consumes the base typography tokens", "`LhTextDefaultsTest.default_level_uses_base_tokens`")),
    DocRow(cells = listOf("`large-level-uses-large-token`", "large level consumes the large size token", "`LhTextDefaultsTest.large_level_uses_large_token`")),
    DocRow(cells = listOf("`bold-weight-uses-bold-token`", "bold consumes the bold weight token", "`LhTextDefaultsTest.bold_weight_uses_bold_token`")),
    DocRow(cells = listOf("`secondary-tone-uses-text-secondary`", "secondary tone takes the secondary text color token", "`LhTextDefaultsTest.secondary_tone_uses_text_secondary`")),
    DocRow(cells = listOf("`render-emits-single-text-command`", "render emits a single TEXT command", "`LhTextDefaultsTest.all_levels_map_to_their_tokens`")),
  ),
  commands = listOf(

    "./gradlew :components:text:test                             # JVM",
  ),
)
