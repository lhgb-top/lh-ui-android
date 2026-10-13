package top.lhgb.ui.switch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
 * 黄金用例的交互部分（cases/switch.json）：
 * tap-toggles-and-emits-onchange / tap-checked-turns-off /
 * disabled-blocks-change / loading-blocks-change。Compose UI Test 在设备/模拟器上
 * 断言点击是否真正触发 onChange。需连接设备或模拟器运行（`connectedAndroidTest`）。
 *
 * switch 无文字标签，用 semantics 的 contentDescription（「已开启」/「已关闭」）定位节点。
 */
class LhSwitchTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: tap-toggles-and-emits-onchange
  @Test
  fun tap_unchecked_emits_true() {
    var emitted by mutableStateOf(false)
    composeRule.setContent {
      LhTheme {
        LhSwitch(checked = false, onChange = { emitted = it })
      }
    }
    composeRule.onNodeWithContentDescription("已关闭").assertIsDisplayed().performClick()
    assertEquals("关闭态点击应发射 checked = true", true, emitted)
  }

  // case: tap-checked-turns-off
  @Test
  fun tap_checked_emits_false() {
    var emitted by mutableStateOf(true)
    composeRule.setContent {
      LhTheme {
        LhSwitch(checked = true, onChange = { emitted = it })
      }
    }
    composeRule.onNodeWithContentDescription("已开启").performClick()
    assertEquals("开启态点击应发射 checked = false", false, emitted)
  }

  // case: disabled-blocks-change
  @Test
  fun disabled_blocks_change() {
    var changes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhSwitch(checked = false, disabled = true, onChange = { changes++ })
      }
    }
    composeRule.onNodeWithContentDescription("已关闭").performClick()
    assertEquals("禁用态点击不应触发 onChange", 0, changes)
  }

  // case: loading-blocks-change
  @Test
  fun loading_blocks_change() {
    var changes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhSwitch(checked = false, loading = true, onChange = { changes++ })
      }
    }
    composeRule.onNodeWithContentDescription("已关闭").performClick()
    assertEquals("加载态点击不应触发 onChange", 0, changes)
  }
}
