package tests.Forms;

import common.Config;
import common.Constants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.HashMap;

import static common.Constants.Urls.formsPage;
import static humanize.Humanize.formatDate;

public class FormsTest extends BaseTest {
    public static final HashMap<String, String> generatedUser = new HashMap<>();

    @BeforeMethod
    public void setUp() {
        generatedUser.put("firstName", faker.name().firstName());
        generatedUser.put("lastName", faker.name().lastName());
        generatedUser.put("email", faker.internet().emailAddress());
        generatedUser.put("phone", faker.phoneNumber().subscriberNumber(10));
        generatedUser.put("birthday", formatDate(faker.date().birthday(), Config.TestDataLocale));
        generatedUser.put("address", faker.address().streetAddress());
        generatedUser.put("state", "NCR");
        generatedUser.put("city", "Delhi");
        generatedUser.put("subject", "Arts");

        goToPage(formsPage);
    }

    @Test(groups = "Forms")
    public void testFillAndSubmit() {
        formsSteps.fillTheForm(generatedUser)
                .submitTheForm()
                .verifyInputAndResultsAreEqual();
    }
}
