/*
 *   YouTestit source code:
 *   ======================
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *  
        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Links:
 *   ======
 *   Homepage : http://www.youtestit.org
 *   Git      : https://github.com/youtestit
 */
package org.youtestit.datamodel.enums;


/**
 * SeleniumActionType
 * 
 * @author "<a href='mailto:patrickguillerm@gmail.com'>Patrick Guillerm</a>"
 * @since Jan 10, 2012
 */
public enum SeleniumActionType {
    /** The add location strategy. */
    addLocationStrategy,

    /** The add location strategy and wait. */
    addLocationStrategyAndWait,

    /** The add script. */
    addScript,

    /** The add script and wait. */
    addScriptAndWait,

    /** The add selection. */
    addSelection,

    /** The add selection and wait. */
    addSelectionAndWait,

    /** The allow native xpath. */
    allowNativeXpath,

    /** The allow native xpath and wait. */
    allowNativeXpathAndWait,

    /** The alt key down. */
    altKeyDown,

    /** The alt key down and wait. */
    altKeyDownAndWait,

    /** The alt key up. */
    altKeyUp,

    /** The alt key up and wait. */
    altKeyUpAndWait,

    /** The answer on next prompt. */
    answerOnNextPrompt,

    /** The assert alert. */
    assertAlert,

    /** The assert alert not present. */
    assertAlertNotPresent,

    /** The assert alert present. */
    assertAlertPresent,

    /** The assert all buttons. */
    assertAllButtons,

    /** The assert all fields. */
    assertAllFields,

    /** The assert all links. */
    assertAllLinks,

    /** The assert all window ids. */
    assertAllWindowIds,

    /** The assert all window names. */
    assertAllWindowNames,

    /** The assert all window titles. */
    assertAllWindowTitles,

    /** The assert attribute. */
    assertAttribute,

    /** The assert attribute from all windows. */
    assertAttributeFromAllWindows,

    /** The assert body text. */
    assertBodyText,

    /** The assert checked. */
    assertChecked,

    /** The assert confirmation. */
    assertConfirmation,

    /** The assert confirmation not present. */
    assertConfirmationNotPresent,

    /** The assert confirmation present. */
    assertConfirmationPresent,

    /** The assert cookie. */
    assertCookie,

    /** The assert cookie by name. */
    assertCookieByName,

    /** The assert cookie not present. */
    assertCookieNotPresent,

    /** The assert cookie present. */
    assertCookiePresent,

    /** The assert css count. */
    assertCssCount,

    /** The assert cursor position. */
    assertCursorPosition,

    /** The assert editable. */
    assertEditable,

    /** The assert element height. */
    assertElementHeight,

    /** The assert element index. */
    assertElementIndex,

    /** The assert element not present. */
    assertElementNotPresent,

    /** The assert element position left. */
    assertElementPositionLeft,

    /** The assert element position top. */
    assertElementPositionTop,

    /** The assert element present. */
    assertElementPresent,

    /** The assert element width. */
    assertElementWidth,

    /** The assert eval. */
    assertEval,

    /** The assert expression. */
    assertExpression,

    /** The assert html source. */
    assertHtmlSource,

    /** The assert location. */
    assertLocation,

    /** The assert mouse speed. */
    assertMouseSpeed,

    /** The assert not alert. */
    assertNotAlert,

    /** The assert not all buttons. */
    assertNotAllButtons,

    /** The assert not all fields. */
    assertNotAllFields,

    /** The assert not all links. */
    assertNotAllLinks,

    /** The assert not all window ids. */
    assertNotAllWindowIds,

    /** The assert not all window names. */
    assertNotAllWindowNames,

    /** The assert not all window titles. */
    assertNotAllWindowTitles,

    /** The assert not attribute. */
    assertNotAttribute,

    /** The assert not attribute from all windows. */
    assertNotAttributeFromAllWindows,

    /** The assert not body text. */
    assertNotBodyText,

    /** The assert not checked. */
    assertNotChecked,

    /** The assert not confirmation. */
    assertNotConfirmation,

    /** The assert not cookie. */
    assertNotCookie,

    /** The assert not cookie by name. */
    assertNotCookieByName,

    /** The assert not css count. */
    assertNotCssCount,

    /** The assert not cursor position. */
    assertNotCursorPosition,

    /** The assert not editable. */
    assertNotEditable,

    /** The assert not element height. */
    assertNotElementHeight,

