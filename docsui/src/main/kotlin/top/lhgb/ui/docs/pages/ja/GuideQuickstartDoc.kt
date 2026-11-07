package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideQuickstartDoc = DocEntry(
  id = "guide-quickstart",
  title = "クイックスタート",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "最小サンプル",
      prose = "`LhTheme` はテーマコンテナで、内部のすべての Lh コンポーネントが自動的にトークンを消費します。ラップしない場合はライトカラーパレットにフォールバックします。",
      code = listOf(
        "import top.lhgb.ui.button.LhButton",
        "import top.lhgb.ui.button.LhButtonType",
        "import top.lhgb.ui.theme.LhTheme",
        "",
        "@Composable",
        "fun Demo() {",
        "  LhTheme {",
        "    LhButton(",
        "      type = LhButtonType.Primary,",
        "      lhOnClick = { /* クリックを処理 */ },",
        "    ) {",
        "      Text(\"プライマリボタン\")",
        "    }",
        "  }",
        "}",
      ),
    ),
    DocSection(
      title = "タイプとサイズ",
      prose = "",
      code = listOf(
        "// 6 種類のタイプ",
        "LhButton(type = LhButtonType.Default, lhOnClick = {}) { Text(\"デフォルト\") }",
        "LhButton(type = LhButtonType.Primary, lhOnClick = {}) { Text(\"プライマリ\") }",
        "LhButton(type = LhButtonType.Success, lhOnClick = {}) { Text(\"成功\") }",
        "LhButton(type = LhButtonType.Warning, lhOnClick = {}) { Text(\"警告\") }",
        "LhButton(type = LhButtonType.Danger, lhOnClick = {}) { Text(\"危険\") }",
        "LhButton(type = LhButtonType.Info, lhOnClick = {}) { Text(\"情報\") }",
        "",
        "// 3 種類のサイズ",
        "LhButton(lhSize = LhButtonSize.Small, lhOnClick = {}) { Text(\"小\") }",
        "LhButton(lhSize = LhButtonSize.Medium, lhOnClick = {}) { Text(\"中\") }",
        "LhButton(lhSize = LhButtonSize.Large, lhOnClick = {}) { Text(\"大\") }",
      ),
    ),
    DocSection(
      title = "状態",
      prose = "",
      code = listOf(
        "// 無効: クリックに応答せず、無効状態のスタイルを表示",
        "LhButton(disabled = true, lhOnClick = {}) { Text(\"無効\") }",
        "",
        "// 読み込み中: インジケーターを表示し、lhOnClick を発火しない",
        "LhButton(loading = true, lhOnClick = {}) { Text(\"読み込み中\") }",
        "",
        "// 両方指定: 無効が優先される（ゴールデンケース disabled-precedes-loading）",
        "LhButton(disabled = true, loading = true, lhOnClick = {}) { Text(\"無効が優先\") }",
      ),
    ),
    DocSection(
      title = "アイコンと紋様背景",
      prose = "",
      code = listOf(
        "// アイコン名はローカルのアイコン体系で解決される（LhButtonIcons を参照、未登録名は描画されない）",
        "LhButton(type = LhButtonType.Primary, icon = \"search\", lhOnClick = {}) { Text(\"検索\") }",
        "",
        "// 中華風の紋様背景（color.decoration トークンを消費）",
        "LhButton(type = LhButtonType.Primary, bgPattern = true, lhOnClick = {}) { Text(\"紋様背景\") }",
      ),
    ),
    DocSection(
      title = "ライト / ダークテーマ",
      prose = "",
      code = listOf(
        "val isDark by remember { mutableStateOf(false) }",
        "",
        "LhTheme(isDark = isDark) {",
        "  // 内部の全コンポーネントがダークトークンに自動切替（core が事前計算した値、ランタイム演算なし）",
        "}",
      ),
    ),
    DocSection(
      title = "ネイティブドキュメントモジュール",
      prose = "本ウェブドキュメントとは別に、リポジトリ内にAndroid ネイティブのドキュメントモジュール（`:docsui`）があります。ドキュメントページは本サイトの構造と 1:1 で一致しますが、プレビューは真の Compose リアルタイムレンダリングでインタラクティブ（ボタンはクリック可、入力はタイピング可、ダイアログは開ける）であり、静的 HTML のシミュレーションではありません。\nドラッグするだけですぐ使える: 任意の Android プロジェクトが依存すれば、`LhDocs.launch(context)` 一行で完全なドキュメント（14 コンポーネントページ + 5 ガイドページ + ライト/ダーク切替 + リアルタイムプレビュー）を開けます:\nライブラリの manifest が `exported = false` の `LhDocsActivity` を自動的にマージします。ホストアプリにランチャーアイコンは注入しません。\n単独パッケージング: `:docsapp` はごく薄い application モジュール（Activity を一つ持つだけ）で、単独でパッケージングすれば「lh-ui ドキュメント」APP が完成します:",
      code = listOf(
        "// build.gradle.kts",
        "implementation(project(\":docsui\"))  // または Maven 座標 top.lhgb.ui:docsui",
        "",
        "// 任意のエントリ（例: 「コンポーネントドキュメント」ボタンのクリックコールバック）",
        "LhDocs.launch(context)",
      ),
    ),
    DocSection(
      title = "コンポーネントサンプルのプレビュー",
      prose = "ネイティブ UI はブラウザでリアルタイムレンダリングできないため、本サイトではインラインの静的 HTML 携帯フレームシミュレーターで外観を示します:\nシミュレーターのダーク表示はサイトと連動します: 右上のテーマを切り替えると、携帯フレーム内の色が即座に変わります（ダークトークンは core が事前計算）。",
    ),
  ),
  commands = listOf(

    "./gradlew :docsapp:installDebug",
  ),
)
