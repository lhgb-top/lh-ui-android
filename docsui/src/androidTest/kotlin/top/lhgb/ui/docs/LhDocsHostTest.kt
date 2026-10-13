package top.lhgb.ui.docs

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import top.lhgb.ui.docs.model.DocGroup

/**
 * 原生文档模块的交互回归（黄金用例之外的宿主层验证）。
 *
 * 覆盖「拖入即用」的核心通路：首页分组列表 → 进组件文档页 → 系统返回回首页 → 暗色切换，
 * 外加 Props 表「自然列宽 + 横滑」的回归（对应移动端表格被压没的修复）。
 * MIUI 禁止 shell input 注入事件，交互断言只能走 instrumentation（本测试）。
 *
 * 注意：文档页内容远超一屏，`assertIsDisplayed` 只认视口内节点，所以跨分组的断言
 * 必须先 `performScrollTo` 滚进视口，否则在真机上必然误报「未展示」。
 */
@RunWith(AndroidJUnit4::class)
class LhDocsHostTest {

  @get:Rule
  val composeRule = createAndroidComposeRule<ComponentActivity>()

  @Test
  fun home_shows_groups_and_component_entries() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    // 顶栏标题（固定在视口内）
    composeRule.onNodeWithText("lh-ui 文档").assertIsDisplayed()
    // 四个分组标题 + 组件页入口都在长列表里，先滚进视口再断言
    DocGroup.entries.forEach { group ->
      composeRule.onNodeWithText(group.label).performScrollTo().assertIsDisplayed()
    }
    composeRule.onNodeWithText("Button 按钮").performScrollTo().assertIsDisplayed()
    composeRule.onNodeWithText("Picker 底部滚轮选择器").performScrollTo().assertIsDisplayed()
  }

  @Test
  fun tapping_entry_opens_component_doc_page() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    composeRule.onNodeWithText("Button 按钮").performScrollTo().performClick()
    // 等待 AnimatedContent 滑入转场结束，避免新旧页面同时存在导致节点歧义
    composeRule.waitForIdle()

    // 首个示例段标题（首页没有「类型」字样，能证明已进详情页）：
    // 「类型」在详情页内出现多次（章节标题 + Props/枚举表头），取树序首个
    composeRule.onAllNodesWithText("类型")[0].performScrollTo().assertIsDisplayed()
    // Props 表头
    composeRule.onAllNodesWithText("属性")[0].getUnclippedBoundsInRoot()
  }

  /**
   * Props 表内容比手机内容区宽时，应保持自然列宽并整体横向滚动，
   * 而不是按权重把列压成竖排窄条（移动端「表格宽度被压缩没了」的回归守护）。
   *
   * 判据：末列「默认值」的左缘应落在屏幕右缘之外——表格被压没时四列必然全挤在一屏内。
   */
  @Test
  fun props_table_scrolls_horizontally_instead_of_squeezing_columns() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    composeRule.onNodeWithText("Button 按钮").performScrollTo().performClick()
    composeRule.waitForIdle()

    val rootRect = composeRule.onRoot().getUnclippedBoundsInRoot()
    val rootWidth = rootRect.right - rootRect.left
    val lastHeaderLeft = composeRule.onNodeWithText("默认值").getUnclippedBoundsInRoot().left
    assert(lastHeaderLeft >= rootWidth) {
      "Props 表应比内容区宽（末列左缘 $lastHeaderLeft 应不小于屏幕宽 $rootWidth），而不是把列压没"
    }
  }

  @Test
  fun system_back_from_detail_returns_to_home() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    // 先进详情页
    composeRule.onNodeWithText("Button 按钮").performScrollTo().performClick()
    composeRule.waitForIdle()
    composeRule.onAllNodesWithText("类型")[0].performScrollTo().assertIsDisplayed()

    // 系统返回键：应出栈回首页，而不是直接 finish 掉文档 Activity
    composeRule.runOnIdle {
      composeRule.activity.onBackPressedDispatcher.onBackPressed()
    }
    composeRule.waitForIdle()

    // 回到首页：顶栏标题 + 四个分组标题都还在
    composeRule.onNodeWithText("lh-ui 文档").assertIsDisplayed()
    DocGroup.entries.forEach { group ->
      composeRule.onNodeWithText(group.label).performScrollTo().assertIsDisplayed()
    }
  }

  @Test
  fun dark_toggle_switches_theme() {
    var dark = false
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme(dark = dark) {
        LhDocsHost(dark = dark, onDarkChange = { dark = it })
      }
    }

    // 顶栏右侧切换按钮：亮色时文案是「暗色」
    composeRule.onNodeWithText("暗色").performClick()
    assert(dark) { "点击暗色切换后宿主状态应翻转为 true" }
  }
}
