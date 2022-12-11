package tests.Elements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.HashMap;

import static common.Constants.Urls.webTablesPage;

public class WebTablesTest extends BaseTest {
    public static HashMap<String, String> generateData() {
        HashMap<String, String> generatedData = new HashMap<>();
        generatedData.put("First Name", faker.name().firstName());
        generatedData.put("Last Name", faker.name().lastName());
        generatedData.put("Email", faker.internet().emailAddress());
        generatedData.put("Age", String.valueOf(faker.number().numberBetween(18, 99)));
        generatedData.put("Salary", String.valueOf(faker.number().numberBetween(500, 5000)));
        generatedData.put("Department", faker.internet().domainWord());
        return generatedData;
    }

    @BeforeMethod
    public void setUp() {
        generateData();

        goToPage(webTablesPage);
    }

    @Test
    public void testAddingNewItemSuccess() {
        webTablesSteps.verifyAddingNewItem();
    }

    @Test
    public void testDeleteBtn() {
        webTablesSteps.verifyDeleteItemBtn();
    }

    @Test(priority = 5)
    public void testDeleteAllItems() {
        webTablesSteps.verifyDeleteAllItemsSuccess();
    }

    @Test
    public void testSearch() {
        webTablesSteps.verifySearchSuccess("Vega");
    }

    @Test
    public void testAddingMultipleItems() {
        webTablesSteps.verifyAddingMultipleItems(5);
    }
}
