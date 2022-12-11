package pages.Elements;

import common.CommonActions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static common.CommonActions.Selenium.sendKeys;

public class WebTablesPage extends BasePage {
    @FindBy(id = "addNewRecordButton")
    WebElement addBtn;
    @FindBy(id = "searchBox")
    WebElement searchInputBox;
    @FindBy(id = "basic-addon2")
    WebElement searchBtn;
    @FindBy(id = "//span[contains(@id,'edit-record')]")
    List<WebElement> editBtns;
    @FindBy(xpath = "//span[contains(@id,'delete-record')]")
    List<WebElement> deleteBtns;
    @FindBy(xpath = "//div[@class='action-buttons']/ancestor::div[@role='rowgroup']")
    List<WebElement> filledRows;
    @FindBy(id = "userForm")
    WebElement userForm;
    @FindBy(id = "submit")
    WebElement submitBtn;
    @FindBy(id = "firstName")
    WebElement firstNameInput;
    @FindBy(id = "lastName")
    WebElement lastNameInput;
    @FindBy(id = "userEmail")
    WebElement userEmailInput;
    @FindBy(id = "age")
    WebElement ageInput;
    @FindBy(id = "salary")
    WebElement salaryInput;
    @FindBy(id = "department")
    WebElement departmentInput;

    @FindBy(xpath = "//select[@aria-label='rows per page']")
    WebElement rowSelector;
    @FindBy(xpath = "//button[text()='Next']")
    WebElement nextBtn;

    @FindBy(xpath = "//button[text()='Previous']")
    WebElement previousBtn;

    public WebElement getRowSelector() {
        return rowSelector;
    }

    public List<WebElement> getDeleteBtns() {
        return deleteBtns;
    }

    public void clickDeleteBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(getDeleteBtns().iterator().next())).click();
    }

    public int getFilledRowsCount() {
        return filledRows.size();
    }

    public HashMap<String, String> getAllRowText(int index) {
        String[] cellsText = filledRows.get(index).getAttribute("innerText").trim().split("\n");
        HashMap<String, String> rowText = new HashMap<>();
        rowText.put("First Name", cellsText[0]);
        rowText.put("Last Name", cellsText[1]);
        rowText.put("Age", cellsText[2]);
        rowText.put("Email", cellsText[3]);
        rowText.put("Salary", cellsText[4]);
        rowText.put("Department", cellsText[5]);
        return rowText;
    }

    public void fillFormAndSubmit(HashMap<String, String> data) {
        CommonActions.Selenium.focusAndClick(addBtn);
        CommonActions.Selenium.waitForVisible(userForm);
        sendKeys(firstNameInput, data.get("First Name"));
        sendKeys(lastNameInput, data.get("Last Name"));
        sendKeys(userEmailInput, data.get("Email"));
        sendKeys(ageInput, data.get("Age"));
        sendKeys(salaryInput, data.get("Salary"));
        sendKeys(departmentInput, data.get("Department"));
        CommonActions.Selenium.focusAndClick(submitBtn);
    }

    public void searchForText(String query) {
        CommonActions.Selenium.focusAndClick(searchInputBox);
        sendKeys(searchInputBox, query + Keys.RETURN);
    }
}
