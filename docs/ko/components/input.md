# Input 입력 상자

제어되는 한 줄 입력 상자입니다. 크기, 비활성화, 읽기 전용, 지우기, 비밀번호 마스킹, 최대 길이를 지원합니다.

> 계약 출처: [`lh-ui-core/specs/input.json`](https://www.lhgb.top/lhui/core/specs/input) ·
> 골든 케이스: [`cases/input.json`](https://www.lhgb.top/lhui/core/cases/input) (8건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 기본 사용법

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input" placeholder="내용을 입력하세요" /></div>
    <div class="sim-row"><input class="sim-input" value="입력된 내용" /></div>
    <div class="sim-row"><input class="sim-input sim-input--disabled" value="비활성화 상태" disabled /></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
var value by remember { mutableStateOf("") }
LhInput(
  value = value,
  placeholder = "내용을 입력하세요",
  onValueChange = { value = it },
)
```

## 크기와 포커스

3단계 크기가 `input.height` / `input.paddingHorizontal` / `input.fontSize` 토큰에 매핑됩니다. 포커스 시 보더는 `color.focus`를 사용하고 `input.focusRingWidth`까지 두꺼워집니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Input 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row"><input class="sim-input sim-input--small" placeholder="small" /></div>
    <div class="sim-row"><input class="sim-input" placeholder="medium(포커스 상태 데모)" /></div>
    <div class="sim-row"><input class="sim-input sim-input--large" placeholder="large" /></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `value` | 제어되는 값(필수) | `String` | — |
| `onValueChange` | 값 변경 콜백(필수) | `(String) -> Unit` | — |
| `placeholder` | 플레이스홀더 글자 | `String?` | `null` |
| `type` | 입력 유형(일반 텍스트 / 비밀번호) | `LhInputType` | `Text` |
| `lhSize` | 크기 | `LhInputSize` | `Medium` |
| `disabled` | 비활성화 여부 | `Boolean` | `false` |
| `readonly` | 읽기 전용 여부(편집은 불가하되 비활성화 스타일은 적용하지 않음) | `Boolean` | `false` |
| `clearable` | 지우기 버튼 표시 여부 | `Boolean` | `false` |
| `maxlength` | 최대 길이(초과 시 잘림) | `Int` | `Int.MAX_VALUE` |
| `lhOnFocus` / `lhOnBlur` | 포커스 / 포커스 아웃 콜백 | `(() -> Unit)?` | `null` |
| `prefix` / `suffix` | 접두 / 접미 슬롯 | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

### LhInputSize

| 값 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `input.height.small` (32dp) | `input.paddingHorizontal.small` (12dp) | `input.fontSize.small` (13sp) |
| `Medium` | `input.height.medium` (40dp) | `input.paddingHorizontal.medium` (16dp) | `input.fontSize.medium` (14sp) |
| `Large` | `input.height.large` (48dp) | `input.paddingHorizontal.large` (20dp) | `input.fontSize.large` (16sp) |

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `input.height.{small,medium,large}` | 3단계 높이 |
| `input.paddingHorizontal.{small,medium,large}` | 3단계 가로 패딩 |
| `input.fontSize.{small,medium,large}` | 3단계 폰트 크기 |
| `input.borderRadius` / `input.borderWidth` / `input.focusRingWidth` | 코너 반경 / 보더 두께 / 포커스 보더 두께 |
| `input.clearIconSize` | 지우기 아이콘 크기 |
| `color.focus` | 포커스 보더 색상 |
| `color.text.placeholder` | 플레이스홀더 글자 색상 |
| `color.border.base` | 기본 보더 색상 |
| `color.disabled.{bg,text,border}` | 비활성화 상태의 세 가지 색 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `input-emits-onvaluechange` | 입력이 onValueChange를 트리거 | `LhInputTest.input_emits_onValueChange` (Compose UI Test) |
| `clearable-emits-empty-value` | 지우기 버튼이 빈 값을 내보냄 | `LhInputTest.clearable_emits_empty_value` |
| `disabled-blocks-input` | 비활성화 상태에서는 입력 불가 | `LhInputTest.disabled_blocks_input` |
| `readonly-blocks-input` | 읽기 전용 상태에서는 입력 불가 | `LhInputTest.readonly_blocks_input` |
| `placeholder-uses-placeholder-token` | 플레이스홀더 색이 플레이스홀더 토큰을 사용 | `LhInputDefaultsTest.placeholder_uses_placeholder_token` |
| `focus-uses-focus-token` | 포커스가 포커스 토큰을 소비(포커스 아웃 시 중성 보더 사용) | `LhInputDefaultsTest.focus_uses_focus_tokens` + `unfocused_uses_neutral_tokens` |
| `small-size-uses-small-tokens` | 소형이 small 크기 토큰을 소비 | `LhInputDefaultsTest.small_size_uses_small_tokens` |
| `maxlength-truncates-input` | 최대 길이를 초과한 입력은 잘림 | `LhInputTest.maxlength_truncates_input` |

```bash
./gradlew :components:input:test                              # JVM 부분
./gradlew :components:input:connectedAndroidTest              # 인터랙션 부분(기기 필요)
```
