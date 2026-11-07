---
'@lh-ui/android': patch
---

Maven 发布链路补齐（D22 单坐标 `top.lhgb.ui:android`，此前 `buildSrc` 发布插件从未落地）：

- 新增约定插件 `buildSrc`（id `lhui.publishing`）：maven-publish + signing；版本读 `gradle.properties` 的 `LHUI_VERSION`（首个 Gradle 侧消费者）；release singleVariant + sources / javadoc jar；POM 元数据（name / description / url / Apache-2.0 / developers / scm / inceptionYear，口径对齐 windows / harmony）；Central（centralUsername / centralPassword）+ GitHub Packages（gpr.*）双仓库；签名三键不齐时本地免签；注册任务别名 `publishToMavenCentralRelease` / `publishToGitHubPackages`（家族命令速查已固化）
- 新增聚合发布模块 `:lhui`：main 源集汇聚 `core` + 全部 `components/*` 的 `src/main/kotlin`（组件只有 kotlin 源、零 res、零 R 引用，单一 namespace 无冲突），发布为唯一坐标——**POM 只列外部依赖（Compose BOM / androidx），不含任何内部 project 坐标**，与发包流程 §5.3「components 并入主库、POM 改写到主坐标」等价且更彻底；开发态不变（docsapp / docsui 继续依赖原模块）
- 修复 `LhText.kt` 的既有编译错误：`Modifier.height(appearance.lineHeight)` 传了 TextUnit（应为 Dp），经 LocalDensity 换算；补 `core` 与 `components/button` 缺失但已被 consumerProguardFiles 引用的 `consumer-rules.pro`
- 本地验证（publishToMavenLocal）：产物齐全（aar / sources / javadoc / pom / module）、POM 元数据齐全且无内部坐标、AAR 含全部 15 组件 + theme + tokens；AGENTS.md 增「发包」章节与命令、README 补安装章节
