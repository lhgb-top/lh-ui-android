package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTagPlainPreview
import top.lhgb.ui.docs.previews.DocTagSizeAndMarkPreview
import top.lhgb.ui.docs.previews.DocTagTypesPreview

val tagDoc = DocEntry(
  id = "tag",
  title = "Tag 태그",
  lede = "시맨틱 색상 태그로, 아웃라인, 완전 둥근 마크, 닫기 버튼을 지원합니다.",
  group = DocGroup.BASIC,
  contract = "계약 출처: `lh-ui-core/specs/tag.json` · 골든 케이스: `cases/tag.json` (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "유형",
      prose = "6가지 시맨틱 유형이 배경색과 글자색을 결정합니다.",
      preview = { DocTagTypesPreview() },
      code = listOf(
        "LhTag(type = LhTagType.success) { Text(\"성공\") }",
      ),
    ),
    DocSection(
      title = "아웃라인(plain)",
      prose = "`plain`은 투명 배경 + 시맨틱 색상 보더와 글자를 사용합니다.",
      preview = { DocTagPlainPreview() },
    ),
    DocSection(
      title = "크기와 마크",
      prose = "3단계 크기가 `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` 토큰에 매핑됩니다. `mark`는 높이의 절반에 해당하는 완전한 코너 반경을 사용합니다.",
      preview = { DocTagSizeAndMarkPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`text`", "태그 글자(편의 작성법, `content`와 택일)", "`String?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
    DocRow(cells = listOf("`type`", "시맨틱 유형", "`LhTagType`", "`Default`")),
    DocRow(cells = listOf("`lhSize`", "크기", "`LhTagSize`", "`Medium`")),
    DocRow(cells = listOf("`plain`", "아웃라인 여부(투명 배경 + 시맨틱 색상 보더)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`round`", "완전 둥근 코너 여부(높이의 절반)", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`closable`", "닫기 버튼 표시 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`onClose`", "닫기 콜백. `closable`이 false인 경우 트리거하지 않음", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`content`", "태그 내용(text를 덮어씀, spec의 default 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhTagType",
      headers = listOf("값", "배경색(plain 아님)", "보더/글자(plain)"),
      rows = listOf(
        DocRow(cells = listOf("`Default`", "`color.background.default` + `color.border.base` 보더", "`color.text.primary`")),
        DocRow(cells = listOf("`Primary`", "`color.primary.default`", "`color.primary.default`")),
        DocRow(cells = listOf("`Success`", "`color.success.default`", "`color.success.default`")),
        DocRow(cells = listOf("`Warning`", "`color.warning.default`", "`color.warning.default`")),
        DocRow(cells = listOf("`Danger`", "`color.danger.default`", "`color.danger.default`")),
        DocRow(cells = listOf("`Info`", "`color.info.default`", "`color.info.default`")),
      ),
    ),
    DocTable(
      title = "LhTagSize",
      headers = listOf("값", "height", "paddingHorizontal", "fontSize"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`tag.height.small` (20dp)", "`tag.paddingHorizontal.small` (8dp)", "`tag.fontSize.small` (12sp)")),
        DocRow(cells = listOf("`Medium`", "`tag.height.medium` (24dp)", "`tag.paddingHorizontal.medium` (10dp)", "`tag.fontSize.medium` (14sp)")),
        DocRow(cells = listOf("`Large`", "`tag.height.large` (28dp)", "`tag.paddingHorizontal.large` (12dp)", "`tag.fontSize.large` (16sp)")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`tag.height.{small,medium,large}`", "3단계 높이")),
    DocRow(cells = listOf("`tag.paddingHorizontal.{small,medium,large}`", "3단계 가로 패딩")),
    DocRow(cells = listOf("`tag.fontSize.{small,medium,large}`", "3단계 폰트 크기")),
    DocRow(cells = listOf("`tag.borderRadius` / `tag.borderWidth`", "코너 반경과 보더 두께")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "시맨틱 배경 / 보더 / 글자 색")),
    DocRow(cells = listOf("`color.onPrimary`", "일반 글자 이외의 색")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "비활성화 상태의 세 가지 색")),
    DocRow(cells = listOf("`spacing.xs`", "닫기 버튼과 글자 사이의 간격")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`default-type-renders-text`", "기본 유형이 중성 배경 + 주 글자색 + 기본 보더를 사용", "`LhTagDefaultsTest.default_type_uses_neutral_tokens`")),
    DocRow(cells = listOf("`primary-type-uses-primary-tokens`", "주요 유형이 주요 토큰을 소비", "`LhTagDefaultsTest.primary_type_uses_primary_tokens`")),
    DocRow(cells = listOf("`plain-uses-bg-tokens`", "plain이 시맨틱 옅은 배경 토큰을 소비", "`LhTagDefaultsTest.plain_uses_bg_tokens`")),
    DocRow(cells = listOf("`small-size-uses-small-tokens`", "소형이 small 토큰을 소비(역어서션으로 large가 적용되지 않았음을 확인)", "`LhTagDefaultsTest.small_size_uses_small_tokens`")),
    DocRow(cells = listOf("`round-makes-radius-half-height`", "round의 코너 반경이 높이의 절반", "`LhTagDefaultsTest.round_makes_radius_half_height`")),
    DocRow(cells = listOf("`closable-tap-emits-onclose`", "닫기 탭이 onClose를 트리거", "`LhTagTest.closable_tap_emits_onClose` (Compose UI Test)")),
  ),
  commands = listOf(

    "./gradlew :components:tag:test                             # JVM 부분",
    "./gradlew :components:tag:connectedAndroidTest             # 인터랙션 부분(기기 필요)",
  ),
)
