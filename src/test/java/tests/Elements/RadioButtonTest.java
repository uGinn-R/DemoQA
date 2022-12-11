package tests.Elements;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.radioButtonPage;

public class RadioButtonTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(radioButtonPage);
    }

    @Test
    public void testYesRadiosSuccess() {
        radioButtonSteps.verifyYesOption();
    }

    @Test
    public void testImpressiveRadiosSuccess() {
        radioButtonSteps.verifyImpressiveOption();
    }
}
