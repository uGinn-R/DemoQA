package common;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import static common.Config.Uploader;
import static common.Constants.TimeOuts.*;

public class CommonActions {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor js;

    public CommonActions(WebDriver driver, WebDriverWait wait) {
        CommonActions.driver = driver;
        CommonActions.wait = wait;
        js = (JavascriptExecutor) driver;
    }

    public static boolean isGUID(String input) {
        return input.matches(".{8}\\-.{4}\\-.{4}-.{4}\\-.{12}");
    }

    public static void sleep() {
        try {
            Thread.sleep(SHORT_THREAD_SLEEP_WAIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ctrlV() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(Constants.TimeOuts.TINY_THREAD_SLEEP_WAIT);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static class Clipboard {
        public static String getText() {
            try {
                return (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void setText(String input) {
            StringSelection ss = new StringSelection(input);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        }
    }

    public static class Selenium {

        public static void focusAndClick(WebElement element) {
            scrollIntoView(element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            sleep();
            element.click();
            sleep();
        }

        public static void sendKeys(WebElement element, String input) {
            scrollIntoView(element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
            element.sendKeys(input);
        }

        public static WebElement waitForVisible(WebElement element) {
            return wait.until(ExpectedConditions.visibilityOf(element));
        }

        public static void jsClick(WebElement element) {
            js.executeScript("arguments[0].click();", element);
        }

        public static void jsHideElement(WebElement element) {
            js.executeScript("arguments[0].hidden = true;", element);
        }

        public static void scrollIntoView(WebElement element) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }

        public static void scrollPage(int pixelsX, int pixelsY) {
            js.executeScript("window.scrollBy(" + pixelsX + "," + pixelsY + ")");
        }

        public static void makeScreenshot() throws IOException {
            File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(scr, new File
                    ("src/screenshots/" + driver.getTitle() + "_" + System.currentTimeMillis() + ".png"));
        }

        public static void uploadFile(WebElement elementToClickForUpload) {
            focusAndClick(elementToClickForUpload);
            try {
                switch (Uploader) {
                    case AutoIt -> Runtime.getRuntime().exec(Constants.Paths.autoItUploaderExe.getAbsolutePath());
                    case Robot -> {
                        Thread.sleep(THREAD_SLEEP_WAIT);
                        CommonActions.Clipboard.setText(Constants.Paths.uploadDummyObject.getAbsolutePath());
                        CommonActions.ctrlV();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
