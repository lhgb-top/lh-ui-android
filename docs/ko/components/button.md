# Button 버튼

기본적인 액션 버튼입니다. 즉시 액션을 트리거하며, 유형, 크기, 로딩, 비활성화, 아이콘, 중국풍 문양 배경을 지원합니다.

> 계약 출처: [`lh-ui-core/specs/button.json`](https://www.lhgb.top/lhui/core/specs/button) ·
> 골든 케이스: [`cases/button.json`](https://www.lhgb.top/lhui/core/cases/button) (8건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 유형

6가지 유형이 배경색과 글자색을 결정합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 유형</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn">기본 버튼</button>
      <button class="sim-btn sim-btn--primary">주요 버튼</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--success">성공 버튼</button>
      <button class="sim-btn sim-btn--warning">경고 버튼</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--danger">위험 버튼</button>
      <button class="sim-btn sim-btn--info">정보 버튼</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text("주요 버튼") }
```

## 크기

3단계 크기가 `button.height` / `paddingHorizontal` / `fontSize` / `iconSize` 토큰에 매핑됩니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--small">소형 버튼</button>
      <button class="sim-btn">중형 버튼</button>
      <button class="sim-btn sim-btn--large">대형 버튼</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## 상태

비활성화와 로딩 중 모두 `lhOnClick`을 트리거하지 않습니다. 둘 다 지정된 경우 **비활성화가 우선**합니다(골든 케이스 `disabled-precedes-loading`).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Button 상태</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">보통</button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>비활성화</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--loading" disabled>
        <span class="sim-btn__spinner"></span>로딩 중
      </button>
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>비활성화 우선</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## 아이콘과 문양 배경

아이콘 이름은 로컬 아이콘 체계에서 해석됩니다(`LhButtonIcons`, 현재 search / refresh / check / close를 지원하며, 미등록 이름은 그려지지 않습니다).
문양 배경은 `color.decoration` 토큰을 소비하며, 배경색 위·글자 아래에 겹쳐져 대비에 영향을 주지 않습니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>아이콘과 문양</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary"><span class="sim-btn__icon"></span>검색</button>
      <button class="sim-btn"><span class="sim-btn__icon"></span>기본</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--pattern">주요 문양 배경</button>
      <button class="sim-btn sim-btn--pattern">기본 문양 배경</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## 라이트 / 다크 테마

사이트 우측 상단에서 테마를 전환하면 시뮬레이터 폰 프레임 내의 버튼 색상이 함께 바뀝니다. 실기기에서는 `LhTheme(isDark = true)`로 전환합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>다크 버튼</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary">주요 버튼</button>
      <button class="sim-btn">기본 버튼</button>
    </div>
    <div class="sim-row">
      <button class="sim-btn sim-btn--primary sim-btn--disabled" disabled>비활성화</button>
      <button class="sim-btn sim-btn--success">성공</button>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님. 라이트 / 다크 값은 모두 core의 토큰 스냅샷에서 가져옵니다</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `lhOnClick` | 탭 콜백. `disabled` 또는 `loading`이 true인 경우 트리거하지 않음 | `() -> Unit` | — |
| `modifier` | 수정자 | `Modifier` | `Modifier` |
| `type` | 버튼 유형. 배경색과 글자색을 결정 | `LhButtonType` | `Default` |
| `lhSize` | 버튼 크기. 크기 토큰에 매핑 | `LhButtonSize` | `Medium` |
| `disabled` | 비활성화 여부 | `Boolean` | `false` |
| `loading` | 로딩 중 여부(인디케이터를 표시하고 lhOnClick을 트리거하지 않음) | `Boolean` | `false` |
| `icon` | 아이콘 이름(로컬 아이콘 체계에서 해석) | `String?` | `null` |
| `bgPattern` | 중국풍 문양 배경을 그릴지 여부 | `Boolean` | `false` |
| `content` | 버튼 글자 내용(spec의 default 슬롯) | `@Composable () -> Unit` | — |

### LhButtonType

| 값 | 배경색 | 글자색 |
| --- | --- | --- |
| `Default` | `color.background.default` | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.onPrimary` |
| `Success` | `color.success.default` | `color.onPrimary` |
| `Warning` | `color.warning.default` | `color.onPrimary` |
| `Danger` | `color.danger.default` | `color.onPrimary` |
| `Info` | `color.info.default` | `color.onPrimary` |

### LhButtonSize

| 값 | height | paddingHorizontal | fontSize | iconSize |
| --- | --- | --- | --- | --- |
| `Small` | `button.height.small` (32dp) | `button.paddingHorizontal.small` (16dp) | `button.fontSize.small` (13sp) | `button.iconSize.small` (16dp) |
| `Medium` | `button.height.medium` (40dp) | `button.paddingHorizontal.medium` (20dp) | `button.fontSize.medium` (14sp) | `button.iconSize.medium` (18dp) |
| `Large` | `button.height.large` (48dp) | `button.paddingHorizontal.large` (24dp) | `button.fontSize.large` (16sp) | `button.iconSize.large` (20dp) |

비활성화 상태 오버라이드: 배경 `color.disabled.bg`, 글자 `color.disabled.text`, 보더 `color.disabled.border`.

## 토큰 매핑

버튼이 소비하는 토큰(`specs/button.json`의 `tokenRefs`와 1:1로 대응):

| 토큰 | 용도 |
| --- | --- |
| `button.height.{small,medium,large}` | 3단계 높이 |
| `button.paddingHorizontal.{small,medium,large}` | 3단계 가로 패딩 |
| `button.fontSize.{small,medium,large}` | 3단계 폰트 크기 |
| `button.iconSize.{small,medium,large}` | 3단계 아이콘 크기 |
| `button.borderRadius` / `button.borderWidth` | 코너 반경(`radius.base`를 따름)과 보더 두께 |
| `button.fontWeight` | 폰트 굵기(`typography.fontWeight.primary`를 따름) |
| `color.primary.default` / `color.onPrimary` | 주요 유형의 배경 / 글자 |
| `color.{success,warning,danger,info}.default` | 시맨틱 유형의 배경색 |
| `color.background.default` / `color.text.primary` / `color.border.base` | 기본 유형의 배경 / 글자 / 보더 |
| `color.disabled.{bg,text,border}` | 비활성화 상태의 세 가지 색 |
| `color.decoration` | 문양 색 |
| `shadow.lighter` | 기본 유형의 elevation |
| `motion.duration.fast` / `motion.easing.easeInOut` | 누름 피드백 모션 |

## 골든 케이스 매핑

`cases/button.json`의 8건 어서션과 로컬 테스트의 대응 관계:

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `click-emits-onclick` | 탭이 onClick을 1회 트리거 | `LhButtonTest.click_emits_onClick` (Compose UI Test) |
| `disabled-blocks-click` | 비활성화 상태는 트리거하지 않음 | `LhButtonTest.disabled_blocks_click` |
| `loading-blocks-click` | 로딩 상태는 트리거하지 않음 | `LhButtonTest.loading_blocks_click` |
| `disabled-precedes-loading` | 비활성화가 우선 | `LhButtonTest.disabled_precedes_loading_blocks_click` + `LhButtonDefaultsTest.disabled_precedes_loading` |
| `default-type-renders-text` | 기본 유형이 글자를 렌더링 | `LhButtonDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | 주요 유형이 주요 토큰을 소비 | `LhButtonDefaultsTest.primary_type_uses_primary_tokens` |
| `small-size-uses-small-tokens` | 소형이 small 크기 토큰을 소비 | `LhButtonDefaultsTest.small_size_uses_small_tokens` |
| `bgpattern-uses-decoration-token` | 문양 배경이 장식 색을 소비 | `LhButtonDefaultsTest.bgPattern_uses_decoration_token` |
| `icon-renders-icon-command` | 등록된 아이콘 이름은 벡터를 렌더링, 미등록 이름은 그리지 않음 | `LhButtonDefaultsTest.icon_renders_registered_vector` + `unknown_icon_name_resolves_to_null` |

```bash
./gradlew :components:button:test                        # JVM 부분
./gradlew :components:button:connectedAndroidTest        # 인터랙션 부분(기기 필요)
```
