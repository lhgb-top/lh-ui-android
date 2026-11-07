package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocToastBasicPreview
import top.lhgb.ui.docs.previews.DocToastPositionPreview
import top.lhgb.ui.docs.previews.DocToastTypesPreview

val toastDoc = DocEntry(
  id = "toast",
  title = "Toast 通知",
  lede = "可視性とタイミングを自前管理する墨色のカプセル型オーバーレイです。軽量な操作フィードバックに使われます。ユーザーの操作はブロックしません。",
  group = DocGroup.OVERLAY,
  contract = "契約元: `lh-ui-core/specs/toast.json` · ゴールデンケース: `cases/toast.json`（6 件、状態マシン 4 件 + レンダリング 2 件、すべてローカルテストに翻訳済み）",
  sections = listOf(
    DocSection(
      title = "使い方",
      prose = "toast は自己完結型の状態マシンです。状態は `LhToastState` が保持し、ホストは `show(text)` で表示、`hide()` で非表示にし、\nタイミングの期限が来ると自動的に非表示になり `onClose` を発火します。コンポーネントは表示中はフレームごとに `tick` を自駆動しますが、ホストが自ら呼び出してタイミングを引き受けることもできます。",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* 任意：閉じたことを記録 */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"保存しました\") }) { Text(\"表示\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "セマンティックタイプ",
      prose = "`default` 以外のタイプでは、テキストの前にフォントサイズと同じ直径のセマンティック色の円点が描画されます。",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "位置",
      prose = "`lhPosition` は `top` / `center` / `bottom` を取り、上下の両端にそれぞれ `spacing.xl` のマージンを確保します。幅はテキストの計測から求められ、利用可能な幅の 80% を上限とします。",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "通知の状態マシン（可視性、テキスト、タイミングを保持）", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "セマンティックタイプ。default 以外のときセマンティック色の円点を描画", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "利用可能領域内での位置", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "修飾子（カプセルに作用）", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("メンバー", "説明"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "表示し、タイミングをリセットする")),
        DocRow(cells = listOf("`hide()`", "すぐに非表示にする。onClose は発火しない")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "タイミングを進める。期限が来たら非表示にし、onClose を発火して true を返す")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "現在の状態（観察可能）")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("値", "円点の色"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "円点なし")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "対応するセマンティック色")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "カプセルの最大幅（240dp。実際は利用可能な幅の 80% との小さい方を取る）")),
    DocRow(cells = listOf("`toast.borderRadius`", "カプセルの角丸（`radius.base` に追従）")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "カプセルのパディング")),
    DocRow(cells = listOf("`toast.fontSize`", "テキストのフォントサイズ（円点の直径も同じ値）")),
    DocRow(cells = listOf("`toast.gap`", "円点とテキストの間隔")),
    DocRow(cells = listOf("`zIndex.toast`", "階層のセマンティックアラインメント（Compose `Popup` がウィンドウレベルの階層を内包）")),
    DocRow(cells = listOf("`color.background.overlay`（テーマレイヤーの ink マッピング）", "カプセルの背景色")),
    DocRow(cells = listOf("`color.onPrimary`", "テキスト色")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "セマンティックな円点の色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "show 後に可視になりテキストを描画", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "期限が来ると自動的に非表示になり onClose を発火", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "期限内は可視を保ち onClose を発火しない", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide は onClose を発火しない", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "success タイプが成功色を消費", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "非表示のときは何も描画しない", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # JVM 部分",
    "./gradlew :components:toast:connectedAndroidTest                 # インタラクション部分（デバイスが必要）",
  ),
)
