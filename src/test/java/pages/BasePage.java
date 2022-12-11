package pages;

import common.CommonActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.time.Duration;

import static common.Constants.TimeOuts.EXPLICITLY_WAIT;

public abstract class BasePage {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected CommonActions Common;

    public BasePage() {
        driver = BaseTest.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT));
        js = (JavascriptExecutor) driver;
        Common = new CommonActions(driver, wait);
        PageFactory.initElements(driver, this);
    }
}