    /** The assert not element index. */
    assertNotElementIndex,

    /** The assert not element position left. */
    assertNotElementPositionLeft,

    /** The assert not element position top. */
    assertNotElementPositionTop,

    /** The assert not element width. */
    assertNotElementWidth,

    /** The assert not eval. */
    assertNotEval,

    /** The assert not expression. */
    assertNotExpression,

    /** The assert not html source. */
    assertNotHtmlSource,

    /** The assert not location. */
    assertNotLocation,

    /** The assert not mouse speed. */
    assertNotMouseSpeed,

    /** The assert not ordered. */
    assertNotOrdered,

    /** The assert not prompt. */
    assertNotPrompt,

    /** The assert not select options. */
    assertNotSelectOptions,

    /** The assert not selected id. */
    assertNotSelectedId,

    /** The assert not selected ids. */
    assertNotSelectedIds,

    /** The assert not selected index. */
    assertNotSelectedIndex,

    /** The assert not selected indexes. */
    assertNotSelectedIndexes,

    /** The assert not selected label. */
    assertNotSelectedLabel,

    /** The assert not selected labels. */
    assertNotSelectedLabels,

    /** The assert not selected value. */
    assertNotSelectedValue,

    /** The assert not selected values. */
    assertNotSelectedValues,

    /** The assert not something selected. */
    assertNotSomethingSelected,

    /** The assert not speed. */
    assertNotSpeed,

    /** The assert not table. */
    assertNotTable,

    /** The assert not text. */
    assertNotText,

    /** The assert not title. */
    assertNotTitle,

    /** The assert not value. */
    assertNotValue,

    /** The assert not visible. */
    assertNotVisible,

    /** The assert not whether this frame match frame expression. */
    assertNotWhetherThisFrameMatchFrameExpression,

    /** The assert not whether this window match window expression. */
    assertNotWhetherThisWindowMatchWindowExpression,

    /** The assert not xpath count. */
    assertNotXpathCount,

    /** The assert ordered. */
    assertOrdered,

    /** The assert prompt. */
    assertPrompt,

    /** The assert prompt not present. */
    assertPromptNotPresent,

    /** The assert prompt present. */
    assertPromptPresent,

    /** The assert select options. */
    assertSelectOptions,

    /** The assert selected id. */
    assertSelectedId,

    /** The assert selected ids. */
    assertSelectedIds,

    /** The assert selected index. */
    assertSelectedIndex,

    /** The assert selected indexes. */
    assertSelectedIndexes,

    /** The assert selected label. */
    assertSelectedLabel,

    /** The assert selected labels. */
    assertSelectedLabels,

    /** The assert selected value. */
    assertSelectedValue,

    /** The assert selected values. */
    assertSelectedValues,

    /** The assert something selected. */
    assertSomethingSelected,

    /** The assert speed. */
    assertSpeed,

    /** The assert table. */
    assertTable,

    /** The assert text. */
    assertText,

    /** The assert text not present. */
    assertTextNotPresent,

    /** The assert text present. */
    assertTextPresent,

    /** The assert title. */
    assertTitle,

    /** The assert value. */
    assertValue,

    /** The assert visible. */
    assertVisible,

    /** The assert whether this frame match frame expression. */
    assertWhetherThisFrameMatchFrameExpression,

    /** The assert whether this window match window expression. */
    assertWhetherThisWindowMatchWindowExpression,

    /** The assert xpath count. */
    assertXpathCount,

    /** The assign id. */
    assignId,

    /** The assign id and wait. */
    assignIdAndWait,
    // break,
    /** The capture entire page screenshot. */
    captureEntirePageScreenshot,

    /** The capture entire page screenshot and wait. */
    captureEntirePageScreenshotAndWait,

    /** The check. */
    check,

    /** The check and wait. */
    checkAndWait,

    /** The choose cancel on next confirmation. */
    chooseCancelOnNextConfirmation,

    /** The choose ok on next confirmation. */
    chooseOkOnNextConfirmation,

    /** The choose ok on next confirmation and wait. */
    chooseOkOnNextConfirmationAndWait,

    /** The click. */
    click,

    /** The click and wait. */
    clickAndWait,

    /** The click at. */
    clickAt,

    /** The click at and wait. */
    clickAtAndWait,

    /** The close. */
    close,

    /** The context menu. */
    contextMenu,

    /** The context menu and wait. */
    contextMenuAndWait,

