package tests.Elements;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static common.Constants.Urls.uploadDownloadPage;

public class UploadDownloadTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        goToPage(uploadDownloadPage);
    }

    @Test()
    public void testUploadSuccess() {
        UDSteps.verifyUploadSuccess();
    }

    @Test
    public void testDownloadSuccess() {
        UDSteps.verifyDownloadSuccess();
    }
}
