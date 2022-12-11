package steps;

import common.CommonActions;
import common.Config;
import common.Constants;
import org.testng.Assert;
import pages.Forms.FormsPage;

import static tests.Forms.FormsTest.generatedUser;

import java.util.HashMap;

public class FormsSteps {
    FormsPage page = new FormsPage();

    public FormsSteps fillTheForm(HashMap<String, String> parameters) {
        page.enterFirstName(parameters.get("firstName"));
        page.enterLastName(parameters.get("lastName"));
        page.enterEmail(parameters.get("email"));
        page.selectRandomGender();
        page.selectRandomHobbies();
        page.uploadFile();
        page.enterSubject(parameters.get("subject"));
        page.enterBirthDay(parameters.get("birthday"));
        page.enterPhoneNumber(parameters.get("phone"));
        page.enterAddress(parameters.get("address"));
        page.selectStateAndCity(parameters.get("state"), parameters.get("city"));
        return this;
    }

    public FormsSteps submitTheForm() {
        page.clickSubmitBtn();
        return this;
    }

    public void verifyInputAndResultsAreEqual() {
        var results = page.readResults();
        Assert.assertEquals(results.get("Student Name"),
                generatedUser.get("firstName") + " " + generatedUser.get("lastName"));
        Assert.assertEquals(results.get("Student Email"), generatedUser.get("email"));
        Assert.assertEquals(results.get("Mobile"), generatedUser.get("phone"));
        Assert.assertEquals(results.get("Address"), generatedUser.get("address"));
        Assert.assertEquals(results.get("State and City"),
                generatedUser.get("state") + " " + generatedUser.get("city"));
        Assert.assertEquals(results.get("Subjects"), generatedUser.get("subject"));
        Assert.assertEquals(results.get("Picture"), Constants.Paths.uploadDummyObject.getName());
        page.clickCloseModalWindow();
    }
}