package common;

import java.util.Locale;

public class Config {
    /* Browser switcher */
    public static final Browser BROWSER = Browser.CHROME;
    /* "Headless" browser mode. Some tests may FAIL if TRUE (interactions with WinUI dialogs etc.) */
    public static final boolean isHeadless = false;
    /* Make a screenshot of failed test's last step */
    public static final boolean makeScrIfTestFailed = true;
    /* Locale for Faker data and DateTime */
    public static final Locale TestDataLocale = Locale.US;
    /* Select file uploading mechanism between AutoIt script and Robot class implementation.
     * Path to the file for uploading is preconfigured in the "Constants.Paths" class.
     * Before every TestSuite the AutoIt script is recompiled using the actual path from constants */
    public static final UploadWith Uploader = UploadWith.AutoIt;
}
