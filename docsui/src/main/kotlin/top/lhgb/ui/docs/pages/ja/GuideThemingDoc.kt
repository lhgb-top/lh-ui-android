package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideThemingDoc = DocEntry(
  id = "guide-theming",
  title = "テーマとダークモード",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "トークンモデル",
      prose = "ネイティブ血脈のトークン真源は `lh-ui-core/tokens/*.json` にあり、codegen が Android 向けの成果物を生成します:\nこれは ios / harmony / windows と一致する（並列オブジェクト）形態であり、docs 端（浅いマージの JSON）とは異なります。\nテーマレイヤーはこれに従って設計されています: `LhColors` は色だけを担い、サイズはコンポーネントが直接 `LhTokens` から取ります。",
    ),
    DocSection(
      title = "LhColors",
      prose = "`LightLhColors` / `DarkLhColors` は生成された二つの object を、一つのイミュータブルなパレットに平坦化します:",
      code = listOf(
        "@Immutable",
        "data class LhColors(",
        "  val primary: Color, val onPrimary: Color,",
        "  val success: Color, val warning: Color, val danger: Color, val info: Color,",
        "  val textPrimary: Color, val textRegular: Color, val textSecondary: Color, /* ... */",
        ")",
        "",
        "val LightLhColors = LhColors(primary = LhTokens.Color.Primary.default, /* ... */)",
        "val DarkLhColors = LhColors(primary = LhDarkTokens.Color.Primary.default, /* ... */)",
      ),
    ),
    DocSection(
      title = "LhTheme",
      prose = "`LhTheme` でラップしない場合は `LightLhColors` にフォールバックします（デフォルトはライト）。",
      code = listOf(
        "LhTheme(isDark = true) {",
        "  // LocalLhColors に DarkLhColors が注入され、内部コンポーネントが自動的に消費する",
        "}",
      ),
    ),
    DocSection(
      title = "ダークの事前計算（重要）",
      prose = "Web 血脈のダークは CSS の `color-mix()` で動的に評価しますが、ネイティブには CSS のカスケードがないため、core はダークの式をリテラルに事前計算しています（例: `rgba(61,100,158,0.25)`、`#B1C1D8`）。帰結（マスタープラン R-n6）:\nプリセットのメインカラーを変更すると、事前計算されたダーク値は自動では追従しません。 手作業で再計算して\n`lh-ui-core/tokens/color-dark.json` を更新し、『デザイン言語基線』を刷新しなければなりません。",
    ),
    DocSection(
      title = "トークン早見表",
      prose = "完全なトークン表（ライト / ダーク / 説明）は lh-ui-core トークン早見表を参照するか、\ncore リポジトリで `pnpm codegen` を実行したあと `dist/docs/tokens.md` を確認してください。コンポーネント別のトークンマッピングは\nButton トークン对照を参照してください。",
    ),
    DocSection(
      title = "カスタムプリセット（予約）",
      prose = "`LhTheme(colors = ...)` はカスタムパレットの渡し込みをサポートします。プリセットテーマの機能は、core のテーマプリセットトークンとともに今後のバージョンで公開される予定です。",
    ),
  ),
)
