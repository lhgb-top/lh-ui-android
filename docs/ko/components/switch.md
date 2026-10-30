# Switch 스위치

캡슐 형태의 트랙 + 원형 썸으로 이루어진 두 상태 토글 컨트롤입니다. 로딩 상태 플레이스홀더를 지원합니다.

> 계약 출처: [`lh-ui-core/specs/switch.json`](https://www.lhgb.top/lhui/core/specs/switch) ·
> 골든 케이스: [`cases/switch.json`](https://www.lhgb.top/lhui/core/cases/switch) (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 상태

켜기는 주요색 트랙, 끄기는 보더색 트랙을 사용합니다. 썸 지름 = 높이 - 2 × `switch.gap`이며, 이동 애니메이션은 `motion.duration.base`를 사용합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-switch"></span>꺼짐</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>켜짐</div>
    <div class="sim-row"><span class="sim-switch sim-switch--disabled"></span>비활성화(꺼짐)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked sim-switch--disabled"></span>비활성화(켜짐)</div>
    <div class="sim-row"><span class="sim-switch sim-switch--checked"></span>로딩 중(썸 위치에 로딩 링을 표시, 탭에 응답하지 않음)</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
var on by remember { mutableStateOf(false) }
LhSwitch(checked = on, onChange = { on = it })
```

## 크기

3단계 크기가 `switch.width` / `switch.height` 토큰에 매핑됩니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Switch 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-switch sim-switch--small sim-switch--checked"></span>
      <span class="sim-switch sim-switch--checked"></span>
      <span class="sim-switch sim-switch--large sim-switch--checked"></span>
    </div>
    <div class="sim-row">
      <span class="sim-switch sim-switch--small"></span>
      <span class="sim-switch"></span>
      <span class="sim-switch sim-switch--large"></span>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `checked` | 켜짐 여부(필수) | `Boolean` | — |
| `onChange` | 스위치 상태 변경 콜백. 새 스위치 상태를 전달 | `(Boolean) -> Unit` | — |
| `disabled` | 비활성화 여부 | `Boolean` | `false` |
| `loading` | 로딩 중 여부(썸 위치에 로딩 링을 표시, 탭에 응답하지 않음) | `Boolean` | `false` |
| `lhSize` | 크기 | `LhSwitchSize` | `Medium` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

### LhSwitchSize

| 값 | width | height |
| --- | --- | --- |
| `Small` | `switch.width.small` (36dp) | `switch.height.small` (20dp) |
| `Medium` | `switch.width.medium` (44dp) | `switch.height.medium` (24dp) |
| `Large` | `switch.width.large` (52dp) | `switch.height.large` (28dp) |

트랙은 캡슐 형태(코너 반경은 높이의 절반)입니다. 탭 영역은 트랙 전체에 해당하며, 탭하면 스위치 상태를 반전하고 `onChange`를 트리거합니다.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `switch.width.{small,medium,large}` | 3단계 트랙 너비 |
| `switch.height.{small,medium,large}` | 3단계 트랙 높이 |
| `switch.gap` | 썸과 트랙 사이의 여백(썸 지름 = 높이 - 2 × gap) |
| `color.primary.default` | 켜짐 트랙 색상 |
| `color.border.base` | 꺼짐 트랙 색상 |
| `color.onPrimary` | 썸 색상 |
| `color.disabled.{bg,text}` | 비활성화 트랙 / 썸 색상 |
| `motion.duration.base` / `motion.easing.easeInOut` | 썸 이동 애니메이션 |
| `loading.strokeWidth` | 로딩 링 보더 두께(재사용) |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | 탭이 상태를 반전하고 onChange를 내보냄 | `LhSwitchTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-turns-off` | 켜진 상태에서 탭하면 꺼짐 | `LhSwitchTest.tap_checked_emits_false` |
| `disabled-blocks-change` | 비활성화 상태는 트리거하지 않음 | `LhSwitchTest.disabled_blocks_change` |
| `loading-blocks-change` | 로딩 상태는 트리거하지 않음 | `LhSwitchTest.loading_blocks_change` |
| `checked-uses-primary-token` | 켜짐이 주요 토큰을 소비 | `LhSwitchDefaultsTest.checked_uses_primary_token` |
| `unchecked-uses-border-token` | 꺼짐이 보더 토큰을 소비 | `LhSwitchDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:switch:test                             # JVM 부분
./gradlew :components:switch:connectedAndroidTest            # 인터랙션 부분(기기 필요)
```
