package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "本地联调",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "家族布局约定",
      prose = "各仓同父目录时，令牌本地直连；兄弟仓不存在（CI 单仓 clone）时自动回退 npm 包。\n这与 web 血脉的 vite alias 守卫机制同构（总纲 D18）。",
      code = listOf(
        "lh-ui/                        # 家族父目录（非仓库）",
        "├── lh-ui-core/               # 令牌真源 + codegen",
        "├── lh-ui-android/            # 本仓",
        "├── lh-ui-ios/                # 兄弟仓",
        "├── lh-ui-harmony/",
        "└── lh-ui-c/",
      ),
    ),
    DocSection(
      title = "三级守卫",
      prose = "`scripts/link-tokens.mjs` 按优先级查找令牌产物：",
    ),
    DocSection(
      title = "日常流程",
      prose = "",
    ),
    DocSection(
      title = "CI 门禁",
      prose = "",
    ),
    DocSection(
      title = "禁止事项",
      prose = "• ❌ 手改 `core/src/main/kotlin/top/lhgb/ui/tokens/`（生成物，gitignore）\n• ❌ 把生成的令牌文件提交入库\n• ❌ 在组件里写色值/间距字面量（如 `Color(0xFF2B4C7C)`、`16.dp`、`14.sp`）\n• ❌ 把 `file:` / `link:` 依赖形态写入 `build.gradle.kts` 提交（联调仅限本地）",
    ),
    DocSection(
      title = "排查",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # 检测令牌是否已同步（文件数比对，未同步则退出码 1）",
  ),
)
