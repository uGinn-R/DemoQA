package steps;

import org.testng.Assert;
import pages.Elements.TextBoxPage;
import tests.Elements.TextBoxTest;

import java.util.HashMap;

public class TextBoxSteps {
    TextBoxPage page = new TextBoxPage();

    public TextBoxSteps fillTheForm(HashMap<String, String> data) {
        page.enterUserName(data.get("userName"));
        page.enterEmail(data.get("userEmail"));
        page.enterCurrentAddress(data.get("currentAddress"));
        page.enterPermanentAddress(data.get("permanentAddress"));
        return this;
    }

    public TextBoxSteps submitTheForm() {
        page.clickSubmitBtn();
        return this;
    }

    public void verifyOutputWithInput() {
        String[] output = page.readOutput();
        Assert.assertEquals(TextBoxTest.generatedData.get("userName"), output[0].split(":")[1]);
        Assert.assertEquals(TextBoxTest.generatedData.get("userEmail"), output[1].split(":")[1]);
        Assert.assertEquals(TextBoxTest.generatedData.get("currentAddress"), output[2].split(":")[1]);
        Assert.assertEquals(TextBoxTest.generatedData.get("permanentAddress"), output[3].split(":")[1]);
    }
}
