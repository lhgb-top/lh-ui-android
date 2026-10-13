package top.lhgb.ui.radio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
 * 黄金用例的交互部分（cases/radio.json）：
 * tap-unchecked-emits-onchange / tap-checked-keeps-checked /
 * disabled-blocks-change。Compose UI Test 在设备/模拟器上断言点击是否真正触发
 * onChange。需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhRadioTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: tap-unchecked-emits-onchange
  @Test
  fun tap_unchecked_emits_onchange_true() {
    var emitted by mutableStateOf(false)
    composeRule.setContent {
      LhTheme {
        LhRadio(checked = false, onChange = { emitted = it }, label = "选项")
      }
    }
    composeRule.onNodeWithText("选项").assertIsDisplayed().performClick()
    assertEquals("未选态点击应发射 checked = true", true, emitted)
  }

  // case: tap-checked-keeps-checked
  @Test
  fun tap_checked_does_not_emit() {
    var changes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhRadio(checked = true, onChange = { changes++ }, label = "选项")
      }
    }
    composeRule.onNodeWithText("选项").performClick()
    assertEquals("已选中时点击应保持选中且不触发 onChange", 0, changes)
  }

  // case: disabled-blocks-change
  @Test
  fun disabled_blocks_change() {
    var changes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhRadio(checked = false, disabled = true, onChange = { changes++ }, label = "禁用")
      }
    }
    composeRule.onNodeWithText("禁用").performClick()
    assertEquals("禁用态点击不应触发 onChange", 0, changes)
  }
}
