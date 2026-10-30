# Tag 태그

시맨틱 색상 태그로, 아웃라인, 완전 둥근 마크, 닫기 버튼을 지원합니다.

> 계약 출처: [`lh-ui-core/specs/tag.json`](https://www.lhgb.top/lhui/core/specs/tag) ·
> 골든 케이스: [`cases/tag.json`](https://www.lhgb.top/lhui/core/cases/tag) (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 유형

6가지 시맨틱 유형이 배경색과 글자색을 결정합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag 유형</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag">기본</span><span class="sim-tag sim-tag--success">성공</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--warning">경고</span><span class="sim-tag sim-tag--danger">위험</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--info">정보</span></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
LhTag(type = LhTagType.success) { Text("성공") }
```

## 아웃라인(plain)

`plain`은 투명 배경 + 시맨틱 색상 보더와 글자를 사용합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag plain</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--plain">주요</span><span class="sim-tag sim-tag--plain sim-tag--success">성공</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--plain sim-tag--danger">위험</span></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## 크기와 마크

3단계 크기가 `tag.height` / `tag.paddingHorizontal` / `tag.fontSize` 토큰에 매핑됩니다. `mark`는 높이의 절반에 해당하는 완전한 코너 반경을 사용합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Tag 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row"><span class="sim-tag sim-tag--small">small</span><span class="sim-tag">medium</span><span class="sim-tag sim-tag--large">large</span></div>
    <div class="sim-row"><span class="sim-tag sim-tag--mark">마크</span><span class="sim-tag sim-tag--success sim-tag--mark">성공 마크</span></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `text` | 태그 글자(편의 작성법, `content`와 택일) | `String?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |
| `type` | 시맨틱 유형 | `LhTagType` | `Default` |
| `lhSize` | 크기 | `LhTagSize` | `Medium` |
| `plain` | 아웃라인 여부(투명 배경 + 시맨틱 색상 보더) | `Boolean` | `false` |
| `round` | 완전 둥근 코너 여부(높이의 절반) | `Boolean` | `false` |
| `closable` | 닫기 버튼 표시 여부 | `Boolean` | `false` |
| `onClose` | 닫기 콜백. `closable`이 false인 경우 트리거하지 않음 | `(() -> Unit)?` | `null` |
| `content` | 태그 내용(text를 덮어씀, spec의 default 슬롯) | `@Composable (() -> Unit)?` | `null` |

### LhTagType

| 값 | 배경색(plain 아님) | 보더/글자(plain) |
| --- | --- | --- |
| `Default` | `color.background.default` + `color.border.base` 보더 | `color.text.primary` |
| `Primary` | `color.primary.default` | `color.primary.default` |
| `Success` | `color.success.default` | `color.success.default` |
| `Warning` | `color.warning.default` | `color.warning.default` |
| `Danger` | `color.danger.default` | `color.danger.default` |
| `Info` | `color.info.default` | `color.info.default` |

### LhTagSize

| 값 | height | paddingHorizontal | fontSize |
| --- | --- | --- | --- |
| `Small` | `tag.height.small` (20dp) | `tag.paddingHorizontal.small` (8dp) | `tag.fontSize.small` (12sp) |
| `Medium` | `tag.height.medium` (24dp) | `tag.paddingHorizontal.medium` (10dp) | `tag.fontSize.medium` (14sp) |
| `Large` | `tag.height.large` (28dp) | `tag.paddingHorizontal.large` (12dp) | `tag.fontSize.large` (16sp) |

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `tag.height.{small,medium,large}` | 3단계 높이 |
| `tag.paddingHorizontal.{small,medium,large}` | 3단계 가로 패딩 |
| `tag.fontSize.{small,medium,large}` | 3단계 폰트 크기 |
| `tag.borderRadius` / `tag.borderWidth` | 코너 반경과 보더 두께 |
| `color.{primary,success,warning,danger,info}.default` | 시맨틱 배경 / 보더 / 글자 색 |
| `color.onPrimary` | 일반 글자 이외의 색 |
| `color.disabled.{bg,text,border}` | 비활성화 상태의 세 가지 색 |
| `spacing.xs` | 닫기 버튼과 글자 사이의 간격 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `default-type-renders-text` | 기본 유형이 중성 배경 + 주 글자색 + 기본 보더를 사용 | `LhTagDefaultsTest.default_type_uses_neutral_tokens` |
| `primary-type-uses-primary-tokens` | 주요 유형이 주요 토큰을 소비 | `LhTagDefaultsTest.primary_type_uses_primary_tokens` |
| `plain-uses-bg-tokens` | plain이 시맨틱 옅은 배경 토큰을 소비 | `LhTagDefaultsTest.plain_uses_bg_tokens` |
| `small-size-uses-small-tokens` | 소형이 small 토큰을 소비(역어서션으로 large가 적용되지 않았음을 확인) | `LhTagDefaultsTest.small_size_uses_small_tokens` |
| `round-makes-radius-half-height` | round의 코너 반경이 높이의 절반 | `LhTagDefaultsTest.round_makes_radius_half_height` |
| `closable-tap-emits-onclose` | 닫기 탭이 onClose를 트리거 | `LhTagTest.closable_tap_emits_onClose` (Compose UI Test) |

```bash
./gradlew :components:tag:test                             # JVM 부분
./gradlew :components:tag:connectedAndroidTest             # 인터랙션 부분(기기 필요)
```
