#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""把 docs/components/*.md + docs/guide/*.md 解析为 docsui 模块的 DocEntry 数据文件。

数据驱动：新增/改文档只重跑本脚本。预览 composable（真 Compose 渲染）手写在
top.lhgb.ui.docs.previews 包，本脚本按 section 标题映射引用。
"""
import os
import re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT_DIR = os.path.join(ROOT, "docsui", "src", "main", "kotlin", "top", "lhgb", "ui", "docs", "pages")

COMPONENTS = [
    ("button", "Button", "BASIC"),
    ("icon", "Icon", "BASIC"),
    ("tag", "Tag", "BASIC"),
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
}

TERMINAL = {"API", "令牌对照", "黄金用例对照"}


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


def parse_md(path):
    with open(path, encoding="utf-8") as fh:
        lines = fh.read().split("\n")
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

    # contract：连续 > 行
    contract_parts = []
    while i < len(lines) and lines[i].strip().startswith(">"):
        t = strip_md(lines[i].strip().lstrip("> "))
        if t:
            contract_parts.append(t)
        i += 1
    contract = " ".join(contract_parts) or None

    sections, props, enums, tokens, cases, commands = [], [], [], [], [], []
    mode = None          # SECTION / API / TOKENS / CASES
    cur_section = None
    sub_title = None     # ### 子标题（API 模式）

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
            if name == "API":
                mode = "API"
            elif name == "令牌对照":
                mode = "TOKENS"
            elif name == "黄金用例对照":
                mode = "CASES"
            else:
                mode = "SECTION"
                cur_section = {
                    "title": name,
                    "prose": [],
                    "code": [],
                    "scene": SCENE_MAP.get(name),
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
    }


def row_list(rows, indent):
    if not rows:
        return "emptyList()"
    inner = []
    for r in rows:
        cells = ", ".join(kt_str(clean_cell(c)) for c in r)
        inner.append(f"{indent}  DocRow(cells = listOf({cells})),")
    return "listOf(\n" + "\n".join(inner) + f"\n{indent})"


def emit_entry(parsed, var_name, group, entry_id, preview_owner, is_guide):
    preview_imports = set()

    section_src = []
    for s in parsed["sections"]:
        prose = "\n".join(s["prose"]).strip()
        preview_name = None
        if s["scene"] and not is_guide:
            preview_name = f"Doc{preview_owner}{s['scene']}Preview"
            preview_imports.add(f"top.lhgb.ui.docs.previews.{preview_name}")
        parts = [
            "    DocSection(",
            f'      title = {kt_str(s["title"])},',
            f'      prose = {kt_str(prose)},',
        ]
        if preview_name:
            parts.append(f"      preview = {{ {preview_name}() }},")
        if s["code"]:
            code_lines = ",\n".join(f"        {kt_str(c)}" for c in s["code"])
            parts.append(f"      code = listOf(\n{code_lines},\n      ),")
        parts.append("    ),")
        section_src.append("\n".join(parts))

    sb = ["package top.lhgb.ui.docs.pages", ""]
    sb.extend([
        "import top.lhgb.ui.docs.model.DocEntry",
        "import top.lhgb.ui.docs.model.DocGroup",
        "import top.lhgb.ui.docs.model.DocRow",
        "import top.lhgb.ui.docs.model.DocSection",
        "import top.lhgb.ui.docs.model.DocTable",
    ])
    if preview_imports:
        sb.append("")
        sb.extend(sorted(f"import {p}" for p in preview_imports))
    sb.append("")
    sb.append(f"val {var_name} = DocEntry(")
    sb.append(f'  id = {kt_str(entry_id)},')
    sb.append(f'  title = {kt_str(parsed["title"])},')
    sb.append(f'  lede = {kt_str(parsed["lede"])},')
    sb.append(f'  group = DocGroup.{group},')
    if parsed["contract"]:
        sb.append(f'  contract = {kt_str(parsed["contract"])},')
    if section_src:
        sb.append("  sections = listOf(")
        sb.append("\n".join(section_src))
        sb.append("  ),")
    if parsed["props"]:
        sb.append(f"  props = {row_list(parsed['props'], '  ')},")
    if parsed["enums"]:
        enum_src = []
        for title, headers, rows in parsed["enums"]:
            if not rows or not headers:
                continue
            headers_s = ", ".join(kt_str(h) for h in headers)
            rows_s = row_list(rows, "      ")
            enum_src.append(
                "    DocTable(\n"
                f"      title = {kt_str(title)},\n"
                f"      headers = listOf({headers_s}),\n"
                f"      rows = {rows_s},\n"
                "    ),"
            )
        if enum_src:
            sb.append("  enums = listOf(")
            sb.append("\n".join(enum_src))
            sb.append("  ),")
    if parsed["tokens"]:
        sb.append(f"  tokens = {row_list(parsed['tokens'], '  ')},")
    if parsed["cases"]:
        sb.append(f"  cases = {row_list(parsed['cases'], '  ')},")
    if parsed["commands"]:
        cmd_lines = ",\n".join(f"    {kt_str(c)}" for c in parsed["commands"])
        sb.append("  commands = listOf(\n")
        sb.append(cmd_lines + ",\n  ),")
    sb.append(")")
    sb.append("")
    return "\n".join(sb)


def main():
    os.makedirs(OUT_DIR, exist_ok=True)
    for stem, pascal, group in COMPONENTS:
        path = os.path.join(ROOT, "docs", "components", f"{stem}.md")
        parsed = parse_md(path)
        src = emit_entry(parsed, f"{stem}Doc", group, stem, pascal, is_guide=False)
        out = os.path.join(OUT_DIR, f"{pascal}Doc.kt")
        with open(out, "w", encoding="utf-8") as fh:
            fh.write(src)
        n_prev = sum(1 for s in parsed["sections"] if s["scene"])
        print(f"{pascal}: sections={len(parsed['sections'])} previews={n_prev} "
              f"props={len(parsed['props'])} enums={len(parsed['enums'])} "
              f"tokens={len(parsed['tokens'])} cases={len(parsed['cases'])} cmds={len(parsed['commands'])}")

    for stem, pascal in GUIDES:
        path = os.path.join(ROOT, "docs", "guide", f"{stem}.md")
        parsed = parse_md(path)
        src = emit_entry(parsed, f"guide{pascal}Doc", "GUIDE", f"guide-{stem}", pascal, is_guide=True)
        out = os.path.join(OUT_DIR, f"Guide{pascal}Doc.kt")
        with open(out, "w", encoding="utf-8") as fh:
            fh.write(src)
        print(f"guide-{stem}: sections={len(parsed['sections'])} cmds={len(parsed['commands'])}")


if __name__ == "__main__":
    main()
