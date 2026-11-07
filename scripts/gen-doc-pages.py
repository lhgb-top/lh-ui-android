#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""把 docs/components/*.md + docs/guide/*.md 解析为 docsui 模块的 DocEntry 数据文件。

**多语言**：逐语言解析 docs/<lang>/ 下的同名 md（根目录即简体中文），输出到
`docsui/.../pages/`（zh，保持与历史同路径）与 `docsui/.../pages/<lang>/`（其余语言）
两个位置；另生成 `DocLocalePages.kt` 提供 `docEntriesFor(code)` 聚合入口。

数据驱动：新增/改文档只重跑本脚本。预览 composable（真 Compose 渲染）手写在
top.lhgb.ui.docs.previews 包，本脚本按 section 位置映射引用——各语言 md 与中文版
结构逐节对齐，预览归属按节序号从中文 master 转移；对齐失败时告警并降级为无预览。

所有路径均为 pathlib.Path 并经 [path_under] 校验（段名白名单 + resolve 边界检查），
禁止离开仓库根目录。
"""
import os
import re
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
DOCS_DIR = ROOT / "docs"
OUT_DIR = ROOT / "docsui" / "src" / "main" / "kotlin" / "top" / "lhgb" / "ui" / "docs" / "pages"

# (语言代码, docs 子目录名)；"" 子目录 = 根（简体中文，与文档站 root=zh-CN 同口径）。
LOCALES = [
    ("zh", ""),
    ("en", "en"),
    ("zh-TW", "zh-TW"),
    ("ja", "ja"),
    ("ko", "ko"),
    ("es", "es"),
    ("fr", "fr"),
    ("ru", "ru"),
    ("ar", "ar"),
]

COMPONENTS = [
    ("button", "Button", "BASIC"),
    ("icon", "Icon", "BASIC"),
    ("tag", "Tag", "BASIC"),
    ("text", "Text", "BASIC"),
    ("loading", "Loading", "BASIC"),
    ("cell", "Cell", "BASIC"),
    ("input", "Input", "FORM"),
    ("textarea", "Textarea", "FORM"),
    ("checkbox", "Checkbox", "FORM"),
    ("radio", "Radio", "FORM"),
    ("switch", "Switch", "FORM"),
    ("dialog", "Dialog", "OVERLAY"),
    ("toast", "Toast", "OVERLAY"),
    ("picker", "Picker", "OVERLAY"),
    ("navbar", "Navbar", "OVERLAY"),
]

GUIDES = [
    ("introduction", "Introduction"),
    ("installation", "Installation"),
    ("quickstart", "Quickstart"),
    ("theming", "Theming"),
    ("local-linking", "LocalLinking"),
]

SCENE_MAP = {
    "类型": "Types",
    "尺寸": "Sizes",
    "状态": "States",
    "图标与花纹底": "IconAndPattern",
    "明暗主题": "Theme",
    "基本用法": "Basic",
    "必填与链接": "RequiredAndLink",
    "状态与单选组": "StatesAndGroup",
    "尺寸与色调": "SizeAndTone",
    "语义色调": "Tones",
    "图标注册表": "Registry",
    "尺寸与焦点": "SizeAndFocus",
    "镂空（plain）": "Plain",
    "尺寸与标记": "SizeAndMark",
    "字数统计": "Counter",
    "语义类型": "Types",
    "定位": "Position",
    "用法": "Basic",
    "左右内容": "Slots",
    "选中机制": "Selection",
    "语义类型与遮罩": "SemanticAndMask",
    # text
    "字号级别": "Levels",
    "色调": "Tones",
    "字重": "Weight",
}

# 终止段（不再产生 DocSection，内容落入 props/tokens/cases 数据）：中文标题 → 模式。
TERMINAL_NAMES = {
    "API": "API",
    "令牌对照": "TOKENS",
    "黄金用例对照": "CASES",
}


def safe_name(name: str) -> str:
    """路径段白名单：仅允许字母数字 _ - .，且不得是 . / .. 等逃逸段。"""
    if not re.fullmatch(r"[A-Za-z0-9_.\-]+", name) or name in ("..", "."):
        raise SystemExit("非法路径段，拒绝拼接: " + name)
    return name


def path_under(root: Path, *parts: str) -> Path:
    """把 parts 拼到 root 下，先过 [safe_name] 白名单，再 resolve 校验边界。"""
    joined = root.joinpath(*[safe_name(p) for p in parts])
    real = joined.resolve()
    root_real = root.resolve()
    if real != root_real and not str(real).startswith(str(root_real) + os.sep):
        raise SystemExit("路径越界，拒绝写入/读取: " + str(joined))
    return joined


def package_suffix(code: str) -> str:
    """Kotlin 包后缀：zh 无后缀（沿用 pages 根包），zh-TW → zhTw。"""
    return "" if code == "zh" else code.replace("-", "")


def strip_md(line: str) -> str:
    line = re.sub(r"\[([^\]]*)\]\([^)]*\)", r"\1", line)
    line = line.replace("<code>", "").replace("</code>", "")
    line = re.sub(r"\*\*([^*]*)\*\*", r"\1", line)
    line = line.replace(r"\|", "|")
    return line.strip()


def clean_cell(cell: str) -> str:
    cell = cell.strip()
    cell = re.sub(r"\[([^\]]*)\]\([^)]*\)", r"\1", cell)
    cell = cell.replace("<code>", "").replace("</code>", "")
    cell = re.sub(r"\*\*([^*]*)\*\*", r"\1", cell)
    cell = cell.replace(r"\|", "|")
    return cell.strip()


def kt_str(s: str) -> str:
    return '"' + s.replace("\\", "\\\\").replace("\n", "\\n").replace('"', '\\"') + '"'


def parse_table(lines, start):
    headers, rows = [], []
    i = start
    while i < len(lines):
        line = lines[i].strip()
        if not line.startswith("|"):
            break
        cells = [clean_cell(c) for c in line.strip("|").split("|")]
        if all(re.fullmatch(r":?-{2,}:?", c) for c in cells if c):
            i += 1
            continue
        if not headers:
            headers = cells
        else:
            rows.append(cells)
        i += 1
    return headers, rows, i


def skip_code(lines, i):
    """从 ``` 行开始，返回 (block_lines, next_index)。"""
    i += 1
    block = []
    while i < len(lines) and not lines[i].strip().startswith("```"):
        block.append(lines[i])
        i += 1
    return block, i + 1


