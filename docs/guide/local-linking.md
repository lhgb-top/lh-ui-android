# 本地联调

## 家族布局约定

```
lh-ui/                        # 家族父目录（非仓库）
├── lh-ui-core/               # 令牌真源 + codegen
├── lh-ui-android/            # 本仓
├── lh-ui-ios/                # 兄弟仓
├── lh-ui-harmony/
└── lh-ui-c/
```

各仓同父目录时，令牌**本地直连**；兄弟仓不存在（CI 单仓 clone）时**自动回退 npm 包**。
这与 web 血脉的 vite alias 守卫机制同构（总纲 D18）。

## 三级守卫

`scripts/link-tokens.mjs` 按优先级查找令牌产物：

| 优先级 | 来源 | 说明 |
| --- | --- | --- |
| 1 | `../lh-ui-core/dist/android` | 本地直连：改令牌即时生效 |
| 2 | `node_modules/@lh-ui/core/dist/android` | npm 回退：`@lh-ui/core` 的 `files:["dist"]` 含四端产物 |
| 3 | — | 报错退出，缺令牌无法构建 |

## 日常流程

```bash
# 1. 改令牌（在 core 仓）
cd ../lh-ui-core
vim tokens/color.json           # 或 spacing / typography / ...

# 2. 重新生成 + 校验
pnpm codegen                    # 生成 dist/ 五端产物
pnpm verify                     # schema + 引用 + WCAG 对比度 + 契约一致性

# 3. 回本仓同步
cd ../lh-ui-android
pnpm link:tokens                # 覆盖 core/src/main/kotlin/top/lhgb/ui/tokens/

# 4. 回归
./gradlew :components:button:test
```

## CI 门禁

```bash
pnpm link:tokens:check          # 检测令牌是否已同步（文件数比对，未同步则退出码 1）
```

## 禁止事项

- ❌ 手改 `core/src/main/kotlin/top/lhgb/ui/tokens/`（生成物，gitignore）
- ❌ 把生成的令牌文件提交入库
- ❌ 在组件里写色值/间距字面量（如 `Color(0xFF2B4C7C)`、`16.dp`、`14.sp`）
- ❌ 把 `file:` / `link:` 依赖形态写入 `build.gradle.kts` 提交（联调仅限本地）

## 排查

| 症状 | 原因 | 解决 |
| --- | --- | --- |
| 编译报 `unresolved reference: LhTokens` | 令牌未同步 | `pnpm link:tokens` |
| 令牌值与预期不符 | core 仓未重新 codegen | core 仓 `pnpm codegen` 后重新 link |
| `link:tokens` 报「未找到令牌产物」 | 兄弟仓不在且未装 npm 包 | 检查家族布局或 `pnpm i -D @lh-ui/core` |
| 暗色颜色不对 | 改了明色主色但没更新暗色预计算 | 更新 `color-dark.json` 并重跑（R-n6） |
