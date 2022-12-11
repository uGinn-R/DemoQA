package pages.Elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class BrokenLinksPage extends BasePage {
    @FindBy(xpath = "//div/img")
    List<WebElement> images;

    @FindBy(xpath = "//div/a[contains(text(),'Click Here')]")
    List<WebElement> links;

    public List<WebElement> getImages() {
        return images;
    }

    public List<WebElement> getLinks() {
        return links;
    }
}
