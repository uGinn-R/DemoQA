package steps;

import common.CommonActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.Elements.WebTablesPage;
import tests.Elements.WebTablesTest;

import java.util.HashMap;

public class WebTablesSteps {
    WebTablesPage page = new WebTablesPage();

    public WebTablesSteps verifyAddingNewItem() {
        int initRows = page.getFilledRowsCount();
        int expected = initRows + 1;
        var data = WebTablesTest.generateData();
        page.fillFormAndSubmit(data);
        Assert.assertEquals(page.getFilledRowsCount(), initRows + 1); // verify new row is added
        Assert.assertEquals(page.getAllRowText(page.getFilledRowsCount() - 1), data); // verify input and output
        return this;
    }

    public WebTablesSteps verifyAddingMultipleItems(int quantity) {
        CommonActions.Selenium.scrollIntoView(page.getRowSelector());
        /* Weird way to prepare "good" conditions for assertions: if number of rows we should add is more than
         * empty rows on the screen - then switching pagination to more elements so that all rows are visible */
        if (quantity > Integer.parseInt(page.getRowSelector().getAttribute("value")) - page.getFilledRowsCount()) {
            CommonActions.Selenium.focusAndClick(page.getRowSelector());
            page.getRowSelector().sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.RETURN));
        }

        for (; quantity > 0; quantity--)
            verifyAddingNewItem();
        return this;
    }

    public WebTablesSteps verifyDeleteItemBtn() {
        int initRows = page.getFilledRowsCount();
        if (initRows > 0) {
            page.clickDeleteBtn();
            Assert.assertEquals(page.getFilledRowsCount(), initRows - 1);
        }
        return this;
    }

    public WebTablesSteps verifyDeleteAllItemsSuccess() {
        while (page.getDeleteBtns().iterator().hasNext()) {
            page.clickDeleteBtn();
        }
        Assert.assertEquals(page.getFilledRowsCount(), 0); // no rows with text in a table
        return this;
    }

    public WebTablesSteps verifySearchSuccess(String query) {
        page.searchForText(query);
        for (int i = 0; i > page.getFilledRowsCount(); i++) { // for all non-empty rows (with text)
            Assert.assertTrue(page.getAllRowText(i).containsValue(query)); // looking for row containing query text
        }
        return this;
    }
}