    /** The context menu at. */
    contextMenuAt,

    /** The context menu at and wait. */
    contextMenuAtAndWait,

    /** The control key down. */
    controlKeyDown,

    /** The control key down and wait. */
    controlKeyDownAndWait,

    /** The control key up. */
    controlKeyUp,

    /** The control key up and wait. */
    controlKeyUpAndWait,

    /** The create cookie. */
    createCookie,

    /** The create cookie and wait. */
    createCookieAndWait,

    /** The delete all visible cookies. */
    deleteAllVisibleCookies,

    /** The delete all visible cookies and wait. */
    deleteAllVisibleCookiesAndWait,

    /** The delete cookie. */
    deleteCookie,

    /** The delete cookie and wait. */
    deleteCookieAndWait,

    /** The deselect pop up. */
    deselectPopUp,

    /** The deselect pop up and wait. */
    deselectPopUpAndWait,

    /** The double click. */
    doubleClick,

    /** The double click and wait. */
    doubleClickAndWait,

    /** The double click at. */
    doubleClickAt,

    /** The double click at and wait. */
    doubleClickAtAndWait,

    /** The drag and drop. */
    dragAndDrop,

    /** The drag and drop and wait. */
    dragAndDropAndWait,

    /** The drag and drop to object. */
    dragAndDropToObject,

    /** The drag and drop to object and wait. */
    dragAndDropToObjectAndWait,

    /** The dragdrop. */
    dragdrop,

    /** The dragdrop and wait. */
    dragdropAndWait,

    /** The echo. */
    echo,

    /** The fire event. */
    fireEvent,

    /** The fire event and wait. */
    fireEventAndWait,

    /** The focus. */
    focus,

    /** The focus and wait. */
    focusAndWait,

    /** The go back. */
    goBack,

    /** The go back and wait. */
    goBackAndWait,

    /** The highlight. */
    highlight,

    /** The highlight and wait. */
    highlightAndWait,

    /** The ignore attributes without value. */
    ignoreAttributesWithoutValue,

    /** The ignore attributes without value and wait. */
    ignoreAttributesWithoutValueAndWait,

    /** The key down. */
    keyDown,

    /** The key down and wait. */
    keyDownAndWait,

    /** The key press. */
    keyPress,

    /** The key press and wait. */
    keyPressAndWait,

    /** The key up. */
    keyUp,

    /** The key up and wait. */
    keyUpAndWait,

    /** The meta key down. */
    metaKeyDown,

    /** The meta key down and wait. */
    metaKeyDownAndWait,

    /** The meta key up. */
    metaKeyUp,

    /** The meta key up and wait. */
    metaKeyUpAndWait,

    /** The mouse down. */
    mouseDown,

    /** The mouse down and wait. */
    mouseDownAndWait,

    /** The mouse down at. */
    mouseDownAt,

    /** The mouse down at and wait. */
    mouseDownAtAndWait,

    /** The mouse down right. */
    mouseDownRight,

    /** The mouse down right and wait. */
    mouseDownRightAndWait,

    /** The mouse down right at. */
    mouseDownRightAt,

    /** The mouse down right at and wait. */
    mouseDownRightAtAndWait,

    /** The mouse move. */
    mouseMove,

    /** The mouse move and wait. */
    mouseMoveAndWait,

    /** The mouse move at. */
    mouseMoveAt,

    /** The mouse move at and wait. */
    mouseMoveAtAndWait,

    /** The mouse out. */
    mouseOut,

    /** The mouse out and wait. */
    mouseOutAndWait,

    /** The mouse over. */
    mouseOver,

    /** The mouse over and wait. */
    mouseOverAndWait,

    /** The mouse up. */
    mouseUp,

    /** The mouse up and wait. */
    mouseUpAndWait,

    /** The mouse up at. */
    mouseUpAt,

    /** The mouse up at and wait. */
    mouseUpAtAndWait,

    /** The mouse up right. */
    mouseUpRight,

    /** The mouse up right and wait. */
    mouseUpRightAndWait,

    /** The mouse up right at. */
    mouseUpRightAt,

    /** The mouse up right at and wait. */
    mouseUpRightAtAndWait,

    /** The open. */
    open,

    /** The open window. */
    openWindow,

    /** The open window and wait. */
    openWindowAndWait,

    /** The pause. */
    pause,

    /** The refresh. */
    refresh,

