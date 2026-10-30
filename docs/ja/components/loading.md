# Loading ローディング

読み込みインジケーターです。リング状の回転アニメーションで、周期は `motion.duration.base`（ミリ秒）を取ります。

> 契約元: [`lh-ui-core/specs/loading.json`](https://www.lhgb.top/lhui/core/specs/loading) ·
> ゴールデンケース: [`cases/loading.json`](https://www.lhgb.top/lhui/core/cases/loading)（5 件、表示専用コンポーネント、JVM 単体テストに翻訳済み）

## サイズと色調

3 段階のサイズが `loading.size.*` トークンに対応し、枠線幅は `loading.strokeWidth`、色調はセマンティック色を取ります。

<div class="sim-phone">
  <div class="sim-statusbar"><span>9:41</span><span>Loading</span></div>
  <div class="sim-screen">
    <div class="sim-row">
      <span class="sim-loading sim-loading--small"></span>
      <span class="sim-loading"></span>
      <span class="sim-loading sim-loading--large"></span>
    </div>
    <div class="sim-row" style="color: var(--lh-color-text-regular)">読み込み中…</div>
  </div>
</div>
<p class="sim-note">HTML シミュレーションプレビュー（Android / Compose の見た目）。実機レンダリングではありません</p>

```kotlin
LhLoading(lhSize = LhLoadingSize.large, tone = LhLoadingTone.primary)
```

## API

### Props

| プロパティ | 説明 | 型 | デフォルト値 |
| --- | --- | --- | --- |
| `lhSize` | インジケーターの直径 | `LhLoadingSize` | `Medium` |
| `tone` | リングの色 | `LhLoadingTone` | `Primary` |
| `modifier` | 修飾子 | `Modifier` | `Modifier` |

### LhLoadingSize

| 値 | サイズトークン |
| --- | --- |
| `Small` | `loading.size.small`（16dp） |
| `Medium` | `loading.size.medium`（20dp） |
| `Large` | `loading.size.large`（24dp） |

### LhLoadingTone

| 値 | カラートークン |
| --- | --- |
| `Default` | `color.text.regular` |
| `Primary` | `color.primary.default` |
| `Success` | `color.success.default` |
| `Warning` | `color.warning.default` |
| `Danger` | `color.danger.default` |
| `Info` | `color.info.default` |

## トークン对照

| トークン | 用途 |
| --- | --- |
| `loading.size.{small,medium,large}` | 3 段階の直径 |
| `loading.strokeWidth` | リングの枠線幅 |
| `color.text.regular` | default 色調 |
| `color.{primary,success,warning,danger,info}.default` | セマンティック色調 |
| `motion.duration.base` | 1 回転の周期（エンド側ではミリ秒の整数値） |

## ゴールデンケース对照

| case id | アサーション | ローカルテスト |
| --- | --- | --- |
| `medium-size-uses-medium-token` | medium が medium サイズトークンを取る | `LhLoadingDefaultsTest.medium_size_uses_medium_token` |
| `large-size-uses-large-token` | large が large サイズトークンを取る | `LhLoadingDefaultsTest.large_size_uses_large_token` |
| `primary-tone-uses-primary-token` | primary 色調が主色トークンを取る | `LhLoadingDefaultsTest.primary_tone_uses_primary_token` |
| `success-tone-uses-success-token` | success 色調が成功色トークンを取る | `LhLoadingDefaultsTest.success_tone_uses_success_token` |
| `render-emits-ring-command` | リングインジケーターがレンダリングされる（枠線幅がトークンを消費） | `LhLoadingDefaultsTest.stroke_width_uses_token` |

```bash
./gradlew :components:loading:test                          # JVM 部分（表示専用、インタラクションケースなし）
```
