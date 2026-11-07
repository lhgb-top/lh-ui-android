package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocLoadingSizeAndTonePreview

val loadingDoc = DocEntry(
  id = "loading",
  title = "Loading 로딩",
  lede = "로딩 인디케이터입니다. 원형 회전 애니메이션의 지속 시간은 `motion.duration.base`(밀리초)에서 가져옵니다.",
  group = DocGroup.BASIC,
  contract = "계약 출처: `lh-ui-core/specs/loading.json` · 골든 케이스: `cases/loading.json` (5건, 순수 표시 컴포넌트로 JVM 단위 테스트로 번역됨)",
  sections = listOf(
    DocSection(
      title = "크기와 색조",
      prose = "3단계 크기가 `loading.size.*` 토큰에 매핑되고, 보더 두께는 `loading.strokeWidth`, 색조는 시맨틱 색을 사용합니다.",
      preview = { DocLoadingSizeAndTonePreview() },
      code = listOf(
        "LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)",
      ),
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhSize`", "인디케이터 지름", "`LhLoadingSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "링 색상", "`LhLoadingTone`", "`Primary`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhLoadingSize",
      headers = listOf("값", "크기 토큰"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`loading.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`loading.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`loading.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhLoadingTone",
      headers = listOf("값", "색상 토큰"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.regular`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`loading.size.{small,medium,large}`", "3단계 지름")),
    DocRow(cells = listOf("`loading.strokeWidth`", "링 보더 두께")),
    DocRow(cells = listOf("`color.text.regular`", "default 색조")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "시맨틱 색조")),
    DocRow(cells = listOf("`motion.duration.base`", "한 바퀴 회전에 걸리는 시간(단말에서는 밀리초 정수)")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`medium-size-uses-medium-token`", "중형이 medium 크기 토큰을 사용", "`LhLoadingDefaultsTest.medium_size_uses_medium_token`")),
    DocRow(cells = listOf("`large-size-uses-large-token`", "대형이 large 크기 토큰을 사용", "`LhLoadingDefaultsTest.large_size_uses_large_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 색조가 주요 색 토큰을 사용", "`LhLoadingDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`success-tone-uses-success-token`", "success 색조가 성공 색 토큰을 사용", "`LhLoadingDefaultsTest.success_tone_uses_success_token`")),
    DocRow(cells = listOf("`render-emits-ring-command`", "원형 인디케이터 렌더링(보더 두께가 토큰을 소비)", "`LhLoadingDefaultsTest.stroke_width_uses_token`")),
  ),
  commands = listOf(

    "./gradlew :components:loading:test                          # JVM 부분(순수 표시, 인터랙션 케이스 없음)",
  ),
)
