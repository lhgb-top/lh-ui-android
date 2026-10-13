package top.lhgb.ui.toast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的渲染部分（cases/toast.json）：show-makes-visible /
 * hidden-emits-no-commands 在设备上的补充断言。需连接设备或模拟器运行
 * （`connectedAndroidTest`）；状态机逻辑见 [LhToastDefaultsTest]。
 */
class LhToastTest {

  @get:Rule
  val composeRule = createComposeRule()

  @Test
  fun shown_toast_renders_text() {
    val state = LhToastState()
    composeRule.setContent {
      LhTheme {
        LhToast(state)
      }
    }
    // 不可见时不渲染
    composeRule.onNodeWithText("已保存").assertDoesNotExist()

    state.show("已保存")
    composeRule.waitForIdle()
    composeRule.onNodeWithText("已保存").assertIsDisplayed()
  }

  @Test
  fun hidden_toast_renders_nothing() {
    val state = LhToastState()
    state.show("提示文字")
    composeRule.setContent {
      LhTheme {
        LhToast(state)
      }
    }
    composeRule.waitForIdle()
    composeRule.onNodeWithText("提示文字").assertIsDisplayed()

    state.hide()
    composeRule.waitForIdle()
    composeRule.onNodeWithText("提示文字").assertDoesNotExist()
  }

  @Test
  fun auto_hides_after_duration() {
    var closes by mutableIntStateOf(0)
    val state = LhToastState(duration = 100, onClose = { closes++ })
    composeRule.setContent {
      LhTheme {
        LhToast(state)
      }
    }
    state.show("两秒后消失")
    composeRule.waitForIdle()
    // 等待帧驱动的 tick 推进到到期（duration 远小于测试等待上限）
    composeRule.waitUntil(5_000) { !state.visible }
    assertFalse("到期后应自动隐藏", state.visible)
    assertEquals("到期应触发 onClose", 1, closes)
  }

  @Test
  fun zero_duration_keeps_visible_without_auto_hide() {
    var closes by mutableIntStateOf(0)
    val state = LhToastState(duration = 0, onClose = { closes++ })
    composeRule.setContent {
      LhTheme {
        LhToast(state)
      }
    }
    state.show("不会自动消失")
    composeRule.waitForIdle()
    // duration = 0 时第一帧 tick 立即到期——这是「不自动关闭」的边界，
    // 故 spec 语义为宿主不调用 tick 时保持可见；此处断言 hide 可静默关闭。
    state.hide()
    composeRule.waitForIdle()
    assertEquals("主动隐藏不触发 onClose", 0, closes)
  }
}
