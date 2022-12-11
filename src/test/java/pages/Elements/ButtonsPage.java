package pages.Elements;

import com.beust.ah.A;
import common.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import pages.BasePage;

public class ButtonsPage extends BasePage {
    @FindBy(id = "doubleClickBtn")
    WebElement doubleClickBtn;
    @FindBy(id = "rightClickBtn")
    WebElement rightClickBtn;
    @FindBy(xpath = "//button[text()='Click Me']")
    WebElement dynamicIdBtn;
    @FindBy(id = "doubleClickMessage")
    WebElement doubleClickMessage;
    @FindBy(id = "rightClickMessage")
    WebElement rightClickMessage;
    @FindBy(id = "dynamicClickMessage")
    WebElement dynamicClickMessage;

    public void clickDoubleClickBtn() {
        new Actions(driver).doubleClick(doubleClickBtn).build().perform();
    }

    public void clickRightClickBtn() {
        new Actions(driver).contextClick(rightClickBtn).build().perform();
    }

    public void clickDynamicIdBtn() {
        new Actions(driver).click(dynamicIdBtn).build().perform();
    }

    public void verifyDoubleClickResult() {
        Assert.assertEquals(doubleClickMessage.getText(), "You have done a double click");
    }

    public void verifyRightClickResult() {
        Assert.assertEquals(rightClickMessage.getText(), "You have done a right click");
    }

    public void verifyDynamicIdBtnClickResult() {
        Assert.assertEquals(dynamicClickMessage.getText(), "You have done a dynamic click");
    }
}
