# 테마 및 다크 모드

## 토큰 모델

네이티브 혈통의 토큰 원본은 `lh-ui-core/tokens/*.json`이며, codegen이 Android 산출물을 생성합니다:

| 산출물 | 내용 | 다크 형태 |
| --- | --- | --- |
| `LhTokens` | 라이트 전체: Button 크기, Color 색상표, Motion, Radius, Shadow, Spacing, Typography, ZIndex | — |
| `LhDarkTokens` | `Color` 서브 객체만(**병렬 객체** 모델) | 다크는 색상만 덮어쓰고 크기 / 모션 / 코너 반경은 어둠 속에서도 변하지 않음 |

> 이는 ios / harmony / windows와 일치(병렬 객체)하며, docs 측(단순 병합 JSON)과는 형태가 다릅니다.
> 테마 레이어는 이에 따라 설계되었습니다: `LhColors`는 색상만 담당하고 크기는 컴포넌트가 `LhTokens`에서 직접 가져옵니다.

## LhColors

`LightLhColors` / `DarkLhColors`는 생성된 두 object를 한 장의 불변 색상표로 평탄화합니다:

```kotlin
@Immutable
data class LhColors(
  val primary: Color, val onPrimary: Color,
  val success: Color, val warning: Color, val danger: Color, val info: Color,
  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */
)

val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)
val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)
```

## LhTheme

```kotlin
LhTheme(isDark = true) {
  // LocalLhColors에 DarkLhColors를 주입하고 내부 컴포넌트가 자동으로 소비
}
```

`LhTheme`으로 감싸지 않으면 `LightLhColors`로 폴백합니다(기본값은 라이트).

## 다크 사전 계산(중요)

Web 혈통의 다크는 CSS `color-mix()`로 동적으로 계산하지만, 네이티브에는 CSS 캐스케이드가 없기 때문에 core가 다크 식을
**리터럴로 미리 계산**해 둡니다(예: `rgba(61,100,158,0.25)`, `#B1C1D8`). 결과(마스터 플랜 R-n6):

> **프리셋 메인 컬러를 변경하더라도 다크 사전 계산 값은 자동으로 따라가지 않습니다.** 반드시 다시 수동으로 계산하여
> `lh-ui-core/tokens/color-dark.json`을 업데이트하고 *디자인 언어 기준선*을 새로 고쳐야 합니다.

## 토큰 빠른 참조

전체 토큰 표(라이트 / 다크 / 설명)는 [lh-ui-core 토큰 빠른 참조](https://www.lhgb.top/lhui/core/tokens/)에서, 또는 core 저장소에서
`pnpm codegen`을 실행한 뒤 `dist/docs/tokens.md`에서 확인할 수 있습니다. 컴포넌트 수준 토큰 매핑은
[Button 토큰 매핑](/ko/components/button#토큰-매핑)을 참조하세요.

## 커스텀 프리셋(예정)

`LhTheme(colors = ...)`을 통해 커스텀 색상표를 전달할 수 있으며, 프리셋 테마 기능은 향후 버전에서 core의
테마 프리셋 토큰과 함께 공개될 예정입니다.
