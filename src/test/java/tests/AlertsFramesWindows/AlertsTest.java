package tests.AlertsFramesWindows;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.alertsPage;

public class AlertsTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        goToPage(alertsPage);
    }

    @Test
    public void testAlertBtn() {
        alertsSteps.verifyAlertBtnSuccess();
    }

    @Test
    public void testTimerAlertBtn() {
        alertsSteps.verifyTimerAlertBtnSuccess();
    }

    @Test
    public void testConfirmAlertBtn() {
        alertsSteps.verifyConfirmBtnClick().verifyOkResult();
        alertsSteps.verifyConfirmBtnClick().verifyCancelResult();
    }

    @Test
    public void testPromptBtnSuccess() {
        alertsSteps.verifyPromptAlertBtnSuccess("text");
    }
}
