# lh-ui-android —— AGENTS.md

> `@lh-ui/android` · Kotlin + Jetpack Compose · **原生血脉**成员之一。
> 权威规范：本文件 > 家族根 `AGENTS.md` > 总纲 `docs/多端组件库规划.md`。

## 一、定位与血脉

- **原生血脉**：`lh-ui-core`（JSON 令牌 + 契约 + 黄金用例 + codegen）→ android / ios / harmony / windows。
- 本仓是 Android 端 UI 层：**Kotlin + Jetpack Compose 手写 UI**，运行时零 JS、零 TS、零 npm 依赖。
- 与 web 血脉（`@lh-ui/share` → plus/vue2/react/uniapp）**互相独立、不得关联**（总纲 D13）。本仓不依赖
  `@lh-ui/share`，web 仓也不依赖本仓。视觉一致性靠《设计语言基线.md》人工对齐。
- 唯一外部令牌来源：core codegen 生成的 `LhTokens` / `LhDarkTokens`（包 `top.lhgb.ui.tokens`）。

## 二、目录职责

```
lh-ui-android/
├── settings.gradle.kts / build.gradle.kts / gradle/libs.versions.toml  # Gradle 配置
├── scripts/
│   ├── link-tokens.mjs        # 令牌本地连接（三级存在性守卫，D18）
│   └── gen-sim-tokens.mjs     # 文档模拟器令牌 CSS 生成（D19）
├── core/                      # 主题接入层模块
│   └── src/main/kotlin/top/lhgb/ui/
│       ├── theme/LhTheme.kt   # 明/暗令牌 → CompositionLocal
│       └── tokens/            # 【生成物，gitignore】link-tokens 同步的 LhTokens/LhDarkTokens
├── components/                # 每组件一个 Gradle 模块（对齐 web 五件套精神）
│   └── button/
│       ├── src/main/.../button/{LhButton,LhButtonDefaults,LhButtonType,LhButtonSize,LhButtonPreview}.kt
│       ├── src/test/.../button/LhButtonDefaultsTest.kt       # JVM JUnit（黄金用例：token 映射 + gate）
│       └── src/androidTest/.../button/LhButtonTest.kt         # Compose UI Test（点击断言，需设备）
├── docsui/                    # 【原生文档库】拖入即用：LhDocs.launch(context) 打开全部文档
│   └── src/main/kotlin/top/lhgb/ui/docs/
│       ├── LhDocs.kt          # 公共 API（launch）
│       ├── LhDocsActivity.kt  # 入口 Activity（manifest 合并到宿主，exported=false）
│       ├── LhDocsHost.kt      # 导航宿主：返回栈 + LhNavbar 顶栏 + 暗色切换 + 全局 Toast
│       ├── DocRegistry.kt     # 页面注册表（数据驱动，新增组件只加一条）
│       ├── model/             # DocEntry / DocSection / DocRow / DocTable 数据类
│       ├── widgets/           # DocHeading / DocProse / DocTable / DocCodeBlock / DocPreviewFrame
│       ├── pages/             # 首页 + 14 组件页 + 5 指南页（由 scripts/gen-doc-pages.py 从 docs/*.md 生成）
│       └── previews/          # 33 个真 Compose 可交互预览（替代网页 HTML 模拟器，手写）
├── docsapp/                   # 【独立文档 APP】极薄 application 模块，一个 LAUNCHER Activity
├── docs/                      # VitePress 文档站（内联静态 HTML 模拟器，D19；docsui 的内容真源）
└── package.json               # 构建期工具脚本入口（非运行时）
```

> 原生文档模块（`:docsui` / `:docsapp`）与网页文档站（`docs/`）同源：组件页结构 1:1 对齐
> `docs/components/*.md`，内容由 `scripts/gen-doc-pages.py` 解析 md 生成 `DocEntry` 数据。
> **改文档先改 md，再跑 `python scripts/gen-doc-pages.py`**；预览（真 Compose 交互）手写在
> `docsui/.../previews/`，脚本按 section 标题映射引用。

