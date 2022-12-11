package steps;

import org.testng.Assert;
import pages.AlertsFramesWindows.AlertsPage;

public class AlertsSteps {
    AlertsPage page = new AlertsPage();

    public AlertsSteps verifyAlertBtnSuccess() {
        page.clickAlertBtn();
        page.waitForAlert();
        Assert.assertEquals(page.getAlertText(), "You clicked a button");
        page.clickAlertOK();
        return this;
    }

    public AlertsSteps verifyTimerAlertBtnSuccess() {
        page.clickTimerAlertBtn();
        page.waitForAlert();
        Assert.assertEquals(page.getAlertText(), "This alert appeared after 5 seconds");
        page.clickAlertOK();
        return this;
    }

    public AlertsSteps verifyConfirmBtnClick() {
        page.clickConfirmBtn();
        page.waitForAlert();
        Assert.assertEquals(page.getAlertText(), "Do you confirm action?");
        return this;
    }

    public AlertsSteps verifyOkResult() {
        page.clickAlertOK();
        Assert.assertEquals(page.getConfirmResultText(), "You selected Ok");
        return this;
    }

    public AlertsSteps verifyCancelResult() {
        page.clickAlertCancel();
        Assert.assertEquals(page.getConfirmResultText(), "You selected Cancel");
        return this;
    }

    public AlertsSteps verifyPromptAlertBtnSuccess(String text) {
        page.clickPromptBtn();
        page.waitForAlert();
        page.sendTextToAlert(text);
        page.clickAlertOK();
        Assert.assertEquals(page.getPromptResultText(), "You entered " + text);
        return this;
    }
}
