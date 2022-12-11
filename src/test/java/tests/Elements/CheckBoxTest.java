package tests.Elements;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Elements.CheckBoxPage;
import tests.BaseTest;

import static common.Constants.Urls.checkBoxPage;

public class CheckBoxTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(checkBoxPage);
    }

    @Test
    public void testExpandAndCollapseBtnsSuccess() {
        checkBoxSteps.verifyExpandAndCollapse();
    }

    @Test(priority = 1)
    public void testClickOnGroupSuccess() {
        checkBoxSteps.clickGroupCheckbox(CheckBoxPage.CheckBoxGroup.WorkSpace);
    }

    @Test(priority = 2)
    public void testAllCheckedSuccess() {
        checkBoxSteps.verifyAllSelected();
    }
}