## 三、红线

1. **令牌唯一源**：组件实现禁止出现任何色值 / 间距 / 圆角 / 字号字面量（`Color(0xFF2B4C7C)`、`16.dp`、
   `14.sp` 等），只能引用 `LhTokens.Button.*` / `LhTheme.colors.*`。违例即 drift。
2. **core 生成的令牌文件禁止手改**：`core/src/main/kotlin/top/lhgb/ui/tokens/` 是生成物（gitignore），
   改令牌去 `lh-ui-core/tokens/*.json` → `pnpm codegen` → 本仓 `pnpm link:tokens`。
3. **只生成骨架不生成 UI**：core 不会、也不应生成任何 `@Composable` 实现体；本仓的 Composable 全部手写。
4. **血脉隔离**：禁止 `import` 任何 `@lh-ui/share` / web 仓符号；本仓不发布到 npm。
5. **黄金用例对齐**：组件行为必须满足 `lh-ui-core/cases/<comp>.json`，并人工翻译为
   `src/test`（纯逻辑：令牌映射 + 状态 gate）与 `src/androidTest`（Compose UI Test：交互断言）。
6. **命名与 spec 对齐**（R-n5）：props 名严格沿用 `lh-ui-core/specs/<comp>.json` 的驼峰命名
   （`type` / `size` / `disabled` / `loading` / `icon` / `bgPattern`），不得自创 `variant` / `dimension` 等。

## 四、令牌消费机制（D18）

core 的 codegen 产物是 `top.lhgb.ui.tokens.LhTokens`（明）与 `LhDarkTokens`（暗）两个 object。

- **暗色模型是并列对象**：`LhDarkTokens` 只含 `Color` 子对象，尺寸类令牌暗色不覆盖（四端
  android / ios / harmony / windows 一致；docs 端是浅合并）。主题层据此设计。
- **本地连接**：`pnpm link:tokens`（脚本带三级守卫：本地 `../lh-ui-core/dist/android` →
  npm `@lh-ui/core/dist/android` → 报错）。禁止把生成的令牌文件提交入库。
- **回退形态**：家族布局不存在时（CI 单仓 clone），`@lh-ui/core` npm 包的 `files:["dist"]`
  含四端产物，`pnpm i @lh-ui/core` 后 link 脚本自动回退。Maven 侧令牌随主包
  `top.lhgb.ui:android` 分发（D22 单坐标，不再单独发令牌包）。

## 五、命名规范（总纲 D20 + D22）

- **平台包名一律反域名**：Kotlin package 与 Gradle namespace 为 `top.lhgb.ui.*`
  （`top.lhgb.ui.core` / `top.lhgb.ui.button` / `top.lhgb.ui.tokens`——这是**代码包名**，
  与发包名解耦）。Maven 发包坐标为 `top.lhgb.ui:android`（D22：groupId `top.lhgb.ui` +
  平台名 artifactId，组件与令牌全部并入单坐标）。
- **自有标识符一律 LH 前缀**：类 / 枚举 / 对象 `Lh*`（`LhButton`、`LhButtonType`、`LhColors`），
  按语言惯例定大小写；第三方符号不加。
- npm 的 `@lh-ui/*` scope 不受此约束（组织已建，npm 不使用反域名）。

## 六、平台 gotcha

- **单位**：尺寸 `dp`、字号 `sp`（生成的 `LhTokens` 已带单位，直接用）。暗色对象不含尺寸令牌，
  `LhTheme` 取尺寸时始终走明色对象。
- **时长**：android 产物是**毫秒**整型（`Motion.Duration.base = 300`），与 ios（秒）不同。
- **圆角**：`Radius.circle` 是 0.5 分数，端侧取 `min(宽,高)/2` 换算，不能当 dp 用。
- **阴影**：Compose `Shadow` 不支持扩展量（spread），生成的 Shadow 已忽略 spread 并注释。
- **字体**：`Typography.fontFamily` 是字体栈字符串，端侧取首个可用族；国风衬线是默认调性。
- **@Preview** 即文档：`LhButtonPreview.kt` 是组件示例的第一现场，文档站 HTML 模拟器是第二现场。

