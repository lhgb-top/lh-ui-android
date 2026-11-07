package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocRadioSizesPreview
import top.lhgb.ui.docs.previews.DocRadioStatesAndGroupPreview

val radioDoc = DocEntry(
  id = "radio",
  title = "Radio 라디오 버튼",
  lede = "제어되는 라디오 버튼입니다. 선택된 상태에서 다시 탭해도 선택이 취소되지 않습니다(라디오 시맨틱).",
  group = DocGroup.FORM,
  contract = "계약 출처: `lh-ui-core/specs/radio.json` · 골든 케이스: `cases/radio.json` (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "상태와 라디오 그룹",
      prose = "컴포넌트 자체는 그룹 로직을 포함하지 않으며, 라디오 상호 배제는 호스트가 유지합니다(`value`로 그룹 내 항목을 식별).",
      preview = { DocRadioStatesAndGroupPreview() },
      code = listOf(
        "var selected by remember { mutableStateOf(\"a\") }",
        "listOf(\"옵션 1\" to \"a\", \"옵션 2\" to \"b\").forEach { (text, value) ->",
        "  LhRadio(",
        "    checked = selected == value,",
        "    onChange = { selected = value },",
        "    label = text,",
        "    value = value,",
        "  )",
        "}",
      ),
    ),
    DocSection(
      title = "크기",
      prose = "3단계 링 크기가 `radio.size.*` 토큰에 매핑됩니다. 내부 점의 지름 = 크기 × `radio.dotScale`입니다.",
      preview = { DocRadioSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "선택 여부(필수)", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "미선택에서 선택으로 바뀔 때 트리거. `checked = true`를 전달", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "비활성화 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "라벨 글자", "`String?`", "`null`")),
    DocRow(cells = listOf("`value`", "라디오 값(그룹 내 식별자. 컴포넌트 자체는 그룹 로직을 포함하지 않음)", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "링 크기", "`LhRadioSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "라벨 내용(label을 덮어씀, spec의 default 슬롯)", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhRadioSize",
      headers = listOf("값", "크기 토큰", "내부 점 지름"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`radio.size.small` (16dp)", "16dp × `radio.dotScale` (0.5)")),
        DocRow(cells = listOf("`Medium`", "`radio.size.medium` (20dp)", "20dp × `radio.dotScale`")),
        DocRow(cells = listOf("`Large`", "`radio.size.large` (24dp)", "24dp × `radio.dotScale`")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`radio.size.{small,medium,large}`", "3단계 링 크기")),
    DocRow(cells = listOf("`radio.ringWidth`", "외곽 링 보더 두께")),
    DocRow(cells = listOf("`radio.dotScale`", "내부 점의 외곽 링 지름에 대한 비율(소수)")),
    DocRow(cells = listOf("`radio.gap`", "링과 라벨 사이의 간격")),
    DocRow(cells = listOf("`color.primary.default`", "선택 보더와 점 색상")),
    DocRow(cells = listOf("`color.border.base`", "미선택 보더 색상")),
    DocRow(cells = listOf("`color.disabled.{border,text}`", "비활성화 보더 / 점 / 글자 색상")),
    DocRow(cells = listOf("`color.text.primary`", "라벨 색상")),
    DocRow(cells = listOf("`typography.fontSize.base`", "라벨 폰트 크기")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-unchecked-emits-onchange`", "미선택 탭이 onChange(true)를 트리거", "`LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test)")),
    DocRow(cells = listOf("`tap-checked-keeps-checked`", "선택된 상태의 탭은 트리거하지 않음", "`LhRadioTest.tap_checked_does_not_emit`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "비활성화 상태는 트리거하지 않음", "`LhRadioTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "선택 상태가 주요 토큰을 소비", "`LhRadioDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "미선택이 보더 토큰을 소비", "`LhRadioDefaultsTest.unchecked_uses_border_token`")),
    DocRow(cells = listOf("`small-size-uses-small-token`", "소형이 small 크기 토큰을 소비", "`LhRadioDefaultsTest.small_size_uses_small_token`")),
  ),
  commands = listOf(

    "./gradlew :components:radio:test                              # JVM 부분",
    "./gradlew :components:radio:connectedAndroidTest             # 인터랙션 부분(기기 필요)",
  ),
)
