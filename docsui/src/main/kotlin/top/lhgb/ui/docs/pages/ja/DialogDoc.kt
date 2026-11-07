package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocDialogSemanticAndMaskPreview
import top.lhgb.ui.docs.previews.DocDialogTypesPreview

val dialogDoc = DocEntry(
  id = "dialog",
  title = "Dialog ダイアログ",
  lede = "モーダルオーバーレイです。重要な確認やユーザーに集中してもらう必要がある内容に使います。ウィンドウレベルの階層は Compose の `Dialog` が管理します。",
  group = DocGroup.OVERLAY,
  contract = "契約元: `lh-ui-core/specs/dialog.json` · ゴールデンケース: `cases/dialog.json`（8 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "タイプ",
      prose = "`type` が確認ボタンの色を決定します（`LhButtonType` と 1:1 で対応）。それ以外のレイアウトは変わりません。",
      preview = { DocDialogTypesPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "LhDialog(",
        "  visible = visible,",
        "  onConfirm = { /* 確認のロジック */ },",
        "  onCancel = {},",
        "  onClose = { visible = false },",
        "  title = \"削除の確認\",",
        "  content = \"削除後は復元できません。削除しますか？\",",
        "  type = LhDialogType.danger,",
        ")",
      ),
    ),
    DocSection(
      title = "セマンティックタイプとオーバーレイ",
      prose = "`type` が `danger` の場合、確認ボタンは危険色になります。`closable` はオーバーレイのタップ / 戻るボタンで `onClose` を発火するかどうかを制御します。\n確認 / キャンセルボタンはクリック後、先にビジネスコールバックを発火し、そのあと `onClose` を発火します。ホストはこれに従って `visible` を false にします。",
      preview = { DocDialogSemanticAndMaskPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "表示するかどうか。false の場合は何も描画しない", "`Boolean`", "—")),
    DocRow(cells = listOf("`onConfirm`", "確認ボタンクリック時に発火", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "キャンセルボタンクリック時に発火", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "確認 / キャンセル後、または（closable の場合）オーバーレイのタップで発火", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`title`", "タイトル", "`String?`", "`null`")),
    DocRow(cells = listOf("`content`", "本文の内容", "`String?`", "`null`")),
    DocRow(cells = listOf("`type`", "セマンティックタイプ。確認ボタンのタイプ（色）を決定", "`LhDialogType`", "`default`")),
    DocRow(cells = listOf("`confirmText`", "確認ボタンのテキスト。空の場合は確認ボタンを表示しない", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "キャンセルボタンのテキスト。空の場合はキャンセルボタンを表示しない", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`closable`", "オーバーレイのタップ / 戻るボタンで onClose を発火するかどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`titleContent`", "タイトル内容（title を上書き、spec の title スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`contentContent`", "本文の内容（content を上書き、spec の default スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`footer`", "底部のボタン領域（デフォルトのボタンを上書き、spec の footer スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子（内容カードに作用）", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhDialogType",
      headers = listOf("値", "確認ボタンのタイプ"),
      rows = listOf(
        DocRow(cells = listOf("`default` / `primary` / `success` / `warning` / `danger` / `info`", "同名の `LhButtonType`。対応するセマンティック色を使用")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`dialog.width`", "内容カードの幅（320dp）")),
    DocRow(cells = listOf("`dialog.borderRadius`", "カードの角丸（`radius.base` に追従）")),
    DocRow(cells = listOf("`dialog.paddingHorizontal` / `dialog.paddingVertical`", "カードのパディング（`spacing.lg` に追従）")),
    DocRow(cells = listOf("`dialog.titleFontSize` / `dialog.contentFontSize`", "タイトル / 本文のフォントサイズ")),
    DocRow(cells = listOf("`dialog.gap`", "タイトル、本文、ボタン領域の間隔")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "階層のセマンティックアラインメント（Compose `Dialog` がウィンドウレベルの階層を管理）")),
    DocRow(cells = listOf("`color.background.overlay`", "オーバーレイ色")),
    DocRow(cells = listOf("`color.background.default`", "カードの背景色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "タイトル / 本文の色")),
    DocRow(cells = listOf("`shadow.base`", "カードのシャドウ（Compose は spread 非対応のため、ぼかし半径を elevation に使用）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm`", "確認が onConfirm + onClose を発火", "`LhDialogTest.confirm_emits_onConfirm_and_onClose`（Compose UI Test）")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "確認後に onClose を発火", "同上")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "キャンセルが onCancel + onClose を発火", "`LhDialogTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`closable-overlay-emits-onclose`", "closable の場合、オーバーレイのタップで onClose を発火", "`LhDialogTest.closable_overlay_emits_onClose`")),
    DocRow(cells = listOf("`not-closable-blocks-overlay-close`", "非 closable の場合、オーバーレイのタップを握りつぶす", "`LhDialogTest.not_closable_blocks_overlay_close`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "非表示のときは描画しない", "`LhDialogTest.hidden_is_not_rendered`")),
    DocRow(cells = listOf("`title-renders-text`", "タイトルがテキストを描画", "`LhDialogTest.title_renders_text`")),
    DocRow(cells = listOf("`danger-type-uses-danger-token`", "danger タイプの確認ボタンは危険色を使用", "`LhDialogDefaultsTest.danger_type_maps_to_danger_button`")),
  ),
  commands = listOf(

    "./gradlew :components:dialog:test                             # JVM 部分",
    "./gradlew :components:dialog:connectedAndroidTest             # インタラクション部分（デバイスが必要）",
  ),
)