## 七、命令速查

| 任务 | 命令 | 说明 |
| --- | --- | --- |
| 同步令牌 | `pnpm link:tokens` | 从 core dist 同步到本仓源码树（改令牌后必跑） |
| 令牌同步校验 | `pnpm link:tokens:check` | CI 门禁：检测令牌是否已同步 |
| 单测（JVM） | `./gradlew :components:button:test` | 黄金用例纯逻辑部分 |
| 仪器测试 | `./gradlew :components:button:connectedAndroidTest` | 需连接设备/模拟器 |
| 构建 | `./gradlew :components:button:build` | 产出 AAR |
| 文档 dev | `pnpm docs:dev` | 先 gen:sim 再起 VitePress（端口 5181） |
| 文档构建 | `pnpm docs:build` | 部署前跑，产物在 docs/.vitepress/dist |
| 原生文档页同步 | `python scripts/gen-doc-pages.py` | 改 docs/*.md 后跑，重新生成 docsui 的 DocEntry 数据 |
| 原生文档库编译 | `./gradlew :docsui:compileDebugKotlin` | |
| 文档 APP 打包/装机 | `./gradlew :docsapp:installDebug` | 独立分发的「lh-ui 文档」APP |
| 类型 | `./gradlew :components:button:compileDebugKotlin` | Kotlin 编译即类型检查 |
| 发布本地预验 | `./gradlew :lhui:publishToMavenLocal` | 无凭据免签名，产物落 ~/.m2 检查 POM / AAR |
| 发布 Maven Central | `./gradlew :lhui:publishToMavenCentralRelease` | 需 ~/.gradle/gradle.properties 的 central.* + signing.* 凭据 |
| 发布 GitHub Packages | `./gradlew :lhui:publishToGitHubPackages` | 需 gpr.* 凭据（GitHub PAT packages:write） |

## 八、发包（Maven 单坐标，D22）

发布链路于 2026-11-04 补齐：约定插件 `buildSrc/src/main/kotlin/LhuiPublishingPlugin.kts`
（id `lhui.publishing`）+ 聚合发布模块 `:lhui`。

- **坐标**：`top.lhgb.ui:android:<LHUI_VERSION>`（单坐标；`LHUI_VERSION` 由
  `scripts/sync-version.mjs` 从 package.json 单向同步，禁止手改）。`:lhui` 模块的
  main 源集汇聚 `core` + 全部 `components/*` 的 `src/main/kotlin`（组件只有 kotlin 源、
  零 res、零 R 引用，聚合后单一 namespace 无冲突），因此 **POM 只列外部依赖
  （Compose BOM / androidx），不含任何内部 project 坐标**——与
  [docs/发包流程.md](../docs/发包流程.md) §5.3「components 并入主库、POM 依赖改写到
  主坐标」等价且更彻底。开发态不变：docsapp / docsui 继续依赖原模块。
- **凭据**（用户级 `~/.gradle/gradle.properties` 或 CI 的 `ORG_GRADLE_PROJECT_*`
  环境变量，**禁止入库**）：发布 Central 用 `centralUsername` / `centralPassword`
  （Central Portal 令牌）+ 签名三键 `signingKeyId` / `signingPassword` /
  `signingSecretKeyRingFile`；GitHub Packages 用 `gprUsername` / `gprPassword`。
  签名三键不齐时本地 `publishToMavenLocal` 免签通过，方便零门槛预验。
- **发版纪律**：Central 已发布版本不可回滚（只能发新版本），发版前必须先跑
  `publishToMavenLocal`，检查 `~/.m2/repository/top/lhgb/ui/android/<版本>/` 的
  POM（元数据齐全、无内部坐标）与 AAR（classes.jar 含全部组件 + theme + tokens）。
- POM 元数据口径与 windows（NuGet）/ harmony（ohpm）对齐：Apache-2.0、developers
  lhgb `<i@lhgb.top>`、scm 指向 github 仓、inceptionYear 2026。
- **任务别名** `publishToMavenCentralRelease` / `publishToGitHubPackages` 由插件
  注册（docs/发包流程.md §5.3 与家族命令速查已固化这两个名字）。

## 九、Read before touching sensitive areas

- 改 `LhTheme.kt`：先读 core 的 `dist/android/top/lhgb/ui/tokens/LhDarkTokens.kt`，确认暗色覆盖范围
  （只 Color，不含尺寸），否则主题层取尺寸会拿到 null。
- 改 `LhButtonDefaults.kt`：先读 `../../lh-ui-core/specs/button.json` 的 behaviors 与 tokenRefs，
  令牌绑定必须逐条对齐；黄金用例（`cases/button.json`）会断言这些映射。
- 改 `scripts/*.mjs`：守卫逻辑改动影响四仓统一机制，先读 core 的
  `scripts/resolve-local-tokens.mjs` 与总纲 D18。
- 加新组件：先在 core 仓写 `specs/<comp>.json` + `cases/<comp>.json`（codegen 不生成 UI），
  再本仓建 `components/<comp>` 模块，翻译黄金用例为 `src/test` + `src/androidTest`；
  然后写 `docs/components/<comp>.md`（网页文档），跑 `python scripts/gen-doc-pages.py`
  同步原生文档页，并在 `DocRegistry` + `docsui/.../previews/` 补该组件的注册条与可交互预览。
- 改 `docsui` 的文档内容：内容是 `docs/*.md` 的生成物（见二节注），**禁止手改 `pages/*Doc.kt`**，
  只能手写 `previews/` 里的可交互预览 composable。

## 十、多语言（i18n）

- **语言集**：zh（根，简体）、en、zh-TW、ja、ko、es、fr、ru、ar，与文档站
  `docs/.vitepress/config.ts` 的 locales 一一对应。`values/` 默认中文。
- **文档内容**（标题/散文/表格数据）：`scripts/gen-doc-pages.py` 从九语言 md
  **逐节对齐**生成 `pages/<lang>/`（zh 保持根路径），聚合入口是生成的
  `docEntriesFor(code)`；md 与中文版结构必须对齐，脚本带 props/tokens 行数校验。
- **结构性 UI 文案**：core 的 `values/strings.xml`（`lh_*` 前缀，组件层共用：
  确认/取消/无障碍描述等）+ docsui 的 `values/strings.xml`（`doc_*` 前缀）。
  组件用 `stringResource(top.lhgb.ui.core.R.string.lh_*)`；新增 core 字符串时
  必须同步登记 `lhui/src/main/kotlin/top/lhgb/ui/core/R.kt` 聚合垫片。
- **解析与切换**：`LhAppLocale`（core）按 Configuration 解析语言代码（回退 zh），
  应用内切换 = 持久化 + Activity `attachBaseContext` 覆盖 + `finish()`+`startActivity()`
  relaunch（`recreate()` 不刷新 base context，无效）。宿主声明
  `top.lhgb.ui.docs.LhDocsApplication`（或 Activity 各自 override attachBaseContext），
  docsapp 另配 `localeConfig` 接系统 per-app 语言（API 33+）。
- **嵌宿主破坏性变更（2026-11）**：`DocGroup(label: String)` → `DocGroup(labelRes: Int)`，
  老代码 `group.label` 需改 `stringResource(group.labelRes)`。

## 备案/版权（docs 站）

文档站页脚的备案（ICP / 公安）与版权行走「同步产物 + 适配器」：
`docs/.beian/beian.json` 由工作区根 `scripts/sync-beian.mjs` 从
`lh-feng-mo-workspace` 真源生成（**同步产物，禁止手改**；真源只读，改真源后重跑同步）；
站点差异化（独立备案号、隐藏公安备案、许可证链接等）写 `docs/.beian/beian.local.json`，
同步时与真源深合并。适配器在 docs/.vitepress/beian.ts。
完整机制、渲染格式与变更流程见家族 [docs/备案资源同步方案.md](../docs/备案资源同步方案.md)。
