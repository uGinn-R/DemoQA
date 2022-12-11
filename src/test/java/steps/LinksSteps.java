package steps;

import common.CommonActions;
import common.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pages.Elements.LinksPage;

import java.util.List;

import static common.Constants.TimeOuts.SHORT_THREAD_SLEEP_WAIT;
import static io.restassured.RestAssured.config;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class LinksSteps {
    LinksPage page = new LinksPage();

    public LinksSteps verifyApiServerResponses() {
        /* Had to add the config to avoid ProtocolException when trying to redirect after 301 code */
        config = config().redirect(redirectConfig().followRedirects(false));
        for (WebElement apiLink : page.getApiLinks()) {
            String id = apiLink.getAttribute("id");
            Response response = RestAssured.get(Constants.Urls.root + id);
            apiAssertions(id, response.statusCode());
        }
        return this;
    }

    private void apiAssertions(String id, int actualStatusCode) {
        switch (id) {
            case "created" -> Assert.assertEquals(actualStatusCode, 201);
            case "no-content" -> Assert.assertEquals(actualStatusCode, 204);
            case "moved" -> Assert.assertEquals(actualStatusCode, 301);
            case "bad-request" -> Assert.assertEquals(actualStatusCode, 400);
            case "unauthorized" -> Assert.assertEquals(actualStatusCode, 401);
            case "forbidden" -> Assert.assertEquals(actualStatusCode, 403);
            case "invalid-url" -> Assert.assertEquals(actualStatusCode, 404);
        }
    }

    public LinksSteps verifyApiWebUiResponses() {
        for (WebElement apiLink : page.getApiLinks()) {
            String id = apiLink.getAttribute("id");
            CommonActions.Selenium.focusAndClick(apiLink);
            try {
                Thread.sleep(SHORT_THREAD_SLEEP_WAIT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            apiAssertions(id, page.getWebUiResponseCode());
        }
        return this;
    }

    public LinksSteps verifyNewTabsLinks() {
        for (WebElement e : page.getNewWinLinks()) {
            List<String> WinHandles = page.getWindowHandles();
            e.click();
            page.waitForNumberOfTabs(2);
            List<String> newWinHandles = page.getWindowHandles();
            Assert.assertNotEquals(WinHandles, newWinHandles);
            page.switchToWindow(newWinHandles.get(newWinHandles.size() - 1));
            page.closeTab();
            page.switchToWindow(newWinHandles.get(0));
            page.waitForNumberOfTabs(1);
            newWinHandles = page.getWindowHandles();
            Assert.assertEquals(WinHandles, newWinHandles);
        }
        return this;
    }
}
