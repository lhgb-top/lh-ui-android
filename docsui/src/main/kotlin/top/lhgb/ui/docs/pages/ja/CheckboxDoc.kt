package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocCheckboxSizesPreview
import top.lhgb.ui.docs.previews.DocCheckboxStatesPreview

val checkboxDoc = DocEntry(
  id = "checkbox",
  title = "Checkbox チェックボックス",
  lede = "コントロールされるチェックボックスです。半選（indeterminate）と無効化をサポートします。",
  group = DocGroup.FORM,
  contract = "契約元: `lh-ui-core/specs/checkbox.json` · ゴールデンケース: `cases/checkbox.json`（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "状態",
      prose = "選択 / 半選はソリッドなプライマリ背景 + onPrimary のインジケーター（チェックマーク / 横棒）、未選択はボーダーのみです。",
      preview = { DocCheckboxStatesPreview() },
      code = listOf(
        "var checked by remember { mutableStateOf(false) }",
        "LhCheckbox(",
        "  checked = checked,",
        "  onCheckedChange = { checked = it },",
        "  label = \"規約に同意する\",",
        ")",
      ),
    ),
    DocSection(
      title = "サイズ",
      prose = "3 段階のボックスサイズが `checkbox.size.*` トークンに対応します。",
      preview = { DocCheckboxSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "選択されているかどうか（必須）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onCheckedChange`", "選択状態の変更コールバック。新しい選択状態を伴う", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`indeterminate`", "半選択（不確定状態）かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`disabled`", "無効かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`label`", "ラベルテキスト", "`String?`", "`null`")),
    DocRow(cells = listOf("`lhSize`", "ボックスサイズ", "`LhCheckboxSize`", "`Medium`")),
    DocRow(cells = listOf("`labelContent`", "ラベル内容（label を上書き、spec の default スロット）", "`@Composable (() -> Unit)?`", "`null`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhCheckboxSize",
      headers = listOf("値", "サイズトークン"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`checkbox.size.small`（16dp）")),
        DocRow(cells = listOf("`Medium`", "`checkbox.size.medium`（20dp）")),
        DocRow(cells = listOf("`Large`", "`checkbox.size.large`（24dp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`checkbox.size.{small,medium,large}`", "3 段階のボックスサイズ")),
    DocRow(cells = listOf("`checkbox.borderRadius` / `checkbox.borderWidth`", "ボックスの角丸とボーダー幅")),
    DocRow(cells = listOf("`checkbox.checkWidth`", "チェックマーク / 横棒のボーダー幅")),
    DocRow(cells = listOf("`checkbox.gap`", "ボックスとラベルの間隔")),
    DocRow(cells = listOf("`color.primary.default` / `color.onPrimary`", "選択背景色 / インジケーター色")),
    DocRow(cells = listOf("`color.border.base`", "未選択のボーダー色")),
    DocRow(cells = listOf("`color.disabled.{bg,border,text}`", "無効状態の三色")),
    DocRow(cells = listOf("`color.text.primary`", "ラベル色")),
    DocRow(cells = listOf("`typography.fontSize.base`", "ラベルのフォントサイズ")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "タップが反転し onChange を発火", "`LhCheckboxTest.tap_unchecked_emits_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-unchecks`", "選択状態のタップで選択解除", "`LhCheckboxTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`indeterminate-becomes-checked`", "半選のタップで選択状態になる", "`LhCheckboxTest.tap_indeterminate_emits_true`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "無効状態は発火しない", "`LhCheckboxTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-tokens`", "選択がプライマリトークンを消費", "`LhCheckboxDefaultsTest.checked_uses_primary_tokens`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "未選択がボーダートークンを消費", "`LhCheckboxDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:checkbox:test                            # JVM 部分",
    "./gradlew :components:checkbox:connectedAndroidTest           # インタラクション部分（デバイスが必要）",
  ),
)
