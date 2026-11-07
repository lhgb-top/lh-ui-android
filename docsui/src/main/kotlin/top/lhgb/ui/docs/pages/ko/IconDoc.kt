package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocIconRegistryPreview
import top.lhgb.ui.docs.previews.DocIconSizeAndTonePreview
import top.lhgb.ui.docs.previews.DocIconTonesPreview

val iconDoc = DocEntry(
  id = "icon",
  title = "Icon 아이콘",
  lede = "아이콘 베이스 컴포넌트입니다. 다른 컴포넌트(cell / input / navbar 등)의 아이콘은 모두 이 컴포넌트의 로컬 레지스트리를 거쳐 렌더링됩니다.",
  group = DocGroup.BASIC,
  contract = "계약 출처: `lh-ui-core/specs/icon.json` · 골든 케이스: `cases/icon.json` (5건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "크기와 색조",
      prose = "3단계 크기가 `icon.size.*` 토큰에 매핑됩니다. 색조 `default`는 `color.text.primary`, 나머지는 시맨틱 색을 사용합니다.",
      preview = { DocIconSizeAndTonePreview() },
      code = listOf(
        "LhIcon(name = \"search\", lhSize = LhIconSize.large, tone = LhIconTone.primary)",
      ),
    ),
    DocSection(
      title = "시맨틱 색조",
      prose = "",
      preview = { DocIconTonesPreview() },
    ),
    DocSection(
      title = "아이콘 레지스트리",
      prose = "`LhIcons`는 로컬 아이콘 이름 → Material `ImageVector`의 레지스트리이며, 미등록 아이콘 이름은 `null`을 반환합니다(그려지지 않고, 예외도 발생하지 않음):",
      preview = { DocIconRegistryPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`name`", "아이콘 이름(`LhIcons` 레지스트리에서 해석)", "`String`", "—")),
    DocRow(cells = listOf("`lhSize`", "아이콘 크기", "`LhIconSize`", "`Medium`")),
    DocRow(cells = listOf("`tone`", "시맨틱 색조", "`LhIconTone`", "`Default`")),
    DocRow(cells = listOf("`contentDescription`", "접근성 설명. 기본값은 아이콘 이름, `null`을 명시적으로 전달하면 장식용 아이콘으로 선언", "`String?`", "`name`")),
    DocRow(cells = listOf("`lhOnClick`", "탭 콜백. null인 경우 탭할 수 없음", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhIconSize",
      headers = listOf("값", "크기 토큰"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`icon.size.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`icon.size.medium` (20dp)")),
        DocRow(cells = listOf("`Large`", "`icon.size.large` (24dp)")),
      ),
    ),
    DocTable(
      title = "LhIconTone",
      headers = listOf("값", "색상 토큰"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`")),
        DocRow(cells = listOf("`Regular`", "`color.text.regular`")),
        DocRow(cells = listOf("`Secondary`", "`color.text.secondary`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`icon.size.{small,medium,large}`", "3단계 크기")),
    DocRow(cells = listOf("`color.text.primary`", "default 색조")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "시맨틱 색조")),
    DocRow(cells = listOf("`color.text.{regular,secondary}`", "regular / secondary 색조")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "탭이 onClick을 트리거", "`LhIconTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`default-tone-uses-text-primary`", "기본 색조가 주 글자색 토큰을 사용", "`LhIconDefaultsTest.default_tone_uses_text_primary_token`")),
    DocRow(cells = listOf("`primary-tone-uses-primary-token`", "primary 색조가 주요 색 토큰을 사용", "`LhIconDefaultsTest.primary_tone_uses_primary_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "소형이 small 크기 토큰을 사용(역어서션으로 large가 적용되지 않았음을 확인)", "`LhIconDefaultsTest.small_size_uses_small_token`")),
    DocRow(cells = listOf("`render-emits-icon-command`", "등록된 아이콘 이름은 벡터로 해석, 미등록 이름은 그리지 않음", "`LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe`")),
  ),
  commands = listOf(

    "./gradlew :components:icon:test                            # JVM 부분",
    "./gradlew :components:icon:connectedAndroidTest            # 인터랙션 부분(기기 필요)",
  ),
)
