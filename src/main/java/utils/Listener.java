package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;
import webDriver.WebDriverFactory;
import webDriver.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

public class Listener implements ITestListener {
    private String params;

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] screenshotToAllure(File screen) {
        byte[] screenShot = new byte[0];
        try {
            screenShot = Files.readAllBytes(screen.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenShot;
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("============Test started: " + result.getMethod().getMethodName() + " ============");
        if (WebDriverManager.getDriver() == null
                || WebDriverManager.getDriver().toString().contains("null")) {
            WebDriverManager.setWebDriver(WebDriverFactory.createInstance("chrome"));
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        System.out.println("============Test passed: " + tr.getMethod().getMethodName() + " ============");
        String pathSucceed = "target/screenshots/" + params + "/success/" + tr.getMethod().getMethodName() + ".png";
        File screen = captureScreenshot(WebDriverManager.getDriver());
        try {
            FileUtils.copyFile(screen, new File(pathSucceed));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot captured");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("============Test failed: " + result.getMethod().getMethodName() + " ============");
        String pathFailed = "target/screenshots/" + params + "/failed/" + result.getMethod().getMethodName() + ".png";
        System.out.println("============Log on fail============");

        for (LogEntry entry : WebDriverManager.getDriver().manage().logs().get(LogType.BROWSER).filter(Level.WARNING)) {
            System.out.println(entry.toString());
        }

        System.out.println("============Log on fail============");
        File screen = captureScreenshot(WebDriverManager.getDriver());
        try {
            FileUtils.copyFile(screen, new File(pathFailed));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("==========Finishing testing process============");
        WebDriverManager.getDriver().close();
    }

    private File captureScreenshot(WebDriver driver) {
        File file = null;
        try {
            file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenshotToAllure(file);
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        return file;
    }
}