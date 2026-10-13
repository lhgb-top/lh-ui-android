package top.lhgb.ui.cell

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
 * 黄金用例的交互部分（cases/cell.json 的 clickable-emits-onclick /
 * not-clickable-no-onclick 与 title-and-value-render 的可见性部分）：
 * Compose UI Test 在设备/模拟器上断言。需连接设备或模拟器运行（`connectedAndroidTest`）。
 */
class LhCellTest {

  @get:Rule
  val composeRule = createComposeRule()

  // case: title-and-value-render
  @Test
  fun title_and_value_render() {
    composeRule.setContent {
      LhTheme {
        LhCell(title = "行标题", value = "行内容")
      }
    }
    composeRule.onNodeWithText("行标题").assertIsDisplayed()
    composeRule.onNodeWithText("行内容").assertIsDisplayed()
  }

  // case: clickable-emits-onclick
  @Test
  fun clickable_emits_onClick() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhCell(title = "可点", value = "值", clickable = true, onClick = { clicks++ })
      }
    }
    composeRule.onNodeWithText("可点").assertIsDisplayed().performClick()
    assertEquals(1, clicks)
  }

  // case: not-clickable-no-onclick
  @Test
  fun not_clickable_no_onClick() {
    var clicks by mutableIntStateOf(0)
    composeRule.setContent {
      LhTheme {
        LhCell(title = "不可点", onClick = { clicks++ })
      }
    }
    composeRule.onNodeWithText("不可点").performClick()
    assertEquals("非 clickable 时不应触发 onClick", 0, clicks)
  }

  /** label 与必填红星被渲染 */
  @Test
  fun label_and_required_render() {
    composeRule.setContent {
      LhTheme {
        LhCell(title = "必填项", label = "说明文字", required = true)
      }
    }
    composeRule.onNodeWithText("必填项").assertIsDisplayed()
    composeRule.onNodeWithText("说明文字").assertIsDisplayed()
    composeRule.onNodeWithText("*").assertIsDisplayed()
  }
}
