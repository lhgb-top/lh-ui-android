# Checkbox 체크박스

제어되는 체크박스입니다. 중간 상태(indeterminate)와 비활성화를 지원합니다.

> 계약 출처: [`lh-ui-core/specs/checkbox.json`](https://www.lhgb.top/lhui/core/specs/checkbox) ·
> 골든 케이스: [`cases/checkbox.json`](https://www.lhgb.top/lhui/core/cases/checkbox) (6건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 상태

선택 / 중간 상태는 채워진 주요색 배경 + onPrimary 인디케이터(체크 표시 / 가로 막대), 미선택은 보더만 그립니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox</span></div>
  <div class="sim-screen">
    <div class="sim-row"><label class="sim-checkbox"><span class="sim-checkbox__box"></span>미선택</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>선택됨</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>중간 상태</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--checked sim-checkbox--disabled"><span class="sim-checkbox__box"></span>비활성화(선택)</label></div>
    <div class="sim-row"><label class="sim-checkbox sim-checkbox--disabled"><span class="sim-checkbox__box"></span>비활성화(미선택)</label></div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
var checked by remember { mutableStateOf(false) }
LhCheckbox(
  checked = checked,
  onCheckedChange = { checked = it },
  label = "약관에 동의",
)
```

## 크기

3단계 상자 크기가 `checkbox.size.*` 토큰에 매핑됩니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Checkbox 크기</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--checked"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--checked"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--checked"><span class="sim-checkbox__box"></span>large</label>
    </div>
    <div class="sim-row">
      <label class="sim-checkbox sim-checkbox--small sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>small</label>
      <label class="sim-checkbox sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>medium</label>
      <label class="sim-checkbox sim-checkbox--large sim-checkbox--indeterminate"><span class="sim-checkbox__box"></span>large</label>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `checked` | 선택 여부(필수) | `Boolean` | — |
| `onCheckedChange` | 선택 상태 변경 콜백. 새 선택 상태를 전달 | `(Boolean) -> Unit` | — |
| `indeterminate` | 중간 상태(불확정 상태) 여부 | `Boolean` | `false` |
| `disabled` | 비활성화 여부 | `Boolean` | `false` |
| `label` | 라벨 글자 | `String?` | `null` |
| `lhSize` | 상자 크기 | `LhCheckboxSize` | `Medium` |
| `labelContent` | 라벨 내용(label을 덮어씀, spec의 default 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 수정자 | `Modifier` | `Modifier` |

### LhCheckboxSize

| 값 | 크기 토큰 |
| --- | --- |
| `Small` | `checkbox.size.small` (16dp) |
| `Medium` | `checkbox.size.medium` (20dp) |
| `Large` | `checkbox.size.large` (24dp) |

탭 영역은 행 전체(상자 + 라벨)에 해당합니다. 중간 상태에서 탭하면 선택됨으로 바뀌고, 그 외에는 선택 상태가 반전됩니다.

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `checkbox.size.{small,medium,large}` | 3단계 상자 크기 |
| `checkbox.borderRadius` / `checkbox.borderWidth` | 상자 코너 반경과 보더 두께 |
| `checkbox.checkWidth` | 체크 표시 / 가로 막대 보더 두께 |
| `checkbox.gap` | 상자와 라벨 사이의 간격 |
| `color.primary.default` / `color.onPrimary` | 선택 배경 / 인디케이터 색상 |
| `color.border.base` | 미선택 보더 색상 |
| `color.disabled.{bg,border,text}` | 비활성화 상태의 세 가지 색 |
| `color.text.primary` | 라벨 색상 |
| `typography.fontSize.base` | 라벨 폰트 크기 |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `tap-toggles-and-emits-onchange` | 탭이 상태를 반전하고 onChange를 내보냄 | `LhCheckboxTest.tap_unchecked_emits_true` (Compose UI Test) |
| `tap-checked-unchecks` | 선택된 상태에서 탭하면 선택 해제 | `LhCheckboxTest.tap_checked_emits_false` |
| `indeterminate-becomes-checked` | 중간 상태에서 탭하면 선택됨으로 변경 | `LhCheckboxTest.tap_indeterminate_emits_true` |
| `disabled-blocks-change` | 비활성화 상태는 트리거하지 않음 | `LhCheckboxTest.disabled_blocks_change` |
| `checked-uses-primary-tokens` | 선택 상태가 주요 토큰을 소비 | `LhCheckboxDefaultsTest.checked_uses_primary_tokens` |
| `unchecked-uses-border-token` | 미선택이 보더 토큰을 소비 | `LhCheckboxDefaultsTest.unchecked_uses_border_token` |

```bash
./gradlew :components:checkbox:test                            # JVM 부분
./gradlew :components:checkbox:connectedAndroidTest           # 인터랙션 부분(기기 필요)
```
