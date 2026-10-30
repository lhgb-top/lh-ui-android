# Picker 피커

단일 열의 하단 휠 피커입니다. 스크롤이 멈추면 중앙 인디케이터에 가장 가까운 항목이 선택 항목이 됩니다.

> 계약 출처: [`lh-ui-core/specs/picker.json`](https://www.lhgb.top/lhui/core/specs/picker) ·
> 골든 케이스: [`cases/picker.json`](https://www.lhgb.top/lhui/core/cases/picker) (8건, 클램프와 토큰 매핑은 JVM 단위 테스트로, 스크롤과 버튼 인터랙션은 Compose UI Test로 번역됨)

## 사용법

하단에서 올라옵니다. 상단에 마스크, 하단에 패널(상단 코너 둥글게)이 위치합니다. 툴바는 "취소 / 제목 / 확인"의 3단 구성입니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Picker</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-picker">
      <div class="sim-picker__toolbar">
        <button class="sim-btn sim-btn--text">취소</button>
        <div class="sim-picker__toolbar-title">선택하세요</div>
        <button class="sim-btn sim-btn--text sim-btn--primary">확인</button>
      </div>
      <div class="sim-picker__wheel">
        <div class="sim-picker__item">옵션 1</div>
        <div class="sim-picker__item sim-picker__item--selected">옵션 2</div>
        <div class="sim-picker__item">옵션 3</div>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
var visible by remember { mutableStateOf(false) }
var value by remember { mutableStateOf(0) }
val options = listOf("옵션 1", "옵션 2", "옵션 3")
LhPicker(
  visible = visible,
  options = options,
  onValueChange = { value = it },
  onConfirm = { value = it; visible = false },
  onCancel = { visible = false },
  onClose = { visible = false },
  value = value,
  title = "선택하세요",
)
```

## 선택 메커니즘

휠은 스크롤 가능한 리스트이며, 각 항목의 높이는 `picker.itemHeight`입니다. 중앙 인디케이터는 두 개의 가로선으로, 간격 = itemHeight입니다.
스크롤이 멈추면 스크롤한 거리를 반올림해 선택 인덱스를 구하고, 변경 시 `onValueChange`를 트리거합니다.
`value`가 범위를 벗어나면 `LhPickerDefaults.clamp`가 후보 범위 내로 클램프합니다.

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `visible` | 표시 여부. false인 경우 아무 내용도 렌더링하지 않음 | `Boolean` | — |
| `options` | 후보 항목 텍스트 배열(단일 열) | `List<String>` | — |
| `onValueChange` | 휠이 멈추고 선택 항목이 변경되었을 때 트리거. 새 인덱스를 전달 | `(Int) -> Unit` | — |
| `onConfirm` | 확인 버튼 탭 시 트리거. 현재 선택 인덱스를 전달 | `(Int) -> Unit` | — |
| `onCancel` | 취소 버튼 탭 시 트리거 | `() -> Unit` | — |
| `onClose` | 확인 / 취소 후 트리거. 호스트는 이에 따라 visible을 false로 설정 | `() -> Unit` | — |
| `value` | 선택 항목 인덱스(0부터 시작). 범위를 벗어나면 클램프 | `Int` | `0` |
| `title` | 툴바 중앙의 제목 | `String?` | `null` |
| `confirmText` | 확인 버튼 글자 | `String` | `"确认"` |
| `cancelText` | 취소 버튼 글자 | `String` | `"取消"` |
| `content` | 휠 내용 영역(기본 후보 렌더링을 덮어씀, spec의 default 슬롯) | `@Composable ((List<String>, Int) -> Unit)?` | `null` |
| `toolbar` | 툴바(기본 확인 / 제목 / 취소를 덮어씀, spec의 toolbar 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `listState` | 휠 리스트 상태(기본값은 `rememberLhPickerListState(value, options)`로 제공. 테스트 시 외부에서 주입 가능) | `LazyListState` | 좌측 참조 |
| `modifier` | 수정자(패널에 적용) | `Modifier` | `Modifier` |

테스트 훅: `rememberLhPickerListState(value, options)`는 `value` 기준으로 초기 배치된 `LazyListState`를 반환합니다.
계측 테스트에서 `listState.dispatchRawDelta(...)`로 결정적으로 스크롤을 구동할 수 있습니다.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `picker.height` | 패널 높이(260dp) |
| `picker.itemHeight` | 각 항목의 높이(40dp, 중앙 인디케이터 간격이기도 함) |
| `picker.toolbarHeight` | 툴바 높이(44dp) |
| `picker.itemFontSize` | 후보 항목 폰트 크기 |
| `picker.borderRadius` | 패널 상단 코너 반경 |
| `picker.paddingHorizontal` | 패널 가로 패딩(`spacing.md`를 따름) |
| `zIndex.modal` / `zIndex.modalBackdrop` | 계층 시맨틱 정렬(Compose `Popup`이 윈도우 레벨 계층을 자체 관리) |
| `color.background.overlay` | 마스크 색상 |
| `color.background.default` | 패널 배경색 |
| `color.text.primary` / `color.text.regular` | 제목 / 후보 항목 색상 |
| `color.border.lighter` | 중앙 인디케이터 선 색상 |
| `color.primary.default` | 확인 버튼 색상 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `confirm-emits-onconfirm-with-value` | 확인이 현재 인덱스 + onClose를 트리거 | `LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | 확인 후 onClose를 트리거 | 동일 |
| `cancel-emits-oncancel` | 취소가 onCancel + onClose를 트리거 | `LhPickerTest.cancel_emits_onCancel_and_onClose` |
| `scroll-emits-onvaluechange` | 스크롤이 선택을 변경하고 onValueChange를 트리거 | `LhPickerTest.scroll_emits_onValueChange` |
| `clamp-negative-value-to-zero` | value가 음수인 경우 0으로 클램프 | `LhPickerDefaultsTest.clamp_negative_value_to_zero` |
| `clamp-overflow-value-to-last` | value가 범위를 벗어나면 마지막 항목으로 클램프 | `LhPickerDefaultsTest.clamp_overflow_value_to_last` |
| `title-renders-text` | 제목이 글자를 렌더링 | `LhPickerTest.title_renders_text` |
| `hidden-not-rendered` | 보이지 않을 때 렌더링하지 않음 | `LhPickerTest.hidden_is_not_rendered` |

```bash
./gradlew :components:picker:test                                # JVM 부분
./gradlew :components:picker:connectedAndroidTest               # 인터랙션 부분(기기 필요)
```
