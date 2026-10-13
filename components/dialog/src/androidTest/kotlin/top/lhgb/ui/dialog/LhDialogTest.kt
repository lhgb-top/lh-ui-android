package top.lhgb.ui.dialog

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
 * 黄金用例的交互部分（cases/dialog.json）：
 * confirm-emits-onconfirm / cancel-emits-oncancel / confirm-also-emits-onclose /
 * closable-overlay-emits-onclose / not-closable-blocks-overlay-close /
 * hidden-not-rendered / title-renders-text。Compose UI Test 在设备/模拟器上断言。
 * 需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhDialogTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: confirm-emits-onconfirm + confirm-also-emits-onclose
  @Test
  fun confirm_emits_onConfirm_and_onClose() {
    var confirms by mutableIntStateOf(0)
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhDialog(
          visible = true,
          title = "提示",
          onConfirm = { confirms++ },
          onCancel = {},
          onClose = { closes++ },
        )
      }
    }
    composeRule.onNodeWithText("确认").assertIsDisplayed().performClick()
    assertEquals("确认按钮应触发 onConfirm", 1, confirms)
    assertEquals("确认后应触发 onClose", 1, closes)
  }

  // case: cancel-emits-oncancel（取消同样触发 onClose）
  @Test
  fun cancel_emits_onCancel_and_onClose() {
    var cancels by mutableIntStateOf(0)
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhDialog(
          visible = true,
          title = "提示",
          onConfirm = {},
          onCancel = { cancels++ },
          onClose = { closes++ },
        )
      }
    }
    composeRule.onNodeWithText("取消").assertIsDisplayed().performClick()
    assertEquals("取消按钮应触发 onCancel", 1, cancels)
    assertEquals("取消后应触发 onClose", 1, closes)
  }

  // case: closable-overlay-emits-onclose
  @Test
  fun closable_overlay_emits_onClose() {
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhDialog(
          visible = true,
          closable = true,
          onConfirm = {},
          onCancel = {},
          onClose = { closes++ },
        )
      }
    }
    composeRule.onNodeWithContentDescription("遮罩").performClick()
    assertEquals("closable 时点遮罩应触发 onClose", 1, closes)
  }

  // case: not-closable-blocks-overlay-close
  @Test
  fun not_closable_blocks_overlay_close() {
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhDialog(
          visible = true,
          closable = false,
          onConfirm = {},
          onCancel = {},
          onClose = { closes++ },
        )
      }
    }
    composeRule.onNodeWithContentDescription("遮罩").performClick()
    assertEquals("非 closable 时点遮罩不应触发 onClose", 0, closes)
  }

  // case: hidden-not-rendered
  @Test
  fun hidden_is_not_rendered() {
    composeRule.setContent {
      LhTheme {
        LhDialog(
          visible = false,
          title = "删除确认",
          onConfirm = {},
          onCancel = {},
          onClose = {},
        )
      }
    }
    composeRule.onNodeWithText("删除确认").assertDoesNotExist()
  }

  // case: title-renders-text
  @Test
  fun title_renders_text() {
    composeRule.setContent {
      LhTheme {
        LhDialog(
          visible = true,
          title = "删除确认",
          onConfirm = {},
          onCancel = {},
          onClose = {},
        )
      }
    }
    composeRule.onNodeWithText("删除确认").assertIsDisplayed()
  }
}