    /** The refresh and wait. */
    refreshAndWait,

    /** The remove all selections. */
    removeAllSelections,

    /** The remove all selections and wait. */
    removeAllSelectionsAndWait,

    /** The remove script. */
    removeScript,

    /** The remove script and wait. */
    removeScriptAndWait,

    /** The remove selection. */
    removeSelection,

    /** The remove selection and wait. */
    removeSelectionAndWait,

    /** The rollup. */
    rollup,

    /** The rollup and wait. */
    rollupAndWait,

    /** The run script. */
    runScript,

    /** The run script and wait. */
    runScriptAndWait,

    /** The select. */
    select,

    /** The select and wait. */
    selectAndWait,

    /** The select frame. */
    selectFrame,

    /** The select pop up. */
    selectPopUp,

    /** The select pop up and wait. */
    selectPopUpAndWait,

    /** The select window. */
    selectWindow,

    /** The set browser log level. */
    setBrowserLogLevel,

    /** The set browser log level and wait. */
    setBrowserLogLevelAndWait,

    /** The set cursor position. */
    setCursorPosition,

    /** The set cursor position and wait. */
    setCursorPositionAndWait,

    /** The set mouse speed. */
    setMouseSpeed,

    /** The set mouse speed and wait. */
    setMouseSpeedAndWait,

    /** The set speed. */
    setSpeed,

    /** The set speed and wait. */
    setSpeedAndWait,

    /** The set timeout. */
    setTimeout,

    /** The shift key down. */
    shiftKeyDown,

    /** The shift key down and wait. */
    shiftKeyDownAndWait,

    /** The shift key up. */
    shiftKeyUp,

    /** The shift key up and wait. */
    shiftKeyUpAndWait,

    // liste incomplète : les "store" sont des Accessors et non des Actions
    // (check wether they are in the API or not)
    // store,
    // storeAlert,
    // storeAlertPresent,
    // storeAllButtons,
    // storeAllFields,
    // storeAllLinks,
    // storeAllWindowIds,
    // storeAllWindowNames,
    // storeAllWindowTitles,
    // storeAttribute,
    /** The submit. */
    submit,

    /** The submit and wait. */
    submitAndWait,

    /** The type. */
    type,

    /** The type and wait. */
    typeAndWait,

    /** The type keys. */
    typeKeys,

    /** The type keys and wait. */
    typeKeysAndWait,

    /** The uncheck. */
    uncheck,

    /** The uncheck and wait. */
    uncheckAndWait,

    /** The use xpath library. */
    useXpathLibrary,

    /** The use xpath library and wait. */
    useXpathLibraryAndWait,

    /** The verify alert. */
    verifyAlert,

    /** The verify alert not present. */
    verifyAlertNotPresent,

    /** The verify alert present. */
    verifyAlertPresent,

    /** The verify all buttons. */
    verifyAllButtons,

    /** The verify all fields. */
    verifyAllFields,

    /** The verify all links. */
    verifyAllLinks,

    /** The verify all window ids. */
    verifyAllWindowIds,

    /** The verify all window names. */
    verifyAllWindowNames,

    /** The verify all window titles. */
    verifyAllWindowTitles,

    /** The verify attribute. */
    verifyAttribute,

    /** The verify attribute from all windows. */
    verifyAttributeFromAllWindows,

    /** The verify body text. */
    verifyBodyText,

    /** The verify checked. */
    verifyChecked,

    /** The verify confirmation. */
    verifyConfirmation,

    /** The verify confirmation not present. */
    verifyConfirmationNotPresent,

    /** The verify confirmation present. */
    verifyConfirmationPresent,

    /** The verify cookie. */
    verifyCookie,

    /** The verify cookie by name. */
    verifyCookieByName,

    /** The verify cookie not present. */
    verifyCookieNotPresent,

    /** The verify cookie present. */
    verifyCookiePresent,

    /** The verify css count. */
    verifyCssCount,

    /** The verify cursor position. */
    verifyCursorPosition,

    /** The verify editable. */
    verifyEditable,

    /** The verify element height. */
    verifyElementHeight,

    /** The verify element index. */
    verifyElementIndex,

    /** The verify element not present. */
    verifyElementNotPresent,

    /** The verify element position left. */
    verifyElementPositionLeft,

    /** The verify element position top. */
    verifyElementPositionTop,

    /** The verify element present. */
    verifyElementPresent,

