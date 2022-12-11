QA Automation Test Framework for https://www.demoqa.com website

Dependencies used:

AssertJ 3.23.1
Humanize 1.2.2
Java Faker 1.0.2
REST Assured 5.3.0
Selenium 4.6.0
TestNG 7.6.1

Configurable pre-requisites in "Config" class:

[BROWSER] - browser used to run tests, enum selected.
[isHeadless] - optional "Headless" mode. Some tests may fail if TRUE.
[makeScrIfTestFailed] - Make a screenshot of failed test's last step if TRUE.
[TestDataLocale] - locale for generating test data and correct datetime values.
[Uploader] - enum selected file uploading mechanism between AutoIt script and Robot class implementation.
Path to the file for uploading is preconfigured in the "Constants.Paths" class.
Before every TestSuite the AutoIt script is recompiled using the actual path from constants.
The AutoIt option is always preferable, but the limitations are:
- for Windows only
- antiviruses (including Windows Defender) often react on AutoIt files
The "Robot" option should be used in an emergency or for compatibility.