def skip_html(lines, i):
    depth = 0
    started = False
    while i < len(lines):
        line = lines[i]
        depth += line.count("<div")
        depth -= line.count("</div>")
        started = True
        i += 1
        if started and depth <= 0:
            break
    return i


def parse_md(path: Path, decisions=None):
    """解析单个 md。

    :param decisions: `##` 标题序列对应的 (mode, scene) 决策（按节序号）。
        None = 按标题名自行判定（中文 master 走这条路，并把自身决策放进返回值，
        供其他语言按位置复用）。
    """
    lines = path.read_text(encoding="utf-8").split("\n")
    title = lines[0].lstrip("# ").strip()

    # lede：标题后到 > / ## / <div / ``` 之前
    lede_parts, i = [], 1
    while i < len(lines):
        line = lines[i].strip()
        if not line:
            i += 1
            continue
        if line.startswith((">", "##", "<div", "```")):
            break
        lede_parts.append(strip_md(line))
        i += 1
    lede = "\n".join(lede_parts)

    # contract：连续 > 行（md 自带各语言的「契约源/Spec source」标签，原样保留）
    contract_parts = []
    while i < len(lines) and lines[i].strip().startswith(">"):
        t = strip_md(lines[i].strip().lstrip("> "))
        if t:
            contract_parts.append(t)
        i += 1
    contract = " ".join(contract_parts) or None

    sections, props, enums, tokens, cases, commands = [], [], [], [], [], []
    heading_modes = []  # 实际生效的 (mode, scene) 序列（供对齐复用）
    mode = None          # SECTION / API / TOKENS / CASES
    cur_section = None
    sub_title = None     # ### 子标题（API 模式）
    heading_idx = 0

    while i < len(lines):
        line = lines[i].strip()

        if line.startswith("```"):
            block, i = skip_code(lines, i)
            lang = line.strip("` ").lower()
            if lang in ("bash", "sh"):
                commands = [b.strip() for b in block if b.strip()]
            elif mode == "SECTION" and cur_section is not None:
                cur_section["code"].extend(block)
            continue

        if line.startswith("<div"):
            i = skip_html(lines, i)
            continue
        if line.startswith("<p") or line.startswith("<details") or line.startswith("</details"):
            i += 1
            continue

        if line.startswith("### "):
            sub_title = line[4:].strip()
            i += 1
            continue

        if line.startswith("## "):
            name = line[3:].strip()
            i += 1
            sub_title = None
            cur_section = None
            if decisions is None:
                mode = TERMINAL_NAMES.get(name, "SECTION")
                scene = SCENE_MAP.get(name)
            elif heading_idx < len(decisions):
                mode, scene = decisions[heading_idx]
            else:
                # 目标语言节数多于中文 master：降级为普通 SECTION（无预览）
                mode, scene = "SECTION", None
            heading_modes.append((mode, scene))
            heading_idx += 1
            if mode == "SECTION":
                cur_section = {
                    "title": name,
                    "prose": [],
                    "code": [],
                    "scene": scene,
                }
                sections.append(cur_section)
            continue

        if not line:
            i += 1
            continue

        if line.startswith("|"):
            headers, rows, i = parse_table(lines, i)
            if mode == "API":
                if sub_title == "Props":
                    props = rows
                elif sub_title and rows:
                    enums.append((sub_title, headers, rows))
            elif mode == "TOKENS":
                tokens = rows
            elif mode == "CASES":
                cases = rows
            continue

        if mode == "SECTION" and cur_section is not None:
            text = strip_md(line)
            if text.startswith("- "):
                text = "• " + text[2:]
            elif text.startswith("> "):
                text = text[2:]
            if text:
                cur_section["prose"].append(text)
        i += 1

    return {
        "title": title,
        "lede": lede,
        "contract": contract,
        "sections": sections,
        "props": props,
        "enums": enums,
        "tokens": tokens,
        "cases": cases,
        "commands": commands,
        "heading_modes": heading_modes,
    }


