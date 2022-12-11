package tests.Elements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.linksPage;

public class LinksTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(linksPage);
    }

    @Test
    public void testApiLinksResponses() {
        linksSteps.verifyApiServerResponses();
    }

    @Test
    public void testNewTabLinks() {
        linksSteps.verifyNewTabsLinks();
    }

    @Test
    public void testApiWebUiresponses() {
        linksSteps.verifyApiWebUiResponses();
    }
}
