package top.lhgb.ui.tag

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
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/tag.json 的 closable-tap-emits-onclose 与
 * default-type-renders-text 的可见性部分）：Compose UI Test 在设备/模拟器上断言。
 * 需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhTagTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: closable-tap-emits-onclose
  @Test
  fun closable_tap_emits_onClose() {
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhTag(text = "可关闭", closable = true, onClose = { closes++ })
      }
    }
    composeRule.onNodeWithText("可关闭").assertIsDisplayed()
    composeRule.onNodeWithContentDescription("关闭").performClick()
    assertEquals(1, closes)
  }

  /** 无关闭符时不存在「关闭」语义节点 */
  @Test
  fun without_closable_no_close_node() {
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhTag(text = "普通标签", closable = false, onClose = { closes++ })
      }
    }
    composeRule.onNodeWithContentDescription("关闭").assertDoesNotExist()
    composeRule.onNodeWithText("普通标签").performClick()
    assertEquals("标签本体点击不应触发 onClose", 0, closes)
  }
}
