package top.lhgb.ui.docs

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
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
 * 外加 Props 窄屏折叠卡片的回归。MIUI 禁止 shell input 注入事件，交互断言只能走
 * instrumentation（本测试）。
 *
 * 断言文案一律按当前 Locale 动态解析（[android.content.Context.getString] /
 * [DocRegistry.all]），不在测试里写死中文，保证在任意系统语言的设备上一致。
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

    val context = composeRule.activity
    // 顶栏标题（固定在视口内，按当前 Locale 的资源文案断言）
    composeRule.onNodeWithText(context.getString(top.lhgb.ui.docsui.R.string.doc_title))
      .assertIsDisplayed()
    // 四个分组标题 + 组件页入口都在长列表里，先滚进视口再断言
    DocGroup.entries.forEach { group ->
      composeRule.onNodeWithText(context.getString(group.labelRes)).performScrollTo().assertIsDisplayed()
    }
    val entries = DocRegistry.all(context)
    listOf("button", "picker").forEach { id ->
      val title = entries.first { it.id == id }.title
      composeRule.onNodeWithText(title).performScrollTo().assertIsDisplayed()
    }
  }

  @Test
  fun tapping_entry_opens_component_doc_page() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    val context = composeRule.activity
    val entry = DocRegistry.all(context).first { it.id == "button" }
    composeRule.onNodeWithText(entry.title).performScrollTo().performClick()
    // 等待 AnimatedContent 滑入转场结束，避免新旧页面同时存在导致节点歧义
    composeRule.waitForIdle()

    // 首个示例段标题（首页没有该字样，能证明已进详情页）：
    // 段标题在详情页内可能出现多次（章节 + 表头），取树序首个
    val firstSectionTitle = entry.sections.first().title
    composeRule.onAllNodesWithText(firstSectionTitle)[0].performScrollTo().assertIsDisplayed()
    // Props 表头（资源文案）
    composeRule.onAllNodesWithText(context.getString(top.lhgb.ui.docsui.R.string.doc_prop_name))[0]
      .getUnclippedBoundsInRoot()
  }

  /**
   * 手机视口下 Props 表折叠为字段卡片（窄屏断点以内），四列数据完整可达——
   * 字段标签（资源文案）逐张卡片渲染，而不是把列压成竖排窄条
   * （移动端「表格宽度被压缩没了」的回归守护，断言随窄屏卡片形态更新）。
   */
  @Test
  fun props_table_renders_all_fields_on_narrow_screen() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    val context = composeRule.activity
    val entry = DocRegistry.all(context).first { it.id == "button" }
    composeRule.onNodeWithText(entry.title).performScrollTo().performClick()
    composeRule.waitForIdle()

    listOf(
      top.lhgb.ui.docsui.R.string.doc_prop_name,
      top.lhgb.ui.docsui.R.string.doc_prop_desc,
      top.lhgb.ui.docsui.R.string.doc_prop_type,
      top.lhgb.ui.docsui.R.string.doc_prop_default,
    ).forEach { resId ->
      composeRule.onAllNodesWithText(context.getString(resId))[0].performScrollTo().assertIsDisplayed()
    }
  }

  @Test
  fun system_back_from_detail_returns_to_home() {
    composeRule.setContent {
      top.lhgb.ui.theme.LhTheme { LhDocsHost(dark = false, onDarkChange = {}) }
    }

    val context = composeRule.activity
    val entry = DocRegistry.all(context).first { it.id == "button" }
    // 先进详情页
    composeRule.onNodeWithText(entry.title).performScrollTo().performClick()
    composeRule.waitForIdle()
    composeRule.onAllNodesWithText(entry.sections.first().title)[0].performScrollTo().assertIsDisplayed()

    // 系统返回键：应出栈回首页，而不是直接 finish 掉文档 Activity
    composeRule.runOnIdle {
      composeRule.activity.onBackPressedDispatcher.onBackPressed()
    }
    composeRule.waitForIdle()

    // 回到首页：顶栏标题 + 四个分组标题都还在
    composeRule.onNodeWithText(context.getString(top.lhgb.ui.docsui.R.string.doc_title)).assertIsDisplayed()
    DocGroup.entries.forEach { group ->
      composeRule.onNodeWithText(context.getString(group.labelRes)).performScrollTo().assertIsDisplayed()
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

    // 顶栏右侧切换按钮已改为图标按钮（moon/sun，无文案），
    // 按导航栏右侧区域的无障碍描述定位点击
    composeRule.onNodeWithContentDescription(composeRule.activity.getString(top.lhgb.ui.core.R.string.lh_a11y_right)).performClick()
    composeRule.waitForIdle()
    assert(dark) { "点击暗色切换后宿主状态应翻转为 true" }
  }
}