    /** The verify element width. */
    verifyElementWidth,

    /** The verify eval. */
    verifyEval,

    /** The verify expression. */
    verifyExpression,

    /** The verify html source. */
    verifyHtmlSource,

    /** The verify location. */
    verifyLocation,

    /** The verify mouse speed. */
    verifyMouseSpeed,

    /** The verify not alert. */
    verifyNotAlert,

    /** The verify not all buttons. */
    verifyNotAllButtons,

    /** The verify not all fields. */
    verifyNotAllFields,

    /** The verify not all links. */
    verifyNotAllLinks,

    /** The verify not all window ids. */
    verifyNotAllWindowIds,

    /** The verify not all window names. */
    verifyNotAllWindowNames,

    /** The verify not all window titles. */
    verifyNotAllWindowTitles,

    /** The verify not attribute. */
    verifyNotAttribute,

    /** The verify not attribute from all windows. */
    verifyNotAttributeFromAllWindows,

    /** The verify not body text. */
    verifyNotBodyText,

    /** The verify not checked. */
    verifyNotChecked,

    /** The verify not confirmation. */
    verifyNotConfirmation,

    /** The verify not cookie. */
    verifyNotCookie,

    /** The verify not cookie by name. */
    verifyNotCookieByName,

    /** The verify not css count. */
    verifyNotCssCount,

    /** The verify not cursor position. */
    verifyNotCursorPosition,

    /** The verify not editable. */
    verifyNotEditable,

    /** The verify not element height. */
    verifyNotElementHeight,

    /** The verify not element index. */
    verifyNotElementIndex,

    /** The verify not element position left. */
    verifyNotElementPositionLeft,

    /** The verify not element position top. */
    verifyNotElementPositionTop,

    /** The verify not element width. */
    verifyNotElementWidth,

    /** The verify not eval. */
    verifyNotEval,

    /** The verify not expression. */
    verifyNotExpression,

    /** The verify not html source. */
    verifyNotHtmlSource,

    /** The verify not location. */
    verifyNotLocation,

    /** The verify not mouse speed. */
    verifyNotMouseSpeed,

    /** The verify not ordered. */
    verifyNotOrdered,

    /** The verify not prompt. */
    verifyNotPrompt,

    /** The verify not select options. */
    verifyNotSelectOptions,

    /** The verify not selected id. */
    verifyNotSelectedId,

    /** The verify not selected ids. */
    verifyNotSelectedIds,

    /** The verify not selected index. */
    verifyNotSelectedIndex,

    /** The verify not selected indexes. */
    verifyNotSelectedIndexes,

    /** The verify not selected label. */
    verifyNotSelectedLabel,

    /** The verify not selected labels. */
    verifyNotSelectedLabels,

    /** The verify not selected value. */
    verifyNotSelectedValue,

    /** The verify not selected values. */
    verifyNotSelectedValues,

    /** The verify not something selected. */
    verifyNotSomethingSelected,

    /** The verify not speed. */
    verifyNotSpeed,

    /** The verify not table. */
    verifyNotTable,

    /** The verify not text. */
    verifyNotText,

    /** The verify not title. */
    verifyNotTitle,

    /** The verify not value. */
    verifyNotValue,

    /** The verify not visible. */
    verifyNotVisible,

    /** The verify not whether this frame match frame expression. */
    verifyNotWhetherThisFrameMatchFrameExpression,

    /** The verify not whether this window match window expression. */
    verifyNotWhetherThisWindowMatchWindowExpression,

    /** The verify not xpath count. */
    verifyNotXpathCount,

    /** The verify ordered. */
    verifyOrdered,

    /** The verify prompt. */
    verifyPrompt,

    /** The verify prompt not present. */
    verifyPromptNotPresent,

    /** The verify prompt present. */
    verifyPromptPresent,

    /** The verify select options. */
    verifySelectOptions,

    /** The verify selected id. */
    verifySelectedId,

    /** The verify selected ids. */
    verifySelectedIds,

    /** The verify selected index. */
    verifySelectedIndex,

    /** The verify selected indexes. */
    verifySelectedIndexes,

    /** The verify selected label. */
    verifySelectedLabel,

    /** The verify selected labels. */
    verifySelectedLabels,

    /** The verify selected value. */
    verifySelectedValue,

    /** The verify selected values. */
    verifySelectedValues,

    /** The verify something selected. */
    verifySomethingSelected,

