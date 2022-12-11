package pages.Forms;

import common.CommonActions;
import common.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.devtools.idealized.target.Target;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static common.CommonActions.Selenium.sendKeys;
import static common.Constants.TimeOuts.THREAD_SLEEP_WAIT;

public class FormsPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'custom-radio')]")
    public List<WebElement> RadioBtns;
    @FindBy(how = How.XPATH, using = "//div[contains(@class,'custom-checkbox')]")
    public List<WebElement> Checkboxes;
    @FindBy(how = How.XPATH, using = "//div[@class='modal-content']//tbody/tr")
    public List<WebElement> Results;
    Random rnd = new Random();
    @FindBy(xpath = "//ul[@class='menu-list']//span[contains(text(),'Practice Form')]")
    WebElement PracticeFormMenuBtn;
    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "dateOfBirthInput")
    WebElement birthdayField;
    @FindBy(xpath = "//label[@for='uploadPicture']")
    WebElement uploadBtn;
    @FindBy(id = "subjectsInput")
    WebElement subjectInput;
    @FindBy(id = "userNumber")
    WebElement phoneNumber;
    @FindBy(id = "currentAddress")
    WebElement addressInput;
    @FindBy(id = "react-select-3-input")
    WebElement stateSelector;
    @FindBy(id = "react-select-4-input")
    WebElement citySelector;
    @FindBy(id = "submit")
    WebElement submitBtn;
    @FindBy(id = "closeLargeModal")
    WebElement closeModalBtn;
    @FindBy(tagName = "footer")
    WebElement footer;
    @FindBy(id = "uploadPicture")
    WebElement uploadInput;

    public void selectRandomGender() {
        RadioBtns.get(rnd.nextInt(RadioBtns.size())).click();
    }

    public void selectRandomHobbies() {
        int count = rnd.nextInt(Checkboxes.size());
        CommonActions.Selenium.scrollIntoView(Checkboxes.get(count));
        for (int i = 0; i <= count; i++) {
            Checkboxes.get(i).click();
        }
    }

    public void uploadFile() {
        CommonActions.Selenium.uploadFile(uploadBtn);
        wait.until(ExpectedConditions
                .textToBePresentInElementValue(uploadInput, Constants.Paths.uploadDummyObject.getName()));
    }

/*    public void uploadFile(String path) {
        uploadBtn.click();
        try {
            Thread.sleep(THREAD_SLEEP_WAIT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CommonActions.Clipboard.setText(path);
        CommonActions.ctrlV();
    }*/


    public void enterFirstName(String firstname) {
        sendKeys(firstName, firstname);
        //firstName.sendKeys(firstname);
    }

    public void enterLastName(String lastname) {
        sendKeys(lastName, lastname);
        //lastName.sendKeys(lastname);
    }

    public void enterEmail(String email) {
        sendKeys(userEmail, email);
        //userEmail.sendKeys(email);
    }

    public void enterBirthDay(String birthday) {
        birthdayField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        birthdayField.sendKeys(birthday);
        birthdayField.sendKeys(Keys.RETURN);
    }

    public void enterSubject(String subject) {
        subjectInput.sendKeys(subject);
        wait.until(ExpectedConditions.textToBePresentInElementValue(subjectInput, subject));
        subjectInput.sendKeys(Keys.RETURN);
    }

    public void enterPhoneNumber(String number) {
        sendKeys(phoneNumber, number);
        //phoneNumber.sendKeys(number);
    }

    public void enterAddress(String address) {
        sendKeys(addressInput, address);
        //addressInput.sendKeys(address);
    }

    public void selectStateAndCity(String state, String city) {
        CommonActions.Selenium.jsHideElement(footer);
        stateSelector.sendKeys(state, Keys.RETURN);
        citySelector.sendKeys(city, Keys.RETURN);
    }

    public void clickSubmitBtn() {
        /* Offset is used because clicking at the center of the button failed in CHROME */
        CommonActions.Selenium.scrollIntoView(submitBtn);
        new Actions(driver).moveToElement(submitBtn, -10, -10).click().build().perform();
    }

    public HashMap<String, String> readResults() {
        HashMap<String, String> results = new HashMap<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(Results));
        for (int i = 0; i < Results.size(); i++) {
            String[] str = Results.get(i).getAttribute("innerText").split("\t");
            /* Filling results with modal table data: Lcolumn - key, Rcolumn - value */
            results.put(str[0], str.length == 2 ? str[1] : "");
        }
        return results;
    }

    public void clickCloseModalWindow() {
        CommonActions.Selenium.focusAndClick(closeModalBtn);
    }
}