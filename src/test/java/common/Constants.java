package common;

import java.io.File;

public class Constants {
    public static class Paths {
        public static File resources = new File("src/test/resources");
        public static File uploadDummyObject = new File(resources + "/dummyUpload.png");

        public static File autoitUploaderScript = new File(resources + "/uploader.au3");

        public static File autoItUploaderExe = new File(resources + "/uploader.exe");

    }

    public static class TimeOuts {
        public static int IMPLICITLY_WAIT = 5;
        public static int EXPLICITLY_WAIT = 6;
        public static int THREAD_SLEEP_WAIT = 1500; // ms
        public static int SHORT_THREAD_SLEEP_WAIT = 500; // ms
        public static int TINY_THREAD_SLEEP_WAIT = 100; // ms


    }

    public static class Urls {
        public static final String root = "https://demoqa.com/";
        public static final String formsPage = "automation-practice-form";
        public static final String textBoxPage = "text-box";
        public static final String radioButtonPage = "radio-button";
        public static final String buttonsPage = "buttons";
        public static final String checkBoxPage = "checkbox";
        public static final String dynamicPropertiesPage = "dynamic-properties";
        public static final String uploadDownloadPage = "upload-download";
        public static final String linksPage = "links";
        public static final String brokenLinksPage = "broken";
        public static final String webTablesPage = "webtables";
        public static final String alertsPage = "alerts";

    }
}
