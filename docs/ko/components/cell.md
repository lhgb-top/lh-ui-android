# Cell 셀

리스트 셀입니다. 아이콘 / 제목 + 설명 / 우측 값 + 화살표의 3단 레이아웃으로, 주로 리스트와 조합해 사용합니다.

> 계약 출처: [`lh-ui-core/specs/cell.json`](https://www.lhgb.top/lhui/core/specs/cell) ·
> 골든 케이스: [`cases/cell.json`](https://www.lhgb.top/lhui/core/cases/cell) (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 기본 사용법

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title">제목 글자</div>
      <div class="sim-cell__value">우측 값</div>
    </div>
    <div class="sim-cell">
      <div>
        <div class="sim-cell__title">설명이 있는 제목</div>
        <div class="sim-cell__label">설명 글자</div>
      </div>
      <div class="sim-cell__value">값</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>필수 항목</div>
      <div class="sim-cell__value">상세</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
LhCell(
  title = "설명이 있는 제목",
  label = "설명 글자",
  value = "값",
  isLink = true,
  lhOnClick = { /* 이동 */ },
)
```

## 필수 표시와 링크

`required`는 제목 앞에 빨간색 별표(`color.danger.default`)를 그리고, `isLink`는 끝에 우측 화살표 아이콘을 그립니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Cell 변형</span></div>
  <div class="sim-screen" style="padding: 0">
    <div class="sim-cell">
      <div class="sim-cell__title"><span class="sim-cell__required">*</span>필수</div>
      <div class="sim-cell__value">화살표 →</div>
    </div>
    <div class="sim-cell">
      <div class="sim-cell__title">보더 없음</div>
      <div class="sim-cell__value">값</div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `title` | 제목(주 글자) | `String?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |
| `value` | 우측 값(보조 글자, 우측 정렬) | `String?` | `null` |
| `label` | 제목 아래의 설명 글자 | `String?` | `null` |
| `bordered` | 하단 구분선을 그릴지 여부(`color.border.lighter`) | `Boolean` | `true` |
| `clickable` | 탭 가능 여부. true인 경우 우측 안내 화살표를 표시하고 탭에 응답 | `Boolean` | `false` |
| `required` | 제목 앞에 필수 빨간 별표를 표시할지 여부(`color.danger.default`) | `Boolean` | `false` |
| `icon` | 좌측 아이콘 이름(로컬 아이콘 체계). 비어 있으면 그리지 않음 | `String?` | `null` |
| `lhOnClick` | 탭 콜백. `clickable`이 false인 경우 트리거하지 않음 | `(() -> Unit)?` | `null` |
| `titleContent` | 제목 내용(title을 덮어씀, spec의 default 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `valueContent` | 우측 값 내용(value를 덮어씀, spec의 value 슬롯) | `@Composable (() -> Unit)?` | `null` |

`clickable`은 "우측 안내 화살표를 그릴지 여부"와 "행 전체가 탭에 응답할지 여부"를 함께 제어합니다. `clickable = true`이고 `lhOnClick`이 비어 있지 않을 때만 탭이 트리거됩니다.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `cell.paddingVertical` | 세로 패딩(`spacing.md` 참조) |
| `cell.paddingHorizontal` | 가로 패딩(`spacing.md`) |
| `cell.fontSizeTitle` / `cell.fontSizeLabel` / `cell.fontSizeValue` | 제목 / 설명 / 값 폰트 크기 |
| `cell.gap` | 아이콘과 제목 사이의 간격(`spacing.xs`) |
| `icon.size.medium` | 좌측 아이콘과 안내 화살표 크기 |
| `cell.borderWidth` | 하단 구분선 두께 |
| `typography.lineHeight.primary` | 글자 줄 높이 |
| `color.text.{primary,secondary}` | 제목 / 설명 / 값 색상 |
| `color.danger.default` | 필수 빨간 별표 |
| `color.border.lighter` | 하단 구분선 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `title-and-value-render` | 제목과 우측 값이 글자를 렌더링 | `LhCellTest.title_and_value_render` (Compose UI Test) |
| `label-renders-secondary-tone` | label이 보조 글자색 토큰을 사용 | `LhCellDefaultsTest.label_uses_secondary_text_token` |
| `required-uses-danger-token` | 필수 표시가 위험 색 토큰을 사용 | `LhCellDefaultsTest.required_uses_danger_token` |
| `bordered-emits-line-command` | 구분선이 보더 토큰을 소비 | `LhCellDefaultsTest.bordered_uses_border_lighter_token` |
| `clickable-emits-onclick` | 탭이 onClick을 트리거 | `LhCellTest.clickable_emits_onClick` |
| `not-clickable-no-onclick` | 탭할 수 없는 상태는 트리거하지 않음 | `LhCellTest.not_clickable_no_onClick` |

```bash
./gradlew :components:cell:test                              # JVM 부분
./gradlew :components:cell:connectedAndroidTest              # 인터랙션 부분(기기 필요)
```
