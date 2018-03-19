package utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObjects.LogIn;
import ru.yandex.qatools.allure.annotations.Attachment;
import webDriver.WebDriverFactory;
import webDriver.WebDriverManager;

import java.util.logging.Level;

public class Listener implements ITestListener {
    private String params;

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] screenshotToAllure() {
        return ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("============Test started: " + result.getMethod().getMethodName() + " ============");
        if (WebDriverManager.getDriver() == null
                || WebDriverManager.getDriver().toString().contains("null")) {
            WebDriverManager.setWebDriver(WebDriverFactory.createInstance());
        }
        LogIn log = new LogIn();
        log.logIn();

    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println("============Test passed: " + tr.getMethod().getMethodName() + " ============");
        screenshotToAllure();
        System.out.println("Screenshot captured");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("============Test failed: " + result.getMethod().getMethodName() + " ============");
        System.out.println("============Log on fail============");
        for (LogEntry entry : WebDriverManager.getDriver().manage().logs().get(LogType.BROWSER).filter(Level.WARNING)) {
            System.out.println(entry.toString());
        }

        screenshotToAllure();
        System.out.println("Screenshot captured");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("============Test skipped: " + iTestResult.getMethod().getMethodName() + " ============");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("======Test failed, but that the expected result=======");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("============Starting testing process============");
        params = new UiUtils().getTime();
        if (WebDriverManager.getDriver() == null
                || WebDriverManager.getDriver().toString().contains("null")) {
            WebDriverManager.setWebDriver(WebDriverFactory.createInstance());
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("==========Finishing testing process============");
        WebDriverManager.getDriver().close();
    }


}