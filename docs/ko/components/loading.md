# Loading 로딩

로딩 인디케이터입니다. 원형 회전 애니메이션의 지속 시간은 `motion.duration.base`(밀리초)에서 가져옵니다.

> 계약 출처: [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> 골든 케이스: [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading) (5건, 순수 표시 컴포넌트로 JVM 단위 테스트로 번역됨)

## 크기와 색조

3단계 크기가 `loading.size.*` 토큰에 매핑되고, 보더 두께는 `loading.strokeWidth`, 색조는 시맨틱 색을 사용합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">로딩 중…</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `lhSize` | 인디케이터 지름 | `LhLoadingSize` | `Medium` |
| `tone` | 링 색상 | `LhLoadingTone` | `Primary` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

### LhLoadingSize

| 값 | 크기 토큰 |
| --- | --- |
| `Small` | `loading.size.small` (16dp) |
| `Medium` | `loading.size.medium` (20dp) |
| `Large` | `loading.size.large` (24dp) |

### LhLoadingTone

| 값 | 색상 토큰 |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `loading.size.{small,medium,large}` | 3단계 지름 |
| `loading.strokeWidth` | 링 보더 두께 |
| `color.text.regular` | default 색조 |
| `color.{primary,success,warning,danger,info}.default` | 시맨틱 색조 |
| `motion.duration.base` | 한 바퀴 회전에 걸리는 시간(단말에서는 밀리초 정수) |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `medium-size-uses-medium-token` | 중형이 medium 크기 토큰을 사용 | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | 대형이 large 크기 토큰을 사용 | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | primary 색조가 주요 색 토큰을 사용 | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | success 색조가 성공 색 토큰을 사용 | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | 원형 인디케이터 렌더링(보더 두께가 토큰을 소비) | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # JVM 부분(순수 표시, 인터랙션 케이스 없음)
```
