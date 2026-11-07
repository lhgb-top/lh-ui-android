package top.lhgb.ui.docs.pages

import top.lhgb.ui.docs.model.DocEntry

/**
 * 语言 → 文档页数据。由 `scripts/gen-doc-pages.py` 生成，**禁止手改**：
 * 改文档改 docs 下各语言目录的 md 后重跑脚本。
 *
 * 顺序对齐 [top.lhgb.ui.docs.DocRegistry]：指南 → 基础 → 表单 → 反馈与层叠。
 */
fun docEntriesFor(code: String): List<DocEntry> = when (code) {
  "zh" -> zhEntries
  "en" -> enEntries
  "zh-TW" -> zhTWEntries
  "ja" -> jaEntries
  "ko" -> koEntries
  "es" -> esEntries
  "fr" -> frEntries
  "ru" -> ruEntries
  "ar" -> arEntries
  else -> zhEntries
}

private val zhEntries = listOf(
  guideIntroductionDoc, guideInstallationDoc, guideQuickstartDoc, guideThemingDoc, guideLocalLinkingDoc, buttonDoc, iconDoc, tagDoc, textDoc, loadingDoc, cellDoc, inputDoc, textareaDoc, checkboxDoc, radioDoc, switchDoc, dialogDoc, toastDoc, pickerDoc, navbarDoc,
)

private val enEntries = listOf(
  top.lhgb.ui.docs.pages.en.guideIntroductionDoc, top.lhgb.ui.docs.pages.en.guideInstallationDoc, top.lhgb.ui.docs.pages.en.guideQuickstartDoc, top.lhgb.ui.docs.pages.en.guideThemingDoc, top.lhgb.ui.docs.pages.en.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.en.buttonDoc, top.lhgb.ui.docs.pages.en.iconDoc, top.lhgb.ui.docs.pages.en.tagDoc, top.lhgb.ui.docs.pages.en.textDoc, top.lhgb.ui.docs.pages.en.loadingDoc, top.lhgb.ui.docs.pages.en.cellDoc, top.lhgb.ui.docs.pages.en.inputDoc, top.lhgb.ui.docs.pages.en.textareaDoc, top.lhgb.ui.docs.pages.en.checkboxDoc, top.lhgb.ui.docs.pages.en.radioDoc, top.lhgb.ui.docs.pages.en.switchDoc, top.lhgb.ui.docs.pages.en.dialogDoc, top.lhgb.ui.docs.pages.en.toastDoc, top.lhgb.ui.docs.pages.en.pickerDoc, top.lhgb.ui.docs.pages.en.navbarDoc,
)

private val zhTWEntries = listOf(
  top.lhgb.ui.docs.pages.zhTW.guideIntroductionDoc, top.lhgb.ui.docs.pages.zhTW.guideInstallationDoc, top.lhgb.ui.docs.pages.zhTW.guideQuickstartDoc, top.lhgb.ui.docs.pages.zhTW.guideThemingDoc, top.lhgb.ui.docs.pages.zhTW.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.zhTW.buttonDoc, top.lhgb.ui.docs.pages.zhTW.iconDoc, top.lhgb.ui.docs.pages.zhTW.tagDoc, top.lhgb.ui.docs.pages.zhTW.textDoc, top.lhgb.ui.docs.pages.zhTW.loadingDoc, top.lhgb.ui.docs.pages.zhTW.cellDoc, top.lhgb.ui.docs.pages.zhTW.inputDoc, top.lhgb.ui.docs.pages.zhTW.textareaDoc, top.lhgb.ui.docs.pages.zhTW.checkboxDoc, top.lhgb.ui.docs.pages.zhTW.radioDoc, top.lhgb.ui.docs.pages.zhTW.switchDoc, top.lhgb.ui.docs.pages.zhTW.dialogDoc, top.lhgb.ui.docs.pages.zhTW.toastDoc, top.lhgb.ui.docs.pages.zhTW.pickerDoc, top.lhgb.ui.docs.pages.zhTW.navbarDoc,
)

