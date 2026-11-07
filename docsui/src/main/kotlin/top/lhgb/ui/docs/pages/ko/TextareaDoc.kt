package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocTextareaBasicPreview
import top.lhgb.ui.docs.previews.DocTextareaCounterPreview

val textareaDoc = DocEntry(
  id = "textarea",
  title = "Textarea 텍스트 영역",
  lede = "제어되는 여러 줄 입력 상자입니다. 행 수, 글자 수 카운트, 최대 길이를 지원합니다.",
  group = DocGroup.FORM,
  contract = "계약 출처: `lh-ui-core/specs/textarea.json` · 골든 케이스: `cases/textarea.json` (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)",
  sections = listOf(
    DocSection(
      title = "기본 사용법",
      prose = "최소 높이 = 행 수 × 줄 높이 × 폰트 크기. 이를 초과하면 세로로 스크롤됩니다.",
      preview = { DocTextareaBasicPreview() },
      code = listOf(
        "var value by remember { mutableStateOf(\"\") }",
        "LhTextarea(",
        "  value = value,",
        "  rows = 3,",
        "  showCount = true,",
        "  maxlength = 200,",
        "  onValueChange = { value = it },",
        ")",
      ),
    ),
    DocSection(
      title = "글자 수 카운트",
      prose = "`showCount`가 true인 경우 우측 하단에 카운트를 렌더링합니다(`textarea.countFontSize`, 색상 `text.secondary`). `maxlength`는 입력을 자릅니다.",
      preview = { DocTextareaCounterPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`value`", "제어되는 값(필수)", "`String`", "—")),
    DocRow(cells = listOf("`onValueChange`", "값 변경 콜백(필수)", "`(String) -> Unit`", "—")),
    DocRow(cells = listOf("`placeholder`", "플레이스홀더 글자", "`String?`", "`null`")),
    DocRow(cells = listOf("`rows`", "행 수(최소 높이를 결정)", "`Int`", "`3`")),
    DocRow(cells = listOf("`disabled`", "비활성화 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`readonly`", "읽기 전용 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`showCount`", "글자 수 카운트 표시 여부", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`maxlength`", "최대 길이(초과 시 잘림)", "`Int`", "`Int.MAX_VALUE`")),
    DocRow(cells = listOf("`lhOnFocus` / `lhOnBlur`", "포커스 / 포커스 아웃 콜백", "`(() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "수정자", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`textarea.paddingVertical` / `textarea.paddingHorizontal`", "패딩(`spacing.md`)")),
    DocRow(cells = listOf("`textarea.fontSize`", "폰트 크기(`typography.fontSize.base`)")),
    DocRow(cells = listOf("`textarea.borderRadius` / `textarea.borderWidth` / `textarea.focusRingWidth`", "코너 반경 / 보더 두께 / 포커스 보더 두께")),
    DocRow(cells = listOf("`textarea.countFontSize`", "카운트 폰트 크기(`typography.fontSize.extraSmall`)")),
    DocRow(cells = listOf("`textarea.minHeight`", "최소 높이 기준")),
    DocRow(cells = listOf("`typography.lineHeight.primary`", "줄 높이(행 수 환산)")),
    DocRow(cells = listOf("`color.text.{primary,secondary,placeholder}`", "글자 / 카운트 / 플레이스홀더 색상")),
    DocRow(cells = listOf("`color.focus` / `color.border.base`", "포커스 / 기본 보더 색상")),
    DocRow(cells = listOf("`color.disabled.{bg,text,border}`", "비활성화 상태의 세 가지 색")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`input-emits-onvaluechange`", "입력이 onValueChange를 트리거", "`LhTextareaTest.input_emits_onValueChange` (Compose UI Test)")),
    DocRow(cells = listOf("`disabled-blocks-input`", "비활성화 상태에서는 입력 불가", "`LhTextareaTest.disabled_blocks_input`")),
    DocRow(cells = listOf("`showcount-renders-count`", "카운트가 \"n / max\"를 렌더링", "`LhTextareaTest.showcount_renders_count`")),
    DocRow(cells = listOf("`showcount-without-maxlength-renders-length-only`", "maxlength가 없을 때는 길이만 렌더링", "`LhTextareaTest.showcount_without_maxlength_renders_length_only`")),
    DocRow(cells = listOf("`focus-uses-focus-token`", "포커스가 포커스 토큰을 소비", "`LhTextareaDefaultsTest.focus_uses_focus_tokens`")),
    DocRow(cells = listOf("`maxlength-truncates-input`", "최대 길이를 초과한 입력은 잘림", "`LhTextareaTest.maxlength_truncates_input`")),
  ),
  commands = listOf(

    "./gradlew :components:textarea:test                            # JVM 부분",
    "./gradlew :components:textarea:connectedAndroidTest           # 인터랙션 부분(기기 필요)",
  ),
)
