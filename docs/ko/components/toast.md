# Toast 알림

가시성과 타이밍을 자체 관리하는 ink-colored pill 오버레이로, 가벼운 작업 피드백에 사용합니다. 사용자 조작을 차단하지 않습니다.

> 계약 출처: [`lh-ui-core/specs/toast.json`](https://www.lhgb.top/lhui/core/specs/toast) ·
> 골든 케이스: [`cases/toast.json`](https://www.lhgb.top/lhui/core/cases/toast) (6건, 상태 머신 4건 + 렌더링 2건, 모두 로컬 테스트로 번역됨)

## 사용법

toast는 **자체 상태 머신**입니다. 상태는 `LhToastState`가 담당하고, 호스트는 `show(text)`로 표시, `hide()`로 숨깁니다. 타이밍이 만료되면 자동으로 숨겨지고 `onClose`를 트리거합니다. 컴포넌트는 보이는 동안 프레임 단위로 `tick`을 자체 구동하며, 호스트가 직접 tick을 호출해 타이밍을 인수취할 수도 있습니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast</span></div>
  <div class="sim-screen">
    <div class="sim-toast">작업 성공</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
val state = rememberLhToastState(onClose = { /* 선택: 닫힘 기록 */ })
Column {
  LhButton(lhOnClick = { state.show("저장됨") }) { Text("실행") }
  LhToast(state = state)
}
```

## 시맨틱 유형

`default`가 아닌 유형의 경우 글자 앞에 지름이 폰트 크기와 같은 시맨틱 색상 점을 그립니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 유형</span></div>
  <div class="sim-screen">
    <div class="sim-toast sim-toast--success"><span class="sim-toast__dot"></span>성공 알림</div>
    <div class="sim-toast sim-toast--warning"><span class="sim-toast__dot"></span>경고 알림</div>
    <div class="sim-toast sim-toast--danger"><span class="sim-toast__dot"></span>실패 알림</div>
    <div class="sim-toast sim-toast--primary"><span class="sim-toast__dot"></span>주요 알림</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## 위치

`lhPosition`은 `top` / `center` / `bottom` 중 하나이며, 양끝에 각각 `spacing.xl` 여백을 둡니다. 너비는 글자 측정으로 구해지고 사용 가능 너비의 80%를 상한으로 합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Toast 위치</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-toast sim-toast--top">상단</div>
    <div class="sim-toast sim-toast--bottom">하단</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `state` | 알림 상태 머신(가시성, 글자, 타이밍을 담당) | `LhToastState` | — |
| `type` | 시맨틱 유형. default가 아닌 경우 시맨틱 색상 점을 그림 | `LhToastType` | `default` |
| `lhPosition` | 사용 가능 영역 내의 위치 | `LhToastPosition` | `center` |
| `modifier` | 수정자(캡슐에 적용) | `Modifier` | `Modifier` |

### LhToastState

| 멤버 | 설명 |
| --- | --- |
| `show(text: String)` | 표시하고 타이밍을 재설정 |
| `hide()` | 즉시 숨김. **onClose를 트리거하지 않음** |
| `tick(deltaMs: Int): Boolean` | 타이밍을 진행. 만료 시 숨기고 onClose를 트리거한 뒤 true를 반환 |
| `visible` / `text` / `duration` | 현재 상태(관찰 가능) |

팩토리: `rememberLhToastState(duration = 2000, onClose = {})`.

### LhToastType / LhToastPosition

| 값 | 점 색상 |
| --- | --- |
| `default` | 점 없음 |
| `primary` / `success` / `warning` / `danger` / `info` | 해당 시맨틱 색상 |

`LhToastPosition`: `top` / `center` / `bottom`.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `toast.maxWidth` | 캡슐 최대 너비(240dp. 실제로는 사용 가능 너비의 80%와 이 값 중 작은 값) |
| `toast.borderRadius` | 캡슐 코너 반경(`radius.base`를 따름) |
| `toast.paddingHorizontal` / `toast.paddingVertical` | 캡슐 패딩 |
| `toast.fontSize` | 글자 폰트 크기(점 지름도 이 값과 동일) |
| `toast.gap` | 점과 글자 사이의 간격 |
| `zIndex.toast` | 계층 시맨틱 정렬(Compose `Popup`이 윈도우 레벨 계층을 자체 관리) |
| `color.background.overlay`(테마 레이어의 ink 매핑) | 캡슐 배경색 |
| `color.onPrimary` | 글자 색상 |
| `color.{primary,success,warning,danger,info}.default` | 시맨틱 점 색상 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `show-makes-visible` | show 이후 보이고 글자를 렌더링 | `LhToastDefaultsTest.show_makes_visible_and_renders_text` |
| `auto-hides-after-duration` | 만료 시 자동으로 숨겨지고 onClose를 트리거 | `LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration` |
| `tick-before-duration-keeps-visible` | 타이밍 만료 전에는 계속 보이고 onClose를 트리거하지 않음 | `LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit` |
| `hide-is-silent` | hide는 onClose를 트리거하지 않음 | `LhToastDefaultsTest.hide_is_silent` |
| `success-type-uses-success-token` | success 유형이 성공 색상을 소비 | `LhToastDefaultsTest.success_type_uses_success_tokens` |
| `hidden-emits-no-commands` | 보이지 않을 때는 렌더링하지 않음 | `LhToastTest.hidden_toast_renders_nothing` |

```bash
./gradlew :components:toast:test                                  # JVM 부분
./gradlew :components:toast:connectedAndroidTest                 # 인터랙션 부분(기기 필요)
```
