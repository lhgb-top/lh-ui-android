---
'@lh-ui/android': patch
---

# 开源化与 GitHub 发包链路接入

- 新增 Apache License 2.0（LICENSE）
- `package.json` 补 `license` / `repository` / `bugs` / `homepage` 元数据
- `gradle.properties` 补 `LHUI_VERSION=0.0.1`（库版本真源，供各模块 versionName 与 Maven 坐标读取）
- 新增 `scripts/sync-version.mjs`：package.json version → gradle.properties `LHUI_VERSION` 单向幂等同步（支持 `--check` 门禁）
- 新增 `scripts/push-release.mjs`：校验版本同步后打 `lh-ui-android@<版本>` tag，推 master + tag 到 GitHub（支持 `--dry-run` 自检）
- 新增 `scripts/release-notes.mjs`：从 CHANGELOG.md 提取指定版本发行版说明
- 新增 `.github/workflows/release.yml`：tag 触发，打源码 zip 建 GitHub Release 并同步 Gitee
- `package.json` 的 `changeset:version` 挂上 sync-version，`release:push` 脚本就位
- `.changeset/config.json` 的 `baseBranch` 由 `main` 改为 `master`（家族默认分支统一）
