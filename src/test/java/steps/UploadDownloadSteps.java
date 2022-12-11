package steps;

import common.Constants;
import org.testng.Assert;
import pages.Elements.UploadDownloadPage;

import java.io.File;
import java.nio.file.Files;

public class UploadDownloadSteps {
    UploadDownloadPage page = new UploadDownloadPage();

    public UploadDownloadSteps verifyUploadSuccess() {
        page.uploadFile();
        Assert.assertTrue(page.getUploadedPathResult().contains(Constants.Paths.uploadDummyObject.getName()));
        return this;
    }

    public UploadDownloadSteps verifyDownloadSuccess() {
        File downloaded = new File(Constants.Paths.resources.getPath() + "/" + page.getDownloadFileName());
        if (downloaded.exists()) downloaded.delete();
        page.clickDownloadBtn();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertTrue(downloaded.exists());

        return this;
    }
}
