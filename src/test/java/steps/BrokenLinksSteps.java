package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import pages.Elements.BrokenLinksPage;

public class BrokenLinksSteps {
    BrokenLinksPage page = new BrokenLinksPage();

    public BrokenLinksSteps verifyImageIsLoaded() {
        Assert.assertTrue(Integer.parseInt(page.getImages().get(0).getAttribute("naturalWidth")) > 0);
        return this;
    }

    public BrokenLinksSteps verifyImageIsBroken() {
        Assert.assertTrue(Integer.parseInt(page.getImages().get(1).getAttribute("naturalWidth")) == 0);
        return this;
    }

    public BrokenLinksSteps verifyBrokenLinkReturns500() {
        Response response = RestAssured.get(page.getLinks().get(1).getAttribute("href"));
        Assert.assertEquals(response.getStatusCode(), 500);
        return this;
    }

    public BrokenLinksSteps verifyValidLinkResponse() {
        Response response = RestAssured.get(page.getLinks().get(0).getAttribute("href"));
        Assert.assertEquals(response.getStatusCode(), 200);
        return this;
    }
}
