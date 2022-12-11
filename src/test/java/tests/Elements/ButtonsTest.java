package tests.Elements;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.buttonsPage;

public class ButtonsTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(buttonsPage);
    }

    @Test
    public void testAllButtonsPressSuccess() {
        buttonsSteps.clickAllBtns().verifyResultMessages();
    }
}
