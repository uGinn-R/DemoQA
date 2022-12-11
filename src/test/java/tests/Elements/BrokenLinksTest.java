package tests.Elements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.brokenLinksPage;

public class BrokenLinksTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(brokenLinksPage);
    }

    @Test
    public void testImages() {
        brokenLinksSteps.verifyImageIsBroken();
        brokenLinksSteps.verifyImageIsLoaded();
    }

    @Test
    public void testLinks() {
        brokenLinksSteps.verifyBrokenLinkReturns500();
        brokenLinksSteps.verifyValidLinkResponse();
    }
}
