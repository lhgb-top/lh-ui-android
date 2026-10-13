package top.lhgb.ui.navbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/navbar.json）：
 * leftclick-emits-onleftclick / rightclick-emits-onrightclick /
 * no-left-content-no-onleftclick / title-renders-text。Compose UI Test 在设备/模拟器
 * 上断言。需连接设备或模拟器运行（`connectedAndroidTest`）。
 *
 * 左右可点击区域用 semantics 的 contentDescription（「左侧」/「右侧」）定位；无内容时
 * 该节点不存在（即不响应点击）。
 */
class LhNavbarTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: title-renders-text
  @Test
  fun title_renders_text() {
    composeRule.setContent {
      LhTheme {
        LhNavbar(title = "青花瓷", onLeftClick = {}, onRightClick = {})
      }
    }
    composeRule.onNodeWithText("青花瓷").assertIsDisplayed()
  }

  // case: leftclick-emits-onleftclick
  @Test
  fun left_click_emits_onLeftClick() {
    var leftClicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhNavbar(
          title = "标题",
          leftText = "返回",
          onLeftClick = { leftClicks++ },
          onRightClick = {},
        )
      }
    }
    composeRule.onNodeWithContentDescription("左侧").assertIsDisplayed().performClick()
    assertEquals("点击左侧应触发 onLeftClick", 1, leftClicks)
  }

  // case: rightclick-emits-onrightclick
  @Test
  fun right_click_emits_onRightClick() {
    var rightClicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhNavbar(
          title = "标题",
          rightText = "编辑",
          onLeftClick = {},
          onRightClick = { rightClicks++ },
        )
      }
    }
    composeRule.onNodeWithContentDescription("右侧").assertIsDisplayed().performClick()
    assertEquals("点击右侧应触发 onRightClick", 1, rightClicks)
  }

  // case: no-left-content-no-onleftclick
  @Test
  fun no_left_content_does_not_emit_onLeftClick() {
    var leftClicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhNavbar(title = "标题", onLeftClick = { leftClicks++ }, onRightClick = {})
      }
    }
    // 无左侧内容时「左侧」可点击节点不存在
    composeRule.onNodeWithContentDescription("左侧").assertDoesNotExist()
    assertEquals("无左侧内容时不应触发 onLeftClick", 0, leftClicks)
  }

  @Test
  fun left_icon_and_text_both_render() {
    composeRule.setContent {
      LhTheme {
        LhNavbar(
          title = "标题",
          leftIcon = "arrow-left",
          leftText = "返回",
          onLeftClick = {},
          onRightClick = {},
        )
      }
    }
    composeRule.onNodeWithText("返回").assertIsDisplayed()
    composeRule.onNodeWithContentDescription("左侧").assertIsDisplayed()
  }
}
