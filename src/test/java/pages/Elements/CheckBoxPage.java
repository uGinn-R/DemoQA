package pages.Elements;

import common.CommonActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePage;

import java.util.List;

public class CheckBoxPage extends BasePage {

    @FindBy(xpath = "//span[@class='rct-checkbox']")
    public List<WebElement> checkboxes;
    @FindBy(xpath = "//div[@id='result']/span[@class='text-success']")
    public List<WebElement> results;
    @FindBy(xpath = "//*[contains(@class,'rct-icon rct-icon-check')]/../..")
    public List<WebElement> checkedCheckboxesLabels;
    @FindBy(xpath = "//li[contains(@class,'rct-node-parent')]/span/label")
    public List<WebElement> treeGroups;
    @FindBy(xpath = "//div[@class='rct-options']/button[@title='Expand all']")
    WebElement expandAllBtn;
    @FindBy(xpath = "//div[@class='rct-options']/button[@title='Collapse all']")
    WebElement collapseAllBtn;

    public void expandAll() {
        CommonActions.Selenium.focusAndClick(expandAllBtn);
        //expandAllBtn.click();
    }

    public void collapseAll() {
        CommonActions.Selenium.focusAndClick(collapseAllBtn);
        //collapseAllBtn.click();
    }

    public void checkAll() {
        checkboxes.iterator().next().click();
    }

    public void verifyCollapsed() {
        Assert.assertEquals(checkboxes.size(), 1);
    }

    public void verifyExpanded() {
        Assert.assertEquals(checkboxes.size(), 17);
    }

    public void verifyChecked() {
        Assert.assertEquals(results.size(), checkedCheckboxesLabels.size());
    }

    public void clickGroupCheckbox(CheckBoxGroup group) {
        for (WebElement e : treeGroups) {
            if (e.getAttribute("innerText").equals(group.toString())) {
                CommonActions.Selenium.focusAndClick(e);
            }
        }
    }

    public enum CheckBoxGroup {
        Home, Desktop, Documents, WorkSpace, Office, Downloads;
    }
}
