# Navbar 내비게이션 바

페이지 상단의 제목 표시줄입니다. 좌 / 중 / 우의 3단 레이아웃으로, 주로 뒤로 가기와 제목 표시에 사용합니다.

> 계약 출처: [`lh-ui-core/specs/navbar.json`](https://www.lhgb.top/lhui/core/specs/navbar) ·
> 골든 케이스: [`cases/navbar.json`](https://www.lhgb.top/lhui/core/cases/navbar) (6건, 토큰 매핑은 JVM 단위 테스트로, 좌우 탭은 Compose UI Test로 번역됨)

## 사용법

3단이 동일한 너비(각 `weight(1f)`)를 가지며, 제목은 사용 가능 너비 기준으로 수평 중앙에 놓입니다. 기본적으로 하단 구분선을 그립니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span>뒤로</div>
      <div class="sim-navbar__title">페이지 제목</div>
      <div class="sim-navbar__side sim-navbar__side--right">더보기</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
LhNavbar(
  title = "페이지 제목",
  leftIcon = "arrow-left",
  leftText = "뒤로",
  rightText = "더보기",
  onLeftClick = { /* 뒤로 가기 */ },
  onRightClick = { /* 더보기 */ },
)
```

## 좌우 내용

글자와 아이콘을 동시에 사용할 수 있습니다(아이콘이 왼쪽, 글자가 오른쪽, 간격 `spacing.xs`). **어느 한쪽에 내용이 없으면 탭에 응답하지 않습니다**
(골든 케이스 `no-left-content-no-onleftclick`: 해당 노드에 탭 가능하다는 의미조차 존재하지 않습니다).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Navbar 변형</span></div>
  <div class="sim-screen sim-screen--navbar">
    <div class="sim-navbar">
      <div class="sim-navbar__side"><span class="sim-icon"></span></div>
      <div class="sim-navbar__title">아이콘만</div>
      <div class="sim-navbar__side sim-navbar__side--right"></div>
    </div>
    <div class="sim-navbar sim-navbar--borderless">
      <div class="sim-navbar__side">글자</div>
      <div class="sim-navbar__title">구분선 없음</div>
      <div class="sim-navbar__side sim-navbar__side--right">편집</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `title` | 중앙 제목 | `String?` | `null` |
| `onLeftClick` | 좌측 영역 탭 시 트리거 | `() -> Unit` | `{}` |
| `onRightClick` | 우측 영역 탭 시 트리거 | `() -> Unit` | `{}` |
| `leftText` | 좌측 버튼 글자. leftIcon과 공존 가능 | `String?` | `null` |
| `leftIcon` | 좌측 아이콘 이름(로컬 아이콘 체계, 예: `"arrow-left"`) | `String?` | `null` |
| `rightText` | 우측 버튼 글자. rightIcon과 공존 가능 | `String?` | `null` |
| `rightIcon` | 우측 아이콘 이름 | `String?` | `null` |
| `bordered` | 하단 구분선을 그릴지 여부 | `Boolean` | `true` |
| `titleContent` | 제목 내용(title을 덮어씀, spec의 default 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `leftContent` | 좌측 내용(leftText/leftIcon을 덮어씀, spec의 left 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `rightContent` | 우측 내용(rightText/rightIcon을 덮어씀, spec의 right 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

어느 한쪽의 `*Content` / `*Text` / `*Icon`가 모두 비어 있으면 해당 쪽은 탭할 수 없고 아무 내용도 그리지 않습니다.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `navbar.height` | 내비게이션 바 높이(44dp) |
| `navbar.paddingHorizontal` | 좌우 가로 패딩(`spacing.md`를 따름) |
| `navbar.titleFontSize` | 제목과 양쪽 글자의 폰트 크기 |
| `navbar.iconSize` | 양쪽 아이콘 크기(`icon.size.medium`를 따름) |
| `navbar.borderWidth` | 하단 구분선 두께 |
| `zIndex.sticky` | 상단 고정 계층 시맨틱 정렬 |
| `color.background.default` | 배경색 |
| `color.text.primary` / `color.text.regular` | 제목 / 양쪽 글자 색상 |
| `color.border.lighter` | 하단 구분선 색상 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `leftclick-emits-onleftclick` | 좌측 탭이 onLeftClick을 트리거 | `LhNavbarTest.left_click_emits_onLeftClick` (Compose UI Test) |
| `rightclick-emits-onrightclick` | 우측 탭이 onRightClick을 트리거 | `LhNavbarTest.right_click_emits_onRightClick` |
| `no-left-content-no-onleftclick` | 좌측 내용이 없으면 트리거하지 않음 | `LhNavbarTest.no_left_content_does_not_emit_onLeftClick` |
| `title-renders-text` | 제목이 글자를 렌더링 | `LhNavbarTest.title_renders_text` |
| `bordered-uses-border-token` | 구분선이 보더 토큰을 소비 | `LhNavbarDefaultsTest.bordered_uses_border_token` + `not_bordered_has_no_divider` |
| `height-uses-navbar-height-token` | 높이가 navbar 토큰을 소비 | `LhNavbarDefaultsTest.height_uses_navbar_height_token` |

```bash
./gradlew :components:navbar:test                                 # JVM 부분
./gradlew :components:navbar:connectedAndroidTest                # 인터랙션 부분(기기 필요)
```
