# Radio 라디오 버튼

제어되는 라디오 버튼입니다. 선택된 상태에서 다시 탭해도 선택이 취소되지 않습니다(라디오 시맨틱).

> 계약 출처: [`lh-ui-core/specs/radio.json`](https://www.lhgb.top/lhui/core/specs/radio) ·
> 골든 케이스: [`cases/radio.json`](https://www.lhgb.top/lhui/core/cases/radio) (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 상태와 라디오 그룹

컴포넌트 자체는 그룹 로직을 포함하지 않으며, 라디오 상호 배제는 호스트가 유지합니다(`value`로 그룹 내 항목을 식별).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-radio"><span class="sim-radio__ring"></span>미선택</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>선택됨</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--disabled"><span class="sim-radio__ring"></span>비활성화(미선택)</label></div>
    <div class="sim-row"><label class="sim-radio sim-radio--checked sim-radio--disabled"><span class="sim-radio__ring"></span>비활성화(선택)</label></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
var selected by remember { mutableStateOf("a") }
listOf("옵션 1" to "a", "옵션 2" to "b").forEach { (text, value) ->
  LhRadio(
    checked = selected == value,
    onChange = { selected = value },
    label = text,
    value = value,
  )
}
```

## 크기

3단계 링 크기가 `radio.size.*` 토큰에 매핑됩니다. 내부 점의 지름 = 크기 × `radio.dotScale`입니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Radio 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-radio sim-radio--small sim-radio--checked"><span class="sim-radio__ring"></span>small</label>
      <label class="sim-radio sim-radio--checked"><span class="sim-radio__ring"></span>medium</label>
      <label class="sim-radio sim-radio--large sim-radio--checked"><span class="sim-radio__ring"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `checked` | 선택 여부(필수) | `Boolean` | — |
| `onChange` | 미선택에서 선택으로 바뀔 때 트리거. `checked = true`를 전달 | `(Boolean) -> Unit` | — |
| `disabled` | 비활성화 여부 | `Boolean` | `false` |
| `label` | 라벨 글자 | `String?` | `null` |
| `value` | 라디오 값(그룹 내 식별자. 컴포넌트 자체는 그룹 로직을 포함하지 않음) | `String?` | `null` |
| `lhSize` | 링 크기 | `LhRadioSize` | `Medium` |
| `labelContent` | 라벨 내용(label을 덮어씀, spec의 default 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

### LhRadioSize

| 값 | 크기 토큰 | 내부 점 지름 |
| --- | --- | --- |
| `Small` | `radio.size.small` (16dp) | 16dp × `radio.dotScale` (0.5) |
| `Medium` | `radio.size.medium` (20dp) | 20dp × `radio.dotScale` |
| `Large` | `radio.size.large` (24dp) | 24dp × `radio.dotScale` |

탭 영역은 행 전체에 해당합니다. 미선택이고 비활성화되지 않은 경우에만 선택 상태로 바뀌고 `onChange(true)`를 트리거합니다.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `radio.size.{small,medium,large}` | 3단계 링 크기 |
| `radio.ringWidth` | 외곽 링 보더 두께 |
| `radio.dotScale` | 내부 점의 외곽 링 지름에 대한 비율(소수) |
| `radio.gap` | 링과 라벨 사이의 간격 |
| `color.primary.default` | 선택 보더와 점 색상 |
| `color.border.base` | 미선택 보더 색상 |
| `color.disabled.{border,text}` | 비활성화 보더 / 점 / 글자 색상 |
| `color.text.primary` | 라벨 색상 |
| `typography.fontSize.base` | 라벨 폰트 크기 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `tap-unchecked-emits-onchange` | 미선택 탭이 onChange(true)를 트리거 | `LhRadioTest.tap_unchecked_emits_onchange_true` (Compose UI Test) |
| `tap-checked-keeps-checked` | 선택된 상태의 탭은 트리거하지 않음 | `LhRadioTest.tap_checked_does_not_emit` |
| `disabled-blocks-change` | 비활성화 상태는 트리거하지 않음 | `LhRadioTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 선택 상태가 주요 토큰을 소비 | `LhRadioDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 미선택이 보더 토큰을 소비 | `LhRadioDefaultsTest.unchecked_uses_border_token` |
| `small-size-uses-small-token` | 소형이 small 크기 토큰을 소비 | `LhRadioDefaultsTest.small_size_uses_small_token` |

```bash
./gradlew :components:radio:test                              # JVM 부분
./gradlew :components:radio:connectedAndroidTest             # 인터랙션 부분(기기 필요)
```
