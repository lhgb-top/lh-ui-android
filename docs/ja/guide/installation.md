# インストール

## 環境要件

- Android Studio Ladybug 以上（AGP 8.7+）
- Kotlin 2.1+、Jetpack Compose（BOM 2024.12+）
- compileSdk 35、minSdk 24
- ビルド時ツールチェーン: Node.js >= 20 + pnpm（トークンの接続とドキュメントサイトでのみ使用、**ランタイムには含まれない**）

## 依存関係の追加

### Maven（パッケージ公開パイプライン開通後）

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.github.com/lhgb-top/lh-ui-android") // ファミリー Maven リポジトリ（移行期 / 社内ネットワーク用）
  }
}
```

```kotlin
// app/build.gradle.kts
dependencies {
  implementation("top.lhgb.ui:android:0.0.1") // D22 の単一座標: コンポーネント + トークンがすべて含まれる
}
```

### ソースコード統合（現段階）

ファミリーレイアウトでは子モジュールを直接参照します:

```kotlin
// settings.gradle.kts
include(":core")
include(":components:button")
project(":core").projectDir = file("../lh-ui-android/core")
project(":components:button").projectDir = file("../lh-ui-android/components/button")
```

## トークンの同期

トークンは生成物であり、`lh-ui-core` から同期しなければなりません（唯一の真源であり、手書きでの変更は禁止）:

```bash
cd ../lh-ui-core && pnpm codegen   # dist/android のトークン成果物を生成
cd ../lh-ui-android
pnpm install                        # ビルド時ツールチェーンをインストール
pnpm link:tokens                    # トークンを core/src/main/kotlin/top/lhgb/ui/tokens/ に同期
```

`pnpm link:tokens` は三段階の存在性ガード付きです: ローカルの `../lh-ui-core/dist/android` → npm の `@lh-ui/core` 成果物 → エラー。

## インストールの検証

```bash
./gradlew :components:button:test                # JVM 単体テスト（ゴールデンケースの純粋なロジック）
./gradlew :components:button:assembleDebug       # ビルド
```
