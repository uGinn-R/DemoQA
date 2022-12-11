package pages.Elements;

import common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class TextBoxPage extends BasePage {
    @FindBy(id = "userName")
    WebElement userName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "currentAddress")
    WebElement currentAddress;
    @FindBy(id = "permanentAddress")
    WebElement permanentAddress;
    @FindBy(id = "output")
    WebElement outputDiv;
    @FindBy(id = "submit")
    WebElement submitBtn;

    public void enterUserName(String username) {
        userName.sendKeys(username);
    }

    public void enterEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void enterCurrentAddress(String currentaddress) {
        currentAddress.sendKeys(currentaddress);
    }

    public void enterPermanentAddress(String permanentaddress) {
        permanentAddress.sendKeys(permanentaddress);
    }

    public void clickSubmitBtn() {
        CommonActions.Selenium.focusAndClick(submitBtn);
    }

    public String[] readOutput() {
        return outputDiv.getAttribute("innerText").split("\n\n");
    }
}
