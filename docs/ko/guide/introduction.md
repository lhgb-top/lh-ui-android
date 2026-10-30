# 소개

**lh-ui-android**는 lh-ui 패밀리의 **네이티브 혈통**에 속하는 Android 구현체입니다. **Kotlin + Jetpack Compose** 기반의 UI 컴포넌트 라이브러리입니다.

## 패밀리 내 위치

```
                        ┌── Web 혈통: share(TS core + SCSS) → plus / vue2 / react / uniapp
lh-ui 패밀리(동일 브랜드)─────┤
                        └── 네이티브 혈통: core(JSON 토큰 + 계약 + codegen) → android / ios / harmony / windows
                                                                    ↑ 이 저장소
```

- **빌드 시간의 중심**: [`lh-ui-core`](https://www.lhgb.top)는 네이티브 혈통의 토큰 원본(`tokens/*.json`), 컴포넌트 계약(`specs/`), 골든 케이스(`cases/`), codegen 도구입니다. Kotlin 토큰 골격(`LhTokens` / `LhDarkTokens`)을 생성하지만, **UI 구현은 생성하지 않습니다**.
- **이 저장소의 역할**: Compose UI 직접 작성, 테마 연결, 프로퍼티 열거형과 기본값 바인딩, 골든 케이스의 로컬 테스트 번역.
- **Web 혈통과는 서로 독립**: `@lh-ui/share`에 의존하지 않으며, 릴리스 순서의 제약도 없습니다. 시각적 일관성은 *디자인 언어 기준선*에 수동으로 맞춰 유지합니다.

## 디자인 언어

중국풍 테마: 青花瓷(청화백자) 블루 메인 컬러(`#2B4C7C`), rice-paper white 배경(`#FAF6F0`), 성공은 pine-flower green, 경고는 藤黄(등황), 위험은 朱砂(주사). 세리프 폰트 스택(Noto Serif SC → STSong → SimSun). 모든 시각적 값이 토큰이며, 컴포넌트에는 어떤 리터럴도 등장하지 않습니다.

## 왜 Compose인가

- 플랫폼 일등 시민인 선언형 UI로, SwiftUI / ArkUI와 사고 모델이 동형이라 크로스 플랫폼 계약을 맞추기 쉽습니다
- JS 런타임 없음, HTML/CSS 브리지 없음. 네이티브 성능과 임베디드 친화 제약을 충족합니다
- `@Preview`가 곧 문서, `@Composable`이 곧 컴포넌트로, Web 혈통의 SFC 개발 경험과 대칭입니다

## 현재 상태

- **P0 전체 14개 컴포넌트가 제공됨**(button / icon / tag / loading / cell / input / textarea / checkbox / radio / switch / dialog / toast / picker / navbar). 각 컴포넌트는 동일한 패러다임의 전체 체인을 따릅니다: 토큰 소비 + 외관 해석 + Compose UI + JVM 단위 테스트 + Compose UI Test + `@Preview` + 문서 페이지.
- **네이티브 문서 모듈 `:docsui`가 제공됨**: 드래그하여 바로 사용(`LhDocs.launch(context)`) + 독립 패키징(`:docsapp`). 미리보기는 실제 Compose 실시간 렌더링이며 인터랙티브합니다. [빠른 시작](./quickstart)을 참조하세요.
