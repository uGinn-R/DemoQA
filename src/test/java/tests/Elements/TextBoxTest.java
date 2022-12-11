package tests.Elements;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import steps.TextBoxSteps;
import tests.BaseTest;

import static common.Constants.Urls.textBoxPage;

import java.util.HashMap;

public class TextBoxTest extends BaseTest {
    public static final HashMap<String, String> generatedData = new HashMap<>();

    @BeforeMethod
    public void setUp() {
        goToPage(textBoxPage);
        generatedData.put("userName", faker.name().fullName());
        generatedData.put("userEmail", faker.internet().emailAddress());
        generatedData.put("currentAddress", faker.address().fullAddress());
        generatedData.put("permanentAddress", generatedData.get("currentAddress")); // same

    }
    
    @Test
    public void testVerifySuccess() {
        textBoxSteps.fillTheForm(generatedData);
        textBoxSteps.submitTheForm();
        textBoxSteps.verifyOutputWithInput();
    }
}
