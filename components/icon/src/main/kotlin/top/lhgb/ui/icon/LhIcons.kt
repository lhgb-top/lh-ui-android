package top.lhgb.ui.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 图标名 → Material 图标矢量的本地注册表。
 *
 * spec 的 `name` 是平台无关的图标名，各端按本地图标体系解析（behaviors：
 * 「绘制指令为 draw_icon，由后端按 name 解析本地图形资源」）。Android 端
 * 用 Material Icons 兜底；**未登记的图标名返回 null（不绘制），不抛异常**。
 *
 * 命名用 kebab-case（跨端通用约定），与 web/ios/harmony 端对齐。
 */
object LhIcons {
  private val icons: Map<String, ImageVector> = mapOf(
    "search" to Icons.Filled.Search,
    "refresh" to Icons.Filled.Refresh,
    "check" to Icons.Filled.Check,
    "check-circle" to Icons.Filled.CheckCircle,
    "close" to Icons.Filled.Close,
    "clear" to Icons.Filled.Clear,
    "star" to Icons.Filled.Star,
    "warning" to Icons.Filled.Warning,
    "info" to Icons.Filled.Info,
    "plus" to Icons.Filled.Add,
    "minus" to Icons.Filled.Remove,
    "arrow-left" to Icons.AutoMirrored.Filled.ArrowBack,
    "arrow-right" to Icons.AutoMirrored.Filled.ArrowForward,
    "chevron-right" to Icons.AutoMirrored.Filled.KeyboardArrowRight,
  )

  /** 解析图标名；null 或未登记名返回 null（调用方决定是否绘制）。 */
  fun resolve(name: String?): ImageVector? = name?.let { icons[it] }

  /** 该图标名是否已登记（测试与文档模拟器用）。 */
  fun contains(name: String?): Boolean = name != null && name in icons
}
