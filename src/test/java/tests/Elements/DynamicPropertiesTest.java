package tests.Elements;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.dynamicPropertiesPage;

public class DynamicPropertiesTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(dynamicPropertiesPage);
    }

    @Test()
    public void testStateOK() {
        dynamicPropertiesSteps.verifyElementsInitState().verifyElementsNewState();
    }
}
