package top.lhgb.ui.docs.pages.ko

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast 알림",
  lede = "가시성과 타이밍을 자체 관리하는 ink-colored pill 오버레이로, 가벼운 작업 피드백에 사용합니다. 사용자 조작을 차단하지 않습니다.",
  group = DocGroup.OVERLAY,
  contract = "계약 출처: `lh-ui-core/specs/toast.json` · 골든 케이스: `cases/toast.json` (6건, 상태 머신 4건 + 렌더링 2건, 모두 로컬 테스트로 번역됨)",
  sections = listOf(
    DocSection(
      title = "사용법",
      prose = "toast는 자체 상태 머신입니다. 상태는 `LhToastState`가 담당하고, 호스트는 `show(text)`로 표시, `hide()`로 숨깁니다. 타이밍이 만료되면 자동으로 숨겨지고 `onClose`를 트리거합니다. 컴포넌트는 보이는 동안 프레임 단위로 `tick`을 자체 구동하며, 호스트가 직접 tick을 호출해 타이밍을 인수취할 수도 있습니다.",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* 선택: 닫힘 기록 */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"저장됨\") }) { Text(\"실행\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "시맨틱 유형",
      prose = "`default`가 아닌 유형의 경우 글자 앞에 지름이 폰트 크기와 같은 시맨틱 색상 점을 그립니다.",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "위치",
      prose = "`lhPosition`은 `top` / `center` / `bottom` 중 하나이며, 양끝에 각각 `spacing.xl` 여백을 둡니다. 너비는 글자 측정으로 구해지고 사용 가능 너비의 80%를 상한으로 합니다.",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "알림 상태 머신(가시성, 글자, 타이밍을 담당)", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "시맨틱 유형. default가 아닌 경우 시맨틱 색상 점을 그림", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "사용 가능 영역 내의 위치", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "수정자(캡슐에 적용)", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("멤버", "설명"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "표시하고 타이밍을 재설정")),
        DocRow(cells = listOf("`hide()`", "즉시 숨김. onClose를 트리거하지 않음")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "타이밍을 진행. 만료 시 숨기고 onClose를 트리거한 뒤 true를 반환")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "현재 상태(관찰 가능)")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("값", "점 색상"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "점 없음")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "해당 시맨틱 색상")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "캡슐 최대 너비(240dp. 실제로는 사용 가능 너비의 80%와 이 값 중 작은 값)")),
    DocRow(cells = listOf("`toast.borderRadius`", "캡슐 코너 반경(`radius.base`를 따름)")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "캡슐 패딩")),
    DocRow(cells = listOf("`toast.fontSize`", "글자 폰트 크기(점 지름도 이 값과 동일)")),
    DocRow(cells = listOf("`toast.gap`", "점과 글자 사이의 간격")),
    DocRow(cells = listOf("`zIndex.toast`", "계층 시맨틱 정렬(Compose `Popup`이 윈도우 레벨 계층을 자체 관리)")),
    DocRow(cells = listOf("`color.background.overlay`(테마 레이어의 ink 매핑)", "캡슐 배경색")),
    DocRow(cells = listOf("`color.onPrimary`", "글자 색상")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "시맨틱 점 색상")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "show 이후 보이고 글자를 렌더링", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "만료 시 자동으로 숨겨지고 onClose를 트리거", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "타이밍 만료 전에는 계속 보이고 onClose를 트리거하지 않음", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide는 onClose를 트리거하지 않음", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "success 유형이 성공 색상을 소비", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "보이지 않을 때는 렌더링하지 않음", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # JVM 부분",
    "./gradlew :components:toast:connectedAndroidTest                 # 인터랙션 부분(기기 필요)",
  ),
)
