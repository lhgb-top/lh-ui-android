package top.lhgb.ui.icon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/icon.json 的 click-emits-onclick）：
 * Compose UI Test 在设备/模拟器上断言点击是否真正触发 onClick。
 * 需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhIconTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: click-emits-onclick
  @Test
  fun click_emits_onClick() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhIcon(name = "star", onClick = { clicks++ })
      }
    }
    composeRule.onNodeWithContentDescription("star").assertIsDisplayed().performClick()
    assertEquals(1, clicks)
  }

  /** 未登记图标名安全：不绘制、不崩、点击区域不存在 */
  @Test
  fun unknown_name_is_safe() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhIcon(name = "not-registered-icon", onClick = { clicks++ })
      }
    }
    composeRule.onNodeWithContentDescription("not-registered-icon").assertDoesNotExist()
    assertEquals("未绘制就不应有点击", 0, clicks)
  }
}
