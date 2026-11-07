# Text 텍스트

텍스트 표시 원시 컴포넌트: 전역 타이포그래피 토큰을 소비하며 크기 레벨, 자중, 색조, 정렬을 지원.

> 계약 원본: `lh-ui-core/specs/text.json` · 골든 케이스: `cases/text.json`(5건, JVM 단위 테스트로 번역됨)

## 크기 레벨

`level`은 `typography.fontSize.<level>` 토큰에 매핑되며 기본값은 `base`.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text sim-text--extraLarge">extraLarge</p>
    <p class="sim-text sim-text--large">large</p>
    <p class="sim-text sim-text--medium">medium</p>
    <p class="sim-text">base</p>
    <p class="sim-text sim-text--small">small</p>
    <p class="sim-text sim-text--extraSmall">extraSmall</p>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이터 미리보기(Android / Compose 외관), 실기기 렌더링이 아님</p>

```kotlin
LhText(content = "Title text", level = LhTextLevel.large)
```

## 자중

`weight`는 `typography.fontWeight.<weight>` 토큰에 매핑(primary 400 / bold 600).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text">primary</p>
    <p class="sim-text sim-text--bold">bold</p>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이터 미리보기(Android / Compose 외관), 실기기 렌더링이 아님</p>

## 색조

`tone`은 `color.text.<tone>` 토큰에 매핑. `selected`는 `color.selected` 사용.

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text">primary</p>
    <p class="sim-text sim-text--regular">regular</p>
    <p class="sim-text sim-text--secondary">secondary</p>
    <p class="sim-text sim-text--placeholder">placeholder</p>
    <p class="sim-text sim-text--disabled">disabled</p>
    <p class="sim-text sim-text--selected">selected</p>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이터 미리보기(Android / Compose 외관), 실기기 렌더링이 아님</p>

## 정렬과 행 높이

`lhAlign`은 행 박스 안에서 정렬. 행 높이는 항상 `typography.lineHeight.primary`(크기 × 1.8).

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Text</span></div>
  <div class="sim-screen">
    <p class="sim-text sim-text--align-center">center</p>
    <p class="sim-text sim-text--align-end">end</p>
  </div>
</div>
<p class="sim-note">HTML 시뮬레이터 미리보기(Android / Compose 외관), 실기기 렌더링이 아님</p>

```kotlin
LhText(content = "Centered", lhAlign = LhTextAlign.center)
```

## Props

| Prop | Description | Type | Default |
| --- | --- | --- | --- |
| `content` | text content (string form of the spec default slot) | `String` | `""` |
| `modifier` | modifier | `Modifier` | `Modifier` |
| `level` | font-size level | `LhTextLevel` | `Base` |
| `weight` | font weight | `LhTextWeight` | `Primary` |
| `tone` | text tone | `LhTextTone` | `Primary` |
| `lhAlign` | horizontal alignment | `LhTextAlign` | `Start` |

### LhTextLevel

| Value | fontSize token |
| --- | --- |
| `ExtraLarge` | `typography.fontSize.extraLarge` (20sp) |
| `Large` | `typography.fontSize.large` (18sp) |
| `Medium` | `typography.fontSize.medium` (16sp) |
| `Base` | `typography.fontSize.base` (14sp, default) |
| `Small` | `typography.fontSize.small` (13sp) |
| `ExtraSmall` | `typography.fontSize.extraSmall` (12sp) |

### LhTextTone

| Value | text color token |
| --- | --- |
| `Primary` | `color.text.primary` |
| `Regular` | `color.text.regular` |
| `Secondary` | `color.text.secondary` |
| `Placeholder` | `color.text.placeholder` |
| `Disabled` | `color.text.disabled` |
| `Selected` | `color.selected` |

## 토큰 참조

| Token | Usage |
| --- | --- |
| `typography.fontSize.<level>` | six size levels |
| `typography.fontWeight.{primary,bold}` | two weights |
| `typography.lineHeight.primary` | line height (font size × 1.8) |
| `color.text.<tone>` / `color.selected` | text tones |

## 골든 케이스 참조

| case id | 어설션 | 로컬 테스트 |
| --- | --- | --- |
| `default-level-uses-base-tokens` | default level consumes the base typography tokens | `LhTextDefaultsTest.default_level_uses_base_tokens` |
| `large-level-uses-large-token` | large level consumes the large size token | `LhTextDefaultsTest.large_level_uses_large_token` |
| `bold-weight-uses-bold-token` | bold consumes the bold weight token | `LhTextDefaultsTest.bold_weight_uses_bold_token` |
| `secondary-tone-uses-text-secondary` | secondary tone takes the secondary text color token | `LhTextDefaultsTest.secondary_tone_uses_text_secondary` |
| `render-emits-single-text-command` | render emits a single TEXT command | `LhTextDefaultsTest.all_levels_map_to_their_tokens` |

```bash
./gradlew :components:text:test                             # JVM
```