def row_list(rows, indent):
    if not rows:
        return "emptyList()"
    inner = []
    for r in rows:
        cells = ", ".join(kt_str(clean_cell(c)) for c in r)
        inner.append(f"{indent}  DocRow(cells = listOf({cells})),")
    return "listOf(\n" + "\n".join(inner) + f"\n{indent})"


def emit_entry(parsed, var_name, group, entry_id, preview_owner, is_guide, suffix):
    preview_imports = set()

    section_src = []
    for s in parsed["sections"]:
        prose = "\n".join(s["prose"]).strip()
        preview_name = None
        if s["scene"] and not is_guide:
            preview_name = "Doc" + preview_owner + s["scene"] + "Preview"
            preview_imports.add("top.lhgb.ui.docs.previews." + preview_name)
        parts = [
            "    DocSection(",
            "      title = " + kt_str(s["title"]) + ",",
            "      prose = " + kt_str(prose) + ",",
        ]
        if preview_name:
            parts.append("      preview = { " + preview_name + "() },")
        if s["code"]:
            code_lines = ",\n".join("        " + kt_str(c) for c in s["code"])
            parts.append("      code = listOf(\n" + code_lines + ",\n      ),")
        parts.append("    ),")
        section_src.append("\n".join(parts))

    sb = ["package top.lhgb.ui.docs.pages" + ("" if not suffix else "." + suffix), ""]
    sb.extend([
        "import top.lhgb.ui.docs.model.DocEntry",
        "import top.lhgb.ui.docs.model.DocGroup",
        "import top.lhgb.ui.docs.model.DocRow",
        "import top.lhgb.ui.docs.model.DocSection",
        "import top.lhgb.ui.docs.model.DocTable",
    ])
    if preview_imports:
        sb.append("")
        sb.extend(sorted("import " + p for p in preview_imports))
    sb.append("")
    sb.append("val " + var_name + " = DocEntry(")
    sb.append("  id = " + kt_str(entry_id) + ",")
    sb.append("  title = " + kt_str(parsed["title"]) + ",")
    sb.append("  lede = " + kt_str(parsed["lede"]) + ",")
    sb.append("  group = DocGroup." + group + ",")
    if parsed["contract"]:
        sb.append("  contract = " + kt_str(parsed["contract"]) + ",")
    if section_src:
        sb.append("  sections = listOf(")
        sb.append("\n".join(section_src))
        sb.append("  ),")
    if parsed["props"]:
        sb.append("  props = " + row_list(parsed["props"], "  ") + ",")
    if parsed["enums"]:
        enum_src = []
        for title, headers, rows in parsed["enums"]:
            if not rows or not headers:
                continue
            headers_s = ", ".join(kt_str(h) for h in headers)
            rows_s = row_list(rows, "      ")
            enum_src.append(
                "    DocTable(\n"
                "      title = " + kt_str(title) + ",\n"
                "      headers = listOf(" + headers_s + "),\n"
                "      rows = " + rows_s + ",\n"
                "    ),"
            )
        if enum_src:
            sb.append("  enums = listOf(")
            sb.append("\n".join(enum_src))
            sb.append("  ),")
    if parsed["tokens"]:
        sb.append("  tokens = " + row_list(parsed["tokens"], "  ") + ",")
    if parsed["cases"]:
        sb.append("  cases = " + row_list(parsed["cases"], "  ") + ",")
    if parsed["commands"]:
        cmd_lines = ",\n".join("    " + kt_str(c) for c in parsed["commands"])
        sb.append("  commands = listOf(\n")
        sb.append(cmd_lines + ",\n  ),")
    sb.append(")")
    sb.append("")
    return "\n".join(sb)