private val jaEntries = listOf(
  top.lhgb.ui.docs.pages.ja.guideIntroductionDoc, top.lhgb.ui.docs.pages.ja.guideInstallationDoc, top.lhgb.ui.docs.pages.ja.guideQuickstartDoc, top.lhgb.ui.docs.pages.ja.guideThemingDoc, top.lhgb.ui.docs.pages.ja.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.ja.buttonDoc, top.lhgb.ui.docs.pages.ja.iconDoc, top.lhgb.ui.docs.pages.ja.tagDoc, top.lhgb.ui.docs.pages.ja.textDoc, top.lhgb.ui.docs.pages.ja.loadingDoc, top.lhgb.ui.docs.pages.ja.cellDoc, top.lhgb.ui.docs.pages.ja.inputDoc, top.lhgb.ui.docs.pages.ja.textareaDoc, top.lhgb.ui.docs.pages.ja.checkboxDoc, top.lhgb.ui.docs.pages.ja.radioDoc, top.lhgb.ui.docs.pages.ja.switchDoc, top.lhgb.ui.docs.pages.ja.dialogDoc, top.lhgb.ui.docs.pages.ja.toastDoc, top.lhgb.ui.docs.pages.ja.pickerDoc, top.lhgb.ui.docs.pages.ja.navbarDoc,
)

private val koEntries = listOf(
  top.lhgb.ui.docs.pages.ko.guideIntroductionDoc, top.lhgb.ui.docs.pages.ko.guideInstallationDoc, top.lhgb.ui.docs.pages.ko.guideQuickstartDoc, top.lhgb.ui.docs.pages.ko.guideThemingDoc, top.lhgb.ui.docs.pages.ko.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.ko.buttonDoc, top.lhgb.ui.docs.pages.ko.iconDoc, top.lhgb.ui.docs.pages.ko.tagDoc, top.lhgb.ui.docs.pages.ko.textDoc, top.lhgb.ui.docs.pages.ko.loadingDoc, top.lhgb.ui.docs.pages.ko.cellDoc, top.lhgb.ui.docs.pages.ko.inputDoc, top.lhgb.ui.docs.pages.ko.textareaDoc, top.lhgb.ui.docs.pages.ko.checkboxDoc, top.lhgb.ui.docs.pages.ko.radioDoc, top.lhgb.ui.docs.pages.ko.switchDoc, top.lhgb.ui.docs.pages.ko.dialogDoc, top.lhgb.ui.docs.pages.ko.toastDoc, top.lhgb.ui.docs.pages.ko.pickerDoc, top.lhgb.ui.docs.pages.ko.navbarDoc,
)

private val esEntries = listOf(
  top.lhgb.ui.docs.pages.es.guideIntroductionDoc, top.lhgb.ui.docs.pages.es.guideInstallationDoc, top.lhgb.ui.docs.pages.es.guideQuickstartDoc, top.lhgb.ui.docs.pages.es.guideThemingDoc, top.lhgb.ui.docs.pages.es.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.es.buttonDoc, top.lhgb.ui.docs.pages.es.iconDoc, top.lhgb.ui.docs.pages.es.tagDoc, top.lhgb.ui.docs.pages.es.textDoc, top.lhgb.ui.docs.pages.es.loadingDoc, top.lhgb.ui.docs.pages.es.cellDoc, top.lhgb.ui.docs.pages.es.inputDoc, top.lhgb.ui.docs.pages.es.textareaDoc, top.lhgb.ui.docs.pages.es.checkboxDoc, top.lhgb.ui.docs.pages.es.radioDoc, top.lhgb.ui.docs.pages.es.switchDoc, top.lhgb.ui.docs.pages.es.dialogDoc, top.lhgb.ui.docs.pages.es.toastDoc, top.lhgb.ui.docs.pages.es.pickerDoc, top.lhgb.ui.docs.pages.es.navbarDoc,
)

