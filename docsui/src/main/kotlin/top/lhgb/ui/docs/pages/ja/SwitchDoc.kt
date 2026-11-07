package top.lhgb.ui.docs.pages.ja

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

import top.lhgb.ui.docs.previews.DocSwitchSizesPreview
import top.lhgb.ui.docs.previews.DocSwitchStatesPreview

val switchDoc = DocEntry(
  id = "switch",
  title = "Switch スイッチ",
  lede = "カプセル型のトラック + 円形のつまみによる 2 状態切り替えコントロールです。読み込み中の表示をサポートします。",
  group = DocGroup.FORM,
  contract = "契約元: `lh-ui-core/specs/switch.json` · ゴールデンケース: `cases/switch.json`（6 件、JVM 単体テスト + Compose UI Test に翻訳済み）",
  sections = listOf(
    DocSection(
      title = "状態",
      prose = "オンは主色のトラック、オフはボーダー色のトラックを取ります。つまみの直径 = 高さ - 2 × `switch.gap` で、移動アニメーションは `motion.duration.base` を取ります。",
      preview = { DocSwitchStatesPreview() },
      code = listOf(
        "var on by remember { mutableStateOf(false) }",
        "LhSwitch(checked = on, onChange = { on = it })",
      ),
    ),
    DocSection(
      title = "サイズ",
      prose = "3 段階のサイズが `switch.width` / `switch.height` トークンに対応します。",
      preview = { DocSwitchSizesPreview() },
    ),
  ),
  props = listOf(
    DocRow(cells = listOf("`checked`", "オンかどうか（必須）", "`Boolean`", "—")),
    DocRow(cells = listOf("`onChange`", "スイッチ状態の変更コールバック。新しいスイッチ状態を伴う", "`(Boolean) -> Unit`", "—")),
    DocRow(cells = listOf("`disabled`", "無効かどうか", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`loading`", "読み込み中かどうか（つまみの位置に読み込みリングが表示され、タップに応答しない）", "`Boolean`", "`false`")),
    DocRow(cells = listOf("`lhSize`", "サイズ", "`LhSwitchSize`", "`Medium`")),
    DocRow(cells = listOf("`modifier`", "修飾子", "`Modifier`", "`Modifier`")),
  ),
  enums = listOf(
    DocTable(
      title = "LhSwitchSize",
      headers = listOf("値", "width", "height"),
      rows = listOf(
        DocRow(cells = listOf("`Small`", "`switch.width.small`（36dp）", "`switch.height.small`（20dp）")),
        DocRow(cells = listOf("`Medium`", "`switch.width.medium`（44dp）", "`switch.height.medium`（24dp）")),
        DocRow(cells = listOf("`Large`", "`switch.width.large`（52dp）", "`switch.height.large`（28dp）")),
      ),
    ),
  ),
  tokens = listOf(
    DocRow(cells = listOf("`switch.width.{small,medium,large}`", "3 段階のトラック幅")),
    DocRow(cells = listOf("`switch.height.{small,medium,large}`", "3 段階のトラック高さ")),
    DocRow(cells = listOf("`switch.gap`", "つまみとトラックの間の余白（つまみの直径 = 高さ - 2 × gap）")),
    DocRow(cells = listOf("`color.primary.default`", "オンのトラック色")),
    DocRow(cells = listOf("`color.border.base`", "オフのトラック色")),
    DocRow(cells = listOf("`color.onPrimary`", "つまみの色")),
    DocRow(cells = listOf("`color.disabled.{bg,text}`", "無効状態のトラック / つまみの色")),
    DocRow(cells = listOf("`motion.duration.base` / `motion.easing.easeInOut`", "つまみの移動アニメーション")),
    DocRow(cells = listOf("`loading.strokeWidth`", "読み込みリングの枠線幅（再利用）")),
  ),
  cases = listOf(
    DocRow(cells = listOf("`tap-toggles-and-emits-onchange`", "タップが反転し onChange を発火", "`LhSwitchTest.tap_unchecked_emits_true`（Compose UI Test）")),
    DocRow(cells = listOf("`tap-checked-turns-off`", "オン状態のタップでオフになる", "`LhSwitchTest.tap_checked_emits_false`")),
    DocRow(cells = listOf("`disabled-blocks-change`", "無効状態は発火しない", "`LhSwitchTest.disabled_blocks_change`")),
    DocRow(cells = listOf("`loading-blocks-change`", "読み込み中は発火しない", "`LhSwitchTest.loading_blocks_change`")),
    DocRow(cells = listOf("`checked-uses-primary-token`", "オンが主色トークンを消費", "`LhSwitchDefaultsTest.checked_uses_primary_token`")),
    DocRow(cells = listOf("`unchecked-uses-border-token`", "オフがボーダートークンを消費", "`LhSwitchDefaultsTest.unchecked_uses_border_token`")),
  ),
  commands = listOf(

    "./gradlew :components:switch:test                             # JVM 部分",
    "./gradlew :components:switch:connectedAndroidTest            # インタラクション部分（デバイスが必要）",
  ),
)