def entry_ids():
    """DocRegistry 首页顺序：指南 → 基础 → 表单 → 反馈与层叠。"""
    ids = [("guide" + pascal + "Doc", "guide-" + stem) for stem, pascal in GUIDES]
    ids += [(stem + "Doc", stem) for stem, _pascal, _group in COMPONENTS]
    return ids


def emit_locale_pages():
    """生成 DocLocalePages.kt：语言代码 → 该语言的全部 DocEntry。"""
    lines = [
        "package top.lhgb.ui.docs.pages",
        "",
        "import top.lhgb.ui.docs.model.DocEntry",
        "",
        "/**",
        " * 语言 → 文档页数据。由 `scripts/gen-doc-pages.py` 生成，**禁止手改**：",
        " * 改文档改 docs 下各语言目录的 md 后重跑脚本。",
        " *",
        " * 顺序对齐 [top.lhgb.ui.docs.DocRegistry]：指南 → 基础 → 表单 → 反馈与层叠。",
        " */",
        "fun docEntriesFor(code: String): List<DocEntry> = when (code) {",
    ]
    for code, _dir in LOCALES:
        lines.append('  "' + code + '" -> ' + code.replace("-", "") + "Entries")
    lines.append("  else -> zhEntries")
    lines.append("}")
    lines.append("")
    for code, _dir in LOCALES:
        suffix = package_suffix(code)
        list_name = code.replace("-", "") + "Entries"
        # 同包直接引用；其他语言用全限定路径（Kotlin 不支持相对包子段引用）
        prefix = "" if not suffix else "top.lhgb.ui.docs.pages." + suffix + "."
        refs = ", ".join(prefix + var for var, _id in entry_ids())
        lines.append("private val " + list_name + " = listOf(")
        lines.append("  " + refs + ",")
        lines.append(")")
        lines.append("")
    lines.append("")
    return "\n".join(lines)


