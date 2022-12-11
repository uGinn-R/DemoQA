package pages.Elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class DynamicPropertiesPage extends BasePage {
    @FindBy(id = "visibleAfter")
    WebElement VisibleAfterDelayBtn;
    @FindBy(id = "enableAfter")
    WebElement EnabledWithFiveSecDelayBtn;
    @FindBy(id = "colorChange")
    WebElement ColorChangeBtn;
    @FindBy(xpath = "//div/button[@type='button']")
    List<WebElement> buttons;

    public WebElement getEnabledWithFiveSecDelayBtn() {
        return EnabledWithFiveSecDelayBtn;
    }

    public WebElement getColorChangeBtn() {
        return ColorChangeBtn;
    }

    public WebElement getVisibileAfterDelayBtn() {
        return VisibleAfterDelayBtn;
    }

    public List<WebElement> getButtonsList() {
        return buttons;
    }
}
