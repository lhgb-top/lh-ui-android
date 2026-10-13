package top.lhgb.ui.input

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import top.lhgb.ui.theme.LhTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * 黄金用例的交互部分（cases/input.json 的 input-emits-onvaluechange /
 * clearable-emits-empty-value / disabled-blocks-input / readonly-blocks-input /
 * maxlength-truncates-input）：Compose UI Test 在设备/模拟器上断言。
 * 需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhInputTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: input-emits-onvaluechange
  @Test
  fun input_emits_onValueChange() {
    var text by mutableStateOf("")
    composeRule.setContent {
      LhTheme {
        LhInput(value = text, onValueChange = { text = it }, placeholder = "占位")
      }
    }
    composeRule.onNodeWithText("占位").assertIsDisplayed().performTextInput("hello")
    assertEquals("hello", text)
  }

  // case: clearable-emits-empty-value
  @Test
  fun clearable_emits_empty_value() {
    var text by mutableStateOf("有内容")
    composeRule.setContent {
      LhTheme {
        LhInput(value = text, onValueChange = { text = it }, clearable = true)
      }
    }
    composeRule.onNodeWithContentDescription("清除").performClick()
    assertEquals("清除按钮应触发 onValueChange(\"\")", "", text)
  }

  // case: disabled-blocks-input
  @Test
  fun disabled_blocks_input() {
    var text by mutableStateOf("初始")
    composeRule.setContent {
      LhTheme {
        LhInput(value = text, onValueChange = { text = it }, disabled = true)
      }
    }
    composeRule.onNodeWithText("初始").performTextInput("被拦截")
    assertEquals("禁用态不应接受输入", "初始", text)
  }

  // case: readonly-blocks-input
  @Test
  fun readonly_blocks_input() {
    var text by mutableStateOf("初始")
    composeRule.setContent {
      LhTheme {
        LhInput(value = text, onValueChange = { text = it }, readonly = true)
      }
    }
    composeRule.onNodeWithText("初始").performTextInput("被拦截")
    assertEquals("只读态不应接受输入", "初始", text)
  }

  // case: maxlength-truncates-input
  @Test
  fun maxlength_truncates_input() {
    var text by mutableStateOf("")
    composeRule.setContent {
      LhTheme {
        LhInput(value = text, onValueChange = { text = it }, maxlength = 3)
      }
    }
    composeRule.onNodeWithText("").performTextInput("abcdef")
    assertEquals("超出 maxlength 的字符应被截断", "abc", text)
  }

  /** 密码类型以圆点掩码（spec behaviors） */
  @Test
  fun password_masks_text() {
    composeRule.setContent {
      LhTheme {
        LhInput(value = "secret", onValueChange = {}, type = LhInputType.password)
      }
    }
    // 掩码后明文不应出现在语义树里
    composeRule.onNodeWithText("secret").assertDoesNotExist()
  }
}
