package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocButtonIconAndPatternPreview
import top.lhgb.ui.docs.previews.DocButtonSizesPreview
import top.lhgb.ui.docs.previews.DocButtonStatesPreview
import top.lhgb.ui.docs.previews.DocButtonThemePreview
import top.lhgb.ui.docs.previews.DocButtonTypesPreview

val buttonDoc = DocEntry(
  id = "button",
  title = "Button 버튼",
  lede = "기본적인 액션 버튼입니다. 즉시 액션을 트리거하며, 유형, 크기, 로딩, 비활성화, 아이콘, 중국풍 문양 배경을 지원합니다.",
  group = DocGroup.BASIC,
  contract = "계약 출처: `lh-ui-core/specs/button.json` · 골든 케이스: `cases/button.json` (8건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "유형",
      prose = "6가지 유형이 배경색과 글자색을 결정합니다.",
      preview = { DocButtonTypesPreview() },
      code = listOf(
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"주요 버튼\") }",
      ),
    ),
    DocSection(
      title = "크기",
      prose = "3단계 크기가 `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` 토큰에 매핑됩니다.",
      preview = { DocButtonSizesPreview() },
    ),
    DocSection(
      title = "상태",
      prose = "비활성화와 로딩 중 모두 `lhOnClick`을 트리거하지 않습니다. 둘 다 지정된 경우 비활성화가 우선합니다(골든 케이스 `disabled-precedes-loading`).",
      preview = { DocButtonStatesPreview() },
    ),
    DocSection(
      title = "아이콘과 문양 배경",
      prose = "아이콘 이름은 로컬 아이콘 체계에서 해석됩니다(`LhButtonIcons`, 현재 search / refresh / check / close를 지원하며, 미등록 이름은 그려지지 않습니다).\n문양 배경은 `color.decoration` 토큰을 소비하며, 배경색 위·글자 아래에 겹쳐져 대비에 영향을 주지 않습니다.",
      preview = { DocButtonIconAndPatternPreview() },
    ),
    DocSection(
      title = "라이트 / 다크 테마",
      prose = "사이트 우측 상단에서 테마를 전환하면 시뮬레이터 폰 프레임 내의 버튼 색상이 함께 바뀝니다. 실기기에서는 `LhTheme(isDark = true)`로 전환합니다.",
      preview = { DocButtonThemePreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`lhOnClick`", "탭 콜백. `disabled` 또는 `loading`이 true인 경우 트리거하지 않음", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "버튼 유형. 배경색과 글자색을 결정", "`LhButtonType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "버튼 크기. 크기 토큰에 매핑", "`LhButtonSize`", "`Medium`")),
    DocRow(cells = listOf("`disabled`", "비활성화 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "로딩 중 여부(인디케이터를 표시하고 lhOnClick을 트리거하지 않음)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`icon`", "아이콘 이름(로컬 아이콘 체계에서 해석)", "`String?`", "`null`")),
    DocRow(cells = listOf("`bgPattern`", "중국풍 문양 배경을 그릴지 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`content`", "버튼 글자 내용(spec의 default 슬롯)", "`@Composable () -> Unit`", "—")),
  ),
  enums = listOf(
    DocTable(
      title = "LhButtonType",
      headers = listOf("값", "배경색", "글자색"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default`", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.onPrimary`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.onPrimary`")),
      ),
    ),
    DocTable(
      title = "LhButtonSize",
      headers = listOf("값", "height", "paddingHorizontal", "fontSize", "iconSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`button.height.small` (32dp)", "`button.paddingHorizontal.small` (16dp)", "`button.fontSize.small` (13sp)", "`button.iconSize.small` (16dp)")),
        DocRow(cells = listOf("`Medium`", "`button.height.medium` (40dp)", "`button.paddingHorizontal.medium` (20dp)", "`button.fontSize.medium` (14sp)", "`button.iconSize.medium` (18dp)")),
        DocRow(cells = listOf("`Large`", "`button.height.large` (48dp)", "`button.paddingHorizontal.large` (24dp)", "`button.fontSize.large` (16sp)", "`button.iconSize.large` (20dp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`button.height.{small,medium,large}`", "3단계 높이")),
    DocRow(cells = listOf("`button.paddingHorizontal.{small,medium,large}`", "3단계 가로 패딩")),
    DocRow(cells = listOf("`button.fontSize.{small,medium,large}`", "3단계 폰트 크기")),
    DocRow(cells = listOf("`button.iconSize.{small,medium,large}`", "3단계 아이콘 크기")),
    DocRow(cells = listOf("`button.borderRadius` / `button.borderWidth`", "코너 반경(`radius.base`를 따름)과 보더 두께")),
    DocRow(cells = listOf("`button.fontWeight`", "폰트 굵기(`typography.fontWeight.primary`를 따름)")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "주요 유형의 배경 / 글자")),
    DocRow(cells = listOf("`color.{success,warning,danger,info}.default`", "시맨틱 유형의 배경색")),
    DocRow(cells = listOf("`color.background.default` / `color.text.primary` / `color.border.base`", "기본 유형의 배경 / 글자 / 보더")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "비활성화 상태의 세 가지 색")),
    DocRow(cells = listOf("`color.decoration`", "문양 색")),
    DocRow(cells = listOf("`shadow.lighter`", "기본 유형의 elevation")),
    DocRow(cells = listOf("`motion.duration.fast` / `motion.easing.easeInOut`", "누름 피드백 모션")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`click-emits-onclick`", "탭이 onClick을 1회 트리거", "`LhButtonTest.click_emits_onClick` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-click`", "비활성화 상태는 트리거하지 않음", "`LhButtonTest.disabled_blocks_click`")),
    DocRow(cells = listOf("`loading-blocks-click`", "로딩 상태는 트리거하지 않음", "`LhButtonTest.loading_blocks_click`")),
    DocRow(cells = listOf("`disabled-precedes-loading`", "비활성화가 우선", "`LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading`")),
    DocRow(cells = listOf("`default-type-renders-text`", "기본 유형이 글자를 렌더링", "`LhButtonDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "주요 유형이 주요 토큰을 소비", "`LhButtonDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "소형이 small 크기 토큰을 소비", "`LhButtonDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`bgpattern-uses-decoration-token`", "문양 배경이 장식 색을 소비", "`LhButtonDefaultsTest.bgPattern_uses_decoration_token`")),
    DocRow(cells = listOf("`icon-renders-icon-command`", "등록된 아이콘 이름은 벡터를 렌더링, 미등록 이름은 그리지 않음", "`LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null`")),
  ),
  commands = listOf(

    "./gradlew :components:button:test                        # JVM 부분",
    "./gradlew :components:button:connectedAndroidTest        # 인터랙션 부분(기기 필요)",
  ),
)
