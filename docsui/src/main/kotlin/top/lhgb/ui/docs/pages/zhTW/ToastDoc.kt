package top.lhgb.ui.docs.pages.zhTW

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
  title = "Toast 輕提示",
  lede = "自持可見性與計時的墨色膠囊浮層，用於輕量級操作回饋。不阻塞使用者操作。",
  group = DocGroup.OVERLAY,
  contract = "契約源：`lh-ui-core/specs/toast.json` · 黃金用例：`cases/toast.json`（6 條，狀態機 4 條 + 渲染 2 條，全部翻譯為本地測試）",
  sections = listOf(
    DocSection(
      title = "用法",
      prose = "toast 是自持狀態機：狀態由 `LhToastState` 承載，宿主呼叫 `show(text)` 顯示、`hide()` 隱藏，\n計時到期自動隱藏並觸發 `onClose`。元件在可見時按幀自驅動 `tick`，宿主也可自行呼叫接管計時。",
      preview = { DocToastBasicPreview() },
      code = listOf(
        "val state = rememberLhToastState(onClose = { /* 可選：記錄關閉 */ })",
        "Column {",
        "  LhButton(lhOnClick = { state.show(\"已儲存\") }) { Text(\"觸發\") }",
        "  LhToast(state = state)",
        "}",
      ),
    ),
    DocSection(
      title = "語義類型",
      prose = "非 `default` 類型時在文字前繪製直徑等於字級的語義色圓點。",
      preview = { DocToastTypesPreview() },
    ),
    DocSection(
      title = "定位",
      prose = "`lhPosition` 取 `top` / `center` / `bottom`，兩端各留 `spacing.xl` 邊距；寬度由文字測量得出並封頂可用寬度的 80%。",
      preview = { DocToastPositionPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`state`", "輕提示狀態機（承載可見性、文字與計時）", "`LhToastState`", "—")),
    DocRow(cells = listOf("`type`", "語義類型；非 default 時繪製語義色圓點", "`LhToastType`", "`default`")),
    DocRow(cells = listOf("`lhPosition`", "在可用區域內的定位", "`LhToastPosition`", "`center`")),
    DocRow(cells = listOf("`modifier`", "修飾符（作用於膠囊）", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhToastState",
      headers = listOf("成員", "說明"),
      rows = listOf(
        DocRow(cells = listOf("`show(text: String)`", "顯示並重設計時")),
        DocRow(cells = listOf("`hide()`", "立即隱藏，不觸發 onClose")),
        DocRow(cells = listOf("`tick(deltaMs: Int): Boolean`", "推進計時；到期時隱藏、觸發 onClose 並回傳 true")),
        DocRow(cells = listOf("`visible` / `text` / `duration`", "當前狀態（可觀察）")),
      ),
    ),
    DocTable(
      title = "LhToastType / LhToastPosition",
      headers = listOf("值", "圓點色"),
      rows = listOf(
        DocRow(cells = listOf("`default`", "無圓點")),
        DocRow(cells = listOf("`primary` / `success` / `warning` / `danger` / `info`", "對應語義色")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`toast.maxWidth`", "膠囊最大寬度（240dp；實際取可用寬度 80% 與之較小）")),
    DocRow(cells = listOf("`toast.borderRadius`", "膠囊圓角（跟隨 `radius.base`）")),
    DocRow(cells = listOf("`toast.paddingHorizontal` / `toast.paddingVertical`", "膠囊內邊距")),
    DocRow(cells = listOf("`toast.fontSize`", "文字字級（圓點直徑同此值）")),
    DocRow(cells = listOf("`toast.gap`", "圓點與文字間距")),
    DocRow(cells = listOf("`zIndex.toast`", "層級語義對齊（Compose `Popup` 自帶視窗級層級）")),
    DocRow(cells = listOf("`color.background.overlay`（主題層 ink 映射）", "膠囊底色")),
    DocRow(cells = listOf("`color.onPrimary`", "文字色")),
    DocRow(cells = listOf("`color.{primary,success,warning,danger,info}.default`", "語義圓點色")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`show-makes-visible`", "show 後可見且渲染文字", "`LhToastDefaultsTest.show_makes_visible_and_renders_text`")),
    DocRow(cells = listOf("`auto-hides-after-duration`", "到期自動隱藏並觸發 onClose", "`LhToastDefaultsTest.auto_hides_after_duration_and_emits_onClose` + `LhToastTest.auto_hides_after_duration`")),
    DocRow(cells = listOf("`tick-before-duration-keeps-visible`", "計時未到保持可見且不觸發 onClose", "`LhToastDefaultsTest.tick_before_duration_keeps_visible_and_does_not_emit`")),
    DocRow(cells = listOf("`hide-is-silent`", "hide 不觸發 onClose", "`LhToastDefaultsTest.hide_is_silent`")),
    DocRow(cells = listOf("`success-type-uses-success-token`", "success 類型消費成功色", "`LhToastDefaultsTest.success_type_uses_success_tokens`")),
    DocRow(cells = listOf("`hidden-emits-no-commands`", "不可見時不渲染", "`LhToastTest.hidden_toast_renders_nothing`")),
  ),
  commands = listOf(

    "./gradlew :components:toast:test                                  # JVM 部分",
    "./gradlew :components:toast:connectedAndroidTest                 # 互動部分（需裝置）",
  ),
)
