# ローカル連携

## ファミリーレイアウトの慣習

```
lh-ui/                        # ファミリーの親ディレクトリ（リポジトリではない）
├── lh-ui-core/               # トークン真源 + codegen
├── lh-ui-android/            # 本リポジトリ
├── lh-ui-ios/                # 兄弟リポジトリ
├── lh-ui-harmony/
└── lh-ui-windows/
```

各リポジトリが同じ親ディレクトリにある場合、トークンは**ローカル直結**します。兄弟リポジトリが存在しない（CI での単一リポジトリ clone）場合は**自動的に npm パッケージにフォールバック**します。
これは Web 血脈の vite alias ガード機構と同構です（マスタープラン D18）。

## 三段階のガード

`scripts/link-tokens.mjs` が優先度順にトークン成果物を探します:

| 優先度 | ソース | 説明 |
| --- | --- | --- |
| 1 | `../lh-ui-core/dist/android` | ローカル直結: トークン変更が即時反映 |
| 2 | `node_modules/@lh-ui/core/dist/android` | npm フォールバック: `@lh-ui/core` の `files:["dist"]` が四端の成果物を含む |
| 3 | — | エラーで終了、トークンがなければビルド不可 |

## 日常のフロー

```bash
# 1. トークンを変更（core リポジトリで）
cd ../lh-ui-core
vim tokens/color.json           # または spacing / typography / ...

# 2. 再生成 + 検証
pnpm codegen                    # dist/ の四端成果物を生成
pnpm verify                     # schema + 参照 + WCAG コントラスト + 契約の一致性

# 3. 本リポジトリに同期
cd ../lh-ui-android
pnpm link:tokens                # core/src/main/kotlin/top/lhgb/ui/tokens/ を上書き

# 4. リグレッション
./gradlew :components:button:test
```

## CI ゲート

```bash
pnpm link:tokens:check          # トークンが同期済みか検出（ファイル数の比較、未同期なら終了コード 1）
```

## 禁止事項

- ❌ `core/src/main/kotlin/top/lhgb/ui/tokens/` の手書き変更（生成物、gitignore 対象）
- ❌ 生成されたトークンファイルをリポジトリにコミット
- ❌ コンポーネント内に色や間隔のリテラルを書く（例: `Color(0xFF2B4C7C)`、`16.dp`、`14.sp`）
- ❌ `file:` / `link:` の依存形態を `build.gradle.kts` に書いてコミット（連携はローカルのみ）

## トラブルシューティング

| 症状 | 原因 | 解決策 |
| --- | --- | --- |
| コンパイルが `unresolved reference: LhTokens` で失敗 | トークンが未同期 | `pnpm link:tokens` |
| トークン値が想定と違う | core リポジトリが codegen を再実行していない | core リポジトリで `pnpm codegen` 後に再度 link |
| `link:tokens` が「トークン成果物が見つからない」と報告 | 兄弟リポジトリが不在かつ npm パッケージ未インストール | ファミリーレイアウトを確認するか `pnpm i -D @lh-ui/core` |
| ダークの色がおかしい | ライトのメインカラーを変えたがダークの事前計算を更新していない | `color-dark.json` を更新して再実行（R-n6） |
