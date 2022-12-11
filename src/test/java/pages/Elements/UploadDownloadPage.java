package pages.Elements;

import common.CommonActions;
import common.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import static common.Constants.TimeOuts.THREAD_SLEEP_WAIT;

public class UploadDownloadPage extends BasePage {
    @FindBy(id = "downloadButton")
    WebElement downloadBtn;
    @FindBy(xpath = "//label[@for='uploadFile']")
    WebElement uploadBtn;
    @FindBy(id = "uploadedFilePath")
    WebElement uploadedFilePath;

    public void uploadFile() {
        CommonActions.Selenium.uploadFile(uploadBtn);
        wait.until(ExpectedConditions
                .textToBePresentInElement(uploadedFilePath, Constants.Paths.uploadDummyObject.getName()));
    }

    public String getUploadedPathResult() {
        return uploadedFilePath.getText();
    }

    public void clickDownloadBtn() {
        downloadBtn.click();
    }

    public String getDownloadFileName() {
        return downloadBtn.getAttribute("download");
    }
}
