package top.lhgb.ui.textarea

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/textarea.json 的 input-emits-onvaluechange /
 * disabled-blocks-input / maxlength-truncates-input 与 showcount 的渲染部分）：
 * Compose UI Test 在设备/模拟器上断言。需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhTextareaTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: input-emits-onvaluechange
  @Test
  fun input_emits_onValueChange() {
    var text by mutableStateOf("")
    composeRule.setContent {
      LhTheme {
        LhTextarea(value = text, onValueChange = { text = it }, placeholder = "占位")
      }
    }
    composeRule.onNodeWithText("占位").assertIsDisplayed().performTextInput("hello")
    assertEquals("hello", text)
  }

  // case: disabled-blocks-input
  @Test
  fun disabled_blocks_input() {
    var text by mutableStateOf("初始")
    composeRule.setContent {
      LhTheme {
        LhTextarea(value = text, onValueChange = { text = it }, disabled = true)
      }
    }
    composeRule.onNodeWithText("初始").performTextInput("被拦截")
    assertEquals("禁用态不应接受输入", "初始", text)
  }

  // case: showcount-renders-count —— 格式「当前/最大」
  @Test
  fun showcount_renders_count() {
    composeRule.setContent {
      LhTheme {
        LhTextarea(value = "两行", onValueChange = {}, showCount = true, maxlength = 10)
      }
    }
    composeRule.onNodeWithText("两行").assertIsDisplayed()
    composeRule.onNodeWithText("2/10").assertIsDisplayed()
  }

  // case: showcount-without-maxlength-renders-length-only
  @Test
  fun showcount_without_maxlength_renders_length_only() {
    composeRule.setContent {
      LhTheme {
        LhTextarea(value = "三行文字", onValueChange = {}, showCount = true)
      }
    }
    composeRule.onNodeWithText("3").assertIsDisplayed()
  }

  // case: maxlength-truncates-input
  @Test
  fun maxlength_truncates_input() {
    var text by mutableStateOf("")
    composeRule.setContent {
      LhTheme {
        LhTextarea(value = text, onValueChange = { text = it }, maxlength = 3)
      }
    }
    composeRule.onNodeWithText("").performTextInput("abcdef")
    assertEquals("超出 maxlength 的字符应被截断", "abc", text)
  }

  /** 只读态不接受输入（spec behaviors） */
  @Test
  fun readonly_blocks_input() {
    var text by mutableStateOf("初始")
    composeRule.setContent {
      LhTheme {
        LhTextarea(value = text, onValueChange = { text = it }, readonly = true)
      }
    }
    composeRule.onNodeWithText("初始").performTextInput("被拦截")
    assertEquals("只读态不应接受输入", "初始", text)
  }
}
