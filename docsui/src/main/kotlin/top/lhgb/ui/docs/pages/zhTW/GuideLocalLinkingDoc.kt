package top.lhgb.ui.docs.pages.zhTW

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "本地聯調",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "家族布局約定",
      prose = "各倉同父目錄時，令牌本地直連；兄弟倉不存在（CI 單倉 clone）時自動回退 npm 套件。\n這與 web 血脈的 vite alias 守衛機制同構（總綱 D18）。",
      code = listOf(
        "lh-ui/                        # 家族父目錄（非倉庫）",
        "├── lh-ui-core/               # 令牌真源 + codegen",
        "├── lh-ui-android/            # 本倉",
        "├── lh-ui-ios/                # 兄弟倉",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "三級守衛",
      prose = "`scripts/link-tokens.mjs` 按優先級查找令牌產物：",
    ),
    DocSection(
      title = "日常流程",
      prose = "",
    ),
    DocSection(
      title = "CI 門禁",
      prose = "",
    ),
    DocSection(
      title = "禁止事項",
      prose = "• ❌ 手改 `core/src/main/kotlin/top/lhgb/ui/tokens/`（生成物，gitignore）\n• ❌ 把生成的令牌檔案提交入庫\n• ❌ 在元件裡寫色值/間距字面量（如 `Color(0xFF2B4C7C)`、`16.dp`、`14.sp`）\n• ❌ 把 `file:` / `link:` 依賴形態寫入 `build.gradle.kts` 提交（聯調僅限本地）",
    ),
    DocSection(
      title = "排查",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # 偵測令牌是否已同步（檔案數比對，未同步則退出碼 1）",
  ),
)