    /** The verify speed. */
    verifySpeed,

    /** The verify table. */
    verifyTable,

    /** The verify text. */
    verifyText,

    /** The verify text not present. */
    verifyTextNotPresent,

    /** The verify text present. */
    verifyTextPresent,

    /** The verify title. */
    verifyTitle,

    /** The verify value. */
    verifyValue,

    /** The verify visible. */
    verifyVisible,

    /** The verify whether this frame match frame expression. */
    verifyWhetherThisFrameMatchFrameExpression,

    /** The verify whether this window match window expression. */
    verifyWhetherThisWindowMatchWindowExpression,

    /** The verify xpath count. */
    verifyXpathCount,
    // w
    /** The wait for alert. */
    waitForAlert,

    /** The wait for alert not present. */
    waitForAlertNotPresent,

    /** The wait for alert present. */
    waitForAlertPresent,

    /** The wait for all buttons. */
    waitForAllButtons,

    /** The wait for all fields. */
    waitForAllFields,

    /** The wait for all links. */
    waitForAllLinks,

    /** The wait for all window ids. */
    waitForAllWindowIds,

    /** The wait for all window names. */
    waitForAllWindowNames,

    /** The wait for all window titles. */
    waitForAllWindowTitles,

    /** The wait for attribute. */
    waitForAttribute,

    /** The wait for attribute from all windows. */
    waitForAttributeFromAllWindows,

    /** The wait for body text. */
    waitForBodyText,

    /** The wait for checked. */
    waitForChecked,

    /** The wait for condition. */
    waitForCondition,

    /** The wait for confirmation. */
    waitForConfirmation,

    /** The wait for confirmation not present. */
    waitForConfirmationNotPresent,

    /** The wait for confirmation present. */
    waitForConfirmationPresent,

    /** The wait for cookie. */
    waitForCookie,

    /** The wait for cookie by name. */
    waitForCookieByName,

    /** The wait for cookie not present. */
    waitForCookieNotPresent,

    /** The wait for cookie present. */
    waitForCookiePresent,

    /** The wait for css count. */
    waitForCssCount,

    /** The wait for cursor position. */
    waitForCursorPosition,

    /** The wait for editable. */
    waitForEditable,

    /** The wait for element height. */
    waitForElementHeight,

    /** The wait for element index. */
    waitForElementIndex,

    /** The wait for element not present. */
    waitForElementNotPresent,

    /** The wait for element position left. */
    waitForElementPositionLeft,

    /** The wait for element position top. */
    waitForElementPositionTop,

    /** The wait for element present. */
    waitForElementPresent,

    /** The wait for element width. */
    waitForElementWidth,

    /** The wait for expression. */
    waitForExpression,

    /** The wait for eval. */
    waitForEval,

    /** The wait for frame to load. */
    waitForFrameToLoad,

    /** The wait for html source. */
    waitForHtmlSource,

    /** The wait for location. */
    waitForLocation,

    /** The wait for mouse speed. */
    waitForMouseSpeed,

    /** The wait for not alert. */
    waitForNotAlert,

    /** The wait for not all buttons. */
    waitForNotAllButtons,

    /** The wait for not all fields. */
    waitForNotAllFields,

    /** The wait for not all links. */
    waitForNotAllLinks,

    /** The wait for not all window ids. */
    waitForNotAllWindowIds,

    /** The wait for not all window names. */
    waitForNotAllWindowNames,

    /** The wait for not all window titles. */
    waitForNotAllWindowTitles,

    /** The wait for not attribute. */
    waitForNotAttribute,

    /** The wait for not attribute from all windows. */
    waitForNotAttributeFromAllWindows,

    /** The wait for not body text. */
    waitForNotBodyText,

    /** The wait for not checked. */
    waitForNotChecked,

    /** The wait for not confirmation. */
    waitForNotConfirmation,

    /** The wait for not cookie. */
    waitForNotCookie,

    /** The wait for not cookie by name. */
    waitForNotCookieByName,

    /** The wait for not css count. */
    waitForNotCssCount,

    /** The wait for not cursor position. */
    waitForNotCursorPosition,

    /** The wait for not editable. */
    waitForNotEditable,

    /** The wait for not element height. */
    waitForNotElementHeight,

    /** The wait for not element index. */
    waitForNotElementIndex,

    /** The wait for not element position left. */
    waitForNotElementPositionLeft,

    /** The wait for not element position top. */
    waitForNotElementPositionTop,

