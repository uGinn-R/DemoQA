package steps;

import common.CommonActions;
import pages.BasePage;
import pages.Elements.ButtonsPage;

import java.awt.image.ColorModel;

public class ButtonsSteps {
    ButtonsPage page = new ButtonsPage();


    public ButtonsSteps clickAllBtns() {
        page.clickDoubleClickBtn();
        page.clickRightClickBtn();
        page.clickDynamicIdBtn();
        return this;
    }

    public ButtonsSteps verifyResultMessages() {
        page.verifyDoubleClickResult();
        page.verifyRightClickResult();
        page.verifyDynamicIdBtnClickResult();
        return this;
    }
}
