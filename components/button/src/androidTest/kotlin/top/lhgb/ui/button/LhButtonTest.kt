package top.lhgb.ui.button

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/button.json 的 click-emits-onclick / disabled-blocks-click /
 * loading-blocks-click / disabled-precedes-loading）：Compose UI Test 在设备/模拟器上断言
 * 点击是否真正触发 onClick。需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhButtonTest {

  @get:Rule
  val composeRule = createComposeRule()

  @Test
  fun click_emits_onClick() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhButton(onClick = { clicks++ }) { Text("按钮") }
      }
    }
    composeRule.onNodeWithText("按钮").assertIsDisplayed().performClick()
    assertEquals(1, clicks)
  }

  @Test
  fun disabled_blocks_click() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhButton(disabled = true, onClick = { clicks++ }) { Text("禁用按钮") }
      }
    }
    composeRule.onNodeWithText("禁用按钮").performClick()
    assertEquals("禁用态点击不应触发 onClick", 0, clicks)
  }

  @Test
  fun loading_blocks_click() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhButton(loading = true, onClick = { clicks++ }) { Text("加载按钮") }
      }
    }
    composeRule.onNodeWithText("加载按钮").performClick()
    assertEquals("加载态点击不应触发 onClick", 0, clicks)
  }

  @Test
  fun disabled_precedes_loading_blocks_click() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhButton(disabled = true, loading = true, onClick = { clicks++ }) { Text("禁用加载按钮") }
      }
    }
    composeRule.onNodeWithText("禁用加载按钮").performClick()
    assertEquals("禁用 + 加载共存时仍不可交互", 0, clicks)
  }
}
