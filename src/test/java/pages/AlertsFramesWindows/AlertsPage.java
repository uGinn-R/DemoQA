package pages.AlertsFramesWindows;

import common.CommonActions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class AlertsPage extends BasePage {
    @FindBy(id = "alertButton")
    WebElement alertBtn;
    @FindBy(id = "timerAlertButton")
    WebElement timerAlertBtn;

    @FindBy(id = "confirmButton")
    WebElement confirmBtn;

    @FindBy(id = "confirmResult")
    WebElement confirmResult;
    @FindBy(id = "promtButton")
    WebElement promptBtn;

    @FindBy(id = "promptResult")
    WebElement promptResult;

    public void clickAlertBtn() {
        CommonActions.Selenium.focusAndClick(alertBtn);
    }

    public void clickTimerAlertBtn() {
        CommonActions.Selenium.focusAndClick(timerAlertBtn);
    }

    public void clickConfirmBtn() {
        CommonActions.Selenium.focusAndClick(confirmBtn);
    }

    public void clickPromptBtn() {
        CommonActions.Selenium.focusAndClick(promptBtn);
    }

    public String getConfirmResultText() {
        return wait.until(ExpectedConditions.visibilityOf(confirmResult)).getAttribute("textContent");
    }

    public String getPromptResultText() {
        return wait.until(ExpectedConditions.visibilityOf(promptResult)).getAttribute("textContent");
    }

    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        CommonActions.sleep();
    }

    public void clickAlertOK() {
        driver.switchTo().alert().accept();
        CommonActions.sleep();
    }

    public void clickAlertCancel() {
        driver.switchTo().alert().dismiss();
        CommonActions.sleep();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
        CommonActions.sleep();
    }
}
