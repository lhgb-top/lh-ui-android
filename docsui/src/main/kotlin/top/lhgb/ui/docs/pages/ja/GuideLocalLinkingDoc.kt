package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "ローカル連携",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "ファミリーレイアウトの慣習",
      prose = "各リポジトリが同じ親ディレクトリにある場合、トークンはローカル直結します。兄弟リポジトリが存在しない（CI での単一リポジトリ clone）場合は自動的に npm パッケージにフォールバックします。\nこれは Web 血脈の vite alias ガード機構と同構です（マスタープラン D18）。",
      code = listOf(
        "lh-ui/                        # ファミリーの親ディレクトリ（リポジトリではない）",
        "├── lh-ui-core/               # トークン真源 + codegen",
        "├── lh-ui-android/            # 本リポジトリ",
        "├── lh-ui-ios/                # 兄弟リポジトリ",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "三段階のガード",
      prose = "`scripts/link-tokens.mjs` が優先度順にトークン成果物を探します:",
    ),
    DocSection(
      title = "日常のフロー",
      prose = "",
    ),
    DocSection(
      title = "CI ゲート",
      prose = "",
    ),
    DocSection(
      title = "禁止事項",
      prose = "• ❌ `core/src/main/kotlin/top/lhgb/ui/tokens/` の手書き変更（生成物、gitignore 対象）\n• ❌ 生成されたトークンファイルをリポジトリにコミット\n• ❌ コンポーネント内に色や間隔のリテラルを書く（例: `Color(0xFF2B4C7C)`、`16.dp`、`14.sp`）\n• ❌ `file:` / `link:` の依存形態を `build.gradle.kts` に書いてコミット（連携はローカルのみ）",
    ),
    DocSection(
      title = "トラブルシューティング",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # トークンが同期済みか検出（ファイル数の比較、未同期なら終了コード 1）",
  ),
)
