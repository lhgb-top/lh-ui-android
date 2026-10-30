# Dialog 대화상자

모달 오버레이입니다. 중요한 확인이나 사용자에게 집중이 필요한 내용에 사용합니다. 윈도우 레벨 계층은 Compose `Dialog`가 관리합니다.

> 계약 출처: [`lh-ui-core/specs/dialog.json`](https://www.lhgb.top/lhui/core/specs/dialog) ·
> 골든 케이스: [`cases/dialog.json`](https://www.lhgb.top/lhui/core/cases/dialog) (8건, JVM 단위 테스트 + Compose UI Test로 번역됨)

## 유형

`type`이 확인 버튼의 색상을 결정합니다(`LhButtonType`와 1:1로 매핑). 그 외의 레이아웃은 변하지 않습니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">제목</div>
      <div class="sim-dialog__content">본문 내용입니다. 현재 작업의 영향을 설명합니다.</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">취소</button>
        <button class="sim-btn sim-btn--primary">확인</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

```kotlin
var visible by remember { mutableStateOf(false) }
LhDialog(
  visible = visible,
  onConfirm = { /* 확인 로직 */ },
  onCancel = {},
  onClose = { visible = false },
  title = "삭제 확인",
  content = "삭제 후에는 되돌릴 수 없습니다. 삭제하시겠습니까?",
  type = LhDialogType.danger,
)
```

## 시맨틱 유형과 오버레이

`type`이 `danger`인 경우 확인 버튼은 위험 색상을 사용합니다. `closable`은 오버레이 탭 / 뒤로 가기 버튼으로 `onClose`를 트리거할지 여부를 제어합니다.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Dialog danger</span></div>
  <div class="sim-screen sim-screen--overlay">
    <div class="sim-overlay"></div>
    <div class="sim-dialog">
      <div class="sim-dialog__title">삭제 확인</div>
      <div class="sim-dialog__content">삭제 후에는 되돌릴 수 없습니다. 삭제하시겠습니까?</div>
      <div class="sim-dialog__footer">
        <button class="sim-btn">취소</button>
        <button class="sim-btn sim-btn--danger">확인</button>
      </div>
    </div>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이션 미리보기(Android / Compose 외형), 실기기 렌더링이 아님</p>

확인 / 취소 버튼은 탭 후 **비즈니스 콜백을 먼저 트리거하고, 그 다음 `onClose`를 트리거**합니다. 호스트는 이에 따라 `visible`을 false로 설정합니다.

## API

### Props

| 속성 | 설명 | 타입 | 기본값 |
| --- | --- | --- | --- |
| `visible` | 표시 여부. false인 경우 아무것도 렌더링하지 않음 | `Boolean` | — |
| `onConfirm` | 확인 버튼 탭 시 트리거 | `() -> Unit` | — |
| `onCancel` | 취소 버튼 탭 시 트리거 | `() -> Unit` | — |
| `onClose` | 확인 / 취소 후, 또는 (closable인 경우) 오버레이 탭 시 트리거 | `() -> Unit` | — |
| `title` | 제목 | `String?` | `null` |
| `content` | 본문 내용 | `String?` | `null` |
| `type` | 시맨틱 유형. 확인 버튼의 유형(색상)을 결정 | `LhDialogType` | `default` |
| `confirmText` | 확인 버튼 글자. 비어 있는 경우 확인 버튼을 표시하지 않음 | `String` | `"确认"` |
| `cancelText` | 취소 버튼 글자. 비어 있는 경우 취소 버튼을 표시하지 않음 | `String` | `"取消"` |
| `closable` | 오버레이 탭 / 뒤로 가기 버튼으로 onClose를 트리거할지 여부 | `Boolean` | `false` |
| `titleContent` | 제목 내용(title을 덮어씀, spec의 title 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `contentContent` | 본문 내용(content를 덮어씀, spec의 default 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `footer` | 하단 버튼 영역(기본 버튼을 덮어씀, spec의 footer 슬롯) | `@Composable (() -> Unit)?` | `null` |
| `modifier` | 수정자(내용 카드에 적용) | `Modifier` | `Modifier` |

### LhDialogType

| 값 | 확인 버튼 유형 |
| --- | --- |
| `default` / `primary` / `success` / `warning` / `danger` / `info` | 같은 이름의 `LhButtonType`. 해당 시맨틱 색상 사용 |

## 토큰 매핑

| 토큰 | 용도 |
| --- | --- |
| `dialog.width` | 내용 카드 너비(320dp) |
| `dialog.borderRadius` | 카드 코너 반경(`radius.base`를 따름) |
| `dialog.paddingHorizontal` / `dialog.paddingVertical` | 카드 패딩(`spacing.lg`를 따름) |
| `dialog.titleFontSize` / `dialog.contentFontSize` | 제목 / 본문 폰트 크기 |
| `dialog.gap` | 제목, 본문, 버튼 영역 사이의 간격 |
| `zIndex.modal` / `zIndex.modalBackdrop` | 계층 시맨틱 정렬(Compose `Dialog`가 윈도우 레벨 계층을 관리) |
| `color.background.overlay` | 오버레이 색상 |
| `color.background.default` | 카드 배경색 |
| `color.text.primary` / `color.text.regular` | 제목 / 본문 색상 |
| `shadow.base` | 카드 그림자(Compose는 spread를 지원하지 않으므로 흐림 반경을 elevation으로 사용) |

## 골든 케이스 매핑

| case id | 어서션 | 로컬 테스트 |
| --- | --- | --- |
| `confirm-emits-onconfirm` | 확인이 onConfirm + onClose를 트리거 | `LhDialogTest.confirm_emits_onConfirm_and_onClose` (Compose UI Test) |
| `confirm-also-emits-onclose` | 확인 후 onClose를 트리거 | 동일 |
| `cancel-emits-oncancel` | 취소가 onCancel + onClose를 트리거 | `LhDialogTest.cancel_emits_onCancel_and_onClose` |
| `closable-overlay-emits-onclose` | closable인 경우 오버레이 탭이 onClose를 트리거 | `LhDialogTest.closable_overlay_emits_onClose` |
| `not-closable-blocks-overlay-close` | closable이 아닌 경우 오버레이 탭을 무시 | `LhDialogTest.not_closable_blocks_overlay_close` |
| `hidden-not-rendered` | 숨겨진 경우 렌더링하지 않음 | `LhDialogTest.hidden_is_not_rendered` |
| `title-renders-text` | 제목이 글자를 렌더링 | `LhDialogTest.title_renders_text` |
| `danger-type-uses-danger-token` | danger 유형의 확인 버튼이 위험 색상을 사용 | `LhDialogDefaultsTest.danger_type_maps_to_danger_button` |

```bash
./gradlew :components:dialog:test                             # JVM 부분
./gradlew :components:dialog:connectedAndroidTest             # 인터랙션 부분(기기 필요)
```
