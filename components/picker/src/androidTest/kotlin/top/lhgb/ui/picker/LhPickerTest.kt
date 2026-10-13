package top.lhgb.ui.picker

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/picker.json）：
 * confirm-emits-onconfirm-with-value / confirm-also-emits-onclose /
 * cancel-emits-oncancel / scroll-emits-onvaluechange / title-renders-text /
 * hidden-not-rendered。Compose UI Test 在设备/模拟器上断言。需连接设备或模拟器运行
 * （`connectedAndroidTest`）。
 */
class LhPickerTest {

  @get:Rule
  val composeRule = createComposeRule()

  private val options = listOf("甲", "乙", "丙")

  // case: confirm-emits-onconfirm-with-value + confirm-also-emits-onclose
  @Test
  fun confirm_emits_onConfirm_with_value_and_onClose() {
    var confirmValue by mutableIntStateOf(-1)
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhPicker(
          visible = true,
          options = options,
          value = 1,
          onValueChange = {},
          onConfirm = { confirmValue = it },
          onCancel = {},
          onClose = { closes++ },
        )
      }
    }
    composeRule.onNodeWithText("确认").assertIsDisplayed().performClick()
    assertEquals("确认应携带当前选中索引", 1, confirmValue)
    assertEquals("确认后应触发 onClose", 1, closes)
  }

  // case: cancel-emits-oncancel
  @Test
  fun cancel_emits_onCancel_and_onClose() {
    var cancels by mutableIntStateOf(0)
    var closes by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhPicker(
          visible = true,
          options = options,
          onValueChange = {},
          onConfirm = {},
          onCancel = { cancels++ },
          onClose = { closes++ },
        )
      }
    }
    composeRule.onNodeWithText("取消").assertIsDisplayed().performClick()
    assertEquals("取消应触发 onCancel", 1, cancels)
    assertEquals("取消后应触发 onClose", 1, closes)
  }

  // case: scroll-emits-onvaluechange
  @Test
  fun scroll_emits_onValueChange() {
    var changeValue by mutableIntStateOf(-1)
    var changes by mutableIntStateOf(0)
    var listState: LazyListState? = null
    composeRule.setContent {
      LhTheme {
        val state = rememberLhPickerListState(value = 0, options = options)
        listState = state
        LhPicker(
          visible = true,
          options = options,
          onValueChange = { changeValue = it; changes++ },
          onConfirm = {},
          onCancel = {},
          onClose = {},
          listState = state,
        )
      }
    }

    val itemHeightPx = with(composeRule.density) {
      top.lhgb.ui.tokens.LhTokens.Picker.itemHeight.toPx()
    }
    // 向上滚动两项（负 delta = 内容上移），中央指示器应落在第 2 项
    composeRule.runOnIdle { listState!!.dispatchRawDelta(-itemHeightPx * 2f) }
    composeRule.waitForIdle()

    assertEquals("滚动停止应触发 onValueChange", 1, changes)
    assertEquals("onValueChange 应携带新选中索引", 2, changeValue)
  }

  // case: title-renders-text
  @Test
  fun title_renders_text() {
    composeRule.setContent {
      LhTheme {
        LhPicker(
          visible = true,
          options = options,
          title = "选择",
          onValueChange = {},
          onConfirm = {},
          onCancel = {},
          onClose = {},
        )
      }
    }
    composeRule.onNodeWithText("选择").assertIsDisplayed()
  }

  // case: hidden-not-rendered
  @Test
  fun hidden_is_not_rendered() {
    composeRule.setContent {
      LhTheme {
        LhPicker(
          visible = false,
          options = options,
          title = "选择",
          onValueChange = {},
          onConfirm = {},
          onCancel = {},
          onClose = {},
        )
      }
    }
    composeRule.onNodeWithText("选择").assertDoesNotExist()
  }

  @Test
  fun options_are_rendered() {
    composeRule.setContent {
      LhTheme {
        LhPicker(
          visible = true,
          options = options,
          onValueChange = {},
          onConfirm = {},
          onCancel = {},
          onClose = {},
        )
      }
    }
    composeRule.onNodeWithText("甲").assertIsDisplayed()
    composeRule.onNodeWithText("确认").assertIsDisplayed()
  }
}
