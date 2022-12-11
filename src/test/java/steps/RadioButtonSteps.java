package steps;

import org.testng.Assert;
import pages.Elements.RadioButtonPage;

public class RadioButtonSteps {
    RadioButtonPage page = new RadioButtonPage();

    public RadioButtonSteps verifyYesOption(){
        page.checkYes();
        Assert.assertEquals(page.getResultText(), "Yes");
        return this;
    }

    public RadioButtonSteps verifyImpressiveOption(){
        page.checkImpressive();
        Assert.assertEquals(page.getResultText(), "Impressive");
        return this;
    }
}
