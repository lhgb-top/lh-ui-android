import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugins.signing.SigningExtension

/**
 * lh-ui android 家族 Maven 发布约定插件（D22 单坐标口径）。
 *
 * - 坐标：groupId 固定 `top.lhgb.ui`；artifactId 取模块的 `lhuiArtifactId` 扩展属性
 *   （主库模块为 `android`，见 settings 中的 :lhui 聚合模块）；version 读
 *   gradle.properties 的 LHUI_VERSION（由 scripts/sync-version.mjs 从 package.json
 *   单向同步，禁止手改）
 * - 产物：release singleVariant + sources jar + javadoc jar（Maven Central 硬性要求）
 * - POM：name / description / url / Apache-2.0 / developers / scm / inceptionYear，
 *   元数据口径与 windows（NuGet） / harmony（oh-package） 对齐
 * - 双仓库：Maven Central（centralUsername / centralPassword）+ GitHub Packages
 *   （gprUsername / gprPassword）；凭据放用户级 ~/.gradle/gradle.properties 或
 *   CI 环境变量 ORG_GRADLE_PROJECT_*，**禁止入库**
 * - 签名：signingKeyId / signingPassword / signingSecretKeyRingFile 三键齐全才签名
 *   （本地 publishToMavenLocal 无凭据时跳过，保证本地验证零门槛）
 * - 任务别名：publishToMavenCentralRelease / publishToGitHubPackages
 *   （docs/发包流程.md §5.3 与家族命令速查已固化这两个名字）
 *
 * 已发布版本不可回滚（Central 只能发新版本），发版前必须先跑
 * publishToMavenLocal 预验（见各模块 AGENTS 或 docs/发包流程.md §十）。
 */
class LhuiPublishingPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    project.plugins.apply("maven-publish")
    project.plugins.apply("signing")

    val artifactId =
      project.findProperty("lhuiArtifactId") as? String
        ?: error("使用 lhui.publishing 的模块必须声明 extra[\"lhuiArtifactId\"]（主库为 \"android\"）")
    val version =
      (project.findProperty("LHUI_VERSION") as? String)?.takeIf { it.isNotBlank() }
        ?: error("gradle.properties 缺少 LHUI_VERSION（由 scripts/sync-version.mjs 同步，禁止手改）")

    // 注意：Android library 的 publishing singleVariant（withSourcesJar / withJavadocJar）
    // 由各模块 android 块自行声明——本插件不引用 AGP 类型，避免插件 classpath 与
    // 主构建的插件解析冲突（plugin is already on the classpath with an unknown version）。
    //
    // AGP 的 release softwareComponent 在 android 块评估后才注册，
    // publication / signing 配置统一延迟到 afterEvaluate。

    project.afterEvaluate {
      project.extensions.configure<PublishingExtension> {
        publications {
          create("release", MavenPublication::class.java) {
            this.groupId = "top.lhgb.ui"
            this.artifactId = artifactId
            this.version = version

            from(project.components["release"])

            pom { configurePom() }
          }
        }
        repositories {
          maven {
            name = "MavenCentral"
            url = project.uri("https://central.sonatype.com/repository/maven-central/")
            credentials {
              username = project.findProperty("centralUsername") as? String
              password = project.findProperty("centralPassword") as? String
            }
          }
          maven {
            name = "GitHubPackages"
            url = project.uri("https://maven.pkg.github.com/lhgb-top/lh-ui-android")
            credentials {
              username = project.findProperty("gprUsername") as? String
              password = project.findProperty("gprPassword") as? String
            }
          }
        }
      }

      // 签名：三键齐全才配置（缺凭据时本地 publishToMavenLocal 免签通过）
      val keyId = project.findProperty("signingKeyId") as? String
      val signingPassword = project.findProperty("signingPassword") as? String
      val ringFile =
        (project.findProperty("signingSecretKeyRingFile") as? String)?.let { project.file(it) }
      project.extensions.configure<SigningExtension> {
        if (keyId != null && signingPassword != null && ringFile != null) {
          useInMemoryPgpKeys(keyId, ringFile.readText(), signingPassword)
          sign(project.extensions.getByType(PublishingExtension::class.java).publications["release"])
        }
      }
    }

    // 任务别名：docs/发包流程.md §5.3 与根 AGENTS.md 命令速查已固化的发布入口
    project.tasks.register("publishToMavenCentralRelease") {
      group = "publishing"
      description = "发布 release publication 到 Maven Central（central.* 凭据需就绪）"
      dependsOn("publishReleasePublicationToMavenCentralRepository")
    }
    project.tasks.register("publishToGitHubPackages") {
      group = "publishing"
      description = "发布 release publication 到 GitHub Packages（gpr.* 凭据需就绪）"
      dependsOn("publishReleasePublicationToGitHubPackagesRepository")
    }
  }

  /** POM 元数据：Maven Central 上架硬性要求，口径对齐 windows / harmony 两端 */
  private fun MavenPom.configurePom() {
    name.set("lh-ui Android")
    description.set("lh-ui 原生血脉 Android 端：Kotlin + Jetpack Compose UI 库")
    url.set("https://www.lhgb.top/lhui/android/")
    inceptionYear.set("2026")
    licenses {
      license {
        name.set("The Apache License, Version 2.0")
        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
        distribution.set("repo")
      }
    }
    developers {
      developer {
        id.set("lhgb")
        name.set("lhgb")
        email.set("i@lhgb.top")
        url.set("https://www.lhgb.top")
      }
    }
    scm {
      url.set("https://github.com/lhgb-top/lh-ui-android")
      connection.set("scm:git:git://github.com/lhgb-top/lh-ui-android.git")
      developerConnection.set("scm:git:ssh://git@github.com/lhgb-top/lh-ui-android.git")
    }
  }
}
