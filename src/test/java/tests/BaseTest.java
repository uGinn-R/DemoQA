package tests;

import com.github.javafaker.Faker;
import common.CommonActions;
import common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import steps.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static common.Config.*;
import static common.Constants.Paths.*;
import static common.Constants.TimeOuts.*;

public abstract class BaseTest {
    public static Faker faker = new Faker(TestDataLocale);
    private static WebDriver driver;
    public FormsSteps formsSteps;
    public TextBoxSteps textBoxSteps;
    public RadioButtonSteps radioButtonSteps;
    public ButtonsSteps buttonsSteps;
    public CheckBoxSteps checkBoxSteps;
    public DynamicPropertiesSteps dynamicPropertiesSteps;
    public UploadDownloadSteps UDSteps;
    public LinksSteps linksSteps;
    public BrokenLinksSteps brokenLinksSteps;
    public WebTablesSteps webTablesSteps;
    public AlertsSteps alertsSteps;

    public static WebDriver getDriver() {
        return driver;
    }

    public ChromeOptions setCustomChromeOptions(File downloadPath) {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath.getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);
        options.setHeadless(isHeadless);
        return options;
    }

    public EdgeOptions setCustomEdgeOptions(File downloadPath) {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath.getAbsolutePath());
        options.setExperimentalOption("prefs", prefs);
        options.setHeadless(isHeadless);
        return options;
    }

    public FirefoxOptions setCustomFireFoxOptions(File downloadPath) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadPath.getAbsolutePath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/zip");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(isHeadless);
        options.setProfile(profile);
        return options;
    }

    public void adBlock() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> Ads = driver.findElements(By.xpath("//div[contains(@id,'ad')]"));
        if (Ads.size() > 0) { //* ADBLOCK :) *//
            for (WebElement ad : Ads) {
                js.executeScript("arguments[0].hidden = true;", ad);
            }
            //System.out.println(Ads.size() + " banners has been hidden");
        }
    }

    @BeforeClass
    public void beforeClass() {
        /*
         * There is no need in SystemProperties and drivers executables since Selenium 4.6.0 is used with
         * built-in WebDriverManager that handles this automatically.
         *
         * Custom Driver Options are provided for tests with download verifications.
         * */
        switch (BROWSER) {
            case CHROME -> driver = new ChromeDriver(setCustomChromeOptions(resources));
            case FIREFOX -> driver = new FirefoxDriver(setCustomFireFoxOptions(resources));
            case EDGE -> driver = new EdgeDriver(setCustomEdgeOptions(resources));
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT));
        driver.manage().window().maximize();

        formsSteps = new FormsSteps();
        textBoxSteps = new TextBoxSteps();
        radioButtonSteps = new RadioButtonSteps();
        buttonsSteps = new ButtonsSteps();
        checkBoxSteps = new CheckBoxSteps();
        dynamicPropertiesSteps = new DynamicPropertiesSteps();
        UDSteps = new UploadDownloadSteps();
        linksSteps = new LinksSteps();
        brokenLinksSteps = new BrokenLinksSteps();
        webTablesSteps = new WebTablesSteps();
        alertsSteps = new AlertsSteps();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        /* Screenshot capturing if test has been failed */
        if (makeScrIfTestFailed) {
            if (iTestResult.getStatus() == ITestResult.FAILURE) {
                try {
                    CommonActions.Selenium.makeScreenshot();
                    System.out.println(iTestResult.getName() + " FAILED! See screenshot for details.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        prepareAutoItUploaderScriptAndCompile();
    }

    public void goToPage(String URL) {
        String path = Objects.equals(URL, Constants.Urls.root) ? URL : Constants.Urls.root + URL;
        driver.get(path);
        /* Banners are overlapping some page elements, so we have to hide them */
        adBlock();
    }

    public void prepareAutoItUploaderScriptAndCompile() {
        try {
            System.out.println("Preparing AutoIt uploader script for compiling...");
            List<String> lines = Files.readAllLines
                    (Path.of(autoitUploaderScript.getAbsolutePath()), StandardCharsets.UTF_8);
            var fos = new FileOutputStream(autoitUploaderScript.getAbsolutePath());
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fos, "UTF-8"));
            for (String s : lines) {
                if (s.contains("ControlSetText")) {
                    String toReplace = s.split(",")[3];
                    String res = s.replace(toReplace.substring(0, toReplace.length() - 2),
                            '"' + uploadDummyObject.getAbsolutePath() + '"');
                    writer.println(res);
                } else
                    writer.println(s);
            }
            writer.close();
            fos.close();
            Thread.sleep(SHORT_THREAD_SLEEP_WAIT);
            System.out.println("Compiling script to exe...");
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "compile.bat"}, null, resources);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}