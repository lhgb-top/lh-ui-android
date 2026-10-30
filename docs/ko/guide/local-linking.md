# 로컬 연결

## 패밀리 레이아웃 규칙

```
lh-ui/                        # 패밀리 상위 디렉터리(저장소가 아님)
├── lh-ui-core/               # 토큰 원본 + codegen
├── lh-ui-android/            # 이 저장소
├── lh-ui-ios/                # 형제 저장소
├── lh-ui-harmony/
└── lh-ui-windows/
```

각 저장소가 동일한 상위 디렉터리에 있으면 토큰은 **로컬 직접 연결**됩니다. 형제 저장소가 존재하지 않는 경우(CI의 단일 저장소 clone)에는
**자동으로 npm 패키지로 폴백**합니다.
이는 Web 혈통의 vite alias 가드 메커니즘과 동형입니다(마스터 플랜 D18).

## 3단계 가드

`scripts/link-tokens.mjs`가 우선순위 순서로 토큰 산출물을 찾습니다:

| 우선순위 | 출처 | 설명 |
| --- | --- | --- |
| 1 | `../lh-ui-core/dist/android` | 로컬 직접 연결: 토큰을 변경하면 즉시 반영 |
| 2 | `node_modules/@lh-ui/core/dist/android` | npm 폴백: `@lh-ui/core`의 `files:["dist"]`에 4개 플랫폼 산출물 포함 |
| 3 | — | 오류로 종료, 토큰 없이는 빌드 불가 |

## 일상 흐름

```bash
# 1. 토큰 수정(core 저장소에서)
cd ../lh-ui-core
vim tokens/color.json           # 또는 spacing / typography / ...

# 2. 재생성 + 검증
pnpm codegen                    # dist/에 4개 플랫폼 산출물 생성
pnpm verify                     # schema + 참조 + WCAG 대비 + 계약 일관성

# 3. 이 저장소로 돌아와 동기화
cd ../lh-ui-android
pnpm link:tokens                # core/src/main/kotlin/top/lhgb/ui/tokens/를 덮어씀

# 4. 회귀
./gradlew :components:button:test
```

## CI 게이트

```bash
pnpm link:tokens:check          # 토큰이 동기화되었는지 검사(파일 수 비교, 미동기화 시 종료 코드 1)
```

## 금지 사항

- ❌ `core/src/main/kotlin/top/lhgb/ui/tokens/`를 수동으로 수정(생성물, gitignore)
- ❌ 생성된 토큰 파일을 저장소에 커밋
- ❌ 컴포넌트에 색상 / 간격 리터럴을 직접 작성(예: `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)
- ❌ `file:` / `link:` 의존성 형태를 `build.gradle.kts`에 작성하여 커밋(로컬 연결은 로컬에만)

## 문제 해결

| 증상 | 원인 | 해결 |
| --- | --- | --- |
| 컴파일 오류 `unresolved reference: LhTokens` | 토큰이 동기화되지 않음 | `pnpm link:tokens` |
| 토큰 값이 예상과 다름 | core 저장소에서 codegen을 다시 하지 않음 | core 저장소에서 `pnpm codegen` 후 다시 link |
| `link:tokens`가 "토큰 산출물을 찾을 수 없음" 오류 | 형제 저장소가 없고 npm 패키지도 설치되지 않음 | 패밀리 레이아웃을 확인하거나 `pnpm i -D @lh-ui/core` |
| 다크 색상이 올바르지 않음 | 라이트 메인 컬러를 변경했지만 다크 사전 계산을 업데이트하지 않음 | `color-dark.json`을 업데이트하고 다시 실행(R-n6) |
