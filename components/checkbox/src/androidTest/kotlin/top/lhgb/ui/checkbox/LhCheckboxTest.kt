package top.lhgb.ui.checkbox

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
 * 黄金用例的交互部分（cases/checkbox.json）：
 * tap-toggles-and-emits-onchange / tap-checked-unchecks /
 * disabled-blocks-change / indeterminate-becomes-checked。
 *
 * Compose UI Test 在设备/模拟器上断言点击是否真正触发 onCheckedChange。
 * 需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhCheckboxTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: tap-toggles-and-emits-onchange
  @Test
  fun tap_unchecked_emits_true() {
    var emitted by mutableStateOf(false)
    composeRule.setContent {
      LhTheme {
        LhCheckbox(checked = false, onCheckedChange = { emitted = it }, label = "未选中")
      }
    }
    composeRule.onNodeWithText("未选中").assertIsDisplayed().performClick()
    assertEquals("未选态点击应发射 true", true, emitted)
  }

  // case: tap-checked-unchecks
  @Test
  fun tap_checked_emits_false() {
    var emitted by mutableStateOf(true)
    composeRule.setContent {
      LhTheme {
        LhCheckbox(checked = true, onCheckedChange = { emitted = it }, label = "已选中")
      }
    }
    composeRule.onNodeWithText("已选中").performClick()
    assertEquals("选中态点击应发射 false", false, emitted)
  }

  // case: indeterminate-becomes-checked
  @Test
  fun tap_indeterminate_emits_true() {
    var emitted by mutableStateOf(false)
    composeRule.setContent {
      LhTheme {
        LhCheckbox(
          checked = false,
          indeterminate = true,
          onCheckedChange = { emitted = it },
          label = "半选",
        )
      }
    }
    composeRule.onNodeWithText("半选").performClick()
    assertEquals("半选态点击应变为选中（发射 true）", true, emitted)
  }

  // case: disabled-blocks-change
  @Test
  fun disabled_blocks_change() {
    var changes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhCheckbox(checked = true, disabled = true, onCheckedChange = { changes++ }, label = "禁用")
      }
    }
    composeRule.onNodeWithText("禁用").performClick()
    assertEquals("禁用态点击不应触发 onCheckedChange", 0, changes)
  }
}