private val frEntries = listOf(
  top.lhgb.ui.docs.pages.fr.guideIntroductionDoc, top.lhgb.ui.docs.pages.fr.guideInstallationDoc, top.lhgb.ui.docs.pages.fr.guideQuickstartDoc, top.lhgb.ui.docs.pages.fr.guideThemingDoc, top.lhgb.ui.docs.pages.fr.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.fr.buttonDoc, top.lhgb.ui.docs.pages.fr.iconDoc, top.lhgb.ui.docs.pages.fr.tagDoc, top.lhgb.ui.docs.pages.fr.textDoc, top.lhgb.ui.docs.pages.fr.loadingDoc, top.lhgb.ui.docs.pages.fr.cellDoc, top.lhgb.ui.docs.pages.fr.inputDoc, top.lhgb.ui.docs.pages.fr.textareaDoc, top.lhgb.ui.docs.pages.fr.checkboxDoc, top.lhgb.ui.docs.pages.fr.radioDoc, top.lhgb.ui.docs.pages.fr.switchDoc, top.lhgb.ui.docs.pages.fr.dialogDoc, top.lhgb.ui.docs.pages.fr.toastDoc, top.lhgb.ui.docs.pages.fr.pickerDoc, top.lhgb.ui.docs.pages.fr.navbarDoc,
)

private val ruEntries = listOf(
  top.lhgb.ui.docs.pages.ru.guideIntroductionDoc, top.lhgb.ui.docs.pages.ru.guideInstallationDoc, top.lhgb.ui.docs.pages.ru.guideQuickstartDoc, top.lhgb.ui.docs.pages.ru.guideThemingDoc, top.lhgb.ui.docs.pages.ru.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.ru.buttonDoc, top.lhgb.ui.docs.pages.ru.iconDoc, top.lhgb.ui.docs.pages.ru.tagDoc, top.lhgb.ui.docs.pages.ru.textDoc, top.lhgb.ui.docs.pages.ru.loadingDoc, top.lhgb.ui.docs.pages.ru.cellDoc, top.lhgb.ui.docs.pages.ru.inputDoc, top.lhgb.ui.docs.pages.ru.textareaDoc, top.lhgb.ui.docs.pages.ru.checkboxDoc, top.lhgb.ui.docs.pages.ru.radioDoc, top.lhgb.ui.docs.pages.ru.switchDoc, top.lhgb.ui.docs.pages.ru.dialogDoc, top.lhgb.ui.docs.pages.ru.toastDoc, top.lhgb.ui.docs.pages.ru.pickerDoc, top.lhgb.ui.docs.pages.ru.navbarDoc,
)

private val arEntries = listOf(
  top.lhgb.ui.docs.pages.ar.guideIntroductionDoc, top.lhgb.ui.docs.pages.ar.guideInstallationDoc, top.lhgb.ui.docs.pages.ar.guideQuickstartDoc, top.lhgb.ui.docs.pages.ar.guideThemingDoc, top.lhgb.ui.docs.pages.ar.guideLocalLinkingDoc, top.lhgb.ui.docs.pages.ar.buttonDoc, top.lhgb.ui.docs.pages.ar.iconDoc, top.lhgb.ui.docs.pages.ar.tagDoc, top.lhgb.ui.docs.pages.ar.textDoc, top.lhgb.ui.docs.pages.ar.loadingDoc, top.lhgb.ui.docs.pages.ar.cellDoc, top.lhgb.ui.docs.pages.ar.inputDoc, top.lhgb.ui.docs.pages.ar.textareaDoc, top.lhgb.ui.docs.pages.ar.checkboxDoc, top.lhgb.ui.docs.pages.ar.radioDoc, top.lhgb.ui.docs.pages.ar.switchDoc, top.lhgb.ui.docs.pages.ar.dialogDoc, top.lhgb.ui.docs.pages.ar.toastDoc, top.lhgb.ui.docs.pages.ar.pickerDoc, top.lhgb.ui.docs.pages.ar.navbarDoc,
)