def md_path(lang_dir, kind, stem):
    """docs[/<lang>]/components|guide/<stem>.md，路径全部走 [path_under] 校验。"""
    stem_md = safe_name(stem) + ".md"
    kind = safe_name(kind)
    if lang_dir:
        return path_under(DOCS_DIR, safe_name(lang_dir), kind, stem_md)
    return path_under(DOCS_DIR, kind, stem_md)


def page_out(out_dir, pascal, is_guide):
    """输出文件路径：pages[/<lang>]/<Pascal>Doc.kt，全部走 [path_under] 校验。"""
    name = safe_name(pascal) + "Doc.kt"
    if is_guide:
        name = "Guide" + name
    return path_under(out_dir, name)


def main():
    warns = []

    for code, lang_dir in LOCALES:
        suffix = package_suffix(code)
        out_dir = OUT_DIR if not suffix else path_under(OUT_DIR, safe_name(suffix))
        out_dir.mkdir(parents=True, exist_ok=True)

        for stem, pascal, group in COMPONENTS:
            path = md_path(lang_dir, "components", stem)
            if not path.exists():
                warns.append("[skip] " + code + ": 缺 " + str(path))
                continue
            zh_parsed = parse_md(md_path("", "components", stem))
            decisions = zh_parsed["heading_modes"]
            if code == "zh":
                parsed = zh_parsed
            else:
                parsed = parse_md(path, decisions)
                # 结构对齐校验：props/tokens 行数最能反映终止段是否对齐
                if len(parsed["props"]) != len(zh_parsed["props"]) or len(parsed["tokens"]) != len(zh_parsed["tokens"]):
                    warns.append(
                        "[align] " + code + "/" + stem + ": props "
                        + str(len(zh_parsed["props"])) + "→" + str(len(parsed["props"]))
                        + ", tokens " + str(len(zh_parsed["tokens"])) + "→" + str(len(parsed["tokens"]))
                    )

            src = emit_entry(parsed, stem + "Doc", group, stem, pascal, is_guide=False, suffix=suffix)
            out = page_out(out_dir, pascal, is_guide=False)
            out.write_text(src, encoding="utf-8")
            n_prev = sum(1 for s in parsed["sections"] if s["scene"])
            print(code + "/" + pascal + ": sections=" + str(len(parsed["sections"]))
                  + " previews=" + str(n_prev)
                  + " props=" + str(len(parsed["props"])) + " enums=" + str(len(parsed["enums"]))
                  + " tokens=" + str(len(parsed["tokens"])) + " cases=" + str(len(parsed["cases"]))
                  + " cmds=" + str(len(parsed["commands"])))

        for stem, pascal in GUIDES:
            path = md_path(lang_dir, "guide", stem)
            if not path.exists():
                warns.append("[skip] " + code + ": 缺 " + str(path))
                continue
            zh_parsed = parse_md(md_path("", "guide", stem))
            parsed = zh_parsed if code == "zh" else parse_md(path, zh_parsed["heading_modes"])
            src = emit_entry(parsed, "guide" + pascal + "Doc", "GUIDE", "guide-" + stem, pascal, is_guide=True, suffix=suffix)
            out = page_out(out_dir, pascal, is_guide=True)
            out.write_text(src, encoding="utf-8")
            print(code + "/guide-" + stem + ": sections=" + str(len(parsed["sections"]))
                  + " cmds=" + str(len(parsed["commands"])))

    # 语言聚合入口
    path_under(OUT_DIR, "DocLocalePages.kt").write_text(emit_locale_pages(), encoding="utf-8")
    print("wrote DocLocalePages.kt")

    if warns:
        print("\n=== 告警 ===")
        for w in warns:
            print(w)
    else:
        print("\n结构对齐：全部通过")


if __name__ == "__main__":
    main()
