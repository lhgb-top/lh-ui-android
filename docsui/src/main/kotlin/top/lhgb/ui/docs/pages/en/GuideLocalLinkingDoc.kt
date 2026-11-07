package top.lhgb.ui.docs.pages.en

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.model.DocRow
import top.lhgb.ui.docs.model.DocSection
import top.lhgb.ui.docs.model.DocTable

val guideLocalLinkingDoc = DocEntry(
  id = "guide-local-linking",
  title = "Local Linking",
  lede = "",
  group = DocGroup.GUIDE,
  sections = listOf(
    DocSection(
      title = "Family layout convention",
      prose = "When every repo shares the same parent directory, tokens are linked locally; when a sibling repo is missing (a single-repo CI clone), the toolchain falls back to the npm package automatically.\nThis mirrors the vite alias guard mechanism of the web bloodline (master plan D18).",
      code = listOf(
        "lh-ui/                        # family parent directory (not a repo)",
        "├── lh-ui-core/               # token source of truth + codegen",
        "├── lh-ui-android/            # this repo",
        "├── lh-ui-ios/                # sibling repos",
        "├── lh-ui-harmony/",
        "└── lh-ui-windows/",
      ),
    ),
    DocSection(
      title = "Three-level guard",
      prose = "`scripts/link-tokens.mjs` looks up the token artifacts by priority:",
    ),
    DocSection(
      title = "Daily workflow",
      prose = "",
    ),
    DocSection(
      title = "CI gate",
      prose = "",
    ),
    DocSection(
      title = "What not to do",
      prose = "• ❌ Hand-editing `core/src/main/kotlin/top/lhgb/ui/tokens/` (generated output, gitignored)\n• ❌ Committing generated token files to the repo\n• ❌ Writing color or spacing literals in components (e.g. `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)\n• ❌ Committing `file:` / `link:` dependency forms into `build.gradle.kts` (local linking is local-only)",
    ),
    DocSection(
      title = "Troubleshooting",
      prose = "",
    ),
  ),
  commands = listOf(

    "pnpm link:tokens:check          # checks whether tokens are in sync (file-count comparison; exit code 1 if not)",
  ),
)