    /** The wait for not element width. */
    waitForNotElementWidth,

    /** The wait for not eval. */
    waitForNotEval,

    /** The wait for not expression. */
    waitForNotExpression,

    /** The wait for not html source. */
    waitForNotHtmlSource,

    /** The wait for not location. */
    waitForNotLocation,

    /** The wait for not mouse speed. */
    waitForNotMouseSpeed,

    /** The wait for not ordered. */
    waitForNotOrdered,

    /** The wait for not prompt. */
    waitForNotPrompt,

    /** The wait for not select options. */
    waitForNotSelectOptions,

    /** The wait for not selected id. */
    waitForNotSelectedId,

    /** The wait for not selected ids. */
    waitForNotSelectedIds,

    /** The wait for not selected index. */
    waitForNotSelectedIndex,

    /** The wait for not selected indexes. */
    waitForNotSelectedIndexes,

    /** The wait for not selected label. */
    waitForNotSelectedLabel,

    /** The wait for not selected labels. */
    waitForNotSelectedLabels,

    /** The wait for not selected value. */
    waitForNotSelectedValue,

    /** The wait for not selected values. */
    waitForNotSelectedValues,

    /** The wait for not something selected. */
    waitForNotSomethingSelected,

    /** The wait for not speed. */
    waitForNotSpeed,

    /** The wait for not table. */
    waitForNotTable,

    /** The wait for not text. */
    waitForNotText,

    /** The wait for not title. */
    waitForNotTitle,

    /** The wait for not value. */
    waitForNotValue,

    /** The wait for not visible. */
    waitForNotVisible,

    /** The wait for not whether this frame match frame expression. */
    waitForNotWhetherThisFrameMatchFrameExpression,

    /** The wait for not whether this window match window expression. */
    waitForNotWhetherThisWindowMatchWindowExpression,

    /** The wait for not xpath count. */
    waitForNotXpathCount,

    /** The wait for ordered. */
    waitForOrdered,

    /** The wait for page to load. */
    waitForPageToLoad,

    /** The wait for pop up. */
    waitForPopUp,

    /** The wait for prompt. */
    waitForPrompt,

    /** The wait for prompt not present. */
    waitForPromptNotPresent,

    /** The wait for prompt present. */
    waitForPromptPresent,

    /** The wait for select options. */
    waitForSelectOptions,

    /** The wait for selected id. */
    waitForSelectedId,

    /** The wait for selected ids. */
    waitForSelectedIds,

    /** The wait for selected index. */
    waitForSelectedIndex,

    /** The wait for selected indexes. */
    waitForSelectedIndexes,

    /** The wait for selected label. */
    waitForSelectedLabel,

    /** The wait for selected labels. */
    waitForSelectedLabels,

    /** The wait for selected value. */
    waitForSelectedValue,

    /** The wait for selected values. */
    waitForSelectedValues,

    /** The wait for something selected. */
    waitForSomethingSelected,

    /** The wait for speed. */
    waitForSpeed,

    /** The wait for table. */
    waitForTable,

    /** The wait for text. */
    waitForText,

    /** The wait for text not present. */
    waitForTextNotPresent,

    /** The wait for text present. */
    waitForTextPresent,

    /** The wait for title. */
    waitForTitle,

    /** The wait for value. */
    waitForValue,

    /** The wait for visible. */
    waitForVisible,

    /** The wait for whether this frame match frame expression. */
    waitForWhetherThisFrameMatchFrameExpression,

    /** The wait for whether this window match window expression. */
    waitForWhetherThisWindowMatchWindowExpression,

    /** The wait for xpath count. */
    waitForXpathCount,

    /** The window focus. */
    windowFocus,

    /** The window focus and wait. */
    windowFocusAndWait,

    /** The window maximize. */
    windowMaximize,

    /** The window maximize and wait. */
    windowMaximizeAndWait;


    // =========================================================================
    // ENUM METHODS
    // =========================================================================
    /**
     * Allow to gets a enum with string representation.
     * 
     * @param name the enum name
     * @return the enum or null if it dosn't exists
     */
    public static SeleniumActionType getEnum(final String name) {
        SeleniumActionType res = null;
        if (name != null) {
            for (SeleniumActionType item : SeleniumActionType.values()) {
                if (item.name().equals(name)) {
                    res = item;
                    break;
                }
            }
        }
        return res;
    }
}
