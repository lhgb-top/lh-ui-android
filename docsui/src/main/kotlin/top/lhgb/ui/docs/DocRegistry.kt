package top.lhgb.ui.docs

import top.lhgb.ui.docs.model.DocEntry
import top.lhgb.ui.docs.model.DocGroup
import top.lhgb.ui.docs.pages.buttonDoc
import top.lhgb.ui.docs.pages.cellDoc
import top.lhgb.ui.docs.pages.checkboxDoc
import top.lhgb.ui.docs.pages.dialogDoc
import top.lhgb.ui.docs.pages.guideInstallationDoc
import top.lhgb.ui.docs.pages.guideIntroductionDoc
import top.lhgb.ui.docs.pages.guideLocalLinkingDoc
import top.lhgb.ui.docs.pages.guideQuickstartDoc
import top.lhgb.ui.docs.pages.guideThemingDoc
import top.lhgb.ui.docs.pages.iconDoc
import top.lhgb.ui.docs.pages.inputDoc
import top.lhgb.ui.docs.pages.loadingDoc
import top.lhgb.ui.docs.pages.navbarDoc
import top.lhgb.ui.docs.pages.pickerDoc
import top.lhgb.ui.docs.pages.radioDoc
import top.lhgb.ui.docs.pages.switchDoc
import top.lhgb.ui.docs.pages.tagDoc
import top.lhgb.ui.docs.pages.textareaDoc
import top.lhgb.ui.docs.pages.toastDoc

/**
 * 文档页面注册表：数据驱动，新增组件只需加一条。
 *
 * 顺序严格对齐网页版 sidebar（docs/.vitepress/config.ts）：
 * 指南 → 基础组件（button → icon → tag → loading → cell）
 * → 表单组件（input → textarea → checkbox → radio → switch）
 * → 反馈与层叠（dialog → toast → picker → navbar）。
 */
object DocRegistry {

  val all: List<DocEntry> = listOf(
    // 指南
    guideIntroductionDoc,
    guideInstallationDoc,
    guideQuickstartDoc,
    guideThemingDoc,
    guideLocalLinkingDoc,
    // 基础组件
    buttonDoc,
    iconDoc,
    tagDoc,
    loadingDoc,
    cellDoc,
    // 表单组件
    inputDoc,
    textareaDoc,
    checkboxDoc,
    radioDoc,
    switchDoc,
    // 反馈与层叠
    dialogDoc,
    toastDoc,
    pickerDoc,
    navbarDoc,
  )

  /** 按 id 查页；找不到返回 null（调用方负责兜底）。 */
  fun byId(id: String): DocEntry? = all.firstOrNull { it.id == id }

  /** 全部分组（顺序即首页渲染顺序）。 */
  val groups: List<DocGroup> = DocGroup.entries
}
