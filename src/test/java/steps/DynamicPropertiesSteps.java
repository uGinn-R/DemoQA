package steps;

import common.CommonActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.Elements.DynamicPropertiesPage;

public class DynamicPropertiesSteps {
    DynamicPropertiesPage page = new DynamicPropertiesPage();

    public DynamicPropertiesSteps verifyElementsInitState() {
        Assert.assertFalse(page.getEnabledWithFiveSecDelayBtn().isEnabled(), "enabled but not expected to be");
        Assert.assertEquals(page.getButtonsList().size(), 2, "visible but not expected to be");
        Assert.assertFalse(page.getColorChangeBtn()
                .getAttribute("class")
                .contains("text-danger"), "wrong color");
        return this;
    }

    public DynamicPropertiesSteps verifyElementsNewState() {
        CommonActions.Selenium.waitForVisible(page.getVisibileAfterDelayBtn());
        Assert.assertTrue(page.getEnabledWithFiveSecDelayBtn().isEnabled(), "enabled but not expected to be");
        Assert.assertTrue(page.getButtonsList().size() == 3 && page.getVisibileAfterDelayBtn().isDisplayed(),
                "visible but not expected to be");
        Assert.assertTrue(page.getColorChangeBtn()
                        .getAttribute("class")
                        .contains("text-danger"),
                "wrong color");
        return this;
    }
}
