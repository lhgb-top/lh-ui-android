# テーマとダークモード

## トークンモデル

ネイティブ血脈のトークン真源は `lh-ui-core/tokens/*.json` にあり、codegen が Android 向けの成果物を生成します:

| 成果物 | 内容 | ダーク形態 |
| --- | --- | --- |
| `LhTokens` | ライト全量: Button のサイズ、Color パレット、Motion、Radius、Shadow、Spacing、Typography、ZIndex | — |
| `LhDarkTokens` | `Color` サブオブジェクトのみ（**並列オブジェクト**モデル） | ダークは色だけを上書きし、サイズ / モーション / 角丸は暗くしても変化しない |

> これは ios / harmony / windows と一致する（並列オブジェクト）形態であり、docs 端（浅いマージの JSON）とは異なります。
> テーマレイヤーはこれに従って設計されています: `LhColors` は色だけを担い、サイズはコンポーネントが直接 `LhTokens` から取ります。

## LhColors

`LightLhColors` / `DarkLhColors` は生成された二つの object を、一つのイミュータブルなパレットに平坦化します:

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
  // LocalLhColors に DarkLhColors が注入され、内部コンポーネントが自動的に消費する
}
```

`LhTheme` でラップしない場合は `LightLhColors` にフォールバックします（デフォルトはライト）。

## ダークの事前計算（重要）

Web 血脈のダークは CSS の `color-mix()` で動的に評価しますが、ネイティブには CSS のカスケードがないため、core はダークの式を**リテラルに事前計算**しています（例: `rgba(61,100,158,0.25)`、`#B1C1D8`）。帰結（マスタープラン R-n6）:

> **プリセットのメインカラーを変更すると、事前計算されたダーク値は自動では追従しません。** 手作業で再計算して
> `lh-ui-core/tokens/color-dark.json` を更新し、『デザイン言語基線』を刷新しなければなりません。

## トークン早見表

完全なトークン表（ライト / ダーク / 説明）は [lh-ui-core トークン早見表](https://www.lhgb.top/lhui/core/tokens/)を参照するか、
core リポジトリで `pnpm codegen` を実行したあと `dist/docs/tokens.md` を確認してください。コンポーネント別のトークンマッピングは
[Button トークン对照](/ja/components/button#トークン对照)を参照してください。

## カスタムプリセット（予約）

`LhTheme(colors = ...)` はカスタムパレットの渡し込みをサポートします。プリセットテーマの機能は、core のテーマプリセットトークンとともに今後のバージョンで公開される予定です。
