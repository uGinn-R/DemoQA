package pages.Elements;

import common.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class LinksPage extends BasePage {
    @FindBy(xpath = "//a[@href='javascript:void(0)']")
    List<WebElement> apiLinks;

    @FindBy(xpath = "//p[@id='linkResponse']/b[1]")
    WebElement webUiResponseCode;

    @FindBy(xpath = "//div/p/a[@href='https://demoqa.com']")
    List<WebElement> newWinLinks;

    public List<WebElement> getNewWinLinks() {
        return newWinLinks;
    }

    public int getWebUiResponseCode() {
        return Integer.parseInt(webUiResponseCode.getText());
    }

    public List<WebElement> getApiLinks() {
        return apiLinks;
    }

    public void waitForNumberOfTabs(int number) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(number));
    }

    public List<String> getWindowHandles() {
        return driver.getWindowHandles().stream().toList();
    }

    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
    }

    public void closeTab() {
        driver.close();
    }

}
