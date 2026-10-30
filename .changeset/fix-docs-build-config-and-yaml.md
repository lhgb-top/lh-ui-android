---
'@lh-ui/android': patch
---

修复文档站构建失败两处：

- `docs/.vitepress/config.ts` 侧边栏 radio 条目的 `labels` 行末多了一个 `}`（`} },`），esbuild 解析配置即报 `Unexpected "}"`。
- `docs/es/index.md` 首页 frontmatter 两处 `details` 明文标量包含「冒号+空格」（`componentes: todos`、`core: sin`），YAML 解析失败；按 fr/ru 语言的既有写法给值加上双引号。
- `docs/ru/guide/introduction.md` 的「Быстрый старт」链接指向 `./quickstart`，但 ru 语言尚未翻译该页，vitepress 死链检查失败；暂改为指向根语言 `/guide/quickstart`。ru 语言目前缺 quickstart 及全部组件页，补齐翻译前侧边栏对应入口仍会 404。
