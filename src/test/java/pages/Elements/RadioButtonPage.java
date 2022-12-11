package pages.Elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class RadioButtonPage extends BasePage {
    @FindBy(xpath = "//p[contains(text(),'You have selected ')]/span") WebElement selectionResult;
    @FindBy(xpath = "//label[@for='yesRadio']") WebElement yesRadio;
    @FindBy(xpath = "//label[@for='impressiveRadio']") WebElement impressiveRadio;

    public void checkYes(){
        new Actions(driver).click(yesRadio).build().perform();
        //yesRadio.click();
    }

    public void checkImpressive(){
        new Actions(driver).click(impressiveRadio).build().perform();

        //impressiveRadio.click();
    }

    public String getResultText(){
        return selectionResult.getText();
    }
}
