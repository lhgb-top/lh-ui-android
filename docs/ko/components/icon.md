# Icon 아이콘

아이콘 베이스 컴포넌트입니다. 다른 컴포넌트(cell / input / navbar 등)의 아이콘은 모두 이 컴포넌트의 로컬 레지스트리를 거쳐 렌더링됩니다.

> 계약 출처: [`lh-ui-core/specs/icon.json`](https://www.lhgb.top/lhui/core/specs/icon) ·
> 골든 케이스: [`cases/icon.json`](https://www.lhgb.top/lhui/core/cases/icon) (5건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 크기와 색조

3단계 크기가 `icon.size.*` 토큰에 매핑됩니다. 색조 `default`는 `color.text.primary`, 나머지는 시맨틱 색을 사용합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-icon sim-icon--small"></span>
      <span class="sim-icon"></span>
      <span class="sim-icon sim-icon--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-primary)">기본 색(text.primary)</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
LhIcon(name = "search", lhSize = LhIconSize.large, tone = LhIconTone.primary)
```

## 시맨틱 색조

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Icon 색조</span></div>
  <div class="sim-screen">
    <div class="sim-row" style="color: var(--lh-color-text-primary)"><span class="sim-icon"></span>default</div>
    <div class="sim-row" style="color: var(--lh-color-primary-default)"><span class="sim-icon"></span>primary</div>
    <div class="sim-row" style="color: var(--lh-color-success-default)"><span class="sim-icon"></span>success</div>
    <div class="sim-row" style="color: var(--lh-color-warning-default)"><span class="sim-icon"></span>warning</div>
    <div class="sim-row" style="color: var(--lh-color-danger-default)"><span class="sim-icon"></span>danger</div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## 아이콘 레지스트리

`LhIcons`는 로컬 아이콘 이름 → Material `ImageVector`의 레지스트리이며, 미등록 아이콘 이름은 `null`을 반환합니다(그려지지 않고, 예외도 발생하지 않음):

| 아이콘 이름 | Material 벡터 |
| --- | --- |
| `search` / `refresh` / `check` / `check-circle` | Search / Refresh / Check / CheckCircle |
| `close` / `clear` / `star` / `warning` | Close / Clear / Star / Warning |
| `info` / `plus` / `minus` | Info / Add / Remove |
| `arrow-left` / `arrow-right` / `chevron-right` | ArrowBack / ArrowForward / ChevronRight |

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `name` | 아이콘 이름(`LhIcons` 레지스트리에서 해석) | `String` | — |
| `lhSize` | 아이콘 크기 | `LhIconSize` | `Medium` |
| `tone` | 시맨틱 색조 | `LhIconTone` | `Default` |
| `contentDescription` | 접근성 설명. 기본값은 아이콘 이름, `null`을 명시적으로 전달하면 장식용 아이콘으로 선언 | `String?` | `name` |
| `lhOnClick` | 탭 콜백. null인 경우 탭할 수 없음 | `(() -> Unit)?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

### LhIconSize

| 값 | 크기 토큰 |
| --- | --- |
| `Small` | `icon.size.small` (16dp) |
| `Medium` | `icon.size.medium` (20dp) |
| `Large` | `icon.size.large` (24dp) |

### LhIconTone

| 값 | 색상 토큰 |
| --- | --- |
| `Default` | `color.text.primary` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `icon.size.{small,medium,large}` | 3단계 크기 |
| `color.text.primary` | default 색조 |
| `color.{primary,success,warning,danger,info}.default` | 시맨틱 색조 |
| `color.text.{regular,secondary}` | regular / secondary 색조 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `click-emits-onclick` | 탭이 onClick을 트리거 | `LhIconTest.click_emits_onClick` (Compose UI Test) |
| `default-tone-uses-text-primary` | 기본 색조가 주 글자색 토큰을 사용 | `LhIconDefaultsTest.default_tone_uses_text_primary_token` |
| `primary-tone-uses-primary-token` | primary 색조가 주요 색 토큰을 사용 | `LhIconDefaultsTest.primary_tone_uses_primary_token` |
| `small-size-uses-small-token` | 소형이 small 크기 토큰을 사용(역어서션으로 large가 적용되지 않았음을 확인) | `LhIconDefaultsTest.small_size_uses_small_token` |
| `render-emits-icon-command` | 등록된 아이콘 이름은 벡터로 해석, 미등록 이름은 그리지 않음 | `LhIconDefaultsTest.registered_name_resolves_to_vector` + `LhIconTest.unknown_name_is_safe` |

```bash
./gradlew :components:icon:test                            # JVM 부분
./gradlew :components:icon:connectedAndroidTest            # 인터랙션 부분(기기 필요)
```
