package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocPickerBasicPreview
import top.lhgb.ui.docs.previews.DocPickerSelectionPreview

val pickerDoc = DocEntry(
  id = "picker",
  title = "Picker ピッカー",
  lede = "単一列のボトムホイールピッカーです。スクロール停止後に中央のインジケーターに最も近い項目が選択されます。",
  group = DocGroup.OVERLAY,
  contract = "契約元: `lh-ui-core/specs/picker.json` · ゴールデンケース: `cases/picker.json`（8 件、クランプとトークンマッピングは JVM 単体テストに、スクロールとボタン操作は Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "使い方",
      prose = "ボトムシートとして表示されます。上にマスク領域、下にパネル（上部は角丸）が配置されます。ツールバーは「キャンセル / タイトル / 確認」の 3 段構成です。",
      preview = { DocPickerBasicPreview() },
      code = listOf(
        "var visible by remember { mutableStateOf(false) }",
        "var value by remember { mutableStateOf(0) }",
        "val options = listOf(\"選択肢 1\", \"選択肢 2\", \"選択肢 3\")",
        "LhPicker(",
        "  visible = visible,",
        "  options = options,",
        "  onValueChange = { value = it },",
        "  onConfirm = { value = it; visible = false },",
        "  onCancel = { visible = false },",
        "  onClose = { visible = false },",
        "  value = value,",
        "  title = \"選択してください\",",
        ")",
      ),
    ),
    DocSection(
      title = "選択の仕組み",
      prose = "ホイールはスクロール可能なリストで、各項目の高さは `picker.itemHeight` です。中央のインジケーターは 2 本の水平線で、その間隔は itemHeight に等しくなります。\nスクロール停止後、スクロールした距離を四捨五入して選択インデックスを求め、変化があったときに `onValueChange` を発火します。\n`value` が範囲外のときは `LhPickerDefaults.clamp` によって候補範囲内にクランプされます。",
      preview = { DocPickerSelectionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`visible`", "表示するかどうか。false の場合は何も描画しない", "`Boolean`", "—")),
    DocRow(cells = listOf("`options`", "候補項目のテキスト配列（単一列）", "`List<String>`", "—")),
    DocRow(cells = listOf("`onValueChange`", "ホイールが停止し選択項目が変化したときに発火。新しいインデックスを伴う", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onConfirm`", "確認ボタンをタップしたときに発火。現在の選択インデックスを伴う", "`(Int) -> Unit`", "—")),
    DocRow(cells = listOf("`onCancel`", "キャンセルボタンをタップしたときに発火", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`onClose`", "確認 / キャンセル後に発火。ホストはこれに従って visible を false にする", "`() -> Unit`", "—")),
    DocRow(cells = listOf("`value`", "選択項目のインデックス（0 基点）。範囲外のときはクランプ", "`Int`", "`0`")),
    DocRow(cells = listOf("`title`", "ツールバー中央のタイトル", "`String?`", "`null`")),
    DocRow(cells = listOf("`confirmText`", "確認ボタンのテキスト", "`String`", "`\"确认\"`")),
    DocRow(cells = listOf("`cancelText`", "キャンセルボタンのテキスト", "`String`", "`\"取消\"`")),
    DocRow(cells = listOf("`content`", "ホイールの内容領域（デフォルトの候補レンダリングを上書き、spec の default スロット）", "`@Composable ((List<String>, Int) -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`toolbar`", "ツールバー（デフォルトの確認 / タイトル / キャンセルを上書き、spec の toolbar スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`listState`", "ホイールのリスト状態（デフォルトは `rememberLhPickerListState(value, options)` が提供。テスト時に外部から注入可能）", "`LazyListState`", "左記の通り")),
    DocRow(cells = listOf("`modifier`", "修飾子（パネルに作用）", "`Modifier`", "`Modifier`")),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`picker.height`", "パネルの高さ（260dp）")),
    DocRow(cells = listOf("`picker.itemHeight`", "各項目の高さ（40dp、中央インジケーターの間隔でもある）")),
    DocRow(cells = listOf("`picker.toolbarHeight`", "ツールバーの高さ（44dp）")),
    DocRow(cells = listOf("`picker.itemFontSize`", "候補項目のフォントサイズ")),
    DocRow(cells = listOf("`picker.borderRadius`", "パネル上部の角丸")),
    DocRow(cells = listOf("`picker.paddingHorizontal`", "パネルの横向きパディング（`spacing.md` に追従）")),
    DocRow(cells = listOf("`zIndex.modal` / `zIndex.modalBackdrop`", "階層のセマンティックアラインメント（Compose `Popup` がウィンドウレベルの階層を内包）")),
    DocRow(cells = listOf("`color.background.overlay`", "マスク色")),
    DocRow(cells = listOf("`color.background.default`", "パネルの背景色")),
    DocRow(cells = listOf("`color.text.primary` / `color.text.regular`", "タイトル / 候補項目の色")),
    DocRow(cells = listOf("`color.border.lighter`", "中央インジケーターの線色")),
    DocRow(cells = listOf("`color.primary.default`", "確認ボタンの色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`confirm-emits-onconfirm-with-value`", "確認が現在のインデックス + onClose を発火", "`LhPickerTest.confirm_emits_onConfirm_with_value_and_onClose`（Compose UI Test）")),
    DocRow(cells = listOf("`confirm-also-emits-onclose`", "確認後に onClose を発火", "同上")),
    DocRow(cells = listOf("`cancel-emits-oncancel`", "キャンセルが onCancel + onClose を発火", "`LhPickerTest.cancel_emits_onCancel_and_onClose`")),
    DocRow(cells = listOf("`scroll-emits-onvaluechange`", "スクロールが選択を変え onValueChange を発火", "`LhPickerTest.scroll_emits_onValueChange`")),
    DocRow(cells = listOf("`clamp-negative-value-to-zero`", "value が負数のとき 0 にクランプ", "`LhPickerDefaultsTest.clamp_negative_value_to_zero`")),
    DocRow(cells = listOf("`clamp-overflow-value-to-last`", "value が範囲外のとき末項にクランプ", "`LhPickerDefaultsTest.clamp_overflow_value_to_last`")),
    DocRow(cells = listOf("`title-renders-text`", "タイトルがテキストを描画", "`LhPickerTest.title_renders_text`")),
    DocRow(cells = listOf("`hidden-not-rendered`", "非表示のときは描画しない", "`LhPickerTest.hidden_is_not_rendered`")),
  ),
  commands = listOf(

    "./gradlew :components:picker:test                                # JVM 部分",
    "./gradlew :components:picker:connectedAndroidTest               # インタラクション部分（デバイスが必要）",
  ),
)
